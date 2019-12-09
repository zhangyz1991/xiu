package com.vick.xiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vick.framework.page.PageRequest;
import com.vick.framework.result.ResultModel;
import com.vick.xiu.entity.Class;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vick.xiu.web.request.ClassAddRequest;
import com.vick.xiu.web.response.ClassResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyz
 * @since 2019-12-09
 */
public interface IClassService extends IService<Class> {

    ResultModel add(ClassAddRequest request);

    ResultModel<IPage<ClassResponse>> list(PageRequest request);
}
