package com.vick.xiu.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zyz
 * @since 2019-12-03
 */
@Getter
@Setter
public class ScoreDetailAddReq implements Serializable {

    private static final long serialVersionUID = 1618991797708098010L;

    @NotNull(message = "课程ID不能为空")
    @ApiModelProperty(value = "课程ID", required = true)
    private Long courseId;

    @NotNull(message = "分数不能为空")
    @ApiModelProperty(value = "分数", required = true)
    private Float score;
}
