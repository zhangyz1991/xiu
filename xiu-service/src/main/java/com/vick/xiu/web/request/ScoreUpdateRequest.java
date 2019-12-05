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
public class ScoreUpdateRequest implements Serializable {

    private static final long serialVersionUID = -5313107831227365879L;

    @NotNull(message = "成绩ID不能为空")
    @ApiModelProperty(value = "成绩ID")
    private Long id;

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
    private List<ScoreDetailUpdateReq> scoreDetailList;
}
