package com.px.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import com.baidu.aip.antiporn.AipAntiporn;
/**
 * 
 * @ClassName: Query
 * @Description: 控制器
 * @author: Bardon
 * @date: 2017年5月4日 上午8:10:42
 * @version1.0
 */
public class Query extends HttpServlet{

	private static final long serialVersionUID = 1L;
    public static final String APP_ID="9389343";
    public static final String APP_KEY="9FfcAkwQ9pBWgYO4vmfdOf31";
    public static final String SELECT_KEY="XQMyp7ZYp1D99XCrzPxB7bTjPEOSGYwr";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = req.getParameter("path");
		String filepath=req.getRealPath(path);
		String result=getResult(filepath);
		resp.getWriter().print(result);
	}
	
	/**
	 * 传一张图片，判断是色情、性感还是正常的方法
	 * @Title: getResult
	 * @Author: Bardon
	 * @Time: 2017年5月4日 上午8:11:10
	 * @params: @param path
	 * @params: @return
	 * @return: String json数据，为三种类型的概率：provavility
	 * @throws
	 */
	public static String getResult(String path){
		//创建对象实例
		AipAntiporn cliAipAntiporn=new AipAntiporn(APP_ID, APP_KEY, SELECT_KEY);
		JSONObject detect = cliAipAntiporn.detect(path);
		//System.out.println(detect.toString());
		return detect.toString();
	}

	public static void main(String[] args) {
		System.out.println(getResult("E:\\起风了\\timg.jpg"));
		System.out.println(getResult("E:\\起风了\\12.jpg"));
		System.out.println(getResult("E:\\起风了\\1.png"));
	}
}
