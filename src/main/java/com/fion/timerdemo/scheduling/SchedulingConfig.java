package com.fion.timerdemo.scheduling;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * SchedulingConfigurer 实现定时任务
 *
 * @author fion yang
 * @date 2020-10-28 14:39
 */
@Configuration
@EnableScheduling
public class SchedulingConfig implements SchedulingConfigurer {

    /**
     * 线程池任务调度类
     */
    private ThreadPoolTaskScheduler taskScheduler;

    /**
     * 注入所有定时任务
     */
    @Autowired
    private List<AbstractDynamicTask> tasks;

    /**
     * 配置任务
     *
     * @param scheduledTaskRegistrar 任务注册器
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        if (null == scheduledTaskRegistrar.getScheduler()) {
            scheduledTaskRegistrar.setScheduler(taskScheduler);
        }
        if (!CollectionUtils.isEmpty(tasks)) {
            tasks.forEach(task -> scheduledTaskRegistrar.addTriggerTask(task.triggerTask()));
        }
    }

    /**
     * 初始化方法，初始线程池任务调度类
     */
    @PostConstruct
    public void init() {
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(Runtime.getRuntime().availableProcessors() * 2 + 1);
        taskScheduler.setThreadFactory(new ThreadFactoryBuilder().setNameFormat("scheduler-%d").build());
        taskScheduler.initialize();
    }

    /**
     * 销毁方法，关闭线程池
     */
    @PreDestroy
    public void destroy() {
        if (null != taskScheduler) {
            taskScheduler.shutdown();
        }
    }
}
