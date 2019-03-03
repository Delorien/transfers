package com.company.transfers.repository;

import com.company.transfers.repository.model.Transfer;

import java.util.List;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public interface TransferRepository {

    Transfer save(Transfer transfer);

    Transfer findById(Long id);

    List<Transfer> list();
}
