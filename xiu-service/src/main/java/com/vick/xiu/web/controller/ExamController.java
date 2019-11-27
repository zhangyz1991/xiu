package com.vick.xiu.web.controller;


import com.vick.framework.result.ResultModel;
import com.vick.xiu.service.IExamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zyz
 * @since 2019-11-26
 */
@Api(tags = "WEB端 - 测试信息")
@RestController
@RequestMapping("/exam")
public class ExamController {

    @Resource
    private IExamService iExamService;

    @ApiOperation(value = "增加测试")
    @ApiImplicitParams({})
    @PostMapping("add")
    public ResultModel add() {
        return null;
    }

    @ApiOperation(value = "测试列表")
    @ApiImplicitParams({})
    @GetMapping("list")
    public ResultModel list() {
        return null;
    }

    @ApiOperation(value = "更新测试")
    @ApiImplicitParams({})
    @PostMapping("update")
    public ResultModel update() {
        return null;
    }

    @ApiOperation(value = "删除测试")
    @ApiImplicitParams({})
    @PostMapping("delete")
    public ResultModel delete() {
        return null;
    }

}
