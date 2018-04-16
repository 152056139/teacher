package main.controller.classes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

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
		String notecmd_form=request.getParameter("notecmd");
		String noteid_form =request.getParameter("noteid");
		String userid_form=request.getParameter("userid");
		int noteId=Integer.parseInt(noteid_form);
		String OldCmd=Notes.search_note_Comment(noteId);
		com.alibaba.fastjson.JSONObject jsonObject=new com.alibaba.fastjson.JSONObject();
		jsonObject.put(userid_form, notecmd_form);
		com.alibaba.fastjson.JSONArray jsonArray=new com.alibaba.fastjson.JSONArray();
		jsonArray.add(jsonObject);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
