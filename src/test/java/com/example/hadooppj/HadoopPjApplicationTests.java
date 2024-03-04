package com.example.hadooppj;

import com.lantu.sys.service.HadoopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class HadoopPjApplicationTests {

    @Autowired
    private HadoopService hdfsService;

    @Test
    public void testWriteAndReadFile() throws Exception {
        String testPath = "/test-dir/test-file.txt";
        String content = "Hello Hadoop from Spring Boot Test!";

//        // Write content to a file in HDFS
//        boolean writeResult = hdfsService.writeFile(testPath, content);
//        assertTrue(writeResult, "Failed to write file to HDFS");

        // Read content back from the file
        String readContent = hdfsService.readFile(testPath);
        System.out.println(readContent);
        assertTrue(content.equals(readContent), "Read content does not match written content");

//        // Clean up: delete the test file
//        boolean deleteResult = hdfsService.deleteFile(testPath);
//        assertTrue(deleteResult, "Failed to delete test file");
    }

}
