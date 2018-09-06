<%@ page language="java" import="java.util.*,com.gdts.selecting.util.ResourceUtil" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>登录</title>
    <link type="text/css" rel="stylesheet" href="<%=basePath %>/css/login.css">
    <link type="text/css" rel="stylesheet" href="<%=basePath %>/css/loading.css">
    <script src="<%=basePath%>js/jquery/jquery-1.9.1.min.js"></script>
</head>
<body>
	<script type="text/javascript">
		/* var allResource={
				key:value,
				key1:values1,
				key2:values2			
		} */
		var rs ="${allResource }";
		rs=rs.substring(1,rs.length-1);
		var arr = rs.split(',');
		var arr1 = [];
		var arr2 = [];
		for(var i=0;i<arr.length;i++){
			var temp = arr[i].split('='); 
			arr1.push($.trim(temp[0]));
			arr2.push($.trim(temp[1]));
		};
		var allResource = {},
			html = "{";
		for(var i=0;i<arr1.length;i++){
			html += '"'+arr1[i]+'":"'+arr2[i]+'",';
		};
		html = html.substring(0,html.length-1);
		html += "}";
		//console.log(html);
		allResource =  $.parseJSON(html);
		var _path = "<%=basePath %>";
	</script>
    <div class="wrap">
        <div id="container" class="container">
            <h1>Welcome</h1>
	        <input type="text" autofocus="autofocus" name="userId" placeholder="user login"/>
	        <input type="password" name="userPassword" placeholder="password"/>
            <a href="javascript:;" >Login</a>
            <!-- <a href="Institute_jump.action">学院添加页面</a>
            <a href="Class_jump.action">班级添加页面</a>
            <a href="SysUser_openRegister.action">学生注册页面</a>
            <a href="Administrator_administratorLimit.action?start=0&end=5">分页测试</a>
            <a href="Administrator_loginMsg.action">登录日志</a> -->
        </div>
        <script type="text/javascript">
        	;!function(){
        		document.querySelector('h1').innerHTML = allResource.loginJsp_titleNull_msg;
        		document.querySelector('input[type="text"]').placeholder = allResource.loginJsp_userNameNull_msg;
        		document.querySelector('input[type="password"]').placeholder = allResource.loginJsp_passwordNull_msg;
        		document.querySelector('a').innerHTML = allResource.sysmanage_loginJSp_loginBtn;
        	}();
        	$(function(){
        		//console.log(_path);
        		var timer = null;
        		function doLogin(){
        			var userId = $('input[name="userId"]').val().trim();
        			var userPassword = $('input[name="userPassword"]').val().trim();
        			if(timer)clearTimeout(timer);
        			if(userId.length == 0){
        				$('input[name="userId"]').focus();
        				loading({el:'tip',content:'账号不能为空！'});
        				timer = setTimeout(function(){
        						$('#tip').slideUp(1500).delay(1500);
        				},1000);
        				return;
        			}
        			if(userPassword.length == 0){
        				$('input[name="userPassword"]').focus();
        				loading({el:'tip',content:'密码不能为空！'});
        				timer = setTimeout(function(){
        						$('#tip').slideUp(1500).delay(1500);
        				},1000);
        				return;
        			}
        			loading({el:'tip',content:'正在登陆，请稍等'});
        			$.ajax({
        				url : "<%=basePath%>Login_doLogin.action",
        				type : "POST",
        				async:true,
        				data : {
        					"sysUser.userId" : userId,
        					"sysUser.userPassword" : userPassword
        				},
        				success : function(msg){
        					if(msg==true){
        						loading({el:'tip',content:'登录成功,即将跳转！'});
        						timer = setTimeout(function(){
            						window.location.href = _path+"Login_userLogin.action";
            					},1500);
        					}else if(msg==false){
        						loading({el:'tip',content:'登录失败,账号或密码错误！'});
        					}else if(msg=="error"){
        						loading({el:'tip',content:'登录失败,账号不存在！'});
        					}
        					timer = setTimeout(function(){
        						$('#tip').slideUp(1500);
        					},1000);
        				},
        				error : function(er){
        					console.log("error");
        					console.log(er);
        				}
        			});
        		};

        		//登录按钮登录
        		$('a[href="javascript:;"]').click(function(){
        			doLogin();
        		})

        		//回车键监听登录
        		document.onkeydown = function(ev){
		            ev = ev || window.event;
		            if(ev.keyCode == 13 || ev.key == "Enter")doLogin();
		        };
        	});
        </script>
        <ul>
            <li>欢</li>
            <li>迎</li>
            <li>使</li>
            <li>用</li>
            <li>毕</li>
            <li>业</li>
            <li>选</li>
            <li>题</li>
            <li>系</li>
            <li>统</li>
        </ul>
    </div>
    <script type="text/javascript">
        var container = document.getElementById('container');
        var w = window.innerWidth;
        var h = window.innerHeight;
        setElSize(container);
        window.onresize = function(){
            setElSize(container);
        };
        function setElSize(obj){
        	w = window.innerWidth;
            h = window.innerHeight;
            obj.style.left = (window.innerWidth - obj.clientWidth)/2 + 'px';
        };
        function loading(mJson){
            /*
                mJson = {
                    el : tagNameId，
                    content ： '提示内容',
                }
            */
            var tipDom = document.getElementById(mJson.el);
            if(tipDom)tipDom.parentNode.removeChild(tipDom);
            var inTip = document.createElement('div');
            inTip.id = mJson.el;
            inTip.className = 'me_loading magictime bottomToCenter';
            inTip.innerHTML = mJson.content;
            inTip.style.left = (w-200)/2 + 'px';
            inTip.style.top = (h+17)/2 + 'px';
            document.body.appendChild(inTip);
        };
    </script>
    <div class="chang_lang">
    	<span>更换语言</span>
    	<a href="Login_openLogin.action?flagString=en">英文</a>
        <a href="Login_openLogin.action">中文</a>
    </div>
    <script>
    	;!function(){
    		var flag = false;
    		document.querySelector('.chang_lang').onclick = function(){
    			var aDom = this.querySelectorAll('a');
    			if(!flag){
    				for(var i=0;i<aDom.length;i++){
    					aDom[i].style.display = 'block';
    				};
    				flag = true;
       			}else{
       				for(var i=0;i<aDom.length;i++){
    					aDom[i].style.display = 'none';
    				};
    				flag = false;
       			};
    		};
    	}();
    </script>
</body>
</html>
