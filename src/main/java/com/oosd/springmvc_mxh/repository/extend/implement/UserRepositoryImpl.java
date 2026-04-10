package com.oosd.springmvc_mxh.repository.extend.implement;

import com.oosd.springmvc_mxh.sql.Database;
import com.oosd.springmvc_mxh.repository.extend.UserRepository;
import com.oosd.springmvc_mxh.entity.User;
import com.oosd.springmvc_mxh.sql.Command;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public final class UserRepositoryImpl implements UserRepository {

	@Override
	public boolean insert(User user) throws SQLException {
		try (PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(Command.User.INSERT)) {
			preparedStatement.setInt(1, user.getId());
			preparedStatement.setString(2, user.getUsername());
			preparedStatement.setString(3, user.getHashedPassword());
			preparedStatement.setString(4, user.getRole());
			preparedStatement.setLong(5, user.getCreatedAt());
			return preparedStatement.executeUpdate() > 0;
		}
	}

	@Override
	public boolean update(User user) throws SQLException {
		try (PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(Command.User.UPDATE)) {
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getHashedPassword());
			preparedStatement.setString(3, user.getRole());
			preparedStatement.setLong(4, user.getCreatedAt());
			preparedStatement.setInt(5, user.getId());
			return preparedStatement.executeUpdate() > 0;
		}
	}

	@Override
	public boolean delete(User user) throws SQLException {
		try (PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(Command.User.DELETE)) {
			preparedStatement.setInt(1, user.getId());
			return preparedStatement.executeUpdate() > 0;
		}
	}

	@Override
	public boolean isExists(String username) throws SQLException {
		try (PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(Command.User.SELECT_USERNAME)) {
			preparedStatement.setString(1, username);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.next();
			}
		}
	}

	@Override
	public boolean isValid(String username, String hashed_password) throws SQLException {
		try (PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(Command.User.SELECT_USERNAME_HASHED_PASSWORD)) {
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, hashed_password);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				return resultSet.next();
			}
		}
	}

	@Override
	public int getId(String username) throws SQLException {
		try (PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(Command.User.SELECT_ID_BY_USERNAME)) {
			preparedStatement.setString(1, username);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getInt("id");
				} else {
					return -1;
				}
			}
		}
	}

}