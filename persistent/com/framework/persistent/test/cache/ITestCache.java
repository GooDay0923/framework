package com.framework.persistent.test.cache;

import com.framework.persistent.test.domain.TestBean;

/**
 * 
 * Test缓存数据接口
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
public interface ITestCache 
{
	/**
	 * 根据名称保存TestBean
	 * 
	 * @param testBean TestBean
	 * 
	 * @return void
	 * 
	 */
	void saveByName(TestBean testBean);
    
	/**
	 * 根据名称查找TestBean
	 * 
	 * @param id 
	 * 
	 * @return TestBean
	 * 
	 */
	TestBean findByName(String name);
    
}
