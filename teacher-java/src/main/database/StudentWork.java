package main.database;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import main.common.MysqlBase;
public class StudentWork {
    /**
     * 是否提交作业
     * @param userId
     * @return
     */
	public static boolean searchSubmit(String userId,String workId) {
		MysqlBase mysqlBase =new MysqlBase();
		Connection connection=mysqlBase.createConnect();
		String sql="SELECT * FROM student_work WHERE user_id='"+userId+"' AND work_id='"+workId+"' ;";
		ResultSet rSet =mysqlBase.search(sql, connection);
		boolean submit=false;
		try {
			if(rSet.next()) {
				submit=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}mysqlBase.close(connection);
		return submit;
	}
	/**
	 * 提交作业
	 * @param userId
	 * @param workId
	 * @param answer
	 * @return
	 */
	public static boolean insertStudentWorks(String userId,String workId,String answer) {
		MysqlBase mysqlBase =new MysqlBase();
		Connection connection=mysqlBase.createConnect();
		
		String sql="INSERT INTO student_work (user_id,work_id,answer_content)"
				+ " values ('"+userId+"','"+workId+"','"+answer+"');";
		boolean rSet=mysqlBase.execute(sql, connection);
		if(rSet) {
			System.out.println("提交作业成功！");
		}
		mysqlBase.close(connection);
		return rSet;
	}
	/**
	 * 查找已完成的人
	 * @param courseId
	 * @param workId
	 * @return
	 */
	public static JSONArray searchSubmited(String courseId,String workId){
		MysqlBase mysqlBase=new MysqlBase();
		Connection connection=mysqlBase.createConnect();
		String sql="SELECT distinct user_name from user WHERE user_id="
				+ "(SELECT distinct sw.user_id FROM student_course sc , student_work sw "
				+ "WHERE sc.course_id='"+courseId+"' AND sw.work_id='"+workId+"');";
		System.out.println(sql);
		ResultSet rSet=mysqlBase.search(sql, connection);
		JSONArray jsonArray=new JSONArray();
		try {
			while(rSet.next()) {
				jsonArray.add(rSet.getString("user_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);
		return jsonArray;
	}
	/**
	 * 查询未完成的学生
	 * @param courseId
	 * @param workId
	 * @return
	 */
	public static JSONArray searchUnsubmited(String courseId,String workId){
		MysqlBase mysqlBase=new MysqlBase();
		Connection connection=mysqlBase.createConnect();
		String sql="SELECT distinct user_name FROM user WHERE user_id="
				+ "(SELECT user_id FROM student_course WHERE user_id NOT IN"
				+ "(SELECT sw.user_id FROM student_course sc , student_work sw "
				+ "WHERE sc.course_id='"+courseId+"' AND sw.work_id='"+workId+"'));";
		System.out.println(sql);
		ResultSet rSet=mysqlBase.search(sql, connection);
		JSONArray jsonArray=new JSONArray();
		try {
			while(rSet.next()) {
				jsonArray.add(rSet.getString("user_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);
		return jsonArray;
	}
	/**
	 * 查看作业完成情况
	 * @param studentName
	 * @param workId
	 * @return
	 */
	public static JSONObject searchWorkAnswer(String studentName,String workId) {
		MysqlBase mysqlBase=new MysqlBase();
		Connection connection=mysqlBase.createConnect();
		String sql ="SELECT answer_content,answer_score FROM student_work WHERE user_id"
				+ "=(SELECT user_id FROM user WHERE user_name='"+studentName+"') AND"
			    + " work_id='"+workId+"';";
		System.out.println(sql);
		ResultSet rSet=mysqlBase.search(sql, connection);
		JSONObject jsonObject=new JSONObject();
		try {
			while(rSet.next()) {
				jsonObject.put("answerContent", rSet.getString("answer_content"));
				jsonObject.put("answerScore", rSet.getString("answer_score"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}mysqlBase.close(connection);
		System.out.println(jsonObject);
		return jsonObject;
				
	}
	/**
	 * 作业评分
	 * @param studentName
	 * @param workId
	 * @param workScore
	 * @return
	 */
	public static boolean updateWorkScore(String studentName,String workId,String workScore) {
		MysqlBase mysqlBase =new MysqlBase();
		Connection connection=mysqlBase.createConnect();
		String sql="UPDATE student_work SET answer_score='"+workScore+"' "
				+ " WHERE work_id='"+workId+"' AND user_id="
			    + "(SELECT user_id FROM user WHERE user_name='"+studentName+"')";
		boolean rSet=mysqlBase.execute(sql, connection);
		mysqlBase.close(connection);
		return rSet;
	}
	/**
	 * 更改作业答案（已提交的、作业正在进行中的）
	 * @param studentId
	 * @param workId
	 * @param newAnswer
	 * @return
	 */
	public static boolean updateWorkAnswer(String studentId,String workId,String newAnswer) {
		MysqlBase mysqlBase=new MysqlBase();
		Connection connection=mysqlBase.createConnect();
		String sql="UPDATE student_work SET answer_content='"+newAnswer+"' "
				+ " WHERE user_id='"+studentId+"'AND work_id='"+workId+"';";
		boolean rSet=mysqlBase.execute(sql, connection);
		mysqlBase.close(connection);
		return rSet;
	}
}
