package com.company.transfers.repository.model;

import com.company.transfers.exception.InsufficientBalanceException;
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
    public Account(final Long id, final String document, final BigDecimal balance) {
        this.id = id;
        this.document = document;
        this.balance = balance;
    }

    public void subtractFromBalance(final BigDecimal amount) {
        BigDecimal subtract = this.balance.subtract(amount);
        if (subtract.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientBalanceException();
        }
        this.balance = subtract;
    }

    public void chargeToBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }
}
