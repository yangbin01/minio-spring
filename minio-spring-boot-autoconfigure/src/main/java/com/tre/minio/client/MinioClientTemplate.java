package com.tre.minio.client;

import com.tre.minio.MinioConfigProperties;
import com.tre.minio.analyzer.MinioConnectionFailureException;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * @author yangbin
 * @date 2020/8/3 13:48
 * @description
 */
public class MinioClientTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioClientTemplate.class);

    private static final String TEST_BUCKET = "testbucket";

    private final MinioConfigProperties minioConfigProperties;

    private MinioClient minioClient;

    public MinioClientTemplate(MinioConfigProperties minioConfigProperties) {
        this.minioConfigProperties = minioConfigProperties;
        buildMinioClient();
        checkMinioConnect();
    }

    private void buildMinioClient() {
        this.minioClient = MinioClient.builder().
                endpoint(
                        minioConfigProperties.getEndpoint(),
                        minioConfigProperties.getPort(),
                        minioConfigProperties.isSecure()).
                credentials(
                        minioConfigProperties.getAccessKey(),
                        minioConfigProperties.getSecretKey()).
                build();
        minioClient.setTimeout(
                minioConfigProperties.getConnectTimeout().toMillis(),
                minioConfigProperties.getWriteTimeout().toMillis(),
                minioConfigProperties.getReadTimeout().toMillis()
        );
    }

    private void checkMinioConnect() {
        try {
            chooseMinioClient().bucketExists(BucketExistsArgs.builder().bucket(TEST_BUCKET).build());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new MinioConnectionFailureException(
                    this.minioConfigProperties.getEndpoint(), this.minioConfigProperties.getPort(), e.getMessage(), e);
        }
    }

    private MinioClient chooseMinioClient() {
        return this.minioClient;
    }

    @Deprecated
    public ObjectStat statObject(String bucketName, String objectName)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().statObject(bucketName, objectName);
    }

    @Deprecated
    public ObjectStat statObject(
            String bucketName, String objectName, ServerSideEncryptionCustomerKey ssec)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().statObject(bucketName, objectName, ssec);
    }

    public ObjectStat statObject(StatObjectArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().statObject(args);
    }

    public String getObjectUrl(String bucketName, String objectName)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {

        return chooseMinioClient().getObjectUrl(bucketName, objectName);
    }


    @Deprecated
    public InputStream getObject(String bucketName, String objectName)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().getObject(bucketName, objectName);
    }

    @Deprecated
    public InputStream getObject(
            String bucketName, String objectName, ServerSideEncryptionCustomerKey ssec)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().getObject(bucketName, objectName, ssec);
    }

    @Deprecated
    public InputStream getObject(String bucketName, String objectName, long offset)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().getObject(bucketName, objectName, offset);
    }

    @Deprecated
    public InputStream getObject(String bucketName, String objectName, long offset, Long length)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().getObject(bucketName, objectName, offset, length);
    }

    @Deprecated
    public InputStream getObject(
            String bucketName,
            String objectName,
            Long offset,
            Long length,
            ServerSideEncryptionCustomerKey ssec)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().getObject(bucketName, objectName, offset, length, ssec);
    }

    public InputStream getObject(GetObjectArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().getObject(args);
    }

    @Deprecated
    public void getObject(String bucketName, String objectName, String fileName)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().getObject(bucketName, objectName, fileName);
    }

    @Deprecated
    public void getObject(
            String bucketName, String objectName, ServerSideEncryptionCustomerKey ssec, String fileName)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().getObject(bucketName, objectName, ssec, fileName);
    }

    public void downloadObject(DownloadObjectArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().downloadObject(args);
    }


    @Deprecated
    public void copyObject(
            String bucketName,
            String objectName,
            Map<String, String> headerMap,
            ServerSideEncryption sse,
            String srcBucketName,
            String srcObjectName,
            ServerSideEncryption srcSse,
            CopyConditions copyConditions)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().copyObject(bucketName, objectName, headerMap, sse, srcBucketName, srcObjectName, srcSse, copyConditions);
    }

    public ObjectWriteResponse copyObject(CopyObjectArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().copyObject(args);

    }

    @Deprecated
    public void composeObject(
            String bucketName,
            String objectName,
            List<ComposeSource> sources,
            Map<String, String> headerMap,
            ServerSideEncryption sse)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().composeObject(bucketName, objectName, sources, headerMap, sse);
    }

    public ObjectWriteResponse composeObject(ComposeObjectArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().composeObject(args);
    }

    @Deprecated
    public String getPresignedObjectUrl(
            Method method,
            String bucketName,
            String objectName,
            Integer expires,
            Map<String, String> reqParams)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidExpiresRangeException,
            InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException,
            ServerException, XmlParserException {
        return chooseMinioClient().getPresignedObjectUrl(method, bucketName, objectName, expires, reqParams);
    }

    public String getPresignedObjectUrl(GetPresignedObjectUrlArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidExpiresRangeException,
            InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException,
            XmlParserException, ServerException {
        return chooseMinioClient().getPresignedObjectUrl(args);
    }

    @Deprecated
    public String presignedGetObject(
            String bucketName, String objectName, Integer expires, Map<String, String> reqParams)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidExpiresRangeException,
            InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException,
            ServerException, XmlParserException {
        return chooseMinioClient().presignedGetObject(bucketName, objectName, expires, reqParams);
    }

    @Deprecated
    public String presignedGetObject(String bucketName, String objectName, Integer expires)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidExpiresRangeException,
            InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException,
            ServerException, XmlParserException {
        return chooseMinioClient().presignedGetObject(bucketName, objectName, expires);
    }

    @Deprecated
    public String presignedGetObject(String bucketName, String objectName)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidExpiresRangeException,
            InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException,
            ServerException, XmlParserException {
        return chooseMinioClient().presignedGetObject(bucketName, objectName);
    }

    @Deprecated
    public String presignedPutObject(String bucketName, String objectName, Integer expires)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidExpiresRangeException,
            InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException,
            ServerException, XmlParserException {
        return chooseMinioClient().presignedPutObject(bucketName, objectName, expires);
    }

    @Deprecated
    public String presignedPutObject(String bucketName, String objectName)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidExpiresRangeException,
            InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException,
            ServerException, XmlParserException {
        return chooseMinioClient().presignedPutObject(bucketName, objectName);

    }

    public Map<String, String> presignedPostPolicy(PostPolicy policy)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidExpiresRangeException,
            InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException,
            ServerException, XmlParserException {
        return chooseMinioClient().presignedPostPolicy(policy);
    }

    @Deprecated
    public void removeObject(String bucketName, String objectName)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().removeObject(bucketName, objectName);
    }

    public void removeObject(RemoveObjectArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().removeObject(args);
    }

    @Deprecated
    public Iterable<Result<DeleteError>> removeObjects(
            final String bucketName, final Iterable<String> objectNames) {
        return chooseMinioClient().removeObjects(bucketName, objectNames);
    }

    public Iterable<Result<DeleteError>> removeObjects(RemoveObjectsArgs args) {
        return chooseMinioClient().removeObjects(args);
    }

    @Deprecated
    public Iterable<Result<Item>> listObjects(final String bucketName) throws XmlParserException {
        return chooseMinioClient().listObjects(bucketName);
    }

    @Deprecated
    public Iterable<Result<Item>> listObjects(final String bucketName, final String prefix)
            throws XmlParserException {
        return chooseMinioClient().listObjects(bucketName, prefix);
    }

    @Deprecated
    public Iterable<Result<Item>> listObjects(
            final String bucketName, final String prefix, final boolean recursive) {
        return chooseMinioClient().listObjects(bucketName, prefix, recursive);
    }

    @Deprecated
    public Iterable<Result<Item>> listObjects(
            final String bucketName,
            final String prefix,
            final boolean recursive,
            final boolean useVersion1) {
        return chooseMinioClient().listObjects(bucketName, prefix, recursive, useVersion1);
    }

    @Deprecated
    public Iterable<Result<Item>> listObjects(
            final String bucketName,
            final String prefix,
            final boolean recursive,
            final boolean includeUserMetadata,
            final boolean useVersion1) {
        return chooseMinioClient().listObjects(bucketName, prefix, recursive, includeUserMetadata, useVersion1);
    }

    public Iterable<Result<Item>> listObjects(ListObjectsArgs args) {
        return chooseMinioClient().listObjects(args);
    }

    public List<Bucket> listBuckets()
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().listBuckets();
    }

    @Deprecated
    public boolean bucketExists(String bucketName)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().bucketExists(bucketName);
    }


    public boolean bucketExists(BucketExistsArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().bucketExists(args);
    }

    @Deprecated
    public void makeBucket(String bucketName)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, RegionConflictException,
            ServerException, XmlParserException {
        chooseMinioClient().makeBucket(bucketName);
    }

    @Deprecated
    public void makeBucket(String bucketName, String region)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, RegionConflictException,
            ServerException, XmlParserException {
        chooseMinioClient().makeBucket(bucketName, region);
    }

    @Deprecated
    public void makeBucket(String bucketName, String region, boolean objectLock)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, RegionConflictException,
            ServerException, XmlParserException {
        chooseMinioClient().makeBucket(bucketName, region, objectLock);
    }

    public void makeBucket(MakeBucketArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, RegionConflictException,
            ServerException, XmlParserException {
        chooseMinioClient().makeBucket(args);

    }

    @Deprecated
    public void removeBucket(String bucketName)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().removeBucket(bucketName);
    }

    public void removeBucket(RemoveBucketArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().removeBucket(args);
    }

    @Deprecated
    public void putObject(
            String bucketName, String objectName, String filename, PutObjectOptions options)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().putObject(bucketName, objectName, filename, options);
    }

    @Deprecated
    public void putObject(
            String bucketName, String objectName, InputStream stream, PutObjectOptions options)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().putObject(bucketName, objectName, stream, options);
    }

    public ObjectWriteResponse putObject(PutObjectArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().putObject(args);
    }

    public String getBucketPolicy(GetBucketPolicyArgs args)
            throws BucketPolicyTooLargeException, ErrorResponseException, IllegalArgumentException,
            InsufficientDataException, InternalException, InvalidBucketNameException,
            InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException,
            ServerException, XmlParserException {
        return chooseMinioClient().getBucketPolicy(args);
    }

    @Deprecated
    public void setBucketPolicy(String bucketName, String policy)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().setBucketPolicy(bucketName, policy);
    }

    public void setBucketPolicy(SetBucketPolicyArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().setBucketPolicy(args);
    }

    public void deleteBucketPolicy(DeleteBucketPolicyArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().deleteBucketPolicy(args);
    }

    @Deprecated
    public void setBucketLifeCycle(String bucketName, String lifeCycle)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().setBucketLifeCycle(bucketName, lifeCycle);
    }

    public void setBucketLifeCycle(SetBucketLifeCycleArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().setBucketLifeCycle(args);
    }

    @Deprecated
    public void deleteBucketLifeCycle(String bucketName)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().deleteBucketLifeCycle(bucketName);
    }

    public void deleteBucketLifeCycle(DeleteBucketLifeCycleArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().deleteBucketLifeCycle(args);
    }

    @Deprecated
    public String getBucketLifeCycle(String bucketName)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().getBucketLifeCycle(bucketName);
    }

    public String getBucketLifeCycle(GetBucketLifeCycleArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().getBucketLifeCycle(args);
    }

    @Deprecated
    public NotificationConfiguration getBucketNotification(String bucketName)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().getBucketNotification(bucketName);
    }

    public NotificationConfiguration getBucketNotification(GetBucketNotificationArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().getBucketNotification(args);
    }

    @Deprecated
    public void setBucketNotification(
            String bucketName, NotificationConfiguration notificationConfiguration)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().setBucketNotification(bucketName, notificationConfiguration);
    }

    public void setBucketNotification(SetBucketNotificationArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().setBucketNotification(args);
    }

    @Deprecated
    public void removeAllBucketNotification(String bucketName)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().removeAllBucketNotification(bucketName);
    }


    public void deleteBucketNotification(DeleteBucketNotificationArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().deleteBucketNotification(args);
    }

    @Deprecated
    public Iterable<Result<Upload>> listIncompleteUploads(String bucketName)
            throws XmlParserException {
        return chooseMinioClient().listIncompleteUploads(bucketName);
    }

    @Deprecated
    public Iterable<Result<Upload>> listIncompleteUploads(String bucketName, String prefix)
            throws XmlParserException {
        return chooseMinioClient().listIncompleteUploads(bucketName, prefix);
    }

    @Deprecated
    public Iterable<Result<Upload>> listIncompleteUploads(
            String bucketName, String prefix, boolean recursive) {
        return chooseMinioClient().listIncompleteUploads(bucketName, prefix, recursive);
    }

    public Iterable<Result<Upload>> listIncompleteUploads(ListIncompleteUploadsArgs args) {
        return chooseMinioClient().listIncompleteUploads(args);
    }


    @Deprecated
    public void removeIncompleteUpload(String bucketName, String objectName)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().removeIncompleteUpload(bucketName, objectName);
    }

    public void removeIncompleteUpload(RemoveIncompleteUploadArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().removeIncompleteUpload(args);
    }

    @Deprecated
    public CloseableIterator<Result<NotificationRecords>> listenBucketNotification(
            String bucketName, String prefix, String suffix, String[] events)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().listenBucketNotification(bucketName, prefix, suffix, events);
    }

    public CloseableIterator<Result<NotificationRecords>> listenBucketNotification(
            ListenBucketNotificationArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().listenBucketNotification(args);
    }

    @Deprecated
    public SelectResponseStream selectObjectContent(
            String bucketName,
            String objectName,
            String sqlExpression,
            InputSerialization is,
            OutputSerialization os,
            boolean requestProgress,
            Long scanStartRange,
            Long scanEndRange,
            ServerSideEncryptionCustomerKey ssec)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().selectObjectContent(bucketName, objectName, sqlExpression, is, os, requestProgress, scanStartRange, scanEndRange, ssec);
    }


    public SelectResponseStream selectObjectContent(SelectObjectContentArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().selectObjectContent(args);
    }

    public void setBucketEncryption(SetBucketEncryptionArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().setBucketEncryption(args);
    }

    public SseConfiguration getBucketEncryption(GetBucketEncryptionArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().getBucketEncryption(args);
    }

    public void deleteBucketEncryption(DeleteBucketEncryptionArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().deleteBucketEncryption(args);
    }

    public Tags getBucketTags(GetBucketTagsArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().getBucketTags(args);
    }


    public void setBucketTags(SetBucketTagsArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().setBucketTags(args);
    }

    public void deleteBucketTags(DeleteBucketTagsArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().deleteBucketTags(args);
    }

    public Tags getObjectTags(GetObjectTagsArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        return chooseMinioClient().getObjectTags(args);
    }

    public void setObjectTags(SetObjectTagsArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().setObjectTags(args);
    }

    public void deleteObjectTags(DeleteObjectTagsArgs args)
            throws ErrorResponseException, IllegalArgumentException, InsufficientDataException,
            InternalException, InvalidBucketNameException, InvalidKeyException,
            InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException,
            XmlParserException {
        chooseMinioClient().deleteObjectTags(args);
    }


    public String getServerStatus() {
        try {
            chooseMinioClient().bucketExists(BucketExistsArgs.builder().bucket(TEST_BUCKET).build());
            return "UP";
        } catch (Exception e) {
            return "DOWN";
        }
    }

}


