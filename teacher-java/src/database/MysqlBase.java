package database;

import java.sql.*;

public class MysqlBase {
	// JDBC 驱动名及数据库 URL
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://192.168.1.166:3306/teacher?useSSL=true";

	// 数据库的用户名与密码，需要根据自己的设置
	private static final String USER = "root";
	private static final String PASS = "969110gghhzz";
	//

	/**
	 * 创建连接
	 */
	public Connection createConnect() {
		System.out.println("mysqlbase 正在创建数据库连接");
		Connection conn = null;
		// 注册 JDBC 驱动
		try {
			Class.forName(JDBC_DRIVER);// 打开链接
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("mysqlbase 连接创建成功");
		return conn;
	}

	/**
	 * 执行查询操作
	 * 
	 * @param sql
	 * @return
	 */
	public ResultSet search(String sql, Connection conn) {
		System.out.println("mysqlbase 正在执行查询操作");
		// 执行查询
		Statement stmt = null;
		ResultSet resultSet = null;
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("mysqlbase 查询操作执行成功");
		return resultSet;
	}

	/**
	 * 执行插入，删除，修改操作
	 * 
	 * @param sql
	 */
	public boolean execute(String sql, Connection conn) {
		System.out.println("正在执行插入，删除，修改操作");
		Statement stmt = null;
		boolean flag = false;
		try {
			stmt = conn.createStatement();
			flag = stmt.execute(sql);
			System.out.println("mysql返回"+flag);
			flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
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
