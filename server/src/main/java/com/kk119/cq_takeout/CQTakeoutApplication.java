package com.kk119.cq_takeout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@Slf4j
@EnableScheduling
public class CQTakeoutApplication {
    public static void main(String[] args) {
        SpringApplication.run(CQTakeoutApplication.class, args);
        log.info("CQTakeout Application Started");
    }
}
