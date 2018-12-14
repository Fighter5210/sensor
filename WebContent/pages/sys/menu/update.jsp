<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<script type="text/javascript">
<!--
	//检查当前选中菜单级别
	/* 	jQuery.validator.addMethod("checkMenuLevel", function(value, element, param) {
	 var zTree = $.fn.zTree.getZTreeObj("tree");
	 //1 检查当前选中菜单级别 
	 var nodes = zTree.getSelectedNodes();
	 var node = nodes[0];
	 var level = 0;
	 while (node == null) {
	 node = node.getParentNode();
	 if (node != null)
	 level++;
	 }
	 alert(level);
	 if (level > 2) {
	 return false;
	 }
	 return false;
	 }, "菜单级别不能超过2级！"); */
	function checkMenuLevels() {
		var zTree = $.fn.zTree.getZTreeObj("tree");
		//1 检查当前选中菜单级别 
		var nodes = zTree.getSelectedNodes();
		var node = nodes[0];
		var level = 0;
		while (node == null) {
			node = node.getParentNode();
			if (node != null)
				level++;
		}
		alert("菜单级别不能超过2级！");
		if (level > 2) {
			return false;
		}
		return false;
	}
	$(document).ready(function() {
		initParamsTable();
	});
	function initParamsTable() {
		var queryData = {
			"sysMenuPo.id" : $("#sysMenuPo\\.id").val()
		};
		$("#roles").jqGrid({
			url : ctx + "/sys/menu/roles.do",
			datatype : "json",
			postData : queryData,
			mtype : "post",
			autowidth : true,
			height : 100,
			colNames : [ '角色名称', '备注' ],
			colModel : [ {
				name : "roleName",
				index : "roleName",
				sortable : false,
				align : 'center'
			}, {
				name : "remark",
				index : "remark",
				sortable : false,
				align : 'center'
			} ],
			prmNames : {
				search : "search"
			},
			jsonReader : {
				root : "result.data",
				repeatitems : false
			},
			hidegrid : false
		});

		$(window).resize(function() {
			$("#roles").setGridWidth($("#rolesDiv").width());
		});

		$("#roles").closest(".ui-jqgrid-bdiv").css({
		});
	}
//-->
</script>
<div id="show" style="display: none">
	<table border="0" cellpadding="0" cellspacing="0" class="report"
		width="97%" id="sysMenuPo">
		<tr>
			<th>菜单名称：</th>
			<td><label id="sysMenuPo_menuName"></label></td>
			<th>菜单编码：</th>
			<td><label id="sysMenuPo_menuCode"></label></td>
			<th>所属菜单：</th>
			<td><label id="sysMenuPo_parent_name"></label></td>
		</tr>
		<tr>
			<th>菜单类型：</th>
			<td><label id="sysMenuPo_resourceType"></label></td>
			<th>菜单顺序：</th>
			<td><label id="sysMenuPo_orderId"></label></td>
			<th>菜单地址：</th>
			<td colspan="5"><label id="sysMenuPo_menuUrl"></label></td>
		</tr>
		<tr>
			<th>菜单描述：</th>
			<td><label id="sysMenuPo_menuDesc"></label></td>
		</tr>
	</table>

	<div class="panel clear" id="rolesDiv">
		<table id="roles"></table>
	</div>

	<p class="btnbar">
		<shiro:hasPermission name="menu:save">
			<input type="button" class="btn" id="btn_add_scp" onclick="show('2')"
				value="新建子菜单" style="margin-right: 5px; margin-left: 5px;" />
			<input type="button" class="btn" id="btn_mod_scp" onclick="show('1')"
				value="修改" style="margin-right: 5px; margin-left: 5px;" />
		</shiro:hasPermission>
		<shiro:hasPermission name="menu:delete">
			<input type="button" class="btn" id="btn_mod_scp" onclick="deletes()"
				value="删除" style="margin-right: 5px; margin-left: 5px;" />
		</shiro:hasPermission>
	</p>
</div>
<style>
<!--
.pageFormContent p{
	width: 350px;
}
-->
</style>
<div id="edit" style="display: none; background-color: white;"
	class="pageFormContent">
	<form action="" id="forms1">
		<p>
			<i><font color="red">*</font>菜单名称：</i><input id="sysMenuPo.menuName"
				name="sysMenuPo.menuName" class="textInput">
		</p>
		<p>
			<i>菜单编码：</i><input id="sysMenuPo.menuCode" class="textInput">
		</p>
		<p>
			<i>菜单地址：</i><input id="sysMenuPo.menuUrl" class="textInput">
		</p>
		<p>
			<i><font color="red">*</font>菜单类型：</i><select
				id="sysMenuPo.resourceType" name="sysMenuPo.resourceType"
				class="selInput">
				<option value="">选择</option>
				<option value="menu">menu</option>
				<option value="function">function</option>
			</select>
		</p>
		<p>
			<i>菜单描述：</i>
			<textarea id="sysMenuPo.menuDesc" name="sysMenuPo.menuDesc"
				class="textInput"></textarea>
		</p>
		<p>
			<i>菜单顺序：</i><input id="sysMenuPo.orderId" class="textInput">
		</p>
		<p>
			<i>所属菜单：</i><label id="sysMenuPo_parent_name2"></label>
		</p>
		<input id="sysMenuPo.id" type="hidden"> <input
			id="sysMenuPo.parent.id" type="hidden">
		<p class="winbtnbar" style="text-align: center;">
			<label id="sp_error_tip"></label>
			
				<input type="button" id="btn_mod_sp" class="btn_ok" onclick="save()"
					title="保存" style="margin-right: 5px; margin-left: 5px;" />
			<input type="button" id="btnAddSp" class="btn_cancel" title="取消"
				onclick="closeView('edit')" style="margin-right: 5px;" />
		</p>
	</form>
</div>