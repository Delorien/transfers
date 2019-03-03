package com.company.transfers.service;

import com.company.transfers.resources.dto.AccountBalanceDTO;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public interface AccountBalanceService {

    AccountBalanceDTO save(AccountBalanceDTO accountBalance);
}
