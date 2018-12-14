<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!-- 展示显示详情 -->
<div id="show" style="display: none">
	<table border="0" class="report" width="97%" id=sysOrganizationPo>
		<tr>
			<th>部门名称：</th>
			<td><label id="sysOrganizationPo_orgName"></label></td>
			<th>所属部门：</th>
			<td><label id="sysOrganizationPo_parent_orgName"></label></td>
		</tr>
		<tr>
			<th>部门描述：</th>
			<td><label id="sysOrganizationPo_remark"></label></td>
			<th>创建时间：</th>
			<td><label id="sysOrganizationPo_createTime"></label></td>
		</tr>
	</table>
	<p class="btnbar">
		<input type="button" class="btn" id="btn_add_scp" onclick="show('2')"
			value="新建子部门" style="margin-right: 5px; margin-left: 5px;" /><input
			type="button" class="btn" id="btn_mod_scp" onclick="show('1')"
			value="修改" style="margin-right: 5px; margin-left: 5px;" /><input
			type="button" class="btn" id="btn_mod_scp" onclick="deletes()"
			value="删除" style="margin-right: 5px; margin-left: 5px;" />
	</p>
</div>

<!-- 添加和修改 -->
<style>
<!--
.pageFormContent p {
	width: 350px;
}
-->
</style>
<div id="edit" style="display: none; background-color: white;"
	class="pageFormContent">
	<form action="" id="forms1">
		<input id="sysOrganizationPo.orgId" type="hidden"> <input
			id="sysOrganizationPo.parent.orgId" type="hidden">

		<p>
			<i><font color="red">*</font>部门名称：</i><input
				id="sysOrganizationPo.orgName" name="sysOrganizationPo.orgName"
				class="textInput">
		</p>
		<p>
			<i>部门描述：</i>
			<textarea id="sysOrganizationPo.remark"
				name="sysOrganizationPo.remark" class="textInput"></textarea>
		</p>
		<p>
			<i>所属部门：</i> <label id="sysOrganizationPo_parent_orgName2"></label>
		</p>
		<p class="winbtnbar" style="text-align: center;">
			<label id="sp_error_tip"></label> <input type="button"
				id="btn_mod_sp" class="btn_ok" onclick="save()" title="保存"
				style="margin-right: 5px; margin-left: 5px;" /><input type="button"
				id="btnAddSp" class="btn_cancel" title="取消"
				onclick="closeView('edit')" style="margin-right: 5px;" />
		</p>
	</form>
</div>