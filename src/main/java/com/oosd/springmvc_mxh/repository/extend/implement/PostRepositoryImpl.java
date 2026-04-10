package com.oosd.springmvc_mxh.repository.extend.implement;

import com.oosd.springmvc_mxh.sql.Database;
import com.oosd.springmvc_mxh.repository.extend.PostRepository;
import com.oosd.springmvc_mxh.entity.Post;
import com.oosd.springmvc_mxh.sql.Command;
import com.oosd.springmvc_mxh.util.ResultSetMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public final class PostRepositoryImpl implements PostRepository {

	@Override
	public boolean insert(Post post) throws SQLException {
		try (PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(Command.Post.INSERT)) {
			preparedStatement.setInt(1, post.getId());
			preparedStatement.setString(2, post.getTitle());
			preparedStatement.setString(3, post.getBody());
			preparedStatement.setInt(4, post.getUserId());
			preparedStatement.setString(5, post.getStatus());
			preparedStatement.setLong(6, post.getCreatedAt());
			return preparedStatement.executeUpdate() > 0;
		}
	}

	@Override
	public boolean update(Post post) throws SQLException {
		try (PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(Command.Post.UPDATE)) {
			preparedStatement.setString(1, post.getTitle());
			preparedStatement.setString(2, post.getBody());
			preparedStatement.setInt(3, post.getUserId());
			preparedStatement.setString(4, post.getStatus());
			preparedStatement.setLong(5, post.getCreatedAt());
			preparedStatement.setInt(6, post.getId());
			return preparedStatement.executeUpdate() > 0;
		}
	}

	@Override
	public boolean delete(Post post) throws SQLException {
		try (PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(Command.Post.DELETE)) {
			preparedStatement.setInt(1, post.getId());
			return preparedStatement.executeUpdate() > 0;
		}
	}

	@Override
	public Post getById(int id) throws SQLException {
		try (PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(Command.Post.SELECT_BY_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			return ResultSetMapper.map(resultSet, Post.class).getFirst();
		}
	}

	@Override
	public List<Post> getByUserId(int user_id) throws SQLException {
		try (PreparedStatement preparedStatement = Database.getInstance().getConnection().prepareStatement(Command.Post.SELECT_BY_USER_ID)) {
			preparedStatement.setInt(1, user_id);
			ResultSet resultSet = preparedStatement.executeQuery();
//			List<Post> result = new ArrayList<>();
//			while (resultSet.next()) {
//				result.add(new Post(
//						resultSet.getInt(1),
//						resultSet.getString(2),
//						resultSet.getString(3),
//						resultSet.getInt(4),
//						resultSet.getString(5),
//						resultSet.getLong(6)
//				));
//			}
			return ResultSetMapper.map(resultSet, Post.class);
		}
	}

}