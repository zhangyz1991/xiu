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
    public ResultModel add(@Valid @RequestBody CourseRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.validFailure(bindingResult);
        }
        return iCourseService.add(request);
    }

    @PostMapping(value = "delete")
    @ApiOperation(value = "删除课程", notes = "删除课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long")
    })
    /**
     * @RequestBody不能接收json格式的(如{"a":v})单个的原始类型对象(除String)，但
     * 1-1 可以以String类型的变量接收整个Json字符串-然后就可以用这个字符串转化为Json对象继续处理，
     * 1-2 也可以以Map对象接收json对象(Spring底层就是用Map处理的),
     * 1-3 也可以把原始数据类型放入JavaBean里接收
     * 2   也可以直接传输值,比如要传输{"id":2},可直接在body中输出 2
     */
    public ResultModel delete(@RequestBody Long id) {
        boolean removeResult = iCourseService.removeById(id);
        if (removeResult) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure("没有发现要删除的数据");
        }
    }
}
