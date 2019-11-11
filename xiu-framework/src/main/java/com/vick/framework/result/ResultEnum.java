package com.vick.framework.result;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum ResultEnum {

    SUCCESS("0", "success"),
    FAILURE("-1", "failure");

    private String key;
    private String desc;

    ResultEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    private static Map<String, String> resultEnumMap;

    static {
        resultEnumMap = new HashMap<>();
        for (ResultEnum resultEnum : ResultEnum.values()) {
            resultEnumMap.put(resultEnum.getKey(), resultEnum.getDesc());
        }
    }
}
