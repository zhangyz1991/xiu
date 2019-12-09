package com.vick.xiu.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.vick.framework.page.PageRequest;
import com.vick.framework.result.ResultModel;
import com.vick.framework.result.ResultUtil;
import com.vick.xiu.service.IClassService;
import com.vick.xiu.service.IClassUserService;
import com.vick.xiu.web.request.ClassAddRequest;
import com.vick.xiu.web.response.ClassResponse;
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
 *  前端控制器
 * </p>
 *
 * @author zyz
 * @since 2019-12-09
 */
@Api(tags = "WEB端 - 班级管理")
@RestController
@RequestMapping("/class")
public class ClassController {

    @Resource
    private IClassService iClassService;
    @Resource
    private IClassUserService iClassUserService;

    @ApiOperation(value = "增加班级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, dataType = "ClassAddRequest")
    })
    @PostMapping("add")
    public ResultModel add(@Valid @RequestBody ClassAddRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.validFailure(bindingResult);
        }
        return iClassService.add(request);
    }

    @ApiOperation(value = "班级列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", readOnly = true, dataType = "PageRequest")
    })
    @GetMapping("list")
    public ResultModel<IPage<ClassResponse>> list(PageRequest request) {
        return iClassService.list(request);
    }

    @PostMapping("delete")
    public ResultModel delete(@RequestBody Long id) {
        QueryWrapper query = Wrappers.query();
        query.eq("class_id", id);
        int count = iClassUserService.count(query);
        if (count > 0) {
            return ResultUtil.failure("班级已被使用,不能删除");
        }
        boolean removeResult = iClassService.removeById(id);
        if (removeResult) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure("没有发现要删除的数据");
        }
    }
}
