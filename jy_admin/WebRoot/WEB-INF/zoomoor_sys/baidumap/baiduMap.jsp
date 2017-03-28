<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/taglib.jsp" %>
<div class="pageContent" >
	<div id="baidumap" style="width:580px;height:480px">	
	</div>
	<input id="lnglat" type="hidden" value="${lngLat}">
</div>
<div style="display:none;">
	<input type="checkbox"  id="lnglatcheckbox" name="infoDeptSub" />
</div>
<div class="formBar">
	<ul>
		<li>
			<div class="button"><div class="buttonContent"><button type="button" id="baiduMapbuttonlook" bringBackFun="setval" multLookup="infoDeptSub" >确认</button></div></div>
		</li>
		<li>
			<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
		</li>
	</ul>
</div>
<script type="text/javascript">
	/**
     * @param {double} Longitude 经度
     * @param {double} Latitude 纬度
     * @param {int} mapLevel 地图级别
     * @param {string} divId 地图divID
     * @return {BMap} map返回百度地图对象
     * */
	function mapInit(Longitude,Latitude,mapLevel,divId)
	{
		var map = new BMap.Map(divId); 
        map.centerAndZoom(new BMap.Point(Longitude, Latitude), mapLevel);
        map.addControl(new BMap.MapTypeControl()); 
        map.setCurrentCity('西安');
        map.enableScrollWheelZoom(false);
        return map;
	}
	
	/**
     * fun 获取经纬度后执行方法
     * eg : mapTool.getLatLongInf(function(long,lat)
     *       {
     *         alert("经度是" + long + ",纬度是" + lat);
     *        });
     * */
	function getLatLongInf(fun)
	{
	 	var Longitude = 0.0;
        var Latitude = 0.0;
        var geolocation = new BMap.Geolocation();
        geolocation.getCurrentPosition(function(r)
        {
            if(this.getStatus() == BMAP_STATUS_SUCCESS){
                var mk = new BMap.Marker(r.point);
                Longitude = r.point.lng;
                Latitude = r.point.lat;
                fun(Longitude,Latitude);
            }
            else {
                    alert('failed'+this.getStatus());
            }        
	    },{enableHighAccuracy: true})
	}	

	/**
    * @param map 百度地图对象
    * @param Longitude type{int} 经纬度
    * @param Latitude 经纬度
    * @param title title
    * @param info 消息框
    */
	function addMarker(map,Longitude,Latitude,title,info)
	{
		var markerPoint = new BMap.Point(Longitude, Latitude);
        var marker = new BMap.Marker(markerPoint);  //创建标注
        map.addOverlay(marker);    // 将标注添加到地图中
        var opts = {
            width: 200,    // 信息窗口宽度
            height: 60,     // 信息窗口高度
            title: title, // 信息窗口标题
            enableAutoPan: true //自动平移
        }
        var infoWindow = new BMap.InfoWindow(info, opts);  // 创建信息窗口对象
        marker.addEventListener("click", function () {
            map.openInfoWindow(infoWindow, markerPoint); //开启信息窗口
        });
	}

	/**
    * 添加点击事件
    * @param map 地图对象
    * @param fun(lng,lat)点击后对象函数
    */
	function addClickEvent(map,fun)
	{
		map.addEventListener('click',function(e)
        {
            fun(e.point.lng,e.point.lat);
        });
	}
	/**
    * 清除覆盖物
    */
	function clear(map)
	{
		map.clearOverlays();
	}

	$(function()
	{
		var map;
		var editLngLat = $("#lnglat").val();
		if(editLngLat==="")
		{
			getLatLongInf(function(Longitude,Latitude)
			{
				map = mapInit(Longitude,Latitude,18,'baidumap');
				//添加点击动作
				addClickEvent(map,function(lng,lat)
				{
					clear(map);
					addMarker(map,lng,lat,"您点击位置");
					$("#lnglat").val(lng + "," + lat)
					var lnglat = $("#lnglat").val();
					//var s="{id:'"+nodes[0].id+"',name:'"+nodes[0].getParentNode().name+"-"+nodes[0].name+"'}"
					var obj = "{position:'" + lnglat + "'}";
					var lnglatCtl = $("#lnglatcheckbox");
					/*lnglatCtl.val("(" + obj + "),(setval" + obj + ")");*/
					lnglatCtl.val(obj);
					lnglatCtl.attr("checked","checked");
				});
			});
			
		}else
		{
			var array = editLngLat.split(",");
			map = mapInit(parseFloat(array[0]),parseFloat(array[1]),18,'baidumap');
			addMarker(map,parseFloat(array[0]),parseFloat(array[1]),"您点击位置");
			//添加点击动作
			addClickEvent(map,function(lng,lat)
			{
				clear(map);
				addMarker(map,lng,lat,"您点击位置");
				$("#lnglat").val(lng + "," + lat);
				$("#lnglat").val(lng + "," + lat)
				var lnglat = $("#lnglat").val();
				//var s="{id:'"+nodes[0].id+"',name:'"+nodes[0].getParentNode().name+"-"+nodes[0].name+"'}"
				var obj = "{position:'" + lnglat + "'}";
				var lnglatCtl = $("#lnglatcheckbox");
				/*lnglatCtl.val("(" + obj + "),(setval" + obj + ")");*/
				lnglatCtl.val(obj);
				lnglatCtl.attr("checked","checked");
			});
		}
				
	});
</script>
