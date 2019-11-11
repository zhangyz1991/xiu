package com.vick.framework.page;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zyz
 * @since 2019-11-11
 */
@Getter
@Setter
public class PageRequest {

    /**
     * 当前页数
     */
    private int currentPage;

    /**
     * 每页条数
     */
    private int pageSize;
}
