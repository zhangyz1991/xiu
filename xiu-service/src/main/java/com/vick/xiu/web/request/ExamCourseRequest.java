package com.vick.xiu.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zyz
 * @since 2019-11-27
 */
@Getter
@Setter
public class ExamCourseRequest implements Serializable {

    private static final long serialVersionUID = 5478426641437463912L;

    @ApiModelProperty(value = "考试ID")
    private Long examId;

    @NotNull(message = "课程不能为空")
    @ApiModelProperty(value = "课程ID")
    private Long courseId;

    @NotNull(message = "课程分数制不能为空")
    @ApiModelProperty(value = "分制")
    private Integer pointScale;

}
