package com.st.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.st.model.User;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 对每一个请求都设计编码格式
		// req.setCharacterEncoding("utf-8");
		// resp.setCharacterEncoding("utf-8");
		String pathInfo = req.getPathInfo();
		if ("/login".equals(pathInfo)) {
			req.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 对每一个请求都设计编码格式
		// req.setCharacterEncoding("utf-8");
		// resp.setCharacterEncoding("utf-8");
		String pathInfo = req.getPathInfo();
		if ("/login".equals(pathInfo)) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");

			if (username.equals("admin") && password.equals("123")) {
				User u = new User();
				u.setUsername(username);
				u.setPassword(password);
				req.getSession().setAttribute("login_user", u);

				req.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(req, resp);

			} else {
				req.setAttribute("error", "用户名密码不正确");
				req.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(req, resp);
			}
		}
	}

}
