package main.controller.classes.notes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import main.common.DownloadFile;
import main.common.MysqlBase;
import main.database.Notes;
import main.database.Users;

/**
 * Servlet implementation class GetNotes
 */
@WebServlet("/GetNotes")
public class GetNotes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetNotes() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String classid_form = request.getParameter("classid");
		int classId = Integer.parseInt(classid_form);
		JSONArray jsonArray = Notes.searchNoteid(classId);
		JSONArray jsonArray2 = new JSONArray();

		for (int i = 0; i < jsonArray.size(); i++) {

			

			JSONObject jsonObject = new JSONObject();
			JSONObject jsonObject2 = new JSONObject();
			JSONArray jsonArray3 = new JSONArray();
			jsonObject = (JSONObject) jsonArray.get(i);
			String userid = jsonObject.getString("userId");
			String noteId = jsonObject.getString("noteId");
			String noteContent = jsonObject.getString("noteContent");// 笔记内容
			String noteTime = jsonObject.getString("noteTime");// 笔记发布时间
			String noteCmd = jsonObject.getString("noteCmd");// 笔记评论
			String noteImage = jsonObject.getString("noteImage");// 笔记图片
			// 根据userid获取身份、用户名、用户头像
			int userId = Integer.parseInt(userid);
			String userName = Users.searchUserName(userId);// 用户名
			String userIdentity = Users.searchUserIdentity(userId);// 用户身份
			String userHeadPath = Users.searchUserHead(userId);// 用户头像
			jsonArray3 = JSON.parseArray(noteImage);
			// 编辑头像图片路径

			// 发送
			jsonObject2.put("userName", userName);
			jsonObject2.put("useIdentity", userIdentity);
			jsonObject2.put("userHeadPath", userHeadPath);
			jsonObject2.put("noteContent", noteContent);
			jsonObject2.put("noteTime", noteTime);
			jsonObject2.put("noteCmd", noteCmd);
			jsonObject2.put("noteImage", jsonArray3);
			jsonArray2.add(jsonObject2);
		}
		out.println(jsonArray2);
		System.out.println("success!");

	}

}
