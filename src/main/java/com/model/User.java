package com.model;

public class User {
	private int id;
	private String username;
	private String email;
	private String password;
	private String role;

	public User() {}
	
	public User(int id, String username, String email, String password, String role) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getId() {
		return id;
	}


	public String getUsername() {
		return username;
	}


	public String getEmail() {
		return email;
	}

}
