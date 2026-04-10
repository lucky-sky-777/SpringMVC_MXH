package com.oosd.springmvc_mxh.repository.extend;

import com.oosd.springmvc_mxh.repository.BaseRepository;
import com.oosd.springmvc_mxh.entity.Follow;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

public interface FollowRepository extends BaseRepository<Follow> {
	default boolean isExists(int user_id, int target_user_id) throws SQLException {
		return false;
	}
}