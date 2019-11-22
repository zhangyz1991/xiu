package com.vick.framework.page;

/**
 * @author zyz
 * @since 2019-11-21
 */

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 作者：zyz 日期：2019/11/21 描述：简单分页模型
 */
public class Page<T> extends com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> {

    private static final long serialVersionUID = 2285332413185444356L;

    /**
     * 总数
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long total;
    /**
     * 每页显示条数
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long size;

    /**
     * 当前页
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long current;

    /**
     * 总页数
     */
    @JsonSerialize(using = LongJsonSerializer.class)
    private long pages;

    /**
     * 分页构造函数
     *
     * @param current 当前页
     * @param size    每页显示条数
     */
    public Page(long current, long size) {
        super(current, size, 0);
    }

}
