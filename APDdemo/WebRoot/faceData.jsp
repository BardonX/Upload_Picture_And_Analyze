
<%@page import="com.px.util.Query"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%


 response.setCharacterEncoding("utf-8");
 String path=request.getParameter("path");
 String pathName=request.getRealPath(path);
 
 String msg=Query.getResult(pathName);
 out.print(msg);
 
%>

