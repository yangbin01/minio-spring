package com.tre.minio.example.exception;

/**
 * @author yangbin
 * @date 2020/8/5 13:31
 * @description
 */
public class GetObjectException extends ObjectBasedStorageException {

    private String bucketName;

    private String objectName;

    public GetObjectException(String bucketName, String objectName) {
        super();
        this.bucketName = bucketName;
        this.objectName = objectName;
    }

    public GetObjectException(String bucketName, String objectName, String message) {
        super(message);
        this.bucketName = bucketName;
        this.objectName = objectName;
    }

    public GetObjectException(String bucketName, String objectName, String message, Throwable cause) {
        super(message, cause);
        this.bucketName = bucketName;
        this.objectName = objectName;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
