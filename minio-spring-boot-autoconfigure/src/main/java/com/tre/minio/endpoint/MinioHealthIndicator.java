package com.tre.minio.endpoint;

import com.tre.minio.client.MinioClientTemplate;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

/**
 * @author yangbin
 * @date 2020/8/3 15:35
 * @description
 */
public class MinioHealthIndicator extends AbstractHealthIndicator {

    private final MinioClientTemplate minioClientTemplate;

    public MinioHealthIndicator(MinioClientTemplate minioClientTemplate) {
        this.minioClientTemplate = minioClientTemplate;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        String status = minioClientTemplate.getServerStatus();
        builder.status(status);
        switch (status) {
            case "UP":
                builder.up();
                break;
            case "DOWN":
                builder.down();
                break;
            default:
                builder.unknown();
                break;
        }

    }
}
