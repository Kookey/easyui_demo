package com.steel.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.steel.dao.UserDao;
import com.steel.factory.UserDaoFactory;
import com.steel.util.MessageUtil;

public class DeleteUserServlet extends HttpServlet {

	private static final long serialVersionUID = -8738495606408301226L;

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
		String idStr = request.getParameter("id");
		int userId = Integer.parseInt(idStr);
		UserDao userDao = UserDaoFactory.getUserDaoIntance();
		try {
			userDao.deleteUserById(userId);
			String result = MessageUtil.getMessage(true, "删除用户成功");
			writer.append(result);
		} catch (SQLException e) {
			e.printStackTrace();
			String result =MessageUtil. getMessage(false, "删除用户失败");
			writer.append(result);
		}
	}
}