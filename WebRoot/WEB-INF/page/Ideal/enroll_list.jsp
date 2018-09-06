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
</head>
<body class="user-select">
	<!-- 位置信息 -->
	<div class="position">
        <ul>
            <li>教师系统&gt;</li>
            <li>录取名单&gt;</li>
        </ul>
    </div>
    	<table> 
	        <!-- 信息头 -->
	        <thead>
	             <tr>
	             	<th>序号</th>
	                 <th>学生学号</th>
	                 <th>学生姓名</th>
	                 <th>所选课题</th>
	                 <th>志愿类型</th>
	                 <th>所在专业</th>
	                 <th>志愿状态</th>
	             </tr>
	         </thead>
	        <!-- 记录信息列表 -->
	        <tbody>
		        <s:iterator value="teachLookIdealList.data">
		            <tr>
	                    <td class="list-idx">0</td>
	                    <td>${userId}</td>
	                    <td><a href="<%=basePath%>Tutor_openShow.action?userId=${userId}">${userName}</a></td>
	                    
	                   	<td>${topicTitle}</td>
	                  	<td><s:if test="idealType=='first'">第一志愿</s:if>
	                   <s:else>第二志愿</s:else></td>
	                   	<td>${instituteName}</td>
	                    <td>
	                    <s:if test="isAccept==1"><button type="button" class="btn btn-success btn-xs">已录取</button></s:if>
	                    	<s:else><button type="button" class="btn btn-success btn-xs">看见这个是不可能的，除非bug</button></s:else>
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
	<script type="text/javascript">
		var url = "<%= basePath%>/Ideal_enrollList.action";                 //获取表单url
		var trDom = document.querySelector('tr>td');
		if(trDom && trDom.getAttribute('topicId')){
			var attr = trDom.getAttribute('topicId');
			url += attr;
		};
		//首页
		function first(){
		   window.location.href  = url+"?page=1";
		}
		//上一页
		function previous(){
		    window.location.href  = url+"?page=${teachLookIdealList.previousPageNumber}";
		}
		//下一页
		function next(){
		    window.location.href  = url+"?page=${teachLookIdealList.nextPageNumber}";
		}
		//尾页
		function last(){
		  window.location.href  = url+"?page=${teachLookIdealList.totalPage}";
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
