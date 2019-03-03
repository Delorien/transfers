package com.company.transfers.resources.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
@Data
public class AccountDTO {

    private Long id;
    private String document;
    private BigDecimal balance;
}
