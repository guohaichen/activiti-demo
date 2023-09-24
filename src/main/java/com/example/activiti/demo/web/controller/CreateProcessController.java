package com.example.activiti.demo.web.controller;

import com.example.activiti.demo.activiti.service.AskForLeaveBpmImpl;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cgh
 * @create 2023-09-22
 */
@RestController
@Slf4j
@RequestMapping
public class CreateProcessController {

    @Autowired
    AskForLeaveBpmImpl askForLeaveBpm;

    @Autowired
    TaskService taskService;

    @GetMapping("processInstance")
    public String createProcessInstance(@RequestParam String userId, @RequestParam String processKey) {
        return askForLeaveBpm.createProcessInstance(userId, processKey);
    }

    @GetMapping("process")
    public String createProcess() {
        return askForLeaveBpm.buildProcess();
    }

    /**
     * 查询
     *
     * @param username 用户id
     * @return 返回代办任务
     */
    @GetMapping("myTodo")
    public List<String> queryTaskToCompById(@RequestParam String username) {
        List<Task> todoTaskList = taskService.createTaskQuery()
                .processDefinitionKey("ask-for-leave-app-test")
                .taskAssignee(username)
                .list();
        ArrayList<String> taskList = new ArrayList<>();
        todoTaskList.forEach(
                (one) -> {
                    taskList.add(one.getName());
                    log.info("任务名称：{}", one.getName());
                }
        );
        return taskList;
    }
}
