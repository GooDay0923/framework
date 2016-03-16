package com.framework.basic.listener;

import java.io.File;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;
import org.logicalcobwebs.proxool.configuration.ServletConfigurator;

/**
 * 
 * 初始化proxool数据库连接池
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
public class ProxoolInitialListener implements ServletContextListener
{
	private static final Log LOG = LogFactory.getLog(ProxoolInitialListener.class);
	
	public void contextDestroyed(ServletContextEvent event) 
	{
		
	}

	/**
	 * 
	 * 初始化proxool数据库连接池
	 * 
	 * @param event 
	 * 
	 * @return ModelAndView
	 */
	public void contextInitialized(ServletContextEvent event) 
	{
		LOG.info("ProxoolInitialListener init ...");
		String appDir = event.getServletContext().getRealPath("/");
		String value = event.getServletContext().getInitParameter("proxoolXmlFile");
		try 
		{
            File file = new File(value);
            if (file.isAbsolute())
            {
                JAXPConfigurator.configure(value, false);
            } 
            else 
            {
                JAXPConfigurator.configure(appDir + File.separator + value, false);
            }
        } 
		catch (ProxoolException e)
		{
            LOG.error("Problem configuring " + value, e);
        }
	}
}
