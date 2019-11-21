package com.vick.xiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vick.framework.page.PageRequest;
import com.vick.framework.result.ResultModel;
import com.vick.xiu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyz
 * @since 2019-11-10
 */
public interface ICourseService extends IService<Course> {

    ResultModel<IPage<Course>> list(PageRequest request);
}
