package com.oucre.interceptor;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.oucre.pojo.User;
import com.oucre.service.BusiCommonService;

public class CommonInterceptor implements HandlerInterceptor {
	@Resource
	private BusiCommonService commonService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTf-8");
		User u = (User) request.getSession().getAttribute("user_info");
		String url = request.getRequestURI().toString();
		if (isRoleResources(u.getRoleid(), url)) {
			System.out.println("============================");
			System.out.println("您已经别拦截");
			System.out.println("============================");
			writerout("非法请求,您无权访问!", response);
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
	}

	/**
	 * 逻辑方法
	 * 
	 * @date 2015年4月17日 上午11:41:37
	 * @author haoli_jun
	 * @param Roleid
	 * @param url
	 * @return
	 */
	public boolean isRoleResources(Integer Roleid, String url) {
		String sdo = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf(".do"));
		if (url.indexOf("common") > -1) {
			return false;
		}
		if (commonService.isResources(Roleid, sdo)) {
			return false;
		}
		return true;
	}

	public void writerout(String str, HttpServletResponse response) {
		// 返回到登录界面
		String s = "<script language='javascript'>alert('" + str + "');window.top.location.href='" + "/login.jsp" + "';</script>";
		response.setContentType("text/html;charset=UTF-8");// 解决中文乱码
		try {
			PrintWriter writer = response.getWriter();
			writer.write(s);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
