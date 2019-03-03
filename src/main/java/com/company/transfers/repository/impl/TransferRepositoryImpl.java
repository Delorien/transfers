package com.company.transfers.repository.impl;

import com.company.transfers.repository.TransferRepository;
import com.company.transfers.repository.dao.TransferDAO;
import com.company.transfers.repository.model.Transfer;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Inject;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public class TransferRepositoryImpl implements TransferRepository {

    public static final Class<TransferDAO> CLASS = TransferDAO.class;
    private final Jdbi jdbi;

    @Inject
    public TransferRepositoryImpl(final Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public Transfer save(Transfer transfer) {
        Long id = jdbi.withExtension(CLASS, repository -> repository.insert(transfer));
        return jdbi.withExtension(CLASS, repository -> repository.findById(id));
    }
}
