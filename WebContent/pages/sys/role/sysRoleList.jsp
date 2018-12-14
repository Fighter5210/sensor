<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>系统管理-角色管理</title>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/jquery_common.jsp"%>
<%@ include file="/common/zTree_load.jsp"%>
<script type="text/javascript"
	src="${ctx}/pages/sys/role/js/sysRoleList.js"></script>
<script type="text/javascript"
	src="${ctx}/pages/sys/role/js/authorization.js"></script>

<script type="text/javascript" src="${ctx}/jquery/js/jquery-slider.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/slider.css"></link>
<link rel="stylesheet"
	href="${ctx}/pages/system/app/css/zTreeStyle/zTreeStyle.css"
	type="text/css"></link>
<script type="text/javascript">
	$(function() {
		initSysRoleList();
		// 自适应宽度
		$("#gridTable").setGridWidth($("#gridMain").width());
		$(window).resize(function() {
			$("#gridTable").setGridWidth($("#gridMain").width());
		});
	});
</script>
<link rel="stylesheet" href="${ctx}/common/css/search_new.css"
	type="text/css"></link>

</head>

<body class="main1">
	<div class="content-main clear">
		<div id="nav">
			<i class="home"></i> <span>当前位置:</span><span class="active">系统管理-&gt;角色管理</span>
		</div>
		<div class="pageFormContent3"
			style="padding-top: 0px; padding-bottom: 7px;overflow: hidden;">
			<!--新添加的搜索标题-->
			<div class="mainSearchBt">
				<i></i> <span class="bt">查询区域</span>
				<div class="searchBtn_Wrap">
					<input type="button" class="btn_search" onclick="search();"
						title="查询角色" style="vertical-align: middle" />
					<shiro:hasPermission name="role:save">
						<input type="button" value="添加" onclick="createSysRole();"
							class="btn_add" title='添加' style="vertical-align: middle" />
					</shiro:hasPermission>
					<shiro:hasPermission name="role:delete">
						<input type="button" value="删除" onclick="deleteSysRole()"
							class="btn_del" title='删除' style="vertical-align: middle" />
						<input type="hidden" id="deleteFlag" name="deleteFlag" value="1" />
					</shiro:hasPermission>
					<shiro:hasPermission name="role:authorization">
						<input type="button" value="授权" onclick="roleAuthorization()"
							class="btn_link" title='角色授权' style="vertical-align: middle" />
					</shiro:hasPermission>
					<shiro:hasPermission name="role:update">
						<input type="hidden" id="updateFlag" name="updateFlag" value="1" />
					</shiro:hasPermission>
					<shiro:hasPermission name="role:userList">
						<input type="hidden" id="userListFlag" name="userListFlag"
							value="1" />
					</shiro:hasPermission>
				</div>
			</div>
			<table height="12%" align="left" border="0" class="searchWrap">
				<tr>
					<td align="left" class="name" style="width: 80px;">&nbsp;&nbsp;角色名称：</td>
					<td align="left" class="content"><input type="text"
						id="roleName" name="roleName" class="textInput" /></td>
				</tr>
			</table>

		</div>
		<div class="panel clear" id="gridMain">
			<div>
				<table id="gridTable"></table>
				<div id="gridPager"></div>
			</div>
		</div>
	</div>

	<div id="sysRoleUpdateDiv" style="display: none;">
		<form action="" method="post" id="updateForm">
			<div id="sysRole" class="pageFormContent">
				<input type="hidden" id="roleMethod" name="roleMethod" /> <input
					type="hidden" id="roleIsActive" name="roleIsActive" />
				<p>
					<i><font color="red">*</font>角色名称：</i> <input type="text"
						id="roleNameInput" name="roleNameInput" class="textInput"></input>
				</p>
				<p>
					<i>备注：</i>
					<textarea id="remarkInput" name="remarkInput" rows="3" align="left"
						class="textInput"></textarea>
				</p>
				<p style="text-align: center;">
					<input type="button" title="确定" class="btn_ok"
						onclick="saveOrUpdateBtn()" style="margin-right: 5px;" /><input
						type="button" title="取消" class="btn_cancel"
						onclick="closeView('sysRoleUpdateDiv')" style="margin-right: 5px;" />
				</p>
				<input type="hidden" id="roleId" name="roleId" />
			</div>

		</form>
	</div>

	<div id="SysRoleShowDiv" class="div_center" style="display: none;">
		<form action="" method="post" id="showForm">
			<div id="sysRoleShow">
				<table class="pagelist">
					<tr>
						<th>角色名称：</th>
						<td><label id="roleNameShow"></label></td>
					</tr>
					<tr>
						<th>备注：</th>
						<td><label id="remarkShow"></label></td>
					</tr>
				</table>
			</div>
			<p class="btnbar">
				<input type="button" class="btn_cancel"
					onclick="closeView('SysRoleShowDiv')" style="margin-right: 5px;" />
			</p>
		</form>
	</div>
	<!-- 查询指定角色下的用户信息 -->
	<div id="userAuthorizationDiv" class="div_center"
		style="display: none;">
		<div class="gridMain">
			<table id="gridTableUser"></table>
			<table id="gridPagerUser"></table>
		</div>
		<p class="btnbar">
			<input type="button" class="btn" value="关闭窗口"
				onclick="closeView('userAuthorizationDiv')"
				style="margin-right: 5px;" />
		</p>
	</div>
	<jsp:include page="authorization.jsp"></jsp:include>
</body>

</html>