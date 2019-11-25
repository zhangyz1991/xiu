package com.vick.xiu.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zyz
 * @since 2019-11-25
 */
@Getter
@Setter
public class UserRequest implements Serializable {

    private static final long serialVersionUID = -860940036569744628L;

    @ApiModelProperty(value = "人员ID")
    private Long id;

    @ApiModelProperty(value = "姓名")
    private String name;
}
