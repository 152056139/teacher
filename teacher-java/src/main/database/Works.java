package main.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import main.common.MysqlBase;

public class Works {

	/**
	 * 插入作业信息
	 * 
	 * @param jsonObject
	 * @return
	 */
	public static boolean insertWorks(JSONObject jsonObject) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String classId = (String) jsonObject.get("classId");
		String workTitle = (String) jsonObject.get("workTitle");
		String workDescription = (String) jsonObject.get("workDescription");
		String workBeginTime = (String) jsonObject.get("workBeginTime");
		String workEndTime = (String) jsonObject.get("workEndTime");
		String sql = "INSERT INTO work (class_id,work_title,work_description,work_begin_time,work_end_time)"
				+ " values ('" + classId + "','" + workTitle + "','" + workDescription + "','" + workBeginTime + "','"
				+ workEndTime + "');";
		boolean rSet = mysqlBase.execute(sql, connection);
		if (rSet) {
			System.out.println("发布作业成功！");
		}
		mysqlBase.close(connection);
		return rSet;
	}

	/**
	 * 查询某一堂课的作业信息
	 * 
	 * @param classId
	 * @return
	 */
	public static JSONArray searchWorks(String classId) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "SELECT * FROM work WHERE class_id='" + classId + "';";
		ResultSet rSet = mysqlBase.search(sql, connection);
		JSONArray jsonArray = new JSONArray();
		try {
			while (rSet.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("workId", rSet.getString("work_id"));
				jsonObject.put("workTitle", rSet.getString("work_title"));
				jsonObject.put("workDescription", rSet.getString("work_description"));
				jsonObject.put("workBeginTime", rSet.getString("work_begin_time"));
				jsonObject.put("workEndTime", rSet.getString("work_end_time"));
				jsonArray.add(jsonObject);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);
		return jsonArray;
	}

	/**
	 * 获取作业的题目内容
	 * 
	 * @param workId
	 * @return
	 */
	public static JSONObject searchWhoWork(String workId) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "SELECT work_title,work_description FROM work WHERE work_id='" + workId + "';";
		JSONObject jsonObject = new JSONObject();
		ResultSet rSet = mysqlBase.search(sql, connection);
		try {
			while (rSet.next()) {
				jsonObject.put("workTitle", rSet.getString("work_title"));
				jsonObject.put("workDescription", rSet.getString("work_description"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mysqlBase.close(connection);
		}
		return jsonObject;
	}

	/**
	 * 查询某项作业的信息
	 * 
	 * @param workId
	 * @return
	 */
	public static JSONObject searchWorkTitle(String workId, String userId) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql1 = "SELECT work_title,work_description from work WHERE work_id='" + workId + "';";
		String sql2 ="SELECT answer_content from student_work WHERE user_id='"+userId+"' AND work_id='"+workId+"';";
		System.out.println(sql1);
		System.out.println(sql2);
		ResultSet rSet1 = mysqlBase.search(sql1, connection);
		ResultSet rSet2=mysqlBase.search(sql2, connection);
		JSONObject jsonObject = new JSONObject();
		try {
			while (rSet1.next()) {
				jsonObject.put("workTitle", rSet1.getString("work_title"));
				jsonObject.put("workDescription", rSet1.getString("work_description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			while(rSet2.next()) {
			jsonObject.put("answerContent", rSet2.getString("answer_content"));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean isHas=jsonObject.containsKey("answerContent");
		if (isHas) {
			jsonObject.put("isHas", "true");
		}else {
			jsonObject.put("isHas", "false");
		}
		System.out.println(jsonObject);
		mysqlBase.close(connection);
		return jsonObject;
	}

}
