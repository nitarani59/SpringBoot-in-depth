package com.springboot.practice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${hello.world.message}")
    private String message;

    @GetMapping("/")
    public String helloWorld() {
        return message;
    }
}
