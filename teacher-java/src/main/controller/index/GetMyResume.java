package main.controller.index;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import main.database.Users;

/**
 * Servlet implementation class GetMyResume
 */
@WebServlet("/GetMyResume")
public class GetMyResume extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMyResume() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out =response.getWriter();
		String userId=request.getParameter("userId");
		int userID=Integer.parseInt(userId);
		System.out.println("表单userid     "+userID);
		JSONObject jsonObject=new JSONObject();
		String userName=Users.searchUserName(userID); 
		String userImage=Users.searchUserHead(userID);
		String userSex=Users.searchUserSex(userID);
		String userBirthday=Users.searchUserBirthday(userID);
		String userSchoolId=Users.searchUserSchoolId(userID);
		jsonObject.put("userName", userName);
		jsonObject.put("userImage", userImage);
		jsonObject.put("userSex", userSex);
		jsonObject.put("userBirthday", userBirthday);
		jsonObject.put("userSchoolId", userSchoolId);
		out.print(jsonObject);
		System.out.println("查询成功！");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
