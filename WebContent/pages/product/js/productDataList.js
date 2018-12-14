$(function() {
   productDataList();
});


function  productDataList(){

	$("#gridTable")
			.jqGrid(
					{
						url : ctx + "/product/getProductListByPage.action", // 提交的action地址
						rownumbers : true, // 是否显示前面的行号
						datatype : "json", // 返回的数据类型
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
									sortable : false,
									align : 'center',
									hidden : true
								},
								{
									name : "addressCode",
									index : "addressCode",
									label : "产品地址码",
									width : 100,
									sortable : false,
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
									name : "操作",
									index : "option",
									sortable : false,
									align : "center",
									width : 120,
									title : false,
									formatter : function(cellVall, options,
											rowObject) {
										var result = "";

										var del = "<input type='button' class='btn_del_s' title='删除' onclick=updateProductData('"
												+ rowObject.id + "','delete') />";
										
										var recover = "&nbsp;<input type='button' class='btn_shuxing_s' title='恢复' onclick=updateProductData('"
											+ rowObject.id + "','recover') />";
												
									    var deleteFlag = $("#deleteFlag").val();
									    if (deleteFlag == "1"){
											result += del;
											result += recover;
										}
										return result;
									}
								}
								],
						viewrecords : true,
						altRows:true,
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
						pager : "#gridPager",
						rowNum: 10,
						hidegrid : false,
						gridComplete: function () {
							//获取列表数据
							var rowDatas = $("#gridTable").jqGrid('getRowData');
							for(i=1;i<=rowDatas.length;i++){
							    var rowData = rowDatas[i-1]; 
								 if(rowData.isOverTemAllowOffset=='1'){
								    $("#"+rowData.id).find("td:eq(9)").css("color", "red");
								 }
								 if(rowData.isOverHumAllowOffset=='1'){
								    $("#"+rowData.id).find("td:eq(12)").css("color", "red");
								 }
						    }
							
						}
					});
}
/**
 * 删除用户
 */
function updateProductData(productDataId,flag) {
    var flagName = "";
    if(flag=='delete'){
       flagName='删除';
    }else{
       flagName='恢复';
    }
	showTip("确定"+flagName+"该条数据吗？", function() {
		$.ajax({
			async : false,
			cache : false,
			url : ctx + "/product/updateProductData.action",
			data : {
				"id" : productDataId,
				"flag":flag
			},
			type : 'post',
			dataType : "json",
			success : function(datas) {
				if (datas.result == "0") {
					showTip("发生异常!");
				}
				searchProduct();
			}
		});
	});
}
function searchProduct() {
	jqGridReload("gridTable", {
		"barCode" : $("#barCode").val(),
		"productDate" : $("#productDate").val(),
		"productBatch" : $("#productBatch").val(),
		"isDelete" : $("#isDelete").val()
	});
}


function exportProduct(){

    var barCode = $("#barCode").val();
    var productDate = $("#productDate").val();
    var productBatch = $("#productBatch").val();
    var isDelete = $("#isDelete").val()

     var form=$("<form>");//定义一个form表单
	 form.attr("style","display:none");
	 form.attr("target","");
	 form.attr("method","post");
	 form.attr("action",ctx + "/product/outPutProductData2Excel.action");
	 
	 var page=$("<input>");
	 page.attr("type","hidden");
	 page.attr("name","page");
	 page.attr("value",1);
	 form.append(page);
	 
	 var rows=$("<input>");
	 rows.attr("type","hidden");
	 rows.attr("name","rows");
	 rows.attr("value",60000);
	 form.append(rows);
	 
	 var barCodeObj=$("<input>");
	 barCodeObj.attr("type","hidden");
	 barCodeObj.attr("name","barCode");
	 barCodeObj.attr("value",barCode);
	 form.append(barCodeObj);
	 
	 var productDateObj=$("<input>");
	 productDateObj.attr("type","hidden");
	 productDateObj.attr("name","productDate");
	 productDateObj.attr("value",productDate);
	 form.append(productDateObj);
	 
	 var productBatchObj=$("<input>");
	 productBatchObj.attr("type","hidden");
	 productBatchObj.attr("name","productBatch");
	 productBatchObj.attr("value",productBatch);
	 form.append(productBatchObj);
	 
	 var isDeleteObj=$("<input>");
	 isDeleteObj.attr("type","hidden");
	 isDeleteObj.attr("name","isDelete");
	 isDeleteObj.attr("value",isDelete);
	 form.append(isDeleteObj);
	 
	 $("body").append(form);//将表单放置在web中
	 form.submit();//表单提交
	 
	 } 