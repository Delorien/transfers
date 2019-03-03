package com.company.transfers.repository;

import com.company.transfers.repository.model.AccountBalance;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public interface AccountBalanceRepository {

    String ACCOUNT_BALANCE = "account_balance";

    @SqlUpdate("CREATE TABLE " + ACCOUNT_BALANCE + " (id INT PRIMARY KEY AUTO_INCREMENT, account_id VARCHAR, balance DECIMAL)")
    void createTable();

    @SqlUpdate("INSERT INTO " + ACCOUNT_BALANCE + " (account_id, balance) values (:accountId, :balance)")
    @GetGeneratedKeys("id")
    Long insert(@BindBean AccountBalance accountBalance);
}
