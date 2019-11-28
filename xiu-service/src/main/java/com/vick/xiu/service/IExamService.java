package com.vick.xiu.service;

import com.vick.framework.result.ResultModel;
import com.vick.xiu.entity.Exam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vick.xiu.web.request.ExamRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyz
 * @since 2019-11-26
 */
public interface IExamService extends IService<Exam> {

    ResultModel add(ExamRequest request);
}
