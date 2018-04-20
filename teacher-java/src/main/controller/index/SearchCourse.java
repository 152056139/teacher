package main.controller.index;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		String conditionsForm=request.getParameter("conditions");//courseid,coursename,teachername,
		PrintWriter out =response.getWriter();
		//courseid,coursename,courseimage,teachername,coursestatu 1 over
		
		JSONArray jsonArray=new JSONArray();
		
			//用json接受
			
			
			jsonArray=Course.SearchCourseByConditions(conditionsForm);
			out.print(jsonArray);
			System.out.println(jsonArray);
		
	}

}
