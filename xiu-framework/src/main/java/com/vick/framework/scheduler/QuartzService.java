package com.vick.framework.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zyz
 * @since 2019-12-31
 */
@Slf4j
@Service
public class QuartzService {

    @Autowired
    private Scheduler scheduler;

    private static final Integer defaultRepeatCount = -1;

    /**
     * 作者：zyz 日期：2020/1/9 描述：spring.quartz.auto-startup的配置与该方法作用相同
     */
    public void startScheduler() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个job
     *
     * @param jobClass          任务实现类
     * @param jobName           任务名称
     * @param intervalInSeconds 时间表达式 (这是每隔多少秒为一次任务)
     * @param jobData           参数
     */
    public void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, int intervalInSeconds, Map jobData) {
        this.addJob(jobClass, jobName, intervalInSeconds, -1, jobData);
    }

    /**
     * 增加一个job
     *
     * @param jobClass          任务实现类
     * @param jobName           任务名称
     * @param intervalInSeconds 时间表达式 (这是每隔多少秒为一次任务)
     * @param repeatCount       运行的次数 （<0:表示不限次数）
     * @param jobData           参数
     */
    public void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, int intervalInSeconds,
                       int repeatCount, Map jobData) {
        this.addJob(jobClass, jobName, Scheduler.DEFAULT_GROUP, intervalInSeconds, repeatCount, jobData);
    }

    /**
     * 增加一个job
     *
     * @param jobClass          任务实现类
     * @param jobName           任务名称
     * @param jobGroupName      任务组名
     * @param intervalInSeconds 时间表达式 (这是每隔多少秒为一次任务)
     * @param repeatCount       运行的次数 （<0:表示不限次数）
     * @param jobData           参数
     */
    public void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName,
                       int intervalInSeconds, int repeatCount, Map jobData) {
        // 使用simpleTrigger规则
        if (intervalInSeconds <= 0) {
            intervalInSeconds = 1;
        }
        Trigger trigger = null;
        if (repeatCount < 0) {
            trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(intervalInSeconds))
                    .startNow().build();
        } else {
            trigger = TriggerBuilder
                    .newTrigger().withIdentity(jobName, jobGroupName).withSchedule(SimpleScheduleBuilder
                            .simpleSchedule().withIntervalInSeconds(intervalInSeconds).withRepeatCount(repeatCount))
                    .startNow().build();
        }
        this.addJob(jobClass, jobName, jobGroupName, trigger, jobData);
    }

    /**
     * 增加一个job
     *
     * @param jobClass       任务实现类
     * @param jobName        任务名称
     * @param cronExpression 时间表达式 （如：0/5 * * * * ? ）
     * @param jobData        参数
     */
    public void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String cronExpression, Map jobData) {
        // 使用simpleTrigger规则
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, Scheduler.DEFAULT_GROUP)
                .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).startNow().build();
        this.addJob(jobClass, jobName, Scheduler.DEFAULT_GROUP, trigger, jobData);
    }

    /**
     * 增加一个job
     *
     * @param jobClass       任务实现类
     * @param jobName        任务名称
     * @param jobGroupName   任务组名
     * @param cronExpression 时间表达式 （如：0/5 * * * * ? ）
     * @param jobData        参数
     */
    public void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName, String cronExpression, Map jobData) {
        // 使用simpleTrigger规则
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).startNow().build();
        this.addJob(jobClass, jobName, jobGroupName, trigger, jobData);
    }

    /**
     * 增加一个job
     *
     * @param jobClass     任务实现类
     * @param jobName      任务名称
     * @param jobGroupName 任务组名
     * @param trigger      Trigger表达式
     * @param jobData      参数
     */
    public void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName, Trigger trigger, Map jobData) {
        try {
            // 创建jobDetail实例: 指明job的名称，所在组的名称，以及绑定job类绑定Job实现类
            // 任务名称和组构成任务key
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
            // 设置job参数
            if (jobData != null && jobData.size() > 0) {
                jobDetail.getJobDataMap().putAll(jobData);
            }
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改 一个job的 时间表达式
     *
     * @param jobName
     * @param jobGroupName
     * @param cronExpression 时间表达式 （如：0/5 * * * * ? ）
     */
    public void updateJob(String jobName, String jobGroupName, String cronExpression) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
            // 重启触发器
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除任务一个job
     *
     * @param jobName      任务名称
     * @param jobGroupName 任务组名
     */
    public void deleteJob(String jobName, String jobGroupName) {
        try {
            scheduler.deleteJob(new JobKey(jobName, jobGroupName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停一个job
     *
     * @param jobName
     * @param jobGroupName
     */
    public void pauseJob(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 恢复一个job
     *
     * @param jobName
     * @param jobGroupName
     */
    public void resumeJob(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 立即执行一个job
     *
     * @param jobName
     * @param jobGroupName
     */
    public void runJobNow(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有计划中的任务列表
     *
     * @return
     */
    public List<Map<String, Object>> queryAllJob() {
        List<Map<String, Object>> jobList = null;
        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            jobList = new ArrayList<>();
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("jobName", jobKey.getName());
                    map.put("jobGroupName", jobKey.getGroup());
                    map.put("description", "触发器:" + trigger.getKey());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    map.put("jobStatus", triggerState.name());
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        map.put("cronExpression", cronExpression);
                    }
                    jobList.add(map);
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobList;
    }

    /**
     * 获取所有正在运行的job
     *
     * @return
     */
    public List<Map<String, Object>> queryRunJob() {
        List<Map<String, Object>> jobList = null;
        try {
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            jobList = new ArrayList<>(executingJobs.size());
            for (JobExecutionContext executingJob : executingJobs) {
                Map<String, Object> map = new HashMap<>();
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();
                Trigger trigger = executingJob.getTrigger();
                map.put("jobName", jobKey.getName());
                map.put("jobGroupName", jobKey.getGroup());
                map.put("description", "触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                map.put("jobStatus", triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    map.put("cronExpression", cronExpression);
                }
                jobList.add(map);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobList;
    }
}
