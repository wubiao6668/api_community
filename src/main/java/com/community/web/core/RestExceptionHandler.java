package com.community.web.core;

import com.community.common.core.throwable.DataException;
import com.community.domain.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(DataException.class)
    public Response handleDataException(final DataException exception) {
        logger.error("method[handleDataException],exception:", exception);
        Response apiResponse = new Response(exception.getCode(), exception.getMessage());
        return apiResponse;
    }

    @ExceptionHandler(Exception.class)
    public Response handleException(final Exception exception) {
        logger.error("method[handleException],exception:{}", exception);
        Response apiResponse = Response.exception();
        return apiResponse;
    }


}
