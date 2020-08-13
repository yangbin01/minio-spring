package com.tre.minio.example.controller;

import com.tre.minio.example.common.CommonResponse;
import com.tre.minio.example.exception.GetObjectException;
import com.tre.minio.example.service.ObjectBasedStorageService;
import com.tre.minio.example.utils.ObjectBasedStorageUtils;
import com.tre.minio.example.vo.BucketVO;
import com.tre.minio.example.vo.ItemVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @author yangbin
 * @date 2020/6/18 10:35
 * @description 对象存储接口
 */

@RestController
public class ObjectBasedStorageController {

    private final Logger LOGGER = LoggerFactory.getLogger(ObjectBasedStorageController.class);

    @Autowired
    private ObjectBasedStorageService objectBasedStorageService;

    @PostMapping("/buckets/{name}")
    public CommonResponse makeBucket(@PathVariable @NotBlank(message = "bucket name can not be null") String name) {
        objectBasedStorageService.makeBucket(name);
        return CommonResponse.createSuccess();
    }

    @DeleteMapping("/buckets/{name}")
    public CommonResponse removeBucket(@PathVariable @NotBlank(message = "bucket name can not be null") String name) {
        objectBasedStorageService.removeBucket(name);
        return CommonResponse.deleteSuccess();
    }

    @GetMapping("/buckets")
    public List<BucketVO> listBucket() {
        return objectBasedStorageService.listBucket();
    }

    @PostMapping("/buckets/{bucketName}/objects/upload")
    public CommonResponse putObject(@PathVariable @NotBlank(message = "bucket name can not be null") String bucketName, @RequestParam(value = "object") MultipartFile object) {
        objectBasedStorageService.putObject(bucketName, object);
        return CommonResponse.createSuccess();
    }

    @PostMapping("/buckets/{bucketName}/objects/upload/multi")
    public CommonResponse putObjects(@PathVariable @NotBlank(message = "bucket name can not be null") String bucketName, @RequestParam(value = "objects") MultipartFile[] objects) {
        objectBasedStorageService.putObjects(bucketName, objects);
        return CommonResponse.createSuccess();
    }

    @GetMapping(value = "/buckets/{bucketName}/objects/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void getObject(@PathVariable @NotBlank(message = "bucket name can not be null") String bucketName,
                          @RequestParam @NotBlank(message = "object name can not be null") String objectName,
                          HttpServletResponse response) {
        InputStream inputStream = objectBasedStorageService.getObject(bucketName, objectName);
        try {
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode(objectName, "UTF-8"));
            StreamUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            LOGGER.error("get object error bucketName = {}, objectName = {}", bucketName, objectName, e);
            throw new GetObjectException(bucketName, objectName, e.getMessage());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                LOGGER.warn("close stream error {}", e.getMessage());
            }
        }
    }

    @GetMapping(value = "/buckets/{bucketName}/objects/download/multi/zip", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void getObjects(@PathVariable @NotBlank(message = "bucket name can not be null") String bucketName,
                           @RequestParam @NotEmpty(message = "objectList can not be null") List<String> objectList,
                           HttpServletResponse response) {
        Map<String, InputStream> objects = objectBasedStorageService.getObjects(bucketName, objectList);
        ZipOutputStream zipOutputStream = null;
        try {
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode(bucketName + ".zip", "UTF-8"));
            zipOutputStream = new ZipOutputStream(response.getOutputStream());
            ObjectBasedStorageUtils.createZipStream(zipOutputStream, objects);
        } catch (IOException e) {
            throw new GetObjectException(bucketName, objectList.toString(), e.getMessage());
        } finally {
            try {
                if (zipOutputStream != null) {
                    zipOutputStream.finish();
                }
            } catch (IOException e) {
                LOGGER.warn("close stream error {}", e.getMessage());
            }
            try {
                if (zipOutputStream != null) {
                    zipOutputStream.close();
                }
            } catch (IOException e) {
                LOGGER.warn("close stream error {}", e.getMessage());
            }
        }

    }

    @GetMapping("/buckets/{bucketName}/objects")
    public List<ItemVO> listObjects(@PathVariable @NotBlank(message = "bucket name can not be null") String bucketName,
                                    @RequestParam(required = false, defaultValue = "") String prefix,
                                    @RequestParam(required = false, defaultValue = "") String startAfter) {
        return objectBasedStorageService.listObjects(bucketName, prefix, startAfter);
    }

    @DeleteMapping("/buckets/{bucketName}/objects/{objectName}")
    public CommonResponse deleteObject(@PathVariable @NotBlank(message = "bucket name can not be null") String bucketName, @PathVariable @NotBlank(message = "object name can not be null") String objectName) {
        objectBasedStorageService.deleteObject(bucketName, objectName);
        return CommonResponse.deleteSuccess();
    }

}
