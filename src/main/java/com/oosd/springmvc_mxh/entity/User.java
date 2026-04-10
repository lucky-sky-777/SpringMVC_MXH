package com.oosd.springmvc_mxh.entity;

public class User {

	private int id;
	private String username;
	private String hashed_password;
	private String role;
	private long created_at;

	public User() {
	}

	public User(int id, String username, String hashed_password, String role, long created_at) {
		this.id = id;
		this.username = username;
		this.hashed_password = hashed_password;
		this.role = role;
		this.created_at = created_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHashedPassword() {
		return hashed_password;
	}

	public void setHashedPassword(String hashed_password) {
		this.hashed_password = hashed_password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(long created_at) {
		this.created_at = created_at;
	}
}