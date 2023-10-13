package com.dpablos.grpckafkademo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
    "com.dpablos.grpckafkademo.user.application.bean",
    "com.dpablos.grpckafkademo.user.application.configuration.handler",
})
public class UserServiceGrpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceGrpcApplication.class, args);
    }

}