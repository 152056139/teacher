package main.controller.classes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.database.Classes;

/**
 * Servlet implementation class GetTeasay
 */
@WebServlet("/SetTeasay")
public class SetTeasay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetTeasay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		String teachersay_form = request.getParameter("teachersay");
		String classid_form = request.getParameter("classid");
		//转化classid
		int claId = Integer.parseInt(classid_form);
		String sql="UPDATE CLASS SET teacher_say='"+teachersay_form+"' "
				  +"WHERE class_id='"+claId+"';";
		//调用基类方法
		Classes.updateClass( sql);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
