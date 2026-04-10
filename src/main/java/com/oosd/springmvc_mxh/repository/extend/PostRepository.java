package com.oosd.springmvc_mxh.repository.extend;

import com.oosd.springmvc_mxh.repository.BaseRepository;
import com.oosd.springmvc_mxh.entity.Post;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PostRepository extends BaseRepository<Post> {
	default Post getById(int id) throws SQLException {
		return null;
	}
	default List<Post> getByUserId(int user_id) throws SQLException {
		return new ArrayList<>();
	}
}