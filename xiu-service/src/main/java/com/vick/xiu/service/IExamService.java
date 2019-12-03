package com.vick.xiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vick.framework.page.PageRequest;
import com.vick.framework.result.ResultModel;
import com.vick.xiu.entity.Exam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vick.xiu.web.request.ExamRequest;
import com.vick.xiu.web.response.ExamResponse;

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

    ResultModel<IPage<ExamResponse>> list(PageRequest request);
}
