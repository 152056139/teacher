package main.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

	public Users(String userName, String userPassword, String userBirthday, String userIdentity, String userSex,
			String userEmail, String userPhone, String userSchoolId, String userImage) {
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
	public static Map<String, String> getPassword(String userName) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		ResultSet resultset = mysqlBase.search(
				"select user_password,user_id , user_identity from user where user_name = '" + userName + "';",
				connection);

		String password = "";
		int id = 0;
		int identity = 0;
		try {
			while (resultset.next()) {
				password = resultset.getString("user_password");
				id = resultset.getInt("user_id");
				identity = resultset.getInt("user_identity");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);

		Map<String, String> map = new HashMap<String, String>();
		map.put("password", password);
		map.put("id", Integer.toString(id));
		map.put("identity", Integer.toString(identity));
		return map;
	}

	public static Map<String, String> oldPassword(int userid) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		ResultSet resultset = mysqlBase.search("select user_password from user where user_id = '" + userid + "';",
				connection);

		String password = "";

		try {
			while (resultset.next()) {
				password = resultset.getString("user_password");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);

		Map<String, String> map = new HashMap<String, String>();
		map.put("password", password);

		return map;
	}

	/**
	 * 更改用户密码
	 * 
	 * @param userid
	 * @param newpass
	 */
	public static void update_password(int userid, String newpass) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "UPDATE user SET user_password='" + newpass + "'";
		mysqlBase.execute(sql, connection);

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
	public boolean register(String username, String password, String path) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		mysqlBase.execute("insert into user (user_name, user_password, user_image) values ('" + username + "','"
				+ password + "','" + path + "')", connection);
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
		System.out.println("注册，其他信息插入成功" + sex + birthday + schoolid + email + phone + identity + id);
	}

	public void update_onlyIdentity(int identity, String id) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		mysqlBase.execute("update user set user_identity ='" + identity + "'  where user_id='" + id + "'", connection);
		mysqlBase.close(connection);

	}

	/**
	 * 根据userid获取username
	 * 
	 * @param userId
	 * @return
	 */
	public static String search_user_name(int userId) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "SELECT user_name From user WHERE user_id='" + userId + "';";
		ResultSet rSet = mysqlBase.search(sql, connection);
		String rS = "";
		try {
			while (rSet.next()) {
				rS = rSet.getString("user_name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);
		return rS;
	}

	/**
	 * 根据userid获取user_identity
	 * 
	 * @param userId
	 * @return
	 */
	public static String search_user_identity(int userId) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "SELECT user_identity From user WHERE user_id='" + userId + "';";
		ResultSet rSet = mysqlBase.search(sql, connection);
		String rS = "";
		try {
			while (rSet.next()) {
				rS = rSet.getString("user_identity");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);
		return rS;
	}

	/**
	 * 根据userid获取userimage
	 * 
	 * @param userId
	 * @return
	 */
	public static String search_user_head(int userId) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "SELECT user_image From user WHERE user_id='" + userId + "';";
		ResultSet rSet = mysqlBase.search(sql, connection);
		String rS = "";
		try {
			while (rSet.next()) {
				rS = rSet.getString("user_image");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);
		return rS;
	}

	/**
	 * 获取教师名字
	 * 
	 * @param teacherId
	 * @return
	 */
	public static String searchTeacherName(int teacherId) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "SELECT user_name From user WHERE user_id='" + teacherId + "';";
		ResultSet rSet = mysqlBase.search(sql, connection);
		String rS = "";
		try {
			while (rSet.next()) {
				rS = rSet.getString("user_name0");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);
		return rS;
	}
		/**
		 * 查无此人||查有此人获取姓名
		 * @param teacherName
		 * @return
		 */
	public static String searchTeacher(String teacherName) {
	    	MysqlBase mysqlBase =new MysqlBase();
	    	Connection connection=mysqlBase.createConnect();
	    	String sql ="SELECT user_id FROM user WHERE user_name='"+teacherName+"';";
	    	ResultSet rSet=mysqlBase.search(sql, connection);
	    	String teacherId="";
	    	
				try {
					while (rSet.next()) {
						teacherId=rSet.getString("user_id");
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			return teacherId;

}

}
