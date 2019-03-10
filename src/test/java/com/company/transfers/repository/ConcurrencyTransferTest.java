package com.company.transfers.repository;

import com.company.transfers.configuration.Persistence;
import com.company.transfers.repository.impl.AccountRepositoryImpl;
import com.company.transfers.repository.model.Account;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by Leonardo Tonin on 09/03/19.
 */
@RunWith(JUnit4.class)
public class ConcurrencyTransferTest {

    private final AccountRepository repository = new AccountRepositoryImpl(Persistence.getInstance().getJdbi());
    private CyclicBarrier barrier;

    @BeforeClass
    public static void beforeClass() {
        Persistence.getInstance().initializeDatabase();
    }

    @Before
    public void beforeTest() {
        this.barrier = new CyclicBarrier(3);
    }

    @Test
    public void concurrentTransferMustBePossible() throws InterruptedException {

        Long originId = repository.save(getOriginAccount()).getId();
        Long receiverId = repository.save(getReceiverAccount()).getId();

        Thread updateA = new Thread(() -> {
            await();
            repository.moveAmount(originId, receiverId, new BigDecimal(100));
        });

        Thread updateB = new Thread(() -> {
            await();
            repository.moveAmount(originId, receiverId, new BigDecimal(50));
        });

        updateA.start();
        updateB.start();

        await();

        updateA.join();
        updateB.join();

        BigDecimal finalOriginBalance = repository.findById(originId).get().getBalance();
        BigDecimal finalReceiverBalance = repository.findById(receiverId).get().getBalance();

        assertThat(finalOriginBalance, is(equalTo(new BigDecimal(850))));
        assertThat(finalReceiverBalance, is(equalTo(new BigDecimal(150))));
    }

    private Account getOriginAccount() {
        Account account = new Account();
        account.setDocument(Integer.toString(new Random().nextInt(90000)));
        account.setBalance(new BigDecimal(1000.0));
        return account;
    }

    private Account getReceiverAccount() {
        Account account = new Account();
        account.setDocument(Integer.toString(new Random().nextInt(90000)));
        account.setBalance(new BigDecimal(0.0));
        return account;
    }

    private void await() {
        try {
            barrier.await();
        } catch (Exception e) {
        }
    }
}
