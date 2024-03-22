package com.app.jujamanru.config;

import com.app.jujamanru.component.exception.CustomException;
import com.querydsl.core.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        log.error(ex.getMessage());
        return this.getResponseEntityException(ex);
    }

    public ResponseEntity<?> getResponseEntityException(Exception e) {
        if (e instanceof CustomException) {
            CustomException cex = (CustomException)e;
            return new ResponseEntity(this.getMessage(cex), this.getResponseHeaders(), cex.getHttpStatus());
        } else if (e instanceof BadCredentialsException) {
            return new ResponseEntity(e.getMessage(), this.getResponseHeaders(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(StringUtils.isNullOrEmpty(e.getMessage()) ? "시스템 에러가 발생했습니다" : e.getMessage(), this.getResponseHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private HttpHeaders getResponseHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
        return responseHeaders;
    }

    private String getMessage(CustomException ex) {
        return ex.getMessages() != null && ex.getMessages().length > 0 ? (String) Arrays.stream(ex.getMessages()).collect(Collectors.joining(",")) : "";
    }
}
