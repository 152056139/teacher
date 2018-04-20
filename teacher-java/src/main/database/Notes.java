package main.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import main.common.MysqlBase;

public class Notes {
	/**
	 * 发布笔记时插入课堂id，用户id，笔记正文
	 * 
	 * @param classid
	 * @param userid
	 * @param notetext
	 * @throws SQLException
	 */
	public static int insertNote(int userid, int classid, String notetext) throws SQLException {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();//
		String sql = "insert into note(note_comment,note_image, class_id,user_id,note_content) values('[]','[]','"
				+ classid + "','" + userid + "','" + notetext + "');";
		String sql2 = "select Max(note_id) from note;";
		System.out.println(sql);
		mysqlBase.execute(sql, connection);
		ResultSet resultSet = mysqlBase.search(sql2, connection);
		int noteId = 0;
		while (resultSet.next()) {
			noteId = resultSet.getInt(1);
		}
		mysqlBase.close(connection);
		System.out.println("笔记信息插入成功    " + classid + " " + userid + " " + notetext + "  " + noteId);
		return noteId;
	}

	/**
	 * 获取某条笔记的image
	 * 
	 * @param noteId
	 * @return
	 */
	public static String serchNoteImage(int noteId) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "SELECT NOTE_IMAGE FROM NOTE WHERE NOTE_ID='" + noteId + "';";

		ResultSet rSet = mysqlBase.search(sql, connection);
		String oldImage = "";
		try {
			while (rSet.next()) {
				oldImage = rSet.getString("note_image");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return oldImage;
	}

	/**
	 * 更新笔记的图片
	 * 
	 * @param image
	 * @param noteId
	 */
	public static void updateNoteImage(String image, int noteId) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "UPDATE NOTE SET note_image='" + image + "' WHERE note_id='" + noteId + "';";
		mysqlBase.execute(sql, connection);
		mysqlBase.close(connection);
		System.out.println("笔记图片插入成功");
	}

	/**
	 * 查询已有的笔记点赞
	 * 
	 * @param noteId
	 * @return
	 */
	public static String searchNoteGood(int noteId) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "SELECT NOTE_GOOD FROM NOTE WHERE note_id='" + noteId + "'; ";
		ResultSet rSet = mysqlBase.search(sql, connection);
		String OldGood = "";
		try {
			while (rSet.next()) {
				OldGood = rSet.getString("NOTE_GOOD");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return OldGood;
	}

	/**
	 * 更新笔记的点赞
	 * 
	 * @param userId
	 * @param noteId
	 */
	public static void updateNoteGood(String userId, int noteId) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "UPDATE NOTE SET note_good='" + userId + "'WHERE note_id='" + noteId + "';";
		mysqlBase.execute(sql, connection);
		mysqlBase.close(connection);
		System.out.println("点赞更新成功");
	}

	/**
	 * 获取该条笔记已有的评论
	 * 
	 * @param noteId
	 * @return
	 */
	public static String searchNoteComment(int noteId) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "SELECT note_comment FROM note WHERE note_id='" + noteId + "';";
		String OldCmd = "";
		ResultSet rSet = mysqlBase.search(sql, connection);
		try {
			while (rSet.next()) {
				OldCmd = rSet.toString();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.search(sql, connection);
		mysqlBase.close(connection);
		return OldCmd;
	}

	/**
	 * 更新该条笔记的评论
	 * 
	 * @param commend
	 * @param noteId
	 */
	public static void updateNoteComment(String commend, int noteId) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "UPDATE NOTE SET note_commend ='" + commend + "'WHERE note_id='" + noteId + "';";
		mysqlBase.execute(sql, connection);
		mysqlBase.close(connection);
	}

	/**
	 * 
	 * 获取该堂课所有笔记的id
	 * 
	 * @param classid
	 * @return
	 */
	public static JSONArray searchNoteid(int classid) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "SELECT note_id,user_id,note_image,note_content,note_time,note_comment FROM note WHERE class_id='"
				+ classid + "';";
		ResultSet rSet = mysqlBase.search(sql, connection);
		
		JSONArray jsonArray=new JSONArray();
		try {
			while (rSet.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("noteId", rSet.getString("note_id"));
				jsonObject.put("userId", rSet.getString("user_id"));
				jsonObject.put("noteContent", rSet.getString("note_content"));
				jsonObject.put("noteTime", rSet.getString("note_time"));
				jsonObject.put("noteCmd", rSet.getString("note_comment"));
				jsonObject.put("noteImage", rSet.getString("note_image"));
				jsonArray.add(jsonObject);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlBase.close(connection);

		return jsonArray;
	}
}
