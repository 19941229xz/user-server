package com.example.userserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@EnableEurekaClient
public class UserServerApplication {

    @Value("${spring.application.name}")
    public String name;

    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }


    @RequestMapping("/hello")
    public String hello(){
        return name;
    }

}
