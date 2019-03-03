package com.company.transfers.service.imp;

import com.company.transfers.repository.TransferRepository;
import com.company.transfers.repository.model.Transfer;
import com.company.transfers.resources.dto.AccountDTO;
import com.company.transfers.resources.dto.TransferDTO;
import com.company.transfers.service.AccountService;
import com.company.transfers.service.TransferService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public class TransferServiceImpl implements TransferService {

    private final AccountService accountService;
    private final TransferRepository repository;
    private final ModelMapper modelMapper;

    @Inject
    public TransferServiceImpl(final AccountService accountService, final TransferRepository repository, final ModelMapper modelMapper) {
        this.accountService = accountService;
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TransferDTO create(TransferDTO transfer) {

        AccountDTO origin = accountService.removeFromAccount(transfer.getOrigin(), transfer.getAmount());
        AccountDTO receiver = accountService.chargeInAccount(transfer.getReceiver(), transfer.getAmount());

        Transfer saved = repository.save(modelMapper.map(transfer, Transfer.class));

        TransferDTO result = modelMapper.map(saved, TransferDTO.class);
        result.setOrigin(origin);
        result.setReceiver(receiver);
        return result;
    }

    @Override
    public TransferDTO get(Long id) {
        return modelMapper.map(repository.findById(id), TransferDTO.class);
    }

    @Override
    public List<TransferDTO> list() {
        return repository.list().stream().map(transfer -> modelMapper.map(transfer, TransferDTO.class)).collect(Collectors.toList());
    }
}
