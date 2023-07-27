package com.example.musiApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Testing {
    @GetMapping()
    public static String home(){
        return "this for testing only....";
    }
}
