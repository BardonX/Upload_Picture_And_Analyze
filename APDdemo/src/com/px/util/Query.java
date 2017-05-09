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
 * @Description: ������
 * @author: Bardon
 * @date: 2017��5��4�� ����8:10:42
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
	 * ��һ��ͼƬ���ж���ɫ�顢�Ըл��������ķ���
	 * @Title: getResult
	 * @Author: Bardon
	 * @Time: 2017��5��4�� ����8:11:10
	 * @params: @param path
	 * @params: @return
	 * @return: String json���ݣ�Ϊ�������͵ĸ��ʣ�provavility
	 * @throws
	 */
	public static String getResult(String path){
		//��������ʵ��
		AipAntiporn cliAipAntiporn=new AipAntiporn(APP_ID, APP_KEY, SELECT_KEY);
		JSONObject detect = cliAipAntiporn.detect(path);
		//System.out.println(detect.toString());
		return detect.toString();
	}

	public static void main(String[] args) {
		System.out.println(getResult("E:\\�����\\timg.jpg"));
		System.out.println(getResult("E:\\�����\\12.jpg"));
		System.out.println(getResult("E:\\�����\\1.png"));
	}
}
