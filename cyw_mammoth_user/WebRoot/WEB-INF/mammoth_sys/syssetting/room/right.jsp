<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>房间管理</title>
</head>
  
  <body>
   <div class="rightMenu">
        	<h6><img class="margin-right-5" src="/img/point-down.png">房间管理</h6>
            <ul class="rightNav">
            	<li><a href="${ctx}/building/list.do" <c:if test="${hoverType==1 }">class="rightNavPoint"</c:if> > <img class="rightNavImg " src="${ctx}/img/rightNavImg.png">建筑定义</a></li>
                <li><a href="${ctx}/hroomType/list.do" <c:if test="${hoverType==2 }">class="rightNavPoint"</c:if>><img class="rightNavImg" src="${ctx}/img/rightNavImg.png">房间类型定义</a></li>
                <li><a href="${ctx}/hroomCharacters/list.do" <c:if test="${hoverType==3 }">class="rightNavPoint"</c:if>><img class="rightNavImg" src="${ctx}/img/rightNavImg.png">房间特征定义</a></li>
                <li><a href="${ctx}/hroomDefine/list.do" <c:if test="${hoverType==4 }">class="rightNavPoint"</c:if>><img class="rightNavImg" src="${ctx}/img/rightNavImg.png">房间定义</a></li>
                <li><a href="${ctx}/clockRoom/list.do" <c:if test="${hoverType==5 }">class="rightNavPoint"</c:if>><img class="rightNavImg" src="${ctx}/img/rightNavImg.png">钟点房设置</a></li>
                <li><a href="${ctx}/holidays/list.do" <c:if test="${hoverType==6 }">class="rightNavPoint"</c:if>><img class="rightNavImg" src="${ctx}/img/rightNavImg.png">周末及节日设置</a></li>
                <li><a href="${ctx}/hroomPlan/list.do" <c:if test="${hoverType==7 }">class="rightNavPoint"</c:if>><img class="rightNavImg" src="${ctx}/img/rightNavImg.png">房价方案</a></li>
            </ul>
            <a href="javascript:;" class="helpImg margin-top-30"><img class="margin-right-10" src="${ctx}/img/help.png">帮助中心</a>
    </div>
  </body>
  <script type="text/javascript">
	/*二级菜单点击选中事件*/
	$(".secondMenu li a").click(function(){
		$(".secondMenu li a").removeClass('thisSecMenu');
		$(this).addClass('thisSecMenu');
	});
	/*二级菜单点击选中事件 结束*/
	/*二级菜单关闭事件*/
	$(".secondMenu li a img").click(function(){
		$(this).parent().parent().prev().children().addClass("thisSecMenu");
		$(this).parent().parent().remove();
	});
	/*二级菜单关闭事件 结束*/
	/*右侧菜单点击*/
	$(".rightNav li a").click(function(){
		$(".rightNav li a").removeClass("rightNavPoint");
		$(this).addClass("rightNavPoint");
		//$(this).parent().parent().remove();
	});
	/*右侧菜单点击 结束*/
	/*添加标签页*/
	$(".nav .showMenu li").click(function(){
		var scdMenuName = $(this).html();
		if(ifHave(scdMenuName)) return;//判断是否已经打开标签
		$(".secondMenu li:last").after('<li><a href="javascript:;">'+scdMenuName+'<img src="${ctx}/img/closeSeMenu.png"></a></li>');
		$(".secondMenu li a").click(function(){
			$(".secondMenu li a").removeClass('thisSecMenu');
			$(this).addClass('thisSecMenu');
		});
		$(".secondMenu li a img").click(function(){
			$(this).parent().parent().prev().children().addClass("thisSecMenu")
			$(this).parent().parent().remove();
		});
	});	
	//便利标签页
	function ifHave(text){
		var tabs = $('ul.secondMenu li');
		for(var i=0;i<tabs.length;i++)
		if($(tabs[i]).text() == text) return true;
	}
	//便利标签页END
	/*添加标签页 结束*/
</script>
</html>
