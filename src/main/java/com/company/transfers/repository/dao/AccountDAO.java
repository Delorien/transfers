package com.company.transfers.repository.dao;

import com.company.transfers.repository.model.Account;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

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

    @SqlUpdate("INSERT INTO " + ACCOUNT_TABLE + " (document, balance) values (:document, :balance)")
    @GetGeneratedKeys("id")
    Long insert(@BindBean Account account);

    @SqlQuery("select * from " + ACCOUNT_TABLE + " where id=:id")
    @RegisterConstructorMapper(Account.class)
    Optional<Account> findById(@Bind("id") Long id);

    @SqlUpdate("UPDATE " + ACCOUNT_TABLE + " SET balance=:balance where id=:id")
    @RegisterBeanMapper(Account.class)
    Boolean updateBalance(@Bind("balance") BigDecimal balance, @Bind("id") Long id);

    @SqlQuery("select * from " + ACCOUNT_TABLE)
    @RegisterConstructorMapper(Account.class)
    List<Account> list();
}
