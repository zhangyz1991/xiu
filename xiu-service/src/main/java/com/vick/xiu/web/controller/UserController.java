package com.vick.xiu.web.controller;


import com.vick.xiu.entity.User;
import com.vick.xiu.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyz
 * @since 2019-11-10
 */
@Api(tags = "WEB端 - 用户信息")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping(value = "list")
    @ApiOperation(value = "学生列表", notes = "学生列表")
    @ApiImplicitParams({})
    public List<User> list(){
        return iUserService.list();
    }
}
