package com.oosd.springmvc_mxh.repository;

import java.sql.SQLException;

public interface BaseRepository<Entity> {
	default boolean insert(Entity entity) throws SQLException {
		return false;
	}
	default boolean update(Entity entity) throws SQLException {
		return false;
	}
	default boolean delete(Entity entity) throws SQLException {
		return false;
	}
}