function initSysRoleList() {
	$("#gridTable").jqGrid({
		url : ctx+"/sys/role/getSysRoleList.action",
		rownumbers : true, // 是否显示前面的行号
		datatype : "json", // 返回的数据类型
		mtype : "post", // 提交方式
		height : 300,
		autowidth : true, // 是否自动调整宽度
		multiselect:true,
		multiboxonly: false,
		colModel : [ {
			name : "roleId",
			index : "roleId",
			label : "roleId",
			width : 0,
			sortable : true,
			align : 'center',
			hidden:true	
		},  {
			name : "roleName",
			index : "roleName",
			label : "角色名称",
			width : 200,
			sortable : true,
			align : 'center'
		},{
			name : "createUser",
			index : "createUser",
			label : "创建人",
			width : 200,
			sortable : true,
			align : 'center'
		},{
			name : "createDatetime",
			index : "createDatetime",
			label : "创建时间",
			width : 200,
			sortable : true,
			align : 'center',
			formatter : function(cellValue, options,
					rowObject) {
				return formatTime(cellValue);
			}
		},{
			name : "remark",
			index : "remark",
			label : "备注",
			width : 200,
			sortable : true,
			align : 'center'
		}, {
			name : 'option',
		    index : 'option',
		    label : "操作",
			width : 150,
			align : "center",
			sortable:false,
			title : false,
			formatter : function(cellValue,options,rowObject) {
				var updateflag = $("#updateFlag").val();
				var deleteFlag = $("#deleteFlag").val();
				var userListFlag = $("#userListFlag").val();
				var str1="";
				var str2="";
				var str3="";
				if(updateflag=="1"){
					str1= "<input type='button' class='btn_edit_s' onclick=showUpdateDiv('"+rowObject.roleId+"')  title='修改' />&nbsp;&nbsp;"
				}
				if(deleteFlag=="1"){
					str2 = "<input type='button'  class='btn_del_s' onclick=deleteSysRole('"+rowObject.roleId+"') title='删除'/>&nbsp;&nbsp;"
				}
				if(userListFlag=="1"){
					str3 = "<input type='button'  class='btn_search_s' onclick=addUser('"+rowObject.roleId+"') title='查看角色用户'/>"
				}
				
				return 	str1+str2+str3;
			}														
		}],
		
		viewrecords : true,
		sortname : "roleName",
		rowNum : 10,
		rowList : [ 5, 10, 15, 20, 30 ],
		prmNames : {
			search : "search"
		},
		jsonReader : {
			root : "dataList",
			records : "record",
			repeatitems : false
		},
		pager : "#gridPager",
//		caption : "角色信息记录",
		hidegrid : false
	});
	$("#updateForm").validate({
		rules: {
			roleNameInput: { required: true }
		},
	 messages: {
		  roleNameInput: {required: "[角色名称]不能为空"}
		
		},
		submitHandler: function() {
			updateOrSaveData();
		}
	});
}
	

/**
 * 打开角色下关联用户窗口
 */
function addUser(roleId){
	$("#roleId").val(roleId);
	//先卸载jqgrid，这里可以重复使用jqGrid方法进行加载，而不必使用reload方法
	$("#gridTableUser").jqGrid().GridUnload("gridTableUser");
	  autoRoleUser();//加载用户信息
	openDialog('userAuthorizationDiv','所属角色下用户列表',667,470);
}

/**
 * 角色下用户列表
 * @return
 */
