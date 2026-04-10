package com.oosd.springmvc_mxh.service;

import com.oosd.springmvc_mxh.repository.extend.UserRepository;
import com.oosd.springmvc_mxh.entity.User;
import com.oosd.springmvc_mxh.util.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public final class UserService {

	private final UserRepository userDAO;

	@Autowired
	public UserService(UserRepository userDAO) {
		this.userDAO = userDAO;
	}

	public boolean signIn(String username, String password) throws SQLException {
		String hashed_password = Hasher.hash(password, Hasher.Algorithm.SHA_256);
		return userDAO.isValid(username, hashed_password);
	}

	public boolean signUp(String username, String password, String repassword) throws SQLException {
		if (!password.equals(repassword)) {
			return false;
		}

		if (userDAO.isExists(username)) {
			return false;
		}

		String hashed_password = Hasher.hash(password, Hasher.Algorithm.SHA_256);

		long currentTimeMillis = System.currentTimeMillis();
		User user = new User(
				(int) currentTimeMillis,
				username,
				hashed_password,
				"user",
				currentTimeMillis
		);

		return userDAO.insert(user);
	}

	public int getUserId(String username) throws SQLException {
		return userDAO.getId(username);
	}

}