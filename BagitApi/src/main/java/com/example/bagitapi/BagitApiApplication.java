package com.example.bagitapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.Charset;

@SpringBootApplication
public class BagitApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BagitApiApplication.class, args);
    }



}
