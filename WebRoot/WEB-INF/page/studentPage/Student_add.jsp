<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="Author" content=" ">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>学生添加</title>
    <style type="text/css">
        *{margin: 0;padding: 0;}
        body{font-size: 14px;font-family: "微软雅黑";}
        .add-student{width:870px;margin:0 auto;}
        .add-student .container .row span{margin: -10px 0 15px 15px;display: block;width: 95px;height: 5px;background-color: #5cb85c;}
    	.add-student .container .form-btn{text-align: center;}
    	.add-student .container .form-btn input:nth-child(2){margin-left: 10px;}
    </style>
    <link rel="stylesheet" href="<%=basePath%>/css/public-scoll.css">
    <link rel="stylesheet" href="<%=basePath%>/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/1.8.0/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/laydate/laydate.js"></script>
	<script type="text/javascript">
		laydate.render({
	 		 elem: '#userBirthday' //指定元素
		});
	</script>
	<script type="text/javascript">
		$(function(){
			$("#instituteId").change(function(){
			    var url = "<%= basePath%>/Institute_findMajorsListById.action";
			    var instituteId = $("#instituteId").find("option:selected").val();
			    $("#majorId option:not(:first)").remove();
			    $.post(url,{"instituteId":instituteId},function(data){
					for(var i = 0; i < data.length; i++){
						$("#majorId").append("<option  value='"+data[i].instituteId+"'>"+data[i].instituteName+"</option>");
					}
				},"json");
			});
		});
		$(function(){
			$("#majorId").change(function(){
			      var url = "<%= basePath%>/Class_findClassListById.action";
			      var majorId = $("#majorId").find("option:selected").val();
			      $("#classId option:not(:first)").remove();
			      $.post(url,{"instituteId":majorId},function(data){
				    for(var i = 0; i < data.length; i++){
			           $("#classId").append("<option  value='"+data[i].classId+"'>"+data[i].className+"</option>");
					}
				},"json");
			});
		});
	</script> 
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
	<div class="add-student">
    	<div class="container">
			<div class="row">
				<h3 class="col-sm-12">添加学生</h3>
			</div>
			<div class="row">
				<span></span>
			</div>
			<div>
				<form action="<%= basePath%>/SysUser_studentAdd.action" method="post" onsubmit="return checkForm();">
					<div class="form-group">
					    <label for="userId">学号</label>
					    <input type="text" name="sysUser.userId" onblur="checkuserId();" class="form-control" id="userId" placeholder="请输入学号">
					    <span id="span1"></span>
					</div>
					<div class="form-group">
					    <label for="userName">学生姓名</label>
					    <input type="text" name="sysUser.userName" class="form-control" id="userName" placeholder="请输入学生姓名">
					</div>
					<div class="form-group">
					    <label for="userPassword">密码</label>
					    <input type="text" name="sysUser.userPassword" class="form-control" id="userPassword" placeholder="请输入学生登录密码">
					</div>
					<div class="form-group">
						<label>学生性别</label>
						<label class="radio-inline">
						     <input type="radio" name="sysUser.userSex" checked="checked" value="1">男
						</label>
						<label class="radio-inline">
						     <input type="radio" name="sysUser.userSex" value="0">女
						</label>
					</div>
					<div class="form-group">
					    <label for="userBirthday">出生日期</label>
					    <input type="text" name="userBirthday" class="form-control" id="userBirthday" placeholder="请输入学生出生日期">
					</div>
					<div class="form-group">
					    <label for="studentScore">平时成绩</label>
					    <input type="text" name="sysUser.studentScore" class="form-control" id="studentScore" placeholder="请输入学生平时成绩">
					</div>
					<div class="form-group">
					    <label for="userNative">籍贯</label>
					    <input type="text" name="sysUser.userNative" class="form-control" id="userNative" placeholder="请输入学生籍贯">
					</div>
			    	<input type="hidden" name="sysUser.userType" value="3">
			    	<div class="form-group">
					    <label for="userPhone">联系方式</label>
					    <input type="text" name="sysUser.userPhone" class="form-control" id="userPhone" placeholder="请输入学生联系方式">
					</div>
					<div class="form-group">
						<label for="instituteId">所属学院</label>
						<s:iterator>
						 	<select id="instituteId" name="instituteId" class="form-control">
						 	     <option selected="selected" value="">请选择学院：</option>
								 <s:iterator value="instituteList" >
					        		  <option value="${instituteId}">${instituteName}</option>
				        	     </s:iterator>
							</select>
						</s:iterator>
					</div>
					<div class="form-group">
						<label for="majorId">所属专业</label>
						<s:iterator>
						 	<select id="majorId" name="sysUser.instituteId" class="form-control">
								  <option selected="selected" value="">请选择专业：</option>
							</select>
						</s:iterator>
					</div>
					<div class="form-group">
						<label for="classId">所属班级</label>
						<s:iterator>
						 	<select name="sysUser.classId" id="classId" class="form-control">
								  <option selected="selected" value="">请选择班级：</option>
							</select>
						</s:iterator>
					</div>
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

