package com.tre.minio.example.controller;

import com.tre.minio.example.ObjectBasedStorageApplication;
import com.tre.minio.example.service.ObjectBasedStorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author yangbin
 * @date 2020/8/11 14:44
 * @description
 */

@SpringBootTest(classes = ObjectBasedStorageApplication.class)
class ObjectBasedStorageControllerTest {

    @Autowired
    private ObjectBasedStorageService objectBasedStorageService;

    @Test
    void makeBucket() {
       
    }

    @Test
    void removeBucket() {
    }

    @Test
    void listBucket() {
        System.out.println(objectBasedStorageService.listBucket());
    }

    @Test
    void putObject() {
    }

    @Test
    void putObjects() {
    }

    @Test
    void getObject() {
    }

    @Test
    void getObjects() {
    }

    @Test
    void listObjects() {
    }

    @Test
    void deleteObject() {
    }
}