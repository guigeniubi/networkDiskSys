package com.lantu.sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lantu.sys.mapper")
public class HadoopPjApplication {

    public static void main(String[] args) {
        SpringApplication.run(HadoopPjApplication.class, args);
    }

}
