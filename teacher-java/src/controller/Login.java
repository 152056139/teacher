package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import database.*;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String username_form = request.getParameter("username");
		String password_form = request.getParameter("password");
		System.out.println("username:" + username_form + " password:" + password_form);

		// create user object
		Users user = new Users();
		String password="";
		String id = "";
		Map<String,String> map=new HashMap<String,String>();
		 map = user.getPasswordFormMysql(username_form);
		 password = map.get("password");
		 id = map.get("id");
		 System.out.println("数据库中搜索到id="+id);
		 
		 
		 
		 
		System.out.println("从数据库中搜到的密码:" + password);

		System.out.println("表单传过来的密码：" + password_form);
		if (password_form.equals(password)) {
			// console log
			System.out.println("登陆成功 ");

			// response wx,return login success
			JSONObject success = new JSONObject();
			success.put("status", "success");
			success.put("id", id);
			out.println(success.toString());
		} else if (password.isEmpty()) {
			// console log
			System.out.println("用户不存在");
			// response wx,return login fail
			JSONObject fail = new JSONObject();
			fail.put("status", "not found user");
			out.println(fail.toString());
		} else {
			// console log
			System.out.println("密码错误");
			// response wx,return login fail
			JSONObject fail = new JSONObject();
			fail.put("status", "wrong password");
			out.println(fail.toString());
		}
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
