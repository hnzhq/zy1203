package com.st.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 获取数据库连接
 * 
 * @author shenming
 *
 */
public final class JdbcUtil {
	// 只需要获取数据库连接，其他的可由导入的包实现
	// 获取数据库连接
	public static Connection getConnection(String driver, String jdbcutil, String user, String password) {
		// 检查驱动是否异常
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(jdbcutil, user, password);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			// 抛出运行时异常
			throw new RuntimeException("获取数据库连接失败，请检查");
		}
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
