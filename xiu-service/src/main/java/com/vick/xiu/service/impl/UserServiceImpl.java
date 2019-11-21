package com.vick.xiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vick.framework.page.Page;
import com.vick.framework.result.ResultModel;
import com.vick.framework.result.ResultUtil;
import com.vick.xiu.entity.User;
import com.vick.xiu.mapper.UserMapper;
import com.vick.xiu.service.IUserService;
import com.vick.xiu.web.request.UserListRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zyz
 * @since 2019-11-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public ResultModel<IPage<User>> list(UserListRequest request) {
        Page page = new Page(request.getCurrentPage(), request.getPageSize());
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.orderByDesc("id");
        IPage iPage = userMapper.selectPage(page, wrapper);
        return ResultUtil.success(iPage);
    }
}
