package main.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import main.common.MysqlBase;

public class Course {
	/**
	 * 将授课教师和课程名插入课程表中
	 * 
	 * @param coursename
	 * @param userid
	 * @return
	 */
	public boolean inSert(String coursename, int userid) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		boolean flag = mysqlBase.execute(
				"insert into course (`course_name`,`teacher_id`) values ('" + coursename + "','" + userid + "')",
				connection);

		System.out.println("base返回" + flag);
		mysqlBase.close(connection);
		return flag;
	}

	/**
	 * 查询某教师教授的所有课程的课程号和课程名
	 * 
	 * @param teacherid
	 * @return
	 */
	public static Map<Integer, String> search_course(int teacherid) {
		MysqlBase mysqlBase = new MysqlBase();
		Map<Integer, String> map = new HashMap<Integer, String>();
		Connection connection = mysqlBase.createConnect();//
		String sql = "SELECT course_id,course_name from course WHERE teacher_id='" + teacherid + "';";
		ResultSet resultSet = mysqlBase.search(sql, connection);
		try {
			while (resultSet.next()) {
				int courseid = resultSet.getInt("course_id");
				String coursename = resultSet.getString("course_name");
				map.put(courseid, coursename);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);
		return map;
	}

	/**
	 * 获取教师用户教授的所有课程
	 * 
	 * @param userid
	 * @return
	 */
	public static List<Integer> search_courseid(int userid) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		List<Integer> list = new ArrayList<Integer>();
		String sql = "SELECT course_id from course WHERE teacher_id='" + userid + "';";
		ResultSet resultSet = mysqlBase.search(sql, connection);

		try {
			while (resultSet.next()) {
				list.add(resultSet.getInt("course_id"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);
		return list;
	}

	/**
	 * 获取课程名
	 * 
	 * @param courseid
	 * @return
	 */
	public static String search_coursename(int courseid) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "SELECT course_name FROM course WHERE course_id='" + courseid + "'";
		ResultSet rSet = mysqlBase.search(sql, connection);
		String courseName = "";
		try {
			while (rSet.next()) {
				courseName = rSet.getString("course_name").toString();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseName;

	}

	/**
	 * 获取课程图片
	 * 
	 * @param courseid
	 * @return
	 */
	public static String searchCourseImage(int courseid) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "SELECT course_image FROM course WHERE course_id='" + courseid + "'";
		ResultSet rSet = mysqlBase.search(sql, connection);
		String courseImage = "";
		try {
			while (rSet.next()) {
				courseImage = rSet.getString("course_image");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseImage;

	}

	/**
	 * 获取teacherid
	 * 
	 * @param courseid
	 * @return
	 */
	public static int searchTeacherId(int courseid) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "SELECT teacher_id From class WHERE course_id='" + courseid + "';";
		ResultSet rSet = mysqlBase.search(sql, connection);
		int teacherId = 0;
		try {
			while (rSet.next()) {
				teacherId = Integer.parseInt(rSet.getString("teacher_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);
		return teacherId;
	}

	/**
	 * 查询课程状态
	 * 
	 * @param courseid
	 * @return
	 */
	public static String searchCourseStatus(String courseid) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "SELECT course_status FROM course WHERE course_id='" + courseid + "'";
		ResultSet rSet = mysqlBase.search(sql, connection);
		String courseStatus = "";
		try {
			while (rSet.next()) {
				if (Integer.parseInt(rSet.getString("course_status")) == 0) {
					courseStatus = "课程进行中";
				} else {
					courseStatus = "课程已结束";
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseStatus;

	}

	public static JSONArray SearchCourseByConditions(String condiTions) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql ="SELECT DISTINCT" + 
				"	*" + 
				"FROM " + 
				"	course c INNER JOIN `user` u" + 
				" WHERE " + 
				"	c.course_name LIKE '%"+condiTions+"%' OR " + 
				"	u.user_name LIKE '%"+condiTions+"%';";
System.out.println(sql);
		ResultSet rSet = mysqlBase.search(sql, connection);
		JSONArray jsonArray = new JSONArray();

		try {
			while (rSet.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("courseId", rSet.getString("course_id"));
				jsonObject.put("courseName", rSet.getString("course_name"));
				jsonObject.put("courseImage", rSet.getString("course_image"));
				if (rSet.getString("course_status").equals("0")) {
					jsonObject.put("courseStatu", "课程正在进行");
				} else if (rSet.getString("course_status").equals("1")) {
					jsonObject.put("courseStatu", "课程已结束");
				}
				jsonObject.put("teacherName", rSet.getString("user_name"));
				jsonArray.add(jsonObject);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray;
	}
}
