package com.community.common.core.throwable.exception;

import com.community.common.core.throwable.DataException;

/**
 * 业务异常
 */
public class BusinessException extends DataException {

    protected BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}