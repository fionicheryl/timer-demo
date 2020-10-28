package com.fion.timerdemo.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;

/**
 * 动态任务抽象类
 *
 * @author fion yang
 * @date 2020-10-28 14:42
 */
@Slf4j
public abstract class AbstractDynamicTask {

    /**
     * 获取任务名称
     *
     * @return
     */
    protected abstract String getName();

    /**
     * 定时任务是否开启
     *
     * @return
     */
    protected abstract boolean isEnable();

    /**
     * 获取定时任务cron表达式
     *
     * @return
     */
    protected abstract String getCron();

    /**
     * 执行任务
     */
    protected abstract void doTask();

    /**
     * 获取定时任务触发器
     *
     * @return
     */
    private Trigger trigger() {
        return triggerContext -> {
            CronTrigger trigger = new CronTrigger(getCron());
            return trigger.nextExecutionTime(triggerContext);
        };
    }

    /**
     * 获取任务
     *
     * @return
     */
    private Runnable task() {
        return () -> {
            String name = getName();
            boolean enable = isEnable();
            String cron = getCron();
            log.info("[Dynamic Task] name: {}, enable: {}, cron: {}", name, enable, cron);
            if (enable) {
                try {
                    doTask();
                } catch (Exception e) {
                    // 可加告警
                    log.error("[Dynamic Task] do task error", e);
                }
            }
        };
    }

    /**
     * 获取定时任务
     *
     * @return
     */
    public TriggerTask triggerTask() {
        return new TriggerTask(task(), trigger());
    }
}
