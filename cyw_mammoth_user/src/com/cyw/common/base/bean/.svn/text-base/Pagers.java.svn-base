/*
 * 
 */
package com.cyw.common.base.bean;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Pagers.
 *
 * @author litiangang(litiangang@cyw.so)
 * @version 1.0
 * @Comments:  对基础分页类的补充，提供手动封装分页的功能，
 * @date 2014-10-23 下午09:55:32
 */
public class Pagers extends Pager implements java.io.Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5251565694917279631L;

	/**
	 * Instantiates a new pagers.
	 */
	public Pagers(){
	}

	/**
	 * 构造器.
	 *
	 * @param pageNumber            页码
	 * @param pageSize            每页几条数据
	 * @param totalCount            总共几条数据
	 */
	public Pagers(int pageNumber, int pageSize, int totalCount) {
		super(pageNumber, pageSize, totalCount);
	}

	/**
	 * 构造器.
	 *
	 * @param pageNo the page no
	 * @param pageSize            每页几条数据
	 * @param totalCount            总共几条数据
	 * @param list            分页内容
	 */
	public Pagers(int pageNo, int pageSize, int totalCount, List<?> list) {
		super(pageNo, pageSize, totalCount);
		super.setList(list);
	}
}
