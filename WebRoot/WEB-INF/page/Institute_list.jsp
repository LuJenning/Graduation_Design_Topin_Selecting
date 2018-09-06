<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title>学院信息列表</title>
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
        .wrap{min-width: 880px;margin:0;}
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
        thead tr th:nth-child(1),tbody tr td:nth-child(1){width: 50px;}
        thead tr th:nth-last-child(1),
        thead tr th:nth-last-child(2),
        thead tr th:nth-last-child(3),
        tbody tr td:nth-last-child(1),
        tbody tr td:nth-last-child(2),
        tbody tr td:nth-last-child(3){width: 100px;}
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
</head>
<body class="user-select">
    <!-- 位置信息 -->
    <div class="position">
        <ul>
            <li>管理员系统&gt;</li>
            <li>学院/专业管理&gt;</li>
            <li>学院/专业列表&gt;</li>
        </ul>
    </div>
    <!-- 位置信息 -->
    <div class="wrap">
        <!-- 查询-->
        <div class="seach">
            <form action="<%=basePath%>Institute_institutePageList.action" method="post">
	            <div class="input-group">
	                <span class="input-group-addon">模糊搜索</span>
	                <input type="text" name="instituteInfo.instituteName" class="form-control" placeholder="Search for...">
	                <span class="input-group-btn">
	                    <input class="btn btn-default" type="submit" value="Go!">
	                </span>
	                <div class="word-box" style="display:none;">
	                	<ul></ul>
	                </div>
	            </div>
	            <script type="text/javascript">
            		;!function(){
	            		var textInput = document.querySelector('input[name="instituteInfo.instituteName"]');
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
						window.flyword = flyword;
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
            		}();
	            </script>
            </form>
        </div>
        <!-- 查询-->
        <table>
            <thead> 
                <tr>
                    <th>序号</th>
                    <th>id</th>
                    <th>学院/专业名称</th>
                    <th>是否是学院</th>
                    <th>是否是专业</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            <s:iterator value="pageResult.data">
                <tr>
                    <td class="list-idx">0</td>
                    <td class="instituteId">${instituteId }</td>
                    <td class="instituteName">${instituteName }</td>
                    <td class="institutePid">${institutePid }</td>
                    <td class="institute-Pid">${institutePid }</td>
                    <td>
                    	<button type="button" class="btn btn-info btn-xs" onclick="show_modal(this,'修改学院信息','modify',${instituteId},${institutePid});">修改</button>
	                    <button type="button" class="btn btn-danger btn-xs" onclick="show_modal(this,'删除学院','del',${instituteId},${institutePid});">删除</button>
                    </td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
        <script type="text/javascript">
			;!function(){//动态修改序号
				var list = document.querySelectorAll('.list-idx');
				var institutePids = document.querySelectorAll('.institutePid');
				var instituteids = document.querySelectorAll('.institute-Pid');
				for(var i=0;i<list.length;i++){
					list[i].innerHTML = i+1;
					if(institutePids[i].innerHTML == '0'){
						institutePids[i].innerHTML = '是';
					}else{
						institutePids[i].innerHTML = '否';
					};
					if(instituteids[i].innerHTML != '0'){
						instituteids[i].innerHTML = '是';
					}else{
						instituteids[i].innerHTML = '否';
					};
				};
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
		var modifyMsgURL = '<%=basePath %>Institute_getInstituteForAjax.action',
		    doDeleteURL = '<%=basePath %>Institute_doInstituteDeleteByAjax.action',
		    doModifySaveURL = '<%=basePath %>Institute_doEditInstituteSaveByAjax.action',
		    timer = null;
		function show_modal(obj,title,typeAction,values,changType){
	         $('#myModal').modal('show');
	         if(title)$('#myModalLabel').html(title);
	         if(typeAction == "del"){
	         	$('#id_ad_search').html('确认删除');
	         	var delData = {
                    	"instituteInfo.instituteId":values,
                    };
	         	//绑定删除按钮
	         	if(changType != '0'){//删除学院
	         		$('.modal-body').html('').append('你确认该学院删除吗？');
		         	document.getElementById('id_ad_search').onclick = function () {
		         		selfTip(timer,'正在提交删除');
						doModifyAjax(obj,doDeleteURL,delData,function(msg){
							$(this).parents('tr').remove();
							console.log(msg);
							(msg)?selfTip(timer,'信息已修改成功'):selfTip(timer,'信息修改失败');
						});
		         	};
	         	}else if(changType == '0'){//删除学院
	         		$('.modal-body').html('').append('你确认该专业删除吗？');
		         	document.getElementById('id_ad_search').onclick = function () {
		         		selfTip(timer,'正在提交删除');
						doModifyAjax(obj,doDeleteURL,delData,function(msg){
							$(this).parents('tr').remove();
							console.log(msg);
							(msg)?selfTip(timer,'信息已修改成功'):selfTip(timer,'信息修改失败');
						});
		         	};
	         	};
	         }else if(typeAction == 'modify'){
	         	$('#id_ad_search').html('确认修改');
	         	$('.modal-body').html('');
	         	//doModifyAjax(obj,url,data,callBack)
	         	var data = {'instituteId':values};
	         	if(changType != '0'){//修改专业
	         		doModifyAjax($('.modal-body'),modifyMsgURL,data,function(backData){
		         		//console.log(backData);
		         		var temp = '<div class="input-group mar-t">'
								+'    <span class="input-group-addon">学院编号</span>'
								+'	  <input type="text" class="form-control" value="'+backData.instituteId+'" name="instituteId" readonly>'
								+'	</div>'
								+'  <div class="input-group mar-t">'
								+'    <span class="input-group-addon">学院名称</span>'
								+'	  <input type="text" class="form-control" value="'+backData.instituteName+'" name="instituteName">'
								+'	</div>'
								+'  <div class="input-group mar-t">'
								+'    <span class="input-group-addon">学院/班级</span>'
								+'	  <input type="text" class="form-control" value="专业" name="instituteName" readonly>'
								+'	</div>';
						$(this).append(temp);//'+backData.topicInfo.instituteId+'
						//绑定修改按钮
						document.getElementById('id_ad_search').onclick = function () {
							var instituteId = $('.modal-body').find('input[name="instituteId"]').val();
	                        var instituteName = $('.modal-body').find('input[name="instituteName"]').val();
	                        if(instituteName.length <= 0){
	                        	$('input[name="instituteName]').focus();
	                        	return;
	                        };
	                        var saveData = {
	                        	"instituteInfo.instituteId":instituteId,
	                        	"instituteInfo.instituteName":instituteName,
	                        	"instituteInfo.institutePid":changType
	                        };
							//timer
							selfTip(timer,'正在提交修改');
							doModifyAjax(obj,doModifySaveURL,saveData,function(msg){
								var trDom = $(this).parents('tr').find('td')[2];
								$(trDom).html(instituteName);
								console.log(msg);
								(msg)?selfTip(timer,'信息已修改成功'):selfTip(timer,'信息修改失败');
							});
						};
		         	});
	         	}else if(changType == '0'){//修改学院
	         		doModifyAjax($('.modal-body'),modifyMsgURL,data,function(backData){
		         		console.log(backData);
		         		var temp = '<div class="input-group mar-t">'
								+'    <span class="input-group-addon">学院编号</span>'
								+'	  <input type="text" class="form-control" value="'+backData.instituteId+'" name="instituteId" readonly>'
								+'	</div>'
								+'  <div class="input-group mar-t">'
								+'    <span class="input-group-addon">学院名称</span>'
								+'	  <input type="text" class="form-control" value="'+backData.instituteName+'" name="instituteName">'
								+'	</div>'
								+'  <div class="input-group mar-t">'
								+'    <span class="input-group-addon">学院/班级</span>'
								+'	  <input type="text" class="form-control" value="学院" name="instituteName" readonly>'
								+'	</div>';
						$(this).append(temp);//'+backData.topicInfo.instituteId+'
						//绑定修改按钮
						document.getElementById('id_ad_search').onclick = function () {
							var instituteId = $('.modal-body').find('input[name="instituteId"]').val();
	                        var instituteName = $('.modal-body').find('input[name="instituteName"]').val();
	                        if(instituteName.length <= 0){
	                        	$('input[name="instituteName]').focus();
	                        	return;
	                        };
	                        var saveData = {
	                        	"instituteInfo.instituteId":instituteId,
	                        	"instituteInfo.instituteName":instituteName,
	                        	"instituteInfo.institutePid":changType
	                        };
	                        console.log(saveData);
							//timer
							selfTip(timer,'正在提交修改');
							doModifyAjax(obj,doModifySaveURL,saveData,function(msg){
								var trDom = $(this).parents('tr').find('td')[2];
								$(trDom).html(instituteName);
								console.log(msg);
								(msg)?selfTip(timer,'信息已修改成功'):selfTip(timer,'信息修改失败');
							});
						};
		         	});
	         	};
	         };
	    };
	    function selfTip(timer,contentT){
	    	contentT = contentT || '世界上最遥远的距离是没有网络！';
	    	loading({el:'tip',content:contentT});
			timer = setTimeout(function(){
					$('#tip').slideUp(1500);
			},1500);
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
	<script type="text/javascript">
		var url = "<%= basePath%>/Institute_institutePageList.action";                 //获取表单url
		//首页
		function first(){
		   window.location.href  = url+"?page=1";
		}
		//上一页
		function previous(){
		    window.location.href  = url+"?page=${pageResult.previousPageNumber}";
		}
		//下一页
		function next(){
		    window.location.href  = url+"?page=${pageResult.nextPageNumber}";
		}
		//尾页
		function last(){
		  window.location.href  = url+"?page=${pageResult.totalPage}";
		}
	</script>
	<!-- 分页 开始 -->
	<div class="limit">
		<!-- 记录信息 -->
		<div class="msg">
			<ul>
                <li>共<span>${pageResult.total}</span>条记录</li>
                <li>共<span>${pageResult.totalPage}</span>页</li>
                <li>当前显示第<span>${pageResult.page}</span>页</li>
            </ul>
		</div>
		<!-- 记录信息 -->
        <nav aria-label="Page navigation">
            <ul class="pagination">
            	<c:choose>
	            	<c:when test="${pageResult.isFirst==true}">
						<li><a href="javascript:;">首页</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:first()">首页</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${pageResult.isFirst==true}">
						<li><a href="javascript:;">上一页</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:previous()">上一页</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${pageResult.hasNext==true}">
						<li><a href="javascript:next()">下一页</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:;">下一页</a></li>
					</c:otherwise>
				</c:choose>
	            <c:choose>
					<c:when test="${pageResult.isLast==true}">
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
