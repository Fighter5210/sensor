<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/jquery_common.jsp"%>
<html>
<head>
<title>产品管理-产品数据</title>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/jquery_common.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/zTree_load.jsp"%>
<link rel="stylesheet" href="${ctx}/common/css/search_new.css"
	type="text/css"></link>
<script type="text/javascript"
	src="${ctx}/pages/product/js/productDataList.js"></script>
<script type="text/javascript">

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
			<i class="home"></i> <span>当前位置:</span><span class="active">产品管理-&gt;产品数据</span>
		</div>
		<div class="pageFormContent2"
			style="padding-top: 0px; padding-bottom: 7px;overflow: hidden;">
			<!--新添加的搜索标题-->
			<form action="">
				<div class="mainSearchBt">
					<i></i> <span class="bt">查询区域</span>
					<div class="searchBtn_Wrap">
					<input type="button"  class="button white"  value="查询" id="search" name="search"
						title="查询" 
						onclick="searchProduct();" maxlength="2" style="height:25px;width:50px;padding:0 0;"> 
					
					<input type="button"  class="button white"  value="导出" id="export" name="export"
						title="导出" 
						onclick="exportProduct();" maxlength="2" style="height:25px;width:50px;padding:0 0;"> 
					</div>
					<shiro:hasPermission name="productdata:delete">
							<input type="hidden" id="deleteFlag" name="deleteFlag" value="1" />
					</shiro:hasPermission>
				</div>
				<table height="50%" width="100%" align="left" border="0"
					class="searchWrap">
					<tr>
						<td align="right" class="name" >条形码：</td>
						<td align="left" class="content"><input type="text"
							id="barCode" name="barCode" class="textInput" /></td>
						<td align="right" class="name">生产日期：</td>
						<td align="left" class="content"><input type="text"
							id="productDate" name="productDate" class="textInput"  onfocus="WdatePicker({skin:'whyGreen',minDate: '1900-01-01', maxDate: '2090-12-30' })"/></td>
						<td align="right" class="name">批次：</td>
						<td align="left" class="content"><input type="text"
							id="productBatch" name="productBatch" class="textInput" /></td>
						<shiro:hasPermission name="productdata:delete">	
						   <td align="left" class="content">
						   <select
								id="isDelete" name="isDelete"
								class="selInput">
								<option value="0">未删除</option>
								<option value="1">已删除</option>
							</select>
						   </td>
					   </shiro:hasPermission>
					</tr>
				</table>
			</form>
		</div>
		<div class="panel clear" id="gridMain">
			<table id="gridTable"></table>
			<div id="gridPager"></div>
		</div>
	</div>
</body>
</html>
