package main.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.common.MysqlBase;

public class Student_course {
	public static void insert_teacher_course(int user_id, int course_id) {
           MysqlBase mysqlBase=new MysqlBase();
           Connection connection =mysqlBase.createConnect();
           String sql="INSERT INTO student_course (user_id,course_id) values('"+user_id+"','"+course_id+"');";
           mysqlBase.execute(sql, connection);
           mysqlBase.close(connection);
	}
}
