define(function(require, exports, module)
{
	var basePath = $("#path").val(); 
	var t = require("../../frame/Tools");
	/**
	*@描述 房态图前端工具类
	*@param index li下标
	*/
	var setMenuStat = function(index)
	{
		index = index -1;
		$("ul.secondMenu > li:eq(" + index + ")").find("a").addClass('thisSecMenu');
	};

	/**
	*描述 查看维修冻结信息
	*@param roomId 房号
	*@param type 2 维修; 3 冻结
	*@param active 0 正常 1 取消
	*@param startDate 开始日期
	*@param endDate 结束日期
	*/
	var viewFixFrozenInf = function(roomId,type,active,startDate,endDate)
	{
		
		var url = basePath + "/roomFixFrozen/getFixFrozenInfo/" + roomId + "/" + type + "/" + active + "/"
				+ startDate + "/" + endDate;
		var params = {type:'page',title:'维修/冻结信息'
					,id:'rooms_fixfrozen_info',content:url
					,isUpdate:true
					,width:950
					,show:function()
					{
						$.fn.alertDialogShow(params.id);
						var showDiv = $("#rooms_fixfrozen_info");
						showDiv.addClass('freezeInformationDiv alertDiv2');
						showDiv.removeClass('checkInDiv');
					}};
		$.fn.alertDialog(params); 
	};

	/**
	*@描述 设置房类 和特征的 查询值
	*@param inputId 值 intputId
	*@param optionName 选项名称
	*/
	var setDefultValue = function(inputId,optionName)
	{
		var fangxing = $("#" + inputId).val();
		var array = fangxing.split(",");
		$("[name='" + optionName + "']").each(function(index, el)
		{
			var elValue = $(el).val();
			if($.inArray(elValue,array)!=-1)
			{
				$(el).prop("checked",true);
			}	
		});;
	};

	/**
	*@描述 设置日期格式
	*@param editColumn修改字段
	*@param bindDataId 绑定数据的id
	*@param fmtStr 格式化日期字符串 如yyyy-mm-dd
	*/
	var setGridDataFmt = function(editColumn,bindDataId,fmtStr)
	{
		var grid = editColumn.grid;

        grid.formatItem.addHandler(function (s, e) {
            var editRange = grid.editRange,
                column = e.panel.columns[e.col];
            // check whether this is an editing cell of the wanted column
            if (!(e.panel.cellType === wijmo.grid.CellType.Cell 
            		&& column === editColumn && 
            		editRange && 
            		editRange.row === e.row && 
            		editRange.col === e.col)) {
                return;
            }

            // hide standard editor (don't remove!)
            if (e.cell.firstChild) {
                e.cell.firstChild.style.display = 'none';
            }

			var editorRoot = "<input type=\"text\" id=\"" +  bindDataId + "_"+e.row+"\"class=\" wj-grid-editor wj-form-control\" style=\"text-align: center;\">";
			e.cell.innerHTML = editorRoot;
			var ttrr = $("#" + bindDataId + "_" +e.row).inputmask(fmtStr);
			$("#" + bindDataId + "_" + e.row).val(grid.getCellData(e.row, e.col, true));

            // cellEditEnding that updates cell with user's input
            var editEndingEH = function (s, args) {
                grid.cellEditEnding.removeHandler(editEndingEH);
                if (!args.cancel) {
                    args.cancel = true;
                    grid.setCellData(e.row, e.col, $("#" + bindDataId + "_" + e.row).val());
                   
                }
            };

            grid.cellEditEnding.addHandler(editEndingEH);
        });
	};

	/**
	*@描述 发房卡
	*@param  checkid,roomid|checkid,roomid|
	*/
	var hairRoomCard = function(checkIdAndroomId)
	{
		try
		{
			idcard.runADELCard(checkIdAndroomId);
		}catch(e)
		{
			t.error("发房卡错误");
		}
	};

	/**
	*@描述 扫描身份证 返回信息
	*@param  checkid,roomid|checkid,roomid|
	*/
	var scanIdCard = function(checkIdAndroomId,openRoomCard)
	{
		try
		{
			if(t.isEmpty(checkIdAndroomId))
			{
				return 'false';
			}else
			{
				return idcard.runIDCard(checkIdAndroomId,openRoomCard);
			}
		}catch(e)
		{
			return 'true';//如果没有在壳子里面 直接打开
		}

	};

	/**
	*@描述 初始化图片
	*@param imgID 图片ID
	*@param checkID 客户checkID
	*/
	var initPicture = function(imgID,checkId)
	{
		$("#" + imgID).prop('src'
			,basePath + '/guest/loadImage.do?tableName=Citizen&colName=id_bmp&where=where Check_ID='
			+ checkId 
			+ '&r=' 
			+ Math.random());
	};

	/**
	*@描述 更新gird列表
	*@param flexGrid grid对象
	*@param dataList数据列表
	*/
	var updateFlexGrid = function(flexGrid,dataSource)
	{
		flexGrid.onSelectionChanging = function(e)
	    {
			return true;
		};
		view = new wijmo.collections.CollectionView(dataSource);
		flexGrid.itemsSource = view;
		view.refresh();
		view.moveCurrentToLast();
		return view;
		flexGrid.onSelectionChanging = function(e)
	    {
			return false;
		};
		return view;
	};
	
	/**
	*@注册用户信息
	*@param userId 用户ID
	*@param userName 用户名称
	*/
	var registerUserInfo = function(userId,userName)
	{
		try
		{
			idcard.regUserInfo(userId, userName);
		}catch(e)
		{
			t.error("注册用户出错");
		}
	};

	/**
	*@注册用户信息
	*@param userId 用户ID
	*@param userName 用户名称
	*/
	module.exports.RegisterUserInfo = function(userId,userName)
	{
		registerUserInfo(userId,userName);
	};
	/**
	*@描述 发房卡
	*@param  checkid,roomid|checkid,roomid|
	*/
	module.exports.HairGuestRoomCard = function(checkIdAndroomId)
	{
		hairRoomCard(checkIdAndroomId);
	};

	/**
	*@描述 设置房类 和特征的 查询值
	*@param inputId 值 intputId
	*@param optionName 选项名称
	*/
	module.exports.SetDefultInputValue = function(inputId,optionName)
	{
		setDefultValue(inputId,optionName);
	};

	/**
	*@描述 更新gird列表
	*@param flexGrid grid对象
	*@param dataList数据列表
	*/
	module.exports.UpdateWijomFlexGrid = function(flexGrid,dataSource)
	{
		return updateFlexGrid(flexGrid,dataSource);
	};

	/**
	*@描述 初始化图片
	*@param imgID 图片ID
	*@param checkID 客户checkID
	*/
	module.exports.InitPictureSrc = function(imgID,checkId,openRoomCard)
	{
		initPicture(imgID,checkId,openRoomCard);
	};

	/**
	*@描述 扫描身份证 返回信息
	*@param  chekcId
	*@param　roomId
	*@param openRoomCard 是否需要开房卡
	*/
	module.exports.ScanOutIdCard = function(checkIdAndroomId,openRoomCard)
	{
		return scanIdCard(checkIdAndroomId,openRoomCard);
	};

	/**
	*@描述 设置日期格式
	*@param editColumn修改字段
	*@param bindDataId 绑定数据的id
	*@param fmtStr 格式化日期字符串 如yyyy-mm-dd
	*/
	module.exports.SetUtilGridDataFmt = function(editColumn,bindDataId,fmtStr)
	{
		setGridDataFmt(editColumn,bindDataId,fmtStr);
	};
	
	/**
	*@描述 打开查看维修冻结信息
	*@param roomId 房间ID
	*@param type 类型
	*@param active 有效无效
	*/
	module.exports.ViewFixFrozenInfOpen = function(roomId,type,active,startDate,endDate)
	{
		viewFixFrozenInf(roomId,type,active,startDate,endDate);
	};

	module.exports.SetRoomMunu = function(index)
	{
		setMenuStat(index);
	};
});