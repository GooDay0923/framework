package com.framework.basic.velocity;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;

/**
 * 
 * 自定义import标签：实现动态include功能
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
public class ImportDirective extends Directive
{

	private Log logger = LogFactory.getLog(this.getClass().getName());
	
	/**
	 * 指定指令的名称
	 */
	public String getName() {
		
		return "import";
	}

	/**
	 * 指定指令类型：LINE,只在一行里面
	 */
	public int getType() {
		
		return LINE;
	}

	/**
	 * 执行import指令操作
	 * 
	 * 指令格式: #import("请求路径",request,response,"参数键值对1","参数键值对2","参数键值对n")
	 *          例如：#import("/test/view.do",$request,$response,"name=apple","age=12","xxx=xxx")
	 *          
	 * 参数说明：第一个参数为请求路径，例如：/test/view.do
	 *          第二个参数为请求对象，例如：HttpServletRequest
	 *          第三个参数为响应对象，例如：HttpServletResponse
	 *          第四个参数为传入参数：例如：name=apple
	 *          第五个参数为传入参数：例如： age=12
	 *          第n个参数为传入参数：  例如： xxx=xxx
	 *          传入参数会保存到请求(HttpServletRequest)的请求属性中.
	 */
	public boolean render(InternalContextAdapter contextAdapter, Writer writer, Node node)
			throws IOException, ResourceNotFoundException, ParseErrorException,
			MethodInvocationException 
	{
		//请求路径
		String requestPath = "";
		
		//request对象
		HttpServletRequest request = null;
		
		//response对象
		HttpServletResponse response = null;
		
		//传入参数
		Map<String,String> requestMap = new HashMap<String,String>();
		
		//获取参数
		int argsNum = node.jjtGetNumChildren();
		for(int step = 0 ; step < argsNum; step++)
		{
			SimpleNode simpleNode = (SimpleNode) node.jjtGetChild(step);
			if(step == 0)
			{
				//路径
				requestPath = (String) simpleNode.value(contextAdapter);
			}
			else
			{
				Object argObject = simpleNode.value(contextAdapter);

				//传入参数
				if(argObject instanceof String)
				{
					String arg = (String) argObject;
					String[] argArray = arg.split("=");
					if(argArray.length == 2)
					{
						requestMap.put(argArray[0].trim(), argArray[1].trim());
					}
				}
				//request对象
				else if(argObject instanceof HttpServletRequest)
				{
					request = (HttpServletRequest)argObject;
				}
				//response对象
				else if(argObject instanceof HttpServletResponse)
				{
					response = (HttpServletResponse)argObject;
				}
				
			}
		}
		 
		//把传入参数放到request
		if(!requestMap.isEmpty())
		{
			Iterator<String> requestArg = requestMap.keySet().iterator();
			while(requestArg.hasNext())
			{
				String key = requestArg.next();
				request.setAttribute(key, requestMap.get(key));
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(requestPath);
		try 
		{
			//动态包含
			dispatcher.include((ServletRequest)request, (ServletResponse)response);
		}
		catch (ServletException e)
		{
			logger.error("执行velociey import 指令内容操作异常!", e);
		}
		
		//刷新buffer输出
		response.flushBuffer();
		return true;
	}

}
