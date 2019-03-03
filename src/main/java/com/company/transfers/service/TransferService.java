package com.company.transfers.service;

import com.company.transfers.resources.dto.TransferDTO;

import java.util.List;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public interface TransferService {

    TransferDTO create(TransferDTO transfer);

    TransferDTO get(Long id);

    List<TransferDTO> list();
}
