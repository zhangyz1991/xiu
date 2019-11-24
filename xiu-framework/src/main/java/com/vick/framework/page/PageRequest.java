package com.vick.framework.page;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "当前页数", notes = "当前页数", required = true)
    private int currentPage;

    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数", notes = "每页条数", required = true)
    private int pageSize;
}
