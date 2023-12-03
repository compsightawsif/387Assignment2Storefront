package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exceptions.PasscodeChangeException;
import com.model.Order;
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

	public void changePermission(int userPasscode, String role) throws SQLException {
		try {
			query = "UPDATE user SET role = ? WHERE user_passcode = ?";
			pst = this.connection.prepareStatement(query);
			pst.setString(1, role);
			pst.setInt(2, userPasscode);
			pst.execute();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean setPasscode(int userId, int userPasscode) throws SQLException {
		try {
			query = "UPDATE user SET user_passcode = ? where user_id = ?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, userPasscode);
			pst.setInt(2, userId);
			pst.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		String query = "SELECT * FROM user";

		try (PreparedStatement pst = connection.prepareStatement(query);
			 ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("user_id"));
				u.setRole(rs.getString("role"));
				u.setPasscode(rs.getInt("user_passcode"));
				users.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
}