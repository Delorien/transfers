package com.company.transfers.resources.dto;

import com.company.transfers.resources.validation.TransferGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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

    @Valid
    @NotNull(groups = TransferGroup.class)
    private AccountDTO origin;

    @Valid
    @NotNull(groups = TransferGroup.class)
    private AccountDTO receiver;

    @NotNull(groups = TransferGroup.class)
    @Positive(groups = TransferGroup.class)
    private BigDecimal amount;

    private Date date;
}
