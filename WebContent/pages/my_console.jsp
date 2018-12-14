<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/jquery_common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>高伟达-数据中心自动化运维管理平台</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

body {
	font-size: 12px;
	color: #222;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	background: #f0f0f0;
}

.clearfix:after {
	content: ".";
	display: block;
	height: 0;
	clear: both;
	visibility: hidden;
}

.clearfix {
	zoom: 1;
}

ul,li {
	list-style: none;
}

img {
	border: 0;
}

.wrapper {
	width: 400px;
	margin: 0 auto;
	padding-bottom: 50px;
}

h1 {
	height: 50px;
	line-height: 50px;
	font-size: 22px;
	font-weight: normal;
	font-family: "Microsoft YaHei", SimHei;
	margin-bottom: 20px;
}
</style>
<script type="text/javascript" src="../jquery/js/echarts-plain.js"></script>
<script type="text/javascript">
	function setEcharts() {
		var labelTop = {
			normal : {
				label : {
					show : true,
					position : 'center',
					textStyle : {
						baseline : 'bottom'
					}
				},
				labelLine : {
					show : false
				}
			}
		};
		var labelBottom = {
			normal : {
				color : '#ccc',
				label : {
					show : true,
					position : 'center',
					formatter : function(a, b, c) {
						return 100 - c + '%'
					},
					textStyle : {
						baseline : 'top'
					}
				},
				labelLine : {
					show : false
				}
			},
			emphasis : {
				color : 'rgba(0,0,0,0)'
			}
		};
		var radius = [ 40, 55 ];
	}
	//处理按钮 
	function dealRequest(url, todoId) {
		$.post(ctx + "/request/base/todoStartDeal.action", {
			todoId : todoId
		}, function(data) {
			if (data.result == "success") {
				var targetUrl = "";
				if (url.indexOf("?") > 0) {
					targetUrl = ctx + "/" + url + "&todoId=" + todoId;
				} else {
					targetUrl = ctx + "/" + url + "?todoId=" + todoId;
				}
				window.location.href = targetUrl;
			}
		});
	}
	//操作按钮
	function lookupWorkflow(srId) {
		$
				.post(
						ctx + "/request/base/findInstanceIdBySrId.action",
						{
							srId : srId
						},
						function(data) {
							window.location.href = ctx
									+ "/pages/workflow/instance/processInstance.jsp?state=view&instanceId="
									+ data.result;
						});
	}
	//最新实施完成的申请
	function NewestComplete() {
		$
				.post(
						ctx + '/request/base/findNewestCompleteRequest.action',
						{},
						function(result) {
							if (result.length > 0) {
								for ( var i = 0; i < result.length; i++) {
									var dateobj = new Date(
											result[i].closeTime.time);
									$("#NewestCompleteTab")
											.append(
													'<tr><td>'
															+ result[i].srCode
															+ '</td><td>'
															+ dateobj
																	.toLocaleDateString()
															+ "  "
															+ dateobj
																	.toLocaleTimeString()
															+ '</td><td>'
															+ result[i].appName
															+ '</td><td>'
															+ result[i].srStatus
															+ '</td><td>'
															+ "<input type='button' class='btn_apply_s' title='申请进度' onclick=lookupWorkflow('"
															+ result[i].srId
															+ "') />"
															+ '</td></tr>');
								}
							} else {
								$("#NewestCompleteTab").append(
										'<tr><td>无数据</td></tr>');
							}

						});
	}
	//我最新的服务申请
	function NewestCreate() {
		$
				.post(
						ctx + '/request/base/findNewestCreateRequest.action',
						{},
						function(result) {
							if (result.length > 0) {
								for ( var i = 0; i < result.length; i++) {
									var dateobj = new Date(
											result[i].createTime.time);
									$("#NewestCreateTab")
											.append(
													'<tr><td>'
															+ result[i].srCode
															+ '</td><td>'
															+ dateobj
																	.toLocaleDateString()
															+ "  "
															+ dateobj
																	.toLocaleTimeString()
															+ '</td><td>'
															+ result[i].appName
															+ '</td><td>'
															+ result[i].srStatus
															+ '</td><td>'
															+ "<input type='button' class='btn_apply_s' title='申请进度' onclick=lookupWorkflow('"
															+ result[i].srId
															+ "') />"
															+ '</td></tr>');
								}
							} else {
								$("#NewestCreateTab").append(
										'<tr><td>无数据</td></tr>');
							}

						});
	}
	//最新待我处理的申请
	function NewestWaitDeal() {
		$
				.post(
						ctx + '/request/base/findNewestWaitDealRequest.action',
						{},
						function(result) {
							if (result.length > 0) {
								for ( var i = 0; i < result.length; i++) {
									var dateobj = new Date(
											result[i].createTime.time);
									$("#NewestWaitDealTab")
											.append(
													'<tr><td>'
															+ result[i].srCode
															+ '</td><td>'
															+ dateobj
																	.toLocaleDateString()
															+ "  "
															+ dateobj
																	.toLocaleTimeString()
															+ '</td><td>'
															+ result[i].appName
															+ '</td><td>'
															+ result[i].srStatus
															+ '</td><td>'
															+ "<input type='button' class='btn_config_s' title='处理' onclick=dealRequest('"
															+ result[i].pageUrl
															+ "','"
															+ result[i].todoId
															+ "') />"
															+ '</td></tr>');
								}
							} else {
								$("#NewestWaitDealTab").append(
										'<tr><td>无数据</td></tr>');
							}

						});
	}

	//最新入库的设备
	function DeviceDiagram() {
		$.post(ctx + '/resmgt-common/device/selectDeviceDiagramInfo.action',
				{}, function(result) {
					if (result.length > 0) {
						for ( var i = 0; i < result.length; i++) {
							var dateobj = new Date(
									result[i].createDatetime.time);
							$("#DeviceDiagramTab").append(
									'<tr><td>' + dateobj.toLocaleDateString()
											+ " "
											+ dateobj.toLocaleTimeString()
											+ '</td><td>'
											+ result[i].deviceType
											+ '</td><td>' + result[i].sn
											+ '</td><td>' + result[i].poolName
											+ '</td></tr>');
						}
					} else {
						$("#DeviceDiagramTab").append('<tr><td>无数据</td></tr>');
					}

				});
	}

	//IP地址 使用情况
	function findRmNwCclass() {
		$
				.post(ctx + '/network/findRmNwCclassFullVoListAct.action', {},
						function(result) {
							if (result.length > 0) {
								for ( var i = 0; i < result.length; i++) {
									$("#findRmNwCclassTab").append(
											'<tr><td>'
													+ result[i].datacenterName
													+ '</td><td>'
													+ result[i].bclassName
													+ '</td><td>'
													+ result[i].ipNum
													+ '</td><td>'
													+ result[i].vIpNum
													+ '</td><td>'
													+ result[i].hIpNum
													+ '</td><td>'
													+ result[i].mIpNum
													+ '</td><td>'
													+ result[i].pIpNum
													+ '</td><td>'
													+ result[i].iLoIpNum
													+ '</td><td>'
													+ result[i].vmoIpNum
													+ '</td><td>'
													+ result[i].priIpNum
													+ '</td><td>'
													+ result[i].fsp1Num
													+ '</td><td>'
													+ result[i].fsp2Num
													+ '</td></tr>');
								}
							} else {
								$("#findRmNwCclassTab").append(
										'<tr><td>无数据</td></tr>');
							}

						});
	}

	//获取虚拟机类型对应的虚拟机数量
	function getVmTypeNum() {
		$.post(ctx + '/resmgt-common/device/selectVmTypeNumInfo.action', {},
				function(result) {
					if (result.length > 0) {
						var dateValue = [];
						var Xvalue = [];
						for ( var i = 0; i < result.length; i++) {
							dateValue[i] = {
								'value' : result[i].vmNum,
								'name' : result[i].vmType
							};
						}
						for ( var i = 0; i < result.length; i++) {
							Xvalue[i] = result[i].vmType;
						}
						var myChartTop = echarts.init(document
								.getElementById('topss'));
						setEcharts();
						var options = {
							title : {
								text : '计算资源图'
							},
							tooltip : {
								trigger : 'item',
								formatter : "{a} <br/>{b} : {c} ({d}%)"
							},
							legend : {
								orient : 'vertical',
								x : 'right',
								data : Xvalue
							},
							//color:['#00448a','#0580b9','#28c6b9','#84e6f1','#dddddd'],
							calculable : true,
							series : [ {
								name : '虚拟机类型',
								type : 'pie',
								radius : [ '50%', '70%' ],
								itemStyle : {
									normal : {
										label : {
											show : false
										},
										labelLine : {
											show : false
										}
									},
									emphasis : {
										label : {
											show : true,
											position : 'center',
											textStyle : {
												fontSize : '30',
												fontWeight : 'bold'
											}
										}
									}
								},
								data : dateValue
							} ]
						};
						myChartTop.setOption(options);
					} else {
						//$("#DeviceDiagramTab").append('<tr>无数据sadfa</tr>');
					}

				});
	}
	//应用系统服务器柱状图
	function AppSysVirtual() {
		$.post(ctx + '/request/base/findAppSysVirtualServer.action', {},
				function(result) {
					var Xvalue = [];//得到X轴数值
					var Yvalue = [];//得到Y轴数值
					if (result.length > 0) {
						for ( var i = 0; i < result.length; i++) {
							Xvalue[i] = result[i].appCName;
							Yvalue[i] = result[i].deviceNums;
						}
						var myChartTopss = echarts.init(document
								.getElementById('topssss'));
						setEcharts();
						var optionsss = {
							title : {
								text : '应用系统服务器图'
							},
							tooltip : {
								show : true
							},
							legend : {
								x : 'right',
								data : [ '虚拟机数量' ]
							},
							toolbox : {
								show : true,
								orient : 'vertical',
								y : 'center',
								feature : {
									magicType : {
										show : true,
										type : [ 'line', 'bar' ]
									},
								}
							},
							//color:['#00448a','#0580b9','#28c6b9','#84e6f1','#dddddd'],
							xAxis : [ {
								type : 'category',
								data : Xvalue
							} ],
							yAxis : [ {
								type : 'value'
							} ],
							series : [ {
								"name" : "虚拟机数量",
								"type" : "bar",
								"data" : Yvalue
							} ]
						};
						myChartTopss.setOption(optionsss);
					} else {
						//$("#NewestWaitDealTab").append('<tr>无数据sadfa</tr>');
					}

				});
	}
	//本月实施完成的申请   
	function AppSysComplete() {
		$.post(ctx + '/request/base/findAppSysCompleteRequest.action', {},
				function(result) {
					var Xvalue = [];//得到X轴数值
					var Yvalue = [];//得到Y轴数值
					if (result.length > 0) {
						for ( var i = 0; i < result.length; i++) {
							Xvalue[i] = result[i].appCName;
							Yvalue[i] = result[i].requestNums;
						}
						var myChart = echarts.init(document
								.getElementById('mainss'));
						setEcharts();
						var option = {
							title : {
								text : '本月实施完成的申请'
							},
							tooltip : {
								show : true
							},
							legend : {
								x : 'right',
								data : [ '申请数量' ]
							},
							toolbox : {
								show : true,
								orient : 'vertical',
								y : 'center',
								feature : {
									magicType : {
										show : true,
										type : [ 'line', 'bar' ]
									},
								}
							},
							// color:['#00448a','#0580b9','#28c6b9','#84e6f1','#dddddd'],
							xAxis : [ {
								type : 'category',
								data : Xvalue
							} ],
							yAxis : [ {
								type : 'value'
							} ],
							series : [ {
								"name" : "申请数量",
								"type" : "bar",
								"data" : Yvalue
							} ]
						};
						myChart.setOption(option);
					} else {
						//$("#NewestWaitDealTab").append('<tr>无数据sadfa</tr>');
					}

				});
	}
	//计算资源使用情况--- 物理机数、虚拟机数、物理机cpu数、虚拟机cpu数
	function getPoolHostAndVm() {
		$.post(ctx + '/resmgt-common/device/selectResPoolHostVmInfo.action',
				{}, function(result) {
					if (result.length > 0) {
						var Xvalue = [];
						var vmemArray = [];
						var hmemArray = [];
						var vcpuArray = [];
						var hcpuArray = [];
						for ( var i = 0; i < result.length; i++) {
							Xvalue[i] = result[i].poolName;
							vmemArray[i] = result[i].vmem;
							hmemArray[i] = result[i].hmem;
							vcpuArray[i] = result[i].vcpu;
							hcpuArray[i] = result[i].hcpu;
						}
						var myChartTops = echarts.init(document
								.getElementById('topsss'));
						setEcharts();
						var optionss = {
							title : {
								text : '计算资源使用情况'
							},
							tooltip : {
								trigger : 'axis',
								axisPointer : {
									type : 'shadow'
								}
							},
							legend : {
								x : 'right',
								data : [ '物理机数', '虚拟机数', '物理机cpu数', '虚拟机cpu数' ]
							},
							toolbox : {
								show : true,
								orient : 'vertical',
								y : 'center',
								feature : {
									magicType : {
										show : true,
										type : [ 'line', 'bar', 'stack',
												'tiled' ]
									},
								}
							},
							//color:['#00448a','#0580b9','#28c6b9','#84e6f1','#dddddd'],
							calculable : true,
							xAxis : [ {
								type : 'category',
								data : Xvalue
							} ],
							yAxis : [ {
								type : 'value',
								splitArea : {
									show : true
								}
							} ],
							grid : {
								x2 : 40
							},
							series : [ {
								name : '物理机数',
								type : 'bar',
								stack : '总量',
								data : hmemArray
							}, {
								name : '虚拟机数',
								type : 'bar',
								stack : '总量',
								data : vmemArray
							}, {
								name : '物理机cpu数',
								type : 'bar',
								stack : '总量',
								data : hcpuArray
							}, {
								name : '虚拟机cpu数',
								type : 'bar',
								stack : '总量',
								data : vcpuArray
							} ]
						};
						myChartTops.setOption(optionss);
					} else {
						//$("#DeviceDiagramTab").append('<tr>无数据</tr>');
					}

				});
	}

	$(function() {

		AppSysVirtual();
		AppSysComplete();
		getVmTypeNum();
		getPoolHostAndVm();
		NewestComplete();
		NewestCreate();
		NewestWaitDeal();
		DeviceDiagram();
		findRmNwCclass();
		//showLeftBanner();
		//showRightBanner();
	});
