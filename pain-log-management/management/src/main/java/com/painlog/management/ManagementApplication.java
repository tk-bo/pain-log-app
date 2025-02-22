package com.painlog.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ManagementApplication {
    public static void main(String[] args) {
      SpringApplication.run(ManagementApplication.class, args);
    }
}
