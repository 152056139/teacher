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
				// 閫氳繃瀛楁妫�绱�
				password = resultset.getString("user_password");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);
		return password;
	}
	public boolean inSert(String username,String password)
	{
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		mysqlBase.execute("insert into user (user_name,user_password) values ('"+username+"','"+password+"')",connection);
		mysqlBase.close(connection);
		return true;
	}
	public int countUserName (String username) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		ResultSet resultSet=mysqlBase.search("select count(user_name) count from user where user_name='"+username+"'",connection);
		int count = 5;
		
		try {
			while (resultSet.next()) {
				// 閫氳繃瀛楁妫�绱�
				count = resultSet.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return count;
		
	}
}
