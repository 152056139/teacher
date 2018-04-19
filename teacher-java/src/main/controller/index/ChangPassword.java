package main.controller.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import main.common.MysqlBase;
import main.database.Users;

/**
 * Servlet implementation class ChangPassword
 */
@WebServlet("/ChangPassword")
public class ChangPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userid_form=request.getParameter("userid");
		String oldpass_form=request.getParameter("oldpassword");
		String newpass_form=request.getParameter("newpassword");
		int userId =Integer.parseInt(userid_form);
		JSONObject jsonObject=new JSONObject();
		PrintWriter out=response.getWriter();
		Map<String, String> map = new HashMap<String, String>();
		map=Users.oldPassword(userId);
		String oldpass=map.get("password");
		
		if(oldpass.equals(oldpass_form)) {
			Users.update_password(userId, newpass_form);
			jsonObject.put("STATU", "success");
			out.print(jsonObject);
		}else {
			jsonObject.put("STATU", "wrong");
			out.print(jsonObject);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
