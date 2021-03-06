<%@ page language="java" pageEncoding="UTF-8"%>
<!--选定房间弹出框-->
<div id="selectRoomsAlert" class="alertDiv moveBar2 alertDiv2 checkRoomDiv" style="z-index:22">
	<div class="alertMain greyBg" style="width:500px;margin-top:150px;">
    	<h4 class="moveDivAlert2">选定房间明细表<a href="javascript:closeDiv('selectRoomsAlert');" class="closeDiv2 closeAlert"></a></h4>
        <div class="borderSolid">
        	<!--table-->
                <div class="widthB100">
                	<!--table-->
                    <div class="tableDiv floatL margin-left-10 widthB97 margin-top-10">
                        <!--table title-->
                        <table class="tableMain">
                            <thead> 
                                <tr>
                                    <td width="15%">房号</td>
                                    <td width="15%">建筑</td>
                                    <td width="15%">楼层</td>
                                    <td width="20%">房号</td>
                                    <td>房型</td>
                                </tr>
                            </thead>
                        </table>
                        <!--table title end -->
                        <!--table enner-->
                        <div class="tableHeiScll height300" style="overflow-y:auto;">
                            <table id="selectRoomsTable" class="tableMain">
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <!--table enner -END- -->
                    </div>
                    <!--/table-->
                    <span class="margin-left-10" href="javascript:;">房间数量：<span id="selectRoomsNum_span">0</span></span>
                    <div class="clearBoth">
                    	<a class="button_02 floatR margin-left-5" href="javascript:closeDiv('selectRoomsAlert');">关&nbsp;&nbsp;闭</a>
                    </div>
                </div>
            <div class="clearBoth"></div>
        </div>
    </div>
</div>
<!--/选定房间弹出框-->