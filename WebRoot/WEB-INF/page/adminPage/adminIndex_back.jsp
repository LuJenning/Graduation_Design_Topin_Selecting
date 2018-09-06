<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>管理员界面</title>
<style type="text/css">
	*{margin: 0;padding: 0;}
    a{text-decoration: none;color: #333;}
    ul,li{list-style: none;}
    body{font-size: 14px;font-family: "微软雅黑";}
    .container{text-align: center;}
    .container ul li{margin:0;height: 35px;line-height: 35px;padding:0;}
    .container ul li:nth-child(6){width:200px;}
</style>
<link href="<%=basePath %>/css/public-scoll.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=basePath %>/third/layui/css/layui.css">
<link type="text/css" rel="stylesheet" href="<%=basePath %>/css/loading.css">
<link href="<%=basePath %>/css/limit.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>/css/SpringIframe.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=basePath %>bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script src="http://apps.bdimg.com/libs/jquery/1.8.0/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/echarts.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/SpringIframe.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/SpringLimit.js"></script>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">毕业设计选题系统-管理员</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a href="">控制台</a></li>
				<li class="layui-nav-item"><a href="">商品管理</a></li>
				<li class="layui-nav-item"><a href="">用户</a></li>
				<li class="layui-nav-item"><a href="javascript:;">其它系统</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">邮件管理</a>
						</dd>
						<dd>
							<a href="">消息管理</a>
						</dd>
						<dd>
							<a href="">授权管理</a>
						</dd>
					</dl></li>
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="http://t.cn/RCzsdCq" class="layui-nav-img">admin
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="javascript:;" onmousedown="doIframe(this,{'url':'<%=basePath%>/SysUser_studentShow.action','tTitle':'Spring内置iframe'});">基本资料</a>
						</dd>
						<dd>
							<a href="">安全设置</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="<%=basePath%>Login_logout.action">退了</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree layui-bg-cyan layui-inline"
					lay-filter="test">
					<li class="layui-nav-item"><a href="javascript:;">学生管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;" onmousedown="doIframe(this,{'url':'<%=basePath%>/Tutor_openListForStudent.action','tTitle':'学生列表'});">学生列表</a>
							</dd>
						<dd>
								<a href="javascript:;" onmousedown="doIframe(this,{'url':'<%=basePath%>/SysUser_openRegister.action','tTitle':'添加学生'});">添加学生</a>
							</dd>
						</dl>
						</li>
					<li class="layui-nav-item layui-nav-itemed"><a class=""
						href="javascript:;">教师管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;" onmousedown="doIframe(this,{'url':'<%=basePath%>/Tutor_openAdd.action','tTitle':'创建教师'});">创建教师</a>
							</dd>
							
							<dd>
								<a href="javascript:;" onmousedown="doIframe(this,{'url':'<%=basePath%>/Tutor_openList.action','tTitle':'教师列表'});">教师列表</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;">学院管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="<%=basePath%>/Institute_institutePageList.action">da</a>
								<a href="javascript:;" onmousedown="doIframe(this,{'url':'<%=basePath%>/Institute_institutePageList.action','tTitle':'学院列表'});">学院列表</a>
							</dd>
							<dd>
								<a href="javascript:;" onmousedown="doIframe(this,{'url':'<%=basePath%>/Institute_jump.action','tTitle':'添加学院'});">添加学院</a>
							</dd>
							<dd>
								<a href="javascript:;" onmousedown="doIframe(this,{'url':'<%=basePath%>/Class_jump.action','tTitle':'添加班级'});">添加班级</a>
							</dd>
							<dd>
								<a href="">超链接</a>
							</dd>
						</dl></li>
						
					<li class="layui-nav-item"><a href="">云市场</a></li>
					<li class="layui-nav-item"><a href="">发布商品</a></li>
					<li class="layui-nav-item"><a href="javascript:;" onclick="administratorlimi('Administrator_administratorLimit.action',{'url':'Administrator_getMaxTotel.action','tableName':'SysUser','userType':1});">管理员分页列表</a></li>
				</ul>
			</div>
		</div>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div style="padding: 15px;">
				<!-- <div class="container">
			    	<ul class="row">
			    		<li class="col-sm-1">序号</li>
			    		<li class="col-sm-1">账号</li>
			    		<li class="col-sm-1">姓名</li>
			    		<li class="col-sm-1">密码</li>
			    		<li class="col-sm-1">性别</li>
			    		<li class="col-sm-1">出生日期</li>
			    		<li class="col-sm-1">类型</li>
			    		<li class="col-sm-1">电话</li>
			    		<li class="col-sm-1">籍贯</li>
			    		<li class="col-sm-1">操作</li>
			    	</ul>
			    </div>
			    <div administrator="administrator" class="container"></div>
				分页区域开始
				<div administratorlimi="administratorlimi"></div> -->
				<!-- 分页区域结束 -->
				<div style="width:50%;height:30px;float:left;">
					<h2>用户登录日志</h2>
					<div class="tzme" style="width:100%;height:500px;"></div>
				</div>
				<div style="width:50%;height:30px;float:right;">
					<h2>用户登录总量分析</h2>
					<div class="tzme-all" style="width:100%;height:500px;"></div>
				</div>
			    
				<script type="text/javascript">
					$(function(){
						setTimeout(function(){
							$.ajax({
				                url : "Administrator_loginMsg.action",
				                type : "POST",
				                async: true,
				                success : function(msg){
									var myEchar = echarts.init(document.getElementsByClassName('tzme')[0]);
									var option = msg; 
									myEchar.setOption(option);
				                	console.log(option);
				                },
				                error : function(er){
				                	console.log(er);
				                }
				            });
        				},1000);
					});
					$(function(){
						setTimeout(function(){
							$.ajax({
				                url : "Administrator_countSysUserAll.action",
				                type : "POST",
				                async: true,
				                success : function(msg){
									var myEchar = echarts.init(document.getElementsByClassName('tzme-all')[0]);
									var option = msg; 
									myEchar.setOption(option);
				                	console.log(option);
				                },
				                error : function(er){
				                	console.log(er);
				                }
				            });
        				},1000);
					});
				</script>
				<%-- <script type="text/javascript">
					function delSysUser(obj,mJson){
						$.ajax({
			                url : mJson.url,
			                type : "POST",
			                async:false,
			                data : {
			                    "sysUser.id" : mJson.id
			                },
			                success : function(msg){
			                	//console.log(msg);
			                	alert(msg.t.userName+msg.msg);
			                	var parent = obj.parentNode.parentNode;
			                	var parents = obj.parentNode.parentNode.parentNode;
			                	parents.removeChild(parent);
			                }
			            });
					};
				
					function getMaxTotel(mJson){
						var max = 0;
						$.ajax({
			                url : mJson.url,
			                type : "POST",
			                async:false,
			                data : {
			                    "tableName" : mJson.tableName,
			                    "userType" : mJson.userType
			                },
			                success : function(msg){
			                	max = msg;
			            		return max;
			                }
			            });
			            return max;
					};
					function administratorlimi(url,mjson){
						var max = getMaxTotel(mjson);
						var flag = true;
						var dom = new SpringLimitPage({'parentContainer':'div[administratorlimi="administratorlimi"]','idx':1,'max':max,'pageSize':10,'userType':mjson.userType});
				        /*
				            url : URL,callBack : 回调函数
				        */
				        if(flag){
				        	flag = false;
				        	dom.URLAction = url;
				        	dom.doLimitPage(0,dom.curentPageSize-1,function(data,statues){
				        		var html = '';
				        		var idx = 1;
				        		$('div[administrator="administrator"]').empty();
				        		(data.start == 0)?idx=0:idx=data.start;
				        		for(var i=0;i<data.SysUser.length;i++){
				        			idx = idx + 1;
									html += '<ul class="row">'
								    	   +'  <li class="col-sm-1">'+idx+'</li>'
								    	   +'  <li class="col-sm-1">'+data.SysUser[i].userId+'</li>'
								    	   +'  <li class="col-sm-1">'+data.SysUser[i].userName+'</li>'
								    	   +'  <li class="col-sm-1">'+data.SysUser[i].userPassword+'</li>'
								    	   +'  <li class="col-sm-1">'+data.SysUser[i].userSex+'</li>'
								    	   +'  <li class="col-sm-1">'+data.SysUser[i].userBirthday+'</li>'
								    	   +'  <li class="col-sm-1">'+data.SysUser[i].userType+'</li>'
								    	   +'  <li class="col-sm-1">'+data.SysUser[i].userPhone+'</li>'
								    	   +'  <li class="col-sm-1">'+data.SysUser[i].userNative+'</li>'
								    	   +'  <li class="col-sm-1">'
								    	   +'      <a href="javascript:;" onmousedown=doIframe(this,{"url":"<%=basePath%>/Institute_jump.action","tTitle":"添加学院"});>修改</a>'
								    	   +'      <a href="javascript:;" onclick=delSysUser(this,{"url":"<%=basePath%>/SysUser_delStudent.action","id":'+data.SysUser[i].id+'});>删除</a>'
								    	   +'  </li>'
								    	   +'</ul>';
				        		}
				        		$('div[administrator="administrator"]').append(html);
				        	});
				        };
				        dom.init(url,function(data,statues){
				            if(statues == 200){
				                //console.log(data);
				                var html = '';
				        		var idx = 1;
				        		$('div[administrator="administrator"]').empty();
				        		(data.start == 0)?idx=0:idx=data.start;
				        		for(var i=0;i<data.SysUser.length;i++){
				        			idx = idx + 1;
									html += '<ul class="row">'
								    	   +'  <li class="col-sm-1">'+idx+'</li>'
								    	   +'  <li class="col-sm-1">'+data.SysUser[i].userId+'</li>'
								    	   +'  <li class="col-sm-1">'+data.SysUser[i].userName+'</li>'
								    	   +'  <li class="col-sm-1">'+data.SysUser[i].userPassword+'</li>'
								    	   +'  <li class="col-sm-1">'+data.SysUser[i].userSex+'</li>'
								    	   +'  <li class="col-sm-1">'+data.SysUser[i].userBirthday+'</li>'
								    	   +'  <li class="col-sm-1">'+data.SysUser[i].userType+'</li>'
								    	   +'  <li class="col-sm-1">'+data.SysUser[i].userPhone+'</li>'
								    	   +'  <li class="col-sm-1">'+data.SysUser[i].userNative+'</li>'
								    	   +'  <li class="col-sm-1">'
								    	   +'      <a href="javascript:;" onmousedown=doIframe(this,{"url":"<%=basePath%>/Institute_jump.action","tTitle":"添加学院"});>修改</a>'
								    	   +'      <a href="javascript:;" onclick=delSysUser(this,{"url":"<%=basePath%>/SysUser_delStudent.action","id":'+data.SysUser[i].id+'});>删除</a>'
								    	   +'  </li>'
								    	   +'</ul>';
				        		}
				        		$('div[administrator="administrator"]').append(html);
				                return true;
				            }else if(statues == 500){
				                console.log(data);
				                console.log('服务器错误');
				            }
				        });
					};
				</script> --%>
			</div>
		</div>
		<div class="layui-footer">
			<!-- 底部固定区域 -->
			底部固定区域
		</div>
	</div>
	<script src="third/layui/layui.js"></script>
	<script>
		//JavaScript代码区域
		layui.use('element', function() {
			var element = layui.element;

		});
	</script>
</body>
</html>
<!-- <!DOCTYPE html> -->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="Author" content=" ">
    <title>Document</title>
    <link rel="stylesheet" href="<%=basePath %>/css/public-scoll.css">
    <link rel="stylesheet" href="<%=basePath %>/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link href="<%=basePath %>/css/SpringIframe.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        *{margin: 0;padding: 0;}
        a{text-decoration: none;color: #333;}
        ul,li{list-style: none;}
        body{font-size: 14px;font-family: "微软雅黑";padding-top:50px;}
        .ds-n{display: none;}
        /* start header */
        header{position:fixed;top:0;left:0;width:100%;height:50px;color:#ccc;background-color: rgba(60,75,85,.9);z-index: 999;}
        header h2{margin:0;padding-left:20px;line-height: 50px;font-size: 18px;}
        header .user{position: absolute;right: 80px;top: 8px;}
        header .user .btn.focus, .btn:focus, .btn:hover{color: #ccc;}
        header .user .dropdown-menu{left:-40px;}
        header .logout{position: absolute;right: 30px;top: 8px;height: 34px;line-height: 34px;transition: .5s;}
        header .logout a{color: #aaa;}
        header .logout a:hover{color: #ccc;}
        /* end header */

        /* start left */
        .left{float:left;width:180px;height: 500px;background-color: #f6f9fa;border-top: 1px solid #ccc;border-right: 1px solid #ccc;}
        .left .nav-item h3, .left .nav-item ul li{padding-left:15px;margin:0;vertical-align: top;cursor: pointer;color:#666;font-size: 14px;height:35px;line-height: 35px;transition: 0.5s;border-bottom: 1px solid #ccc;}
        .left .nav-item ul{margin-bottom:0;}
        .left .nav-item h3:hover, .left .nav-item ul li:hover{background-color: #40d0a7;}
        .left .nav-item ul li a{display: block;height: 100%;width: 100%;padding: 0;margin: 0;line-height: 35px;color: inherit;text-decoration:none;}
        .bg-c{background-color: rgb(60,200,170,.7) !important/* #40d0a7 */;}
        .bg-df{background-color: rgb(217,236,235);}
        .fl-r{margin-right:5px;margin-top: 17px;float: right;display: block;border-width: 5px;}
        /* end left */

        /* start body */
        .body{margin-left:180px;height: 100vh;}
        .body .body-frame{display: block;padding:0;margin: 0;border: 0;width: 100%;height: 100%;}
        /* end body */

        /* start footer */
        footer{position:fixed;left:0;bottom:0;width:100%;height: 35px;background-color: rgba(60,75,85,.9);z-index: 999;color: #666;text-align: center;}
        footer p{height:35px;line-height: 35px;cursor: pointer;transition:.5s;color:#ccc;}
        footer p:hover{color: #40a6a7;}
        /* end footer */
    </style>
    <script src="<%=basePath%>js/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>/js/SpringIframe.js"></script>
    <script type="text/javascript">
        document.createElement('header');
        document.createElement('section');
        document.createElement('footer');
    </script>
</head>
<body>
    <!-- start header -->
    <header>
        <h2>毕业设计选题系统-管理员</h2>
        <div class="user">
            <div class="dropdown">
                <div class="btn dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    admin
                    <span class="caret"></span>
                </div>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                    <li><a href="javascript:;" onmousedown="doIframe(this,{'url':'<%=basePath%>/SysUser_studentShow.action','tTitle':'Spring内置iframe'});">基本资料</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="#">Separated link</a></li>
                </ul>
            </div>
        </div>
        <div class="logout"><a href="<%=basePath%>Login_logout.action">退出</a></div>
    </header>
    <!-- end header -->
    <!-- start left -->
    <section class="left">
        <div class="nav-item">
            <h3 class="bg-df bg-c"><i class="glyphicon glyphicon-th-list"></i>课题管理<span class="fl-r caret"></span></h3>
            <ul class="ds-n">
                <li><a href="<%=basePath%>/Topic_openAdd.action" target="iframe">发布课题</a></li>
                <li><a href="<%=basePath%>/Topic_openList.action" target="iframe">课题列表</a></li>
                <li><a href="javascript:;">功能三</a></li>
                <li><a href="javascript:;">功能四</a></li>
                <li><a href="javascript:;">功能五</a></li>
            </ul>
        </div>
        <div class="nav-item">
            <h3  class="bg-df"><i class="glyphicon glyphicon-th-list"></i>下拉功能二<span class="fl-r caret"></span></h3>
            <ul class="ds-n">
                <li><a href="javascript:;">功能一</a></li>
                <li><a href="javascript:;">功能二</a></li>
                <li><a href="javascript:;">功能三</a></li>
                <li><a href="javascript:;">功能四</a></li>
                <li><a href="javascript:;">功能五</a></li>
            </ul>
        </div>
        <div class="nav-item">
            <h3  class="bg-df"><i class="glyphicon glyphicon-th-list"></i>下拉功能三<span class="fl-r caret"></span></h3>
            <ul class="ds-n">
                <li><a href="javascript:;">功能一</a></li>
                <li><a href="javascript:;">功能二</a></li>
                <li><a href="javascript:;">功能三</a></li>
                <li><a href="javascript:;">功能四</a></li>
                <li><a href="javascript:;">功能五</a></li>
            </ul>
        </div>
    </section>
    <script type="text/javascript">
        ;!function(){
            $(function(){
                $('.nav-item h3').each(function(i,j){
                    $(j).click(function(){
                        $(this).parents('.nav-item').siblings().each(function(i,j){
                            $(j).find('h3').removeClass('bg-c dropup');
                            $(j).find('ul').hide(300);
                        });
                        $(this).siblings().show(300);
                        $(this).addClass('bg-c dropup');
                    });
                });
            });
            $(function(){
                $('.nav-item li').each(function(i,j){
                    $(j).click(function(){
                        $(this).addClass('bg-c');
                        $(this).siblings().removeClass('bg-c');
                    });
                });
            });
        }();
    </script>
    <!-- end left -->
    <!-- start body-->
    <section class="body">
        <iframe name="iframe" class="body-frame" src="" frameborder="0"></iframe>
    </section>
    <!-- end body-->
    <!-- start footer-->
    <footer>
        <p>Copyright © 2018 All Rights Reserved <span>Design by spring</span></p>
    </footer>
    <!-- end footer-->
    <script type="text/javascript">
        ;!function(){
            var header = document.querySelector('header');
            var left = document.querySelector('section[class="left"]');
            var body = document.querySelector('section[class="body"]');
            var footer = document.querySelector('footer');
            body.style.height = left.style.height = this.innerHeight - header.clientHeight - footer.clientHeight + "px";
            window.onresize = function(){
                body.style.height = left.style.height = this.innerHeight - header.clientHeight - footer.clientHeight + "px";
            };
        }();
    </script>
</body>
</html>