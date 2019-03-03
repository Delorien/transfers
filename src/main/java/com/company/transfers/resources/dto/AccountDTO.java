package com.company.transfers.resources.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
@Data
@JsonInclude(NON_NULL)
public class AccountDTO {

    private Long id;
    private String document;
    private BigDecimal balance;
}
