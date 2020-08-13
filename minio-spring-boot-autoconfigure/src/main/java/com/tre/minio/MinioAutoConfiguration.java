package com.tre.minio;

import com.tre.minio.client.MinioClientTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangbin
 * @date 2020/8/3 13:21
 * @description
 */

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = "minio.enabled", matchIfMissing = true)
public class MinioAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MinioConfigProperties minioConfigProperties() {
        return new MinioConfigProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public MinioClientTemplate MinioClientTemplate(MinioConfigProperties minioConfigProperties) {
        return new MinioClientTemplate(minioConfigProperties);
    }
}
