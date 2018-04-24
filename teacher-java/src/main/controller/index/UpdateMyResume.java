package main.controller.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import main.common.UploadFile;
import main.database.Users;

/**
 * Servlet implementation class UpdateMyResume
 */
@WebServlet("/UpdateMyResume")
public class UpdateMyResume extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateMyResume() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		Map<String, String> map = new HashMap<String, String>();
		JSONObject jsonObject=new JSONObject();
		UploadFile uploadFile = new UploadFile();
		map = uploadFile.uploadFile(request, response);
		String userId=request.getParameter("userId");
		String userImage=map.get("filepath");
		String userSex=request.getParameter("userSex");
		String userBirthday=request.getParameter("birthDay");
		String userMail=request.getParameter("userMail");
		String userPhone=request.getParameter("userPhone");
		jsonObject=Users.updateResume(userId, userSex, userBirthday, userImage, userPhone, userMail);
		out.print(jsonObject);
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
