package com.vick.xiu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vick.framework.result.ResultModel;
import com.vick.xiu.entity.Score;
import com.vick.xiu.mapper.ScoreMapper;
import com.vick.xiu.service.IScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vick.xiu.web.request.ScoreAddRequest;
import com.vick.xiu.web.request.ScoreListRequest;
import com.vick.xiu.web.request.ScoreUpdateRequest;
import com.vick.xiu.web.response.ScoreResponse;
import org.springframework.stereotype.Service;

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

    @Override
    public ResultModel<IPage<ScoreResponse>> list(ScoreListRequest request) {
        return null;
    }

    @Override
    public ResultModel add(ScoreAddRequest request) {
        return null;
    }

    @Override
    public ResultModel update(ScoreUpdateRequest request) {
        return null;
    }
}
