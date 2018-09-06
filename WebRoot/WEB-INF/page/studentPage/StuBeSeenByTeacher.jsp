<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
 <label> <h4>学生简介</h4></label>
  <div>
	<li>学生姓名:${sysUser.userName }</li><br>
    <li>学生学号:${sysUser.userId }</li><br>
	<li>学生性别:<s:if test="sysUser.userSex==1">男</s:if>
	<s:else>女</s:else></li><br>
	<li>联系电话:${sysUser.userPhone }</li><br>
	<li>平时成绩:${sysUser.studentScore }</li><br>
<%-- 	<li>所属学院:${instituteInfo.instituteName }</li><br> --%>
	<li>所属专业:${instituteInfo.instituteName }</li><br>
	<li>所属班级:${classInfo.className }</li><br>
	</div>
  </body>
</html>
