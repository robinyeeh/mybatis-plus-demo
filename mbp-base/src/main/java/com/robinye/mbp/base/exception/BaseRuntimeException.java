/**
 * All rights Reserved, Designed By Robin
 * Copyright: Copyright(C) 2016-2020
 * Company    Robin  Co., Ltd.
 * <p>
 * Date:2019-07-11
 */
package com.robinye.mbp.base.exception;

/**
 * 内容运营系统异常处理基类
 *
 * @author yetianbing
 * @version 1.0.0
 * @since jdk8
 */

public class BaseRuntimeException extends RuntimeException {
    private ErrorCode errorCode;

    public BaseRuntimeException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BaseRuntimeException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

    public BaseRuntimeException(Throwable cause) {
        super(cause);
    }

    public BaseRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
