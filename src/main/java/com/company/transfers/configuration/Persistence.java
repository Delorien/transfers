package com.company.transfers.configuration;

import com.company.transfers.repository.dao.AccountDAO;
import com.company.transfers.repository.dao.TransferDAO;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public class Persistence {

    private static Persistence INSTANCE;
    private final Jdbi jdbi;

    private Persistence() {
        jdbi = configureJdbiConnection();
    }

    public static Persistence getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Persistence();
        }

        return INSTANCE;
    }

    public Jdbi getJdbi() {
        return jdbi;
    }

    public void initializeDatabase() {
        jdbi.useExtension(AccountDAO.class, AccountDAO::createTable);
        jdbi.useExtension(TransferDAO.class, TransferDAO::createTable);
    }

    private Jdbi configureJdbiConnection() {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:transfers;MV_STORE=FALSE;LOCK_MODE=1;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false");
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }
}
