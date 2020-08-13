package com.tre.minio.analyzer;

/**
 * @author yangbin
 * @date 2020/8/3 14:50
 * @description
 */
public class MinioConnectionFailureException extends RuntimeException {

    private String endpoint;

    private int port;

    public MinioConnectionFailureException(String endpoint, int port, String message) {
        super(message);
        this.endpoint = endpoint;
        this.port = port;
    }

    public MinioConnectionFailureException(String endpoint, int port, String message, Throwable cause) {
        super(message, cause);
        this.endpoint = endpoint;
        this.port = port;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public int getPort() {
        return port;
    }
}
