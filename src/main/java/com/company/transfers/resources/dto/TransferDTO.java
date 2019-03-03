package com.company.transfers.resources.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
@Data
@JsonInclude(NON_NULL)
public class TransferDTO {

    private Long id;
    private AccountDTO origin;
    private AccountDTO receiver;
    private BigDecimal amount;
    private Date date;
}
