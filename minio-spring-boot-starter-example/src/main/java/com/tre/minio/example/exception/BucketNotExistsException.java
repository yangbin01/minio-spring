package com.tre.minio.example.exception;

/**
 * @author yangbin
 * @date 2020/8/5 13:31
 * @description
 */
public class BucketNotExistsException extends ObjectBasedStorageException {

    private String bucketName;

    public BucketNotExistsException(String bucketName) {
        super();
        this.bucketName = bucketName;
    }

    public BucketNotExistsException(String bucketNamem, String message) {
        super(message);
        this.bucketName = bucketName;
    }

    public BucketNotExistsException(
            String bucketNamem, String message, Throwable cause) {
        super(message, cause);
        this.bucketName = bucketName;
    }

    public BucketNotExistsException(String bucketNamem, Throwable cause) {
        super(cause);
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
