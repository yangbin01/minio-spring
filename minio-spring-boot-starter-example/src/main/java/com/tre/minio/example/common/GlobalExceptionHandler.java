package com.tre.minio.example.common;

import com.tre.minio.example.exception.BucketNotExistsException;
import com.tre.minio.example.exception.ObjectBasedStorageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

/**
 * @author yangbin
 * @date 2020/8/4 11:17
 * @description 全局异常处理
 */

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        CommonResponse response = CommonResponse.of(ex.getMessage());
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(BucketNotExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResponse handleBucketExistsException(BucketNotExistsException ex) {
        return CommonResponse.of(ex.getMessage());
    }

    @ExceptionHandler(ObjectBasedStorageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse handleObjectBasedStorageException(ObjectBasedStorageException ex) {
        return CommonResponse.of(ex.getMessage());
    }
}
