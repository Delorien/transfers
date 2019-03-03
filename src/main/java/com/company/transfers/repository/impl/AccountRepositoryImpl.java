package com.company.transfers.repository.impl;

import com.company.transfers.repository.AccountRepository;
import com.company.transfers.repository.dao.AccountDAO;
import com.company.transfers.repository.model.Account;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Inject;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public class AccountRepositoryImpl implements AccountRepository {

    public static final Class<AccountDAO> CLASS = AccountDAO.class;
    private final Jdbi jdbi;

    @Inject
    public AccountRepositoryImpl(final Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public Account save(Account account) {
        Long id = jdbi.withExtension(CLASS, repository -> repository.insert(account));
        return jdbi.withExtension(CLASS, repository -> repository.findById(id));
    }

    @Override
    public Account findById(Long id) {
        return jdbi.withExtension(CLASS, repository -> repository.findById(id));
    }

    @Override
    public Boolean update(Account account) {
        return jdbi.withExtension(CLASS, repository -> repository.updateBalance(account.getBalance(), account.getId()));
    }
}
