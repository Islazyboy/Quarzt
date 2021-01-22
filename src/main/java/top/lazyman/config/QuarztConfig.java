package top.lazyman.config;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import top.lazyman.quarzt.MyFirstJob;

import java.util.Date;
import java.util.Objects;

/**
* @Author: lazyBoy
* @Date: 2021/1/22 17:23
*/
@Configuration
public class QuarztConfig {
    /*
    * 定时任务的工作
    * */
    @Bean
    JobDetailFactoryBean jobDetailFactoryBean(){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("name","lazyboy");
        jobDetailFactoryBean.setJobDataMap(jobDataMap);
        jobDetailFactoryBean.setJobClass((Class<? extends Job>) MyFirstJob.class);
        return jobDetailFactoryBean;
    }
    /*
    * 简单的触发器
    * */
    @Bean
    SimpleTriggerFactoryBean simpleTriggerFactoryBean(){
        SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
        simpleTriggerFactoryBean.setJobDetail(Objects.requireNonNull(jobDetailFactoryBean().getObject()));
        simpleTriggerFactoryBean.setStartTime(new Date());
        simpleTriggerFactoryBean.setRepeatInterval(2000L);
        simpleTriggerFactoryBean.setRepeatCount(3);
        return simpleTriggerFactoryBean;
    }
    /*
    * Cron定时器
    * */
    @Bean
    CronTriggerFactoryBean cronTriggerFactoryBean(){
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(Objects.requireNonNull(jobDetailFactoryBean().getObject()));
        cronTriggerFactoryBean.setCronExpression("* * * * * ?");
        return cronTriggerFactoryBean;
    }

    /*
    * 启动定时任务
    * */
    @Bean
    SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTriggers(simpleTriggerFactoryBean().getObject(), cronTriggerFactoryBean().getObject());
        return bean;
    }
}
