package main.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import main.common.MysqlBase;

public class Users {
	private int userId;
	private String userName;
	private String userPassword;
	private String userBirthday;
	private int userIdentity;
	private int userSex;
	private String userEmail;
	private String userPhone;
	private String userSchoolId;
	private String userImage;


	public String getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getUserSchoolId() {
		return userSchoolId;
	}

	public void setUserSchoolId(String userSchoolId) {
		this.userSchoolId = userSchoolId;
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



	public int getUserIdentity() {
		return userIdentity;
	}

	public void setUserIdentity(int userIdentity) {
		this.userIdentity = userIdentity;
	}

	public int getUserSex() {
		return userSex;
	}

	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Users() {

	}
	public Users(String userName, String userPassword, String userBirthday, String userIdentity,
			String userSex, String userEmail, String userPhone, String userSchoolId, String userImage) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userIdentity = Integer.parseInt(userIdentity);
		this.userSex = Integer.parseInt(userSex);
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.userSchoolId = userSchoolId;
		this.userImage = userImage;
		this.userBirthday = userBirthday;
	}

	/**
	 * 通过用户名获取用户的密码以及id
	 * 
	 * @param userName
	 * @return
	 */
	public Map<String, String> getPassword(String userName) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		ResultSet resultset = mysqlBase
				.search("select user_password,user_id from user where user_name = '" + userName + "';", connection);

		String password = "";
		int id = 0;
		try {
			while (resultset.next()) {
				password = resultset.getString("user_password");
				id = resultset.getInt("user_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);

		Map<String, String> map = new HashMap<String, String>();
		map.put("password", password);
		map.put("id", Integer.toString(id));
		return map;
	}

	/**
	 * 查询用户名在数据库中有多少个
	 * 
	 * @param username
	 * @return
	 */
	public int countUserName(String username) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		ResultSet resultSet = mysqlBase
				.search("select count(user_name) count from user where user_name='" + username + "'", connection);
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

	/**
	 * 向数据库中插入一条记录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean register(String username, String password) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		mysqlBase.execute("insert into user (user_name,user_password) values ('" + username + "','" + password + "')",
				connection);
		mysqlBase.close(connection);
		return true;
	}

	public void update_other(int id, int sex, Timestamp birthday, String schoolid, String email, String phone,
			int identity) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "UPDATE user SET" + " user_sex='" + sex + "'," + " user_birthday='" + birthday + "',"
				+ " user_schoolid='" + schoolid + "'," + " user_email='" + email + "'," + " user_phone='" + phone + "',"
				+ " user_identity='" + identity + "'" + " WHERE user_id='" + id + "';";
		mysqlBase.execute(sql, connection);
		mysqlBase.close(connection);
		System.out.println(sql);
		System.out.println("注册，其他信息插入成功" + sex + birthday + schoolid + email + phone + identity + id);
	}

	public void update_onlyIdentity(int identity, String id) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		mysqlBase.execute("update user set user_identity ='" + identity + "'  where user_id='" + id + "'", connection);
		mysqlBase.close(connection);

	}

}
