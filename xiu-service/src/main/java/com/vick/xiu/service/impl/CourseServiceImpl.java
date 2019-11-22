package com.vick.xiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.vick.framework.page.Page;
import com.vick.framework.page.PageRequest;
import com.vick.framework.result.ResultModel;
import com.vick.framework.result.ResultUtil;
import com.vick.xiu.entity.Course;
import com.vick.xiu.entity.User;
import com.vick.xiu.mapper.CourseMapper;
import com.vick.xiu.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vick.xiu.web.request.CourseRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyz
 * @since 2019-11-10
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Resource
    private CourseMapper courseMapper;

    @Override
    public ResultModel<IPage<Course>> list(PageRequest request) {
        Page page = new Page(request.getCurrentPage(), request.getPageSize());
        QueryWrapper<Course> query = Wrappers.query();
        IPage<Course> iPage = courseMapper.selectPage(page, query);
        for (Course course : iPage.getRecords()) {
            course.setLongTest(2222222222222222222L);
        }
        return ResultUtil.success(iPage);
    }

    @Override
    public ResultModel add(CourseRequest request) {
        Course course = new Course();
        course.setName(request.getName());
        int insertResult = courseMapper.insert(course);
        if (insertResult == 0) {
            return ResultUtil.failure("course add failure");
        }
        return ResultUtil.success();
    }
}
