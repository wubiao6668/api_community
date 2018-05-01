package com.community.common.core.throwable.exception;

/**
 * 状态异常
 */
public class StateException extends BusinessException {

    protected StateException() {
    }

    public StateException(String message) {
        super(message);
    }

    public StateException(int code, String message) {
        super(message);
        this.code = code;
    }

    public StateException(String message, Throwable cause) {
        super(message, cause);
    }

    public StateException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}
