package com.arex.blog.action;

import java.lang.reflect.ParameterizedType;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CommonAction<T> extends ActionSupport implements ServletRequestAware, ServletResponseAware, ModelDriven<T>{

	protected T model;
	
	protected ServletRequest request;
	protected HttpSession session;
	protected ServletContext application;
	protected ServletResponse response;
	
	public CommonAction() {
		 ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		 Class clazz = (Class) type.getActualTypeArguments()[0];
		 
		 try {
			model = (T) clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
		this.application = session.getServletContext();
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public T getModel() {
		return model;
	}
}
