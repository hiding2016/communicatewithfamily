package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//扫描mybatis包路径
@MapperScan(basePackages ="com.study.mapper")
//扫描所有需要的包，包含一些工具类所在的路径
@ComponentScan(basePackages = {
        "com.study",
        "org.n3r.idworker"

})
//开启定时任务
//@EnableScheduling
////开启异步调用
//@EnableAsync
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
