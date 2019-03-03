package com.company.transfers.repository.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
@Data
@NoArgsConstructor
public class Account {

    private Long id;
    private String document;
    private BigDecimal balance;

    @ConstructorProperties({"id", "document", "balance"})
    public Account(Long id, String document, BigDecimal balance) {
        this.id = id;
        this.document = document;
        this.balance = balance;
    }
}
