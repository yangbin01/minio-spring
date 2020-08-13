package com.tre.minio.example.service;

import com.tre.minio.example.vo.BucketVO;
import com.tre.minio.example.vo.ItemVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author yangbin
 * @date 2020/8/5 12:43
 * @description
 */
public interface ObjectBasedStorageService {


    void makeBucket(String name);

    void removeBucket(String name);

    boolean bucketExists(String name);

    List<BucketVO> listBucket();

    void putObject(String bucketName, MultipartFile file);

    void putObjects(String bucketName, MultipartFile[] files);

    InputStream getObject(String bucketName, String objectName);

    Map<String, InputStream> getObjects(String bucketName, List<String> objectList);

    List<ItemVO>  listObjects(String bucketName, String prefix, String startAfter);

    void deleteObject(String bucketName, String objectName);
}
