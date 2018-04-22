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

import main.database.Classes;
import main.database.StudentWork;
import main.database.Works;

/**
 * Servlet implementation class GetWhoWork
 */
@WebServlet("/GetWhoWork")
public class GetWhoWork extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetWhoWork() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		String workidForm=request.getParameter("workId");
		String classidForm=request.getParameter("classId");
		String courseidForm=Classes.searchCourseByClassid(classidForm);
		JSONArray jsonArray=new JSONArray();//总数组
		JSONObject jsonObject=Works.searchWhoWork(workidForm);//作业信息
		JSONArray subMited=StudentWork.searchSubmited(courseidForm, workidForm);//已完成列表
		jsonObject.put("已完成", subMited);
		JSONArray unsubMited=StudentWork.searchUnsubmited(courseidForm, workidForm);
		jsonObject.put("未完成", unsubMited);
		jsonArray.add(jsonObject);
		out.print(jsonArray);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
