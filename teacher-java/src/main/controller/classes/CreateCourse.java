package main.controller.classes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.database.*;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class CreatClass
 */
@WebServlet("/CreateCourse")
public class CreateCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject status = new JSONObject();
		
		String coursename_form=request.getParameter("course_name");
		String username_id=request.getParameter("user_id");
		System.out.println("收到表单创建课程名："+coursename_form);

		System.out.println("收到表单id："+username_id);
		
		Course course=new Course();
		int id=Integer.parseInt(username_id);
		//调用方法
		boolean flag= course.inSert(coursename_form, id);
		System.out.println("create返回"+flag);
		if(flag) {
			System.out.println("创建成功！");
			status.put("status", "success");
			out.println(status.toString());
		}
		else {
			System.out.println("创建失败！");
			status.put("status", "fail");
			out.println(status.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
