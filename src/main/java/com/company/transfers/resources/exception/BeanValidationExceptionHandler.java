package com.company.transfers.resources.exception;

import com.company.transfers.exception.APIError;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.eclipse.jetty.http.HttpStatus.BAD_REQUEST_400;

/**
 * Created by Leonardo Tonin on 04/03/19.
 */
@Provider
public class BeanValidationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    public Response toResponse(final ConstraintViolationException exception) {

        List<APIError> errors = exception.getConstraintViolations().stream().map(violation -> new APIError(getFormattedFieldPath(violation), violation.getMessage())).collect(Collectors.toList());
        return Response.status(BAD_REQUEST_400).entity(errors).build();
    }

    private String getFormattedFieldPath(final ConstraintViolation violation) {
        List<String> nodes = getNodes(violation);

        if (nodes.size() > 2) {
            return nodes.stream().skip(2).collect(Collectors.joining("."));
        }
        return violation.getPropertyPath().toString();

    }

    private List<String> getNodes(final ConstraintViolation violation) {
        List<String> nodes = new ArrayList<>();
        violation.getPropertyPath().forEach(node -> nodes.add(node.getName()));
        return nodes;
    }
}
