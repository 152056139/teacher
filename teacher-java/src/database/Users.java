package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Users {
	private int userId;
	private String userName;
	private String userPassword;
	
	public Users(String userName, String userPassword) {
		this.userName = userName;
		this.userPassword = userPassword;
	}
	public Users() {
		
	}
	public int getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getPasswordFormMysql(String userName) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		ResultSet resultset = mysqlBase
				.search("select user_password from user where user_name = '" + userName + "';", connection);

		String password = "";
		try {
			while (resultset.next()) {
				// 通过字段检索
				password = resultset.getString("user_password");
				System.out.println("数据库中获取的密码：" + password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);
		return password;
	}
}
