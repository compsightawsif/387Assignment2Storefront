package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.User;

public class UserDao {
	private Connection connection;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public UserDao(Connection connection) {
		this.connection = connection;
	}
	
	public User userLogin(int userPasscode) throws SQLException {
		User user = null;
		
		try {
			query = "select * from user where user_passcode = ?";
		
			pst = this.connection.prepareStatement(query);
			pst.setObject(1, userPasscode);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("user_id"));
				user.setId(rs.getInt("user_passcode"));
				user.setRole(rs.getString("role"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public void changePermission(int userId, String role) throws SQLException {
		User user = null;

		try {
			query = "UPDATE user SET role = ? WHERE user_id = ?";
			pst = this.connection.prepareStatement(query);
			pst.setString(1, role);
			pst.setInt(2, userId);
			rs = pst.executeQuery();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}

	}
}