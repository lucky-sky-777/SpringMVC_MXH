package com.oosd.springmvc_mxh.sql;

public final class Command {

	public static final class Follow {

		public static final String INSERT = "insert into follows values (?, ?, ?)";

		public static final String UPDATE = "update follows set following_user_id = ?, followed_user_id = ?, created_at = ? where following_user_id = ? and followed_user_id = ?";

		public static final String DELETE = "delete from follows where following_user_id = ? and followed_user_id = ?";

		public static final String SELECT_ALL = "select * from follows";

		public static final String SELECT_USER_ID_TARGET_USER_ID = "select * from follows where followed_user_id = ? and following_user_id = ?";

	}

	public static final class Post {

		public static final String INSERT = "insert into posts values (?, ?, ?, ?, ?, ?)";

		public static final String UPDATE = "update posts set title = ?, body = ?, user_id = ?, status = ?, created_at = ? where id = ?";

		public static final String DELETE = "delete from posts where id = ?";

		public static final String SELECT_ALL = "select * from posts";

		public static final String SELECT_BY_ID = "select 1 from posts where id = ?";

		public static final String SELECT_BY_USER_ID = "select id, title, body, user_id, status, created_at from posts where user_id = ? order by created_at desc";

	}

	public static final class User {

		public static final String INSERT = "insert into users values (?, ?, ?, ?, ?)";

		public static final String UPDATE = "update users set username = ?, hashed_password = ?, role = ?, created_at = ? where id = ?";

		public static final String DELETE = "delete from users where id = ?";

		public static final String SELECT_ALL = "select * from users";

		public static final String SELECT_USERNAME = "select 1 from users where username = ? limit 1";

		public static final String SELECT_USERNAME_HASHED_PASSWORD = "select 1 from users where username = ? and hashed_password = ? limit 1";

		public static final String SELECT_ID_BY_USERNAME = "select id from users where username = ?";

	}

}