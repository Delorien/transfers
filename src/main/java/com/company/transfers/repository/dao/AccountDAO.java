package com.company.transfers.repository.dao;

import com.company.transfers.exception.AccountNotFoundException;
import com.company.transfers.repository.model.Account;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public interface AccountDAO {

    String ACCOUNT_TABLE = "account";

    @SqlUpdate("CREATE TABLE " + ACCOUNT_TABLE + " (id INT PRIMARY KEY AUTO_INCREMENT, document VARCHAR UNIQUE, balance DECIMAL)")
    void createTable();

    @SqlUpdate("INSERT INTO " + ACCOUNT_TABLE + " (document, balance) VALUES (:document, :balance)")
    @GetGeneratedKeys("id")
    Long insert(@BindBean Account account);

    @SqlQuery("SELECT * FROM " + ACCOUNT_TABLE + " WHERE id=:id")
    @RegisterConstructorMapper(Account.class)
    Optional<Account> findById(@Bind("id") Long id);

    @SqlQuery("SELECT * FROM " + ACCOUNT_TABLE + " WHERE id=:id FOR UPDATE")
    @RegisterConstructorMapper(Account.class)
    Optional<Account> findByIdLockingForUpdate(@Bind("id") Long id);

    @SqlUpdate("UPDATE " + ACCOUNT_TABLE + " SET balance=:balance WHERE id=:id")
    @RegisterBeanMapper(Account.class)
    Boolean updateBalance(@Bind("balance") BigDecimal balance, @Bind("id") Long id);

    @SqlQuery("SELECT * FROM " + ACCOUNT_TABLE)
    @RegisterConstructorMapper(Account.class)
    List<Account> list();

    @Transaction
    default Account subtractBalanceFromAcount(BigDecimal amount, Long id) {
        Account account = findByIdLockingForUpdate(id).orElseThrow(AccountNotFoundException::new);
        account.subtractFromBalance(amount);
        updateBalance(account.getBalance(), account.getId());
        return account;
    }

    @Transaction
    default Account chargeBalanceToAccount(BigDecimal amount, Long id) {
        Account account = findByIdLockingForUpdate(id).orElseThrow(AccountNotFoundException::new);
        account.chargeToBalance(amount);
        updateBalance(account.getBalance(), account.getId());
        return account;
    }
}
