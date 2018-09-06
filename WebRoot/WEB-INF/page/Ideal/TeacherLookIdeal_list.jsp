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
        thead tr th:nth-child(2), thead tr th:nth-child(3),tbody tr td:nth-child(2),tbody tr td:nth-child(3){width: 200px;}
        thead tr th:nth-last-child(2),
        tbody tr td:nth-last-child(2){width: 100px;}
        thead tr th:nth-last-child(1),
        tbody tr td:nth-last-child(1){width: 200px;}
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
	                 <th>学生学号</th>
	                 <th>学生姓名</th>
	                 <th>志愿类型</th>
	                 <th>志愿填报时间</th>
	                 <th>录取状态</th>
	                 <th>操作</th>
	             </tr>
	         </thead>
	         <!-- 信息头 -->
	        <!-- 记录信息列表 -->
	        <tbody>
		        <s:iterator value="teachLookIdealList.data">
		            <tr>
	                    <td class="list-idx" topicId=${topicId }>0</td>
	                    <td>${userId}</td>
	                    <td><a href="<%=basePath%>Tutor_openShow.action?userId=${userId}">${userName}</a></td>
	                    
	                   	<td><s:if test="idealType=='first'">第一志愿</s:if>
	                   <s:else>第二志愿</s:else></td>
	                  	<td>${selectDate}</td>
	                   	<td is-accept="true">
	                   	<s:if test="isAccept==1">已录取</s:if>
	                  	<s:elseif test="isAccept==2">已退档</s:elseif>
	                  	<s:else>等待录取</s:else>
	                  	</td>
	                    <td is-choose="${isAccept}">
	                    	<button type="button" class="btn btn-success btn-xs" onclick="show_modal(this,'志愿审核>录取','agree',${tableId });" isflag="1">录取</button>
	                    	<button type="button" class="btn btn-danger btn-xs" onclick="show_modal(this,'志愿审核>退档','ctrl',${tableId });" isflag="2">退档</button>
	                    </td>
	                </tr>
				</s:iterator>
			</tbody>
			<!-- 记录信息列表 -->
		</table>
		<!-- 模态框（Modal） -->
	     <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
		            </div>
		            <div class="modal-body">
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		                <button type="button" class="btn btn-primary" id="id_ad_search">提交更改</button>
		            </div>
		        </div>
		    </div>
		</div>
		<!-- 触发脚本-->
		<script type="text/javascript">
			var agreeMsgURL = '<%=basePath %>Ideal_agreeIdeal.action',
			    ctrlMsgURL = '<%=basePath %>Ideal_ctrlIdeal.action',
			    timer = null;
			function show_modal(obj,title,typeAction,id){
		         $('#myModal').modal('show');
		         if(title)$('#myModalLabel').html(title);
		         var data = {
		         	"ideal.id":id
		         };
		         if(typeAction == "agree"){
		         	$('.modal-body').html('').append('你确认录取吗？');
		         	$('#id_ad_search').html('确认录取');
		         	//绑定录取按钮
		         	document.getElementById('id_ad_search').onclick = function () {
                        msgResoult(timer);//提示信息
		         		//doModifyAjax(obj,url,data,callBack)
		         		doModifyAjax(obj,agreeMsgURL,data,function(reData){
		         			if(reData == true){
		         				msgResoult(timer,'录取成功');
		         				this.parentNode.parentNode.querySelectorAll('td')[5].innerHTML = '已录取';
		         				this.parentNode.innerHTMl = '<span class="btn btn-success btn-xs">已录取</span>';
		         				if(this.getAttribute('isflag') == '1'){
		         					this.parentNode.innerHTML  = '<span class="btn btn-success btn-xs">已录取</span>';
		         				};
		         			}else{
		         				msgResoult(timer,'录取失败');
		         				if(this.getAttribute('isflag') == '1'){
		         					this.parentNode.innerHTML  = '<span class="btn btn-danger btn-xs">录取失败</span>';
		         				};
		         			};
		         		});
		         	};
		         }else if(typeAction == 'ctrl'){
		         	$('.modal-body').html('').append('你确认退档吗？');
		         	$('#id_ad_search').html('确认退档');
					document.getElementById('id_ad_search').onclick = function () {
						msgResoult(timer);//提示信息
						//doModifyAjax(obj,url,data,callBack)
						doModifyAjax(obj,ctrlMsgURL,data,function(reData){
		         			if(reData == true){
		         				msgResoult(timer,'退档成功');
		         				this.parentNode.parentNode.querySelectorAll('td')[5].innerHTML = '已退档';
		         				this.parentNode.innerHTMl = '<span class="btn btn-danger btn-xs">已退档</span>';
		         				if(this.getAttribute('isflag') == '2'){
		         					this.parentNode.innerHTML  = '<span class="btn btn-danger btn-xs">已退档</span>';
		         				};
		         			}else{
		         				msgResoult(timer,'退档失败');
		         				if(this.getAttribute('isflag') == '2'){
		         					this.parentNode.innerHTML  = '<span class="btn btn-danger btn-xs">退档失败</span>';
		         				};
		         			};
		         		});
					};
		         };
		    };
		    
		    function msgResoult(timer,msg){//封装提示信息
		    	var text = '正在提交，请耐心等待...';
		    	if(timer)clearTimeout(timer);
		    	if(msg)text=msg;
		    	loading({el:'tip',content:text});
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
    	<!-- 模态框（Modal） -->
		
		<script type="text/javascript">
			;!function(){//动态修改序号
				var list = document.querySelectorAll('.list-idx');
				for(var i=0;i<list.length;i++){
					list[i].innerHTML = i+1;
				}
			}();
			;!function(){
				var tdDoms = document.querySelectorAll('td[is-choose]');
				for(var i=0;i<tdDoms.length;i++){
					if(tdDoms[i].getAttribute('is-choose') == '1'){
						var lis = tdDoms[i].querySelector('button');
						//for(var j=0;j<lis.length;j++){
							//lis[j].style.display = 'none';
							lis.parentNode.innerHTML = '<span class="btn btn-success btn-xs">已录取过</span>';
						//};
					};
					if(tdDoms[i].getAttribute('is-choose') == '2'){
						var lis = tdDoms[i].querySelector('button');
						//for(var j=0;j<lis.length;j++){
							//lis[j].style.display = 'none';
							lis.parentNode.innerHTML = '<span class="btn btn-danger btn-xs">已退档过</span>';
						//};
					};
				}
			}();
		</script>
	<script type="text/javascript">
		var url = "<%= basePath%>/Ideal_IdealByTeacher.action?topicId=";                 //获取表单url
		var trDom = document.querySelector('tr>td');
		if(trDom && trDom.getAttribute('topicId')){
			var attr = trDom.getAttribute('topicId');
			url += attr;
		};
		//首页
		function first(){
		   window.location.href  = url+"&page=1";
		}
		//上一页
		function previous(){
		    window.location.href  = url+"&page=${teachLookIdealList.previousPageNumber}";
		}
		//下一页
		function next(){
		    window.location.href  = url+"&page=${teachLookIdealList.nextPageNumber}";
		}
		//尾页
		function last(){
		  window.location.href  = url+"&page=${teachLookIdealList.totalPage}";
		}
	</script>
	<!-- 分页 开始 -->
	<div class="limit">
		<!-- 记录信息 -->
		<div class="msg">
			<ul>
                <li>共<span>${teachLookIdealList.total}</span>条记录</li>
                <li>共<span>${teachLookIdealList.totalPage}</span>页</li>
                <li>当前显示第<span>${teachLookIdealList.page}</span>页</li>
            </ul>
		</div>
		<!-- 记录信息 -->
        <nav aria-label="Page navigation">
            <ul class="pagination">
            	<c:choose>
	            	<c:when test="${teachLookIdealList.isFirst==true}">
						<li><a href="javascript:;">首页</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:first()">首页</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${teachLookIdealList.isFirst==true}">
						<li><a href="javascript:;">上一页</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:previous()">上一页</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${teachLookIdealList.hasNext==true}">
						<li><a href="javascript:next()">下一页</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="javascript:;">下一页</a></li>
					</c:otherwise>
				</c:choose>
	            <c:choose>
					<c:when test="${teachLookIdealList.isLast==true}">
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
