package com.mibiatchi.rentals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MibiatchiCarApplication {

    public static void main(String[] args) {
        SpringApplication.run(MibiatchiCarApplication.class, args);
    }

}
