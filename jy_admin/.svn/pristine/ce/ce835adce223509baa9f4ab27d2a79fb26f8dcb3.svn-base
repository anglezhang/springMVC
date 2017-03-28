package com.zoomoor.common.web.session;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OnlineCounter extends HttpServlet implements HttpSessionListener {
	private static final Logger log = LoggerFactory.getLogger(OnlineCounter.class);
	private static final long serialVersionUID = 1L;  
	private static int sessionCounter=0;
	public OnlineCounter(){
		
	}
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		sessionCounter++;
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		sessionCounter--;
	}
	public static int getOnlineSession() {  
        return sessionCounter;  
    } 
}
