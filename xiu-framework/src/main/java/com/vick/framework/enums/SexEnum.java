package com.vick.framework.enums;

import lombok.Getter;

@Getter
public enum SexEnum {

    UNKNOWN(0, "未知"),
    MALE(1, "男"),
    FEMALE(2, "女");

    private Integer key;
    private String desc;

    SexEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }
}
