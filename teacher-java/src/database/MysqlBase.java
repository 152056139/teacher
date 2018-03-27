package database;

import java.sql.*;

public class MysqlBase {
	// JDBC 驱动名及数据库 URL
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/teacher";

	// 数据库的用户名与密码，需要根据自己的设置
	private static final String USER = "root";
	private static final String PASS = "root";

	/**
	 * 创建连接
	 */
	public Connection createConnect() {
		Connection conn = null;
		// 注册 JDBC 驱动
		try {
			Class.forName(JDBC_DRIVER);// 打开链接
			System.out.println("正在连接数据库");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 执行查询操作
	 * 
	 * @param sql
	 * @return
	 */
	public ResultSet search(String sql, Connection conn) {
		// 执行查询
		Statement stmt = null;
		ResultSet resultSet = null;
		System.out.println("正在执行查询操作");
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

	/**
	 * 执行插入，删除，修改操作
	 * 
	 * @param sql
	 */
	public void execute(String sql, Connection conn) {
		System.out.println("正在执行插入，删除，修改操作");
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("关闭成功");
	}
}