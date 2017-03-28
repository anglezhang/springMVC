/**
 * 中文回车事件
 * @param e
 */
function namePress(e)
{
	var keynum;
	if (window.event)
	{ // IE
		keynum = e.keyCode;
	}else if (e.which){ // Netscape/Firefox/Opera
		keynum = e.which;
	}
	var checked = document.getElementById("guestinfo_if_fgst").checked;
	if(keynum==13 && checked)
	{
	}
}
/**
 * 根据身份证号获取生日
 * @param iIdNo
 */
function getBirthdatByIdNo(iIdNo) {
	if ($("#crdkindId").val() != '006001')
		return;
	var tmpStr = "";
	var idDate = "";
	var tmpInt = 0;
	var strReturn = "";

	iIdNo = $.trim(iIdNo);
	if ((iIdNo.length != 15) && (iIdNo.length != 18)) {
		strReturn = "输入的身份证号位数错误";
		altEerrMsg(strReturn);
		return;
	}
	if (iIdNo.length == 15) {
		tmpStr = iIdNo.substring(6, 12);
		tmpStr = "19" + tmpStr;
		tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-"
				+ tmpStr.substring(6);

	} else {
		tmpStr = iIdNo.substring(6, 14);
		tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-"
				+ tmpStr.substring(6);
	}
	$("#guestinfo_birthday").val(tmpStr);
}