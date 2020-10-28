package com.fion.timerdemo.scheduling;

import lombok.Getter;

/**
 * 定时任务名称枚举
 *
 * @author fion yang
 * @date 2020-10-28 15:33
 */
@Getter
public enum TaskNameEnum {

    /**
     * 打印Hello Fion
     */
    HELLO_FION("打印Hello Fion"),

    /**
     * 打印Hello Cheryl
     */
    HELLO_CHERYL("打印Hello Cheryl"),
    ;

    /**
     * 任务名称
     */
    private String name;

    TaskNameEnum(String name) {
        this.name = name;
    }
}
