package main.controller.classes.workes;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import main.database.StudentWork;
import main.database.Works;

/**
 * Servlet implementation class GetWorkList
 */
@WebServlet("/GetWorkList")
public class GetWorkList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetWorkList() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String useridForm = request.getParameter("userId");
		String classidForm = request.getParameter("classId");
		JSONArray jsonArray = new JSONArray();
		JSONArray jsonArray2 = new JSONArray();
		// 查询作业的信息
		jsonArray = Works.searchWorks(classidForm);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			JSONObject jsonObject2 = new JSONObject();
			jsonObject = (JSONObject) jsonArray.get(i);
			String workId = jsonObject.getString("workId");
			String workTitle = jsonObject.getString("workTitle");
			String workDescription = jsonObject.getString("workDescription");
			String workBeginTime = jsonObject.getString("workBeginTime");
			String workEndTime = jsonObject.getString("workEndTime");
			String workStatu = "unknown";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date endDate = null;
			Date nowDate=null;
			try {
				endDate = simpleDateFormat.parse(workEndTime);
				System.out.println("endDate  "+endDate);
				nowDate=simpleDateFormat.parse(simpleDateFormat.format(new Date()));
				 System.out.println("nowDate  "+nowDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			long endTime = endDate.getTime();
			long nowTime = nowDate.getTime();
			System.out.println("时间戳endtime    "+endTime);
			System.out.println("时间戳 nowtime   "+nowTime);
			if (nowTime >= endTime) {//已截止
				System.out.println("已截止");
				boolean isSubmit = StudentWork.searchSubmit(useridForm,workId);
				if (isSubmit) {
					workStatu = "评价中";
				} else {
					workStatu = "已截止";
				}
			} else if (nowTime < endTime) {//进行中
				System.out.println("进行中");
				Boolean isSumbit = StudentWork.searchSubmit(useridForm,workId);
				if (isSumbit) {
					workStatu = "已提交";
				} else {
					workStatu = "未提交";
				}
			}
			System.out.println("workID:"+workId);
			System.out.println("题目         :"+workTitle);
			System.out.println("内容         :"+workDescription);
			System.out.println("结束时间  :"+workEndTime);
			System.out.println("作业状态  :"+workStatu);
			System.out.println("==========================");
			jsonObject2.put("workId", workId);
			jsonObject2.put("workTitle", workTitle);
			jsonObject2.put("workDescription", workDescription);
			jsonObject2.put("workEndTime", workEndTime);
			jsonObject2.put("workStatu", workStatu);
			jsonArray2.add(jsonObject2);
		}

		out.print(jsonArray2);

	}

}
