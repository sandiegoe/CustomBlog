package com.arex.blog.utils;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

public class PageInfoTest {

	@Test
	public void testGetBeginResult() {
		
		//默认pageNo为1 pageSize为5
		PageInfo pageInfo = new PageInfo();
		pageInfo.setTotalResult(2);
		int beginResult = pageInfo.getBeginResult();
		int pageSize = pageInfo.getPageSize();
		PageBean pageBean = pageInfo.getPage();
		
		System.out.println("beginResult : " + beginResult + "   pageSize : " + pageSize);
	}
	
	@Test
	public void testGetBeginResult2() {
		
		//指定pageNo为2 pageSize为5
		PageInfo pageInfo = new PageInfo(2);
		pageInfo.setTotalResult(22);
		int beginResult = pageInfo.getBeginResult();
		int pageSize = pageInfo.getPageSize();
		PageBean pageBean = pageInfo.getPage();
		
		System.out.println("beginResult : " + beginResult + "   pageSize : " + pageSize);
	}
	
	@Test
	public void testGetBeginResult3() {
		
		//默认pageNo为1 pageSize为5
		PageInfo pageInfo = new PageInfo(3);
		pageInfo.setTotalResult(22);
		int beginResult = pageInfo.getBeginResult();
		int pageSize = pageInfo.getPageSize();
		PageBean pageBean = pageInfo.getPage();
		
		System.out.println("beginResult : " + beginResult + "   pageSize : " + pageSize);
	}
	
	@Test
	public void testGetBeginResult4() {
		
		//默认pageNo为1 pageSize为5
		PageInfo pageInfo = new PageInfo();
		pageInfo.setTotalResult(8);
		int beginResult = pageInfo.getBeginResult();
		int pageSize = pageInfo.getPageSize();
		PageBean pageBean = pageInfo.getPage();
		
		System.out.println("beginResult : " + beginResult + "   pageSize : " + pageSize);
	}
	
	@Test
	public void testGetBeginResult5() {
		
		//HttpServletRequest
//		HttpServletRequest request = new HttpServletRequest();
//		request.setAttribute("pageNo", "1");
//		request.setAttribute("pageSize", "8");
		PageInfo pageInfo = new PageInfo("1", "8");
		pageInfo.setTotalResult(22);
		int beginResult = pageInfo.getBeginResult();
		int pageSize = pageInfo.getPageSize();
		PageBean pageBean = pageInfo.getPage();
		
		System.out.println("beginResult : " + beginResult + "   pageSize : " + pageSize);
	}
	
	@Test
	public void testGetBeginResult6() {
		
		//HttpServletRequest
//		HttpServletRequest request = new HttpServletRequest();
//		request.setAttribute("pageNo", "1");
//		request.setAttribute("pageSize", "8");
		PageInfo pageInfo = new PageInfo("2", "8");
		pageInfo.setTotalResult(22);
		int beginResult = pageInfo.getBeginResult();
		int pageSize = pageInfo.getPageSize();
		PageBean pageBean = pageInfo.getPage();
		
		System.out.println("beginResult : " + beginResult + "   pageSize : " + pageSize);
	}
	
	@Test
	public void testGetBeginResult7() {
		
		//HttpServletRequest
//		HttpServletRequest request = new HttpServletRequest();
//		request.setAttribute("pageNo", "1");
//		request.setAttribute("pageSize", "8");
		PageInfo pageInfo = new PageInfo("3", "8");
		pageInfo.setTotalResult(22);
		int beginResult = pageInfo.getBeginResult();
		int pageSize = pageInfo.getPageSize();
		PageBean pageBean = pageInfo.getPage();
		
		System.out.println("beginResult : " + beginResult + "   pageSize : " + pageSize);
	}
}
