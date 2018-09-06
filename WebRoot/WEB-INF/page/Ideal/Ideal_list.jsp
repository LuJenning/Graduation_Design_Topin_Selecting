<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=basePath%>">
	<title>学生查看课题列表</title>
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
        .seach{width: 500px;margin-bottom: 10px;position:relative;float: left;}
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
        thead tr th:nth-last-child(2),tbody tr td:nth-last-child(2){width: 120px;}
        thead tr th:nth-last-child(1),tbody tr td:nth-last-child(1){width:200px;}
        .limit{margin:20px 10px 0 10px;}
        .limit .msg{float:left;}
        .limit .msg ul li{float:left;height:30px;line-height:30px;font-size:15px;color:#337ab7;}
        .limit nav{float: right;}
        .limit nav ul{margin:0;}
        .mar-t{margin-top:10px;}
        .on-see-tip{position:fixed;top:0;left:0;display: block;padding:8px;windth:300px;overflow:hidden;text-overflow: ellipsis;white-space: nowrap;background-color: #fa6;border-radius: 8px;cursor:pointer;}
		.seach-save-box{
            position: fixed;
            bottom: 0;
            left: 0;
            width: 408px;
            background-color: #353c47;
            color: #ccc;
        }
        .seach-save-box h2{text-align:center;border-bottom: 1px solid #fff;margin:0;font-size: 20px;}
        .seach-save-box ul,.seach-save-box ol{border-bottom:1px solid #ccc;float: left;margin:0;height: 25px;}
        .seach-save-box ul li,.seach-save-box ol li{float: left;width: 102px;text-align: center;border:1px solid #ccc;line-height: 25px;overflow:hidden;text-overflow: ellipsis;white-space: nowrap;}
        .seach-save-box ul li:nth-child(1),.seach-save-box ol li:nth-child(1),
        .seach-save-box ul li:nth-child(2),.seach-save-box ol li:nth-child(2),
        .seach-save-box ul li:nth-child(3),.seach-save-box ol li:nth-child(3),
        .seach-save-box ul li:nth-child(4),.seach-save-box ol li:nth-child(4){border-right-color: transparent;border-top-color: transparent;}
        .seach-save-box ul li:nth-child(1),.seach-save-box ol li:nth-child(1){border-left-color: transparent;}
    </style>
    <link rel="stylesheet" href="<%=basePath %>/css/public-scoll.css">
    <link href="<%=basePath %>/css/SpringIframe.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="<%=basePath%>/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="<%=basePath %>/css/loading.css">
    <script src="<%=basePath%>js/jquery/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath%>bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>/js/SpringIframe.js"></script>
    <script src="<%=basePath%>js/lodaing.js"></script>
    <script type="text/javascript">
        document.createElement('nav');
    </script>
</head>
<body class="user-select">
	<!-- 位置信息 -->
	<div class="position">
        <ul>
            <li>学生系统&gt;</li>
            <li>课题相关&gt;</li>
            <li>课题列表&gt;</li>
        </ul>
    </div>
    <!-- 位置信息 -->
    <div class="wrap">
    	<!-- 查询-->
        <div class="seach">
        	<form action="<%=basePath%>Topic_openList.action" method="post">
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
        <div style="float:right;width:300px;float:left;margin-left:100px;">
            <span class="btn btn-success">已选</span>
            <span class="btn btn-info">未选</span>
        </div>
        <!-- 查询-->
    
    	<table>
	        <!-- 信息头 -->
	        <thead>
	             <tr>
	                 <th>序号</th>
	                 <th>题目编号</th>
	                 <th>题目标题</th>
	                 <th>发布时间</th>
	                 <th>课题余量/总量</th>
	                 <th>涉及专业</th>
	                 <th>发布者</th>
	                 <th>填报志愿</th>
	             </tr>
	         </thead>
	         <!-- 信息头 -->
	        <!-- 记录信息列表 -->
	        <tbody>
		        <s:iterator value="pageResult.data">
		        	<s:if test="intNelen<topicSurplus">
			            <tr>
		                    <td class="list-idx">0</td>
		                    <td on-see-tip="${topicId}">${topicId}</td>
		                    <td class="topic-topicTitle" on-see-tip="点击查看更多课题信息">
		                    	<a href="javascript:;" onmousedown="doIframe(this,{'url':'<%=basePath %>Topic_openShow.action?id=${topicInfoId}','tTitle':'课题详情'});">${topicTitle }</a>
		                    </td>
		                    <td on-see-tip="${issueDate}">${issueDate}</td>
		                    <td class="topic-topicSurplus" on-see-tip="${topicSurplus - intNelen}/${topicSurplus}">${topicSurplus - intNelen}/${topicSurplus}</td>
		                    <td class="topic-instituteName" on-see-tip="${instituteName}">${instituteName}</td>
		                    <td class="topic-instituteName" on-see-tip="点击查看教师详细信息">
		                    	<a href="javascript:;" onmousedown="doIframe(this,{'url':'<%=basePath %>Tutor_openShow.action?userId=${userId}','tTitle':'教师详情'});">${userName }</a>
		                    </td>
		                    <td class="seach-box">
		                    	<input type="button" class="btn btn-info btn-xs" seach-first="true" seach-check="false" onclick="show_modal(this,'第一志愿填报','first',${topicInfoId },${topicId });" value="第一志愿" />
		                    	<input type="button" class="btn btn-info btn-xs" seach-second="true" seach-check="false" onclick="show_modal(this,'第二志愿填报','second',${topicInfoId },${topicId });" value="第二志愿"/>
		                    </td>
		                </tr>
		            </s:if>
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
			function doTip(timer,contentValue){
				if(timer)clearTimeout(timer);
                   loading({el:'tip',content:contentValue});
                   timer = setTimeout(function(){
   					$('#tip').slideUp(1500);
   				},1500);
			};
			;!function(){//绑定提示信息
	            var lis = document.querySelectorAll('tr>td');
	            for(var i=0;i<lis.length;i++){
	                lis[i].onmouseenter = function(ev){
	                    var event = ev || window.event;
	                    if(this.getAttribute('on-see-tip')){
		                    var oldDom = document.getElementById('on-see-tip');
		                    if(oldDom)oldDom.parentNode.removeChild(oldDom);
		                    var newDom = document.createElement('p');
		                    newDom.id = 'on-see-tip';
		                    newDom.className = 'on-see-tip';
		                    newDom.innerHTML = this.getAttribute('on-see-tip');
		                    document.body.appendChild(newDom);
		                    document.onmousemove = function(ev){
		                        var ev = ev || window.event;
		                        var ll = ev.clientX,
		                            tl = ev.clientY;
		                            newDom.style.left = ll + 10 +'px';
		                            newDom.style.top = tl + 8 +'px';
		                    };
		                };
	                };
	                lis[i].onmouseleave = function(){
	                    var oldDom = document.getElementById('on-see-tip');
	                    if(oldDom)oldDom.parentNode.removeChild(oldDom);
	                    document.onmousemove = null;
	                };
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
		var addIdealURL = '<%=basePath %>Ideal_saveIdealByAjax.action',
			seachIdealURL = '<%=basePath %>Ideal_seacherIsSelectIdeal.action',
		    timer = null;
			function show_modal(obj,title,typeAction,values,seachValue){
				firstFlag = false;
				if(obj.getAttribute('seach-check') == 'true' && obj.getAttribute('seach-first')=='true'){
				 	if(timer)clearTimeout(timer);
                    loading({el:'tip',content:'您已报考过第一志愿'});
                    timer = setTimeout(function(){
    						$('#tip').slideUp(1500);
    				},1500);
    				obj.style.backgroundColor = "#5cb85c";
    				return;
				};
				if(obj.getAttribute('seach-check') == 'true' && obj.getAttribute('seach-second')=='true'){
				 	if(timer)clearTimeout(timer);
                    loading({el:'tip',content:'您已报考过第二志愿'});
                    timer = setTimeout(function(){
    						$('#tip').slideUp(1500);
    				},1500);
    				obj.style.backgroundColor = "#5cb85c";
    				return;
				};
				//doAddIdealAjax(obj,url,data,callBack)
				 var seachFlag = false;
				 var seachData = {
				 	"ideal.topicId":seachValue
				 };
				 doAddIdealAjaxNotAsync(obj,seachIdealURL,seachData,function(msg){
				 	//console.log("seachData-->msg:"+typeof msg +"---" + msg);
				 	//console.log(null == msg)
				 	console.log(msg)
				 	if(null != msg.data){
				 		var text = msg.data[0].idealType;
				 		var lisDoms = document.querySelectorAll("tr>td>input[type='button']");
				 		switch(text){
				 			case 'first':
					 			for(var i=0;i<lisDoms.length;i++){
					 				if(lisDoms[i].getAttribute('seach-first')){
					 					lisDoms[i].setAttribute('seach-check', 'true');
					 				};
					 			};
				 			break;
				 			case 'second':
				 				for(var i=0;i<lisDoms.length;i++){
					 				if(lisDoms[i].getAttribute('seach-second')){
					 					lisDoms[i].setAttribute('seach-check', 'true');
					 				};
					 			};
				 			break;
				 		};
				 	}else{
				 		firstFlag = true;
				 	};
				 	if(msg.falg == true)seachFlag = true;
				 });
				 if(seachFlag){
				 	if(timer)clearTimeout(timer);
                    loading({el:'tip',content:'您已报考过该志愿'});
                    timer = setTimeout(function(){
    						$('#tip').slideUp(1500);
    				},1500);
    				obj.style.backgroundColor = "#5cb85c";
    				return;
				 };
		         $('#myModal').modal('show');
	         	 if(title)$('#myModalLabel').html(title);
	         	 var lis = obj.parentNode.parentNode.querySelectorAll('td');
		         if(typeAction == 'first'){
		         	$('#id_ad_search').html('确认填报第一志愿');
		         	$('.modal-body').html('');
		         	var html = '<div class="input-group mar-t">'
							+'    <span class="input-group-addon">题目编号</span>'
							+'	  <input type="text" class="form-control" value="'+lis[1].innerText+'" name="userId" readonly>'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon">题目标题</span>'
							+'	  <input type="text" class="form-control" value="'+lis[2].innerText+'" name="userName" readonly>'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon">发布时间</span>'
							+'	  <input type="text" class="form-control" value="'+lis[3].innerText+'" name="userPassword" readonly>'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon">余量/总量</span>'
							+'	  <input type="text" class="form-control" value="'+lis[4].innerText+'" name="userPassword" readonly>'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon">涉及专业</span>'
							+'	  <input type="text" class="form-control" value="'+lis[5].innerText+'" name="userPassword" readonly>'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon">发布教师</span>'
							+'	  <input type="text" class="form-control" value="'+lis[6].innerText+'" name="userPhone" readonly>'
							+'	</div>';
		         	$('.modal-body').html(html);
					//绑定修改按钮
					document.getElementById('id_ad_search').onclick = function () {
						console.log(values);
			         	var data = {
        					'topic.id':values,
        					'type':typeAction
        				};
                        if(timer)clearTimeout(timer);
                        loading({el:'tip',content:'正在填报，请耐心等待...'});
                        timer = setTimeout(function(){
        						$('#tip').slideUp(1500);
        				},1500);
						doAddIdealAjax(obj,addIdealURL,data,function(backData){
							//console.log(backData);
	        				if(backData){
	        					obj.style.backgroundColor = "#5cb85c";
	        					var dom = obj.parentNode.parentNode.querySelectorAll('td')[4];
	        					var text = dom.innerHTML;
	        					var arrText = text.split('/');
	        					arrText[0] -=1;
	        					dom.innerHTML = arrText[0]+"/"+arrText[1];
	        					if(timer)clearTimeout(timer);
		                        loading({el:'tip',content:'填报第一志愿成功，等待教师审核'});
		                        timer = setTimeout(function(){
		        						$('#tip').slideUp(1500);
		        						if(firstFlag)window.location.reload();
		        				},1500);
	        				};
	         			});
					};
		         }else if(typeAction == 'second'){
		         	$('#id_ad_search').html('确认填报第二志愿');
		         	$('.modal-body').html('');
		         	var html = '<div class="input-group mar-t">'
							+'    <span class="input-group-addon">题目编号</span>'
							+'	  <input type="text" class="form-control" value="'+lis[1].innerText+'" name="userId" readonly readonly>'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon">题目标题</span>'
							+'	  <input type="text" class="form-control" value="'+lis[2].innerText+'" name="userName" readonly>'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon">发布时间</span>'
							+'	  <input type="text" class="form-control" value="'+lis[3].innerText+'" name="userPassword" readonly>'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon">余量/总量</span>'
							+'	  <input type="text" class="form-control" value="'+lis[4].innerText+'" name="userPassword" readonly>'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon">涉及专业</span>'
							+'	  <input type="text" class="form-control" value="'+lis[5].innerText+'" name="userPassword" readonly>'
							+'	</div>'
							+'  <div class="input-group mar-t">'
							+'    <span class="input-group-addon">发布教师</span>'
							+'	  <input type="text" class="form-control" value="'+lis[6].innerText+'" name="userPhone" readonly>'
							+'	</div>';
		         	$('.modal-body').html(html);
					//绑定修改按钮
					document.getElementById('id_ad_search').onclick = function () {
						console.log(values);
                        var data = {
        					'topic.id':values,
        					'type':typeAction
        				};
                        if(timer)clearTimeout(timer);
                        loading({el:'tip',content:'正在填报，请耐心等待...'});
                        timer = setTimeout(function(){
        						$('#tip').slideUp(1500);
        				},1500);
						doAddIdealAjax(obj,addIdealURL,data,function(backData){
							console.log(backData);
	        				if(backData){
	        					obj.style.backgroundColor = "#5cb85c";
	        					var dom = obj.parentNode.parentNode.querySelectorAll('td')[4];
	        					var text = dom.innerHTML;
	        					var arrText = text.split('/');
	        					arrText[0] -=1;
	        					dom.innerHTML = arrText[0]+"/"+arrText[1];
	        					if(timer)clearTimeout(timer);
		                        loading({el:'tip',content:'填报第二志愿成功，等待教师审核'});
		                        timer = setTimeout(function(){
		        						$('#tip').slideUp(1500);
		        						if(firstFlag)window.location.reload();
		        				},1500);
	        				};
	         			});
					};
		         };
		    };
	    function doAddIdealAjax(obj,url,data,callBack){
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
	    function doAddIdealAjaxNotAsync(obj,url,data,callBack){
	    	$.ajax({
                url:url,
                data:data,
                type : "POST",
        		async:false,
        		success : function(msg){
        			callBack && callBack.call(obj,msg);
    			},
    			error : function(er){
    				callBack && callBack.call(obj,er);
    			}
            });
	    };
	</script>
    <!-- <div class="seach-save-box user-select">
    	<h2>已选志愿表</h2>
        <ul>
            <li>题目编号</li>
            <li>题目标题</li>
            <li>发布者</li>
            <li>志愿</li>
        </ul>
        <script type="text/javascript">
        	;!function(){
        		var seachBox = document.querySelector('.seach-save-box');
        		if(sessionStorage.getItem('seach-first')){
					seachBox.innerHTML += '<ol>'
								        +'    <li>'+sessionStorage.getItem('num1')+'</li>'
								        +'    <li>'+sessionStorage.getItem('title1')+'</li>'
								        +'    <li>'+sessionStorage.getItem('teacher1')+'</li>'
								        +'    <li>'+sessionStorage.getItem('zhiyuan1')+'</li>'
								        +' </ol>';
				};
				if(sessionStorage.getItem('seach-second')){
					seachBox.innerHTML += '<ol>'
								        +'    <li>'+sessionStorage.getItem('num2')+'</li>'
								        +'    <li>'+sessionStorage.getItem('title2')+'</li>'
								        +'    <li>'+sessionStorage.getItem('teacher2')+'</li>'
								        +'    <li>'+sessionStorage.getItem('zhiyuan2')+'</li>'
								        +' </ol>';
				};
        	}();
        </script>
    </div> -->
    <!-- ---------- -->
<%--
	<td><a href="<%=basePath %>Topic_openShow.action?id=${topicInfoId}">${topicTitle }</a></td>
	<td>${issueDate }</td>
	<td><a href="<%=basePath %>Tutor_openShow.action?userId=${userId}">${userName }</a></td>
	<td>${topicSurplus }</td>
</tr>
 --%>

	<!-- 分页开始 -->
	<script type="text/javascript">
		var url = "<%= basePath%>/Topic_openList.action";                 //获取表单url
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
