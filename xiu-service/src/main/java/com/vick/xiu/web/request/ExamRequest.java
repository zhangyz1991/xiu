package com.vick.xiu.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @author zyz
 * @since 2019-11-26
 */
@Getter
@Setter
public class ExamRequest implements Serializable {

    private static final long serialVersionUID = -7747295327676362788L;

    @ApiModelProperty(value = "考试ID")
    private Long id;

    @NotNull(message = "所在年级不能为空")
    @ApiModelProperty(value = "所在年级")
    private Long gradeId;

    @NotBlank(message = "考试名称不能为空")
    @ApiModelProperty(value = "考试名称")
    private String name;

    @NotNull(message = "考试时间不能为空")
    @ApiModelProperty(value = "考试时间")
    private LocalDate date;

    @NotNull(message = "本次考试满分")
    @ApiModelProperty(value = "满分")
    private Float fullMarks;

    @NotNull(message = "班级参考人数不能为空")
    @ApiModelProperty(value = "班级人数")
    private Integer classSize;

    @NotNull(message = "年级参考人数不能为空")
    @ApiModelProperty(value = "年级人数")
    private Integer gradeSize;

    @NotEmpty(message = "考试科目不能为空")
    @ApiModelProperty(value = "考试科目列表")
    private List<ExamCourseRequest> examCourseList;

}
