package com.cyw.mammoth.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cyw.mammoth.auth.AppBaseCfg;
import com.cyw.mammoth.bean.Operator;

public class BaseAction {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@SuppressWarnings("unused")
	@Autowired
	private  HttpServletRequest request;
	@SuppressWarnings("unused")
	@Autowired
	private  HttpSession session;
	
	/**
	 * 获取session用户
	 * @return
	 */
	public Operator getSessionUser(){
		return AppBaseCfg.getOperator() ;
	}

}
