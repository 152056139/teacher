package main.controller.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import main.database.Classes;
import main.database.Course;
import main.database.Users;

/**
 * Servlet implementation class GetClass
 */
@WebServlet("/GetClass")
public class GetClass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetClass() {
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
		PrintWriter out = response.getWriter();
		List<Integer> list = new ArrayList<Integer>();
		String userid_form = request.getParameter("userid");
		String useridentity_form = request.getParameter("useridentity");
		String date_form = request.getParameter("date");
		int userId = Integer.parseInt(userid_form);
		int userIdentity = Integer.parseInt(useridentity_form);
		JSONArray jsonArray = new JSONArray();
		// 获取时间区间（一天内）
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		java.util.Date date = null;
		try {
			date = dFormat.parse(date_form + " 23:59");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timestamp tommrow = new Timestamp(date.getTime());
		String nextDay = tommrow.toString();
		System.out.println();

		list = Course.search_courseid(userId);// 老师教哪些课
		// 获得courseid
		for (Integer all_courseid : list) {
			
			JSONObject jsonObject=new JSONObject();
			System.out.println("用户courseid " + all_courseid);
			int classid = Classes.search_classid(all_courseid, date_form, nextDay);
			System.out.println("用户classid " + classid);
			int courseId = Classes.search_courseid(classid);
			System.out.println("用户courseid " + courseId);
			String courseName = Course.search_coursename(courseId);// 课程名
			String teacherName="无结果";
			if (userIdentity == 1) {// 教师端的teachername就是用户自己identity0是学生1是老师
				 teacherName = Users.search_user_name(userId);
			} else if(userIdentity==0) {
				int teacherId=Course.searchTeacherId(courseId);
				 teacherName=Users.searchTeacherName(teacherId);
			}
			String classRoom = Classes.search_classroom(classid);
			String classTime = Classes.search_classtime(classid);
			String courseImage = Course.searchCourseImage(courseId);
			System.out.println("课程名称    " + courseName);
			System.out.println("课程图片    " + courseImage);
			System.out.println("上课教师    " + teacherName);
			System.out.println("上课教师    " + classRoom);
			System.out.println("上课时间    " + classTime);

			System.out.println("=============================");
			jsonObject.put("courseName", courseName);
			jsonObject.put("teacherName", teacherName);
			jsonObject.put("classRoom", classRoom);
			jsonObject.put("classTime", classTime);
			jsonObject.put("courseImage", courseImage);
			jsonArray.add(jsonObject);
		}out.print(jsonArray);

	}

}
