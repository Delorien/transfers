package com.company.transfers.configuration;

import com.company.transfers.repository.AccountRepository;
import com.company.transfers.repository.TransferRepository;
import com.company.transfers.repository.impl.AccountRepositoryImpl;
import com.company.transfers.repository.impl.TransferRepositoryImpl;
import com.company.transfers.service.AccountService;
import com.company.transfers.service.TransferService;
import com.company.transfers.service.imp.AccountServiceImpl;
import com.company.transfers.service.imp.TransferServiceImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.jdbi.v3.core.Jdbi;
import org.modelmapper.ModelMapper;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public class ApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(AccountServiceImpl.class).to(AccountService.class);
        bind(AccountRepositoryImpl.class).to(AccountRepository.class);
        bind(TransferServiceImpl.class).to(TransferService.class);
        bind(TransferRepositoryImpl.class).to(TransferRepository.class);
        bind(Persistence.getInstance().getJdbi()).to(Jdbi.class);
        bind(new ModelMapper()).to(ModelMapper.class);
    }
}
