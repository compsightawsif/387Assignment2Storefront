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
	
	public User userLogin(String email, String password) throws SQLException {
		User user = null;
		
		try {
			query = "insert into users values (2,\"test2\",\"test3\",\"test4\",\"test5\");";
		
			pst = this.connection.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, password);
			rs = pst.executeQuery();
			System.out.println(rs);
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
			}
			
		}
		catch (SQLException e) {
			
		}
	
		return user;
		
	}
}