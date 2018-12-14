var method = "";
$(function() {
});

function formValidate() {
	var pDate = $("#pDate").val();
	var pBatch = $("#pBatch").val();
	var pNumber = $("#pNumber").val();
	
	var reg = new RegExp("^[0-9]*$");  
	if (pDate == null || pDate == "") {
		showTip("日期不能为空!");
		return false;
	}
	if (pBatch == null || pBatch == "") {
		showTip("批次不能为空!");
		return false;
	}else if(!reg.test(pBatch)){
	    showTip("批次必须为非负整数！");
		return false;
	}
	
	if (pNumber == null || pNumber == "") {
		showTip("数量不能为空!");
		return false;
	}else if(!reg.test(pNumber)){
	    showTip("数量必须为非负整数！");
		return false;
	}else if(pNumber>255 || pNumber <0){
	    showTip("数量必须为0~255之间的非负整数！");
		return false;
	}
	
	var barCodes = document.getElementsByName("barCode");
	
	for(var i=0;i<barCodes.length;i++){
       
       var barCode = barCodes[i].value;
       
       if (barCode == null || barCode == "") {
		    showTip("条形码不能为空!");
		    return false;
		}else if(!reg.test(barCode)){
		    showTip("条形码必须为非负整数！");
			return false;
		}
		
		if(barCodes.length!=pNumber){
		    showTip("产品录入数量与设定数量不符！");
			return false;
		}
    }
	return true;
}

