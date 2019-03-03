package com.company.transfers.resources.dto;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public class Balance {

    private Long userId;
    private Double balance;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
