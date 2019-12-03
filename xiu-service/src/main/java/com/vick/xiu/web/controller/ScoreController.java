package com.vick.xiu.web.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vick.framework.result.ResultModel;
import com.vick.framework.result.ResultUtil;
import com.vick.xiu.service.IScoreService;
import com.vick.xiu.web.request.ScoreAddRequest;
import com.vick.xiu.web.request.ScoreListRequest;
import com.vick.xiu.web.request.ScoreUpdateRequest;
import com.vick.xiu.web.response.ScoreResponse;
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
 * @since 2019-12-03
 */
@Api(tags = "WEB端 - 成绩管理")
@RestController
@RequestMapping("/score")
public class ScoreController {

    @Resource
    private IScoreService iScoreService;

    @ApiOperation(value = "成绩表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, dataType = "ScoreListRequest")
    })
    @GetMapping(value = "list")
    public ResultModel<IPage<ScoreResponse>> list(@ModelAttribute ScoreListRequest request) {
        return iScoreService.list(request);
    }

    @ApiOperation(value = "修改成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, dataType = "ScoreUpdateRequest")
    })
    @PostMapping(value = "update")
    public ResultModel update(@RequestBody ScoreUpdateRequest request) {
        return iScoreService.update(request);
    }

    @ApiOperation(value = "录入成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request", required = true, dataType = "UserRequest")
    })
    @PostMapping(value = "add")
    public ResultModel add(@Valid @RequestBody ScoreAddRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ResultUtil.validFailure(bindingResult);
        }
        return iScoreService.add(request);
    }

    @ApiOperation(value = "删除成绩")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long")
    })
    @PostMapping(value = "delete")
    public ResultModel delete(@RequestBody Long id) {
        boolean removeResult = iScoreService.removeById(id);
        if (removeResult) {
            return ResultUtil.success();
        } else {
            return ResultUtil.failure("没有发现要删除的数据");
        }
    }
}
