package com.company.transfers.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
@Getter
@RequiredArgsConstructor
public enum ErrorMessages {

    UNEXPECTED_ERROR("Unexpected error occurred", "1000"),
    ACCOUNT_NOT_FOUND("Account not found", "1001"),
    TRANSFER_NOT_FOUND("Transfer not found", "1002"),
    INSUFFICIENT_BALANCE("Insufficient balance", "1003");

    private final String message;
    private final String code;
}

