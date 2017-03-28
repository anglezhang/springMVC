<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../include/taglib.jsp" %>
<div class="secondMenuDiv">
	<ul class="secondMenu">
        <li><a url="/rooms.do" <mammoth:AuthJudge funcId="c_513915">href="${pageContext.request.contextPath}/rooms.do"</mammoth:AuthJudge> >房态图</a></li>
        <li><a url="/bookCheckIn/list.do" <mammoth:AuthJudge funcId="c_548632" >href="${pageContext.request.contextPath}/bookCheckIn/list.do"</mammoth:AuthJudge>  >预定入住</a></li>
        <li><a url="/rooms/groupToChange.do" <mammoth:AuthJudge funcId="c_612231" >href="${pageContext.request.contextPath}/rooms/groupToChange.do"</mammoth:AuthJudge> >散团互转</a></li>
        <li><a url="/rooms/wardtolive.do" <mammoth:AuthJudge funcId="c_659449">href="${pageContext.request.contextPath}/rooms/wardtolive.do"</mammoth:AuthJudge> >换房/续住</a></li>
        <li><a url="/roomsManager/manager.do" <mammoth:AuthJudge funcId="c_874502">href="${pageContext.request.contextPath}/roomsManager/manager.do"</mammoth:AuthJudge> >房态管理</a></li>
        <li><a url="/roomFixFrozen/fixFrozen.do" <mammoth:AuthJudge funcId="c_942392" >href="${pageContext.request.contextPath}/roomFixFrozen/fixFrozen.do"</mammoth:AuthJudge>  >维修/冻结</a></li>
        <li><a  <mammoth:AuthJudge funcId="c_480792" >href="javascript:;"</mammoth:AuthJudge> >网房设置</a></li>
        <li><a   <mammoth:AuthJudge funcId="c_763986">id="changeWork"  href="javascript:;"</mammoth:AuthJudge> >换班次</a></li>
    </ul>
</div>

<div class="alertDiv moveBar2 alertDiv2 changeWorkPup">
    <div class="alertMain greyBg" style="width:365px; margin-top:250px;">
        <h4 class="moveDivAlert2">换班次<a id="hbClose" href="javascript:;" class="closeDiv2 closeAlert"></a></h4>
        <!-- --> <iframe id="iframepage" name="banciFrame" src=""   style="margin: 0 8px 8px; width: 350px;height:170px; border: 0;box-shadow: 1px 0 15px 2px #464646;"></iframe>
    </div>
</div>
<!--   换班次 End   -->
<script type="text/javascript">
	/**
	*@描述 设置当前选中状态
	*@param index 当前菜单下标
	*/
	function setSecondMenuStat(index)
	{
		index = index -1;
		$("ul.secondMenu > li:eq(" + index + ")").find("a").addClass('thisSecMenu');
	}
	
	
	/**
	*@描述 换班次弹层显示隐藏
	*/
	$("#changeWork").on("click",function(){
	    $(".changeWorkPup , .alertDivBg2").fadeIn();
	    $("#iframepage").prop("src" , "/syssetting/showChangeWorkPup.do") ;
	});
	$(".closeAlert").on("click",function(){
	    $(this).parents(".alertDiv").fadeOut();
	    $(".alertDivBg2").fadeOut();
	});
	function closeChangeWorkPup(){
	    $(".alertDiv").fadeOut();
	    $(".alertDivBg2").fadeOut();
	}
	function changeWorkPup(shiftId){
	  $.ajax({
				url:"/syssetting/doChangeWorkPup.do",
				type:"post",
				dataType:'json',
				data : {shiftId:shiftId},
				mimeType : "multipart/form-data",
				cache : false,
				success:function(data, textStatus, jqXHR){
					if(data.success){
					 closeChangeWorkPup();
					}else{
					   altWaringMsg(data.msg);
					}
	
				}
			});
}
</script>
