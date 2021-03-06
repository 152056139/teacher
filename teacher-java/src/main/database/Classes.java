package main.database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	public static void updateClass(String sql) {
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
	public static void insertClass(int courseid, String classtime, String classroom) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();//
		String sql = "insert into class(course_id,class_room,class_time) values('"+courseid+"','"
		                                                                          +classroom+"','"
		                                                                          +classtime+"');";
		mysqlBase.execute(sql, connection);
		mysqlBase.close(connection);
		System.out.println("注册，课堂信息插入成功    "  + courseid + " " + classtime + " " + classroom );
	}
/**
 * 获取某一天内某一门课的classid
 * @param courseid
 * @param mintime
 * @param maxtime
 * @return
 */
	public static List<Integer> searchClassId(int courseid,String mintime,String maxtime) {
		MysqlBase mysqlBase=new MysqlBase();
		Connection connection=mysqlBase.createConnect();
		List<Integer> list = new ArrayList<Integer>();
		String sql="SELECT class_id FROM class WHERE course_id='"+courseid+"' AND class_time<'"+maxtime+"' AND class_time>='"+mintime+"';";
		int classid=0;
		ResultSet rSet=mysqlBase.search(sql, connection);
		try {
			while(rSet.next()) {
				
				list.add(rSet.getInt("class_id"));
				System.out.println("数据库classid  "+classid);
			}
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);
		
		return list ;
	}
	/**
	 * 获取某节课的课程id
	 * @param classid
	 * @return
	 */
	 public static int searchCourseId(int classid) {
    	 MysqlBase mysqlBase =new MysqlBase();
    	 Connection connection=mysqlBase.createConnect();
    	 String sql="SELECT course_id From class WHERE class_id='"+classid+"';";
    	 ResultSet rSet=mysqlBase.search(sql, connection);
    	 int courseId=0;
    	 try {
			while(rSet.next()) {
				 courseId=Integer.parseInt(rSet.getString("course_id"));
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 mysqlBase.close(connection);
    	 return courseId;
     }
	 /**
	  * 根据classid获取教室
	  * @param classid
	  * @return
	  */
	public static String searchClassRoom(int classid) {
		MysqlBase mysqlBase =new MysqlBase();
		Connection connection =mysqlBase.createConnect();
		String sql="SELECT class_room FROM class WHERE class_id='"+classid+"';";
	    ResultSet rSet=mysqlBase.search(sql, connection);
	    String classroom="";
	    try {
			while(rSet.next()) {
				classroom=rSet.getString("class_room");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return classroom;
	}
	/**
	 *根据classid获取classtime
	 * @param classid
	 * @return
	 */
	public static String searchClassTime(int classid) {
		MysqlBase mysqlBase =new MysqlBase();
		Connection connection =mysqlBase.createConnect();
		String sql="SELECT class_time FROM class WHERE class_id='"+classid+"';";
	    ResultSet rSet=mysqlBase.search(sql, connection);
	    String classtime="";
	    try {
			while(rSet.next()) {
				classtime=rSet.getString("class_time");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return classtime;
	}
	/**
	 * classid查courseid
	 * @param classId
	 * @return
	 */
	public static String searchCourseByClassid(String classId) {
		MysqlBase mysqlBase =new MysqlBase();
		Connection connection =mysqlBase.createConnect();
		String sql="SELECT course_id FROM class WHERE class_id='"+classId+"';";
		ResultSet rSet=mysqlBase.search(sql, connection);
		String courseId=null;
		try {
			while(rSet.next()) {
				courseId=rSet.getString("course_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}mysqlBase.close(connection);
		return courseId;
	}
	

}