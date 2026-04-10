package com.oosd.springmvc_mxh.repository.extend.implement;

import com.oosd.springmvc_mxh.sql.Database;
import com.oosd.springmvc_mxh.repository.extend.FollowRepository;
import com.oosd.springmvc_mxh.entity.Follow;
import com.oosd.springmvc_mxh.sql.Command;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public final class FollowRepositoryImpl implements FollowRepository {

	@Override
	public boolean insert(Follow follow) throws SQLException {
		try (PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(Command.Follow.INSERT)) {
			preparedStatement.setInt(1, follow.getFollowingUserId());
			preparedStatement.setInt(2, follow.getFollowedUserId());
			preparedStatement.setLong(3, follow.getCreatedAt());
			return preparedStatement.executeUpdate() > 0;
		}
	}

	@Override
	public boolean update(Follow follow) throws SQLException {
		try (PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(Command.Follow.UPDATE)) {
			preparedStatement.setInt(1, follow.getFollowingUserId());
			preparedStatement.setInt(2, follow.getFollowedUserId());
			preparedStatement.setLong(3, follow.getCreatedAt());
			preparedStatement.setInt(4, follow.getFollowingUserId());
			preparedStatement.setInt(5, follow.getFollowedUserId());
			return preparedStatement.executeUpdate() > 0;
		}
	}

	@Override
	public boolean delete(Follow follow) throws SQLException {
		try (PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(Command.Follow.DELETE)) {
			preparedStatement.setInt(1, follow.getFollowingUserId());
			preparedStatement.setInt(2, follow.getFollowedUserId());
			return preparedStatement.executeUpdate() > 0;
		}
	}

	@Override
	public boolean isExists(int user_id, int target_user_id) throws SQLException {
		try (PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(Command.Follow.SELECT_USER_ID_TARGET_USER_ID)) {
			preparedStatement.setInt(1, user_id);
			preparedStatement.setInt(2, target_user_id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.next();
			}
		}
	}

}