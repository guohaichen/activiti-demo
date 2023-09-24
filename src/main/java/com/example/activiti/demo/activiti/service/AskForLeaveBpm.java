package com.example.activiti.demo.activiti.service;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author cgh
 * @create 2023-09-19
 */
@Mapper
public interface AskForLeaveBpm {

    /**
     * @param userId     用户id，根据用户id 查询用户信息，所在部门，部门领导等；
     * @param processKey 流程id,
     */
    String createProcessInstance(String userId, String processKey);

    String buildProcess();

}
