package com.tre.minio.example.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yangbin
 * @date 2020/8/6 9:24
 * @description
 */
public class RequestContextHolderUtils {

    public static String getCurrentRequestURI() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return request.getRequestURI();
    }
}
