<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>
<%-- 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>请发布课题</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="<%=basePath %>/third/layui/css/layui.css">

<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/1.8.0/jquery.min.js"></script>
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

<body>
	<div>
		<div>
			<form action="<%=basePath %>Topic_doAdd.action" method="post"
				class="" id="">
					
						<input type="text" name="topicTitle" placeholder="题目标题.." ><br>
				
				<input type="text" name="topicRequest" placeholder="题目要求.." ><br>
				
				<textarea rows="5" cols="30" name="topicContent" placeholder="题目内容..." class=></textarea><br> 
			
				<input type="text" name="topicSurplus" placeholder="课题量.." ><br>
				上传附件:<a href=""></a><br>
				<input type="hidden" name="topic.userId" value="${sysUser.userId }"> 
				<input type="hidden" name="topic.userName" value="${sysUser.userName }">
				<label for="instituteId">所针对院系</label>
						<s:iterator>
						 	<select id="instituteId" name="instituteId" class="layui-input-inlines">
									<option value="">--请选择--</option>
									<s:iterator value="instituteList">
										<option value="${instituteId}">${instituteName}</option>
									</s:iterator>
							</select>
						</s:iterator><br>
						<label>专业</label> 
				<select name="instituteInfo.instituteId" id="majorId">
						<option value="">所针对专业</option>
						<s:iterator value="majorList">
							 <option value="${instituteId}">${instituteName}</option>
						</s:iterator>
				</select><br>
				<input type="submit" value="发布" class="layui-btn"><input type="reset" value="重置" class="layui-btn">
				
			</form>
		</div>
	</div>
</body>
<script src="third/layui/layui.js"></script>
	<script>
		//JavaScript代码区域
		layui.use('element', function() {
			var element = layui.element;

		});
	</script>
</html>
 --%>
 <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="Author" content=" ">
    <title>课题发布</title>
    <link rel="stylesheet" href="<%=basePath%>/css/public-scoll.css">
    <link rel="stylesheet" href="<%=basePath%>/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <style type="text/css">
        *{margin: 0;padding: 0;}
        a{text-decoration: none;color: #333;}
        ul,li{list-style: none;}
        body{font-size: 14px;font-family: "微软雅黑";padding-top:30px;}
        .wrap{width:870px;margin: 0 auto;}
        .wrap .row h3.col-sm-12{padding:0;margin:0;text-align:left;}
        .row span{margin: 5px 0 15px 0px;display: block;width: 95px;height: 5px;background-color: #5cb85c;}
    	.form-btn{text-align: center;}
    	.form-btn input:nth-child(2){margin-left: 10px;}
        .form-horizontal .form-group ,.wrap .row {margin-left: 0;margin-right: 0;}
        .wrap .row .col-sm-12{text-align: center;}
    </style>
    <script src="<%=basePath%>js/jquery/jquery-1.9.1.min.js"></script>
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
<body>
    <div class="wrap">
   		<div class="row">
			<h3 class="col-sm-12">课题发布</h3>
		</div>
		<div class="row">
			<span></span>
		</div>
        <form class="form-horizontal" action="<%=basePath %>Topic_doAdd.action" method="post">
            <div class="form-group">
                <label for="topicTitle" class="col-sm-2 control-label">题目标题</label>
                <div class="col-sm-10">
                    <input type="text" name="topicTitle" class="form-control" id="topicTitle" placeholder="题目标题...">
                </div>
            </div>
            <div class="form-group">
                <label for="topicRequest" class="col-sm-2 control-label">题目要求</label>
                <div class="col-sm-10">
                    <input type="text" name="topicRequest" class="form-control" id="topicRequest" placeholder="题目要求..">
                </div>
            </div>
            <div class="form-group">
                <label for="topicContent" class="col-sm-2 control-label">题目内容</label>
                <div class="col-sm-10">
                    <textarea style="resize:none;" class="form-control" name="topicContent" id="topicContent" placeholder="题目内容..."></textarea>
                </div>
            </div>
            <div class="form-group">
                <label for="topicSurplus" class="col-sm-2 control-label">课题量</label>
                <div class="col-sm-10">
                    <input type="number" name="topicSurplus" class="form-control" id="topicSurplus" placeholder="课题量(整数)..">
                </div>
            </div>
            <input type="hidden" name="topic.userId" value="${sysUser.userId }">
            <div class="form-group">
                <label for="instituteId" class="col-sm-2 control-label">所针对院系</label>
                <div class="col-sm-10">
                    <select class="form-control" name="instituteId" id="instituteId">
                        <option value="">--请选择--</option>
                        <s:iterator value="instituteList">
                            <option value="${instituteId}">${instituteName}</option>
                        </s:iterator>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="majorId" class="col-sm-2 control-label">所针对专业</label>
                <div class="col-sm-10">
                    <select class="form-control" name="instituteInfo.instituteId" id="majorId">
                        <option value="">--请选择--</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <input type="submit" class="btn btn-info" value="保存" />
                    <input type="reset" class="btn btn-info" value="重置" />
                </div>
            </div>
        </form>
    </div>
    <script type="text/javascript">
    </script>
</body>
</html>