
<%@page import="com.px.util.Upload"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String filename=Upload.uploadFiles(request, response);
if(null !=filename){
	
	session.setAttribute("fileName", "upload/"+filename);
	response.sendRedirect("face.jsp");
}
%>


