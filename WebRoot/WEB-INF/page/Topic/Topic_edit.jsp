<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>修改题目信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript"
	src="http://apps.bdimg.com/libs/jquery/1.8.0/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#instituteId").change(function(){
		    var url = "<%= basePath%>/Institute_findMajorsListById.action";
		    var instituteId = $("#instituteId").find("option:selected").val();
		    $("#majorId").html('');
		    $.post(url,{"instituteId":instituteId},function(data){
				for(var i = 0; i < data.length; i++){
					$("#majorId").append("<option  value='"+data[i].instituteId+"'>"+data[i].instituteName+"</option>");
				}
			},"json");
		});
	});
</script>
</head>

<body>
	<div class="">
		<h3>修改题目信息</h3>
		<div class="">
			<form action="<%=basePath%>Topic_doEdit.action" method="post">
				<s:hidden name="topic.id"></s:hidden>
				<input type="hidden" name="topic.userId" value="${topic.userId}">
				<input type="hidden" name="topic.topicId" value="${topic.topicId }"><br>
				课题标题<input type="text" name="topic.topicTitle"
					value="${topic.topicTitle }"><br> 课题要求<input
					type="text" name="topic.topicRequest"
					value="${topic.topicRequest }"><br> 课题内容
				<textarea rows="10" cols="50" name="topic.topicContent">${topic.topicContent }</textarea>
				<br> 课题余量<input type="text" name="topic.topicSurplus"
					value="${topic.topicSurplus }"><br> <label
					for="instituteId">院系</label>
				<label>所属学院</label> 
		<select name = "instituteId"  id="instituteId">
	    	<s:iterator value="instituteList" var="list" status="index">
			<option value="${list.instituteId}" <s:if test='#list.instituteId==instituteInfo.institutePid'> selected="selected"</s:if>>	
			${list.instituteName}
			</option>
	       </s:iterator>
	    </select>
	    <br>
   <label>所属专业</label> 
  <select  name="topic.instituteId" id="majorId">
 	<s:iterator value="majorList" var="list">
 		<s:if test='#list.instituteId==topic.instituteId'>
           <option value="${list.instituteId}">${list.instituteName}</option>
		</s:if> 
	</s:iterator>
</select>
<br> <input type="submit" name="btn" value="提交"> <input
					type="reset" name="resbtn" value="重置">
			</form>
		</div>
	</div>
</body>
</html>