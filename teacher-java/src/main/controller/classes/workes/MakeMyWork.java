package main.controller.classes.workes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils.Null;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import main.database.Works;

/**
 * Servlet implementation class MakeMyWork
 */
@WebServlet("/MakeMyWork")
public class MakeMyWork extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MakeMyWork() {
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

		String workId = request.getParameter("workId");
		String studentId = request.getParameter("studentId");
		JSONObject jsonObject = Works.searchWorkTitle(workId, studentId);
		String workTitle = jsonObject.getString("workTitle");
		String workDescription = jsonObject.getString("workDescription");
		String answerContent = jsonObject.getString("answerContent");
		String isHas=jsonObject.getString("isHas");
		JSONObject jsonObject2 = new JSONObject();
            
            System.out.println(isHas);
		if (isHas.equals("false")) {
			answerContent = " ";

			System.out.println("空");

		}
		jsonObject2.put("workTitle", workTitle);
		jsonObject2.put("workDescription", workDescription);
		jsonObject2.put("answerContent", answerContent);
		System.out.println("非空");
		out.print(jsonObject2);

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
