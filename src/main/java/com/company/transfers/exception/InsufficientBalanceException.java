package com.company.transfers.exception;

import static com.company.transfers.exception.ErrorMessages.INSUFFICIENT_BALANCE;
import static org.eclipse.jetty.http.HttpStatus.NOT_FOUND_404;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public class InsufficientBalanceException extends APIException {

    @Override
    public APIError getAPIError() {
        return new APIError(INSUFFICIENT_BALANCE);
    }

    @Override
    public Integer getHttpStatus() {
        return NOT_FOUND_404;
    }
}