function autoRoleUser() {
	$("#gridTableUser").jqGrid({
		url : ctx+"/sys/role/findSysUserListAct.action",
		rownumbers : true, // 是否显示前面的行号
		datatype : "json", // 返回的数据类型
		mtype : "post", // 提交方式
		height : 300,
		autowidth : true, // 是否自动调整宽度
		multiselect:false,
		multiboxonly: false,
		postData: {"roleId" :$("#roleId").val()},
		colModel : [ {
			name : "userId",
			index : "userId",
			label : "userId",
			width : 0,
			sortable : true,
			align : 'center',
			hidden:true	
		},  {
			name : "lastName",
			index : "lastName",
			label : "用户名",
			width : 140,
			sortable : true,
			align : 'center',
			formatter: function(cellValue,options,rowObject){
					return rowObject.firstName+" "+cellValue;
		        }
		},{
			name : "loginName",
			index : "loginName",
			label : "登录名",
			width : 139,
			sortable : true,
			align : 'center'
		},{
			name : "email",
			index : "email",
			label : "邮箱",
			width : 140,
			sortable : true,
			align : 'center'
		}, {
			name : "phone",
			index : "phone",
			label : "电话",
			width : 135,
			sortable : true,
			align : 'center'
		}],
		
		viewrecords : true,
		sortname : "loginName",
		rowNum : 10,
		rowList : [ 5, 10, 15, 20, 30 ],
		prmNames : {
			search : "searchUserUser"
		},
		jsonReader : {
			root : "dataList",
			records : "record",
			repeatitems : false
		},
		pager : "#gridPagerUser",
		hidegrid : false
	});
}

function saveOrUpdateBtn(){
	$("#updateForm").submit();  
}

function search() {
	jqGridReload("gridTable", {
		"roleName" : $("#roleName").val()
	});
}

