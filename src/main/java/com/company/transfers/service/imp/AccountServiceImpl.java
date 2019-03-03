package com.company.transfers.service.imp;

import com.company.transfers.repository.AccountRepository;
import com.company.transfers.repository.model.Account;
import com.company.transfers.resources.dto.AccountDTO;
import com.company.transfers.service.AccountService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final ModelMapper modelMapper;

    @Inject
    public AccountServiceImpl(final AccountRepository repository, final ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountDTO save(AccountDTO account) {
        Account saved = repository.save(modelMapper.map(account, Account.class));
        return modelMapper.map(saved, AccountDTO.class);
    }

    @Override
    public AccountDTO get(Long id) {
        return modelMapper.map(repository.findById(id), AccountDTO.class);
    }

    @Override
    public List<AccountDTO> list() {
        return repository.list().stream().map(account -> modelMapper.map(account, AccountDTO.class)).collect(Collectors.toList());
    }

    @Override
    public AccountDTO removeFromAccount(AccountDTO origin, BigDecimal amount) {
        Account account = repository.findById(origin.getId());
        account.setBalance(account.getBalance().subtract(amount));
        repository.update(account);
        return modelMapper.map(account, AccountDTO.class);
    }

    @Override
    public AccountDTO chargeInAccount(AccountDTO receiver, BigDecimal amount) {
        Account account = repository.findById(receiver.getId());
        account.setBalance(account.getBalance().add(amount));
        repository.update(account);
        return modelMapper.map(account, AccountDTO.class);
    }
}