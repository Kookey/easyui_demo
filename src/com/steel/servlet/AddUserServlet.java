package com.steel.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.steel.dao.UserDao;
import com.steel.entity.User;
import com.steel.factory.UserDaoFactory;
import com.steel.util.MessageUtil;

public class AddUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1751019164536067068L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		User user = new User();
		UserDao userDao = UserDaoFactory.getUserDaoIntance();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		try {
			userDao.addUser(user);
			String result = MessageUtil.getMessage(true, "添加成功");
			writer.append(result);
		} catch (SQLException e) {
			String result = MessageUtil.getMessage(true, "添加失败");
			writer.append(result);
			e.printStackTrace();
		}
	}
}
