package com.vick.framework.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zyz
 * @since 2019-11-11
 */
@Getter
@Setter
public class ResultModel<T> implements Serializable {

    private static final long serialVersionUID = -5603212625425176528L;

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;
}
