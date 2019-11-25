package com.vick.xiu.web.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author zyz
 * @since 2019-11-25
 */
@Getter
@Setter
public class GradeRequest implements Serializable {

    private static final long serialVersionUID = 3351814882029943335L;

    @ApiModelProperty(value = "年级ID")
    private Long id;

    @NotBlank(message = "年级名称不能为空")
    @ApiModelProperty(value = "年级")
    private String name;

    @NotNull(message = "开始时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "开始时间")
    private LocalDate startDate;

    @NotNull(message = "结束时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "结束时间")
    private LocalDate endDate;

}
