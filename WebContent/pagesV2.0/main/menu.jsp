<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>左边导航</title>
<link href="css/public.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<script src="js/jquery-1.8.2.min.js"></script>


<script>

//加载菜单数据;
$(function () {
    var url = "<%=path%>/sys/menu/selSystemMenu.do";
    $.ajax({
        async: true,
        cache: false,
        type: 'POST',
        dataType: 'json',
        url: url,
        error: function () {
        	alert("请求失败", null, "red");
        },
        success: function (data) {
            createMenu(data);
        }
    });
});

//循环所有二级菜单
function goto(obj,url){
	$(".menu2 > li").each(function(){
		$(this).attr("class","");
	});
	$(obj).attr("class","menu2Active"); 
	parent.frames["main"].location.href = url;
}

//隐藏所有二级菜单
function hideChildMenu(){
	
	$("span ~ ul").each(function(){
		$(this).hide();
	});
	//给每个一级菜单添加事件
	$("li > span").each(function(i){
		$(this).click(function(){
			$("span ~ ul").each(function(i,e){
				$(e).hide();
				$(e).prev().css("color","#3e3e3e");
			});
			var obj = $(this).next()[0];
			if($(obj).is(':hidden')){
				$(obj).show();
				$(this).css("color","#005584");
			}else{
				$(obj).hide();
				$(this).css("color","#3e3e3e");
			}
		});
	});
}

/**
 * 创建左边导航菜单;
 */
function createMenu(data){
	 var htmlStr = "<ul class=\"menu1\" id=\"autoMenu\">";
	 $.each(data, function(i, menuPo) {
	     htmlStr=htmlStr+"<li><span>"+menuPo.menuName+"</span>";
		 htmlStr = htmlStr + "<ul class=\"menu2\">";
		 $.each(menuPo.childMenu, function(k, childMenu) {
			htmlStr = htmlStr + "<li onclick=\"goto(this,'<%=path%>"+childMenu.menuUrl+"')\">"+childMenu.menuName+"</li>";
		 });
		 htmlStr = htmlStr+"</ul></li>";
	 });
	 htmlStr = htmlStr+"</ul>";
	 document.getElementById("autoMenu").innerHTML = htmlStr;
	 
	 //隐藏二级菜单;
	 hideChildMenu();
}

</script>

</head>

<body>
	<div id="side">
		<ul class="menu1" id="autoMenu">
			
		</ul>
	</div>
</body>
</html>
