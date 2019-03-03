package com.company.transfers.configuration;

import com.company.transfers.service.AccountBalanceService;
import com.company.transfers.service.imp.AccountBalanceServiceImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.jdbi.v3.core.Jdbi;
import org.modelmapper.ModelMapper;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public class ApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(AccountBalanceServiceImpl.class).to(AccountBalanceService.class);
        bind(Persistence.getInstance().getJdbi()).to(Jdbi.class);
        bind(new ModelMapper()).to(ModelMapper.class);
    }
}
