package com.vick.xiu.web.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vick.framework.page.Page;
import com.vick.framework.page.PageRequest;
import com.vick.framework.result.ResultModel;
import com.vick.framework.result.ResultUtil;
import com.vick.framework.util.ConverterUtils;
import com.vick.xiu.entity.Grade;
import com.vick.xiu.service.IGradeService;
import com.vick.xiu.web.request.GradeRequest;
import com.vick.xiu.web.response.GradeResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zyz
 * @since 2019-11-10
 */
@Api(tags = "WEB端 - 年级信息")

@RestController
@RequestMapping("/grade")
public class GradeController {

    @Resource
    private IGradeService iGradeService;

    @ApiOperation(value = "年级列表", notes = "年级列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, dataType = "PageRequest")
    })
    @GetMapping(value = "list")
    public ResultModel<IPage<GradeResponse>> list(PageRequest request) {
        IPage iPage = iGradeService.page(new Page(request.getCurrentPage(), request.getPageSize()));
        if (CollectionUtils.isEmpty(iPage.getRecords())) {
            return ResultUtil.success();
        }
        List list = ConverterUtils.convert(iPage.getRecords(), GradeResponse.class);
        iPage.setRecords(list);
        return ResultUtil.success(iPage);
    }

    @ApiOperation(value = "更新年级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, dataType = "GradeRequest")
    })
    @PostMapping(value = "update")
    public ResultModel update(@RequestBody GradeRequest request) {
        Grade grade = new Grade();
        BeanUtils.copyProperties(request, grade);
        boolean updateResult = iGradeService.updateById(grade);
        return updateResult ? ResultUtil.success() : ResultUtil.failure("数据没有更新成功");
    }

    @ApiOperation(value = "增加年级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, dataType = "GradeRequest")
    })
    @PostMapping(value = "add")
    public ResultModel add(@Valid @RequestBody GradeRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.validFailure(bindingResult);
        }
        Grade grade = new Grade();
        BeanUtils.copyProperties(request, grade);
        boolean saveResult = iGradeService.save(grade);
        return saveResult ? ResultUtil.success() : ResultUtil.failure("添加年级失败");
    }

    @ApiOperation(value = "删除年级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "map", required = true, dataType = "Map<String, Long>")
    })
    @PostMapping(value = "delete")
    public ResultModel delete(@RequestBody Map<String, Long> map) {
        if (null == map.get("id")) {
            return ResultUtil.failure("ID不能为空");
        }
        boolean removeResult = iGradeService.removeById(map.get("id"));
        return removeResult ? ResultUtil.success() : ResultUtil.failure("删除年级失败");
    }
}
