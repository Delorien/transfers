package com.company.transfers.resources.exception;

import com.company.transfers.exception.APIError;
import com.company.transfers.exception.APIException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static com.company.transfers.exception.ErrorMessages.UNEXPECTED_ERROR;
import static java.util.Arrays.asList;
import static org.eclipse.jetty.http.HttpStatus.INTERNAL_SERVER_ERROR_500;

/**
 * Created by Leonardo Tonin on 03/03/19.
 */
@Provider
public class APIExceptionHandler implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        Integer httpStatus = INTERNAL_SERVER_ERROR_500;
        APIError error = new APIError(UNEXPECTED_ERROR);

        if (exception instanceof APIException) {
            httpStatus = ((APIException) exception).getHttpStatus();
            error = ((APIException) exception).getAPIError();
        }

        return Response.status(httpStatus)
                .entity(asList(error))
                .build();
    }
}