</script>
<style type="text/css">
html,body {
	height: 100%
}

#top .top_bg {
	width: 1347px;
}
</style>
</head>
<%@ include file="top.jsp"%>
<body>
	<div class="panel" style="width: 1347px;">
		<div id="topsss"
			style="height: 300px; width: 673px; float: left; border: #f0f0f0 1px solid;">

		</div>
		<div id="topss"
			style="height: 300px; width: 670px; float: right; border: #f0f0f0 1px solid;">

		</div>
		<div id="topssss"
			style="height: 300px; width: 673px; float: left; border: #f0f0f0 1px solid;">

		</div>
		<div id="mainss"
			style="height: 300px; width: 670px; float: right; border: #f0f0f0 1px solid;">

		</div>
	</div>
	<div class="summary" style="width: 1330px;">
		<div class="panel" style="width: 661px; float: left;">
			<h1>最新完成的申请</h1>
			<div style="height: 215px;">
				<table id="NewestCompleteTab" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<th>单号</th>
						<th>完成时间</th>
						<th>系统</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</table>
			</div>
		</div>

		<div class="panel" style="width: 660px; float: right;">
			<h1>我最新的服务申请</h1>
			<div style="height: 215px;">
				<table id="NewestCreateTab" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<th>单号</th>
						<th>申请时间</th>
						<th>系统</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</table>
			</div>
		</div>

		<div class="panel" style="width: 661px; float: left;">
			<h1>最新待我处理的申请</h1>
			<div style="height: 215px;">
				<table id="NewestWaitDealTab" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<th>单号</th>
						<th>申请时间</th>
						<th>系统</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</table>
			</div>
		</div>

		<div class="panel" style="width: 660px; float: right;">
			<h1>最新入库的设备</h1>
			<div style="height: 215px;">
				<table id="DeviceDiagramTab" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<th>时间</th>
						<th>类型</th>
						<th>SN</th>
						<th>所在资源池</th>
					</tr>
				</table>
			</div>
		</div>

		<div class="panel clear"
			style="width: 100%; margin-top: 10px; float: left;">
			<h1>IP地址使用情况</h1>
			<div>
				<table id="findRmNwCclassTab" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<th>数据中心</th>
						<th>已建B段名</th>
						<th>已用IP个数</th>
						<th>虚拟机占用IP个数</th>
						<th>物理机占用IP个数</th>
						<th>管理占用IP数</th>
						<th>生产占用IP数</th>
						<th>ILO占用IP数</th>
						<th>VMotion占用IP数</th>
						<th>私有占用IP数</th>
						<th>FSP1占用IP数</th>
						<th>FSP2占用IP数</th>
					</tr>
				</table>
			</div>
		</div>
	</div>

</body>
<%@ include file="foot.jsp"%>
</html>