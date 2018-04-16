package main.controller.classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import main.database.Classes;
import main.database.Course;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetCourseList
 */
@WebServlet("/GetCourseList")
public class GetCourseList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetCourseList() {
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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		String teacherid_form = request.getParameter("teacherid");
		// 转化teacherid
		int teaId = Integer.parseInt(teacherid_form);
		Map<Integer, String> map = new HashMap<Integer, String>();
		map = Course.search_course(teaId);
		for(Integer key: map.keySet()) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("courseId", key);
			jsonObject.put("courseName", map.get(key));
			jsonArray.add(jsonObject);
		}
		out.println(jsonArray);
		System.out.println("查到的id，name"+jsonArray);
		System.out.println(teacherid_form);
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
