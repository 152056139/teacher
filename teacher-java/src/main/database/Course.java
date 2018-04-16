package main.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import main.common.MysqlBase;

public class Course {
	/**
	 * 将授课教师和课程名插入课程表中
	 * @param coursename
	 * @param userid
	 * @return
	 */
	public boolean inSert(String coursename,int userid)
	{
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		boolean flag = mysqlBase.execute("insert into course (`course_name`,`teacher_id`) values ('"+coursename+"','"+userid+"')",connection);

		System.out.println("base返回"+flag);
		mysqlBase.close(connection);
		return flag;
	}
	/**
	 * 查询某教师教授的所有课程的课程号和课程名
	 * @param teacherid
	 * @return
	 */
	public static Map<Integer, String> search_course(int teacherid) {
		MysqlBase mysqlBase = new MysqlBase();
		Map<Integer, String> map = new HashMap<Integer, String>();
		Connection connection = mysqlBase.createConnect();//
		String sql = "SELECT course_id,course_name from course WHERE teacher_id='"+teacherid+"';";
		ResultSet resultSet=mysqlBase.search(sql, connection);
		try {
			while(resultSet.next()) {
				int courseid=resultSet.getInt("course_id");
				String coursename=resultSet.getString("course_name");
				map.put(courseid, coursename);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);
		return map;
	}

}
