//package com.lantu;
//
//
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.config.OutputFile;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//import java.util.Collections;
//
//public class CodeGenerator {
//    public static void main(String[] args) {
//        String url="jdbc:mysql:///xdb";
//        String username="root";
//        String password="zengb123";
//        String muduleName="sys";
//        String mapperLocation="C:\\ideaproject\\x-admin\\src\\main\\resources\\mapper\\"+muduleName;
//        String table="x_user,x_role,x_menu,x_user_role,x_role_menu";
//        FastAutoGenerator.create(url, username, password)
//                .globalConfig(builder -> {
//                    builder.author("xuange") // 设置作者
////                            .enableSwagger() // 开启 swagger 模式
////                            .fileOverride() // 覆盖已生成文件
//                            .outputDir("C:\\ideaproject\\x-admin\\src\\main\\java"); // 指定输出目录
//                })
//
//                .packageConfig(builder -> {
//                    builder.parent("com.lantu") // 设置父包名
//                            .moduleName(muduleName) // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperLocation)); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude(table) // 设置需要生成的表名
//                            .addTablePrefix("x_","t_", "c_"); // 设置过滤表前缀
//                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
//    }
//}
