/**
 * Copyright (c) 2012 Robin Solutions. All rights reserved.
 * <p>
 * Created on 2018/5/14.
 */
package com.robinye.mbp.base.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

/**
 * @author robin-laptop
 */
@Slf4j public class BaseControllerException {
    private ObjectMapper objectMapper = new ObjectMapper();

    protected ResponseEntity<ErrorCode> processException(Exception e) {
        log.error("Got exception for request, error.", e);

        ErrorCode errorCode = convertException(e);
        return new ResponseEntity<>(errorCode, errorCode.getHttpStatus());
    }

    protected ErrorCode convertException(Exception ex) {
        ErrorCode errorCode;
        if (ex instanceof NoHandlerFoundException) {
            errorCode = BaseErrorCode.NOT_FOUND;
        } else if (ex instanceof HttpRequestMethodNotSupportedException
                || ex instanceof HttpMediaTypeNotSupportedException) {
            errorCode = BaseErrorCode.METHOD_NOT_ALLOWED;
        } else if (ex instanceof HttpMediaTypeNotAcceptableException) {
            errorCode = BaseErrorCode.NOT_ACCEPTABLE;
        } else if (ex instanceof ServletRequestBindingException) {
            errorCode = BaseErrorCode.REQUIRED_PARAMETER_NOT_PRESENT;
        } else if (ex instanceof TypeMismatchException || ex instanceof HttpMessageNotReadableException) {
            errorCode = BaseErrorCode.TYPE_MISMATCH;
        } else if (ex instanceof MissingServletRequestPartException || ex instanceof BindException) {
            errorCode = BaseErrorCode.BAD_REQUEST;
        } else if (ex instanceof MethodArgumentNotValidException) {
            String message = getMethodArgumentNotValidException((MethodArgumentNotValidException) ex);
            errorCode = new ErrorCode() {
                @Override public HttpStatus getHttpStatus() {
                    return HttpStatus.BAD_REQUEST;
                }

                @Override public String getCode() {
                    return BaseErrorCode.BAD_REQUEST.getCode();
                }

                @Override public String getMessage() {
                    return message;
                }
            };
        } else if (ex instanceof BaseRuntimeException) {
            errorCode = ((BaseRuntimeException) ex).getErrorCode();
        } else {
            errorCode = BaseErrorCode.InternalServer_Error;
        }

        return errorCode;
    }

    protected String getMethodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException) {
        methodArgumentNotValidException.getBindingResult();
        methodArgumentNotValidException.getBindingResult().getFieldErrors();

        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        try {
            return objectMapper.writeValueAsString(fieldErrors);
        } catch (JsonProcessingException e) {
            log.error("Failed to parse invalid method arguments.", e);
            return e.getMessage();
        }

    }
}
