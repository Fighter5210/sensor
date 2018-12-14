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
	src="${ctx}/pages/product/js/addProduct.js"></script>
<style type="text/css">
.pageFormContent p {
	width: 355px;
}
a:visited {color: blue} 
</style>

<script>  
$(document).ready(function() {  
  
var MaxInputs       = 255; //maximum input boxes allowed  
var InputsWrapper   = $("#InputsWrapper"); //Input boxes wrapper ID  
var AddButton       = $("#AddMoreFileBox"); //Add button ID  
  
var x = InputsWrapper.length; //initlal text box count  
var fieldCount=1; //to keep track of text box added  
var fieldCountStr;  
$(AddButton).click(function (e)  //on add input button click  
{  
        document.documentElement.scrollTop=document.body.clientHeight; 
        document.body.scrollTop=document.body.clientHeight; 
        if(parseInt(x) < parseInt(MaxInputs)) //max input box allowed  
        {  
            fieldCount++; //text box added increment  
            if(fieldCount<10){
               fieldCountStr = '00'+fieldCount;
            }else if(fieldCount<100){
               fieldCountStr = '0'+fieldCount;
            }else{
               fieldCountStr = fieldCount;
            }
            
            //add input box  
            $(InputsWrapper).append('<p><input id=addressCode type=hidden name=addressCode value='+fieldCountStr+'><i>'+fieldCountStr+'</i><input class="textInput" type="text" name="barCode" id="field_'+ fieldCountStr +'" maxlength="15" value=""/>&nbsp;&nbsp;<a href="#" class=\"removeClass\">×</a></p>');  
            x++; //text box increment  
        }  
return false;  
});  
  
$("body").on("click",".removeClass", function(e){ //user click on remove text  
       var value = $(this).parent('p').children('input')[0].value;
        if( parseInt(x) > 1 && value == fieldCount ) {  
                $(this).parent('p').remove(); //remove text box 
                fieldCount--; 
                x--; //decrement textbox  
        }else{
        	alert("只能从最后一个开始删除");
        }
        return false;  
})   
  
});  
</script>
</head>

<body class="main1">
	<div class="content-main clear" id="scrollDiv">
		<div id="nav">
			<i class="home"></i> <span>当前位置:</span><span class="active">产品管理-&gt;添加产品</span>
		</div>
		<div class="pageFormContent3">
		<form action="${ctx}/product/addProduct.action" method="post" id="add_update_Product_Form">
			<div id="updateTab" class="pageFormContent">
				<p>
					<i><font color="red">*</font>日期:</i> <input class="textInput" readonly
						type="text" id="pDate" name="productPo.pDate"  onfocus="WdatePicker({skin:'whyGreen',minDate: '1900-01-01', maxDate: '2090-12-30' })"/>
				</p>
				<p>
					<i><font color="red">*</font>批次:</i> <input class="textInput"
						type="text" id="pBatch" name="productPo.pBatch" maxlength="10"/>
				</p>
				<p>
					<i><font color="red">*</font>数量:</i> <input class="textInput"
						type="text" id="pNumber" name="productPo.pNumber" maxlength="3"/>
				</p>
				<p>
					产品地址码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;条形码
				</p>
			    <p id="InputsWrapper">
			      <input id=addressCode type=hidden name=addressCode value=001><i>001</i><input class="textInput" type="text" name="barCode" id="field_001" maxlength="15" value=""/>&nbsp;&nbsp;<a href="#" class="removeClass" title="删除">×</a>
			    </p>  
			    <p>
					<i></i><a href="#" id="AddMoreFileBox"  style="margin-left:180px;">添加</a>
				</p>
			</div>
			<div class="winbtnbar clear" style="text-align: center;">
			<input type="submit" class="btn_ok" id="addProduct" name="addProduct"
				title="保存" style="margin-top: 0px;"
				onclick="return formValidate()"> 
				<!--  input type="button"
				class="btn_cancel" id="cencelProduct" name="cencelProduct" title="取消"
				style="margin-right: 5px;"
				onclick="javascript:history.go(-1);">-->
		    </div>
		</form>
		
		</div>
	</div>
</body>
</html>
