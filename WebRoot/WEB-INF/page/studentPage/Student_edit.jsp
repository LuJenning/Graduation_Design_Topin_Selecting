<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>My JSP 'Student_add.jsp' starting page</title>
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
		.position{height: 30px;padding-left: 10px;font-size: 15px;line-height: 30px;border-bottom: 1px solid #ccc;margin-bottom: 10px;background-color: #eee;}
        .position ul{margin-bottom:0;list-style:none;}
        .position ul li{float: left;}
        .position ul li:hover{color: #337ab7;}
		.mar-t{margin-top:10px;}
		a{padding:0;}
	</style>
	<link rel="stylesheet" href="<%=basePath %>/css/public-scoll.css">
	<link rel="stylesheet" href="<%=basePath%>/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<script src="<%=basePath%>js/jquery/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/laydate/laydate.js"></script>
	<script type="text/javascript">
		$(function(){
			laydate.render({
		 		 elem: '#birthday' //指定元素
			});
		});
	</script>
	<script type="text/javascript">
	<%-- $(function(){
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
	}); --%>
	<%-- $(function(){
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
});--%>
</script> 
  </head>
  <body class="user-select">
  <!-- 个人信息修改  -->
	<div class="wrap">
		<!-- 位置信息 -->
		<div class="position">
	        <ul>
	            <li>学生选题系统&gt;</li>
	            <li>个人信息&gt;</li>
	            <li>个人信息修改&gt;</li>
	        </ul>
	    </div>
	    <!-- 位置信息 -->
	    <form action="<%= basePath%>SysUser_studentEdit.action" method="post">
    		<input name="sysUser.id"  value="${sysUser.id}" type="hidden"/>
    		<input name="sysUser.userId"  value="${sysUser.userId}" type="hidden"/>
			<div class="input-group mar-t">
			    <span class="input-group-addon">学生姓名</span>
				<input type="text" class="form-control" name="sysUser.userName" value="${sysUser.userName}">
			</div>
			<div class="input-group mar-t">
			    <span class="input-group-addon">学生密码</span>
				<input type="text" class="form-control" name="sysUser.userPassword" value="${sysUser.userPassword}">
			</div>
		    <div class="input-group mar-t">
		    	<span class="input-group-addon">出生日期</span>
			    <input type="text" class="form-control" name="userBirthday" id="birthday" value="${sysUser.userBirthday}">
			</div>
			<div class="input-group mar-t">
		    	<span class="input-group-addon">学生籍贯</span>
			    <input type="text" class="form-control" name="sysUser.userNative" value="${sysUser.userNative}">
			</div>
			<div class="input-group mar-t">
		    	<span class="input-group-addon">联系方式</span>
			    <input type="text" class="form-control" name="sysUser.userPhone" value="${sysUser.userPhone}">
			</div>
		    <c:choose>
		        <c:when test="${1 == sysUser.userSex}">
					<div class="input-group mar-t">
				    	<span class="input-group-addon">学生性别</span>
					    <label class="form-control"><input type="radio"  checked="checked" value="1" name="sysUser.userSex">男</label>
					    <label class="form-control"><input type="radio" value="0" name="sysUser.userSex">女</label>
					</div>
		        </c:when>
		        <c:when test="${0 == sysUser.userSex}">
		        	<div class="input-group mar-t">
				    	<span class="input-group-addon">学生性别</span>
					    <label class="form-control"><input type="radio" value="1" name="sysUser.userSex">男</label>
					    <label class="form-control"><input type="radio" checked="checked" value="0" name="sysUser.userSex">女</label>
					</div>
		        </c:when>
	        </c:choose>
			<div class="mar-t">
				<!-- <label>所属学院</label>  -->
				<s:iterator value="instituteList" var="list" status="index">
				<s:if test='#list.instituteId==instituteInfo.institutePid'><input name="" type="hidden" value="${list.instituteId}"> </s:if>
				</s:iterator>
				<!-- <label>所属专业</label>  -->
				<s:iterator value="majorList" var="list">
				<s:if test='#list.instituteId==sysUser.instituteId'><input name="sysUser.instituteId" type="hidden" value="${list.instituteId}"></s:if>
				</s:iterator>
				<br>
				<!-- <label>所属班级</label>  -->
				<s:iterator value="classList" var="list">
				<s:if test='#list.classId==sysUser.classId'><input name="sysUser.classId" type="hidden" value="${list.classId}"></s:if>
				</s:iterator>
				<input type="hidden" name="sysUser.studentScore" value="${sysUser.studentScore }">
			<!-- <select name = "instituteId"  id="instituteId" type=""> -->
	    	<%-- <s:iterator value="instituteList" var="list" status="index"> --%>
			<%-- <option value="${list.instituteId}" <s:if test='#list.instituteId==instituteInfo.institutePid'> selected="selected"</s:if>>	
			${list.instituteName}
			</option> --%>
	       
	    <!-- </select> -->
	    <br>
   
  <%-- <select  name="sysUser.instituteId"id="majorId">
 	<s:iterator value="majorList" var="list"> --%>
          <%--  <option value="${list.instituteId}"<s:if test='#list.instituteId==sysUser.instituteId'>${list.instituteName}</s:if>>                 
				${list.instituteName}
		   </option>
	
 </select> --%>
<br>
  
	<%-- <select  name="sysUser.ClassId"id="classId">
		
	  <s:iterator value="classList" var="list"> --%>
	  		<%-- <option value="${list.classId}" <s:if test='#list.classId==sysUser.classId'></s:if>>
	  		${className}
	  		</option>
	        
          </s:iterator> --%>
				<input type="submit" class="btn btn-info" value="保存修改"/>
				<a class="btn btn-info" href="javascript:history.back();">返回</a>
			</div>
		</form>
	</div>
	<!-- 个人信息修改 -->
   <%--  <form action="<%= basePath%>SysUser_studentEdit.action" method="post">
    		<input name="sysUser.Id"  value="${sysUser.id}" type="hidden"/>
    	学号：<input type="text" name="sysUser.userId" value="${sysUser.userId}"><br>
    	密码：<input type="text" name="sysUser.userPassword" value="${sysUser.userPassword}"/><br>
    	姓名：<input type="text" name="sysUser.userName" value="${sysUser.userName}"/><br>
   		出生日期：<input type="text" name="userBirthday" id="birthday" value="${sysUser.userBirthday}"/><br>
   		平时成绩：<input type="text" name="sysUser.studentScore" value="${sysUser.studentScore}"><br>
    	籍贯：<input type="text" name="sysUser.userNative" value="${sysUser.userNative}"/><br>
    			<input type="hidden" name="sysUser.userType" value="${sysUser.userType}">
    	联系方式：<input type="text" name="sysUser.userPhone" value="${sysUser.userPhone}"/><br>
    	性别：
    		<s:if test='sysUser.userSex=="1"'>
              <input type="radio"checked="checked" value="1" name="sysUser.userSex"/>男
	          <input type="radio" value="0" name="sysUser.userSex" />女
	    	</s:if>
	    	<s:else>
	          <input type="radio" value="1" name="sysUser.userSex"/>男
	          <input type="radio" checked="checked"value="0" name="sysUser.userSex" />女
	    	</s:else>
	    	<br>
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
  <select  name="sysUser.instituteId"id="majorId">
 	<s:iterator value="majorList" var="list">
           <option value="${list.instituteId}"<s:if test='#list.instituteId==sysUser.instituteId'>${list.instituteName}</s:if>>                 
				${list.instituteName}
		   </option>
	</s:iterator>
 </select>
<br>
  <label>所属班级</label> 
	<select  name="sysUser.ClassId"id="classId">
		
	  <s:iterator value="classList" var="list">
	  		<option value="${list.classId}" <s:if test='#list.classId==sysUser.classId'></s:if>>
	  		${className}
	  		</option>
	        
          </s:iterator>
	 </select>
	 <br>
		<input type="submit" value="修改">
		<input type="button" value="取消"/>
    </form> --%>
  </body>
</html>
