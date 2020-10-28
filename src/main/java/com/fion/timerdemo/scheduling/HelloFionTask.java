package com.fion.timerdemo.scheduling;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 打印 Hello Fion 任务
 *
 * @author fion yang
 * @date 2020-10-28 15:25
 */
@Component
public class HelloFionTask extends AbstractDynamicTask {

    /**
     * 定时任务周期表达式
     */
    @Value("${hello.fion.cron:0/5 * * * * ?}")
    private String cron;

    /**
     * Hello Fion 任务开关
     */
    @Value("${hello.fion.enable:true}")
    private Boolean enable;

    /**
     * 获取任务名称
     *
     * @return
     */
    @Override
    protected String getName() {
        return TaskNameEnum.HELLO_FION.getName();
    }

    /**
     * 定时任务是否开启
     *
     * @return
     */
    @Override
    protected boolean isEnable() {
        return enable;
    }

    /**
     * 获取定时任务cron表达式
     *
     * @return
     */
    @Override
    protected String getCron() {
        return cron;
    }

    /**
     * 执行任务
     */
    @Override
    protected void doTask() {
        System.out.println("Hello Fion");
    }
}
