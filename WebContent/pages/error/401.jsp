<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>401 您没有该页面访问权限</title>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/jquery_common.jsp"%>
<script type="text/javascript" src="${ctx}/jquery/js/jquery-slider.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/slider.css"></link>
</head>
<body class="main1">
	<div class="content-main clear">
		<div class="page-header">
			<h1>
				系统异常 <small> 对不起，您没有该页面访问权限,请联系管理员! </small>
			</h1>
		</div>
		<div class="pageFormContent">
			<h2>对不起，您没有该页面访问权限,请联系管理员！</h2>
		</div>
	</div>
</body>
</html>
