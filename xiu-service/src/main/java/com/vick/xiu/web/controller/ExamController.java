package com.vick.xiu.web.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vick.framework.page.PageRequest;
import com.vick.framework.result.ResultModel;
import com.vick.framework.result.ResultUtil;
import com.vick.xiu.service.IExamService;
import com.vick.xiu.web.request.ExamRequest;
import com.vick.xiu.web.response.ExamResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zyz
 * @since 2019-11-26
 */
@Api(tags = "WEB端 - 测试管理")
@RestController
@RequestMapping("/exam")
public class ExamController {

    @Resource
    private IExamService iExamService;

    @ApiOperation(value = "增加测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, dataType = "ExamRequest")
    })
    @PostMapping("add")
    public ResultModel add(@RequestBody ExamRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.validFailure(bindingResult);
        }
        return iExamService.add(request);
    }

    @ApiOperation(value = "测试列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", readOnly = true, dataType = "PageRequest")
    })
    @GetMapping("list")
    public ResultModel<IPage<ExamResponse>> list(PageRequest request) {
        return iExamService.list(request);
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
