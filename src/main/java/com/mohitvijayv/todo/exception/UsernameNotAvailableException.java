package com.mohitvijayv.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason= "Username Not Available")
public class UsernameNotAvailableException extends RuntimeException{
    public UsernameNotAvailableException(){}
    public UsernameNotAvailableException(ErrorWrapper errorWrapper){

    }
}
