package com.vick.xiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vick.framework.page.Page;
import com.vick.framework.page.PageRequest;
import com.vick.framework.result.ResultModel;
import com.vick.framework.result.ResultUtil;
import com.vick.xiu.entity.Course;
import com.vick.xiu.entity.ExamCourse;
import com.vick.xiu.mapper.CourseMapper;
import com.vick.xiu.mapper.ExamCourseMapper;
import com.vick.xiu.service.ICourseService;
import com.vick.xiu.web.request.CourseRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zyz
 * @since 2019-11-10
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Resource
    private CourseMapper courseMapper;
    @Resource
    private ExamCourseMapper examCourseMapper;

    @Override
    public ResultModel<IPage<Course>> list(PageRequest request) {
        Page page = new Page(request.getCurrentPage(), request.getPageSize());
        QueryWrapper<Course> query = Wrappers.query();
        query.orderByAsc("id");
        IPage iPage = courseMapper.selectPage(page, query);
        return ResultUtil.success(iPage);
    }

    @Transactional
    @Override
    public ResultModel add(CourseRequest request) {
        QueryWrapper<Course> query = Wrappers.query();
        query.eq("code", request.getCode())
                .or().eq("name", request.getName());
        Integer count = courseMapper.selectCount(query);
        if (count > 0) {
            return ResultUtil.failure("课程已存在");
        }
        Course course = new Course();
        BeanUtils.copyProperties(request, course);
        int insertResult = courseMapper.insert(course);
        if (insertResult == 0) {
            return ResultUtil.failure("course add failure");
        }
        return ResultUtil.success();
    }

    @Override
    public ResultModel<Course> courseListByExam(Long examId) {
        List<Course> list = examCourseMapper.courseListByExam(examId);
        return ResultUtil.success(list);
    }
}
