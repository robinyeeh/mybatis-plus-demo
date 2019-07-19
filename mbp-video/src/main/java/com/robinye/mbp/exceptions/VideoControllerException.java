/**
 * Copyright (c) 2012 Robin Solutions. All rights reserved.
 * <p>
 * Created on 2018/5/14.
 */
package com.robinye.mbp.exceptions;

import com.robinye.mbp.base.exception.BaseControllerException;
import com.robinye.mbp.base.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author robin-laptop
 */
@ControllerAdvice @Slf4j public class VideoControllerException extends BaseControllerException {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorCode> handlerException(Exception e) {
        return processException(e);
    }
}
