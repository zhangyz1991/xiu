package com.vick.xiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vick.framework.page.Page;
import com.vick.framework.result.ResultModel;
import com.vick.framework.result.ResultUtil;
import com.vick.framework.util.ConverterUtils;
import com.vick.xiu.entity.*;
import com.vick.xiu.mapper.ExamCourseMapper;
import com.vick.xiu.mapper.ExamMapper;
import com.vick.xiu.mapper.ScoreMapper;
import com.vick.xiu.service.IScoreDetailService;
import com.vick.xiu.service.IScoreService;
import com.vick.xiu.service.IUserService;
import com.vick.xiu.web.request.*;
import com.vick.xiu.web.response.ScoreDetailResponse;
import com.vick.xiu.web.response.ScoreResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zyz
 * @since 2019-11-26
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {

    @Resource
    private ScoreMapper scoreMapper;
    @Resource
    private IScoreDetailService iScoreDetailService;
    @Resource
    private ExamMapper examMapper;
    @Resource
    private ExamCourseMapper examCourseMapper;
    @Resource
    private IUserService iUserService;

    @Override
    public ResultModel<IPage<ScoreResponse>> list(ScoreListRequest request) {
        Long examId = request.getExamId();
        //成绩列表
        Page page = new Page(request.getCurrentPage(), request.getPageSize());
        QueryWrapper<Score> scoreQuery = Wrappers.query();
        scoreQuery.eq("exam_id", examId);
        scoreQuery.orderByAsc("id");
        IPage iPage = scoreMapper.selectPage(page, scoreQuery);
        List<Score> scoreList = iPage.getRecords();
        if (CollectionUtils.isEmpty(scoreList)) {
            return ResultUtil.success();
        }
        //课程列表
        List<Course> courseListByExam = examCourseMapper.courseListByExam(examId);
        Map<Long, Course> courseIdCourseMap = new HashMap<>();
        courseListByExam.forEach(course -> courseIdCourseMap.put(course.getId(), course));
        //成绩明细列表
        List<Long> scoreIdList = new ArrayList<>();
        scoreList.forEach(score -> scoreIdList.add(score.getId()));
        QueryWrapper<ScoreDetail> scoreDetailQuery = Wrappers.query();
        scoreDetailQuery.in("score_id", scoreIdList);
        scoreDetailQuery.orderByAsc("score_id")
                .orderByAsc("course_id");
        List<ScoreDetail> scoreDetailList = iScoreDetailService.list(scoreDetailQuery);
        //组织响应信息
        List<ScoreResponse> scoreResList = ConverterUtils.convert(scoreList, ScoreResponse.class);

        Map<Long, List<ScoreDetailResponse>> scoreIdScoreDetailResListMap = new HashMap<>();
        List<ScoreDetailResponse> scoreDetailResList;
        ScoreDetailResponse scoreDetailRes;
        for (ScoreDetail scoreDetail : scoreDetailList) {
            scoreDetailRes = new ScoreDetailResponse();
            scoreDetailRes.setScoreId(scoreDetail.getScoreId());
            scoreDetailRes.setCourseId(scoreDetail.getCourseId());
            scoreDetailRes.setScore(scoreDetail.getScore());
            scoreDetailRes.setCourseCode(courseIdCourseMap.get(scoreDetail.getCourseId()).getCode());
            scoreDetailRes.setCourseName(courseIdCourseMap.get(scoreDetail.getCourseId()).getName());

            if (!scoreIdScoreDetailResListMap.containsKey(scoreDetail.getScoreId())) {
                scoreDetailResList = new ArrayList<>();
                scoreIdScoreDetailResListMap.put(scoreDetail.getScoreId(), scoreDetailResList);
                scoreDetailResList.add(scoreDetailRes);
            } else {
                scoreIdScoreDetailResListMap.get(scoreDetail.getScoreId()).add(scoreDetailRes);
            }
        }
        for (ScoreResponse scoreResponse : scoreResList) {
            if (scoreIdScoreDetailResListMap.containsKey(scoreResponse.getId())) {
                scoreResponse.setScoreDetailList(scoreIdScoreDetailResListMap.get(scoreResponse.getId()));
            }
        }

        iPage.setRecords(scoreResList);
        return ResultUtil.success(iPage);
    }

    @Deprecated
    @Transactional
    @Override
    public ResultModel add(ScoreAddRequest request) {
        Score score = new Score();
        BeanUtils.copyProperties(request, score);
        scoreMapper.insert(score);
        Long scoreId = score.getId();
        ScoreDetail scoreDetail;
        if (!CollectionUtils.isEmpty(request.getScoreDetailList())) {
            List<ScoreDetail> scoreDetailList = new ArrayList<>(request.getScoreDetailList().size());
            for (ScoreDetailAddReq scoreDetailAddReq : request.getScoreDetailList()) {
                scoreDetail = new ScoreDetail();
                scoreDetail.setScoreId(scoreId);
                scoreDetail.setCourseId(scoreDetailAddReq.getCourseId());
                scoreDetail.setScore(scoreDetailAddReq.getScore());

                scoreDetailList.add(scoreDetail);
            }
            iScoreDetailService.saveBatch(scoreDetailList);
        }
        return ResultUtil.success();
    }

    @Transactional
    @Override
    public ResultModel update(ScoreUpdateRequest request) {
        Score scoreUpdate = new Score();
        BeanUtils.copyProperties(request, scoreUpdate);
        int updateResult = scoreMapper.updateById(scoreUpdate);
        if (updateResult == 0) {
            return ResultUtil.failure("没有要更新的数据");
        }

        List<ScoreDetailUpdateReq> scoreDetailUpdateReqList = request.getScoreDetailList();
        UpdateWrapper<ScoreDetail> scoreDetailUpdateWrapper;
        for (ScoreDetailUpdateReq scoreDetailUpdateReq : scoreDetailUpdateReqList) {
            scoreDetailUpdateWrapper = Wrappers.update();
            scoreDetailUpdateWrapper.set("score", scoreDetailUpdateReq.getScore());
            scoreDetailUpdateWrapper.eq("score_id",scoreDetailUpdateReq.getScoreId());
            scoreDetailUpdateWrapper.eq("course_id",scoreDetailUpdateReq.getCourseId());
            iScoreDetailService.update(scoreDetailUpdateWrapper);
        }
        return ResultUtil.success();
    }

    @Transactional
    @Override
    public ResultModel prepareTranscripts(Long examId) {
        Exam exam = examMapper.selectById(examId);
        if (exam == null) {
            return ResultUtil.failure("测试不存在");
        }
        List<User> userList = iUserService.list();
        if (CollectionUtils.isEmpty(userList)) {
            return ResultUtil.failure("不存在用户");
        }
        QueryWrapper<ExamCourse> examCourseQuery = Wrappers.query();
        examCourseQuery.eq("exam_id", examId);
        examCourseQuery.orderByAsc("sequence_number");
        List<ExamCourse> examCourseList = examCourseMapper.selectList(examCourseQuery);
        if (CollectionUtils.isEmpty(examCourseList)) {
            return ResultUtil.failure("测试科目为空");
        }
        QueryWrapper<Score> scoreQuery = Wrappers.query();
        scoreQuery.eq("exam_id", examId);
        Integer count = scoreMapper.selectCount(scoreQuery);
        if (count > 0) {
            return ResultUtil.failure("该测试已经存在成绩单");
        }

        Score score;
        List<Score> scoreList = new ArrayList<>(userList.size());
        for (User user : userList) {
            score = new Score();
            score.setExamId(examId);
            score.setUserId(user.getId());
            score.setUserName(user.getName());
            scoreList.add(score);
        }
        this.saveBatch(scoreList);
        //保存分数明细
        ScoreDetail scoreDetail;
        List<ScoreDetail> scoreDetailList = new ArrayList<>(scoreList.size() * examCourseList.size());
        for (int i = 0; i < scoreList.size(); i++) {
            score = scoreList.get(i);
            for (ExamCourse examCourse : examCourseList) {
                scoreDetail = new ScoreDetail();
                scoreDetail.setScoreId(score.getId());
                scoreDetail.setCourseId(examCourse.getCourseId());

                scoreDetailList.add(scoreDetail);
            }
        }
        iScoreDetailService.saveBatch(scoreDetailList);
        return ResultUtil.success();
    }
}
