<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>代码维护</title>
<body>
	<%@ include file="../../header.jsp"%>
	<!--secondMenu-->
	<%@ include file="../secondMenu.jsp"%>
	<!--secondMenu -end- -->
	<div class="center">
		<!--mainInformation-->
		<div class="mainInfo widthB99">
			<h4 class="fontWeight green margin-top-10">代码维护</h4>
			<div class="splitDivLeft margin-top-5 FJwebLeft" >
				<!--tree-->
				<div class="tree" style="margin: 8px 0px;">
					<div class="treeBody">
						<div class="bodyLiTitle">
							<img class="titleList" src="${ctx}/img/tree_01.png"> 
							<img class="titleImg" src="${ctx}/img/tree_title_img.png"> 
							<span class="titleName">特别代码</span>
						</div>
						<ul class="scdLi">
							<li id="se_tree_li_hconsume">
								<a class="treeLink" href="javascript:findList('se_tree_li_hconsume', 'SE','hconsume','消费点');"> 
									<img class="scdLiList" src="${ctx}/img/scd_list.png"> 
									<img class="scdLiImg" src="${ctx}/img/tree_main_li_img.png"> 
									<span class="scdLiName">消费点</span> 
								</a>
							</li>
							<li id="se_tree_li_hexchange">
								<a class="treeLink" href="javascript:findList('se_tree_li_hexchange', 'SE','hexchange','外汇汇率');"> 
									<img class="scdLiList" src="${ctx}/img/scd_list.png"> 
									<img class="scdLiImg" src="${ctx}/img/tree_main_li_img.png"> 
									<span class="scdLiName">外汇汇率</span> 
								</a>
							</li>
							<li id="se_tree_li_hfolk">
								<a class="treeLink" href="javascript:findList('se_tree_li_hfolk', 'SE','hfolk','民族');"> 
									<img class="scdLiList" src="${ctx}/img/scd_list.png"> 
									<img class="scdLiImg" src="${ctx}/img/tree_main_li_img.png"> 
									<span class="scdLiName">民族</span> 
								</a>
							</li>
							<li id="se_tree_li_hcountry">
								<a class="treeLink" href="javascript:findList('se_tree_li_hcountry', 'SE','hcountry','国籍');"> 
									<img class="scdLiList" src="${ctx}/img/scd_list.png"> 
									<img class="scdLiImg" src="${ctx}/img/tree_main_li_img.png"> 
									<span class="scdLiName">国籍</span>
								</a>
							</li>
							<li id="se_tree_li_hsetlKind">
								<a class="treeLink" href="javascript:findList('se_tree_li_hsetlKind', 'SE','hsetlKind','付款方式类别');"> 
									<img class="scdLiList" src="${ctx}/img/scd_list.png"> 
									<img class="scdLiImg" src="${ctx}/img/tree_main_li_img.png"> 
									<span class="scdLiName">付款方式类别</span> 
								</a>
							</li>
							<li id="se_tree_li_hsettle">
								<a class="treeLink" href="javascript:findList('se_tree_li_hsettle', 'SE','hsettle','付款方式');"> 
									<img class="scdLiList" src="${ctx}/img/scd_list.png"> 
									<img class="scdLiImg" src="${ctx}/img/tree_main_li_img.png"> 
									<span class="scdLiName">付款方式</span> 
								</a>
							</li>
							<li id="se_tree_li_hgstOriType">
								<a class="treeLink" href="javascript:findList('se_tree_li_hgstOriType', 'SE','hgstOriType','客人来源类别');"> 
									<img class="scdLiList" src="${ctx}/img/scd_list.png"> 
									<img class="scdLiImg" src="${ctx}/img/tree_main_li_img.png"> 
									<span class="scdLiName">客人来源类别</span> 
								</a>
							</li>
							<li id="se_tree_li_hgstOri">
								<a class="treeLink" href="javascript:findList('se_tree_li_hgstOri', 'SE','hgstOri','客人来源');"> 
									<img class="scdLiList" src="${ctx}/img/scd_list.png"> 
									<img class="scdLiImg" src="${ctx}/img/tree_main_li_img.png"> 
									<span class="scdLiName">客人来源</span> 
								</a>
							</li>
						</ul>
					</div>

					<div id="GE_div" class="treeBody">
						<div class="bodyLiTitle">
							<img class="titleList" src="${ctx}/img/tree_01.png"> 
							<img class="titleImg" src="${ctx}/img/tree_title_img.png"> 
							<span class="titleName">通用代码</span>
						</div>
					</div>
				</div>
				<!--tree-->
			</div>

			<div class="mainMation margin-top-5 FJwebCenter">
				<h4 id="codeClassTitle" class="fontWeight">代码维护</h4>
				<!--table-->
				<div class="tableDiv margin-top-15 tabInformation" id="theGrid" style="overflow-x: hidden; overflow-y: auto; height: 542px;">
                    <!--table enner -END- -->
				</div>
				<!--table -END- -->
			</div>
			<!--mainInformation END-->
			<form id="searchId" action="${ctx}/codeCare/sglist.do" method="post">
				<input type="hidden" name="type" id="type"> 
				<input type="hidden" name="flag" id="flag"> 
		           <div class="choice FJwebRight">
		               <table class="widthB70 marginLRAuto margin-top-30 margin-bottom-30">
		                   <tr>
		                       <td width="50%" align="center">
		                       	<label>
		                       		<input name="status" type="radio" value="0"
										<c:if test="${status eq 0 or empty status}">checked</c:if>
										onclick="getSearch(0)" />有效
								</label>
		                       </td>
		                       <td align="center">
		                       	<label>
		                       		<input name="status" type="radio" value="1"
										<c:if test="${status eq 1}">checked</c:if>
										onclick="getSearch(1)">无效
		                       	</label>
		                       </td>
		                   </tr>
		               </table>
		               <c:choose>
		               	<c:when test="${status eq 0 or empty status}">
		                	<a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="ok">确&nbsp;&nbsp;定</a>
			                <a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="cancel">放&nbsp;&nbsp;弃</a>
			                <a class="button_03 marginLRAuto widthB60" href="javascript:goAdd();" id="add">新&nbsp;&nbsp;增</a>
			                <a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="del">取&nbsp;&nbsp;消</a>
			                <c:choose>
			                	<c:when test="${empty listJson || fn:length(listJson) == 0  || fn:length(listJson) == 2 }">
					                <a class="button_03 marginLRAuto widthB60" href="javascript:;"id="del_0" style="color: grey;cursor:not-allowed;display: none">恢&nbsp;&nbsp;复</a>
			                	</c:when>
			                	<c:otherwise>
					                <a class="button_03 marginLRAuto widthB60" href="javascript:;"id="del_0" style="display: none">恢&nbsp;&nbsp;复</a>
			                	</c:otherwise>
			                </c:choose>
		               	</c:when>
		               	<c:otherwise>
		               		<a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="ok">确&nbsp;&nbsp;定</a>
			                <a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="cancel">放&nbsp;&nbsp;弃</a>
			                <a class="button_03 marginLRAuto widthB60" 
		                	 	<c:choose>
	                				<c:when test="${empty listJson || fn:length(listJson) == 0  || fn:length(listJson) == 2 }">
	                					style="color: grey;cursor:not-allowed;"
	                					href="javascript:;"
	                				</c:when>
	                				<c:otherwise>
	                					href="javascript:goBack();"
			                		</c:otherwise>
		                		</c:choose>
			                	id="back">恢&nbsp;&nbsp;复</a>
		               	</c:otherwise>
		               </c:choose>
		           </div>
			</form>
			<input type="hidden" id="delIds" name="delIds" />
			<input type="hidden" id="backIds" name="backIds" />
		</div>
	</div>
	<!--center -END -->
	<%@ include file="../../footer.jsp"%>
	<!--copyRight -END -->
	<script type="text/javascript" src="${ctx}/js/syssetting/codeCare/codeCare.js"></script>
	<script type="text/javascript" src="${ctx}/js/syssetting/codeCare/hconsume.js"></script>
	<script type="text/javascript" src="${ctx}/js/syssetting/codeCare/hcountry.js"></script>
	<script type="text/javascript" src="${ctx}/js/syssetting/codeCare/hexChange.js"></script>
	<script type="text/javascript" src="${ctx}/js/syssetting/codeCare/hfolk.js"></script>
	<script type="text/javascript" src="${ctx}/js/syssetting/codeCare/hsetlKind.js"></script>
	<script type="text/javascript" src="${ctx}/js/syssetting/codeCare/hsettle.js"></script>
	<script type="text/javascript" src="${ctx}/js/syssetting/codeCare/hgstOri.js"></script>
	<script type="text/javascript" src="${ctx}/js/syssetting/codeCare/hgstOriType.js"></script>
	<link href="${ctx}/js/syssetting/flexGridEditComm.css" rel="stylesheet" type="text/css" />
</body>
</html>
