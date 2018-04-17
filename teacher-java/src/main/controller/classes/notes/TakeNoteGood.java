package main.controller.classes.notes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import main.database.Notes;

/**
 * Servlet implementation class TakeNoteGood
 */
@WebServlet("/TakeNoteGood")
public class TakeNoteGood extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeNoteGood() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**更新点赞的次数
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userid_form=request.getParameter("userid");
		String noteid_form=request.getParameter("noteid");
		int noteId=Integer.parseInt(noteid_form);
		
		String oldGood=Notes.search_note_good(noteId);
		System.out.println("数据库获取"+oldGood);
		
		com.alibaba.fastjson.JSONArray jsonArray = (com.alibaba.fastjson.JSONArray) JSON.parse(oldGood);
		jsonArray.add(userid_form);
		Notes.update_note_good(jsonArray.toJSONString(), noteId);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
