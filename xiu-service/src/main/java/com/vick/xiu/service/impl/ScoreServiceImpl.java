package com.vick.xiu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vick.framework.result.ResultModel;
import com.vick.framework.result.ResultUtil;
import com.vick.xiu.entity.Score;
import com.vick.xiu.entity.ScoreDetail;
import com.vick.xiu.mapper.ScoreMapper;
import com.vick.xiu.service.IScoreDetailService;
import com.vick.xiu.service.IScoreService;
import com.vick.xiu.web.request.ScoreAddRequest;
import com.vick.xiu.web.request.ScoreDetailAdd;
import com.vick.xiu.web.request.ScoreListRequest;
import com.vick.xiu.web.request.ScoreUpdateRequest;
import com.vick.xiu.web.response.ScoreResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {

    @Resource
    private ScoreMapper scoreMapper;
    @Resource
    private IScoreDetailService iScoreDetailService;

    @Override
    public ResultModel<IPage<ScoreResponse>> list(ScoreListRequest request) {
        return null;
    }

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
            for (ScoreDetailAdd scoreDetailAdd : request.getScoreDetailList()) {
                scoreDetail = new ScoreDetail();
                scoreDetail.setScoreId(scoreId);
                scoreDetail.setCourseId(scoreDetailAdd.getCourseId());
                scoreDetail.setScore(scoreDetailAdd.getScore());

                scoreDetailList.add(scoreDetail);
            }
            iScoreDetailService.saveBatch(scoreDetailList);
        }
        return ResultUtil.success();
    }

    @Override
    public ResultModel update(ScoreUpdateRequest request) {
        return null;
    }
}
