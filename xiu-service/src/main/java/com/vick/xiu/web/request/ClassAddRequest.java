package com.vick.xiu.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zyz
 * @since 2019-12-09
 */
@Getter
@Setter
public class ClassAddRequest implements Serializable {

    private static final long serialVersionUID = -2849307970017165609L;

    @NotNull(message = "年级不能为空")
    @ApiModelProperty(value = "年级ID")
    private Long gradeId;

    @NotBlank(message = "班级名称不能为空")
    @ApiModelProperty(value = "班级")
    private String name;

    @ApiModelProperty(value = "班级人数")
    private Integer size;
}
