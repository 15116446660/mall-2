package com.perenc.mall.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.perenc.mall")
@MapperScan("com.perenc.mall.*.mapper")
public class MallPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallPlatformApplication.class, args);
    }

}
