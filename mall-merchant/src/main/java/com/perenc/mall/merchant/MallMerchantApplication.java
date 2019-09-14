package com.perenc.mall.merchant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.perenc.mall")
@EnableFeignClients({"com.perenc.mall.mobile.service"})
@MapperScan("com.perenc.mall.*.mapper")
public class MallMerchantApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallMerchantApplication.class, args);
    }

}
