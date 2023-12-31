package com.vaultcode.springbatchsection6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class SpringBatchSection6Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchSection6Application.class, args);
    }

}
