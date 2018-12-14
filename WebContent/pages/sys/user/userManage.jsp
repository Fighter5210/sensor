<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/jquery_common.jsp"%>
<html>
<head>
<title>系统管理-用户管理</title>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/jquery_common.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/zTree_load.jsp"%>
<script type="text/javascript"
	src="${ctx}/pages/sys/user/js/userManage.js"></script>
<link rel="stylesheet"
	href="${ctx}/pages/system/app/css/zTreeStyle/zTreeStyle.css"
	type="text/css"></link>
<style type="text/css">
.pageFormContent p {
	width: 355px;
}
</style>
<link rel="stylesheet" href="${ctx}/common/css/search_new.css"
	type="text/css"></link>

</head>

<body class="main1">
	<div class="content-main clear">
		<div id="nav">
			<i class="home"></i> <span>当前位置:</span><span class="active">系统管理-&gt;用户管理</span>
		</div>
		<div class="pageFormContent3"
			style="padding-top: 0px; padding-bottom: 7px;overflow: hidden;">
			<!--新添加的搜索标题-->
			<form action="">
				<div class="mainSearchBt">
					<i></i> <span class="bt">查询区域</span>
					<div class="searchBtn_Wrap">
						<shiro:hasPermission name="user:list">
							<input type="button" class="btn_search" onclick="searchUser();"
								title="查询用户" style="vertical-align: middle" />
						</shiro:hasPermission>
						<shiro:hasPermission name="user:save">
							<input type="button" class="btn_add" onclick="addUser('insert');"
								title="新增用户" style="vertical-align: middle" />
						</shiro:hasPermission>
						<shiro:hasPermission name="user:save">
							<input type="hidden" id="updateFlag" name="updateFlag" value="1" />
						</shiro:hasPermission>
						<shiro:hasPermission name="user:delete">
							<input type="hidden" id="deleteFlag" name="deleteFlag" value="1" />
						</shiro:hasPermission>
						<shiro:hasPermission name="role:authorization">
							<input type="hidden" id="authorizationFlag" name="authorizationFlag" value="1" />
						</shiro:hasPermission>
					</div>
				</div>
				<!--<table height="12%" width="45%" align="left" border="0"
					class="searchWrap">
					<tr>
						<td align="right" class="name">登录名：</td>
						<td align="left" class="content"><input type="text"
							id="loginName_labl" name="loginName_labl" class="textInput" /></td>
						  td align="left" class="name">用户类型：</td>
						<td align="left" class="content"><icms-ui:dic
								id="userType_labl" name="userType_labl" kind="USER_TYPE"
								showType="select" attr="class='selInput'" /></td>
					</tr>
				</table>-->
			</form>
		</div>
		<div class="panel clear" id="gridMain">
			<table id="gridTable"></table>
			<div id="gridPager"></div>
		</div>
	</div>

	<div id="add_update_User_Div" style="display: none">
		<form action="" method="post" id="add_update_User_Form">
			<div id="updateTab" class="pageFormContent">
				<p>
					<i><font color="red">*</font>用户姓:</i> <input class="textInput"
						type="text" id="firstName" name="firstName" />
				</p>
				<p>
					<i><font color="red">*</font>用户名:</i> <input class="textInput"
						type="text" id="lastName" name="lastName" />
				</p>
				<p>
					<i><font color="red">*</font>登录名:</i> <input class="textInput"
						type="text" id="loginName" name="loginName" />
				</p>
				<p>
					<i><font color="red">*</font>登录密码:</i> <input class="textInput"
						type="password" id="loginPassword" name="loginPassword" />
				</p>
				<p>
					<i>邮箱:</i> <input class="textInput" type="text" id="email"
						name="email" />
				</p>
				<p>
					<i>IP地址:</i> <input class="textInput" type="text" id="ipAddress"
						name="ipAddress" />
				</p>
				<!-- p>
					<i><font color="red">*</font>用户类型:</i>
					<icms-ui:dic id="userType" name="userType" kind="USER_TYPE"
						showType="select" attr="class='selInput'" />
				</p> -->
				<p>
					<i><font color="red">*</font>所属部门:</i> <input class="textInput"
						type="text" id="orgName" name="orgName" onclick="showOrgTree()"
						style="cursor: pointer" />
				</p>
				<p>
					<i>电话:</i> <input class="textInput" type="text" id="phone"
						name="phone" />
				</p>
			</div>
		</form>
		<div class="winbtnbar clear" style="text-align: center;">
			<input type="button" class="btn_ok" id="saveUser" name="saveUser"
				title="保存" style="margin-right: 5px; margin-left: 5px;"
				onclick="saveUserBtn()"> <input type="button"
				class="btn_cancel" id="cencelUser" name="cencelUser" title="取消"
				style="margin-right: 5px;"
				onclick="closeView('add_update_User_Div')">
		</div>
	</div>

	<!-- 角色授予 -->
	<div id="roleAuthorizationDiv" class="div_center"
		style="display: none;">
		<div class="gridMain">
			<table id="gridTableRole"></table>
			<table id="gridPagerRole"></table>
		</div>
		<p class="btnbar">
			<input type="button" value="授予角色" class="btn"
				onclick="chooseAuthRole()" style="margin-right: 5px;" /> <input
				type="button" value="取消授予" class="btn" onclick="cancleAuthRole()"
				style="margin-right: 5px;" /> <input type="button" class="btn"
				value="关闭窗口" onclick="closeView('roleAuthorizationDiv')"
				style="margin-right: 5px;" />
		</p>
	</div>

	<!-- 所属部门 -->
	<div id="orgAuthorizationDiv" class="div_center" style="display: none;">
		<form action="" method="post" id="authorizationForm">
			<input type="hidden" id="roleAuthorId" name="roleAuthorId" /> <input
				type="hidden" id="roleMenus" name="roleMenus" />
			<table class="pagelist">
				<tr>
					<td>
						<div id="treeDiv"
							style="overflow: auto; min-height: 306px; height: 100%; width: 100%; background-color: white; float: left; clear: right;">
							<ul id="orgTree" class="ztree"
								style="width: 288px; overflow: auto;"></ul>
						</div>
					</td>
				</tr>
			</table>
			<p class="winbtnbar clear">
				<input type="button" class="btn_ok" title="选取"
					onclick="chooseAuthOrg()" style="margin-right: 5px;" /> <input
					type="button" class="btn_cancel" title="取消"
					onclick="closeView('orgAuthorizationDiv')"
					style="margin-right: 5px;" />
			</p>
		</form>
	</div>
	<input type="hidden" id="userId" name="userId" value="">
	<input type="hidden" id="loginNameOld" name="loginNameOld" value="">
	<input type="hidden" id="loginPassOld" name="loginPassOld" value="">
</body>
</html>
