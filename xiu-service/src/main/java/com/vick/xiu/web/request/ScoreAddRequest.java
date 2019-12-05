package com.vick.xiu.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author zyz
 * @since 2019-12-03
 */
@Getter
@Setter
public class ScoreAddRequest implements Serializable {

    private static final long serialVersionUID = -2363078740653565281L;

    @NotNull(message = "考试ID不能为空")
    @ApiModelProperty(value = "考试ID", required = true)
    private Long examId;

    @NotNull(message = "用户ID不能为空")
    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

    @NotNull(message = "用户总成绩不能为空")
    @ApiModelProperty(value = "用户总成绩", required = true)
    private Integer totalScore;

    @NotNull(message = "班级排名不能为空")
    @ApiModelProperty(value = "班级排名", required = true)
    private Integer classRanking;

    @NotNull(message = "年级排名不能为空")
    @ApiModelProperty(value = "年级排名", required = true)
    private Integer gradeRanking;

    @ApiModelProperty(value = "成绩明细")
    private List<ScoreDetailAddReq> scoreDetailList;
}
