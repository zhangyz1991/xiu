package com.vick.xiu.web.response;

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
public class CourseResponse implements Serializable {

    private static final long serialVersionUID = 1336595618638920724L;

    @ApiModelProperty(value = "课程ID")
    private Long id;

    @ApiModelProperty(value = "课程编码")
    private String code;

    @ApiModelProperty(value = "课程名称")
    private String name;
}
