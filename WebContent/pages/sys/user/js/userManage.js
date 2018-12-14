var method = "";
var choose_orgId = "";
var choose_userName = "";
/**
 * 初始化
 */
$(function() {
	initUserList();// 初始列表
	initOrgTree();// 初始部门树
	$("#orgName").attr("readOnly", "true");

	// 自适应宽度
	$("#gridTable").setGridWidth($("#gridMain").width());
	$(window).resize(function() {
		$("#gridTable").setGridWidth($("#gridMain").width());
	});
});

function initUserList() {
	$("#gridTable")
			.jqGrid(
					{
						url : ctx + "/sys/user/findUserPage.action", // 提交的action地址
						rownumbers : true, // 是否显示前面的行号
						datatype : "json", // 返回的数据类型
						mtype : "post", // 提交方式
						height : 300,
						autowidth : true, // 是否自动调整宽度
						colModel : [
								{
									name : "lastName",
									index : "us.last_name",
									label : "用户名",
									width : 120,
									sortable : true,
									align : 'center',
									formatter : function(cellValue, options,
											rowObject) {
										return rowObject.firstName + cellValue;
									}
								},
								{
									name : "loginName",
									index : "us.login_name",
									label : "登录名",
									width : 100,
									sortable : true,
									align : 'center'
								},
								{
									name : "email",
									index : "us.email",
									label : "邮箱",
									width : 120,
									sortable : true,
									align : 'center'
								},
								{
									name : "ipAddress",
									index : "us.ip_address",
									label : "IP地址",
									width : 100,
									sortable : true,
									align : 'center'
								},
								{
									name : "phone",
									index : "us.phone",
									label : "电话",
									width : 80,
									sortable : true,
									align : 'center'
								},
								{
									name : "orgName",
									index : "org.org_name",
									label : "所属部门",
									width : 80,
									sortable : true,
									align : 'center'
								},
								{
									name : "createDatetime",
									label : "创建时间",
									width : 120,
									sortable : false,
									align : 'center',
									formatter : function(cellValue, options,
											rewObject) {
										return formatTime(cellValue);
									}
								},
								{
									name : "操作",
									index : "option",
									sortable : false,
									align : "center",
									width : 120,
									title : false,
									formatter : function(cellVall, options,
											rowObject) {
										var result = "";

										var mod = "<input type='button' class='btn_edit_s' title='修改' onclick=modifyUser('"
												+ rowObject.userId
												+ "','update') />";
										var del = "<input type='button' class='btn_del_s' title='删除' onclick=deleteUser('"
												+ rowObject.userId + "') />";
										var role = "<input type='button' class='btn_link_s' title='角色授权' onclick=addRole('"
												+ rowObject.userId + "') />";
										var device = "<input type='button' class='btn_shuxing_s' title='用户设备授权' onclick=showUserAuthorizationDeviceDiv('"
												+ rowObject.userId
												+ "','"
												+ rowObject.loginName + "') />";
										var upFlag = $("#updateFlag").val();
										var delFlag = $("#deleteFlag").val();
										var rolFlag = $("#authorizationFlag")
												.val();

										if (upFlag == "1")
											result += mod;

										if (delFlag == "1")
											result += "  " + del;

										if (rolFlag == "1")
											result += "  " + role;
										return result;
									}
								} ],
						viewrecords : true,
						sortname : "org.org_name",
						sortorder : "desc",
						rowNum : 10,
						rowList : [ 5, 10, 15, 20, 30 ],
						prmNames : {
							search : "searchUser"
						},
						jsonReader : {
							root : "dataList",
							records : "record",
							repeatitems : false
						},
						pager : "#gridPager",
						hidegrid : false
					});
}

function searchUser() {
	jqGridReload("gridTable", {
		"loginName" : $("#loginName_labl").val()
		//"userType" : $("#userType_labl").val()
	});
}

/**
 * 所属部门
 * 
 * @return
 */
function showOrgTree() {
	initOrgTree();
	var treeTitle = "选取";
	if (method == "update")
		treeTitle = '【' + choose_userName + '】';

	$("#orgAuthorizationDiv").dialog({
		autoOpen : true,
		modal : true,
		height : 450,
		width : 360,
		title : treeTitle + '所属部门',
		resizable : false
	})
	$.fn.zTree.getZTreeObj("orgTree").checkAllNodes(false);

	var menuList = [];
	menuList.push(choose_orgId);

	if (menuList.length > 0) {
		setTimeout(function() {
			expandNode(menuList, "orgTree");
		}, 300);
	}
}

