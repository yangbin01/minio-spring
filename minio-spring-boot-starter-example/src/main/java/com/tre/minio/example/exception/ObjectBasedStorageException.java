package com.tre.minio.example.exception;

/**
 * @author yangbin
 * @date 2020/8/5 13:04
 * @description
 */
public class ObjectBasedStorageException extends RuntimeException {

    public ObjectBasedStorageException() {
        super();
    }

    public ObjectBasedStorageException(String message) {
        super(message);
    }

    public ObjectBasedStorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectBasedStorageException(Throwable cause) {
        super(cause);
    }

    public ObjectBasedStorageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
