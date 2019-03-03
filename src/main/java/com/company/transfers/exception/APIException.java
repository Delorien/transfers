package com.company.transfers.exception;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
public abstract class APIException extends RuntimeException {

    public abstract APIError getAPIError();

    public abstract Integer getHttpStatus();
}
