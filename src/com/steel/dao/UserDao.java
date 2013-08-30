package com.steel.dao;

import java.sql.SQLException;
import java.util.List;

import com.steel.entity.User;
import com.steel.util.Page;

public interface UserDao{

	public void addUser(User user) throws SQLException;
	public List<User> ListUser(Page<User> page) throws SQLException;
	public void deleteUserById(int userId) throws SQLException;
	public void updateUserById(User user) throws SQLException;
	public int getCount() throws SQLException;
}
