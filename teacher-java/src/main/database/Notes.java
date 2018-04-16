package main.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import com.mysql.jdbc.UpdatableResultSet;

import main.common.MysqlBase;
public class Notes {
	/**
	 * 发布笔记时插入课堂id，用户id，笔记正文
	 * @param classid
	 * @param userid
	 * @param notetext
	 * @throws SQLException 
	 */
	public static int insert_note(int classid, int userid, String notetext) throws SQLException {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();//
		String sql = "insert into note(note_comment,note_good,note_image, class_id,user_id,note_content) values('[]','[]','[]','"+classid+"','"
				                                                               +userid+"','"
				                                                               +notetext+ "');";
		String sql2="select Max(note_id) from note;";
		System.out.println(sql);
		mysqlBase.execute(sql, connection);
		ResultSet resultSet=mysqlBase.search(sql2, connection);
		int noteId=0;
		while(resultSet.next()) {
		 noteId=resultSet.getInt(1);
		}
		mysqlBase.close(connection);
		System.out.println("笔记信息插入成功    "  + classid + " " + userid + " " + notetext+"  "+noteId );
		return noteId;
		}
	/**
	 * 获取某条笔记的image
	 * @param noteId
	 * @return
	 */
	public static String serch_note_image(int noteId) {
		MysqlBase mysqlBase=new MysqlBase();
		Connection connection= mysqlBase.createConnect();
		String sql ="SELECT NOTE_IMAGE FROM NOTE WHERE NOTE_ID='"+noteId+"';";
		
		ResultSet rSet=mysqlBase.search(sql, connection);
		String oldImage = "";
		try {
			while(rSet.next()) {
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
	 * @param image
	 * @param noteId
	 */
	public static void update_note_image(String image,int noteId) {
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		String sql = "UPDATE NOTE SET note_image='"+image+"' WHERE note_id='"+noteId+"';";
		mysqlBase.execute(sql, connection);
		mysqlBase.close(connection);
		System.out.println("笔记图片插入成功");
	}
	/**
	 * 查询已有的笔记点赞
	 * @param noteId
	 * @return
	 */
	public static String search_note_good(int noteId) {
		MysqlBase mysqlBase=new MysqlBase();
		Connection connection= mysqlBase.createConnect();
		String sql="SELECT NOTE_GOOD FROM NOTE WHERE note_id='"+noteId+"'; ";
		ResultSet rSet=mysqlBase.search(sql, connection);
		String OldGood="";
		try {
			while(rSet.next()) {
				OldGood=rSet.getString("NOTE_GOOD");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return OldGood;
	}
	/**
	 * 更新笔记的点赞
	 * @param userId
	 * @param noteId
	 */
	public static void update_note_good(String userId,int noteId) {
	    MysqlBase mysqlBase=new MysqlBase();
	    Connection connection=mysqlBase.createConnect();
	    String sql="UPDATE NOTE SET note_good='"+userId+"'WHERE note_id='"+noteId+"';";
	    mysqlBase.execute(sql, connection);
	    mysqlBase.close(connection);
	    System.out.println("点赞更新成功");
	}
	/**
	 * 获取该条笔记已有的评论
	 * @param noteId
	 * @return
	 */
	public static String search_note_Comment(int noteId) {
		MysqlBase mysqlBase=new MysqlBase();
		Connection connection=mysqlBase.createConnect();
		String sql="SELECT note_comment FROM note WHERE note_id='"+noteId+"';";
		String OldCmd="";
		ResultSet rSet=mysqlBase.search(sql, connection);
		   try {
			while(rSet.next()) {
				   OldCmd=rSet.toString();
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
	 * @param commend
	 * @param noteId
	 */
	public static void update_note_Comment(String commend,int noteId) {
		MysqlBase mysqlBase=new MysqlBase();
		Connection connection=mysqlBase.createConnect();
		String sql ="UPDATE NOTE SET note_commend ='"+commend+"'WHERE note_id='"+noteId+"';";
		mysqlBase.execute(sql, connection);
		mysqlBase.close(connection);
	}
	}
