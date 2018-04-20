package main.controller.course;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.jasper.tagplugins.jstl.core.Out;

import com.alibaba.fastjson.JSONArray;

import main.database.Course;
import main.database.Users;

/**
 * Servlet implementation class SearchCourse
 */
@WebServlet("/SearchCourse")
public class SearchCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchCourse() {
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
		String conditionsForm = request.getParameter("conditions");// courseid,coursename,teachername,
		String useridForm=request.getParameter("userid");
		PrintWriter out = response.getWriter();
		if (!conditionsForm.equals("")) {
			
			JSONArray jsonArray = new JSONArray();

			jsonArray = Course.SearchCourseByConditions(conditionsForm,useridForm);
			out.print(jsonArray);
			System.out.println(jsonArray);
		}
	}

}
