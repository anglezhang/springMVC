package com.zoomoor.common.base.bean;

import java.util.List;

/**
 * @Comments:  共用组件类-基础分页
 * @author zudapeng(zudapeng@zoomoor.com)
 * @date 2014-10-23 下午09:55:32
 * @version 1.0
 */
public class Pager {

	// 排序方式
	public enum OrderType{
		asc, desc
	}

	public static final Integer MAX_PAGE_SIZE = 500;// 每页最大记录数限制

	private Integer pageNum = 1;		// 当前页码
	private Integer numPerPage = 50; 	// 设置分页展示数据条数
	private Integer totalCount = 0; 	// 总记录数
	private Integer pageCount = 0; 		// 总页数
	private String property;			// 查找属性名称
	private String keyword;				// 查找关键字
	private String orderBy = "id";		// 排序字段
	private OrderType orderType = OrderType.asc;// 排序方式
	private List list;					// 数据List

	/**
	 * 构造器
	 */
	public Pager(){

	}
	/**
	 * 构造器
	 *
	 * @param pageNum
	 *            页码
	 * @param numPerPage
	 *            每页几条数据
	 * @param totalCount
	 *            总共几条数据
	 */
	public Pager(int pageNum, int numPerPage, int totalCount) {
		setTotalCount(totalCount);
		setNumPerPage(numPerPage);
		setPageNum(pageNum);
		adjustPageNo();
	}

	/**
	 * 调整页码，使不超过最大页数
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
	 * 检查页码 checkPageNo
	 * 
	 * @param pageNo
	 * @return if pageNo==null or pageNo<1 then return 1 else return pageNo
	 */
	public static int cpn(Integer pageNum) {
		return (pageNum == null || pageNum < 1) ? 1 : pageNum;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageNum < 1) {
			pageNum = 1;
		}
		this.pageNum = pageNum;
	}

	public Integer getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(Integer numPerPage) {
		if (numPerPage < 1) {
			numPerPage = 1;
		} else if(numPerPage > MAX_PAGE_SIZE) {
			numPerPage = MAX_PAGE_SIZE;
		}
		this.numPerPage = numPerPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageCount() {
		pageCount = totalCount / numPerPage;
		if (totalCount % numPerPage > 0) {
			pageCount ++;
		}
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	/**
	 * 第一条数据位置
	 *
	 * @return
	 */
	public int getFirstResult() {
		return (pageNum - 1) * numPerPage;
	}

}