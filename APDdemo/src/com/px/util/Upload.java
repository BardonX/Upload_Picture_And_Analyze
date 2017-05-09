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
 * @Description: ͼƬ�ϴ���
 * @author: Bardon
 * @date: 2017��5��3�� ����4:50:44
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
	 * ��ȡͼƬ����
	 * @Title: upload
	 * @Author: Bardon
	 * @Time: 2017��5��3�� ����4:51:43
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
			//�ж��ļ��ϴ�����
			boolean bol=ServletFileUpload.isMultipartContent(req);
			if(bol){
				try {
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				//�����ļ��ϴ�����
				DiskFileItemFactory factory=new DiskFileItemFactory();
				ServletFileUpload upload=new ServletFileUpload(factory);
				//����������
				Iterator<FileItem> items=upload.parseRequest(req).iterator();
				while(items.hasNext()){
					FileItem item=items.next();
					//�ж��Ƿ�Ϊ��ͨ������
					boolean bl = item.isFormField();
					if(bl){
						//��ȡ�ļ���
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
	 * ͬ���Ŀ��÷���
	 * @Title: uploadFiles
	 * @Author: Bardon
	 * @Time: 2017��5��4�� ����8:16:10
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
				//�����ļ��ϴ�����
				DiskFileItemFactory factory=new DiskFileItemFactory();
				ServletFileUpload upload=new ServletFileUpload(factory);
				
				//�����ļ�������
				Iterator items=upload.parseRequest(rep).iterator();
				while(items.hasNext()){
					FileItem item=(FileItem) items.next();
					//�ж�
					boolean ite=item.isFormField();
					if(!ite){
						//�ļ���
						fileName=item.getName();
						//�����ļ��ϴ�Ŀ¼
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
			//�ļ���
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			
		}
		
		
	}

}
