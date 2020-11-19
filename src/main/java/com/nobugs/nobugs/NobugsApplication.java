package com.nobugs.nobugs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.nobugs.*"})
@MapperScan(value = "com.nobugs.dao")
public class NobugsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NobugsApplication.class, args);
    }

}
