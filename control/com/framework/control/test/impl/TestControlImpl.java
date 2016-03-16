package com.framework.control.test.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.framework.common.Tool;
import com.framework.control.test.ITestControl;
import com.framework.persistent.test.domain.TestBean;
import com.framework.service.test.ITestService;

/**
 * 
 * 测试TestBean控制层实现
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
@Controller  
@RequestMapping("/test")
public class TestControlImpl extends MultiActionController implements ITestControl
{
    //格式化时间
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	//服务层接口
	@Resource(name = "TestService")
	private ITestService testService = null;
	
    //模板 列表TEST页面路径
	private String TEST_LIST_VM = "listTest";

    //模板 增加TEST页面路径
	private String TEST_ADD_VM = "addTest";
	
    //模板 更新TEST页面路径
	private String TEST_UPDATE_VM = "updateTest";
	
    //模板 import指令页面路径
	private String TEST_IMPORT_VM = "importDirective";
	
    //模板 json页面路径
	private String JSON_VM = "json";
	
	private Log logger = LogFactory.getLog(this.getClass().getName());
	
	/**
	 * 保存Test实体Bean
	 * 
	 * @param testBean Test实体Bean
	 * 
	 * @return ModelAndView
	 * 
	 */
	@RequestMapping(value="/save.do", method=RequestMethod.POST)  
	public ModelAndView save(HttpServletRequest request,HttpServletResponse response) throws ServletException
	{
		logger.info("保存Test实体Bean");
		
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String birthday = request.getParameter("birthday");
		TestBean testBean = new TestBean();
		testBean.setName(name);
		testBean.setPhone(phone);
		try 
		{
			testBean.setBirthday(DATE_FORMAT.parse(birthday));
		}
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		testService.save(testBean);
		return this.findAll(request, response);
	}

	/**
	 * 查找增加Test页面
	 * 
	 * @return ModelAndView
	 * 
	 */
	@RequestMapping(value="/save.do", method=RequestMethod.GET)  
	public ModelAndView findSaveVM(HttpServletRequest request,HttpServletResponse response) throws ServletException
	{
		logger.info("查找增加Test页面");
		return new ModelAndView(TEST_ADD_VM);
	}
	
	/**
	 * 保存Test实体Bean
	 * 
	 * @param testBean Test实体Bean
	 * 
	 * @return void
	 * 
	 */
	@RequestMapping(value="/update.do", method=RequestMethod.POST) 
	public ModelAndView update(HttpServletRequest request,HttpServletResponse response) throws ServletException
	{
		logger.info("保存Test实体Bean");
		
		int id = Integer.parseInt(request.getParameter("id"));
		String phone = request.getParameter("phone");
		String birthday = request.getParameter("birthday");
		TestBean testBean = new TestBean();
		testBean.setId(id);
		testBean.setPhone(phone);
		try 
		{
			testBean.setBirthday(DATE_FORMAT.parse(birthday));
		}
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		testService.update(testBean);
		return this.findAll(request, response);
	}

	/**
	 * 查找更新Test页面
	 * 
	 * @param testBean Test实体Bean
	 * 
	 * @return void
	 * 
	 */
	@RequestMapping(value="/update.do", method=RequestMethod.GET) 
	public ModelAndView findUpdateVM(HttpServletRequest request,HttpServletResponse response) throws ServletException
	{
		logger.info("查找更新Test页面");
		int id = Integer.parseInt(request.getParameter("id"));
		TestBean testBean = testService.findById(id);
		return new ModelAndView(TEST_UPDATE_VM, "testBean",testBean);
	}
	
	/**
	 * 删除Test实体Bean
	 * 
	 * @param id Test实体Bean主键
	 * 
	 * @return void
	 * 
	 */
	@RequestMapping(value="/delete.do", method=RequestMethod.GET) 
	public ModelAndView delete(HttpServletRequest request,HttpServletResponse response) throws ServletException
	{
		logger.info("删除Test实体Bean");
		int id = Integer.parseInt(request.getParameter("id"));
		testService.delete(id);
		return this.findAll(request, response);
	}

	/**
	 * 查找Test实体Bean
	 * 
	 * @param id Test实体Bean主键
	 * 
	 * @return Test实体Bean
	 * 
	 */
	@RequestMapping(value="/findById.do", method=RequestMethod.POST) 
	public ModelAndView findById(HttpServletRequest request,HttpServletResponse response) throws ServletException
	{
		logger.info("查找Test实体Bean");
		int id = Integer.parseInt(request.getParameter("id"));
		TestBean testBean = testService.findById(id);
		List<TestBean> testList = new ArrayList<TestBean>();
		if(testBean != null)
		{
			testList.add(testBean);
		}
		return new ModelAndView(TEST_LIST_VM, "testList",testList);
	}
	
	/**
	 * 根据name查找Test实体Bean
	 * 
	 * @param name 名称
	 * 
	 * @return Test实体Bean
	 * 
	 */
	@RequestMapping(value="/findByName.do", method=RequestMethod.POST) 
	public ModelAndView findByName(HttpServletRequest request,HttpServletResponse response) throws ServletException
	{
		logger.info("根据name查找Test实体Bean");
		String name  = request.getParameter("name");
		TestBean testBean = testService.findByNameForCache(name);
		List<TestBean> testList = new ArrayList<TestBean>();
		if(testBean != null)
		{
			testList.add(testBean);
		}
		return new ModelAndView(TEST_LIST_VM, "testList",testList);
	}
	
	
	/**
	 * 获取所在Test实体Bean
	 * 
	 * @return List<TestBean> Test实体Bean集合
	 * 
	 */
	@RequestMapping(value="/findAll.do") 
	public ModelAndView findAll(HttpServletRequest request,HttpServletResponse response) throws ServletException
	{
		logger.info("获取所在Test实体Bean");
		List<TestBean> testList = testService.findAll();
		ModelAndView modelAndView = new ModelAndView(TEST_LIST_VM, "testList",testList);
		modelAndView.addObject("request", request);
		modelAndView.addObject("response", response);
		return modelAndView;
	}
	
	/**
	 * 获取所在Test实体Bean(hashMap)
	 * 
	 * @return List<TestBean> Test实体Bean集合
	 * 
	 */
	@RequestMapping(value="/findTestAllForHashMap.do") 
	public ModelAndView findTestAllForHashMap(HttpServletRequest request,HttpServletResponse response) throws ServletException
	{
		logger.info(" 获取所在Test实体Bean(hashMap)");
		List<Object> testList = testService.findTestAllForHashMap();
		JSONArray myArray = Tool.listMap2jsonArray(testList);
		ModelAndView modelAndView = new ModelAndView(JSON_VM, "content",myArray.toString());
		return modelAndView;
	}
	
	/**
	 * velocity import指令测试
	 * 
	 */
	@RequestMapping(value="/import.do") 
	public ModelAndView importDirective(HttpServletRequest request,HttpServletResponse response) throws ServletException
	{
		logger.info("velocity import指令测试");
		//获取request属性.
		String name = request.getParameter("name");
		ModelAndView modelAndView = new ModelAndView(TEST_IMPORT_VM, "importTest","import指令测试成功! 请求参数：" + name);
		return modelAndView;
	}
	
	/**
	 * 测试输出json字符串
	 * 
	 */
	@RequestMapping(value="/testJson.do")
	public ModelAndView testJson(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		logger.info("测试输出json字符串");
		List<TestBean> testList = testService.findAll();
		JSONArray jsonArray = JSONArray.fromObject(testList);
		return new ModelAndView(JSON_VM, "content",jsonArray.toString());
	}
	
	/**
	 * 测试输出json字符串
	 * 
	 */
	@RequestMapping(value="/testJson2.do") 
	public void testJson2(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		logger.info("测试输出json2字符串");
		response.setContentType("text/html;charset=UTF-8");
		List<TestBean> testList = testService.findAll();
		JSONArray jsonArray = JSONArray.fromObject(testList); 
		response.getWriter().write(jsonArray.toString());
		response.flushBuffer();
	}
	
	/**
	 * 测试输出json字符串
	 * 
	 */
	@RequestMapping(value="/testJson3.do")
	@ResponseBody
	public String testJson3(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		logger.info("测试输出json3字符串");
		List<TestBean> testList = testService.findAll();
		JSONArray jsonArray = JSONArray.fromObject(testList);
		return jsonArray.toString();
	}
}
