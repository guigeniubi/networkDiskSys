package com.example.hadooppj;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class generator {
    public static void main(String[] args) {
        String url="jdbc:mysql:///netdisk";
        String username="root";
        String password="zengb123";
        String muduleName="sys";
        String mapperLocation="E:\\IdeaProjects\\hadoopPJ\\src\\main\\resources\\mapper";
        String table="files,users";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("xuange") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E:\\IdeaProjects\\hadoopPJ\\src\\main\\java"); // 指定输出目录
                })

                .packageConfig(builder -> {
                    builder.parent("com.lantu") // 设置父包名
                            .moduleName(muduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(table) ;// 设置需要生成的表名

                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}