<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>课题详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <label><h4>课题详情</h4></label>
  <body>
	<div></div>
		<div>
			<li>课题编号:${topic.topicId }</li><br>
			<li>课题名称:${topic.topicTitle }</li><br>
			<li>课题要求:${ topic.topicRequest}</li><br>
			<li>课题内容:${topic.topicContent }</li><br>
			<li>下载附件:无<a href=""></a></li><br>

		</div>
  </body>
</html>
			