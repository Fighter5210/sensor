<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/jquery_common.jsp"%>
<html>
<head>
<title>产品管理-添加产品</title>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/jquery_common.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/zTree_load.jsp"%>
<script type="text/javascript"
	src="${ctx}/pages/product/js/sendCommand.js"></script>
<script type="text/javascript">
//屏蔽右键
</script>
<style type="text/css">
.pageFormContent p {
	width: 355px;
}
</style>
</head>

<body class="main1">
	<div class="content-main clear">
		<div id="nav">
			<i class="home"></i> <span>当前位置:</span><span class="active">产品管理-&gt;发送命令</span>
		</div>
		<div class="pageFormContent"
			style="padding-top: 0px; padding-bottom: 7px;overflow: hidden;">
		 <form action="" method="post" id="add_update_Product_Form">
			<table><tr><td>
			<input type="hidden" id="productId" value="${productId}">
			<div id="updateTab" class="pageFormContent2" style="overflow: hidden;">
					<font color="red">*</font>温度标准值:<input class="textInput"
						type="text" id="temperatureStd" name="productVo.temperatureStd" maxlength="5" />
					<font color="red">*</font>湿度标准值: <input class="textInput"
					type="text" id="humidityStd" name="productVo.humidityStd" maxlength="5"/>
					<input type="button"  class="button white"  value="保存" id="addStd" name="addStd"
						title="保存" 
						onclick="updateStd();" maxlength="2" style="height:25px;width:50px;padding:0 0;"> 
			</div>
			    
			<div>
			    <input type="button" class="button blue" id="sendCommand" name="sendCommand" value="下发读取产品温湿度数据指令"
				title="下发读取产品温湿度数据指令" onclick="sendSensorCommand();"> 
				<input type="button" class="button blue" id="sendCommand" name="sendCommand" value="放弃数据"
				title="放弃数据" onclick="clearData();"> 
				<input type="button" class="button blue" id="saveProductData" name="saveProductData" value="保存数据"
				title="保存数据" onclick="saveData();"> 
			</div>
			</td>
			<td style="align:right">
			<table>
				<tr>
				<td>
				    <div style="height:15px;width:50px;background-color:#FFFF37;margin-bottom:1px"></div>
				</td>
				<td>：校验不通过的数据</td>
				</tr>
				<tr>
				<td>
				    <div style="height:15px;width:50px;background-color:#FF5151;margin-bottom:1px"></div>
				</td>
				<td>：未返回指令的数据</td>
				</tr>
			</table>
			</td>
			</tr></table>
		</form>
		</div>
		<div class="panel clear" id="gridMain">
			<table id="gridTable"></table>
		</div>
	</div>
</body>
</html>
