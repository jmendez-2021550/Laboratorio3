package com.jeremymendez.Rautomotriz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutomotiveSparePartsProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AutomotiveSparePartsProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("API en Funcionamiento.");
    }
}
