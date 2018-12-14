<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/jquery_common.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/zTree_load.jsp"%>
<link rel="stylesheet"
	href="${ctx}/pages/system/app/css/zTreeStyle/zTreeStyle.css"
	type="text/css"></link>
<title>系统管理-菜单管理</title>
<script type="text/javascript">
	var setting = {
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pid",
				rootPId : ""
			}
		},
		callback : {
			onMouseDown : onMouseDown
		}
	};

	$(document).ready(function() {
		
		var t = $("#tree");
		// 加载左侧树
		$.ajax({
			type : "post",
			url : ctx + "/sys/menu/loadtree.do",
			data : {},
			beforeSend : function(XMLHttpRequest) {
			},
			success : function(data, textStatus) {
				var zNodes = data.list;
				var root = {
					'id' : '0',
					'name' : '资源',
					'pid' : '-1',
					'open' : true
				};
				zNodes[zNodes.length] = root;
				t = $.fn.zTree.init(t, setting, zNodes);
				return true;
			},
			complete : function(XMLHttpRequest, textStatus) {
			},
			error : function() {
				alert("添加失败！");
				flage = false;
			}
		});
	});
	function onMouseDown(event, treeId, treeNode) {
		if (treeNode.id != '0') {
			load(treeNode.id);
			var queryData = {
				"sysMenuPo.id" : treeNode.id
			};
			$("#roles").jqGrid("setGridParam", {
				postData : queryData
			}).trigger("reloadGrid");
		} else {
			$("#showRoot").show();
			$("#show").hide();
			$("#edit").hide();
		}
	}
	function load(nodeId) {
		$.ajax({
			type : "post",
			url : ctx + "/sys/menu/load.do",
			data : {
				"sysMenuPo.id" : nodeId
			},
			beforeSend : function(XMLHttpRequest) {
			},
			success : function(data, textStatus) {
				$("#showRoot").hide();
				$("#show").show();
				$("#roles").setGridWidth($("#rolesDiv").width());
				var zTree = $.fn.zTree.getZTreeObj("tree");
				$("#sysMenuPo_menuName").text(data.sysMenuPo.menuName);
				$("#sysMenuPo_menuCode").text(data.sysMenuPo.menuCode);
				$("#sysMenuPo_menuUrl").text(data.sysMenuPo.menuUrl);
				if (data.sysMenuPo.resourceType == "menu") {
					$("#sysMenuPo_resourceType").html("<span class='tip_blue'>菜单</span>");
				} else {
					$("#sysMenuPo_resourceType").html("<span class='tip_green'>功能</span>");
				}

				//$("#sysMenuPo_resourceType").text(data.sysMenuPo.resourceType);
				$("#sysMenuPo_menuDesc").text(data.sysMenuPo.menuDesc);
				$("#sysMenuPo_orderId").text(data.sysMenuPo.orderId);

				var pnode = zTree.getNodeByParam("id", data.sysMenuPo.parent.id, null);
				if (pnode != null) {
					$("#sysMenuPo_parent_name").text(pnode.name);
					$("#sysMenuPo_parent_name2").text(pnode.name);
				} else {
					$("#sysMenuPo_parent_name").text("");
					$("#sysMenuPo_parent_name2").text("");
				}
				$("#sysMenuPo\\.id").val(data.sysMenuPo.id);

				$("#sysMenuPo\\.menuName").val(data.sysMenuPo.menuName);
				$("#sysMenuPo\\.menuCode").val(data.sysMenuPo.menuCode);
				$("#sysMenuPo\\.menuUrl").val(data.sysMenuPo.menuUrl);
				$("#sysMenuPo\\.resourceType").val(data.sysMenuPo.resourceType);
				$("#sysMenuPo\\.menuDesc").val(data.sysMenuPo.menuDesc);
				$("#sysMenuPo\\.orderId").val(data.sysMenuPo.orderId);
				$("#sysMenuPo\\.parent\\.id").val(data.sysMenuPo.parent.id);
			},
			complete : function(XMLHttpRequest, textStatus) {
			},
			error : function() {
				alert("添加失败！");
				flage = false;
			}
		});
	}
	
	//表单验证;
	function formValidate(){
		
		var menuName = $("#sysMenuPo\\.menuName").val();
		
		if(menuName == null || menuName == ""){
			showTip("菜单名称不能为空!");
			return false;
		}
		
		if ($("#sysMenuPo\\.resourceType").val() == "") {
			showTip("请选择菜单类型!");
			return false;
		}
		
		var menuDesc = $("#sysMenuPo\\.menuDesc").val();
		
		if(menuDesc != null && menuDesc != "" && menuDesc.length>20){
			showTip("菜单描述太长，超过20个字符!");
			return false;
		}
		
		
		
		return true;
	}
	
	function save() {
		
		if (formValidate()){
			if ($("#sysMenuPo\\.parent\\.id").val() == "") {
				$("#sysMenuPo\\.parent\\.id").val("0");
			}
			var params = {
				'sysMenuPo.id' : $("#sysMenuPo\\.id").val(),
				'sysMenuPo.menuName' : $("#sysMenuPo\\.menuName").val(),
				'sysMenuPo.menuCode' : $("#sysMenuPo\\.menuCode").val(),
				'sysMenuPo.menuUrl' : $("#sysMenuPo\\.menuUrl").val(),
				'sysMenuPo.resourceType' : $("#sysMenuPo\\.resourceType").val(),
				'sysMenuPo.menuDesc' : $("#sysMenuPo\\.menuDesc").val(),
				'sysMenuPo.orderId' : $("#sysMenuPo\\.orderId").val(),
				'sysMenuPo.parent.id' : $("#sysMenuPo\\.parent\\.id").val()
			};
			$.ajax({
				type : "post",
				url : ctx + "/sys/menu/save.do",
				data : params,
				beforeSend : function(XMLHttpRequest) {
				},
				success : function(data, textStatus) {
					var zTree = $.fn.zTree.getZTreeObj("tree");
					var node = zTree.getNodeByParam("id", data.sysMenuPo.id, null);
					if (node == null) {
						var pnode = null;
						node = {};
						node.name = data.sysMenuPo.menuName;
						node.id = data.sysMenuPo.id;
						if (data.sysMenuPo.parent.id == "") {
							node.pid = "0";
						} else {
							node.pid = data.sysMenuPo.parent.id;
						}
						pnode = zTree.getNodeByParam("id", node.pid, null);

						if ("0" != data.sysMenuPo.parent.id && "menu" == data.sysMenuPo.resourceType) {
							node.icon = ctx + "/css/zTreeStyle/img/icons/twolevelmenu.png";
						}
						if ("function" == data.sysMenuPo.resourceType) {
							node.icon = ctx + "/css/zTreeStyle/img/icons/function.png";
						}
						if ("0" == data.sysMenuPo.parent.id && "menu" == data.sysMenuPo.resourceType) {
							node.icon = ctx + "/css/zTreeStyle/img/icons/onelevelmenu.png";
						}
						zTree.addNodes(pnode, node, true);
					} else {
						node.name = data.sysMenuPo.menuName;
					}

					zTree.updateNode(node, true);

					$("#show").hide();
					closeView("edit");
					$("#showRoot").show();
				},
				complete : function(XMLHttpRequest, textStatus) {
				},
				error : function() {
					alert("提交失败！");
					flage = false;
				}
			});
		}
	}
	function deletes() {
		var params = {
			'sysMenuPo.id' : $("#sysMenuPo\\.id").val()
		};

		showTip("确定删除该记录吗?", function() {
			$.ajax({
				type : "post",
				url : ctx + "/sys/menu/delete.do",
				data : params,
				beforeSend : function(XMLHttpRequest) {
				},
				success : function(data, textStatus) {
					var zTree = $.fn.zTree.getZTreeObj("tree");
					var node = zTree.getNodeByParam("id", $("#sysMenuPo\\.id").val(), null);
					zTree.removeNode(node, false);
					showTip("删除成功!");
				},
				complete : function(XMLHttpRequest, textStatus) {
				},
				error : function() {
					showTip("删除失败!");
					flage = false;
				}
			});
		});
	}
	function show(type) {
		var zTree = $.fn.zTree.getZTreeObj("tree");
		var nodes = zTree.getSelectedNodes();
		if (type == "1") {
			for (var i = 0; i < nodes.length; i++) {
				load(nodes[i].id);
			}
			openDialog("edit", "编辑菜单"+info, 370, 400);
		} else if (type == "0") {
			$("#sysMenuPo\\.id").val("");
			$("#sysMenuPo\\.menuName").val("");
			$("#sysMenuPo\\.menuCode").val("");
			$("#sysMenuPo\\.menuUrl").val("");
			$("#sysMenuPo\\.resourceType").val("");
			$("#sysMenuPo\\.menuDesc").val("");
			$("#sysMenuPo\\.orderId").val("");
			$("#sysMenuPo\\.parent\\.id").val("0");
			$("#sysMenuPo_parent_name").text("");
			$("#sysMenuPo_parent_name2").text("");
			openDialog("edit", "新增菜单"+info, 370, 400);
		} else {
			for (var i = 0; i < nodes.length; i++) {
				$("#sysMenuPo_parent_name2").text(nodes[i].name);
				$("#sysMenuPo\\.parent\\.id").val(nodes[i].id);
			}
			$("#sysMenuPo\\.id").val("");
			$("#sysMenuPo\\.menuName").val("");
			$("#sysMenuPo\\.menuCode").val("");
			$("#sysMenuPo\\.menuUrl").val("");
			$("#sysMenuPo\\.resourceType").val("");
			$("#sysMenuPo\\.menuDesc").val("");
			$("#sysMenuPo\\.orderId").val("");
			openDialog("edit", "新增子菜单"+info, 370, 400);
		}
	}
	function closeView(divId) {
		$("#" + divId).dialog("close");
	}
	function openDialog(divId, title, width, height) {
		$("#" + divId).dialog({
			autoOpen : true,
			modal : true,
			height : height,
			width : width,
			title : title,
			resizable : false
		});
	}
</script>
<style type="text/css">
html,body {
	height: 100%
}
</style>
</head>
<body class="main1">

	<div id="nav">
		    <i class="home"></i> <span>当前位置:</span><span class="active">系统管理-&gt;菜单管理</span>
	</div>

	<div id="content" class="main">
		<div id="treeDiv" class="leftTree">
			<ul id="tree" class="ztree"></ul>
		</div>
		<div id="rightContentDiv" style="width:81%;overflow: auto;float:left;clear:right; margin:0 auto;text-align:center;">
			<div id="showRoot" style="display: block;padding-top: 100px;">
				<shiro:hasPermission name="menu:save">
					<input type="button" class="btn" id="btn_add_scp" onclick="show('0')" value="新建菜单"
						style="margin-right: 5px;margin-left: 5px;" />
				</shiro:hasPermission>
			</div>
			<jsp:include page="update.jsp"></jsp:include>
		</div>
	</div>

</body>
</html>