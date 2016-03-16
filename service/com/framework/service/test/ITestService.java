package com.framework.service.test;

import java.util.List;

import com.framework.persistent.test.domain.TestBean;

/**
 * 
 * Test业务处理类接口
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
public interface ITestService 
{
	/**
	 * 保存TestBean
	 * 
	 * @param testBean TestBean
	 * 
	 * @return void
	 * 
	 */
	void save(TestBean testBean);

	/**
	 * 更新TestBean
	 * 
	 * @param testBean TestBean
	 * 
	 * @return void
	 * 
	 */
	void update(TestBean testBean);

	/**
	 * TestBean
	 * 
	 * @param id 
	 * 
	 * @return void
	 * 
	 */
	void delete(int id);

	/**
	 *根据ID查找TestBean
	 * 
	 * @param id 
	 * 
	 * @return TestBean
	 * 
	 * @time 2012-10-10
	 */
	TestBean findById(int id);
	
	/**
	 * 根据name查找TestBean
	 * 
	 * @param id TestBean
	 * 
	 * @return TestBean
	 * 
	 */
	TestBean findByNameForCache(String name);
	
	/**
	 * 查询所有TestBean
	 * 
	 * @return List<TestBean>
	 * 
	 */
	List<TestBean> findAll();
	
	/**
	 * 查询所有TestBean
	 * 
	 * @return List<TestBean>
	 * 
	 */
	List<Object> findTestAllForHashMap();
}
