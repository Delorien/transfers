package com.company.transfers.repository.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
@Data
public class AccountBalance {

    private Long id;
    private Long accountId;
    private BigDecimal balance;
}
