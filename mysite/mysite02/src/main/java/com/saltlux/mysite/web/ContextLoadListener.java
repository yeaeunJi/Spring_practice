package com.saltlux.mysite.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ContextLoadListener implements ServletContextListener {

	
    public ContextLoadListener() {
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    }

    // web application이 로딩될 때 실행됨
    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
    	ServletContext context = servletContextEvent.getServletContext();
    	String contextConfigLocation = context.getInitParameter("contextConfigLocation");
    	System.out.println("web application start.... "+contextConfigLocation);
    }
	
}
