package com.Quartz.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Map;

public class HelloJob implements Job {
    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("我执行一次了");
        System.out.println("接下来我将演示如何从上下文拿到数据");
        JobDataMap dataMap  = jobExecutionContext.getJobDetail().getJobDataMap();
        Map<String,Object> map = dataMap.getWrappedMap();
        map.forEach((key,value)->{
            System.out.println("key是:"+key);
            System.out.println("value是:"+value);
        });
        System.out.println("上面取值的过程是老古董了，接下来我将从属性中取值");
        System.out.println("name as :"+name);
        System.out.println("value as"+value);
    }
}
