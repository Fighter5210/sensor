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
<title>云管理平台</title>
<style>  
	html,body
	{
			margin-left:0;
			margin-top:0;
	}   
</style> 
<script type="text/javascript">



/** 设置导航条内容 */
   function setNavName(pName,cName,url){
      var navFrame = window.frames['navFrame'];
      navFrame.document.getElementById('pName').innerHTML=pName;
      navFrame.document.getElementById('cName').innerHTML=cName;
      //navFrame.document.getElementById('cName').href=url;
      navFrame.document.getElementById('pem').innerHTML='>';
      navFrame.document.getElementById('cem').innerHTML='>';
   }
/** 补充第二个frame背景颜色 */
function setColor(c){
  c.document.write("<body bgcolor='#393945' ></body>");
  c.document.close();
}
</script>
</head>
<frameset rows="30,*" cols="*" frameborder="no" border="0" framespacing="0"  >
  <!-- <frame src="<%=path%>/pages/nav.htm" name="navFrame" scrolling="No" noresize="noresize" id="navFrame" />-->
  <frame id="rightFrame" name="rightFrame" scrolling="auto" noresize  src="rightMain.htm">
</frameset>
</html>
