package com.vick.xiu.web.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zyz
 * @since 2019-12-09
 */
@Getter
@Setter
public class ClassResponse implements Serializable {

    private static final long serialVersionUID = 2559451576743892142L;

    @ApiModelProperty(value = "班级ID")
    private Long id;

    @ApiModelProperty(value = "年级")
    private String gradeName;

    @ApiModelProperty(value = "班级")
    private String name;

    @ApiModelProperty(value = "班级人数")
    private Integer size;
}
