package com.framework.service.test.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.framework.persistent.test.cache.ITestCache;
import com.framework.persistent.test.domain.TestBean;
import com.framework.persistent.test.jdbc.ITestJdbc;
import com.framework.service.test.ITestService;

/**
 * 
 * Test业务处理类
 * 
 * @Project FrameWork
 * 
 * @Version 1.0.0
 * 
 * @JDK version used 6.0
 * 
 * @Modification history none
 * 
 */
@Service(value="TestService")
public class TestServiceImpl implements ITestService
{
	//关系型数据库接口
	@Resource(name = "TestJdbc") 
	private ITestJdbc testJdbc = null;
	
	//缓存接口
	@Resource(name = "TestCacheImpl") 
	private ITestCache testCache = null;
	
	/**
	 * 保存TestBean
	 * 
	 * @param testBean TestBean
	 * 
	 * @return void
	 * 
	 */
	public void save(TestBean testBean)
	{
		testJdbc.save(testBean); 
	}

	/**
	 * 更新TestBean
	 * 
	 * @param testBean TestBean
	 * 
	 * @return void
	 * 
	 */
	public void update(TestBean testBean)
	{
		testJdbc.update(testBean);
	}

	/**
	 * 删除TestBean
	 * 
	 * @param id TestBean
	 * 
	 * @return void
	 * 
	 */
	public void delete(int id)
	{
		testJdbc.delete(id);
	}

	/**
	 * 根据ID查找TestBean
	 * 
	 * @param id TestBean
	 * 
	 * @return TestBean
	 * 
	 */
	public TestBean findById(int id)
	{
		return testJdbc.findById(id);   
	}
	
	/**
	 * 根据name查找TestBean
	 * 
	 * @param id TestBean
	 * 
	 * @return TestBean
	 * 
	 */
	public TestBean findByNameForCache(String name)
	{
		//先去缓存查找
		TestBean testBean = testCache.findByName(name);
		
		//在缓存找不到，在DB查找
		if(testBean == null)
		{
			//在DB中寻找 
			testBean = testJdbc.findByName(name);
			
			//在DB中找到，放入缓存
			if(testBean != null)
			{
				//缓存到redis中，超时时间为100秒!
				testCache.saveByName(testBean);
			}
            return testBean;
		}
		else
		{
			return testBean;
		}
	}
	
	/**
	 * 查询所有TestBean
	 * 
	 * @return List<TestBean>
	 * 
	 */
	public List<TestBean> findAll()
	{
		 return testJdbc.findAll();   
	}
	
	/**
	 * 查询所有TestBean
	 * 
	 * @return List<TestBean> TestBean
	 * 
	 */
	public List<Object> findTestAllForHashMap()
	{
		return testJdbc.findTestAllForHashMap();  
	}
}
