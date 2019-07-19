/**
 * All rights Reserved, Designed By Robin
 * Copyright: Copyright(C) 2016-2020
 * Company    Robin  Co., Ltd.
 * <p>
 * Date:2019-07-11
 */
package com.robinye.mbp.base.exception;

import org.springframework.http.HttpStatus;

/**
 * 内容运营系统错误代码接口
 *
 * @author yetianbing
 * @version 1.0.0
 * @since jdk8
 */
public interface ErrorCode {
    HttpStatus getHttpStatus();

    String getCode();

    String getMessage();
}
