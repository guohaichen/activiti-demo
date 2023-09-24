package com.example.activiti.demo.web.controller;

import com.example.activiti.demo.activiti.service.AskForLeaveBpm;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    SqlMapper sqlMapper;

    @GetMapping("log")
    public String queryProcess() {
        return sqlMapper.testSqlLog();
    }


}
