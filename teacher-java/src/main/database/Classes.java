package main.database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import main.common.MysqlBase;
public class Classes {
	/**
	 * 更新堂课信息
	 * @param classid
	 * @param courseid
	 * @param classtime
	 * @param classroom
	 * @param teachersay
	 */
	public static void update_class(String sql) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();//
		
		mysqlBase.execute(sql, connection);
		mysqlBase.close(connection);
		System.out.println("class基类更新成功！"   );
	}
	/**
	 * 创建某一堂课
	 * @param courseid
	 * @param classtime
	 * @param classroom
	 * @param teachersay
	 */
	public static void insert_class(int courseid, String classtime, String classroom) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();//
		String sql = "insert into class(course_id,class_room,class_time) values('"+courseid+"','"
		                                                                          +classroom+"','"
		                                                                          +classtime+"')";
		mysqlBase.execute(sql, connection);
		mysqlBase.close(connection);
		System.out.println("注册，课堂信息插入成功    "  + courseid + " " + classtime + " " + classroom );
	}

	
	
	
	
	
	

}