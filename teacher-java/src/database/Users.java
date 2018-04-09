package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Users {
	private int userId;
	private String userName;
	private String userPassword;
	private Timestamp userBirthday;
	private int userIdentity;
	private int userSex;
	private String userEmail;
	private String userPhone;
	
	public Users() {

	}
	

	/**
	 * 通过用户名获取用户的密码以及id
	 * @param userName
	 * @return
	 */
	public Map<String, String> getPasswordFormMysql(String userName) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		ResultSet resultset = mysqlBase
				.search("select user_password,user_id from user where user_name = '" + userName + "';", connection);

		String password = "";
		int id=0;
		try {
			while (resultset.next()) {
				password = resultset.getString("user_password");
				id=resultset.getInt("user_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("password", password);
		map.put("id", Integer.toString(id));
		return map;
	}
	
	/**
	 * 向数据库中插入一条记录
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean inSert(String username,String password)
	{
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		mysqlBase.execute("insert into user (user_name,user_password) values ('"+username+"','"+password+"')",connection);
		mysqlBase.close(connection);
		return true;
	}
	
	/**
	 * 查询用户名在数据库中有多少个
	 * @param username
	 * @return
	 */
	public int countUserName (String username) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		ResultSet resultSet=mysqlBase.search("select count(user_name) count from user where user_name='"+username+"'",connection);
		int count = 5;

		try {
			while (resultSet.next()) {
				count = resultSet.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
}
