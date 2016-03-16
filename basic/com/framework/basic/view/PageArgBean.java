package com.framework.basic.view;

/**
 * 
 * 分页参数实体Bean
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
public class PageArgBean 
{
	//分页信息:开始
	private int pageBegin = 0;
	
	//分页信息：页数
	private int pageSize = 5;
	
	//分页信息：当前页
	private int currentPage = 1;

	//分页信息：当前行
	private int beginRow = 0;
	
	public int getBeginRow() {
		
		return beginRow;
	}

	public void setBeginRow(int beginRow) {
		this.beginRow = beginRow;
	}

	public int getPageBegin() {
		return pageBegin;
	}

	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
}
