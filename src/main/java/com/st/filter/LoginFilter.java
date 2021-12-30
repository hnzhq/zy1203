package com.st.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.st.model.User;

/**
 * 用户登录认证过滤器
 * 
 * @author snow1k
 * @date 2021/12/08
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// 对每一个请求都设计编码格式
		// req.setCharacterEncoding("utf-8");
		// resp.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("login_user");
		String request_uri = req.getRequestURI();

		if (req.getRequestURI().startsWith(req.getContextPath() + "/user/login")
				|| request_uri.toString().contains(".css") || request_uri.toString().contains(".js")
				|| request_uri.toString().contains(".jpg")) {
			chain.doFilter(request, response);
		} else {
			if (user == null) {
				resp.sendRedirect(req.getContextPath() + "/user/login");
			} else {
				chain.doFilter(request, response);
			}
		}
	}
}
