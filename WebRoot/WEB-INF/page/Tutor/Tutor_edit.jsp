<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>

<!DOCTYPE HTML PUBLIC>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>修改教师</title>
  </head>
  <body>
  	<div><h3>修改教师</h3></div>
  		<div>
  			<form action="<%=basePath %>Tutor_doEdit.action" method="post">
  				<input type="hidden" name="sysUser.id" value=${sysUser.id }><br>
  				<input type="hidden" name="sysUser.userType" value=${sysUser.userType }>
  				导师账号<input type="text" name="sysUser.userId" value=${sysUser.userId }><br>
  				导师名字<input type="text" name="sysUser.userName" value=${sysUser.userName }><br>
  				导师密码<input type="password" name="sysUser.userPassword" value=${sysUser.userPassword }><br>
  				导师性别&nbsp;&nbsp;男<input type="radio" name="sysUser.userSex" value="1" checked="checked">&nbsp;女<input type="radio" name="sysUser.userSex" value="0"><br>
  				所属院系<s:iterator><select name="sysUser.instituteId">
						<option value="">--请选择--</option>
						<s:iterator value="instituteList">
							<option value="${instituteId}">${instituteName}</option>
						</s:iterator>
				</select></s:iterator><br>
   <input type="submit" name="button" value="提交">
  			</form> 
  		</div>
  </body>
</html>
