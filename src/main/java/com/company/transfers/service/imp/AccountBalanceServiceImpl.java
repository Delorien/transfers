package com.company.transfers.service.imp;

import com.company.transfers.repository.AccountBalanceRepository;
import com.company.transfers.repository.model.AccountBalance;
import com.company.transfers.resources.dto.AccountBalanceDTO;
import com.company.transfers.service.AccountBalanceService;
import org.jdbi.v3.core.Jdbi;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public class AccountBalanceServiceImpl implements AccountBalanceService {

    private final Jdbi jdbi;
    private final ModelMapper modelMapper;

    @Inject
    public AccountBalanceServiceImpl(final Jdbi jdbi, final ModelMapper modelMapper) {
        this.jdbi = jdbi;
        this.modelMapper = modelMapper;
    }

    public AccountBalanceDTO save(AccountBalanceDTO accountBalance) {
        Long id = jdbi.withExtension(AccountBalanceRepository.class, repository -> repository.insert(modelMapper.map(accountBalance, AccountBalance.class)));
        accountBalance.setId(id);
        return accountBalance;
    }
}