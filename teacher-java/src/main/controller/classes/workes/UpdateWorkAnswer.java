package main.controller.classes.workes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import main.database.StudentWork;

/**
 * Servlet implementation class UpdateWorkAnswer
 */
@WebServlet("/UpdateWorkAnswer")
public class UpdateWorkAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateWorkAnswer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("studentId");
		String workId = request.getParameter("worId");
		String newAnswer = request.getParameter("newAnswer");
		boolean rSet = StudentWork.updateWorkAnswer(userId, workId, newAnswer);
		JSONObject jsonObject = new JSONObject();
		if (rSet) {
			jsonObject.put("STATU", "success");
		} else {
			jsonObject.put("STATU", "faild");
		}
		out.print(jsonObject);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
