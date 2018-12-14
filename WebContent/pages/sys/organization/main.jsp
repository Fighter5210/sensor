<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

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
<title>系统管理-部门管理</title>
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
			url : ctx + "/sys/organization/loadtree.do",
			success : function(data, textStatus) {
				var zNodes = data.list;

				var root = {
					'id' : '0',
					'name' : '部门管理',
					'pid' : '-1',
					'open' : true
				};
				zNodes[zNodes.length] = root;
				t = $.fn.zTree.init(t, setting, zNodes);
				return true;
			},
			error : function() {
				showTip(exception_info);
				flage = false;
			}
		});
	});
	function onMouseDown(event, treeId, treeNode) {
		if (treeNode.id != '0') {
			load(treeNode.id);
		} else {
			$("#showRoot").show();
			$("#show").hide();
			$("#edit").hide();
		}
	}
	function load(nodeId) {
		$("#sysOrganizationPo_orgName").text("");
		$("#sysOrganizationPo_remark").text("");
		$("#sysOrganizationPo_createTime").text("");
		$("#sysOrganizationPo_orgId").text("");
		$("#sysOrganizationPo_parent_orgName").text("");
		$("#sysOrganizationPo_parent_orgName2").text("");
		$("#sysOrganizationPo\\.orgId").val("");
		$("#sysOrganizationPo\\.orgName").val("");
		$("#sysOrganizationPo\\.remark").val("");
		$("#sysOrganizationPo\\.orgId").val("");
		$("#sysOrganizationPo\\.parent\\.orgId").val("");
		$.ajax({
			type : "post",
			url : ctx + "/sys/organization/load.do",
			data : {
				"sysOrganizationPo.orgId" : nodeId
			},
			beforeSend : function(XMLHttpRequest) {
			},
			success : function(data, textStatus) {
				$("#showRoot").hide();
				$("#show").show();
				var zTree = $.fn.zTree.getZTreeObj("tree");
				$("#sysOrganizationPo_orgName").text(
						data.sysOrganizationPo.orgName);
				$("#sysOrganizationPo_remark").text(
						data.sysOrganizationPo.remark);
				var createDateTime = data.sysOrganizationPo.createDatetime;
				$("#sysOrganizationPo_createTime").text(createDateTime.replace("T"," "));
				$("#sysOrganizationPo_orgId")
						.text(data.sysOrganizationPo.orgId);

				var pnode = null;
				if (data.sysOrganizationPo.parent != null) {
					pnode = zTree.getNodeByParam("id",
							data.sysOrganizationPo.parent.orgId, null);
					$("#sysOrganizationPo\\.parent\\.orgId").val(
							data.sysOrganizationPo.parent.orgId);
				}
				if (pnode != null) {
					$("#sysOrganizationPo_parent_orgName").text(pnode.name);
					$("#sysOrganizationPo_parent_orgName2").text(pnode.name);
				} else {
					$("#sysOrganizationPo_parent_orgName").text("");
					$("#sysOrganizationPo_parent_orgName2").text("");
				}
				$("#sysOrganizationPo\\.orgId").val(
						data.sysOrganizationPo.orgId);

				$("#sysOrganizationPo\\.orgName").val(
						data.sysOrganizationPo.orgName);
				$("#sysOrganizationPo\\.remark").val(
						data.sysOrganizationPo.remark);
				$("#sysOrganizationPo\\.orgId").val(
						data.sysOrganizationPo.orgId);
			},
			complete : function(XMLHttpRequest, textStatus) {
			},
			error : function() {
				flage = false;
			}
		});
	}
	//表单提交验证!
	function formValidate() {
		var orgName = $("#sysOrganizationPo\\.orgName").val();
		if (orgName == null || orgName == '') {
			showTip("部门名称不能为空!");
			return false;
		} else {
			if (orgName.length > 10) {
				showTip("部门名称长度不能大于10个汉字!");
				return false;
			}
		}

		var remark = $("#sysOrganizationPo\\.remark").val();
		if (remark != null && remark != '' && remark.length > 50) {
			showTip("部门描述长度不能大于50个汉字!");
			return false;
		}

		return true;
	}

	//添加/保存数据;
	function save() {
		if (formValidate()) {
			var params = {
				'sysOrganizationPo.orgName' : $("#sysOrganizationPo\\.orgName")
						.val(),
				'sysOrganizationPo.remark' : $("#sysOrganizationPo\\.remark")
						.val(),
				'sysOrganizationPo.orgId' : $("#sysOrganizationPo\\.orgId")
						.val(),
				'sysOrganizationPo.parent.orgId' : $(
						"#sysOrganizationPo\\.parent\\.orgId").val()
			};
			$
					.ajax({
						type : "post",
						url : ctx + "/sys/organization/save.do",
						data : params,
						beforeSend : function(XMLHttpRequest) {
						},
						success : function(data, textStatus) {
							var zTree = $.fn.zTree.getZTreeObj("tree");
							var node = zTree.getNodeByParam("id",
									data.sysOrganizationPo.orgId, null);
							if (node == null) {
								var pnode = null;
								node = {};
								node.name = data.sysOrganizationPo.orgName;
								node.id = data.sysOrganizationPo.orgId;
								node.pid = data.sysOrganizationPo.parent.orgId;
								//node.icon = ctx+ "/css/zTreeStyle/img/icons/organ.png";
								pnode = zTree.getNodeByParam("id",
										data.sysOrganizationPo.parent.orgId,
										null);

								zTree.addNodes(pnode, node, true);
							} else {
								node.name = data.sysOrganizationPo.orgName;
							}
							//alert(node.icon );
							zTree.updateNode(node);

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
			'sysOrganizationPo.orgId' : $("#sysOrganizationPo\\.orgId").val()
		};

		showTip("确定删除该记录吗?", function() {
			$.ajax({
				type : "post",
				url : ctx + "/sys/organization/delete.do",
				data : params,
				beforeSend : function(XMLHttpRequest) {
				},
				success : function(data, textStatus) {
					var zTree = $.fn.zTree.getZTreeObj("tree");
					var node = zTree.getNodeByParam("id", $(
							"#sysOrganizationPo\\.orgId").val(), null);
					zTree.removeNode(node, false);
				},
				complete : function(XMLHttpRequest, textStatus) {
					showTip("删除成功!");
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
			for ( var i = 0; i < nodes.length; i++) {
				load(nodes[i].id);
			}
			openDialog("edit", "编辑部门" + info, 370, 240);
		} else if (type == "0") {
			$("#sysOrganizationPo\\.orgId").val("");
			$("#sysOrganizationPo\\.orgName").val("");
			$("#sysOrganizationPo\\.remark").val("");
			$("#sysOrganizationPo\\.parent\\.orgId").val("");
			$("#sysOrganizationPo_parent_name").text("");
			$("#sysOrganizationPo_parent_name2").text("");
			openDialog("edit", "新增部门" + info, 370, 240);
		} else if (type == "2") {
			for ( var i = 0; i < nodes.length; i++) {
				$("#sysOrganizationPo_parent_orgName2").text(nodes[i].name);
				$("#sysOrganizationPo\\.parent\\.orgId").val(nodes[i].id);
			}
			$("#sysOrganizationPo\\.orgId").val("");
			$("#sysOrganizationPo\\.orgName").val("");
			$("#sysOrganizationPo\\.remark").val("");
			openDialog("edit", "新增子部门" + info, 370, 240);
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
</head>
<body class="main1">

	<div id="nav">
		    <i class="home"></i> <span>当前位置:</span><span class="active">系统管理-&gt;部门管理</span>
	</div>

	<div id="content" class="main">
		<div id="treeDiv" class="leftTree">
			<ul id="tree" class="ztree"></ul>
		</div>
		<div id="rightContentDiv"
			style="width: 70%; overflow: auto; min-height: 406px; height: 100%; float: left; clear: right; margin: 0 auto; text-align: center;">

			<div id="showRoot" style="display: block">
				<br> <br> <br> <br> <input type="button"
					class="btn" id="btn_add_scp" onclick="show('0')" value="新建部门"
					style="margin-right: 5px; margin-left: 5px;" />
			</div>
			<jsp:include page="update.jsp"></jsp:include>
		</div>
	</div>

</body>
</html>