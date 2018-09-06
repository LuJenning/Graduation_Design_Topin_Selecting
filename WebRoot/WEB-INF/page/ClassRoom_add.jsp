<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>My JSP 'ClassRoom_add.jsp' starting page</title>
	<style type="text/css">
    	*{margin:0;padding:0;}
		a{text-decoration: none;color: #333;}
        ul,li{list-style: none;}
        body{font-size: 14px;font-family: "微软雅黑";}
        .user-select{
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            -o-user-select: none;
            user-select: none;
        }
        .wrap{width: 890px;margin:0 auto;}
        .position{height: 30px;padding-left: 10px;font-size: 15px;line-height: 30px;border-bottom: 1px solid #ccc;margin-bottom: 10px;background-color: #eee;}
        .position ul{margin-bottom:0;}
        .position ul li{float: left;}
        .position ul li:hover{color: #337ab7;}
        .mar-t{margin-top:10px;}
	</style>
	<link rel="stylesheet" href="<%=basePath%>/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/css/loading.css">
	<script src="<%=basePath%>js/jquery/jquery-1.9.1.min.js"></script>
	<script src="<%=basePath%>js/lodaing.js"></script>
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
	</script> 
</head>
<body class="user-select">
    <div class="wrap">
		<!-- 位置信息 -->
		<div class="position">
	        <ul>
	            <li>管理员系统&gt;</li>
	            <li>学院管理&gt;</li>
	            <li>专业管理&gt;</li>
	            <li>班级管理&gt;</li>
	            <li>添加班级&gt;</li>
	        </ul>
	    </div>
	    <!-- 位置信息 -->
	    <form action="<%=basePath%>/Class_classAdd.action" onsubmit="return checkForm();" method="post">
	    	<div class="input-group mar-t">
			    <span class="input-group-addon">所属学院</span>
				<select name = "instituteId" id="instituteId" class="form-control">
    	            <option selected="selected" value="">请选择院系</option>
					<s:iterator value="instituteList" >
    	        	     <option value="${instituteId}">${instituteName}</option>
    	        	</s:iterator>
    	        </select>
			</div>
			<div class="input-group mar-t">
			    <span class="input-group-addon">所属专业</span>
				<select name="instituteInfo.instituteId" id="majorId"  class="form-control">
					<option value="">请选专业</option>
					<s:iterator value="majorList">
						 <option value="${instituteId}">${instituteName}</option>
					</s:iterator>
				</select>
			</div>
			<div class="input-group mar-t">
			    <span class="input-group-addon">班级名称</span>
				<input type="text" class="form-control" name="classInfo.className" placeholder="请输入班级名称">
			</div>
	    	<div class="mar-t" style="text-align:center;">
				<input type="submit" class="btn btn-info" value="确认保存"/>
			</div>
	    </form>
	    <script type="text/javascript">
	    	var timer = null;
	    	function checkForm(){
	    		var txt = $('#instituteId>option:selected').text();
	    		var xy = $('#majorId>option:selected').text();
	    		var bj = $('input[name="classInfo.className"]').val();
	    		if($.trim(txt) == '请选择院系'){
		    		if(timer)clearTimeout(timer);
                    loading({el:'tip',content:'选择错误，请选择学院名称'});
                    timer = setTimeout(function(){
       					$('#tip').slideUp(1500);
       				},1500);
	    			return false;
	    		}
	    		if($.trim(xy) == '请选专业'){
		    		if(timer)clearTimeout(timer);
                    loading({el:'tip',content:'选择错误，请选择专业名称'});
                    timer = setTimeout(function(){
       					$('#tip').slideUp(1500);
       				},1500);
	    			return false;
	    		}
	    		if($.trim(bj).length <= 0){
		    		if(timer)clearTimeout(timer);
                    loading({el:'tip',content:'输入错误，班级名称不能为空'});
                    timer = setTimeout(function(){
       					$('#tip').slideUp(1500);
       				},1500);
	    			$('input[name="classInfo.className"]').focus();
	    			return false;
	    		}
	    	};
	    </script>
	</div>
</body>
</html>
