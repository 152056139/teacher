package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.MysqlBase;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");

		String username_form = request.getParameter("username");
		String password_form = request.getParameter("password");
		System.out.println(username_form + " " + password_form);

		MysqlBase mysqlBase = new MysqlBase();
		Connection connection = mysqlBase.createConnect();
		ResultSet resultset = mysqlBase
				.search("select user_password from user where user_name = '" + username_form + "';", connection);

		String password = "";
		try {
			while (resultset.next()) {
				// 通过字段检索
				password = resultset.getString("user_password");
				System.out.println("数据库中获取的密码：" + password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("表单传过来的密码：" + password_form);
		if (password_form.equals(password)) {
			System.out.println("登陆成功 ");
			// 设置响应内容类型
			response.setContentType("text/html");

			// 实际的逻辑是在这里
			PrintWriter out = response.getWriter();
			out.println("{\"sites\":\"登陆吃吃吃吃吃吃吃\"}");
		} else {
			System.out.println("登陆失败");
		}
		mysqlBase.close(connection);
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
