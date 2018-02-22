/**
 * 
 */
package com.mybatis.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 自定义拦截器1
 * @author asus
 *
 */
public class HandlerInterceptor1 extends HandlerInterceptorAdapter {

	/**
	 * 
	 */
	public HandlerInterceptor1() {
	}

	/*
	 *  预处理回调方法
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//return super.preHandle(request, response, handler);
		System.out.println("===========HandlerInterceptor1 preHandle"); 
		System.out.println("request.getRequestURL():"+request.getRequestURL());  
        System.out.println("request.getServletPath():"+request.getServletPath());
        System.out.println("request.getContentType():"+request.getContentType());
        System.out.println("request.getContextPath():"+request.getContextPath());
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            System.out.println("head: key["+key+"] value["+value+"]");
        }
        HttpSession se = request.getSession();  // Servlet 中获取 Session 对象 
        se.setMaxInactiveInterval(60 * 60 * 3);
    	System.out.println("Session ID：" + se.getId());  // 获取 Session 的 ID 
        System.out.println("request.getSession().getAttribute(\"username\"):"+request.getSession().getAttribute("username"));
        if (request.getSession().getAttribute("username") == null) {        	
        	request.getSession().setAttribute("username", "testUser");
		}
        
		return true;
	}

	/* 
	 * 后处理回调方法，实现处理器的后处理（但在渲染视图之前）
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//super.postHandle(request, response, handler, modelAndView);
		System.out.println("===========HandlerInterceptor1 postHandle");
		System.out.println("response.getStatus():"+response.getStatus());
		System.out.println("response.getCharacterEncoding():"+response.getCharacterEncoding());
		System.out.println("response.getContentType():"+response.getContentType());
		
	}

	/* 
	 * 整个请求处理完毕回调方法，即在视图渲染完毕时回调
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//super.afterCompletion(request, response, handler, ex);
		System.out.println("===========HandlerInterceptor1 afterCompletion");  
	}

	/* 
	 * 这个是针对异步请求的
	 */
	/*@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//super.afterConcurrentHandlingStarted(request, response, handler);
		System.out.println("===========HandlerInterceptor1 afterConcurrentHandlingStarted");  		
	}*/
	
	

}
