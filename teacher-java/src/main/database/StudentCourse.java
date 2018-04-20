package main.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.common.MysqlBase;

public class StudentCourse {
	public static void insertTeacherCourse(int userId, int courseId) {
           MysqlBase mysqlBase=new MysqlBase();
           Connection connection =mysqlBase.createConnect();
           String sql="INSERT INTO student_course (user_id,course_id) values('"+userId+"','"+courseId+"');";
           mysqlBase.execute(sql, connection);
           mysqlBase.close(connection);
	}
}
