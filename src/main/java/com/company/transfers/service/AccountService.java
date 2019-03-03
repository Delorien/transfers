package com.company.transfers.service;

import com.company.transfers.resources.dto.AccountDTO;

import java.math.BigDecimal;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public interface AccountService {

    AccountDTO save(AccountDTO account);

    AccountDTO removeFromAccount(AccountDTO origin, BigDecimal amount);

    AccountDTO chargeInAccount(AccountDTO receiver, BigDecimal amount);
}
