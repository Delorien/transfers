package com.company.transfers.exception;

import lombok.Getter;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
@Getter
public class APIError {

    private final String code;
    private final String message;

    public APIError(final ErrorMessages errorMessages) {
        this.code = errorMessages.getCode();
        this.message = errorMessages.getMessage();
    }
}
