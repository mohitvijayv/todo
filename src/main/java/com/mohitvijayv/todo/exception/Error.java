package com.mohitvijayv.todo.exception;

public class Error {
    public Error(ErrorWrapper error) {
        this.error = error;
    }

    public ErrorWrapper getError() {
        return error;
    }

    public void setError(ErrorWrapper error) {
        this.error = error;
    }

    private ErrorWrapper error;


}
