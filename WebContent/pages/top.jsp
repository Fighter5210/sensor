<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String _path = request.getContextPath();
%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<script type="text/javascript" src="${ctx}/pages/sys/menu/js/menu.js"></script>
<link rel="stylesheet" href="${ctx}/css/top.css" type="text/css"></link>
<script type="text/javascript">
	$(function() {
		setInterval(function() {
			$("#server_time_span").text("系统时间:" + new Date().toLocaleString());
		}, 1000);
		/*显示菜单*/
		showMenu();
		/*绑定鼠标到达|离开事件*/
		$("#sysName").mouseover(function() {
			menuID = "";
			$('#menu').show('fast');
		});
		$("#menu,#sysName").mouseleave(function() {
			if (menuID == "1") {
				$('#menu').show();
			} else {
				$("#menu").hide();
			}
		});

		$("#animate_li").mouseover(function() {
			if ($("#user_img").height() == 0) {
				$("#user_img").animate({
					"height" : "16px"
				}, function() {
					$("#div_right").slideDown('fast');
				});
			}
		});

		$("#animate_li").mouseleave(function() {
			if ($("#user_img").height() == 16) {
				$("#div_right").slideUp('fast', function() {
					$("#user_img").animate({
						"height" : "0px"
					});
				});
			}
		});
	});
</script>
<body id="top">

	<div class="top_bg">

		<div class="logo">
			<img onclick="javascript:window.location='<%=_path%>/flex/Main.html'"
				src="<%=_path%>/images/company_logo_w.png"
				style="cursor: pointer; margin-top: 7px; float: left; margin-right: 5px; width: 32px; height: 32px;" />

			<div id="a"></div>
		</div>
		<div id="MT_nav">
			<ul>
				<li id="sysName" class="MT-item"><a
					onclick="javascript:window.location='<%=_path%>/flex/Main.html'"
					style="cursor: pointer;">传感器产品数据管理平台</a><img
					src="<%=_path%>/images/down_arrow .png"
					style="margin-top: -26px; float: right; margin-right: 20px; width: 12px; height: 12px;" />
					<div
						style="padding: 10px 10px 20px 10px; opacity: 0.9; display: none; height: auto;"
						id="menu"></div></li>
			</ul>
		</div>
		<div class="top_line">
			<li class="light-blue"><img src="<%=_path%>/images/time.png"
				alt="系统时间"> <span class="btn-info" id="server_time_span">
					<small></small>
			</span></li>
			<li class="light-purple" id="animate_li"
				style="border-right: 1px solid #E1E1E1; margin-right: 10px; cursor: pointer;">
				<img src="<%=_path%>/images/user_w.png" id="user_img"
				style="height: 0px;"> <span class="btn-info">
					<div id="MT_nav1">
						<ul>

							<li class="MT-item"><shiro:user>
									<a style="text-decoration: none; font-size: 14px;"><shiro:principal />，您好！</a>
								</shiro:user>
								<div
									style="z-index: 999; padding: 10px 10px 20px 10px; opacity: 0.9;"
									id="div_right">
									<span class="MT-arrow"></span>
									<!-- <a href="#" hidefocus="true" style="text-align: center;">修改密码</a>-->
									<a href="<%=_path%>/shiro/logout.action" hidefocus="true"
										style="text-align: center; background-image: url('../../images/quit2.png'); background-repeat: no-repeat; background-position: 20px center;">退出系统</a>
								</div></li>
						</ul>
					</div>
			</span>
			</li>
		</div>
	</div>