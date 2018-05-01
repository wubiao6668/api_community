package com.community.common.core.throwable.exception;

/**
 * 乐观锁异常
 */
public class OptimisticLockException extends RepositoryException {

    public OptimisticLockException() {
    }

    public OptimisticLockException(String message) {
        super(message);
    }

    public OptimisticLockException(int code, String message) {
        super(code, message);
    }

    public OptimisticLockException(String message, Throwable cause) {
        super(message, cause);
    }

    public OptimisticLockException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}
