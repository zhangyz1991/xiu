package com.vick.xiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vick.framework.result.ResultModel;
import com.vick.xiu.entity.Score;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vick.xiu.web.request.ScoreAddRequest;
import com.vick.xiu.web.request.ScoreListRequest;
import com.vick.xiu.web.request.ScoreUpdateRequest;
import com.vick.xiu.web.response.ScoreResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyz
 * @since 2019-11-26
 */
public interface IScoreService extends IService<Score> {

    ResultModel<IPage<ScoreResponse>> list(ScoreListRequest request);

    ResultModel add(ScoreAddRequest request);

    ResultModel update(ScoreUpdateRequest request);
}
