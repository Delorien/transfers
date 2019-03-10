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

import static org.junit.Assert.assertEquals;

/**
 * Created by Leonardo Tonin on 09/03/19.
 */
@RunWith(JUnit4.class)
public class ConcurrencyBalanceTest {

    private AccountRepository repository = new AccountRepositoryImpl(Persistence.getInstance().getJdbi());
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
    public void concurrentWithdrawMustBePossible() throws InterruptedException {

        Long id = repository.save(getAccount()).getId();

        Thread updateA = new Thread(() -> {
            await();
            repository.subtractBalanceFromAcount(new BigDecimal(100), id);
        });

        Thread updateB = new Thread(() -> {
            await();
            repository.subtractBalanceFromAcount(new BigDecimal(100), id);
        });

        updateA.start();
        updateB.start();

        await();

        updateA.join();
        updateB.join();

        BigDecimal finalBalance = repository.findById(id).get().getBalance();
        assertEquals(new BigDecimal(800), finalBalance);
    }

    @Test
    public void concurrentChargeMustBePossible() throws InterruptedException {
        Long id = repository.save(getAccount()).getId();

        Thread updateA = new Thread(() -> {
            await();
            repository.chargeBalanceToAccount(new BigDecimal(100), id);
        });

        Thread updateB = new Thread(() -> {
            await();
            repository.chargeBalanceToAccount(new BigDecimal(100), id);
        });

        updateA.start();
        updateB.start();

        await();

        updateA.join();
        updateB.join();

        BigDecimal finalBalance = repository.findById(id).get().getBalance();
        assertEquals(new BigDecimal(1200), finalBalance);
    }

    private Account getAccount() {
        Account account = new Account();
        account.setDocument(Integer.toString(new Random().nextInt(90000)));
        account.setBalance(new BigDecimal(1000.0));
        return account;
    }

    private void await() {
        try {
            barrier.await();
        } catch (Exception e) {
        }
    }
}
