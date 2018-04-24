package main.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import main.common.MysqlBase;

public class Users {

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

	/**
	 * 通过userid 获取 密码
	 * 
	 * @param userid
	 * @return
	 */
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
	public static void updatePassword(int userid, String newpass) {
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
	public boolean regisTer(String username, String password, String path) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		mysqlBase.execute("insert into user (user_name, user_password, user_image) values ('" + username + "','"
				+ password + "','" + path + "')", connection);
		mysqlBase.close(connection);
		return true;
	}

	/**
	 * 更新用户其他信息
	 * 
	 * @param id
	 * @param sex
	 * @param birthday
	 * @param schoolid
	 * @param email
	 * @param phone
	 * @param identity
	 */
	public void updateOther(int id, int sex, Timestamp birthday, String schoolid, String email, String phone,
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

	/**
	 * 更新用户的身份
	 * 
	 * @param identity
	 * @param id
	 */
	public void updateOnlyIdentity(int identity, String id) {
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
	public static String searchUserName(int userId) {
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
	public static String searchUserIdentity(int userId) {
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
	public static String searchUserHead(int userId) {
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
				rS = rSet.getString("user_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mysqlBase.close(connection);
		return rS;
	}

	/**
	 * 查无此人||查有此人获取姓名
	 * 
	 * @param teacherName
	 * @return
	 */
	public static String searchTeacher(String teacherName) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "SELECT user_id FROM user WHERE user_name='" + teacherName + "';";
		ResultSet rSet = mysqlBase.search(sql, connection);
		String teacherId = "";

		try {
			while (rSet.next()) {
				teacherId = rSet.getString("user_id");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return teacherId;

	}

	/**
	 * 根据用户id查询用户性别
	 * 
	 * @param userId
	 * @return
	 */
	public static String searchUserSex(int userId) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "SELECT user_sex From user WHERE user_id='" + userId + "';";
		ResultSet rSet = mysqlBase.search(sql, connection);
		String rS = "";
		try {
			while (rSet.next()) {
				rS = rSet.getString("user_sex");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);
		return rS;
	}

	/**
	 * 根據用戶Id查詢用户生日
	 * 
	 * @param userId
	 * @return
	 */
	public static String searchUserBirthday(int userId) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "SELECT user_birthday From user WHERE user_id='" + userId + "';";
		ResultSet rSet = mysqlBase.search(sql, connection);
		String rS = "";
		try {
			while (rSet.next()) {
				rS = rSet.getString("user_birthday");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);
		return rS;
	}

	/**
	 * 根据用户id查询学号
	 * 
	 * @param userId
	 * @return
	 */
	public static String searchUserSchoolId(int userId) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "SELECT user_schoolid From user WHERE user_id='" + userId + "';";
		ResultSet rSet = mysqlBase.search(sql, connection);
		String rS = "";
		try {
			while (rSet.next()) {
				rS = rSet.getString("user_schoolid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);
		return rS;
	}
/**
 * 更改个人信息
 * @param userId
 * @param sex
 * @param userBirthday
 * @param image
 * @param phone
 * @param email
 * @return
 */
	public static JSONObject updateResume(String userId, String sex, String userBirthday, String image, String phone, String email) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "UPDATE user SET" + " user_sex='" + sex + "'," + " user_birthday='" + userBirthday + "',"
				+ " user_email='" + email + "'," + " user_phone='" + phone  + "'  WHERE user_id='" + userId +"';";
		System.out.println(sql);
        JSONObject jsonObject=new JSONObject();
		boolean rSet = mysqlBase.execute(sql, connection);
		mysqlBase.close(connection);
		if (rSet) {
			jsonObject.put("STATU", "success");
			System.out.println("修改成功");
		}else {
			jsonObject.put("STATU", "faile");
		}return jsonObject;
	}

}
