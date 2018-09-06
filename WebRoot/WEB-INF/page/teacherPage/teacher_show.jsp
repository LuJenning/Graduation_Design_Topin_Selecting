<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>查看个人信息</title>
	<style type="text/css">
		*{margin:0;padding:0;}
		.user-select{
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            -o-user-select: none;
            user-select: none;
        }
		.wrap{width:860px;margin:0 auto;}
		.position{height: 30px;padding-left: 10px;font-size: 15px;line-height: 30px;border-bottom: 1px solid #ccc;margin-bottom: 5px;background-color: #eee;}
        .position ul{margin-bottom:0;list-style:none;}
        .position ul li{float: left;}
        .position ul li:hover{color: #337ab7;}
		.mar-t{margin-top:5px;}
		a{padding:0;}
	</style>
	<link rel="stylesheet" href="<%=basePath %>/css/public-scoll.css">
	<link rel="stylesheet" href="<%=basePath%>/bootstrap-3.3.7-dist/css/bootstrap.min.css">
</head>
<body class="user-select">
	<!-- 个人信息查看及修改  -->
	<div class="wrap">
		<!-- 位置信息 -->
		<div class="position">
	        <ul>
	            <li>学生选题系统&gt;</li>
	            <li>个人信息&gt;</li>
	           
	        </ul>
	    </div>
	    <!-- 位置信息 -->
		<div class="input-group mar-t">
		    <span class="input-group-addon">工号</span>
			<input type="text" class="form-control" value="${sysUser.userId}" readonly>
		</div>
		<div class="input-group mar-t">
		    <span class="input-group-addon">密码</span>
			<input type="text" class="form-control" value="${sysUser.userPassword}" readonly>
		</div>
		<div class="input-group mar-t">
		    <span class="input-group-addon">姓名</span>
			<input type="text" class="form-control" value="${sysUser.userName}" readonly>
		</div>
	    
	    <%-- <div class="input-group mar-t">
	    	<span class="input-group-addon">平时成绩</span>
		    <input type="text" class="form-control" value="${sysUser.studentScore}" readonly>
		</div> --%>
		<div class="input-group mar-t">
	    	<span class="input-group-addon">籍贯</span>
		    <input type="text" class="form-control" value="${sysUser.userNative}" readonly>
		</div>
		
		<div class="input-group mar-t">
	    	<span class="input-group-addon">职称</span>
		    <input type="text" class="form-control" value="${sysUser.teacherTitle}" readonly>
		</div>
	    <c:choose>
	        <c:when test="${1 == sysUser.userSex}">
				<div class="input-group mar-t">
			    	<span class="input-group-addon">性别</span>
				    <input type="text" class="form-control" value="男" readonly>
				</div>
	        </c:when>
	        <c:when test="${0 == sysUser.userSex}">
	        	<div class="input-group mar-t">
			    	<span class="input-group-addon">性别</span>
				    <input type="text" class="form-control" value="女" readonly>
				</div>
	        </c:when>
        </c:choose>
        <div class="input-group mar-t">
	    	<span class="input-group-addon">出生日期</span>
		    <input type="text" class="form-control" value="${sysUser.userBirthday}" readonly>
		</div>
        <div class="input-group mar-t">
	    	<span class="input-group-addon">联系方式</span>
		    <input type="text" class="form-control" value="${sysUser.userPhone}" readonly>
		</div>
        <s:iterator value="instituteList" var="list" status="index">
			<s:if test='#list.instituteId==sysUser.instituteId'>
			  	<div class="input-group mar-t">
			    	<span class="input-group-addon">所属学院</span>
			    	<input type="text" class="form-control" value="${list.instituteName}" readonly>
			  	</div>
			</s:if> 
	  	</s:iterator>
		<div class="mar-t">
			<a href="SysUser_openStudentEdit.action?id=${sysUser.id}">
				<button type="button" class="btn btn-info">修改个人信息</button>
			</a>
		</div>
	</div>
	<!-- 个人信息查看及修改 -->
</body>
</html>
