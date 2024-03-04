package com.lantu.sys.service;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class HadoopService {

    private final FileSystem fileSystem;

    public HadoopService() throws IOException {
        // 初始化Hadoop文件系统的配置
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://192.168.88.128:9000");
        // 请根据您的实际Hadoop集群配置设置fs.defaultFS
        this.fileSystem = FileSystem.get(configuration);
    }

    public boolean writeFile(String path, String content) throws IOException {
        Path hdfsWritePath = new Path(path);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
        if (fileSystem.exists(hdfsWritePath)) {
            return false; // 文件已存在，避免覆盖
        }
        try (org.apache.hadoop.fs.FSDataOutputStream outputStream = fileSystem.create(hdfsWritePath)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            return true;
        } catch (IOException e) {
            throw new IOException("Failed to write file to HDFS", e);
        }
    }

    public String readFile(String path) throws IOException {
        Path hdfsReadPath = new Path(path);
        if (!fileSystem.exists(hdfsReadPath)) {
            throw new IOException("File does not exist: " + path);
        }
        try (org.apache.hadoop.fs.FSDataInputStream inputStream = fileSystem.open(hdfsReadPath);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            return outputStream.toString(StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            throw new IOException("Failed to read file from HDFS", e);
        }
    }

    public boolean deleteFile(String path) throws IOException {
        Path hdfsDeletePath = new Path(path);
        if (!fileSystem.exists(hdfsDeletePath)) {
            return false; // 文件不存在
        }
        return fileSystem.delete(hdfsDeletePath, false); // false表示不递归删除
    }
}