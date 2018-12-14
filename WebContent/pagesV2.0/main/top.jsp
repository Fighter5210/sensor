<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String _path = request.getContextPath();
%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>头部页面</title>
    <link href="css/public.css" rel="stylesheet" type="text/css">
    <link href="css/main.css" rel="stylesheet" type="text/css">
    <%@ include file="/common/jquery_common.jsp"%>
<script type="text/javascript">
	$(function() {
		setInterval(function() {
			$("#server_time_span").text("系统时间:" + new Date().toLocaleString());
		}, 1000);
	});
	function goto2(obj,url){
		$(".menu2 > li").each(function(){
			$(this).attr("class","");
		});
		$(obj).attr("class","menu2Active");
		parent.frames["main"].location.href = url;
	}
	
	//退出系统平台;
	function exitSystem(){
		 if(confirm("您确定要退出系统平台吗？")){
			 window.top.location='<%=_path%>/shiro/logout.action';
		 }
	}
</script>
</head>

<body>
<div class="header">
	<div class="headerTop">
        <div class="logo left"><font style="color:white;margin-left:10px;margin-top:20px;padding-top:10px;font-family:黑体;font-size:30px;height:36px;"></font></div>
        <ul class="loginMsg right">
            <li><a href="#" onclick="goto2(this,'main.jsp')"><img src="images/header_icon1.jpg" width="64" height="63" title="我的主页"></a></li>
            <li><a href="#" onclick="parent.location.reload();"><img src="images/header_icon2.jpg" width="64" height="63" title="刷新页面"></a></li>
            <li><a href="#" onclick="exitSystem()"><img src="images/header_icon3.jpg" width="64" height="63" title="退出系统"></a></li>
        </ul>
    </div>
    <div class="headerBot">
    	<P class="left">&nbsp;尊敬的【<strong><shiro:user><shiro:principal /></shiro:user></strong>】欢迎您，登陆传感器产品管理平台！</P>
        <P class="right"><strong>意见反馈</strong>&nbsp;&nbsp;<span class="btn-info" id="server_time_span"/></P>
    </div>
</div>
</body>
</html>
