package com.tre.minio.endpoint;

import com.tre.minio.MinioConfigProperties;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangbin
 * @date 2020/8/3 15:36
 * @description
 */
@Endpoint(id = "minio")
public class MinioEndpoint {

    private final MinioConfigProperties minioConfigProperties;

    public MinioEndpoint(MinioConfigProperties minioConfigProperties) {
        this.minioConfigProperties = minioConfigProperties;
    }

    @ReadOperation
    public Map<String, Object> invoke() {
        Map<String, Object> result = new HashMap<>(16);
        result.put("minioConfigProperties", minioConfigProperties);
        return result;
    }
}
