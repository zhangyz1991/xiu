package com.vick.xiu.scheduler;

import com.vick.framework.scheduler.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author zyz
 * @since 2019-12-31
 */
@Slf4j
@Component
@DisallowConcurrentExecution
public class TestJob extends QuartzJobBean {

    @Autowired
    private QuartzService quartzService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        // 获取参数
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        // 业务逻辑 ...
        log.info(jobDetail.getKey() + "执行" + "###" + jobExecutionContext.getTrigger());
    }

}
