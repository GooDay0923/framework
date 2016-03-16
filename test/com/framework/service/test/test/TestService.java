package com.framework.service.test.test;

import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.framework.persistent.test.domain.TestBean;
import com.framework.service.test.ITestService;

/**
 * 
 * 单元测试TestService
 * 
 * @Project FrameWork
 * 
 * @Version 1.0.0
 * 
 * @JDK version used 6.0
 * 
 * @Modification history none
 */
public class TestService extends TestCase
{

	/**
	 * 单元测试获取所有TestBean
	 * 
	 */
	public void testFindAllTestBean()
	{
		//初始化spring容器
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		
		//从spring容器中获取业务处理Bean:TestService
		ITestService testService = (ITestService)ctx.getBean("TestService");
		
		//调用获取业务处理TestService方法：findAll，获取所有Bean.
		List<TestBean> list = testService.findAll();
		for(TestBean testBean: list)
		{
			System.out.println(testBean.getName());
		}
	}
	
	/**
	 * 单元测试获取所有TestBean
	 * 
	 */
	public static void main(String[] args) throws Exception
	{
		//初始化spring容器
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		
		//从spring容器中获取业务处理Bean:TestService
		ITestService testService = (ITestService)ctx.getBean("TestService");
		
		//调用获取业务处理TestService方法：findAll，获取所有Bean.
		List<TestBean> list = testService.findAll();
		for(TestBean testBean: list)
		{
			System.out.println(testBean.getName());
		}
		
	}

}
