package main.controller.classes.notes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import main.common.UploadFile;
import main.database.Notes;

/**
 * Servlet implementation class TakeNoteImage
 */
@WebServlet("/TakeNoteImage")
public class TakeNoteImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeNoteImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map<String, String> map = new HashMap<String, String>();
		UploadFile uploadFile = new UploadFile();
		map = uploadFile.uploadFile(request, response);
		String path_form = map.get("filepath");
		//获取笔记id
		String noteid=map.get("noteid");
		int noteId=Integer.parseInt(noteid);
		//获取该id下已有的图片
		String oldImage=Notes.serchNoteImage(noteId);
		System.out.println("aaa"+oldImage);
		//将图片路径解析为jsonarry
		com.alibaba.fastjson.JSONArray jsonArray = (com.alibaba.fastjson.JSONArray) JSON.parse(oldImage);
		//加入新的图片路径
		jsonArray.add(path_form);
		Notes.updateNoteImage(jsonArray.toJSONString(), noteId);
	}

}
