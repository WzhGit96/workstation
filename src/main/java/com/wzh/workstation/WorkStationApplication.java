package com.wzh.workstation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wzh
 * @since 2023/2/23
 */
@SpringBootApplication(scanBasePackages = {"org.wzhframework", "com.wzh"})
public class WorkStationApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorkStationApplication.class);
    }
}
