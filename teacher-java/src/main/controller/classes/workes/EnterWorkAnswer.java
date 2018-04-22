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
@WebServlet("/EnterWorkAnswer")
public class EnterWorkAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnterWorkAnswer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out =response.getWriter();
		String studentName=request.getParameter("studentName");
		String workId=request.getParameter("workId");
		System.out.println("表单姓名          "+studentName);
		System.out.println("表单WORKID "+workId);
		JSONObject jsonObject=StudentWork.searchWorkAnswer(studentName, workId);
		String answerContent=jsonObject.getString("answerContent");
		String answerScore=jsonObject.getString("answerScore");
		if(answerScore.equals("101")) {
			answerScore="gradeplease";
		}
		JSONObject jsonObject2=new JSONObject();
		jsonObject2.put("answerContent", answerContent);
		jsonObject2.put("answerScore", answerScore);
		System.out.println("查的数据"+jsonObject2);
		out.print(jsonObject2);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
