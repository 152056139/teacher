package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Course {
	private String courseName;
	
	
	public boolean inSert(String coursename,int userid)
	{
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		boolean flag = mysqlBase.execute("insert into course (`course_name`,`teacher_id`) values ('"+coursename+"','"+userid+"')",connection);

		System.out.println("base返回"+flag);
		mysqlBase.close(connection);
		return flag;
	}

}
