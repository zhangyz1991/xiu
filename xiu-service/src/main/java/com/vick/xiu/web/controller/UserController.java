package com.vick.xiu.web.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vick.framework.result.ResultModel;
import com.vick.framework.result.ResultUtil;
import com.vick.xiu.entity.User;
import com.vick.xiu.service.IUserService;
import com.vick.xiu.web.request.UserListRequest;
import com.vick.xiu.web.request.UserRequest;
import com.vick.xiu.web.response.UserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
            @ApiImplicitParam(name = "request", required = true, dataType = "UserRequest")
    })
    @PostMapping(value = "update")
    public ResultModel update(@RequestBody UserRequest request) {
        return iUserService.update(request);
    }

    @ApiOperation(value = "新增用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, dataType = "UserRequest")
    })
    @PostMapping(value = "add")
    public ResultModel add(@RequestBody UserRequest request) {
        if (!StringUtils.hasLength(request.getName())) {
            return ResultUtil.failure("姓名不能为空");
        }
        User user = new User();
        BeanUtils.copyProperties(request, user);
        boolean saveResult = iUserService.save(user);
        return saveResult ? ResultUtil.success() : ResultUtil.failure("新增用户失败");
    }

    @ApiOperation(value = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, dataType = "UserRequest")
    })
    @PostMapping(value = "delete")
    public ResultModel delete(@RequestBody UserRequest request) {
        if (null == request.getId()) {
            return ResultUtil.failure("ID不能为空");
        }
        boolean removeResult = iUserService.removeById(request.getId());
        return removeResult ? ResultUtil.success() : ResultUtil.failure("删除失败");
    }
}
