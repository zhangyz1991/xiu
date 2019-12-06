package com.vick.xiu.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zyz
 * @since 2019-11-25
 */
@Getter
@Setter
public class UserDeleteRequest implements Serializable {

    private static final long serialVersionUID = -860940036569744628L;

    @NotNull(message = "用户ID不能为空")
    @ApiModelProperty(value = "用户ID", required = true)
    private Long id;
    
}
