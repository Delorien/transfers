package com.company.transfers.repository.impl;

import com.company.transfers.exception.AccountAlreadyExistException;
import com.company.transfers.repository.AccountRepository;
import com.company.transfers.repository.dao.AccountDAO;
import com.company.transfers.repository.model.Account;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public class AccountRepositoryImpl implements AccountRepository {

    private static final Class<AccountDAO> CLASS = AccountDAO.class;
    private final Jdbi jdbi;

    @Inject
    public AccountRepositoryImpl(final Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public Account save(final Account account) {
        try {
            Long id = jdbi.withExtension(CLASS, dao -> dao.insert(account));
            return jdbi.withExtension(CLASS, dao -> dao.findById(id).get());
        } catch (UnableToExecuteStatementException exception) {
            throw resolveException(exception);
        }
    }

    @Override
    public Optional<Account> findById(final Long id) {
        return jdbi.withExtension(CLASS, dao -> dao.findById(id));
    }

    @Override
    public List<Account> list() {
        return jdbi.withExtension(CLASS, dao -> dao.list());
    }

    @Override
    public Account subtractBalanceFromAcount(final BigDecimal amount, final Long id) {
        return jdbi.inTransaction(handle -> handle.attach(CLASS).subtractBalanceFromAcount(amount, id));
    }

    @Override
    public Account chargeBalanceToAccount(final BigDecimal amount, final Long id) {
        return jdbi.inTransaction(handle -> handle.attach(CLASS).chargeBalanceToAccount(amount, id));
    }

    private RuntimeException resolveException(UnableToExecuteStatementException exception) {
        if (exception.getCause() != null && exception.getCause() instanceof JdbcSQLIntegrityConstraintViolationException) {
            return new AccountAlreadyExistException();
        }
        return exception;
    }
}
