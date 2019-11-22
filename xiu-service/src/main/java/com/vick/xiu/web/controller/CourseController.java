package com.vick.xiu.web.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vick.framework.page.PageRequest;
import com.vick.framework.result.ResultModel;
import com.vick.xiu.entity.Course;
import com.vick.xiu.service.ICourseService;
import com.vick.xiu.web.request.CourseRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "list")
    @ApiOperation(value = "课程列表", notes = "课程列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, dataType = "PageRequest")
    })
    public ResultModel<IPage<Course>> list(@RequestBody PageRequest request) {
        return iCourseService.list(request);
    }

    @PostMapping(value = "add")
    @ApiOperation(value = "添加课程", notes = "添加课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, dataType = "CourseRequest")
    })
    public ResultModel list(@RequestBody CourseRequest request) {
        return iCourseService.add(request);
    }
}
