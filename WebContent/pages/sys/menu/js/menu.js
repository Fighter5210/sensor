/** 动态显示菜单项 */
var menuID = "";
function showMenu(pId, id) {
	var menuId = "";
	if (id == '0') {// 如果当前选中菜单ID为0，则需删除二级菜单secondMenuId缓存
		delCookie("secondMenuId");
	}
	var menuPId = getCookie("menuPId");
	if (typeof (id) != "undefined" && id != null && id != "") {
		menuId = id;
	} else if (typeof (menuPId) != "undefined" && menuPId != null
			&& menuPId != "") {
		menuId = menuPId;
	} else {
		menuId = "0";
	}
	var url = ctx + "/sys/menu/showMenusByParentId.action?parentId=" + menuId
			+ "";
	$.ajax({
		async : true,
		cache : false,
		type : 'POST',
		dataType : 'json',
		url : url,// 请求的action路径
		error : function() {// 请求失败处理函数
			showTip("请求失败", null, "red");
		},
		success : function(data) { // 请求成功后处理函数。
			createMenuHtml(data, menuId);
		}
	});
	if (menuId != 0) {// 如果menuId != 0，则被选中的菜单为一级菜单，需设置firstMenuId
		// 缓存，此时的menuId为当前选中菜单ID
		setCookie("firstMenuId", menuId);
	}
	setCookie("menuPId", menuId);// 设置上一级菜单
}
/** 生成菜单html */
function createMenuHtml(data, menuId) {
	$("#menu").html("");
	var id = (menuId == '0' || typeof (menuId) == "undefined" || menuId == null || menuId == "") ? getCookie("firstMenuId")
			: getCookie("secondMenuId");
	var htmlStr = "<span class=\"MT-arrow\"></span>";
	$.each(data, function(i, menuPo) {
		var secondMenuId = "";
		if (menuId == 0
				&& (typeof (menuPo.menuUrl) != "undefined"
						&& menuPo.menuUrl != null && menuPo.menuUrl != "")) {
			secondMenuId = getCookie("secondMenuId");
		}
		if (menuPo.id == id || (menuId == 0 && menuPo.id == secondMenuId)) {
			htmlStr = htmlStr + "<p class=\"nav_p\" onclick=\"showMenuOrPage('"
					+ menuPo.parentId + "','" + menuPo.id + "','"
					+ menuPo.menuUrl + "')\"><img  class=\"nav_img\" src="
					+ ctx + menuPo.imageUrl + " />"
					+ "<label class=\"nav_label_current\">" + menuPo.menuName
					+ "</label></p>";
		} else {
			htmlStr = htmlStr + "<p class=\"nav_p\" onclick=\"showMenuOrPage('"
					+ menuPo.parentId + "','" + menuPo.id + "','"
					+ menuPo.menuUrl + "')\"><img  class=\"nav_img\" src="
					+ ctx + menuPo.imageUrl + " />"
					+ "<label class=\"nav_label\">" + menuPo.menuName
					+ "</label></p>";
		}
	});
	if (menuId != 0) {
		var htmlStr = htmlStr
				+ "<p class=\"nav_p\" onclick=\"showMenuOrPage('0','0','')\"><img  class=\"nav_img\" src=\""
				+ ctx + "/images/sysmenu/goback2.png\" />"
				+ "<label class=\"nav_label\">返回</label></p></p>";
	}
	document.getElementById("menu").innerHTML = htmlStr;
	var menuNumber = data.length;
	var ben = Math.ceil(menuNumber / 4);
	var jio = menuNumber % 2;

	if (jio == 0 && menuNumber != 2 && menuId != 0) {
		ben = ben + 1;
	}

	// alert("倍数：" + ben + " 菜单数量：" + menuNumber);
	$("#menu").height(130 * ben);
}
/** 点击调用此方法进入二级菜单或页面 */
function showMenuOrPage(parentId, id, menuUrl) {
	if (parentId != 0
			|| (typeof (menuUrl) != "undefined" && menuUrl != null && menuUrl != "")) {
		if (parentId == 0) {// 如果点击的一级菜单中有类似“工作台”这种直接进页面的功能，也要设置当前选中一级菜单ID
			// firstMenuId缓存
			setCookie("firstMenuId", id);
		} else { // 否则点击的是二级菜单中的功能，设置当前选中二级菜单ID secondMenuId 缓存
			setCookie("secondMenuId", id);
		}
		window.location.href = ctx + menuUrl;
	} else {
		showMenu(parentId, id);
		menuID = "1";
	}
}
/** 设置被点击菜单缓存 */
function setCookie(name, value) {
	var exp = new Date();
	exp.setTime(exp.getTime() + 60000 * 10);
	document.cookie = name + "=" + escape(value) + ";expires="
			+ exp.toGMTString() + ";path=/";
}

/** 读取被点击菜单缓存 */
function getCookie(name) {
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");

	if (arr = document.cookie.match(reg))

		return (arr[2]);
	else
		return null;
}
/** 删除被点击菜单缓存 */
function delCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 10000);
	var cval = getCookie(name);
	if (cval != null)
		document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString()
				+ ";path=/";
}
/** 关闭浏览器时清空缓存 */
window.onbeforeunload = function() {
	if (event.clientY < 0 || event.altKey) {
		delCookie("firstMenuId");
		delCookie("secondMenuId");
		delCookie("menuPId");
	}
};