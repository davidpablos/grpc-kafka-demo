package com.dpablos.grpckafkademo.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
    "com.dpablos.grpckafkademo.mail.application.bean",
    "com.dpablos.grpckafkademo.mail.application.configuration",
})
public class MailServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailServiceApplication.class, args);
    }

}