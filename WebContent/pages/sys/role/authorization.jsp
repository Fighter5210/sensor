<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div id="roleAuthorizationDiv" class="div_center" style="display: none;">
		<form action="" method="post" id="authorizationForm">
		<input type="hidden" id="roleAuthorId" name="roleAuthorId"  />
		<input type="hidden" id="roleMenus" name="roleMenus" />
        <table class="pagelist">
       		 <tr>
	       		 <td>
	       		 	<div id="treeDiv"
					class="leftTree" style="max-height: 300px;height:300px;max-width:330px;width:330px;">
					<ul id="menuTree" class="ztree"></ul>
					</div>
				 </td>
			 </tr>
		</table>
		<p class="btnbar"><input type="button"  class="btn_ok" onclick="saveAuthorization()" style="margin-right: 5px;" /><input type="button" class="btn_cancel" onclick="closeView('roleAuthorizationDiv')" style="margin-right: 5px;" /></p>
        </form>
		
</div>