package com.steel.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.steel.dao.UserDao;
import com.steel.entity.User;
import com.steel.factory.UserDaoFactory;
import com.steel.util.Page;
import com.steel.util.PageUtil;

public class ListUserServlet extends HttpServlet{

	private static final long serialVersionUID = -5898906287882401891L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String rowsStr = request.getParameter("rows");
		String currentpageStr = request.getParameter("page");
		int pageSize = Integer.parseInt(rowsStr);
		int currentpage = Integer.parseInt(currentpageStr);
		Page<User> page = PageUtil.instancePage(currentpage,pageSize);
		PrintWriter writer = response.getWriter();
		UserDao userDao = UserDaoFactory.getUserDaoIntance();
		try {
			List<User> rows = userDao.ListUser(page);
			int total = userDao.getCount();
			page.setTotal(total);
			page.setRows(rows);
			ObjectMapper mapper = new ObjectMapper();
			String listJson = mapper.writeValueAsString(page);
			writer.append(listJson);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
