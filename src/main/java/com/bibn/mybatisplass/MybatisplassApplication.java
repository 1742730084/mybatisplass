package com.bibn.mybatisplass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.bibn.mybatisplass")
@SpringBootApplication
public class MybatisplassApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisplassApplication.class, args);
    }

}
