package com.vick.framework.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zyz
 * @since 2019-11-20
 */
@Getter
@Setter
public class XiuRunTimeException extends RuntimeException {

    /**
     * 作者：zyz 日期：2019/11/20 描述：异常码
     */
    private Integer code;

    /**
     * 作者：zyz 日期：2019/11/20 描述：异常消息
     */
    private String message;
}
