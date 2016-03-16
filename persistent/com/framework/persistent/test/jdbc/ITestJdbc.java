package com.framework.persistent.test.jdbc;

import java.util.List;

import com.framework.persistent.test.domain.TestBean;

/**
 * 
 * Test关系型数据接口
 * @Project FrameWork
 * 
 * @Version 1.0.0
 * 
 * @JDK version used 6.0
 * 
 * @Modification history none
 * 
 */
public interface ITestJdbc
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
	 * 根据ID删除TestBean
	 * 
	 * @param id
	 * 
	 * @return void
	 * 
	 */
     void delete(int id);

	/**
	 * 根据ID查找TestBean
	 * 
	 * @param id 
	 * 
	 * @return TestBean
	 * 
	 */
	TestBean findById(int id);
	
	/**
	 * 根据name查找TestBean
	 * 
	 * @param name 
	 * 
	 * @return TestBean
	 * 
	 */
	TestBean findByName(String name);
	
	/**
	 * 查找所有TestBean
	 * 
	 * @return List<TestBean>
	 * 
	 */
	 List<TestBean> findAll();
	 
	/**
	 *  查找所有TestBean
	 * 
	 * @return List<TestBean>
	 * 
	 */
	List<Object> findTestAllForHashMap();
}
