package com.tre.minio.example.vo;

import java.io.Serializable;

/**
 * @author yangbin
 * @date 2020/8/5 16:03
 * @description
 */
public class ListObjectsVO implements Serializable {

    private String delimiter = "";

    private boolean useUrlEncodingType = true;

    private String keyMarker;

    private int maxKeys = 1000;

    private String prefix = "";

    private String continuationToken;

    private boolean fetchOwner;

    private String versionIdMarker;

    private boolean includeUserMetadata;

    private boolean recursive;

    private boolean useApiVersion1;

    private boolean includeVersions;

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public boolean isUseUrlEncodingType() {
        return useUrlEncodingType;
    }

    public void setUseUrlEncodingType(boolean useUrlEncodingType) {
        this.useUrlEncodingType = useUrlEncodingType;
    }

    public String getKeyMarker() {
        return keyMarker;
    }

    public void setKeyMarker(String keyMarker) {
        this.keyMarker = keyMarker;
    }

    public int getMaxKeys() {
        return maxKeys;
    }

    public void setMaxKeys(int maxKeys) {
        this.maxKeys = maxKeys;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getContinuationToken() {
        return continuationToken;
    }

    public void setContinuationToken(String continuationToken) {
        this.continuationToken = continuationToken;
    }

    public boolean isFetchOwner() {
        return fetchOwner;
    }

    public void setFetchOwner(boolean fetchOwner) {
        this.fetchOwner = fetchOwner;
    }

    public String getVersionIdMarker() {
        return versionIdMarker;
    }

    public void setVersionIdMarker(String versionIdMarker) {
        this.versionIdMarker = versionIdMarker;
    }

    public boolean isIncludeUserMetadata() {
        return includeUserMetadata;
    }

    public void setIncludeUserMetadata(boolean includeUserMetadata) {
        this.includeUserMetadata = includeUserMetadata;
    }

    public boolean isRecursive() {
        return recursive;
    }

    public void setRecursive(boolean recursive) {
        this.recursive = recursive;
    }

    public boolean isUseApiVersion1() {
        return useApiVersion1;
    }

    public void setUseApiVersion1(boolean useApiVersion1) {
        this.useApiVersion1 = useApiVersion1;
    }

    public boolean isIncludeVersions() {
        return includeVersions;
    }

    public void setIncludeVersions(boolean includeVersions) {
        this.includeVersions = includeVersions;
    }
}
