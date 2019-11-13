package com.vick.xiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vick.framework.result.ResultModel;
import com.vick.xiu.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vick.xiu.web.request.UserListRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyz
 * @since 2019-11-10
 */
public interface IUserService extends IService<User> {

    ResultModel<IPage<User>> list(UserListRequest request);
}
