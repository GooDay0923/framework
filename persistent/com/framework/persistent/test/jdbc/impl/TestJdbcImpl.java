package com.framework.persistent.test.jdbc.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.framework.persistent.test.domain.TestBean;
import com.framework.persistent.test.jdbc.ITestJdbc;

/**
 * 
 * Test关系型数据接口实现
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
@Repository(value="TestJdbc")
public class TestJdbcImpl implements ITestJdbc
{
	//myIbatis数据接口
	@Resource(name = "sqlSessionTemplate") 
	private SqlSessionTemplate sqlSessionTemplate;
	
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
		sqlSessionTemplate.insert("saveTest", testBean); 
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
		sqlSessionTemplate.update("updateTest", testBean);
	}

	/**
	 * 根据ID删除TestBean
	 * 
	 * @param id
	 * 
	 * @return void
	 * 
	 */
	public void delete(int id)
	{
		sqlSessionTemplate.delete("deleteTest", id);
	}

	/**
	 * 根据ID查找TestBean
	 * 
	 * @param id 
	 * 
	 * @return TestBean
	 * 
	 */
	public TestBean findById(int id)
	{
		return (TestBean) sqlSessionTemplate.selectOne("findTestById", id);   
	}
	
	/**
	 * 根据name查找TestBean
	 * 
	 * @param name 
	 * 
	 * @return TestBean
	 * 
	 */
	public TestBean findByName(String name)
	{
		return (TestBean) sqlSessionTemplate.selectOne("findTestByName", name);   
	}
	
	
	/**
	 * 查找所有TestBean
	 * 
	 * @return List<TestBean>
	 * 
	 */
	public List<TestBean> findAll()
	{
		 return sqlSessionTemplate.selectList("findTestAll");   
	}
	
	/**
	 *  查找所有TestBean
	 * 
	 * @return List<TestBean>
	 * 
	 */
	public List<Object> findTestAllForHashMap()
	{
		 return sqlSessionTemplate.selectList("findTestAllForHashMap");   
	}
}
