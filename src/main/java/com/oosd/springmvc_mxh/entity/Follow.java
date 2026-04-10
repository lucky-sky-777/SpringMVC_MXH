package com.oosd.springmvc_mxh.entity;

public class Follow {

	private int following_user_id;
	private int followed_user_id;
	private long created_at;

	public Follow() {
	}

	public Follow(int following_user_id, int followed_user_id, long created_at) {
		this.following_user_id = following_user_id;
		this.followed_user_id = followed_user_id;
		this.created_at = created_at;
	}

	public int getFollowingUserId() {
		return following_user_id;
	}

	public void setFollowingUserId(int following_user_id) {
		this.following_user_id = following_user_id;
	}

	public int getFollowedUserId() {
		return followed_user_id;
	}

	public void setFollowedUserId(int followed_user_id) {
		this.followed_user_id = followed_user_id;
	}

	public long getCreatedAt() {
		return created_at;
	}

	public void setCreatedAt(long created_at) {
		this.created_at = created_at;
	}
}