<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
	String _path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>session_timerout</title>
<style type="text/css">
* {
	padding: 0;
	margin: 0;
	list-style: none;
	font-weight: normal;
	border: 0;
	font-size: 12px;
	font-family: "微软雅黑";
	color: #3e3e3e;
}

.errorWrap {
	width: 100%;
	height: 100%
}

.errowCon {
	width: 580px;
	height: 455px;
	margin: 30px auto 0;
	background: url(../images/auto/error1_bg.jpg) no-repeat;
	position: relative;
}

.errorTxt {
	width: 360px;
	height: 200px;
	position: absolute;
	top: 38px;
	left: 186px;
}

.errorTxt .errorBt {
	width: 300px;
	height: 50px;
	line-height: 50px;
	font-size: 16px;
	font-weight: bold;
	color: #D00202;
	margin: auto;
	text-align: center;
}

.errorTxt .red {
	color: #F60;
	font-weight: bold;
	font-size: 15px;
}

.errorTxt .txt {
	width: 300px;
	margin: 0 auto;
	line-height: 22px;
}

.errorTxt a:link,.errorTxt a:visited {
	color: #F60;
	text-decoration: underline;
}

.errorTxt a:hover {
	color: #F60;
}
</style>
<script>
function exitSystem(){
	
	window.top.location='<%=_path%>/shiro/logout.action';
	
}
</script>
</head>
<body>
	<div class="errorWrap">
		<div class="errowCon">
			<div class="errorTxt">
				<h3 class="errorBt">您已经很长时间没有操作喽...</h3>
				<p class="txt">
					请您退出，重新登录！<br />
					返回，<a href="#" onclick="exitSystem()">请点击这里>></a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>
