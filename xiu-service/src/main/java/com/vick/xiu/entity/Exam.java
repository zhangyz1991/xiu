package com.vick.xiu.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author zyz
 * @since 2019-11-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Exam对象", description="")
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "考试ID")
    private Long id;

    @ApiModelProperty(value = "所在年级")
    private Long gradeId;

    @ApiModelProperty(value = "考试名称")
    private String name;

    @ApiModelProperty(value = "考试时间")
    private LocalDate date;

    @ApiModelProperty(value = "总成绩")
    private Float totalScore;

    @ApiModelProperty(value = "班级排名")
    private Float classRanking;

    @ApiModelProperty(value = "班级人数")
    private Float classSize;

    @ApiModelProperty(value = "年级排名")
    private Float gradeRanking;

    @ApiModelProperty(value = "年级人数")
    private Float gradeSize;


}
