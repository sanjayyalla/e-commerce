package com.jocata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class ProductServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceMain.class, args);
    }
}