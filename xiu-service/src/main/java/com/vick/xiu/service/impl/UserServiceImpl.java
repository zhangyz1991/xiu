package com.vick.xiu.service.impl;

import com.vick.xiu.entity.User;
import com.vick.xiu.mapper.UserMapper;
import com.vick.xiu.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyz
 * @since 2019-11-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
