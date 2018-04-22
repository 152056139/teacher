package main.controller.classes.workes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import main.database.Works;

/**
 * Servlet implementation class CreateWorks
 */
@WebServlet("/CreateWorks")
public class CreateWorks extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateWorks() {
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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String useridForm = request.getParameter("userId");
		String classidForm = request.getParameter("classId");
		String worktitleForm = request.getParameter("workTitle");
		String workDescriptionForm = request.getParameter("workDescription");
		String workbegintimeForm = request.getParameter("workBeginTime");
		String workendtimeForm = request.getParameter("workEndTime");
		boolean rSet = false;
		System.out.println("学号   "+useridForm);
		System.out.println("classid   "+classidForm);
		System.out.println("学号   "+worktitleForm);
		System.out.println("学号   "+workDescriptionForm);
		System.out.println("学号   "+workbegintimeForm);
		System.out.println("学号   "+workendtimeForm);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("classId", classidForm);
		jsonObject.put("workTitle", worktitleForm);
		jsonObject.put("workDescription", workDescriptionForm);
		jsonObject.put("workBeginTime", workbegintimeForm);
		jsonObject.put("workEndTime", workendtimeForm);
		rSet = Works.insertWorks(jsonObject);
		if (rSet) {
			jsonObject.clear();
			jsonObject.put("STATU", "success");
		}out.print(jsonObject);

	}

}
