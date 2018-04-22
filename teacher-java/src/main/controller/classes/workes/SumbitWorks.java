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
 * Servlet implementation class SumbitWorks
 */
@WebServlet("/SumbitWorks")
public class SumbitWorks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SumbitWorks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter();
		JSONObject jsonObject=new JSONObject();
		String StudentidForm=request.getParameter("userId");
		String workidForm=request.getParameter("workId");
		String answerForm=request.getParameter("answer");
		boolean rSet=StudentWork.insertStudentWorks(StudentidForm, workidForm, answerForm);
		if(rSet) {
			jsonObject.put("STATU", "success");
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
