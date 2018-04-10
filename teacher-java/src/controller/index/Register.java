package controller.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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
		String type = request.getParameter("flag");
		System.out.println(type);

		if (type.equals("base")) {
			String username_form = request.getParameter("username");
			String password_form = request.getParameter("password");
			int count = new Users().countUserName(username_form);
			System.out.println(count);
			if (count == 0) {
				boolean flag = new Users().inSert(username_form, password_form);
				Map<String, String> map = new HashMap<String, String>();
				map=new Users().getPasswordFormMysql(username_form);
                
               String id= map.get("id");
                System.out.println(id);
                
                
                
                
				if (flag) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", id);
					jsonObject.put("status", "RegisterSuccess");
					
					out.println(jsonObject.toString());
				} else {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("status", "Registerfail");
					out.println(jsonObject.toString());
				}
			} else {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("status", "userNamExist");
				out.println(jsonObject.toString());
			}

			// response.getWriter().append("Served at:
			// ").append(request.getContextPath());
		}
		else if(type.equals("other")) {
			String sex_form = request.getParameter("sex");
			String birthday_form = request.getParameter("birthday");
			String schoolid_form = request.getParameter("schoolid");
			String email_form = request.getParameter("email");
			String phone_form = request.getParameter("phone");
			String identity_form = request.getParameter("identity");
			String id_form = request.getParameter("id");

		}
		else if(type.equals("onlyIdentity")) {
			String id_form = request.getParameter("id");
			
			String identity_form = request.getParameter("identity");
			System.out.println(identity_form);
			int Identity_form=Integer.parseInt(identity_form);
			
			new Users().update_onlyIdentity(Identity_form, id_form);
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
