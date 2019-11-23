package com.ramkiopt.main.controllers.exceptions;

import com.ramkiopt.main.services.utils.response.BaseResponseService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final BaseResponseService responseService;

    public RestExceptionHandler(BaseResponseService responseService) {
        this.responseService = responseService;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationExc(ConstraintViolationException ex) {
        return responseService.createErrorInfo(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}