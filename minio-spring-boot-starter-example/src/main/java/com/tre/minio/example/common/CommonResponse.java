package com.tre.minio.example.common;

/**
 * @author yangbin
 * @date 2020/8/6 9:39
 * @description
 */
public class CommonResponse {

    private static final String DELETE_SUCCESS_MESSAGE = "delete success";

    private static final String CREATE_SUCCESS_MESSAGE = "create success";

    private static final String UPDATE_SUCCESS_MESSAGE = "update success";

    private String message;

    private Long timestamp = System.currentTimeMillis();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public static CommonResponse of(String message) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setMessage(message);
        return commonResponse;
    }

    public static CommonResponse deleteSuccess() {
        return of(DELETE_SUCCESS_MESSAGE);
    }

    public static CommonResponse createSuccess() {
        return of(CREATE_SUCCESS_MESSAGE);
    }

    public static CommonResponse updateSuccess() {
        return of(UPDATE_SUCCESS_MESSAGE);
    }
}
