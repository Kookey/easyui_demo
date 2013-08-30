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

public class EditUserServlet extends HttpServlet {

	
	private static final long serialVersionUID = -1645211147362657829L;
	@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doPost(request, response);
		}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		UserDao userDao = UserDaoFactory.getUserDaoIntance();
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String userIdStr = request.getParameter("id");
		int userId = Integer.parseInt(userIdStr);
		User user = new User();
		user.setUserId(userId);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPhone(phone);
		try {
			userDao.updateUserById(user);
			String result = MessageUtil.getMessage(true, "修改用户成功");
			writer.append(result);
		} catch (SQLException e) {
			e.printStackTrace();
			String result = MessageUtil.getMessage(true, "修改用户失败");
			writer.append(result);
		}
		
	}
}
