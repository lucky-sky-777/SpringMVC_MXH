package com.oosd.springmvc_mxh.service;

import com.oosd.springmvc_mxh.repository.extend.PostRepository;
import com.oosd.springmvc_mxh.repository.extend.UserRepository;
import com.oosd.springmvc_mxh.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public final class PostService {

	private final PostRepository postRepository;
	private final UserRepository userRepository;

	@Autowired
	public PostService(PostRepository postRepository, UserRepository userRepository) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}

	public boolean createPost(String username, String title, String body) throws SQLException {
		long currentTimeMillis = System.currentTimeMillis();
		int user_id = userRepository.getId(username);

		Post newPost = new Post(
				(int) currentTimeMillis,
				title,
				body,
				user_id,
				"null",
				currentTimeMillis
		);

		return postRepository.insert(newPost);
	}

	public boolean updatePost(String username, int id, String title, String body) throws SQLException {
		Post currentPost = postRepository.getById(id);
		int user_id = userRepository.getId(username);

		if (currentPost.getUserId() != user_id) {
			return false;
		}

		Post updatedPost = new Post(
				currentPost.getId(),
				title,
				body,
				currentPost.getUserId(),
				currentPost.getStatus(),
				currentPost.getCreatedAt()
		);

		return postRepository.update(updatedPost);
	}

	public boolean deletePost(String username, int id) throws SQLException {
		Post currentPost = postRepository.getById(id);
		int user_id = userRepository.getId(username);

		if (currentPost.getUserId() != user_id) {
			return false;
		}

		return postRepository.delete(currentPost);
	}

	public List<Post> getAllPost(int user_id) throws SQLException {
		return postRepository.getByUserId(user_id);
	}

}