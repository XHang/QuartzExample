package com.Quartz.Demo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

/*
 * 
 */
public class TestExtendsJob  {  
	public static void main(String[] args) {
		//一旦加载了Spring的容器，作业调度就启动
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring.xml");
	}
	
}  