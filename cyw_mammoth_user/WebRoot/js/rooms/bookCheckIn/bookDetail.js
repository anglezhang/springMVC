//关闭订单详情页面
function closeBookDetail(){
	$(".alertDivBg").fadeOut();
	$("#bookDetail").fadeOut();
}
//订单详情页面--打开其他信息
function otherInfoDetail(){
	$("#otherInfoDiv").fadeIn();
	$(".alertDivBg2").fadeIn();
}
//订单详情页面--关闭其他信息
function closeOtherInfoDiv(){
	$("#otherInfoDiv").fadeOut();
	$(".alertDivBg2").fadeOut();
}