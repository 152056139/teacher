package main.controller.course;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import main.database.Student_course;

/**
 * Servlet implementation class JoinCourse
 */
@WebServlet("/JoinCourse")
public class JoinCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out =response.getWriter();
		JSONObject jsonObject=new JSONObject();
		response.setContentType("text/html;charset=UTF-8");
		String courseid_form=request.getParameter("courseid");
		String userid_form=request.getParameter("userid");
		int courseId=Integer.parseInt(courseid_form);
		int userId=Integer.parseInt(userid_form);
		Student_course.insertTeacherCourse(userId, courseId);
		jsonObject.put("STATU", "success");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
