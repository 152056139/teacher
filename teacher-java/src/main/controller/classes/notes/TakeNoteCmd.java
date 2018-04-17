package main.controller.classes.notes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import main.database.Notes;

/**
 * Servlet implementation class TakeNoteCmd
 */
@WebServlet("/TakeNoteCmd")
public class TakeNoteCmd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeNoteCmd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 丛表单获取笔记内容， 笔记id， userid
		String notecmd_form=request.getParameter("notecmd");
		String noteid_form =request.getParameter("noteid");
		String userid_form=request.getParameter("userid");
		String isgood_form=request.getParameter("isgood");
		int noteId=Integer.parseInt(noteid_form);
		
		String OldCmd=Notes.search_note_Comment(noteId);
		
		com.alibaba.fastjson.JSONObject jsonObject=new com.alibaba.fastjson.JSONObject();
		jsonObject.put("userId", userid_form);
		jsonObject.put("noteCmd", notecmd_form);
		jsonObject.put("isGood",isgood_form);
		com.alibaba.fastjson.JSONArray jsonArray=new com.alibaba.fastjson.JSONArray();
		jsonArray.add(jsonObject);
		System.out.println(jsonArray);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