/**
 * 初始化部门
 * 
 * @return
 */
function initOrgTree() {
	var t = $("#orgTree");
	// 加载左侧树
	$.ajax({
		type : "post",
		url : ctx + "/sys/organization/loadtree.do",
		data : {},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(data, textStatus) {
			var zNodes = data.list;
			t = $.fn.zTree.init(t, {
				check : {
					enable : true,
					chkStyle : "radio",
					chkboxType : {
						"Y" : "s",
						"N" : "s"
					}
				},
				data : {
					simpleData : {
						enable : true,
						idKey : "id",
						pIdKey : "pid",
						rootPId : ""
					}
				},
				callback : {
					beforeClick : beforeClickmenus,// 点击事件
					onCheck : function(event, treeid, treeNode) {// 单选事件
						beforeClickmenus(treeid, treeNode);
					},
				}
			}, zNodes);
			return true;
		},
		complete : function(XMLHttpRequest, textStatus) {
		},
		error : function() {
			flage = false;
		}
	});
}

function beforeClickmenus(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("orgTree");
	var menunodes = zTree.getCheckedNodes(true);

	for ( var i = 0; i < menunodes.length; i++) {// 取消选中
		zTree.checkNode(menunodes[i], null, false);
	}
	zTree.checkNode(treeNode, null, true);// 选中当前
	return false;
}

/**
 * 选取部门节点
 */
function chooseAuthOrg() {
	var menutree = $.fn.zTree.getZTreeObj("orgTree");
	var menunodes = menutree.getCheckedNodes(true);
	if (menunodes.length > 1) {
		showTip("只能选择一个所属部门!");
		return;
	}
	$("#orgName").val(menunodes[0].name);
	choose_orgId = menunodes[0].id;
	closeView("orgAuthorizationDiv");// 关闭树窗
}

/**
 * 添加用户信息
 */
function addUser(methodname) {
	method = methodname;// insert
	clearUser();
	openDialog('add_update_User_Div', '添加用户信息' + info, 770, 396);
}

/**
 * 修改用户信息
 */
function modifyUser(userId, methodname) {
	method = methodname;// update
	$("#userId").val(userId);
	getUser(userId);
	openDialog('add_update_User_Div', '修改用户信息' + info, 770, 396);
}

/**
 * 角色复选框
 */
function addRole(userId) {
	$("#userId").val(userId);
	if ("" == $("#gridTableRole").text())
		autoUserRole();// 加载角色
	else
		searchUserRole();
	openDialog('roleAuthorizationDiv', '授予角色', 667, 470);
}

/**
 * 保存角色授予
 */
function chooseAuthRole() {
	var userId = $("#userId").val();
	var ids = jQuery("#gridTableRole").jqGrid('getGridParam', 'selarrrow');
	var list = [];
	var extis = 0;

	$(ids).each(function(index, id) {
		var rowData = $("#gridTableRole").getRowData(id);
		if (extis == 0) {
			var indexCount = rowData.roleCount.indexOf("已授予");
			if (indexCount > -1) {
				extis = 1;// 已被授予角色
			}
		}
		list.push(rowData.roleId);
	});

	if (extis == 1) {
		showTip("存在已经被授予的角色，请重新选择!");
		return;
	}

	$.ajax({
		async : false,
		cache : false,
		url : ctx + "/sys/user/saveAutoRole.action",
		data : {
			"userId" : userId,
			"roleIds" : list.join(",")
		},
		type : 'post',
		dataType : "json",
		error : function() {// 请求失败处理函数
			showTip("保存角色授予信息时发生异常!");
		},
		success : function(datas) {
			searchUserRole();
		}
	});
}

/**
 * 取消角色授予
 */
