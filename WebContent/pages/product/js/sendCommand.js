var hasSave = "";
$(function() {
   productDataList();
});

function formValidate() {
	var temperatureStd = $("#temperatureStd").val();
	var humidityStd = $("#humidityStd").val();
	
	var regInt = new RegExp("^[0-9]*$");  
	var regFloat = new RegExp("^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$");
	if (temperatureStd == null || temperatureStd == "") {
		showTip("温度标准值不能为空!");
		return false;
	}else if(!regInt.test(temperatureStd) && !regFloat.test(temperatureStd)){
	    showTip("温度标准值必须为非负数字！");
		return false;
	}
	if (humidityStd == null || humidityStd == "") {
		showTip("湿度标准值不能为空!");
		return false;
	}else if(!regInt.test(humidityStd) && !regFloat.test(humidityStd)){
	    showTip("湿度标准值必须为非负数字！");
		return false;
	}
	return true;
}

function updateStd() {
    if(!formValidate()){
       return false;
    }
	var temperatureStd = $("#temperatureStd").val();
    var humidityStd = $("#humidityStd").val();
    var productId = $("#productId").val();
    
	var dataObj = {
		"productPo.temperatureStd" : temperatureStd,
		"productPo.humidityStd" : humidityStd,
		"productPo.id" : productId
	};
	$.ajax({
		async : false,
		cache : false,
		url : ctx + "/product/updateStd.action",
		data : dataObj,
		type : 'post',
		dataType : "json",
		error : function() {
			showTip("修改失败!");
		},
		success : function(datas) {
				showTip('修改成功！');
		}
	});
}

function sendSensorCommand() { 
    if(checkTemHumStd()){
      $("#gridTable").jqGrid('setGridParam',{datatype:'json'}).trigger('reloadGrid');
    }
}

function checkTemHumStd(){
    var productId = $("#productId").val();
    var flag=true;
	$.ajax({
		async : false,
		cache : false,
		url : ctx + "/product/getProductById.action?productPo.id="+productId,
		type : 'post',
		dataType : "json",
		error : function() {
			showTip("验证失败!");
			flag = false;
		},
		success : function(datas) {
		   if(datas.temperatureStd==0){
		   		alert("温度标准值未设置！");
		   		flag =  false;
		   }else if(datas.humidityStd==0){
		   		alert("湿度标准值未设置！");
		   		flag =  false;
		   }
		}
	});
	
	 return flag;
}

function  productDataList(){
var productId = $("#productId").val();
    
	var dataObj = {
		"productPo.id" : productId
	};

	$("#gridTable")
			.jqGrid(
					{
						url : ctx + "/product/sendCommand.action", // 提交的action地址
						postData : dataObj,
						rownumbers : true, // 是否显示前面的行号
						datatype : "local", // 返回的数据类型
						mtype : "post", // 提交方式
						height : 350,
						autowidth : true, // 是否自动调整宽度
						colModel : [
								{
									name : "id",
									index : "id",
									label : "id",
									width : 120,
									hidden:true,
									align : 'center'
								},
						       {
									name : "productId",
									index : "productId",
									label : "产品ID",
									width : 100,
									sortable : true,
									align : 'center',
									hidden : true
								},
								{
									name : "addressCode",
									index : "addressCode",
									label : "产品地址码",
									width : 100,
									sortable : true,
									align : 'center'
								},
								{
									name : "barCode",
									index : "barCode",
									label : "条形码",
									width : 180,
									align : 'center'
								},
								{
									name : "productDate",
									index : "productDate",
									label : "生产日期",
									width : 120,
									align : 'center'
								},
								{
									name : "productBatch",
									index : "productBatch",
									label : "批次",
									width : 120,
									align : 'center'
								},
								{
									name : "temperatureStd",
									index : "temperatureStd",
									label : "温度标准值",
									width : 120,
									align : 'center'
								},
								{
									name : "temperatureData",
									index : "temperatureData",
									label : "温度数据",
									width : 120,
									align : 'center'
								},
								{
									name : "temperatureOffset",
									index : "temperatureOffset",
									label : "温度偏差",
									width : 120,
									align : 'center'
								},
								{
									name : "humidityStd",
									index : "humidityStd",
									label : "湿度标准值",
									width : 120,
									align : 'center'
								},
								{
									name : "humidityData",
									index : "humidityData",
									label : "湿度数据",
									width : 120,
									align : 'center'
								},
								{
									name : "humidityOffset",
									index : "humidityOffset",
									label : "湿度偏差",
									width : 120,
									align : 'center'
								},
								{
									name : "isCheckNoPass",
									index : "isCheckNoPass",
									label : "是否CRC校验通过",
									width : 120,
									hidden:true,
									align : 'center'
								},
								{
									name : "isOverTemAllowOffset",
									index : "isOverTemAllowOffset",
									label : "是否超过温度标准值",
									width : 120,
									hidden:true,
									align : 'center'
								},
								{
									name : "isOverHumAllowOffset",
									index : "isOverHumAllowOffset",
									label : "是否超过湿度标准值",
									width : 120,
									hidden:true,
									align : 'center'
								},
								{
									name : "unReturnData",
									index : "unReturnData",
									label : "是否未返回数据",
									width : 120,
									hidden:true,
									align : 'center'
								}
								],
						viewrecords : true,
						altRows:true,
						sortname : "addressCode",
						sortorder : "desc",
						rowNum : 10,
						rowList : [  ],
						prmNames : {
							search : "search"
						},
						jsonReader : {
							root : "dataList",
							records : "record",
							repeatitems : false
						},
						gridComplete: function () {
							//获取列表数据
							var rowDatas = $("#gridTable").jqGrid('getRowData');
							for(i=0;i<rowDatas.length;i++){
							    var rowData = rowDatas[i]; 
								 if(rowData.isCheckNoPass=='1'){
								    $("#"+rowData.id).find("td").css("background-color", "#FFFF37");
								 }
								 if(rowData.isOverTemAllowOffset=='1'){
								    $("#"+rowData.id).find("td:eq(9)").css("color", "red");
								 }
								 if(rowData.isOverHumAllowOffset=='1'){
								    $("#"+rowData.id).find("td:eq(12)").css("color", "red");
								 }
								 if(rowData.unReturnData=='1'){
								    $("#"+rowData.id).find("td").css("background-color", "#FF5151");
								 }
						    }
							
						},
						pager : "#gridPager",
						rowNum: 0,
						hidegrid : false
					});
}

function clearData(){
   jQuery("#gridTable").jqGrid("clearGridData");
}
function saveData(){
   if(hasSave=="1"){
      alert("已经保存过了！");
   	  return false;
   }
   var obj = $("#gridTable").jqGrid("getRowData");
   var jsonStr = JSON.stringify(obj); 
   $.ajax({
	async : false,
	cache : false,
	url : ctx + "/product/saveProductData.action",
	data : {"jsonStr":jsonStr},
	type : 'post',
	dataType : "json",
	error : function() {
		showTip("保存失败!");
	},
	success : function(datas) {
			showTip('保存成功！');
			 hasSave = "1";
	}
});

}