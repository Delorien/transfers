package com.company.transfers.service;

import com.company.transfers.resources.dto.AccountDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public interface AccountService {

    AccountDTO save(AccountDTO account);

    AccountDTO removeFromAccount(AccountDTO origin, BigDecimal amount);

    AccountDTO chargeInAccount(AccountDTO receiver, BigDecimal amount);

    AccountDTO get(Long id);

    List<AccountDTO> list();
}
