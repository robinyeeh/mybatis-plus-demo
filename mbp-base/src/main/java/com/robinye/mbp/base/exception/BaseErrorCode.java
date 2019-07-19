/**
 * All rights Reserved, Designed By Robin
 * Copyright: Copyright(C) 2016-2020
 * Company    Robin  Co., Ltd.
 * <p>
 * Date:2019-07-12
 */
package com.robinye.mbp.base.exception;

import org.springframework.http.HttpStatus;

/**
 * //通用错误代码和描述
 *
 * @author yetianbing
 * @version 1.0.0
 * @since jdk8
 */
public enum BaseErrorCode implements ErrorCode {
    InternalServer_Error(HttpStatus.INTERNAL_SERVER_ERROR, "CommonError.InternalServerError", "服务器内部错误"),

    NOT_FOUND(HttpStatus.NOT_FOUND,"CommonError.RequestNotFound", "请求路径不存在"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED,"CommonError.RequestMethodNotAllowed", "改请求方法不允许被处理"),
    NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE,"CommonError.RequestNotAcceptable", "改请求不被接受"),
    REQUIRED_PARAMETER_NOT_PRESENT(HttpStatus.BAD_REQUEST, "CommonError.RequiredParameterNotPresent", "缺少必填参数"),
    TYPE_MISMATCH(HttpStatus.BAD_REQUEST,"CommonError.RequestTypeMismatch", "请求参数类型不屁屁额"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST,"CommonError.BadRequest", "错误请求, 请检查请求参数是否正确"),
    ;

    private HttpStatus httpStatus;

    private String code;

    private String message;

    BaseErrorCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    @Override public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override public String getCode() {
        return null;
    }

    @Override public String getMessage() {
        return null;
    }
}
