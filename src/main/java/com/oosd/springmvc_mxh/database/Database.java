package com.oosd.springmvc_mxh.database;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class Database {

	private MysqlDataSource mysqlDataSource;
	private final Connection connection;
	private static Database INSTANCE = null;

	private Database() throws SQLException {
		Properties properties = new Properties();
		try {
			properties.load(Database.class.getClassLoader().getResourceAsStream("database_config.properties"));
		} catch (IOException ie) {
			throw new RuntimeException(ie);
		}

		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		try {
			connection = getDataSource(url, username, password).getConnection();
		} catch (SQLException se) {
			throw new SQLException(se);
		}
	}

	public static Database getInstance() throws SQLException {
		if (INSTANCE == null || INSTANCE.getConnection().isClosed()) {
			INSTANCE = new Database();
		}
		return INSTANCE;
	}

	public DataSource getDataSource(String url, String user, String password) {
		if (mysqlDataSource == null) {
			mysqlDataSource = new MysqlDataSource();
		}
		mysqlDataSource.setURL(url);
		mysqlDataSource.setUser(user);
		mysqlDataSource.setPassword(password);
		return mysqlDataSource;
	}

	public Connection getConnection() {
		return connection;
	}

}