package controller.index;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Users;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
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
		JSONObject status = new JSONObject();
		
		// 接收表单的注册信息
		String username_form = request.getParameter("username");
		String password_form = request.getParameter("password");
		
		// 判断是否有重名
		int count = new Users().countUserName(username_form);
		System.out.println("register：" + username_form + "在数据库中的个数" + count);

		if (count == 0) {
			boolean flag = new Users().inSert(username_form, password_form);

			if (flag) {
				status.put("status", "RegisterSuccess");
				out.println(status.toString());
			} else {
				status.put("status", "Registerfail");
				out.println(status.toString());
			}
		} else {
			status.put("status", "userNamExist");
			out.println(status.toString());
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
