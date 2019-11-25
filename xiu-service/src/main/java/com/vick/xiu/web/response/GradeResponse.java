package com.vick.xiu.web.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author zyz
 * @since 2019-11-25
 */
@Getter
@Setter
public class GradeResponse implements Serializable {

    private static final long serialVersionUID = 2340361909705045018L;

    @ApiModelProperty(value = "年级ID")
    private Long id;

    @ApiModelProperty(value = "年级")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "开始时间")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "结束时间")
    private LocalDate endDate;
}
