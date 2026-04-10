package com.oosd.springmvc_mxh.repository.extend;

import com.oosd.springmvc_mxh.repository.BaseRepository;
import com.oosd.springmvc_mxh.entity.User;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

public interface UserRepository extends BaseRepository<User> {
	default boolean isExists(String username) throws SQLException {
		return false;
	}
	default boolean isValid(String username, String hashed_password) throws SQLException {
		return false;
	}
	default int getId(String username) throws SQLException {
		return -1;
	}
}