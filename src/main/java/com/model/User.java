package com.model;

public class User {
	private int id;
	private int passcode;
	private String role;

	public User() {}
	
	public User(int id, int passcode, String role) {
		this.id = id;
		this.passcode = passcode;
		this.role = role;
	}
	public int getPasscode() {
		return passcode;
	}

	public void setPasscode(int passcode) {
		this.passcode = passcode;
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

	public int getId() {
		return id;
	}


}
