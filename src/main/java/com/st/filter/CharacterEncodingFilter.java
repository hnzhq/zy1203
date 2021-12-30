package com.st.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤器使用的是职责链的设计模式
 * 
 * @author snow1k
 * @date 2021/12/08
 */
public class CharacterEncodingFilter implements Filter {
	/**
	 * 执行过滤
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// System.out.println(request instanceof HttpServletRequest);
		// System.out.println(response instanceof HttpServletResponse);

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// 对每一个请求都设计编码格式
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");

		// 继续传递给下一个过滤器，如果所有过滤器都结束了，则传递到servlet
		chain.doFilter(request, response);

	}

}
