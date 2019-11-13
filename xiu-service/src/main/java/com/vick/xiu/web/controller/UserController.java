package com.vick.xiu.web.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vick.framework.result.ResultModel;
import com.vick.xiu.entity.User;
import com.vick.xiu.service.IUserService;
import com.vick.xiu.web.request.UserListRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zyz
 * @since 2019-11-10
 */
@Slf4j
@Api(tags = "WEB端 - 用户信息")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 使用@GetMapping会有|{}"等问题，也可以前端对URL编码，还可以换用jetty服务器
     */
    @PostMapping(value = "list")
    @ApiOperation(value = "学生列表", notes = "学生列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, dataType = "UserListRequest")
    })
    public ResultModel<IPage<User>> list(@RequestBody UserListRequest request) {
        return iUserService.list(request);
    }
}
