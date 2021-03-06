package com.vick.xiu.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.vick.framework.page.PageRequest;
import com.vick.framework.result.ResultModel;
import com.vick.framework.result.ResultUtil;
import com.vick.xiu.service.IExamService;
import com.vick.xiu.service.IScoreService;
import com.vick.xiu.web.request.ExamRequest;
import com.vick.xiu.web.response.ExamResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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
    @Resource
    private IScoreService iScoreService;

    @ApiOperation(value = "增加测试")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, dataType = "ExamRequest")
    })
    @PostMapping("add")
    public ResultModel add(@Valid @RequestBody ExamRequest request, BindingResult bindingResult) {
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long")
    })
    @PostMapping("delete")
    public ResultModel delete(@RequestBody Long id) {
        QueryWrapper query = Wrappers.query();
        query.eq("exam_id", id);
        int count = iScoreService.count(query);
        if (count > 0) {
            return ResultUtil.failure("测试已被使用,不能删除");
        }
        boolean removeResult = iExamService.removeById(id);
        if (removeResult) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure("没有发现要删除的数据");
        }
    }

}
