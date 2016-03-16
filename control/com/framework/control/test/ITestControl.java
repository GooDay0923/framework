package com.framework.control.test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.framework.common.Tool;
import com.framework.persistent.test.domain.TestBean;

/**
 * 
 * 测试TestBean控制层接口
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
public interface ITestControl
{
	/**
	 * 保存Test实体Bean
	 * 
	 * @param testBean Test实体Bean
	 * 
	 * @return ModelAndView
	 * 
	 */
	ModelAndView save(HttpServletRequest request,HttpServletResponse response) throws ServletException;
	
	/**
	 * 查找增加Test页面
	 * 
	 * @return ModelAndView
	 * 
	 */ 
	ModelAndView findSaveVM(HttpServletRequest request,HttpServletResponse response) throws ServletException;
	
	/**
	 * 保存Test实体Bean
	 * 
	 * @param testBean Test实体Bean
	 * 
	 * @return void
	 * 
	 */
	ModelAndView update(HttpServletRequest request,HttpServletResponse response) throws ServletException;

	/**
	 * 查找更新Test页面
	 * 
	 * @param testBean Test实体Bean
	 * 
	 * @return void
	 * 
	 */
	ModelAndView findUpdateVM(HttpServletRequest request,HttpServletResponse response) throws ServletException;
	
	/**
	 * 删除Test实体Bean
	 * 
	 * @param id Test实体Bean主键
	 * 
	 * @return void
	 * 
	 */
	ModelAndView delete(HttpServletRequest request,HttpServletResponse response) throws ServletException;

	/**
	 * 查找Test实体Bean
	 * 
	 * @param id Test实体Bean主键
	 * 
	 * @return Test实体Bean
	 * 
	 */
	ModelAndView findById(HttpServletRequest request,HttpServletResponse response) throws ServletException;
	
	/**
	 * 根据name查找Test实体Bean
	 * 
	 * @param id Test实体Bean主键
	 * 
	 * @return Test实体Bean
	 * 
	 */
	ModelAndView findByName(HttpServletRequest request,HttpServletResponse response) throws ServletException;
	
	/**
	 * 获取所在Test实体Bean
	 * 
	 * @return List<TestBean> Test实体Bean集合
	 * 
	 */
	ModelAndView findAll(HttpServletRequest request,HttpServletResponse response) throws ServletException;
	
	/**
	 * 获取所在Test实体Bean(hashMap)
	 * 
	 * @return List<TestBean> Test实体Bean集合
	 * 
	 */
	ModelAndView findTestAllForHashMap(HttpServletRequest request,HttpServletResponse response) throws ServletException;
	
	/**
	 * velocity import指令测试
	 * 
	 */
	ModelAndView importDirective(HttpServletRequest request,HttpServletResponse response) throws ServletException;
	
	/**
	 * 测试输出json字符串
	 * 
	 */
	ModelAndView testJson(HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	/**
	 * 测试输出json字符串
	 * 
	 */
	void testJson2(HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	/**
	 * 测试输出json字符串
	 * 
	 */
	String testJson3(HttpServletRequest request,HttpServletResponse response) throws Exception;
}
