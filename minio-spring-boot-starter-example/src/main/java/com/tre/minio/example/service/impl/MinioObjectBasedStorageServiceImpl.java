package com.tre.minio.example.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tre.minio.client.MinioClientTemplate;
import com.tre.minio.example.exception.ObjectBasedStorageException;
import com.tre.minio.example.service.ObjectBasedStorageService;
import com.tre.minio.example.vo.BucketVO;
import com.tre.minio.example.vo.ItemVO;
import io.minio.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author yangbin
 * @date 2020/8/5 12:43
 * @description 对象存储服务-minio实现
 */

@Service
public class MinioObjectBasedStorageServiceImpl implements ObjectBasedStorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioObjectBasedStorageServiceImpl.class);

    @Autowired
    private MinioClientTemplate minioClientTemplate;

    @Override
    public void makeBucket(String name) {
        try {
            minioClientTemplate.makeBucket(MakeBucketArgs.builder().bucket(name).build());
        } catch (Exception e) {
            LOGGER.error("makeBucket error: ", e);
            throw new ObjectBasedStorageException(e.getMessage(), e);
        }
    }

    @Override
    public void removeBucket(String name) {
        try {
            minioClientTemplate.removeBucket(RemoveBucketArgs.builder().bucket(name).build());
        } catch (Exception e) {
            LOGGER.error("removeBucket error: ", e);
            throw new ObjectBasedStorageException(e.getMessage(), e);
        }
    }

    @Override
    public boolean bucketExists(String name) {
        try {
            return minioClientTemplate.bucketExists(BucketExistsArgs.builder().bucket(name).build());
        } catch (Exception e) {
            LOGGER.error("bucketExists error: ", e);
            throw new ObjectBasedStorageException(e.getMessage(), e);
        }
    }

    @Override
    public List<BucketVO> listBucket() {
        try {
            List<Bucket> buckets = minioClientTemplate.listBuckets();
            List<BucketVO> bucketVOs = Lists.newArrayListWithCapacity(buckets.size());
            buckets.forEach(bucket -> {
                BucketVO bucketVO = new BucketVO();
                bucketVO.setName(bucket.name());
                bucketVO.setCreateDate(bucket.creationDate().toInstant().toEpochMilli());
                bucketVOs.add(bucketVO);
            });
            return bucketVOs;
        } catch (Exception e) {
            LOGGER.error("listBucket error: ", e);
            throw new ObjectBasedStorageException(e.getMessage(), e);
        }
    }

    @Override
    public void putObject(String bucketName, MultipartFile file) {
        try {
            minioClientTemplate.putObject(PutObjectArgs.builder().
                    bucket(bucketName).
                    object(file.getOriginalFilename()).
                    contentType(file.getContentType()).
                    stream(file.getInputStream(), file.getSize(), -1).
                    build());
        } catch (Exception e) {
            LOGGER.error("putObject error: ", e);
            throw new ObjectBasedStorageException("upload file " + file.getOriginalFilename() + " to bucket " + bucketName + " error, detail: " + e.getMessage(), e);
        }
    }

    @Override
    public void putObjects(String bucketName, MultipartFile[] files) {
        List<String> errorFiles = Lists.newArrayList();
        for (MultipartFile file : files) {
            try {
                putObject(bucketName, file);
            } catch (ObjectBasedStorageException e) {
                errorFiles.add(file.getName());
            }
        }
        if (errorFiles.size() > 0) {
            throw new ObjectBasedStorageException("upload files " + errorFiles + " to bucket " + bucketName);
        }
    }

    @Override
    public InputStream getObject(String bucketName, String objectName) {
        try {
            return minioClientTemplate.getObject(GetObjectArgs.builder().
                    bucket(bucketName).
                    object(objectName).
                    build());
        } catch (Exception e) {
            LOGGER.error("getObject error: ", e);
            throw new ObjectBasedStorageException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, InputStream> getObjects(String bucketName, List<String> objectList) {
        Map<String, InputStream> streams = Maps.newLinkedHashMap();
        try {
            for (String object : objectList) {
                InputStream inputStream = minioClientTemplate.getObject(GetObjectArgs.builder().
                        bucket(bucketName).
                        object(object).
                        build());
                streams.put(object, inputStream);
            }
            return streams;
        } catch (Exception e) {
            LOGGER.error("getObjects error: ", e);
            throw new ObjectBasedStorageException(e.getMessage(), e);
        }
    }


    @Override
    public List<ItemVO> listObjects(String bucketName, String prefix, String startAfter) {
        List<ItemVO> items = Lists.newArrayList();
        ListObjectsArgs.Builder builder = ListObjectsArgs.builder().bucket(bucketName);
        if (!StringUtils.isEmpty(prefix)) {
            builder.prefix(prefix);
        }
        if (!StringUtils.isEmpty(startAfter)) {
            builder.startAfter(startAfter);
        }

        Iterable<Result<Item>> results = minioClientTemplate.listObjects(builder.build());
        results.forEach(result -> {
            ItemVO itemVO = new ItemVO();
            try {
                Item minioItem = result.get();
                itemVO.setEtag(minioItem.etag());
                itemVO.setObjectName(minioItem.objectName());
                itemVO.setSize(minioItem.size());
                itemVO.setStorageClass(minioItem.storageClass());
                itemVO.setLatest(minioItem.isLatest());
                itemVO.setVersionId(minioItem.versionId());
                itemVO.setDir(minioItem.isDir());
                itemVO.setLastModified(minioItem.lastModified().toInstant().toEpochMilli());
                //TODO NOT SET
                //itemVO.setOwner();
                //itemVO.setUserMetadata(minioItem.userMetadata());

                items.add(itemVO);
            } catch (Exception e) {
                LOGGER.error("listObjects error: ", e);
                throw new ObjectBasedStorageException(e.getMessage(), e);
            }
        });
        return items;
    }

    @Override
    public void deleteObject(String bucketName, String objectName) {
        try {
            minioClientTemplate.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (Exception e) {
            LOGGER.error("deleteObject error: ", e);
            throw new ObjectBasedStorageException(e.getMessage(), e);
        }
    }
}
