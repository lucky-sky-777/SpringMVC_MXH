package com.oosd.springmvc_mxh.entity;

public class Post {

	private int id;
	private String title;
	private String body;
	private int user_id;
	private String status;
	private long created_at;

	public Post() {
	}

	public Post(int id, String title, String body, int user_id, String status, long created_at) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.user_id = user_id;
		this.status = status;
		this.created_at = created_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(long created_at) {
		this.created_at = created_at;
	}
}