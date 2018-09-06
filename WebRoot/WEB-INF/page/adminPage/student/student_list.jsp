<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>学生列表-分页显示</title>
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
        .wrap{min-width: 890px;margin:0 auto;padding:0 10px;}
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
        table{width: 100%;border-spacing: 0;border-collapse: collapse;overflow: hidden;}
        thead tr th,tbody tr td{border:1px solid #ccc;text-align: center;height:30px;line-height: 30px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
        thead tr th{background-color: rgb(240,240,240);color: #666;}
        thead tr th:nth-child(1),tbody tr td:nth-child(1),thead tr th:nth-child(6),tbody tr td:nth-child(6){width: 50px;}
        thead tr th:nth-child(2), thead tr th:nth-last-child(3),thead tr th:nth-child(4),tbody tr td:nth-child(2)，tbody tr td:nth-child(4),tbody tr td:nth-last-child(3){width: 100px;}
        thead tr th:nth-last-child(2),tbody tr td:nth-last-child(2),thead tr th:nth-child(3),tbody tr td:nth-child(3){width: 80px;}
        thead tr th:nth-child(5),tbody tr td:nth-child(5){width: 150px;}
        thead tr th:nth-last-child(1),tbody tr td:nth-last-child(1){width: 100px;}
        thead tr th:nth-child(3),tbody tr td:nth-child(3){width:200px;}
        thead tr th:nth-last-child(4),tbody tr td:nth-last-child(4){width:100px;}
        .wrap .container .msg-header{background-color: rgb(240,240,240);}
        .wrap .container .row > div {border: 1px solid #ccc;height: 30px;line-height: 30px;}
        .limit{margin:20px 10px 0 10px;}
        .limit .msg{float:left;}
        .limit .msg ul li{float:left;height:30px;line-height:30px;font-size:15px;color:#337ab7;}
        .limit nav{float: right;}
        .limit nav ul{margin:0;}
        .mar-t{margin-top:10px;}
    </style>
	<link rel="stylesheet" href="<%=basePath %>/css/public-scoll.css">
    <link rel="stylesheet" href="<%=basePath%>/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="<%=basePath %>/css/loading.css">
    <script src="<%=basePath%>js/jquery/jquery-1.9.1.min.js"></script>
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
            <li>管理员系统&gt;</li>
            <li>学生管理&gt;</li>
            <li>学生列表&gt;</li>
        </ul>
    </div>
    <!-- 位置信息 -->
    <div class="wrap">
        <!-- 查询-->
        <div class="seach">
            <form action="<%=basePath%>Tutor_openListForStudent.action" method="post">
	            <div class="input-group">
	                <span class="input-group-addon">模糊搜索</span>
	                <input type="text" name="likeString" class="form-control" placeholder="Search for...">
	                <span class="input-group-btn">
	                    <input class="btn btn-default" type="submit" value="Go!">
	                </span>
	                <div class="word-box" style="display:none;">
	                	<ul></ul>
	                </div>
	            </div>
	            <script type="text/javascript">
            		var textInput = document.querySelector('input[name="likeString"]');
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
        <table>
            <thead>
                <tr>
                    <th>序号</th>
                    <th>账号</th>
                    <th>姓名</th>
                    <th>密码</th>
                    <th>所属学院</th>
                    <th>类型</th>
                    <th>联系方式</th>
                    <th>成绩</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            <s:iterator value="teacherPageResult.data">
                <tr>
                    <td class="list-idx">0</td>
                    <td class="sysUser-userId">${userId }</td>
                    <td class="sysUser-userName">${userName }</td>
                    <td class="sysUser-userPassword">${userPassword }</td>
                    <td class="sysUser-institutId">${instituteName }</td>
                    <td>学生</td>
                    <td class="sysUser-userPhone">${userPhone }</td>
                    <td class="sysUser-studentScore">${studentScore }</td>
                    <td>
                    	<button type="button" class="btn btn-info btn-xs" onclick="show_modal(this,'修改学生信息','modify',${sysUserId});">修改</button>
	                    <button type="button" class="btn btn-danger btn-xs" onclick="show_modal(this,'删除学生','del',${sysUserId});">删除</button>
                    </td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
        <script type="text/javascript">
			;!function(){//动态修改序号
				var list = document.querySelectorAll('.list-idx');
				for(var i=0;i<list.length;i++){
					list[i].innerHTML = i+1;
				}
			}();
		</script>
    </div>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
	            </div>
	            <div class="modal-body"><!--modal-body-->
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" id="id_ad_search">提交更改</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
	<!-- 触发脚本-->
	<script type="text/javascript">
		var modifyMsgURL = '<%=basePath %>Tutor_openEditForStudentByAjax.action',
		    deleteMsgURL = '<%=basePath %>Tutor_delete.action',
		    doModifySaveURL = '<%=basePath %>Tutor_doEditSaveByAjax.action',
		    timer = null;
		function show_modal(obj,title,typeAction,values){
	         $('#myModal').modal('show');
	         if(title)$('#myModalLabel').html(title);
	         if(typeAction == "del"){
	         	$('.modal-body').html('').append('你确认删除吗？');
	         	$('#id_ad_search').html('确认删除');
	         	//绑定删除按钮
	         	document.getElementById('id_ad_search').onclick = function () {
	         		window.location.href = deleteMsgURL+"?sysUser.id="+values;
	         	};
	         }else if(typeAction == 'modify'){
	         	$('#id_ad_search').html('确认修改');
	         	$('.modal-body').html('');
	         	//doModifyAjax(obj,url,data,callBack)
	         	var data = {'sysUser.id':values};
	         	doModifyAjax($('.modal-body'),modifyMsgURL,data,function(backData){
	         		console.log(backData);
	         		var temp = '<div class="input-group mar-t">'
							+'    <span class="input-group-addon">学生账号</span>'
							+'	  <input type="text" class="form-control" value="'+backData.sysUser.userId+'" name="userId" readonly>'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon">学生姓名</span>'
							+'	  <input type="text" class="form-control" value="'+backData.sysUser.userName+'" name="userName">'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon">学生密码</span>'
							+'	  <input type="text" class="form-control" value="'+backData.sysUser.userPassword+'" name="userPassword">'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon">学生方式</span>'
							+'	  <input type="text" class="form-control" value="'+backData.sysUser.userPhone+'" name="userPhone">'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon">学生成绩</span>'
							+'	  <input type="text" class="form-control" value="'+backData.sysUser.studentScore+'" name="studentScore">'
							+'	</div>'
							+'	<div class="input-group mar-t">'
							+'		<span class="input-group-addon">学生性别</span>'
							+'		<label class="form-control"> '
							+'			<input type="radio" name="userSex" checked="checked" value="1">男'
							+'		</label> '
							+'		<label class="form-control"> '
							+'		    <input type="radio" name="userSex" value="0">女'
							+'		</label>'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon">所属学院</span>'
							+'	  <select class="form-control" id="topicInfo-instituteId"></select>'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon">所属专业</span>'
							+'	  <select class="form-control" id="instituteId"></select>'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon">所属班级</span>'
							+'	  <select class="form-control" id="className"></select>'
							+'	</div>';
					$(this).append(temp);
					var xueyuan = '';
					var firstXueyuan = '<option value="'+backData.curentInstituteId+'">'+backData.curentInstituteName+'</option>';
					var firstIn = '<option value="'+backData.instituteInfo.instituteId+'">'+backData.instituteInfo.instituteName+'</option>';
					var firstCN = '<option value="'+backData.classInfo.classId+'">'+backData.classInfo.className+'</option>';
					for(var i=0;i<backData.instituteList.length;i++){
						if(backData.instituteList[i].instituteId != backData.curentInstituteId){
							xueyuan += '<option value="'+backData.instituteList[i].instituteId+'">'+backData.instituteList[i].instituteName+'</option>';
						}
					};
					$(this).find('#topicInfo-instituteId').append(firstXueyuan);
					$(this).find('#topicInfo-instituteId').append(xueyuan);
					$(this).find('#instituteId').append(firstIn);
					$(this).find('#className').append(firstCN);
					$(this).find("#topicInfo-instituteId").change(function(){
					    var url = "<%= basePath%>/Institute_findMajorsListById.action";
					    var instituteId = $("#topicInfo-instituteId").find("option:selected").val();
					    $('.modal-body').find("#instituteId").html('');
					    $.post(url,{"instituteId":instituteId},function(data){
					    	console.log("instituteId:"+instituteId)
							for(var i = 0; i < data.length; i++){
								$('.modal-body').find("#instituteId").append("<option  value='"+data[i].instituteId+"'>"+data[i].instituteName+"</option>");
							}
						},"json");
					});
					$(this).find('#instituteId').change(function(){
						var url = "<%= basePath%>/Class_findClassListById.action";
					    var majorId = $('#instituteId').find("option:selected").val();
					    $('.modal-body').find("#className").html('');
					    $.post(url,{"instituteId":majorId},function(data){
					    	console.log("majorId:"+majorId)
						    for(var i = 0; i < data.length; i++){
					           $('.modal-body').find("#className").append("<option  value='"+data[i].classId+"'>"+data[i].className+"</option>");
							}
						},"json");
					});
					//绑定修改按钮
					document.getElementById('id_ad_search').onclick = function () {
						var userId = $('.modal-body').find('input[name="userId"]').val();
                        var userName = $('.modal-body').find('input[name="userName"]').val();
                        var userPassword = $('.modal-body').find('input[name="userPassword"]').val();
                        var userPhone = $('.modal-body').find('input[name="userPhone"]').val();
                        var userSex = $('.modal-body').find('input[name="userSex"]:selected').val();
                        var xueyuanId = $('.modal-body').find('select#topicInfo-instituteId').val();
                        var xueyuanName = $('.modal-body').find('select#topicInfo-instituteId>option:selected').html();
                        var studentScore = $('.modal-body').find('input[name="studentScore"]').val();//studentScore
                        var classId = $('.modal-body').find('#className>option:selected').val();
                        if(userId.length <= 0){
                        	$('input[name="userId]').focus();
                        	return;
                        }
                        if(userName.length <= 0){
                        	$('input[name="userName]').focus();
                        	return;
                        }
                        if(userPassword.length <= 0){
                        	$('input[name="userPassword]').focus();
                        	return;
                        }
                        if(userPhone.length <= 0){
                        	$('input[name="userPhone]').focus();
                        	return;
                        }
                        if(timer)clearTimeout(timer);
                        loading({el:'tip',content:'正在修改，请耐心等待...'});
                        timer = setTimeout(function(){
        						$('#tip').slideUp(1500);
        				},1500);
						var data = {
							"sysUser.id":values,
							"sysUser.userId":userId,
							"sysUser.userName":userName,
							"sysUser.userPassword":userPassword,
							"sysUser.userPhone":userPhone,
							"sysUser.userSex":userSex,
							"sysUser.instituteId":xueyuanId,
							"sysUser.studentScore":studentScore,
							"sysUser.classId":classId,
						};
						//timer
						doModifyAjax(obj,doModifySaveURL,data,function(msg){
							var trDom = $(this).parents('tr');
							console.log(msg)
							if(msg == true || msg == 'true'){
								$(trDom).find('td.sysUser-userId').html(userId);
								$(trDom).find('td.sysUser-userName').html(userName);
								$(trDom).find('td.sysUser-userPassword').html(userPassword);
								$(trDom).find('td.sysUser-institutId').html(xueyuanName);
								$(trDom).find('td.sysUser-userPhone').html(userPhone);
								$(trDom).find('td.sysUser-studentScore').html(studentScore);
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
	</script>
    <!-- 模态框（Modal） -->
	<script type="text/javascript">
		var url = "<%= basePath%>/Tutor_openListForStudent.action";                 //获取表单url
		//首页
		function first(){
		   window.location.href  = url+"?page=1";
		}
		//上一页
		function previous(){
		    window.location.href  = url+"?page=${teacherPageResult.previousPageNumber}";
		}
		//下一页
		function next(){
		    window.location.href  = url+"?page=${teacherPageResult.nextPageNumber}";
		}
		//尾页
		function last(){
		  window.location.href  = url+"?page=${teacherPageResult.totalPage}";
		}
	</script>
	<!-- 分页 开始 -->
	<div class="limit">
		<!-- 记录信息 -->
		<div class="msg">
			<ul>
                <li>共<span>${teacherPageResult.total}</span>条记录</li>
                <li>共<span>${teacherPageResult.totalPage}</span>页</li>
                <li>当前显示第<span>${teacherPageResult.page}</span>页</li>
            </ul>
		</div>
		<!-- 记录信息 -->
        <nav aria-label="Page navigation">
            <ul class="pagination">
            	<c:choose>
	            	<c:when test="${teacherPageResult.isFirst==true}">
						<li><a href="javascript:;">首页</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:first()">首页</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${teacherPageResult.isFirst==true}">
						<li><a href="javascript:;">上一页</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:previous()">上一页</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${teacherPageResult.hasNext==true}">
						<li><a href="javascript:next()">下一页</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:;">下一页</a></li>
					</c:otherwise>
				</c:choose>
	            <c:choose>
					<c:when test="${teacherPageResult.isLast==true}">
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
