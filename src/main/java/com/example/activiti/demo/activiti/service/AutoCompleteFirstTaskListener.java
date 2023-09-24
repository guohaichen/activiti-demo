package com.example.activiti.demo.activiti.service;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cgh
 * @create 2023-09-22
 * 自动完成流程实例的第一个任务，因为第一个任务为发起人提交的，提交即完成，
 * 1. 这里是在bpm文件中加了监听器，所以只对该流程生效。
 * 具体见bpm文件
 * <userTask id="sid-1c99db59-22c5-46fd-af56-f5231995258f" name="user" activiti:assignee="${user}">
 * <extensionElements>
 * <activiti:taskListener class="com.example.activiti.demo.activiti.service.AutoCompleteFirstTaskListener" event="create"/>
 * </extensionElements>
 * </userTask>
 */
@Slf4j
@Component
public class AutoCompleteFirstTaskListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution delegateExecution) {
        TaskService taskService = ProcessEngines.getDefaultProcessEngine().getTaskService();
        String username = (String) delegateExecution.getVariable("user");

        //获取任务id
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("ask-for-leave-app-test") //流程Key
                .taskAssignee(username)  //负责人
                .singleResult();
        String taskId = task.getId();
        log.info("taskId:{}",taskId);

        //完成该任务
        taskService.complete(taskId);
    }
}
