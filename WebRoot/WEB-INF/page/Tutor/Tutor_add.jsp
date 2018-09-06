<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="Author" content=" ">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<title>教师添加</title>
	<style type="text/css">
		* {
			margin: 0;
			padding: 0;
		}
		
		body {
			font-size: 14px;
			font-family: "微软雅黑";
		}
		
		.add-teacher {
			width: 890px;
			margin: 0 auto;
		}
		
		.add-teacher .container .row span {
			margin: -10px 0 15px 15px;
			display: block;
			width: 95px;
			height: 5px;
			background-color: #5cb85c;
		}
		
		.add-teacher .container .form-btn {
			text-align: center;
		}
		
		.add-teacher .container .form-btn input:nth-child(2) {
			margin-left: 10px;
		}
	</style>
	<link rel="stylesheet" href="<%=basePath%>/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=basePath %>/css/public-scoll.css">
<script>
		function checkForm(){
			var userId =document.getElementById("userId").value;
			var userPassword=document.getElementById("userPassword").value;
			var spanDom = document.getElementById('span1').innerText;
			if(userId==null || userId==''){
				alert("用户名不能空");
				return false;
			}
			if(userPassword==null || userPassword==''){
				alert("密码不能为空！");
				return false;
			}
			if(6 == spanDom.length && "账号已经存在" == spanDom){
				alert('该账号存在，不可强行添加！');
				return false;
			}
		};
		function checkuserId(){
			var userId = document.getElementById("userId").value;
			//1.创建异步校验交互对象
			var xhr =createXmlHttp();
			//2.设置监听
			xhr.onreadystatechange= function(){
				if(xhr.readyState==4){
					if(xhr.status==200){
						document.getElementById("span1").innerHTML = xhr.responseText;
					}
				}
			}
			//3.打开链接
			xhr.open("GET", "${pageContext.request.contextPath}/SysUser_findByUserId.action?time="+new Date().getTime()+"&sysUser.userId="+userId,true);
			//4.发送
			xhr.send(null);
		};
		function createXmlHttp(){
			var xmlHttp;
			try{
				xmlHttp=new XMLHttpRequest();
			}
			catch(e){
				try{
					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
				}
				catch(e){
					try{
					xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
					}
					catch(e){}
				}
			}
			return xmlHttp;
		};
	</script>
</head>
<body>${requestScope.msg}
	<div class="add-teacher">
		<div class="container">
			<div class="row">
				<h3 class="col-sm-12">添加教师</h3>
			</div>
			<div class="row">
				<span></span>
			</div>
			<div>
				<form action="<%=basePath%>Tutor_doAdd.action" method="post"
					onsubmit="return checkForm();">
					<div class="form-group">
						<label for="userId">教师账号</label>
						<input type="text" name="sysUser.userId" class="form-control" onblur="checkuserId();" id="userId" placeholder="请输入教师账号">
						<span id="span1"></span>
					</div>
					<div class="form-group">
						<label for="userName">教师姓名</label>
						<input type="text" name="sysUser.userName" class="form-control" id="userName" placeholder="请输入教师姓名">
					</div>
					<div class="form-group">
						<label for="userPassword">密码</label> 
						<input type="text" name="sysUser.userPassword" class="form-control" id="userPassword" placeholder="请输入教师登录密码">
					</div>
					<div class="form-group">
						<label for="userPhone">联系方式</label> 
						<input type="text" name="sysUser.userPhone" class="form-control" id="userPhone" placeholder="请输入教师联系方式">
					</div>
					<div class="form-group">
						<label>导师性别</label>
						<label class="radio-inline"> 
							<input type="radio" name="sysUser.userSex" checked="checked" value="1">男
						</label> 
						<label class="radio-inline"> 
						    <input type="radio" name="sysUser.userSex" value="0">女
						</label>
					</div>
					<div class="form-group">
						<label for="instituteId">所属院系</label>
						<s:iterator>
							<select id="instituteId" name="sysUser.instituteId"
								class="form-control">
								<option value="">--请选择--</option>
								<s:iterator value="instituteList">
									<option value="${instituteId}">${instituteName}</option>
								</s:iterator>
							</select>
						</s:iterator>
					</div>
					<input type="hidden" name="userType" value="2">
					<div class="form-btn">
						<input class="btn btn-info" type="submit" name="submit" value="提交">
						<input class="btn btn-info" type="reset" name="reset" value="重置">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