function cancleAuthRole() {
	var userId = $("#userId").val();
	var ids = jQuery("#gridTableRole").jqGrid('getGridParam', 'selarrrow');
	var list = [];

	$(ids).each(function(index, id) {
		var rowData = $("#gridTableRole").getRowData(id);
		list.push(rowData.roleId);
	})

	$.ajax({
		async : false,
		cache : false,
		url : ctx + "/sys/user/calcleAutoRole.action",
		data : {
			"userId" : userId,
			"roleIds" : list.join(",")
		},
		type : 'post',
		dataType : "json",
		error : function() {// 请求失败处理函数
			showTip("取消角色授予信息时发生异常!");
		},
		success : function(datas) {
			searchUserRole();
		}
	});
}

/**
 * 打开对话框
 * 
 * @param divId
 * @param title
 * @param width
 * @param height
 * @return
 */
function openDialog(divId, title, width, height) {
	$("#" + divId).dialog({
		autoOpen : true,
		modal : true,
		height : height,
		width : width,
		title : title,
		resizable : false
	})
}

/**
 * 提交保存
 * 
 * @return
 */
function saveUserBtn() {
	if (formValidate()) {
		saveUser();
	}
}

/**
 * 表单提交验证;
 */
function formValidate() {
	var firstName = $("#firstName").val();
	if (firstName == null || firstName == "") {
		showTip("用户姓不能为空!");
		return false;
	}

	var lastName = $("#lastName").val();
	if (lastName == null || lastName == "") {
		showTip("用户名不能为空!");
		return false;
	}

	var loginName = $("#loginName").val();
	if (loginName == null || loginName == "") {
		showTip("登录名不能为空!");
		return false;
	}

	var loginPassword = $("#loginPassword").val();
	if (loginPassword == null || loginPassword == "") {
		showTip("登录密码不能为空!");
		return false;
	}

//	var userType = $("#userType").val();
//	if (userType == null || userType == "") {
//		showTip("请选择用户类型!");
//		return false;
//	}

	var orgName = $("#orgName").val();
	if (orgName == null || orgName == "") {
		showTip("请选择所属部门!");
		return false;
	}

	return true;
}

/**
 * 保存用户信息
 */
function saveUser() {
	var userId = $("#userId").val();
	if ("insert" == method) {
		userId = null;
	}
	var firstName = $("#firstName").val();
	var lastName = $("#lastName").val();
	var loginName = $("#loginName").val();
	var loginNameOld = $("#loginNameOld").val();
	var loginPassword = $("#loginPassword").val();
	var loginPassOld = $("#loginPassOld").val();
	var email = $("#email").val();
	var ipAddress = $("#ipAddress").val();
	//var userType = $("#userType").val();
	var phone = $("#phone").val();
	var dataObj = {
		"sysUserPo.userId" : userId,
		"sysUserPo.firstName" : firstName,
		"sysUserPo.lastName" : lastName,
		"sysUserPo.loginName" : loginName,
		"sysUserPo.loginPassword" : loginPassword,
		"sysUserPo.email" : email,
		"sysUserPo.ipAddress" : ipAddress,
		//"sysUserPo.userType" : userType,
		"sysUserPo.orgId" : choose_orgId,
		"sysUserPo.phone" : phone,
		"loginNameOld" : loginNameOld,
		"loginPassOld" : loginPassOld
	};
	$("#saveUser").attr({
		"disabled" : true
	});
	$.ajax({
		async : false,
		cache : false,
		url : ctx + "/sys/user/saveUser.action",
		data : dataObj,
		type : 'post',
		dataType : "json",
		error : function() {// 请求失败处理函数
			showTip("保存用户信息时发生异常!");
		},
		success : function(datas) {
			if (datas.result == "faile") {
				showTip("登录名已存在，请重新输入!");
			} else {
				showTip(add_success);
				closeView('add_update_User_Div');// 关闭
				searchUser();
			}
			$("#saveUser").attr({
				"disabled" : false
			});
		}
	});
}

/**
 * 根据ID用户信息
 * 
 * @param nodeId
 */
function getUser(userId) {
	$.ajax({
		async : false,
		cache : false,
		url : ctx + "/sys/user/findUserById.action",
		data : {
			"userId" : userId
		},
		type : 'post',
		dataType : "json",
		error : function() {// 请求失败处理函数
			showTip("获取用户信息时发生异常!");
		},
		success : function(datas) {
			initUser(datas);
		}
	});
}

