package com.vick.xiu.web.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author zyz
 * @since 2019-11-26
 */
@Getter
@Setter
public class ExamResponse implements Serializable {

    private static final long serialVersionUID = 1818557846171702031L;

    @ApiModelProperty(value = "考试ID")
    private Long id;

    @ApiModelProperty(value = "所在年级")
    private Long gradeId;

    @ApiModelProperty(value = "考试名称")
    private String name;

    @ApiModelProperty(value = "考试时间")
    private LocalDate date;

    @ApiModelProperty(value = "满分")
    private Float fullMarks;

    @ApiModelProperty(value = "班级人数")
    private Integer classSize;

    @ApiModelProperty(value = "年级人数")
    private Integer gradeSize;
}
