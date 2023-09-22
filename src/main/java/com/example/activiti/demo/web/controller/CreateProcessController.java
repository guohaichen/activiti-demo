package com.example.activiti.demo.web.controller;

import com.example.activiti.demo.activiti.service.AskForLeaveBpmImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cgh
 * @create 2023-09-22
 */
@RestController
@RequestMapping
public class CreateProcessController {

    @Autowired
    AskForLeaveBpmImpl askForLeaveBpm;

    @GetMapping("process")
    public String createProcess(@RequestParam String userId, @RequestParam String processKey) {
        return askForLeaveBpm.createProcess(userId, processKey);
    }
}
