package com.example.activiti.demo.activiti.service;

import org.springframework.stereotype.Service;

/**
 * @author cgh
 * @create 2023-09-19
 */
@Service
public interface AskForLeaveBpm {

    /**
     * @param userId     用户id，根据用户id 查询用户信息，所在部门，部门领导等；
     * @param processKey 流程id,
     */
    String createProcess(String userId, String processKey);
}
