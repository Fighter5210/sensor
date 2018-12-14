<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/public.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=path%>/common/css/newTop.css"
	type="text/css"></link>
<script src="js/jquery-1.8.2.min.js"></script>
<title>系统主页</title>
<script>
	// 格式化日期
	function formatDate(ns) {
		if (ns) {
			var d = new Date(parseInt(ns.time + ""));
			var year = d.getFullYear();
			var month = d.getMonth() + 1;
			if (month <= 9) {
				month = "0" + month;
			}
			var date = d.getDate();
			if (date <= 9) {
				date = "0" + date;
			}
			return year + "-" + month + "-" + date;
		}
	}
	
</script>
</head>
<body>
	<div id="nav"
		style="height: 22px; padding-top: 10px; font-family: 'Tahoma', 'MS Serif', 'Microsoft YaHei'">
		<i class="home"></i> <span>当前位置:</span><span class="active">我的主页</span>
	</div>
	<%--<div class="conWrap">
		<div class="item">
			<div class="quickKey left">
				<h2 class="mainBt">快捷操作</h2>
				<ul class="keyBox">
					<li>
						<p class="tu">
							<a href="<%=path%>/maintenance/planIndex.action"><img src="images/mainIcon1.jpg" width="66"
								height="70" /></a>
						</p>
						<p>
							<a href="<%=path%>/maintenance/planIndex.action">例行计划</a>
						</p>
					</li>
					<li>
						<p class="tu">
							<a href="<%=path%>/softwareMaintenance/history_softwareInstallHistory.action"><img src="images/mainIcon2.jpg" width="66"
								height="70" /></a>
						</p>
						<p>
							<a href="<%=path%>/softwareMaintenance/history_softwareInstallHistory.action">软件任务</a>
						</p>
					</li>
					<li>
						<p class="tu">
							<a href="<%=path%>/check/task/init.action"><img src="images/mainIcon3.jpg" width="66"
								height="70" /></a>
						</p>
						<p>
							<a href="<%=path%>/check/task/init.action">巡检任务</a>
						</p>
					</li>
					<li>
						<p class="tu">
							<a href="<%=path%>/gatherlog/gatherLogListView.action"><img src="images/mainIcon4.jpg" width="66"
								height="70" /></a>
						</p>
						<p>
							<a href="<%=path%>/gatherlog/gatherLogListView.action">日志采集</a>
						</p>
					</li>
					<li>
						<p class="tu">
							<a href="<%=path%>/system/gatherdevicemgt/gatherDeviceListView.action"><img src="images/mainIcon5.jpg" width="66"
								height="70" /></a>
						</p>
						<p>
							<a href="<%=path%>/system/gatherdevicemgt/gatherDeviceListView.action">主机采集</a>
						</p>
					</li>

				</ul>
			</div>
			<div class="quickNew right" title="系统公告">
				<ul class="newBox" id="">
				<marquee id="noticeUl" onMouseOut="this.start()" onMouseOver="this.stop()" scrollAmount="2" direction="up" height="80">
				</marquee>
				</ul>
			</div>
		</div>
		<div class="item">
			<div class="numberTu left">
				<h2 class="mainBt">运维统计</h2>
				<div class="photo">
					<!-- <img src="images/tu1.jpg" width="481" height="234" /> -->
					<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="481" height="234" id="ReportColumnMain">
                <param name="movie" value="./flex/ReportColumnMain.swf" />
                <param name="quality" value="high" />
                <param name="bgcolor" value="#ffffff" />
                <param name="allowScriptAccess" value="sameDomain" />
                <param name="allowFullScreen" value="true" />
                <!--[if !IE]>-->
                <object type="application/x-shockwave-flash" data="./flex/ReportColumnMain.swf" width="481" height="234">
                    <param name="quality" value="high" />
                    <param name="bgcolor" value="#ffffff" />
                    <param name="allowScriptAccess" value="sameDomain" />
                    <param name="allowFullScreen" value="true" />
                <!--<![endif]-->
                <!--[if gte IE 6]>-->
                    <p> 
                        Either scripts and active content are not permitted to run or Adobe Flash Player version
                        11.1.0 or greater is not installed.
                    </p>
                <!--<![endif]-->
                    <a href="http://www.adobe.com/go/getflashplayer">
                        <img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash Player" />
                    </a>
                <!--[if !IE]>-->
                </object>
                <!--<![endif]-->
            </object>
				</div>
			</div>
			<div class="numberTu right">
				<h2 class="mainBt">系统统计</h2>
				<div class="photo">
					<!-- <img src="images/tu2.jpg" width="481" height="234" /> -->
					<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="481" height="234" id="ReportColumnMain">
                <param name="movie" value="./flex/ReportPieMain.swf" />
                <param name="quality" value="high" />
                <param name="bgcolor" value="#ffffff" />
                <param name="allowScriptAccess" value="sameDomain" />
                <param name="allowFullScreen" value="true" />
                <!--[if !IE]>-->
                <object type="application/x-shockwave-flash" data="./flex/ReportPieMain.swf" width="481" height="234">
                    <param name="quality" value="high" />
                    <param name="bgcolor" value="#ffffff" />
                    <param name="allowScriptAccess" value="sameDomain" />
                    <param name="allowFullScreen" value="true" />
                <!--<![endif]-->
                <!--[if gte IE 6]>-->
                    <p> 
                        Either scripts and active content are not permitted to run or Adobe Flash Player version
                        11.1.0 or greater is not installed.
                    </p>
                <!--<![endif]-->
                    <a href="http://www.adobe.com/go/getflashplayer">
                        <img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash Player" />
                    </a>
                <!--[if !IE]>-->
                </object>
                <!--<![endif]-->
            </object>
				</div>
			</div>
		</div>
		<div class="item">
			<div class="newWrap1">
				<h2 class="mainBt">
					<span class="newBt">例行待办</span>
				</h2>
				<div class="newText">
					<ul class="newIcon">
						<li class="newIcon1"><a
							href="<%=path%>/maintenance/planIndex.action">维护计划</a></li>
						<li class="newIcon2"><a
							href="<%=path%>/maintenance/taskIndex.action">维护任务</a></li>
						<li class="newIcon3"><a
							href="<%=path%>/pages/maintenance/executeResultShow.jsp">维护历史</a></li>
						<li class="newIcon4"><a
							href="<%=path%>/maintenance/templateIndex.action">模板管理</a></li>
					</ul>
					<ul class="newList" id="daiban">
					</ul>
					<div class="newMore" style="padding: 10px 0;">
						<a href="<%=path%>/maintenance/planIndex.action">更多消息</a>
					</div>
				</div>
			</div>
			<div class="newWrap2">
				<h2 class="mainBt">
					<span class="newBt">巡检待办</span>
				</h2>
				<div class="newText">
					<ul class="newList" id="xunjian">
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的好的最好的务</a><a
							href="#" class="aMore">立即查看</a></li>
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的成本本提高最好的务</a><a
							href="#" class="aMore">立即查看</a></li>
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的提高的最好的最好的务</a><a
							href="#" class="aMore">立即查看</a></li>
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的提高最好的最好的务</a><a
							href="#" class="aMore">立即查看</a></li>
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的提高最好的最好的务</a><a
							href="#" class="aMore">立即查看</a></li>
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的提高最好的最好的务</a><a
							href="#" class="aMore">立即查看</a></li>
					</ul>
					<div class="newMore" style="padding: 10px 0;">
						<a href="<%=path%>/check/task/init.action">更多消息</a>
					</div>
				</div>
			</div>
			<div class="newWrap3" style="margin: 0; float: right">
				<h2 class="mainBt">
					<span class="newBt">软件维护</span>
				</h2>
				<div class="newText">
					<ul class="newList" id="software">
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的好的最好的务</a></li>
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的成本提成本提高最好的务</a></li>
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的提高的成本提最好的务</a></li>
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的成本的成本提高好的务</a></li>
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的提最好的最好的务</a></li>
						<li><span class="newDat">2101-12-02</span><a href="#">以极低的提高的成本提最好的务</a></li>
					</ul>
					<div class="newMore" style="padding: 10px 0;">
						<a
							href="<%=path%>/softwareMaintenance/history_softwareInstallHistory.action">更多消息</a>
					</div>
				</div>
			</div>
		</div>
	</div>--%>
</body>
</html>