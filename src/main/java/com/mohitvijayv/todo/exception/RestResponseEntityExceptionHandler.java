package com.mohitvijayv.todo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import java.util.HashMap;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class, UsernameNotAvailableException.class, AuthenticationException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        ErrorWrapper errorWrapper = new ErrorWrapper("405", "787", new InnerError("check", "test"));
        if(ex instanceof UsernameNotAvailableException) return handleUnae(ex, request);
        return handleExceptionInternal(ex, errorWrapper, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    private ResponseEntity handleUnae(RuntimeException ex, WebRequest request){
        ErrorWrapper errorWrapper = new ErrorWrapper("500", "787", new InnerError("1", "2"));
        return handleExceptionInternal(ex, new Error(errorWrapper), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }




}
