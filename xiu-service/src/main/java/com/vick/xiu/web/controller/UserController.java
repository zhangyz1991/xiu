package com.vick.xiu.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.vick.framework.result.ResultModel;
import com.vick.framework.result.ResultUtil;
import com.vick.xiu.entity.User;
import com.vick.xiu.service.IUserService;
import com.vick.xiu.web.request.UserAddRequest;
import com.vick.xiu.web.request.UserDeleteRequest;
import com.vick.xiu.web.request.UserListRequest;
import com.vick.xiu.web.request.UserUpdateRequest;
import com.vick.xiu.web.response.UserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zyz
 * @since 2019-11-10
 */
@Slf4j
@Api(tags = "WEB端 - 用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @ApiOperation(value = "学生列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, dataType = "UserListRequest")
    })
    @GetMapping(value = "list")
    public ResultModel<IPage<UserResponse>> list(@ModelAttribute UserListRequest request) {
        return iUserService.list(request);
    }

    @ApiOperation(value = "更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, dataType = "UserAddRequest")
    })
    @PostMapping(value = "update")
    public ResultModel update(@Valid @RequestBody UserUpdateRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ResultUtil.validFailure(bindingResult);
        }
        return iUserService.update(request);
    }

    @ApiOperation(value = "新增用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, dataType = "UserAddRequest")
    })
    @PostMapping(value = "add")
    public ResultModel add(@Valid @RequestBody UserAddRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ResultUtil.validFailure(bindingResult);
        }
        if (!StringUtils.isEmpty(StringUtils.trimWhitespace(request.getIdNumber()))) {
            request.setIdNumber(StringUtils.trimWhitespace(request.getIdNumber()));
            QueryWrapper<User> queryWrapper = Wrappers.query();
            queryWrapper.eq("id_number", request.getIdNumber());
            int count = iUserService.count(queryWrapper);
            if(count > 0) {
                return ResultUtil.failure("新增失败,身份证号重复");
            }
        }
        User user = new User();
        BeanUtils.copyProperties(request, user);
        boolean saveResult = iUserService.save(user);
        return saveResult ? ResultUtil.success() : ResultUtil.failure("新增用户失败");
    }

    @ApiOperation(value = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, dataType = "UserAddRequest")
    })
    @PostMapping(value = "delete")
    public ResultModel delete(@Valid @RequestBody UserDeleteRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ResultUtil.validFailure(bindingResult);
        }
        boolean removeResult = iUserService.removeById(request.getId());
        return removeResult ? ResultUtil.success() : ResultUtil.failure("删除失败");
    }
}
