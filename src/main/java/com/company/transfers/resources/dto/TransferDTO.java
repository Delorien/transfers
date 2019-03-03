package com.company.transfers.resources.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
@Data
public class TransferDTO {

    private Long id;
    private AccountDTO origin;
    private AccountDTO receiver;
    private BigDecimal amount;
    private Date date;
}
