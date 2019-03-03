package com.company.transfers.service.imp;

import com.company.transfers.resources.dto.Balance;
import com.company.transfers.service.BalanceService;
import org.jvnet.hk2.annotations.Service;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
@Service
public class BalanceServiceImpl implements BalanceService {

    public Balance save(Balance balance) {
        System.out.println(balance.getBalance());
        return balance;
    }
}
