package com.tre.minio.endpoint;

import com.tre.minio.MinioConfigProperties;
import com.tre.minio.client.MinioClientTemplate;
import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author yangbin
 * @date 2020/8/3 15:34
 * @description
 */
@ConditionalOnWebApplication
@ConditionalOnClass(Endpoint.class)
@ConditionalOnProperty(name = "minio.enabled", matchIfMissing = true)
public class MinioEndpointAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MinioEndpoint minioEndpoint(MinioConfigProperties minioConfigProperties) {
        return new MinioEndpoint(minioConfigProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnEnabledHealthIndicator("minio")
    public MinioHealthIndicator minioIndicator(MinioClientTemplate minioClientTemplate) {
        return new MinioHealthIndicator(minioClientTemplate);
    }

}
