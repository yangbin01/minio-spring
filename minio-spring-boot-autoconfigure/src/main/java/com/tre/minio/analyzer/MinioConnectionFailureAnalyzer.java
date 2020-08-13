package com.tre.minio.analyzer;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

/**
 * @author yangbin
 * @date 2020/8/3 14:52
 * @description
 */
public class MinioConnectionFailureAnalyzer extends AbstractFailureAnalyzer<MinioConnectionFailureException> {
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, MinioConnectionFailureException cause) {
        return new FailureAnalysis(
                "Application failed to connect to Minio server: " + cause.getEndpoint() + " port: " + cause.getPort(),
                "Please check your Minio server config", cause);
    }
}
