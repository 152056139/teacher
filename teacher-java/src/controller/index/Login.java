package controller.index;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String username_form = request.getParameter("username");
		String password_form = request.getParameter("password");
		String js_code = request.getParameter("jscode");
		System.out.println("username:" + username_form + " password:" + password_form + "code:" + js_code);

		// 给微信发http请求
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx6a9f4a2764223f63&secret=fed750aeb0b275ac935abc24563f5507&js_code="
				+ js_code + "&grant_type=authorization_code";
		String result = "";
		try {
			URL httpUrl = new URL(url);
			URLConnection connection = httpUrl.openConnection();
			InputStream in = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(in, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
			isr.close();
			in.close();
			result = sb.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		System.out.println(result);
		
		// create user object
		Users user = new Users();
		String password = "";
		String id = "";
		Map<String, String> map = new HashMap<String, String>();
		map = user.getPassword(username_form);
		password = map.get("password");
		id = map.get("id");
		System.out.println("数据库中搜索到id=" + id);

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
