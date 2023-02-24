package com.wzh.workstation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wzh
 * @since 2023/2/23
 */
@ComponentScan({"org.wzhframework", "com.wzh"})
@SpringBootApplication
public class WorkStationApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkStationApplication.class);
    }
}
