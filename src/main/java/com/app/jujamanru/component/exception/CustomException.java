package com.app.jujamanru.component.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class CustomException extends RuntimeException {
    protected HttpStatus httpStatus;
    protected String[] messages;

    public CustomException(HttpStatus httpStatus) {
        this(httpStatus, (String)null);
    }

    public CustomException(HttpStatus httpStatus, String... messages) {
        super("HttpStatus: " + httpStatus + ", messages: " + Arrays.toString(messages));
        this.httpStatus = httpStatus;
        this.messages = messages;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public String[] getMessages() {
        return this.messages;
    }
}
