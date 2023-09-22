package com.example.activiti.demo.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cgh
 * @create 2023-09-19
 */
@RestController
@RequestMapping
public class TestStringController {

    @GetMapping("index")
    public String index() {
        return "hello";
    }
}
