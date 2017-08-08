package com.Quartz.Demo;
import org.quartz.JobExecutionContext;  
import org.quartz.JobExecutionException;  
import org.springframework.scheduling.quartz.QuartzJobBean; 
/*
 * Quartz作业类的第一种定义方法：继承QuartzJobBean类
 */
public class ExtendsJob extends QuartzJobBean {  
	
	/**
	 * 可以从外部注入的属性
	 */
	public String msg;
	
	/** 
	* 要调度的具体任务 
	*/  
	@Override  
	protected void executeInternal(JobExecutionContext context)  
	throws JobExecutionException {  
	System.out.println("外部注入的属性:"+msg);
	  System.out.println("定时任务执行中…");  
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}  
}  