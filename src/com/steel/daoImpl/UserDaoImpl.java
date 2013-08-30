package com.steel.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.steel.dao.UserDao;
import com.steel.entity.User;
import com.steel.util.DBUtils;
import com.steel.util.Page;

public class UserDaoImpl implements UserDao {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	public void addUser(User user) throws SQLException {
		conn = DBUtils.getConnection();
		String sql = "insert into user(firstName,lastName,email,phone)values(?,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, user.getFirstName());
		ps.setString(2, user.getLastName());
		ps.setString(3, user.getEmail());
		ps.setString(4, user.getPhone());
		ps.executeUpdate();
		DBUtils.close(rs, ps, conn);
	}

	public List<User> ListUser(Page<User> page) throws SQLException {
		List<User> list = new ArrayList<User>();
		User user = null;
		conn = DBUtils.getConnection();
		String sql = "select * from user limit ?,?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, page.getBeginPage());
		ps.setInt(2, page.getPageSize());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			user = new User();
			user.setFirstName(rs.getString("firstName"));
			user.setLastName(rs.getString("lastName"));
			user.setEmail(rs.getString("email"));
			user.setPhone(rs.getString("phone"));
			user.setUserId(rs.getInt("id"));
			list.add(user);
		}
		DBUtils.close(rs, ps, conn);
		return list;
	}

	public void deleteUserById(int userId) throws SQLException {
		conn =DBUtils.getConnection();
		String sql = "delete from user where id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, userId);
		ps.executeUpdate();
		DBUtils.close(rs, ps, conn);
	}

	public void updateUserById(User user) throws SQLException {
		conn =DBUtils.getConnection();
		String sql = "update user set firstName =?,lastName = ?,email=?,phone=? where id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, user.getFirstName());
		ps.setString(2, user.getLastName());
		ps.setString(3, user.getEmail());
		ps.setString(4, user.getPhone());
		ps.setInt(5, user.getUserId());
		ps.executeUpdate();
		DBUtils.close(rs, ps, conn);
	}

	public int getCount() throws SQLException {
		conn = DBUtils.getConnection();
		int count = 0;
		String sql = "select count(*) from user";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			count =rs.getInt(1);
		}
		return count;
	}
}
