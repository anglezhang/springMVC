/*
 * 
 */
package com.cyw.common.base.bean;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Pager.
 *
 * @author litiangang(litiangang@cyw.so)
 * @version 1.0
 * @Comments:  共用组件类-基础分页
 * @date 2014-10-23 下午09:55:32
 */
public class Pager {

	// 排序方式
	/**
	 * The Enum OrderType.
	 */
	public enum OrderType{
		
		/** The asc. */
		asc, 
 /** The desc. */
 desc
	}

	/** The Constant MAX_PAGE_SIZE. */
	public static final Integer MAX_PAGE_SIZE = 500;// 每页最大记录数限制

	/** The page num. */
	private Integer pageNum = 1;		// 当前页码
	
	/** The num per page. */
	private Integer numPerPage = 5; 	// 设置分页展示数据条数
	
	/** The total count. */
	private Integer totalCount = 0; 	// 总记录数
	
	/** The page count. */
	private Integer pageCount = 0; 		// 总页数
	
	/** The property. */
	private String property;			// 查找属性名称
	
	/** The keyword. */
	private String keyword;				// 查找关键字
	
	/** The order by. */
	private String orderBy = "id";		// 排序字段
	
	/** The order type. */
	private OrderType orderType = OrderType.asc;// 排序方式
	
	@SuppressWarnings({  "rawtypes" })   
	/** The list. */
	private List list;					// 数据List

	/**
	 * 构造器.
	 */
	public Pager(){

	}
	
	/**
	 * 构造器.
	 *
	 * @param pageNum            页码
	 * @param numPerPage            每页几条数据
	 * @param totalCount            总共几条数据
	 */
	public Pager(int pageNum, int numPerPage, int totalCount) {
		setTotalCount(totalCount);
		setNumPerPage(numPerPage);
		setPageCount(getPageCount());
		setPageNum(pageNum);
		adjustPageNo();
	}

	/**
	 * 调整页码，使不超过最大页数.
	 */
	public void adjustPageNo() {
		if (pageNum == 1) {
			return;
		}
		int tp = getPageCount();
		if (pageNum > tp) {
			pageNum = tp;
		}
	}

	/**
	 * Gets the page num.
	 *
	 * @return the page num
	 */
	public Integer getPageNum() {
		return pageNum;
	}

	/**
	 * Sets the page num.
	 *
	 * @param pageNum the new page num
	 */
	public void setPageNum(Integer pageNum) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageNum < 1) {
			pageNum = 1;
		}
		this.pageNum = pageNum;
	}

	/**
	 * Gets the num per page.
	 *
	 * @return the num per page
	 */
	public Integer getNumPerPage() {
		return numPerPage;
	}

	/**
	 * Sets the num per page.
	 *
	 * @param numPerPage the new num per page
	 */
	public void setNumPerPage(Integer numPerPage) {
		if (numPerPage < 1) {
			numPerPage = 1;
		} else if(numPerPage > MAX_PAGE_SIZE) {
			numPerPage = MAX_PAGE_SIZE;
		}
		this.numPerPage = numPerPage;
	}

	/**
	 * Gets the total count.
	 *
	 * @return the total count
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

	/**
	 * Sets the total count.
	 *
	 * @param totalCount the new total count
	 */
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * Gets the page count.
	 *
	 * @return the page count
	 */
	public Integer getPageCount() {
		pageCount = totalCount / numPerPage;
		if (totalCount % numPerPage > 0) {
			pageCount ++;
		}
		return pageCount;
	}

	/**
	 * Sets the page count.
	 *
	 * @param pageCount the new page count
	 */
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * Gets the property.
	 *
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * Sets the property.
	 *
	 * @param property the new property
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * Gets the keyword.
	 *
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * Sets the keyword.
	 *
	 * @param keyword the new keyword
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * Gets the order by.
	 *
	 * @return the order by
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * Sets the order by.
	 *
	 * @param orderBy the new order by
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * Gets the order type.
	 *
	 * @return the order type
	 */
	public OrderType getOrderType() {
		return orderType;
	}

	/**
	 * Sets the order type.
	 *
	 * @param orderType the new order type
	 */
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	@SuppressWarnings({ "rawtypes" })   
	public List getList() {
		return list;
	}

	/**
	 * Sets the list.
	 *
	 * @param list the new list
	 */
	@SuppressWarnings({  "rawtypes" })   
	public void setList(List list) {
		this.list = list;
	}

	/**
	 * 第一条数据位置.
	 *
	 * @return the first result
	 */
	public int getFirstResult() {
		return (pageNum - 1) * numPerPage;
	}

}