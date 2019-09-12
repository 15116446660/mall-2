package com.perenc.mall.gate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MallGateApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallGateApplication.class, args);
    }

}
