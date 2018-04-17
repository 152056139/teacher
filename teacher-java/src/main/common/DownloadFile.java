package main.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

public class DownloadFile {
	/**
	 * 下载文件
	 * 
	 * @param request
	 * @param response
	 */
	public static void uploadfile(HttpServletRequest request, HttpServletResponse response, String paths) {
		// 将传过来的图片json字符串解析成jsonarray
		JSONArray jsonArray = (JSONArray) JSONArray.parse(paths);
		// 循环拿到jsonarray的每个路径
		for (int i = 0; i < jsonArray.size(); i++) {
			String path = jsonArray.getString(i);
			// 创建文件对象
			String uploadPath = request.getServletContext().getRealPath(".") + File.separator + "upload";
			File f = new File(uploadPath + path);
			if (f.exists()) {
				FileInputStream fis;
				try {
					fis = new FileInputStream(f);
					String filename = URLEncoder.encode(f.getName(), "utf-8"); // 解决中文文件名下载后乱码的问题
					byte[] b = new byte[fis.available()];
					fis.read(b);
					response.setCharacterEncoding("utf-8");
					response.setHeader("Content-Disposition", "attachment; filename=" + filename + "");
					// 获取响应报文输出流对象
					ServletOutputStream out = response.getOutputStream();
					// 输出
					out.write(b);

					out.flush();
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
