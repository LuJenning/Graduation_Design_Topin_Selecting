<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<title>课题列表</title>
	<style type="text/css">
        *{margin: 0;padding: 0;}
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
        .position{height: 30px;padding-left: 10px;font-size: 15px;line-height: 30px;border-bottom: 1px solid #ccc;margin-bottom: 10px;background-color: #eee;}
        .position ul{margin-bottom:0;}
        .position ul li{float: left;}
        .position ul li:hover{color: #337ab7;}
   
        .wrap{min-width: 1200px;margin:0 auto;padding:0 10px;}
        .seach{width: 500px;margin-bottom: 10px;position:relative;}
        .word-box{position: absolute;
		    top: 33px;
		    left: 82px;
		    width: 200px;
		    border: 1px solid #ccc;
		    border-top:none;
		    z-index: 0;
		    background-color:#fff;
		}
		.word-box ul{margin:0;}
		.word-box ul li{
			width:100%;
			height:25px;
			line-height:25px;
			font-size:12px;
			list-style:none;
			text-indent:10px;
			border-bottom:1px solid #ccc;
		}
		.word-box ul li:hover{background-color:#eee;}
        table{width: 100%;border-spacing: 0;border-collapse: collapse;}
        thead tr th,tbody tr td{border:1px solid #ccc;color: #666;text-align: center;height:30px;line-height: 30px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
        thead tr th{background-color: rgb(240,240,240);}
        thead tr th:nth-child(1),tbody tr td:nth-child(1){width: 50px;}
        thead tr th:nth-child(2), thead tr th:nth-last-child(3),tbody tr td:nth-child(2),tbody tr td:nth-last-child(3){width: 100px;}
        thead tr th:nth-last-child(2),thead tr th:nth-last-child(1),tbody tr td:nth-last-child(2),tbody tr td:nth-last-child(1){width: 120px;}
        .limit{margin:20px 10px 0 10px;}
        .limit .msg{float:left;}
        .limit .msg ul li{float:left;height:30px;line-height:30px;font-size:15px;color:#337ab7;}
        .limit nav{float: right;}
        .limit nav ul{margin:0;}
        .mar-t{margin-top:10px;}
    </style>
    <link rel="stylesheet" href="<%=basePath %>/css/public-scoll.css">
    <link rel="stylesheet" href="<%=basePath %>/css/SpringIframe.css">
    <link rel="stylesheet" href="<%=basePath%>/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="<%=basePath %>/css/loading.css">
    <script src="<%=basePath%>js/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>/js/SpringIframe.js"></script>
    <script src="<%=basePath%>bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>js/lodaing.js"></script>
    <script type="text/javascript">
        document.createElement('nav');
    </script>
</head>
<body class="user-select">
	<!-- 位置信息 -->
	<div class="position">
        <ul>
            <li>教师系统&gt;</li>
            <li>志愿审核&gt;</li>
            <li>志愿课题列表&gt;</li>
        </ul>
    </div>
    <%-- <!-- 位置信息 -->
    <div class="wrap">
    	<!-- 查询-->
        <div class="seach">
        	<form action="<%=basePath%>Topic_openIdealTopicList.action" method="post">
	            <div class="input-group">
	                <span class="input-group-addon">模糊搜索</span>
	                <input type="text" name="topic.topicTitle" class="form-control" placeholder="Search for...">
	                <span class="input-group-btn">
	                    <input class="btn btn-default" type="submit" value="Go!">
	                </span>
	                <div class="word-box" style="display:none;">
	                	<ul></ul>
	                </div>
	            </div>
	            <script type="text/javascript">
            		var textInput = document.querySelector('input[name="topic.topicTitle"]');
            		var wordBox = document.querySelector('.word-box');
            		var oUl = document.querySelector('.word-box ul');
            		function flyword( mJson ){
						oUl.innerHTML = '';
						if(mJson.s.length){
							for ( var i=0;i<mJson.s.length ;i++ ){
								var oLi = document.createElement('li');
								oLi.innerHTML = mJson.s[i];
								oUl.appendChild( oLi );
								oLi.onclick = function(){
									textInput.value = this.innerHTML;
									wordBox.style.display = 'none';
								};
							};
						};
					};
            		textInput.onkeyup = function(){
						var val = this.value;
						var flyE = document.getElementById('fly');
						if(flyE)flyE.parentNode.removeChild(flyE);
						var oS = document.createElement('script');
						oS.id = 'fly';
						oS.src = 'https://sp0.baidu.com/5a1Fazu8AA54nxGko9WTAnF6hhy/su?wd='+ val +'&cb=flyword';
						this.parentNode.appendChild( oS );
						if( val ){
							wordBox.style.display = 'block';
						}else{
							wordBox.style.display = 'none';
						}
					};
	            </script>
            </form>
        </div>
        <!-- 查询-->
    --%>
    	<table> 
	        <!-- 信息头 -->
	        <thead>
	             <tr>
	                 <th>序号</th>
	                 <th>题目编号</th>
	                 <th>题目标题</th>
	                 <th>操作</th>
	             </tr>
	         </thead>
	         <!-- 信息头 -->
	        <!-- 记录信息列表 -->
	        <tbody>
		        <s:iterator value="pageResultT.data">
		            <tr>
	                    <td class="list-idx">0</td>
	                    <td>${topicId}</td>
	                    <td class="topic-topicTitle">${topicTitle}</td>
	                   
	                    <td>
	                    	<button type="button" class="btn btn-danger btn-xs" onmousedown="doIframe(this,{'url':'<%=basePath%>Ideal_IdealByTeacher.action?topicId=${topicId}','tTitle':'查看学生选题情况'});">查看学生选题情况</button>
	                    </td>
	                </tr>
				</s:iterator>
			</tbody>
			<!-- 记录信息列表 -->
		</table>
		<script type="text/javascript">
			;!function(){//动态修改序号
				var list = document.querySelectorAll('.list-idx');
				for(var i=0;i<list.length;i++){
					list[i].innerHTML = i+1;
				}
			}();
		</script>
   <!--  </div> -->
    <!-- 模态框（Modal） -->
    <!-- <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
	            </div>
	            <div class="modal-body">modal-body
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" id="id_ad_search">提交更改</button>
	            </div>
	        </div>/.modal-content
	    </div>/.modal
	</div> -->
	<%-- <!-- 触发脚本-->
	<script type="text/javascript">
		var modifyMsgURL = '<%=basePath %>Topic_openEditByAjax.action',
		    deleteMsgURL = '<%=basePath %>Topic_doDelete.action',
		    changeZYURl = '<%= basePath%>/Institute_findMajorsListById.action',
		    doModifySaveURL = '<%=basePath %>Topic_doEditSaveByAjax.action',
		    timer = null;
		function show_modal(obj,title,typeAction,values){
	         $('#myModal').modal('show');
	         if(title)$('#myModalLabel').html(title);
	         if(typeAction == "del"){
	         	$('.modal-body').html('').append('你确认删除吗？');
	         	$('#id_ad_search').html('确认删除');
	         	//绑定删除按钮
	         	document.getElementById('id_ad_search').onclick = function () {
	         		window.location.href = deleteMsgURL+"?id="+values;
	         	};
	         }else if(typeAction == 'modify'){
	         	$('#id_ad_search').html('确认修改');
	         	$('.modal-body').html('');
	         	//doModifyAjax(obj,url,data,callBack)
	         	var data = {'id':values};
	         	doModifyAjax($('.modal-body'),modifyMsgURL,data,function(backData){
	         		var temp = '<div class="input-group mar-t">'
							+'    <span class="input-group-addon">课题标题</span>'
							+'	  <input type="text" class="form-control" value="'+backData.topicInfo.topicTitle+'" name="topicTitle">'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon">课题要求</span>'
							+'	  <input type="text" class="form-control" value="'+backData.topicInfo.topicRequest+'" name="topicRequest">'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon">课题内容</span>'
							+'	  <input type="text" class="form-control" value="'+backData.topicInfo.topicContent+'" name="topicContent">'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon">课题余量</span>'
							+'	  <input type="number" class="form-control" value="'+backData.topicInfo.topicSurplus+'" name="topicSurplus">'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon">所属学院</span>'
							+'	  <select class="form-control" id="topicInfo-instituteId"><option value="'+backData.curentInstituteId+'">'+backData.curentInstituteName+'</option></select>'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon" id="instituteId">所属专业</span>'
							+'	  <select class="form-control" id="instituteId-zhuanye"><option value="'+backData.instituteInfo.instituteId+'">'+backData.instituteInfo.instituteName+'</option></select>'
							+'	</div>';
					$(this).append(temp);//'+backData.topicInfo.instituteId+'
					var xueyuan = '';
					for(var i=0;i<backData.institute.length;i++){
						if(backData.institute[i].instituteName != backData.curentInstituteName){
							xueyuan += '<option value="'+backData.institute[i].instituteId+'">'+backData.institute[i].instituteName+'</option>';
						}
					};
					$('#topicInfo-instituteId').append(xueyuan).change(function(){
					    var instituteId = $(this).find("option:selected").val();
					    $("#instituteId-zhuanye").html('');
					    $.post(changeZYURl,{"instituteId":instituteId},function(data){
							for(var i = 0; i < data.length; i++){
								$("#instituteId-zhuanye").append("<option  value='"+data[i].instituteId+"'>"+data[i].instituteName+"</option>");
							}
						},"json");
					});
					//绑定修改按钮
					document.getElementById('id_ad_search').onclick = function () {
						var topicTitle = $('.modal-body').find('input[name="topicTitle"]').val();
                        var topicRequest = $('.modal-body').find('input[name="topicRequest"]').val();
                        var topicContent = $('.modal-body').find('input[name="topicContent"]').val();
                        var topicSurplus = $('.modal-body').find('input[name="topicSurplus"]').val();
                        var zhuanye = $('.modal-body').find('select#instituteId-zhuanye').val();
                        var zhuanyeName = $('.modal-body').find('select#instituteId-zhuanye>option:selected').html();
                        if(topicTitle.length <= 0){
                        	$('input[name="topicTitle]').focus();
                        	return;
                        }
                        if(topicRequest.length <= 0){
                        	$('input[name="topicRequest]').focus();
                        	return;
                        }
                        if(topicContent.length <= 0){
                        	$('input[name="topicContent]').focus();
                        	return;
                        }
                        if(topicSurplus.length <= 0){
                        	$('input[name="topicSurplus]').focus();
                        	return;
                        }
                        if(timer)clearTimeout(timer);
                        loading({el:'tip',content:'正在修改，请耐心等待...'});
                        timer = setTimeout(function(){
        						$('#tip').slideUp(1500);
        				},1500);
						var data = {
							"topic.id":values,
							"topic.topicTitle":topicTitle,
							"topic.topicContent":topicContent,
							"topic.topicRequest":topicRequest,
							"topic.topicSurplus":topicSurplus,
							"topic.instituteId":zhuanye
						};
						//timer
						doModifyAjax(obj,doModifySaveURL,data,function(msg){
							var trDom = $(this).parents('tr');
							console.log(msg)
							if(msg == true || msg == 'true'){
								$(trDom).find('td.topic-topicTitle').html(topicTitle);
								$(trDom).find('td.topic-topicRequest').html(topicRequest);
								$(trDom).find('td.topic-topicContent').html(topicContent);
								$(trDom).find('td.topic-topicSurplus').html(topicSurplus);
								$(trDom).find('td.topic-instituteName').html(zhuanyeName);
								loading({el:'tip',content:'修改成功修改！'});
								timer = setTimeout(function(){
		        						$('#tip').slideUp(1500);
		        				},1500);
							}else{
								loading({el:'tip',content:'世界上最遥远的距离是没有网络！'});
								timer = setTimeout(function(){
		        						$('#tip').slideUp(1500);
		        				},1500);
							};
						});
					};
	         	});
	         };
	    };
	    function doModifyAjax(obj,url,data,callBack){
	    	$.ajax({
                url:url,
                data:data,
                type : "POST",
        		async:true,
        		success : function(msg){
        			callBack && callBack.call(obj,msg);
    			},
    			error : function(er){
    				callBack && callBack.call(obj,er);
    			}
            });
	    };
	</script> --%>
    <!-- 模态框（Modal） -->
	<script type="text/javascript">
		var url = "<%= basePath%>/Topic_openIdealTopicList.action";                 //获取表单url
		//首页
		function first(){
		   window.location.href  = url+"?page=1";
		}
		//上一页
		function previous(){
		    window.location.href  = url+"?page=${pageResultT.previousPageNumber}";
		}
		//下一页
		function next(){
		    window.location.href  = url+"?page=${pageResultT.nextPageNumber}";
		}
		//尾页
		function last(){
		  window.location.href  = url+"?page=${pageResultT.totalPage}";
		}
	</script>
	<!-- 分页 开始 -->
	<div class="limit">
		<!-- 记录信息 -->
		<div class="msg">
			<ul>
                <li>共<span>${pageResultT.total}</span>条记录</li>
                <li>共<span>${pageResultT.totalPage}</span>页</li>
                <li>当前显示第<span>${pageResultT.page}</span>页</li>
            </ul>
		</div>
		<!-- 记录信息 -->
        <nav aria-label="Page navigation">
            <ul class="pagination">
            	<c:choose>
	            	<c:when test="${pageResultT.isFirst==true}">
						<li><a href="javascript:;">首页</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:first()">首页</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${pageResultT.isFirst==true}">
						<li><a href="javascript:;">上一页</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:previous()">上一页</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${pageResultT.hasNext==true}">
						<li><a href="javascript:next()">下一页</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:;">下一页</a></li>
					</c:otherwise>
				</c:choose>
	            <c:choose>
					<c:when test="${pageResultT.isLast==true}">
						<li><a href="javascript:;">尾页</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:last()">尾页</a></li>
					</c:otherwise>
				</c:choose>
            </ul>
        </nav>
    </div>
    <!-- 分页 结束-->
</body>
</html>
