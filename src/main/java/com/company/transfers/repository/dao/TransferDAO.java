package com.company.transfers.repository.dao;

import com.company.transfers.repository.model.Transfer;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public interface TransferDAO {

    String TRANSFER_TABLE = "transfer";

    @SqlUpdate("CREATE TABLE IF NOT EXISTS " + TRANSFER_TABLE + " (id INT PRIMARY KEY AUTO_INCREMENT, originId INT, receiverId INT, amount DECIMAL, date DATE)")
    void createTable();

    @SqlUpdate("INSERT INTO " + TRANSFER_TABLE + " (originId, receiverId, amount, date) values (:originId, :receiverId, :amount, CURRENT_TIMESTAMP())")
    @GetGeneratedKeys("id")
    Long insert(@BindBean Transfer transfer);

    @SqlQuery("select * from " + TRANSFER_TABLE + " where id=:id")
    @RegisterConstructorMapper(Transfer.class)
    Optional<Transfer> findById(@Bind("id") Long id);

    @SqlQuery("select * from " + TRANSFER_TABLE)
    @RegisterConstructorMapper(Transfer.class)
    List<Transfer> list();
}
