package com.company.transfers.configuration;

import com.company.transfers.repository.AccountBalanceRepository;
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
        jdbi.useExtension(AccountBalanceRepository.class, AccountBalanceRepository::createTable);
    }

    private Jdbi configureJdbiConnection() {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:transfers;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false");
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }
}
