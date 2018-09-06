<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title>My JSP 'Major_add.jsp' starting page</title>
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
	            <li>专业管理&gt;</li>
	            <li>添加专业&gt;</li>
	        </ul>
	    </div>
	    <!-- 位置信息 -->
	    <form action="<%=basePath%>Institute_instituteAdd.action" onsubmit="return checkForm();" method="post">
	    	<div class="input-group mar-t">
			    <span class="input-group-addon">所属学院</span>
				<select name="instituteInfo.institutePid" class="form-control">
					<option value="">请选择所属学院</option>
					<s:iterator value="instituteList">
						<option value="${instituteId}">${instituteName}</option>
					</s:iterator>
				</select>
			</div>
			<div class="input-group mar-t">
			    <span class="input-group-addon">专业名称</span>
				<input type="text" class="form-control" name="instituteInfo.instituteName" placeholder="请输入专业名称">
			</div>
	    	<div class="mar-t" style="text-align:center;">
				<input type="submit" class="btn btn-info" value="保存修改"/>
				<a class="btn btn-info" href="javascript:history.back();">返回</a>
			</div>
	    </form>
	    <script type="text/javascript">
	    	var timer = null;
	    	function checkForm(){
	    		var txt = $('input[name="instituteInfo.instituteName"]').val();
	    		var xy = $('select[name="instituteInfo.institutePid"]>option:selected').text();
	    		if($.trim(xy) == '请选择所属学院'){
		    		if(timer)clearTimeout(timer);
                    loading({el:'tip',content:'选择错误，请选择学院名称'});
                    timer = setTimeout(function(){
       					$('#tip').slideUp(1500);
       				},1500);
	    			return false;
	    		}
	    		if($.trim(txt).length <= 0){
		    		if(timer)clearTimeout(timer);
                    loading({el:'tip',content:'输入错误，专业名称不能为空'});
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
