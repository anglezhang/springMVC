package com.cyw.mammoth.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cyw.common.web.DynamicDataSource;

public class ApplicationListener implements ServletContextListener {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	private ServletContext context = null;

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		this.context = servletContextEvent.getServletContext();
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		//设置main datasource
        DynamicDataSource.setCurrentLookupKey(DynamicDataSource.dataSourceMainKey);
        logger.debug("------------初始化设置DataSource="+DynamicDataSource.getCurrentLookupKey()+"------------------");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
