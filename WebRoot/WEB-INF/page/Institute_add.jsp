<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'Institute_add.jsp' starting page</title>
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
</head>
<body class="user-select">
	<div class="wrap">
		<!-- 位置信息 -->
		<div class="position">
	        <ul>
	            <li>管理员系统&gt;</li>
	            <li>学院管理&gt;</li>
	            <li>添加学院&gt;</li>
	        </ul>
	    </div>
	    <!-- 位置信息 -->
	    <form action="<%=basePath%>Institute_instituteAdd.action" onsubmit="return checkForm();" method="post">
	    	<input type="hidden" name="instituteInfo.institutePid" value="0"/>
	    	<div class="input-group mar-t">
			    <span class="input-group-addon">学院名称</span>
				<input type="text" class="form-control" name="instituteInfo.instituteName" placeholder="请输入学院名称">
			</div>
	    	<div class="mar-t" style="text-align:center;">
				<input type="submit" class="btn btn-info" value="保存修改"/>
				<a class="btn btn-info" href="<%=basePath%>Institute_openMajorAdd.action">添加专业</a>
			</div>
	    </form>
	    <script type="text/javascript">
	    	var timer = null;
	    	function checkForm(){
	    		var txt = $('input[name="instituteInfo.instituteName"]').val();
	    		if($.trim(txt).length <= 0){
		    		if(timer)clearTimeout(timer);
                    loading({el:'tip',content:'输入错误，学院名称不能为空'});
                    timer = setTimeout(function(){
       					$('#tip').slideUp(1500);
       				},1500);
	    			$('input[name="instituteInfo.instituteName"]').focus();
	    			return false;
	    		}
	    	};
	    </script>
	</div>
</body>
</html>
