package com.arex.blog.utils;

import javax.servlet.http.HttpServletRequest;


public class PageInfo {

	private HttpServletRequest request;
	private int pageNo = 1;
	private int pageSize = 5;
	private int beginResult = 0;
	private int totalResult = 0;
	private int totalPage = 0;
	private PageBean page = null;

	public PageInfo() {
		this.pageNo = 1;
		this.pageSize = 5;
	}

	public PageInfo(int currentPage) {
		this.pageNo = currentPage;
		this.pageSize = 5;
	}

	public PageInfo(HttpServletRequest request) {
		this.pageNo = (request.getParameter("pageNo") != null) ? new Integer(
				request.getParameter("pageNo")).intValue() : 1;
		this.pageSize = (request.getParameter("pageSize") != null) ? new Integer(
				request.getParameter("pageSize")).intValue() : 5;
	}
	
	@Deprecated
	/**
	 * 测试时用于模拟request
	 * @param str1 模拟pageNo
	 * @param str2 模拟pageSize
	 */
	public PageInfo(String str1, String str2) {
		this.pageNo = (str1 != null) ? new Integer(
				str1).intValue() : 1;
		this.pageSize = (str2 != null) ? new Integer(
				str2).intValue() : 5;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public int getCurrentPage() {
		return pageNo;
	}

	public void setCurrentPage(int currentPage) {
		this.pageNo = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getBeginResult() {
		//pageNo  totalPage
		//首先判断是否是共一页，判断是否为最后一页，页打大小是动态的
		if (totalPage == 1) {
			//总共就一页，起始位置就为1,页面的大小为总共打记录
			this.beginResult = 0;
			this.pageSize = totalResult;
			pageNo = totalPage;
		} else {
			//不为1,判断是否为最后一页
			if (pageNo >= totalPage) {
				pageNo = totalPage;
				beginResult = (pageNo - 1) * pageSize;
				pageSize = totalResult - beginResult;
			} else if(pageNo < 1) {
				pageNo = 1;
				beginResult = 0;
			} else {
				beginResult = (pageNo - 1) * pageSize;
			}
		}
		setRequetValue();
		return beginResult;
	}

	private void setRequetValue() {
		this.page = new PageBean();
		page.setFirstPage(this.isFirstPage());
		page.setLastPage(this.isLastPage());
		page.setPageNo(this.pageNo);
		page.setPageSize(this.pageSize);
		page.setTotalPages(this.totalPage);
		page.setTotalResults(this.getTotalResult());
	}

	public void setBeginResult(int beginResult) {
		this.beginResult = beginResult;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
		countPages();
	}

	private void countPages() {
		//new出pageInfo时，指定了pageNo和pageSize
		//根据总共打记录数来计算总共页数 totalPage
		//totalResult / pageSize
		//如果总共的记录数小于1,则设置totalPage为1
		if (totalResult <= 0) {
			this.totalPage = 1;
		}
		
		if (totalResult%pageSize == 0) {
			this.totalPage = totalResult/pageSize;
		} else {
			this.totalPage = totalResult/pageSize+1;
		}
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public PageBean getPage() {
		return page;
	}

	public void setPage(PageBean page) {
		this.page = page;
	}

	public boolean isLastPage() {
		if (this.pageNo >= totalPage) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isFirstPage() {
		if (this.pageNo <= 1) {
			return true;
		} else {
			return false;
		}
	}
}