/**
 * 删除用户
 */
function deleteUser(userId) {
	showTip("确定删除该用户吗？", function() {
		$.ajax({
			async : false,
			cache : false,
			url : ctx + "/sys/user/deleteUser.action",
			data : {
				"userId" : userId
			},
			type : 'post',
			dataType : "json",
			success : function(datas) {
				if (datas.result == "0") {
					showTip("删除用户信息时发生异常!");
				}
				searchUser();
			}
		});
	});
}

/**
 * 初始化用户信息添加页面
 * 
 * @param userId
 */
function clearUser() {
	// selectByValue("srTypeId",srTypeId);
	$("#firstName").val("");
	$("#lastName").val("");
	$("#loginName").val("");
	$("#loginPassword").val("");
	$("#email").val("");
	$("#ipAddress").val("");
	$("#userType").val("");
	$("#orgName").val("");
	$("#phone").val("");
	choose_orgId = "";
}

/**
 * 初始用户信息查看页面
 * 
 * @param datas
 */
function initUser(datas) {
	choose_orgId = datas.orgId;
	choose_userName = datas.firstName + " " + datas.lastName;
	$("#firstName").val(datas.firstName);
	$("#lastName").val(datas.lastName);
	$("#loginName").val(datas.loginName);
	$("#loginNameOld").val(datas.loginName);
	$("#loginPassword").val(datas.loginPassword);
	$("#loginPassOld").val(datas.loginPassword);
	$("#email").val(datas.email);
	$("#ipAddress").val(datas.ipAddress);
	$("#userType").val(datas.userType);
	$("#orgName").val(datas.orgName);
	$("#phone").val(datas.phone);
	// initSevureTierSelect(datas.secureAreaCode);
}

/**
 * 关闭对话框
 * 
 * @param divId
 * @return
 */
function closeView(divId) {
	$("#" + divId).dialog("close");
}

/**
 * 展开节点
 * 
 * @param menuList
 * @return
 */
function expandNode(menuList, treename) {
	if (menuList.length > 0) {
		for (i = 0; i < menuList.length; i++) {
			var zTree = $.fn.zTree.getZTreeObj(treename);
			var node = zTree.getNodeByParam('id', menuList[i], null);
			zTree.checkNode(node, true, false);// 选中原有部门
			var parentNode = node.getParentNode();
			zTree.expandNode(parentNode, true, false, true); // 默认展开
		}
	}
}

/**
 * 授予角色
 * 
 * @return
 */
function autoUserRole() {
	$("#gridTableRole").jqGrid({
		url : ctx + "/sys/user/findSysRoleList.action",
		rownumbers : true, // 是否显示前面的行号
		datatype : "json", // 返回的数据类型
		mtype : "post", // 提交方式
		height : 300,
		autowidth : true, // 是否自动调整宽度
		multiselect : true,
		multiboxonly : false,
		postData : {
			"userIdchoose" : $("#userId").val()
		},
		colModel : [ {
			name : "roleId",
			index : "roleId",
			label : "roleId",
			width : 0,
			sortable : true,
			align : 'left',
			hidden : true
		}, {
			name : "roleName",
			index : "roleName",
			label : "角色名称",
			width : 200,
			sortable : true,
			align : 'left'
		}, {
			name : "remark",
			index : "remark",
			label : "备注",
			width : 238,
			sortable : true,
			align : 'left'
		}, {
			name : "roleCount",
			label : "是否授予",
			width : 95,
			sortable : true,
			align : 'left',
			formatter : function(cellValue, options, rewObject) {
				var result = "<font style='color:red'>未授予</font>";
				if (cellValue > 0)
					result = "<font style='color:green'>已授予</font>";
				return result;
			}
		} ],

		viewrecords : true,
		sortname : "roleName",
		rowNum : 10,
		rowList : [ 5, 10, 15, 20, 30 ],
		prmNames : {
			search : "searchUserRole"
		},
		jsonReader : {
			root : "dataList",
			records : "record",
			repeatitems : false
		},
		pager : "#gridPagerRole",
		hidegrid : false
	});
}

function searchUserRole() {
	var userRoleId = $("#userId").val();
	jqGridReload("gridTableRole", {
		"userIdchoose" : userRoleId
	});
}