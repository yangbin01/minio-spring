package com.tre.minio.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author yangbin
 * @date 2020/8/6 10:05
 * @description 处理文件上传下载的工具类
 */
public class ObjectBasedStorageUtils {

    private  static  Logger LOGGER = LoggerFactory.getLogger(ObjectBasedStorageUtils.class);

    public static void createZipStream(ZipOutputStream zipOutputStream, Map.Entry<String, InputStream> entry) throws IOException {
        String objectName = entry.getKey();
        InputStream inputStream = entry.getValue();
        ZipEntry zipEntry = new ZipEntry(objectName);
        try {
            zipOutputStream.putNextEntry(zipEntry);
            StreamUtils.copy(inputStream, zipOutputStream);
            inputStream.close();
            zipOutputStream.closeEntry();
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                LOGGER.warn("close stream error {}", e.getMessage());
            }
            try {
                zipOutputStream.closeEntry();
            } catch (IOException e) {
                LOGGER.warn("close stream error {}", e.getMessage());
            }
        }
    }

    public static void createZipStream(ZipOutputStream zipOutputStream, Map<String, InputStream> objects) throws IOException {
        for (Map.Entry<String, InputStream> entry : objects.entrySet()) {
            createZipStream(zipOutputStream, entry);
        }
    }
}
