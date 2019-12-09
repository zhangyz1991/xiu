package com.vick.xiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vick.framework.page.Page;
import com.vick.framework.page.PageRequest;
import com.vick.framework.result.ResultModel;
import com.vick.framework.result.ResultUtil;
import com.vick.framework.util.ConverterUtils;
import com.vick.xiu.entity.Class;
import com.vick.xiu.entity.Grade;
import com.vick.xiu.mapper.ClassMapper;
import com.vick.xiu.mapper.GradeMapper;
import com.vick.xiu.service.IClassService;
import com.vick.xiu.web.request.ClassAddRequest;
import com.vick.xiu.web.response.ClassResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyz
 * @since 2019-12-09
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements IClassService {

    @Resource
    private ClassMapper classMapper;
    @Resource
    private GradeMapper gradeMapper;

    @Override
    public ResultModel add(ClassAddRequest request) {
        Class classAdd = new Class();
        BeanUtils.copyProperties(request, classAdd);
        Grade grade = gradeMapper.selectById(request.getGradeId());
        classAdd.setGradeName(grade.getName());
        classMapper.insert(classAdd);
        return ResultUtil.success();
    }

    @Override
    public ResultModel<IPage<ClassResponse>> list(PageRequest request) {
        Page page = new Page(request.getCurrentPage(), request.getPageSize());
        QueryWrapper<Class> query = Wrappers.query();
        query.orderByAsc("id");
        IPage iPage = classMapper.selectPage(page, query);
        if (CollectionUtils.isEmpty(iPage.getRecords())) {
            return ResultUtil.success();
        }
        List list = ConverterUtils.convert(iPage.getRecords(), ClassResponse.class);
        iPage.setRecords(list);
        return ResultUtil.success(iPage);
    }
}
