package com.vick.framework.result;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @author zyz
 * @since 2019-11-11
 */
public class ResultUtil {

    public static ResultModel success() {
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(Integer.valueOf(ResultEnum.SUCCESS.getKey()));
        resultModel.setMessage(ResultEnum.SUCCESS.getDesc());
        return resultModel;
    }

    public static ResultModel success(Object obj) {
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(Integer.valueOf(ResultEnum.SUCCESS.getKey()));
        resultModel.setMessage(ResultEnum.SUCCESS.getDesc());
        resultModel.setData(obj);
        return resultModel;
    }

    public static ResultModel failure(String message) {
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(Integer.valueOf(ResultEnum.FAILURE.getKey()));
        resultModel.setMessage(message);
        return resultModel;
    }

    public static ResultModel failure(int code, String message) {
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(code);
        resultModel.setMessage(message);
        return resultModel;
    }

    public static ResultModel validFailure(BindingResult bindingResult) {
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(Integer.valueOf(ResultEnum.FAILURE.getKey()));

        StringBuffer stringBuffer = new StringBuffer();
        List<ObjectError> errorList = bindingResult.getAllErrors();
        errorList.forEach(objectError -> stringBuffer.append(objectError.getDefaultMessage()).append(","));
        String errorMessage = stringBuffer.substring(0, stringBuffer.length() - 1);

        resultModel.setMessage(errorMessage);
        return resultModel;
    }
}
