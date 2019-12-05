package com.vick.xiu.web.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author zyz
 * @since 2019-11-26
 */
@Getter
@Setter
public class ScoreResponse implements Serializable {
    
    private static final long serialVersionUID = -6332185742706477375L;

    @ApiModelProperty(value = "成绩ID")
    private Long id;

    @ApiModelProperty(value = "考试ID")
    private Long examId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户")
    private String userName;

    @ApiModelProperty(value = "用户总成绩")
    private Integer totalScore;

    @ApiModelProperty(value = "班级排名")
    private Integer classRanking;

    @ApiModelProperty(value = "年级排名")
    private Integer gradeRanking;

    @ApiModelProperty(value = "分数明细")
    private List<ScoreDetailResponse> scoreDetailList;

}
