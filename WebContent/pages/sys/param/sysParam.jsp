<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/jquery_common.jsp"%>
<html>
<head>
<title>系统管理-系统参数管理</title>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/jquery_common.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/zTree_load.jsp"%>
<script type="text/javascript"
	src="${ctx}/pages/sys/param/js/sysParam.js"></script>
<link rel="stylesheet" href="${ctx}/common/css/search_new.css"
	type="text/css"></link>

</head>

<body class="main1">
	<div class="content-main clear">
		<div id="nav">
			<i class="home"></i> <span>当前位置:</span><span class="active">系统管理-&gt;系统参数管理</span>
		</div>
		<div class="pageFormContent3"
			style="padding-top: 0px; padding-bottom: 7px;overflow: hidden;">
		<form action="" method="post" id="add_update_User_Form">
		   <input type="hidden" id="id" name="id" value="">
			<div id="updateTab" class="pageFormContent">
				<p>
					<i><font color="red">*</font>指令间隔:</i> <input class="textInput"
						type="text" id="commIntervalTime" name="commIntervalTime" maxlength="4"/>&nbsp;&nbsp;单位:ms （建议值500以上）
				</p>
				<p>
					<i><font color="red">*</font>温度允许偏差:</i> <input class="textInput"
						type="text" id="temAllowOffset" name="temAllowOffset" maxlength="5"/>
				</p>
				<p>
					<i><font color="red">*</font>湿度允许偏差:</i> <input class="textInput"
						type="text" id="humAllowOffset" name="humAllowOffset" maxlength="5"/>
				</p>
			</div>
			<div class="winbtnbar clear" style="text-align: center;">
				<input type="button" class="btn_ok" id="saveUser" name="saveUser"
					title="保存" style="margin-right: 70px; margin-left: 5px;"
					onclick="updateSysParamProxy();">
		    </div>
		</form>
		</div>
	</div>

</body>
</html>
