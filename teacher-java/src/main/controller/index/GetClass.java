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
		if (userIdentity == 1) {
			list = Course.searchCourseId(userId);
		} // 老师教哪些课
		else if(userIdentity==0) {
			list=Course.searchStudentCourseId(userId);
		}

		// 获得courseid
		for (Integer all_courseid : list) {
			List<Integer> listClassId = new ArrayList<Integer>();

			System.out.println("用户courseid " + all_courseid);
			listClassId = Classes.searchClassId(all_courseid, date_form, nextDay);
			if (!listClassId.isEmpty()) {
				for (Integer classid : listClassId) {
					JSONObject jsonObject = new JSONObject();
					System.out.println("用户classid " + classid);
					int courseId = Classes.searchCourseId(classid);

					System.out.println("用户courseid " + courseId);
					String courseName = Course.searchCourseName(courseId);// 课程名
					String teacherName = "无结果";
					if (userIdentity == 1) {// 教师端的teachername就是用户自己identity0是学生1是老师
						teacherName = Users.searchUserName(userId);
					} else if (userIdentity == 0) {
						int teacherId = Course.searchTeacherId(courseId);
						teacherName = Users.searchTeacherName(teacherId);
					}
					String classRoom = Classes.searchClassRoom(classid);

					// 处理上课时间
					String classTime = Classes.searchClassTime(classid);
					String str = classTime;
					String arr[] = str.split(" ");
					String time = arr[1];
					String arr2[] = time.split(":");
					String hour = arr2[0];
					String min = arr2[1];
					String t = hour + ":" + min + "-" + (Integer.parseInt(hour) + 2) + ":" + min;

					String courseImage = Course.searchCourseImage(courseId);
					System.out.println("课程名称    " + courseName);
					System.out.println("课程图片    " + courseImage);
					System.out.println("上课教师    " + teacherName);
					System.out.println("上课教师    " + classRoom);
					System.out.println("上课时间    " + t);

					System.out.println("=============================");
					jsonObject.put("courseName", courseName);
					jsonObject.put("teacherName", teacherName);
					jsonObject.put("classRoom", classRoom);
					jsonObject.put("classTime", t);
					jsonObject.put("courseImage", courseImage);
					jsonArray.add(jsonObject);
				}
			}
		}
		out.print(jsonArray);

	}

}
