package com.company.transfers.configuration;

import com.company.transfers.service.BalanceService;
import com.company.transfers.service.imp.BalanceServiceImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public class ApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(BalanceServiceImpl.class).to(BalanceService.class);
    }
}
