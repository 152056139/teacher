package main.controller.classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import main.database.Notes;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class TakeNotes
 */
@WebServlet("/TakeNotes")
public class TakeNotes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeNotes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//创建笔记 插入用户id，课堂id，笔记正文，生成默认笔记id和默认发布时间
		PrintWriter out = response.getWriter();
		String userid_form=request.getParameter("userid");
		String classid_form=request.getParameter("classid");
		String notetext_form=request.getParameter("notetext");
		int userId = Integer.parseInt(userid_form);
		int corId = Integer.parseInt(classid_form);
		int noteId=0;
		try {
			noteId=Notes.insert_note(userId, corId, notetext_form);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("noteId",noteId );
		out.print(jsonObject);
		
	}

	/**更新笔记图片
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//获取图片
		
	}

}
