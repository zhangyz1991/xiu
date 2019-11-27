package com.vick.xiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vick.framework.page.Page;
import com.vick.framework.result.ResultModel;
import com.vick.framework.result.ResultUtil;
import com.vick.framework.util.ConverterUtils;
import com.vick.xiu.entity.User;
import com.vick.xiu.mapper.UserMapper;
import com.vick.xiu.service.IUserService;
import com.vick.xiu.web.request.UserQueryRequest;
import com.vick.xiu.web.request.UserRequest;
import com.vick.xiu.web.response.UserResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

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
    public ResultModel<IPage<UserResponse>> list(UserQueryRequest request) {
        Page page = new Page(request.getCurrentPage(), request.getPageSize());
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.orderByDesc("id");
        IPage iPage = userMapper.selectPage(page, wrapper);
        if (CollectionUtils.isEmpty(iPage.getRecords())) {
            return ResultUtil.success();
        }
        List list = ConverterUtils.convert(iPage.getRecords(), UserResponse.class);
        iPage.setRecords(list);
        return ResultUtil.success(iPage);
    }

    @Override
    public ResultModel update(UserRequest request) {
        if (null == request.getId()) {
            return ResultUtil.failure("ID不能为空");
        }
        User user = new User();
        BeanUtils.copyProperties(request, user);
        userMapper.updateById(user);
        return ResultUtil.success();
    }
}