function deleteSysRole(roleId){
	if(roleId){
		showTip("确定删除数据?",function(){
			$.ajax({
				type:'post',
				datatype : "json",
				url:ctx+"/sys/role/deleteSysRolePoByRoleIdAct.action",
				async : false,
				data:{"sysRolePo.roleId":roleId},
				success:(function(data){
					//如果返回数据不为空，说明被删除角色有关联用户数据
					if(data.rtnMsg!=""){
						showTip(data.rtnMsg);
					}else{
						showTip("删除成功");
						$("#gridTable").jqGrid().trigger("reloadGrid");
					}
				}),
				error:function(XMLHttpRequest, textStatus, errorThrown){
					showTip("删除失败!");
				} 
			});
		});
	}else{
		var ids = jQuery("#gridTable").jqGrid('getGridParam','selarrrow');

		if(ids.length == 0){
			showTip("至少选择一条数据。");
			return;
		}
		
		var rtnMsgList = [];
		var list = [];
		$(ids).each(function (index,id2){
			var rowData = $("#gridTable").getRowData(id2);
			list[list.length] = rowData.roleId;
			})
	
		showTip("确定删除数据?",function(){
			$.ajax({
				type:'post',
				datatype : "json",
				url:ctx+"/sys/role/deleteSysRolePoByRoleIdAct.action",
				async : false,
				data:{"sysRolePo.roleId":list.join(",")},
//				beforeSend:(function(data){
//					if(confirm("确定删除数据？")){
//						return true;
//					}else{
//						return false;
//					}
//				}),
				success:(function(data){
					//如果返回数据不为空，说明被删除角色有关联用户数据
					if(data.rtnMsg!=""){
						showTip(data.rtnMsg);
					}else{
						showTip("删除成功");
						$("#gridTable").jqGrid().trigger("reloadGrid");
					}
				}),
				error:function(XMLHttpRequest, textStatus, errorThrown){
					showTip("删除失败。");
				} 
			});
		
		});
	}
}



	// 修改角色信息
	function showUpdateDiv(objectId){
		$("label.error").remove();
		$("#sysRoleUpdateDiv").dialog({ 
				autoOpen : true,
				modal:true,
				height:230,
				width:380,
				title:'修改角色信息'+info,
		        resizable:false
		});
//		clearTab();
		
		//清除form所有数据
		clearForm($('#updateForm'));
		
		$.post(ctx+"/sys/role/findSysRoleByIdAct.action",
			{"sysRolePo.roleId":objectId},
			function(data){
				$("#roleNameInput").val(data.roleName);
				$("#remarkInput").val(data.remark);
				$("#roleId").val(data.roleId);
				$("#roleIsActive").val(data.isActive);
			}
		)
		
		//添加隐藏域的默认值
		$("#roleMethod").val("update");
		
	}

	// 查看角色详细信息。
	function showSysRoleShowDiv(objectId){
		$("label.error").remove();
		$("#SysRoleShowDiv").dialog({
				autoOpen : true,
				modal:true,
				height:280,
				width:380,
				title:'角色信息详情',
//				draggable: false,
		        resizable:false
		})
//		clearTab();
		//清除form所有数据
		clearForm($('#showForm'));
		
		$.post(ctx+"/sys/role/findSysRoleByIdAct.action",
			{"sysRolePo.roleId":objectId},
			function(data){
				$("#roleNameShow").html(data.roleName);
				$("#remarkShow").html(data.remark);
				$("#roleIsActive").val(data.isActive);
			}
		)
	}
	

 	//修改或者添加角色信息
	function updateOrSaveData(){
		var roleMethod = $("#roleMethod").val();
		$("label.error").remove();
		var roleId=$("#roleId").val();//角色id
		var roleNameInput=$("#roleNameInput").val();//
		var remarkInput=$("#remarkInput").val();//
		var roleIsActive=$("#roleIsActive").val();//
		var url;
		if(roleMethod=="update"){
			url= ctx+"/sys/role/updateSysRolePoAct.action"
		}else{
			url= ctx+"/sys/role/saveSysRolePoAct.action"
		}
		var data = {	'sysRolePo.roleId':roleId,
						'sysRolePo.roleName':roleNameInput,
						'sysRolePo.remark':remarkInput,
						'sysRolePo.isActive':roleIsActive
						};
	
		$.ajax({
			type:'post',
			datatype : "json",
			url:url,
			async : false,
			data:data,
			/*beforeSend:(function(data){
				return validate(datas);
			}),*/
			success:(function(data){
				if(data.rtnMsg!=""){
					showTip(data.rtnMsg);
				}else{
					showTip("保存成功。");
					$("#sysRoleUpdateDiv").dialog("close");
//					$("#gridTable").jqGrid().trigger("reloadGrid");
					// 先卸载jqgrid，这里可以重复使用jqGrid方法进行加载，而不必使用reload方法
					$("#gridTable").jqGrid("GridUnload");
					initSysRoleList();
				}
			}),
			error:function(XMLHttpRequest, textStatus, errorThrown){
				showTip("保存失败。");
			} 
		});
	}

	// 添加角色信息数据
	function createSysRole(){
		$("label.error").remove();
		$("#sysRoleUpdateDiv").dialog({
				autoOpen : true,
				modal:true,
				height:230,
				width:380,
				title:'添加角色信息'+info,
				resizable:false
		});
		//清除form所有数据
		clearForm($('#updateForm'));
		//添加隐藏域的默认值
		$("#roleMethod").val("save");
	}
	
	function clearTab(){
		 //var tab = document.getElementById("updateTab") ;
		 var inputs = document.getElementsByTagName("input"); 
		 for(var k=0;k<inputs.length;k++) 
		 { 
			 if(inputs[k].type!='button'&&inputs[k].type!='hidden'){
				 inputs[k].value=""; 
			 }
		 } 
	}
	
	function closeView(divId){
		$("#"+divId).dialog("close");
	}
	
	function clearForm(form) {
		  // iterate over all of the inputs for the form
		  // element that was passed in
		  $(':input', form).each(function() {
		    var type = this.type;
		    var tag = this.tagName.toLowerCase(); // normalize case
		    // it's ok to reset the value attr of text inputs,
		    // password inputs, and textareas
		    if (type == 'text' || type == 'password' || tag == 'textarea')
		      this.value = "";
		    // checkboxes and radios need to have their checked state cleared
		    // but should *not* have their 'value' changed
		    else if (type == 'checkbox' || type == 'radio')
		      this.checked = false;
		    // select elements need to have their 'selectedIndex' property set to -1
		    // (this works for both single and multiple select elements)
		    else if (tag == 'select')
		      this.selectedIndex = -1;
		  });
		};
	