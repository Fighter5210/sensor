<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String url = request.getRequestURL().toString();
	url = url.substring(0, url.indexOf('/', url.indexOf("//") + 2));
	String context = request.getContextPath();
	System.out.println("url=" + url + ",context:" + context);
	url += context;
	application.setAttribute("ctx", url);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>传感器产品管理平台</title>
<link type="text/css" rel="stylesheet"
	href="<%=context%>/pagesV2.0/main/css/index.css" />

<!--  -->

<script type="text/javascript" src="<%=context%>/scripts/jquery-1.8.0.min.js"></script>

<script type="text/javascript" src="<%=context%>/jquery/js/jquery.jbox.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/jquery_global.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/jquery.open-layer.js"></script>
<script type="text/javascript" src="<%=context%>/common/javascript/main.js"></script>
<!--  -->

<!--  -->
<link rel="stylesheet" type="text/css" href="<%=context%>/jquery/css/jbox.css"></link>
<!--  -->

<script type="text/javascript">
	
	
    $(document).ready(function(){ 
    	//输入提示;
		$("#username").blur(function(){
			if($("#username").val() == ""){
				$(this).attr("value","请输入用户名");
			}
		});
    	
		$("#username").focus(function(){
			if($("#username").val() == "请输入用户名"){
				$(this).attr("value","");
			}
		});
		
    	
		$("#password").focus(function(){
			if($("#password").val() == "111111"){
				$(this).attr("value","");
			}
		});
		
		$("#idCode").blur(function(){
			if($("#idCode").val() == ""){
				$(this).attr("value","验证码");
			}
		});
    	
		$("#idCode").focus(function(){
			if($("#idCode").val() == "验证码"){
				$(this).attr("value","");
			}
		});
    	
    }); 

	function refresh() {
		document.getElementById("Image1").src = "./pagesV2.0/common/rand.jsp?"
				+ Math.random();
	}

	//平台用户登录;
	function login() {
		var un = $("#username").val();
		var pwd = $("#password").val();
		var ic = $("#idCode").val();
		
		if(un == "请输入用户名"){
			un = "";
		}
		
		if(pwd == "111111"){
			pwd = "";
		}
		
		if(ic == "验证码"){
			ic = "";
		}
		showTip("load");
		$.ajax({
			type : "POST",
			url : "<%=context%>/shiro/login.action",
			data : {
				"username" : un,
				"password" : pwd,
				"idCode" : ic
			},
			async : true,
			cache : false,
			success : function(data) {
				if(data.success){
					//旧版跳转;
					//location.href = "<%=context%>/flex/Main.html";
					//新版跳转;
					location.href = "<%=context%>/shiro/index.do";
				}else{
					showTip(data.msg);
				}
			},
			error : function(e) {
				showTip(exception_info);
			}
		});
		closeTip();
	}
	
	//重置;
	function reset(){
		$("#username").attr("value","");
		$("#password").attr("value","");
		$("#idCode").attr("value","验证码");
	}
	
	
	//回车事件;
	document.onkeydown = function (e) {
	    if (!e) e = window.event; //火狐中是 window.event
	    if ((e.keyCode || e.which) == 13) {
	    	login();
	    }
	}
</script>


<style>
.inFooter{
	width:733px; 
	height:140px;
	margin-left:20px;
	background:url(./pagesV2.0/main/images/inFooter_bg.png) no-repeat;
	}
</style>

</head>

<body style="overflow: -Scroll; overflow-y: hidden">
	<div class="Wrap">
		<div class="WrapCon">
			<h2 class="logo">传感器产品数据管理平台</h2>
			<div class="Loginbar">
				<form action="<%=context%>/shiro/login.action" method="post" name="form1">
					<div class="Loginbar_left left">
						<input type="text" value="" name="username" id="username" />
						<input type="password" class="password" value="" name="password"
							id="password" />
						<div class="Validate_code">
							<input type="text" class="left" value="验证码" name="idCode" id="idCode"/><span class="left"><img
								id="Image1" title="验证码"
								src="<%=context%>/pagesV2.0/common/rand.jsp" align="absbottom"
								style="border-style: none; border-width: 0px;" /></span><span
								class="float">看不清</span> <a href="javascript:refresh();">换一张</a>
						</div>
						<div class="Btn_wrap">
							<a href="javascript:login();" class="Btn_true">确
								定</a><a href="javascript:reset();" class="Btn_false">重 置</a>
						</div>
					</div>
				</form>
				<div class="Loginbar_right right">
					<img src="<%=context%>/pagesV2.0/main/images/photo1.jpg"
						width="387" height="166" />
				</div>
			</div>
			<!-- div class="inFooter"></div> -->
			<!-- 
			<ul class="IconWrap">
				<li class="icon1">
					<h3>标准化</h3>
					<p>标准化的互联网IT自动化运维方式。</p>
				</li>
				<li class="icon2">
					<h3>高效率</h3>
					<p>颠覆传统数据中心运维模式。</p>
				</li>
				<li class="icon3">
					<h3>低成本</h3>
					<p>以极低成本提高运维服务级别,降低时间。</p>
				</li>
			</ul>
			 -->
		</div>
	</div>
</body>
</html>
