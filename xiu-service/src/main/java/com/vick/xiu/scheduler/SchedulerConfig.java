package com.vick.xiu.scheduler;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail testJobDetail() {
        return JobBuilder.newJob(TestJob.class)
                .withIdentity(TestJob.class.getSimpleName(), Scheduler.DEFAULT_GROUP)
                .storeDurably(true)
                .build();
    }

    @Bean
    public Trigger testJobTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/10 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(testJobDetail())
                .withIdentity(TestJob.class.getSimpleName(), Scheduler.DEFAULT_GROUP)
                .withSchedule(scheduleBuilder)
                .build();
    }

    /*@Bean
    public Trigger testJobSimpleTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.repeatSecondlyForever(10);
        return TriggerBuilder.newTrigger()
                .forJob(testJobDetail())
                .withIdentity(TestJob.class.getSimpleName(), Scheduler.DEFAULT_GROUP)
                .withSchedule(scheduleBuilder)
                .build();
    }*/

}