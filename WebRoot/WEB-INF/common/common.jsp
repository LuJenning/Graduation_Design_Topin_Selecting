<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


//百度地图的密钥
String BAIDU_MAP_KEY = "1iVjCXdjZs2GyNlBXUczpmUV";
double start_longitude = 111.285575;//初始化经度
double start_latitude = 23.482807;//初始化经度
String currentLocation = "梧州市政府";      //初始化地图中心点地址
String currentCity = "梧州市";
%>