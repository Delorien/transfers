package com.company.transfers.resources.dto;

import com.company.transfers.resources.validation.AccountGroup;
import com.company.transfers.resources.validation.TransferGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
@Data
@JsonInclude(NON_NULL)
public class AccountDTO {

    @NotNull(groups = TransferGroup.class)
    private Long id;

    @NotBlank(groups = AccountGroup.class)
    private String document;

    @NotNull(groups = AccountGroup.class)
    private BigDecimal balance;
}
