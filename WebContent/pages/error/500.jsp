<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String _path = request.getContextPath();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>500</title>
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
	background: url(<%=_path%>/images/auto/error1_bg.jpg) no-repeat;
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
</head>
<body>
	<div class="errorWrap">
		<div class="errowCon">
			<div class="errorTxt">
				<h3 class="errorBt">您现在浏览的系统暂时出现错误！</h3>
				<p class="txt">
					您正在浏览的网页可能已被删除，重命名或暂时不可用。<br />
					返回主页，<a href="<%=_path%>/pagesV2.0/main/main.jsp"">请点击这里>></a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>
