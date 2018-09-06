<%@ page language="java" import="java.util.*,com.gdts.selecting.util.ResourceUtil" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//request.getRequestDispatcher("/Login_openLogin.action").forward(request, response);
response.sendRedirect(basePath+"/Login_openLogin.action");
%>
