package com.cn.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author LinChen
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ErrorResponse handleBindException(
            BindException exception,
            HttpServletRequest request) {
        log.error("Bind Error at {}", request.getRequestURI(), exception);
        return ErrorResponse.create(exception, HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                               HttpServletRequest request) {
        log.error("Argument valid error at {}", request.getRequestURI(), exception);
        return ErrorResponse.create(exception, HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
    }

    @ExceptionHandler()
    public ErrorResponse handleAllUncaughtException(
            Throwable t, HttpServletRequest request) {
        log.error("Unhandled Error at {}", request.getRequestURI(), t);
        return ErrorResponse.create(t, HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }
}
