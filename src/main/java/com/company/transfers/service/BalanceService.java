package com.company.transfers.service;

import com.company.transfers.resources.dto.Balance;
import org.jvnet.hk2.annotations.Contract;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
@Contract
public interface BalanceService {

    Balance save(Balance balance);
}
