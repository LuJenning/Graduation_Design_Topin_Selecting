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
        thead tr th:nth-child(2),tbody tr td:nth-child(2){width: 200px;}
        thead tr th:nth-last-child(3),tbody tr td:nth-last-child(3),
        thead tr th:nth-last-child(1),tbody tr td:nth-last-child(1){width:120px;}
        .limit{margin:20px 10px 0 10px;}
        .limit .msg{float:left;}
        .limit .msg ul li{float:left;height:30px;line-height:30px;font-size:15px;color:#337ab7;}
        .limit nav{float: right;}
        .limit nav ul{margin:0;}
        .mar-t{margin-top:10px;}
        .on-see-tip{position:fixed;top:0;left:0;display: block;padding:8px;windth:300px;overflow:hidden;text-overflow: ellipsis;white-space: nowrap;background-color: #fa6;border-radius: 8px;cursor:pointer;}
    </style>
    <link rel="stylesheet" href="<%=basePath %>/css/public-scoll.css">
    <link href="<%=basePath %>/css/SpringIframe.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="<%=basePath%>/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="<%=basePath %>/css/loading.css">
    <script src="<%=basePath%>js/jquery/jquery-1.9.1.min.js"></script>
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
            <li>志愿填报&gt;</li>
            <li>志愿审核&gt;</li>
        </ul>
    </div>
    <!-- 位置信息 -->
    <div class="wrap">
        <!-- 查询-->
        <div class="seach">
            <form action="<%=basePath%>Ideal_IdealByStudent.action" method="post" onsubmit="return false;">
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
        <table>
            <!-- 信息头 -->
            <thead>
                 <tr>
                     <th>序号</th>
                     <th>课题名称</th>
                     <th>课题发布人</th>
                     <th>志愿类型</th>
                     <th>志愿填选时间</th>
                     <th>录取情况</th>
                 </tr>
             </thead>
             <!-- 信息头 -->
            <!-- 记录信息列表 -->
            <tbody>
                <s:iterator value="stuIdealList">
                    <tr>
                        <td class="list-idx">0</td>
                        <td on-see-tip="${topicTitle}">${topicTitle}</td>
                        <td class="topic-topicTitle" on-see-tip="${userName}">${userName}</td>
                        <td on-see-tip="${idealType}" ideal-type="ideal-type">${idealType}</td>
                        <td class="topic-topicSurplus" on-see-tip="${selectDate}">${selectDate}</td>
                        <td class="topic-instituteName" on-see-tip="${isAccept}" is-accept="is-accept">${isAccept}</td>
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
                	lis[i].idx = i;
                	if(lis[i].getAttribute("ideal-type")){
                		var text = $.trim(lis[i].innerText);
                		switch(text){
                			case 'first':
                				lis[i].innerText = '第一志愿';
                				break;
                			case 'second':
                				lis[i].innerText = '第二志愿';
                				break;
                		};
                	};
                	if(lis[i].getAttribute("is-accept")){
                		var text = $.trim(lis[i].innerText);
                		switch(text){
                			case '0':
                				lis[i].innerHTML = '<span style="font-weight:bold;color: #d9534f;font-size:16px;">未录取</span>';
                				lis[i].setAttribute('on-see-tip','未录取');
                				break;
                			case '1':
                				lis[i].innerHTML = '<span style="font-weight:bold;color: #398439;font-size:16px;">已录取</span>';
                				lis[i].setAttribute('on-see-tip','已录取');
                				break;
                			case '2':
                				lis[i].innerHTML = '<span style="font-weight:bold;color: #d9534f;font-size:16px;">退档</span>';
                				lis[i].setAttribute('on-see-tip','退档');
                				break;
                			case '3':
                				lis[i].innerHTML = '<span style="font-weight:bold;color: #269abc;font-size:16px;">等待录取</span>';
                				lis[i].setAttribute('on-see-tip','等待录取');
                				break;
                		};
                	};
                    lis[i].onmouseenter = function(ev){
                        var event = ev || window.event;
                        if(this.getAttribute('on-see-tip')){
                            var oldDom = document.getElementById('on-see-tip');
                            if(oldDom)oldDom.parentNode.removeChild(oldDom);
                            var newDom = document.createElement('p');
                            newDom.id = 'on-see-tip';
                            newDom.className = 'on-see-tip';
                            document.body.appendChild(newDom);
                            newDom.innerHTML = this.getAttribute('on-see-tip');
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
</body>
</html>