package com.example.activiti.demo.activiti.service;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

/**
 * @author cgh
 * @create 2023-09-22
 * 自动完成流程实例的第一个任务，因为第一个任务为发起人提交的，提交即完成，
 */
@Slf4j
@Component
public class AutoCompleteFirstTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("监听器触发");
    }
}
