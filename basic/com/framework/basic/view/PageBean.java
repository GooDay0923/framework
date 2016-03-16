package com.framework.basic.view;

/**
 * 
 * 分页基础信息实体Bean
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
public class PageBean 
{
	//总记录算
    private int allCounts = 0;
    
    //总页数
    private int allPages = 0;
    
    //一页记录数
    private int pageSize = 0;
    
    //当前页
    private int currentPage = 0;

	public int getAllCounts() {
		return allCounts;
	}

	public void setAllCounts(int allCounts) {
		this.allCounts = allCounts;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getAllPages() {
		return allPages;
	}

	public void setAllPages(int allPages) {
		this.allPages = allPages;
	}
}
