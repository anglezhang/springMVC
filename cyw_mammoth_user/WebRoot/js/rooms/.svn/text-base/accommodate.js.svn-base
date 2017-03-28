$(function(){
		/*遮罩层DIV高度*/
		$(".alertDivBg").css("height",$(document).height());     
        $(".alertDivBg").css("width",$(document).width());  
		/*遮罩层DIV高度*/
		//房态右Tab页  右侧的
		$(".roomStatusTitle li").click(function(){
			$(".roomStatusTitle li").removeClass("point");
			$(this).addClass("point");
		});
		//房态右Tab页  右侧的END
		$("#playEnt").click(function(){
			$(".bookRegistration").fadeIn();
			$(".alertDivBg").fadeIn();
		});
		$(".closeAlert").click(function(){
			$(".checkRoomDiv").fadeOut();
			$(".alertDivBg2").fadeOut();
		});
		/*选定房间*/
		$(".checkRoom").click(function(){
			$(".checkRoomDiv").fadeIn();
			$(".alertDivBg2").fadeIn();
			
		});
		/*选定房间*/
		$(".bookRegistration h4 a").click(function(){
			$(".alertDivBg").fadeOut();
			$(".bookRegistration").fadeOut();
		});
        
        ///初始化FlexGrid
        //var aa = eval('('+'${list}'+')');
        var list = ${list};
        var count = list.length;
        var data = list;
        var number=1;
        var tempCheckId = data[0].checkId;
        var array = [];
        for (var i = 0; i < count; i++) {
            var status="";
            if(data[i].bookStat == "B"){
		          	status="预定";
		      }else if(data[i].bookStat=="C"){
		        	status="取消";
		      }else if(data[i].bookStat=="O"){
		        	status="确认预定";
		      }else if(data[i].bookStat=="P"){
		        	status="过期预定";
		      }else if(data[i].bookStat=="R"){
		        	status="部分抵达";
		      }else if(data[i].bookStat=="A"){
		        	status="全部抵达";
		      }else if(data[i].bookStat=="N"){
		        	status="NO SHOW";
		      }  
		    
		    if(tempCheckId!=data[i].checkId){
		    	tempCheckId=data[i].checkId;
		    	number++;
		    } 
		    
          array.push({
	                    序号: number,
	                    checkId: data[i].checkId,
	                    订单号: data[i].bookList,
	                    英文名: data[i].gstNamee,
	                    中文名: data[i].gstNamec,
	                    合约单位:data[i].taName,
	                    电话: data[i].mobile,
	                    抵店日期:data[i].reachDate,
	                    离店日期: new Date(data[i].leaveDate),           
	                    状态:status,
	                    //订房数:data[i].bookNum,
	        
	                    //房类:data[i].roomtypeId,
		                 房类:data[i].codeNamec,
		                 订房:data[i].bookNum,
		                 留房:data[i].saveNum,
		                 抵店日期:data[i].breachDate,
		                 离店日期:data[i].bleaveDate,
		                 房价:data[i].broomPrice,
		                 取消数:data[i].cancelNum,
		                 NOSHOW数:data[i].noShowNum,
		                 抵达数:data[i].reachNum,                  
           });
          
        }
        
        
        // create CollectionView on the data (to get events)
		           var view = new wijmo.collections.CollectionView(array);
		            // initialize the grid
		           var grid = new wijmo.grid.FlexGrid('#guestAccommodate', {
		          		autoGenerateColumns: false,
		          		allowMerging : wijmo.grid.AllowMerging.All,
		          		isReadOnly :  true,
		          		selectionMode : wijmo.grid.SelectionMode.RowRange,
		  		       	  columns: [
		  		              { header: '序号', binding: '序号',width:50,isReadOnly:true,allowMerging:true}, 
		  		              { header: '订单号', binding: '订单号',width:'*' ,allowMerging:true}, 
		  		              { header: '英文名', binding: '英文名',width:'*' ,allowMerging:true}, 
		  		              { header: '中文名', binding: '中文名' ,width:'*',allowMerging:true},
		  		              //----------------bookroom info----------------------
		  		              { header: '房类', binding: '房类',width:'*' },
		  		              { header: '抵店日期', binding: '抵店日期',width:'*',format:'yyyy-MM-dd' },
		  		              { header: '离店日期', binding: '离店日期',width:'*',format:'yyyy-MM-dd' },
		  		              { header: '订房数', binding: '订房',width:'*' },
		  		              { header: '留房数', binding: '留房',width:'*' },
		  		              { header: '抵达数', binding: '抵达数',width:'*' },
		  		              { header: '状态', binding: '状态',width:'*',allowMerging:true },
		  		              { header: 'checkId', binding: 'checkId',width:'*',allowMerging:true },
		  		          ],
		  		          itemsSource: view,
		             });
		            view.trackChanges = true;
		
	});

	/*右侧TAB页切换DIV*/
	
	$("#userCheckIn").click(function(){
		$(".checkLogin").css("display","block");
		$(".currentState").css("display","none");
		$(".yd-TD").addClass("displayNone");
		$(".yd-SK").removeClass("displayNone");
		
	});
	$("#userCurrentState").click(function(){
		$(".checkLogin").css("display","none");
		$(".currentState").css("display","block");
		$(".yd-SK").addClass("displayNone");
		$(".yd-TD").removeClass("displayNone");
	});
	/*END右侧TAB页切换DIV*/
	/*时间控件*/
	$('.datetimepicker').datetimepicker();
	/*时间控件结束*/