package com.Quartz.Demo;

import com.Quartz.job.HelloJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class Quartz {
    public static void main(String [] args){
        try {
            //从工厂中获取调度示例
            StdSchedulerFactory fact = new StdSchedulerFactory();
            //从工厂内部弄一个调度器scheduler，通过读取quartz.properties可得
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //开启调度器，如果有可用的job，则开启后就可以执行了
            scheduler.start();
            doSomething(scheduler);
            //调用以下语句，将结束所有调度程序
            //为了测试调度程序的循环执行，暂时先注释掉
            //scheduler.shutdown();
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    /**
     * 做一些有趣的事
     * 实现的功能是在指定的时间点，触发一个工作，执行一次后over
     */
    private static void doSomething(Scheduler scheduler) throws SchedulerException {
        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                //传输数据给job类
                .usingJobData("这是一段数据","你敢信？")
                .usingJobData("name","你的名字是？")
                .usingJobData("value","你大爷")
                .build();
        String tinme = "2018-09-11 16:59:30";
        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                //现在开始 ，now
               /* .startNow()*/
                .startAt(strToDate(tinme))
                //简单的调度
                .withSchedule(simpleSchedule()
                //每隔三秒执行一次,但是我只要在某某时间执行一遍，就行，所以不考虑哈
              /*  .withIntervalInSeconds(1)
                        .repeatForever()*/)
                .build();
        System.out.println("将于"+tinme+"时间执行");
        scheduler.scheduleJob(job, trigger);
    }

    private static  Date strToDate(String formatStr){
        try {
            String timePattern =  "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timePattern);
            return simpleDateFormat.parse(formatStr);
        } catch (ParseException e) {
            throw new RuntimeException("啊哦，你传的日期格式符不正确哦",e);
        }
    }
}
