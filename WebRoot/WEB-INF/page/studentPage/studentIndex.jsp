<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="Author" content=" ">
    <title>学生界面</title>
    <link rel="stylesheet" href="<%=basePath %>/css/public-scoll.css">
    <link type="text/css" rel="stylesheet" href="<%=basePath %>/css/loading.css">
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
        <h2>毕业设计选题系统-学生</h2>
        <div class="user">
            <div class="dropdown">
                <div class="btn dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    ${sessionScope.SysUser.userName}
                    <span class="caret"></span>
                </div>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                    <li>
                    	<a href="javascript:;" onmousedown="doIframe(this,{'url':'<%=basePath%>/SysUser_studentShow.action','tTitle':'基本资料'});">基本资料</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="logout"><a href="<%=basePath%>Login_logout.action">退出</a></div>
    </header>
    <!-- end header -->
    <!-- start left -->
    <section class="left">
        <div class="nav-item">
            <h3 class="bg-df bg-c"><i class="glyphicon glyphicon-th-list"></i>课题相关<span class="fl-r caret"></span></h3>
            <ul class="ds-n">
                <li><a href="<%=basePath %>Topic_openList.action" target="iframe">查看课题</a></li>
                <li><a href="<%=basePath%>/Ideal_IdealByStudent.action" target="iframe">查看已选志愿信息</a></li>
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
        <iframe name="iframe" class="body-frame" src="<%=basePath %>/Ideal_defaultInform.action" frameborder="0"></iframe>
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