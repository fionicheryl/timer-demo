package com.fion.timerdemo.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduled注解方式实现定时任务
 *
 * @author fion yang
 * @date 2020-10-28 14:22
 */
@Component
public class SchedulDemoTask {

    // @Scheduled(cron = "0/10 * * * * ?")
    private void doTask() {
        System.out.println("hello Scheduled");
    }
}
