package com.vick.xiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vick.framework.page.Page;
import com.vick.framework.page.PageRequest;
import com.vick.framework.result.ResultModel;
import com.vick.framework.result.ResultUtil;
import com.vick.framework.util.ConverterUtils;
import com.vick.xiu.entity.Exam;
import com.vick.xiu.entity.ExamCourse;
import com.vick.xiu.mapper.ExamCourseMapper;
import com.vick.xiu.mapper.ExamMapper;
import com.vick.xiu.service.IExamService;
import com.vick.xiu.web.request.ExamCourseRequest;
import com.vick.xiu.web.request.ExamRequest;
import com.vick.xiu.web.response.ExamResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyz
 * @since 2019-11-26
 */
@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements IExamService {

    @Resource
    private ExamMapper examMapper;
    @Resource
    private ExamCourseMapper examCourseMapper;

    @Transactional
    @Override
    public ResultModel add(ExamRequest request) {
        Exam exam = new Exam();
        BeanUtils.copyProperties(request, exam);
        examMapper.insert(exam);
        Long examId = exam.getId();
        List<ExamCourseRequest> examCourseRequestList = request.getExamCourseList();
        ExamCourse examCourse;
        for (ExamCourseRequest examCourseRequest : examCourseRequestList) {
            examCourse = new ExamCourse();
            examCourse.setExamId(examId);
            examCourse.setCourseId(examCourseRequest.getCourseId());
            examCourse.setPointScale(examCourseRequest.getPointScale());

            examCourseMapper.insert(examCourse);
        }

        return ResultUtil.success();
    }

    @Override
    public ResultModel<IPage<ExamResponse>> list(PageRequest request) {
        Page page = new Page(request.getCurrentPage(), request.getPageSize());
        QueryWrapper<Exam> query = Wrappers.query();
        query.orderByAsc("id");
        IPage iPage = examMapper.selectPage(page, query);
        if (CollectionUtils.isEmpty(iPage.getRecords())) {
            return ResultUtil.success();
        }
        List list = ConverterUtils.convert(iPage.getRecords(), ExamResponse.class);
        iPage.setRecords(list);
        return ResultUtil.success(iPage);
    }
}
