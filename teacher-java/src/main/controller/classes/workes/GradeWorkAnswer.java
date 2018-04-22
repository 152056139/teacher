package main.controller.classes.workes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import main.database.StudentWork;

/**
 * Servlet implementation class GradeWorkAnswer
 */
@WebServlet("/GradeWorkAnswer")
public class GradeWorkAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeWorkAnswer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String studentName=request.getParameter("studentName");
		String workId=request.getParameter("workId");
		String workScore=request.getParameter("workScore");
		boolean rSet=StudentWork.updateWorkScore(studentName, workId, workScore);
		JSONObject jsonObject =new JSONObject();
		if(rSet) {
			jsonObject.put("STATU", "success");
		}else {
			jsonObject.put("STATU", "faild");
		}out.print(jsonObject);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
