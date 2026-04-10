package com.oosd.springmvc_mxh.service;

import com.oosd.springmvc_mxh.repository.extend.FollowRepository;
import com.oosd.springmvc_mxh.repository.extend.UserRepository;
import com.oosd.springmvc_mxh.entity.Follow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public final class FollowService {

	private final FollowRepository followDAO;
	private final UserRepository userDAO;

	@Autowired
	public FollowService(
			FollowRepository followDAO,
			UserRepository userDAO
	) {
		this.followDAO = followDAO;
		this.userDAO = userDAO;
	}

	public boolean follow(String username, String target_username) throws SQLException {
		if (isFollowed(username, target_username)) {
			return false;
		}
		int user_id = userDAO.getId(username);
		int target_user_id = userDAO.getId(target_username);
		long currentTimeMillis = System.currentTimeMillis();
		Follow follow = new Follow(
				target_user_id,
				user_id,
				currentTimeMillis
		);
		return followDAO.insert(follow);
	}

	public boolean unfollow(String username, String target_username) throws SQLException {
		if (!isFollowed(username, target_username)) {
			return false;
		}
		int user_id = userDAO.getId(username);
		int target_user_id = userDAO.getId(target_username);
		long currentTimeMillis = System.currentTimeMillis();
		Follow follow = new Follow(
				target_user_id,
				user_id,
				currentTimeMillis
		);
		return followDAO.delete(follow);
	}

	public boolean isFollowed(String username, String target_username) throws SQLException {
		int user_id = userDAO.getId(username);
		int target_user_id = userDAO.getId(target_username);
		return followDAO.isExists(user_id, target_user_id);
	}

}