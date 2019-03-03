package com.company.transfers.exception;

import static com.company.transfers.exception.ErrorMessages.TRANSFER_NOT_FOUND;
import static org.eclipse.jetty.http.HttpStatus.NOT_FOUND_404;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public class TransferNotFoundException extends APIException {

    @Override
    public APIError getAPIError() {
        return new APIError(TRANSFER_NOT_FOUND);
    }

    @Override
    public Integer getHttpStatus() {
        return NOT_FOUND_404;
    }
}
