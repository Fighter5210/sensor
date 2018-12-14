$(function() {
	showSysParam();
});

function formValidate() {
	var commIntervalTime = $("#commIntervalTime").val();
	var temAllowOffset = $("#temAllowOffset").val();
    var	humAllowOffset = $("#humAllowOffset").val();
    var regInt = new RegExp("^[0-9]*$"); 
    var regFloat = new RegExp("^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$");
    
	if (commIntervalTime == null || commIntervalTime == "") {
		showTip("指令间隔不能为空!");
		return false;
	}else if(!regInt.test(commIntervalTime)){
	    showTip("指令间隔必须为非负正数！");
		return false;
	}
	if (temAllowOffset == null || temAllowOffset == "") {
		showTip("温度允许偏差不能为空!");
		return false;
	}else if(!regFloat.test(temAllowOffset) && !regFloat.test(temAllowOffset)){
	    showTip("温度允许偏差必须为非负数字！");
		return false;
	}
	if (humAllowOffset == null || humAllowOffset == "") {
		showTip("湿度允许偏差不能为空!");
		return false;
	}else if(!regFloat.test(humAllowOffset) && !regFloat.test(humAllowOffset)){
	    showTip("湿度允许偏差必须为非负小数！");
		return false;
	}
	return true;
}

function showSysParam(){
	$.ajax({
		async : false,
		cache : false,
		url : ctx + "/sys/param/findSysParamById.action",
		data : {
			"id" : 1
		},
		type : 'post',
		dataType : "json",
		error : function() {// 
			showTip("保存成功!");
		},
		success : function(datas) { 
		    $("#id").val(datas.id);
			$("#commIntervalTime").val(datas.commIntervalTime);
			$("#temAllowOffset").val(datas.temAllowOffset);
			$("#humAllowOffset").val(datas.humAllowOffset);
		}
		
	});
}
function updateSysParamProxy(){
  if(formValidate()){
  	updateSysParam();
  }
}

function updateSysParam(){
		$.ajax({
		async : false,
		cache : false,
		url : ctx + "/sys/param/updateSysParam.action",
		data : {
		    "sysParamPo.id" : $("#id").val(),
			"sysParamPo.commIntervalTime" : $("#commIntervalTime").val(),
			"sysParamPo.temAllowOffset" : $("#temAllowOffset").val(),
			"sysParamPo.humAllowOffset" : $("#humAllowOffset").val()
		},
		type : 'post',
		dataType : "json",
		error : function() {
			showTip("保存失败!");
		},
		success : function(datas) {
			showTip("保存成功!");
		}
	});
}

