package com.vick.xiu.web.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author zyz
 * @since 2019-12-04
 */
@Getter
@Setter
public class ScoreDetailResponse implements Serializable {

    private static final long serialVersionUID = -5919835936069624309L;

    @ApiModelProperty(value = "成绩ID")
    private Long scoreId;

    @ApiModelProperty(value = "科目ID")
    private Long courseId;

    @ApiModelProperty(value = "科目编码")
    private String courseCode;

    @ApiModelProperty(value = "科目")
    private String courseName;

    @ApiModelProperty(value = "分数")
    private Float score;
}
