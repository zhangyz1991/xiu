package com.vick.framework.advice;

import com.vick.framework.exception.XiuRunTimeException;
import com.vick.framework.result.ResultEnum;
import com.vick.framework.result.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zyz
 * @since 2019-11-20
 */
@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResultModel finalExceptionHandler(Exception e) {
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(ResultEnum.FAILURE.getKey());
        resultModel.setMessage(ResultEnum.FAILURE.getDesc());
        return resultModel;
    }

    @ExceptionHandler(value = XiuRunTimeException.class)
    public ResultModel defaultExceptionHandler(XiuRunTimeException e) {
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(null == e.getCode() ? ResultEnum.FAILURE.getKey() : e.getCode());
        resultModel.setMessage(null == e.getMessage() ? ResultEnum.FAILURE.getDesc() : e.getMessage());
        return resultModel;
    }
}
