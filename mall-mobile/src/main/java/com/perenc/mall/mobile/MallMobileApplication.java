package com.perenc.mall.mobile;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.perenc.mall")
@EnableFeignClients({"com.perenc.mall.mobile.service"})
@MapperScan("com.perenc.mall.mobile.mapper")
public class MallMobileApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallMobileApplication.class, args);
    }

}
