package com.px.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 * 
 * @ClassName: Upload
 * @Description: 图片上传类
 * @author: Bardon
 * @date: 2017年5月3日 下午4:50:44
 * @version1.0
 */
public class Upload extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String fileName=upload(req, resp);
		HttpSession session=req.getSession();
		if(fileName!=null){
			session.setAttribute("fileName", fileName);
			resp.sendRedirect("index.jsp");
		}
	}
	
	/**
	 * 获取图片名称
	 * @Title: upload
	 * @Author: Bardon
	 * @Time: 2017年5月3日 下午4:51:43
	 * @params: @param req
	 * @params: @param resp
	 * @params: @return
	 * @return: String
	 * @throws
	 */
	public static String upload(HttpServletRequest req, HttpServletResponse resp){
		String fileName= null;
		File uploadFile=null;
		try {
			//判断文件上次类型
			boolean bol=ServletFileUpload.isMultipartContent(req);
			if(bol){
				try {
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				//构建文件上传对象
				DiskFileItemFactory factory=new DiskFileItemFactory();
				ServletFileUpload upload=new ServletFileUpload(factory);
				//创建迭代器
				Iterator<FileItem> items=upload.parseRequest(req).iterator();
				while(items.hasNext()){
					FileItem item=items.next();
					//判断是否为普通表单类型
					boolean bl = item.isFormField();
					if(bl){
						//获取文件名
						fileName =item.getName();
						String filepath=req.getRealPath("upload");
						File file=new File(filepath);
						if(!file.exists()){
							file.mkdirs();
						}
						uploadFile=new File(filepath+"\\"+fileName);
						item.write(uploadFile);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	    System.out.println(fileName);	
		return fileName;
	}
	/**
	 * 同样的可用方法
	 * @Title: uploadFiles
	 * @Author: Bardon
	 * @Time: 2017年5月4日 上午8:16:10
	 * @params: @param rep
	 * @params: @param resp
	 * @params: @return
	 * @return: String
	 * @throws
	 */
	public static String uploadFiles(HttpServletRequest rep,HttpServletResponse resp){
		String fileName=null;
		//FileItem item=null;
		try {
			rep.setCharacterEncoding("utf-8");
			boolean bool = ServletFileUpload.isMultipartContent(rep);
			if(bool){
				//构建文件上传对象
				DiskFileItemFactory factory=new DiskFileItemFactory();
				ServletFileUpload upload=new ServletFileUpload(factory);
				
				//创建文件迭代器
				Iterator items=upload.parseRequest(rep).iterator();
				while(items.hasNext()){
					FileItem item=(FileItem) items.next();
					//判断
					boolean ite=item.isFormField();
					if(!ite){
						//文件名
						fileName=item.getName();
						//定义文件上传目录
						String filepath=rep.getRealPath("upload");
						File file=new File(filepath);
						if(!file.exists()){
							file.mkdirs();
						}
						File uploadFile=new File(filepath+"\\"+fileName);
						item.write(uploadFile);
					}
				}
			}
			System.out.println(fileName);
			//文件名
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			
		}
		
		
	}

}
