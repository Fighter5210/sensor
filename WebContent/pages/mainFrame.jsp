<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<title>数据中心自动化运维管理平台</title>
<style>  
/* CSS Document
 * by zhcexo
 * date 2011-05-03
 */
*{margin:0;padding:0;}
html,body{overflow:hidden;}
body{font:16px/1.5 "Microsoft Yahei", sans-serif;}
ol,ul,li{list-style:none;}
a,a:visited{text-decoration:none;}
.clear{display:block;width:0;height:0;font-size:0;clear:both;visibility:hidden;background:none;border:none;float:none;overflow:none;}
#topnav{width:100%;height:62px;border-bottom:1px solid #4294D0;background:#E6F4FD;}
#topnav h1{height:62px;line-height:62px;float:left;display:inline;color:#2495E1;margin-left:20px;}
#main_nav{float:right;display:inline;font-size:12px;margin-top:20px;}
#main_nav li{float:left;}
#main_nav li a{display:block;padding:3px 8px;border:1px solid #C1D5ED;margin-right:10px;background:#EFF2F8;color:#555;}
#main_nav li a:hover{background:#3EA5DD;color:#fff;border-color:#3EA5DD;}
#menu{width:150px;margin:0 auto;margin-top:10px;}
#menu dl{margin-bottom:20px;}
#menu dl dt{padding-left:10px;background:#1D96CF;color:#fff;font-size:14px;line-height:2;}
#menu dl dd ul li a{display:block;width:118px;background:#f6fbff;border:1px solid #cadde4;border-top:none;font-size:12px;line-height:2;color:#364633;padding-left:30px;}
.item{margin:10px 20px;}
.item h3{font-size:14px;color:#fff;background:#1D96CF;line-height:2;padding-left:20px;}
.item .content{border:1px solid #cad9ea;border-top:none;padding:10px 30px;font-size:14px;line-height:2;}
#top{width:100%;height:45px;}
#left_menu,#right_body{float:left;display:inline;}
#mask{width:100%;height:100%;position:absolute;background:#000;opacity:.5}
#popupmenu{display:none;position:absolute;z-index:900;width:100%;height:100%;top:50px;}
.shwobox1{position:absolute;width:485px;background-color:#f5fafe;top:0px;right:3px;border-bottom:2px solid #c8d2d5;border-right:2px solid #c8d2d5;z-index:9;zoom:1;}
.shwobox1 .box{border:1px solid #4294ce;padding:10px 20px 40px;zoom:1;}
.shwobox1 .box dl{float:left;width:100px;padding-left:10px;padding-top:10px;}
.shwobox1 .box dl dt{font-size:16px;line-height:30px;font-weight:bold;height:30px;}
.shwobox1 .box dl dt a{color:#069;}
.shwobox1 .box dd{font-size:14px;line-height:1.8;}
.shwobox1 .box dd a{color:#333;}
.shwobox1 .box dd a:hover{color:#f00;}
.shwobox1 .box em{position:absolute;right:10px;bottom:10px;height:17px;width:17px;cursor:pointer;z-index:99;}
</style> 
  <script>
  window.location="<%=path%>/resmgt-storage/frame/storagePoolMgr.action";
  </script>
<script type="text/javascript">
  function openUrl(url,pName,cName){
     //window.frames['mainFrame'].document.getElementById('rightFrame').src = url;
	  document.getElementById('right').src = url;
  }
</script>
</head>
<body>
 <div id="popupmenu">
       <iframe allowtransparency="true" src="<%=path%>/pages/NewFile.html" scrolling="auto" width="100%" height="100%" frameborder="0"></iframe>
 </div>
 <div id="top" style="z-index:1">
     <iframe src="<%=path%>/pages/top.jsp" scrolling="no" width="100%" frameborder="0"></iframe>
 </div>
 <div id="center" style="z-index:1">
     <iframe id="left_menu" src="<%=path%>/sys/menu/showMenu.action" scrolling="no" width="180" height="100%" frameborder="0"></iframe>
     <iframe id="right" src="" scrolling="auto" frameborder="0"></iframe>     
 </div>
 <div id="bottom" style="z-index: 1;width:100%">
	  <iframe id="foot" scrolling="no" frameborder="0"  noresize  src="<%=path%>/pages/foot.jsp"style="width:100%"></iframe>
 </div>
 <script type="text/javascript">
 function autoSize(){
		var maxWidth = Math.max(
			document.documentElement["clientWidth"],
			document.body["scrollWidth"], document.documentElement["scrollWidth"],
			document.body["offsetWidth"], document.documentElement["offsetWidth"]
		);
		document.getElementById("left_menu").style.height = ( document.documentElement["clientHeight"] - 73 ) +'px';
		document.getElementById("right").style.width = ( maxWidth - 180 ) +'px';
		document.getElementById("right").style.height = ( document.documentElement["clientHeight"] - 73 ) +'px';
	}
function showPop(){
	  document.getElementById("popupmenu").style.display = 'block';
}
function closePop(){
	  document.getElementById("popupmenu").style.display = 'none';
}
autoSize();
window.onresize = autoSize; 
 </script>
 </body>
</html>
