package com.vick.xiu.web.request;

import com.vick.framework.enums.SexEnum;
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
public class UserUpdateRequest implements Serializable {

    private static final long serialVersionUID = -860940036569744628L;

    @NotNull(message = "用户ID不能为空")
    @ApiModelProperty(value = "用户ID", required = true)
    private Long id;

    @NotBlank(message = "姓名不能为空")
    @ApiModelProperty(value = "姓名")
    private String name;

    @NotNull(message = "性别不能为空")
    @ApiModelProperty(value = "姓别")
    private SexEnum sex;

    @ApiModelProperty(value = "身份证号")
    private String idNumber;

    @ApiModelProperty(value = "生日")
    private LocalDate birthday;
    
}
