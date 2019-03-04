package com.company.transfers.exception;

import static com.company.transfers.exception.ErrorMessages.ACCOUNT_ALREADY_EXIST;
import static org.eclipse.jetty.http.HttpStatus.CONFLICT_409;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public class AccountAlreadyExistException extends APIException {

    @Override
    public APIError getAPIError() {
        return new APIError(ACCOUNT_ALREADY_EXIST);
    }

    @Override
    public Integer getHttpStatus() {
        return CONFLICT_409;
    }
}
