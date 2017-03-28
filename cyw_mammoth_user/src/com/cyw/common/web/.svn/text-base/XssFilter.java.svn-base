package com.cyw.common.web;

/**
 * @author Tom
 */
import java.io.IOException;

import javax.servlet.Filter;

import javax.servlet.FilterChain;

import javax.servlet.FilterConfig;

import javax.servlet.ServletException;

import javax.servlet.ServletRequest;

import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class XssFilter.
 */
public class XssFilter implements Filter {
	
	/** The filter char. */
	private String filterChar;
	
	/** The replace char. */
	private String replaceChar;
	
	/** The split char. */
	private String splitChar;
	
	/** The filter config. */
	FilterConfig filterConfig = null;
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterChar=filterConfig.getInitParameter("FilterChar");
		this.replaceChar=filterConfig.getInitParameter("ReplaceChar");
		this.splitChar=filterConfig.getInitParameter("SplitChar");
		this.filterConfig = filterConfig;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		this.filterConfig = null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,

	FilterChain chain) throws IOException, ServletException {
		chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request,filterChar,replaceChar,splitChar), response);
	}

}
