package top.lazyman.quarzt;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import sun.util.calendar.LocalGregorianCalendar;

import java.util.Date;

/**
 * @Author: lazyBoy
 * @Date: 2021/1/22 17:20
 */
public class MyFirstJob extends QuartzJobBean {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Date date = new Date();
        System.out.println(name+"/"+date.toLocaleString());
    }
}
