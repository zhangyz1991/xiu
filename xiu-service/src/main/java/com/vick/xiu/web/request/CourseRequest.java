package com.vick.xiu.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zyz
 * @since 2019-11-21
 */
@Getter
@Setter
public class CourseRequest implements Serializable {

    private static final long serialVersionUID = 2620141434430114853L;

    @ApiModelProperty(value = "课程ID")
    private Integer id;

    @ApiModelProperty(value = "课程名称")
    private String name;
}
