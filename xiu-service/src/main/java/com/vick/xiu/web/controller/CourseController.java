package com.vick.xiu.web.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vick.framework.page.PageRequest;
import com.vick.framework.result.ResultModel;
import com.vick.framework.result.ResultUtil;
import com.vick.xiu.entity.Course;
import com.vick.xiu.service.ICourseService;
import com.vick.xiu.web.request.CourseRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zyz
 * @since 2019-11-10
 */
@Api(tags = "WEB端 - 课程信息")
@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    private ICourseService iCourseService;

    @GetMapping(value = "list")
    @ApiOperation(value = "课程列表", notes = "课程列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", readOnly = true, dataType = "PageRequest")
    })
    public ResultModel<IPage<Course>> list(PageRequest request) {
        return iCourseService.list(request);
    }

    @PostMapping(value = "add")
    @ApiOperation(value = "添加课程", notes = "添加课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, dataType = "CourseRequest")
    })
    public ResultModel add(@RequestBody CourseRequest request) {
        return iCourseService.add(request);
    }

    @PostMapping(value = "delete")
    @ApiOperation(value = "删除课程", notes = "删除课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, dataType = "Map<String,Integer>")
    })
    /**
     * @RequestBody不能接收单个的原始类型对象(除String)，
     * 但可以以String类型的变量接收整个Json字符串，
     * 也可以以Map对象接收json对象(Spring底层就是用Map处理的),
     * 也可以把原始数据类型放入JavaBean里接收
     */
    public ResultModel delete(@RequestBody String id) {
        boolean removeResult = iCourseService.removeById(Integer.valueOf(id));
        if (removeResult) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure("删除课程失败");
        }
    }
}
