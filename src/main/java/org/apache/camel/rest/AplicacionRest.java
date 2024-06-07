package org.apache.camel.rest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AplicacionRest {

    public static void main(String[] args) {
        SpringApplication.run(RestConsumerRouter.class, args);

    }
    
}
