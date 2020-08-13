package com.tre.minio.example.vo;

/**
 * @author yangbin
 * @date 2020/8/5 14:21
 * @description
 */
public class BucketVO {

    private String name;

    private Long createDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }
}
