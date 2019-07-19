/**
 * All rights Reserved, Designed By Robin
 * Copyright: Copyright(C) 2016-2020
 * Company    Robin  Co., Ltd.
 * <p>
 * Date:2019-07-12
 */
package com.robinye.mbp.exceptions;

import com.robinye.mbp.base.exception.BaseRuntimeException;
import com.robinye.mbp.base.exception.ErrorCode;

/**
 * Video异常处理类
 *
 * @author yetianbing
 * @version 1.0.0
 * @since jdk8
 */

public class VideoRuntimeException extends BaseRuntimeException {
    public VideoRuntimeException(ErrorCode errorCode) {
        super(errorCode);
    }

    public VideoRuntimeException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public VideoRuntimeException(Throwable cause) {
        super(cause);
    }

    public VideoRuntimeException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
