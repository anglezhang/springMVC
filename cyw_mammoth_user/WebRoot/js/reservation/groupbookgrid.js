 //预定详细列表
 function bookDiaryGrid(){
	var checkId =$("#checkId").val();
	var path=$("#path").val();
	if(checkId!=""){
		var url=path+"/bookroom/retain.do?checkId="+checkId;
		$.post(url,{checkId:checkId},function(data){
			data=eval(data);
			if(data!=""&&data.length>0){
 			var array_book=[];
             var count_book = data.length;
             for (var i = 0; i < count_book; i++) {
                 array_book.push({
		        序号: i+1,
		        房类: data[i].roomtypeId,
		        订房: data[i].bookNum,
		        留房: data[i].saveNum,
		       	抵店日期: new Date(data[i].reachDate),
	        	离店日期: new Date(data[i].leaveDate),
	        	房价:data[i].roomPrice,
	        	取消数:data[i].cancelNum,
	        	NoShow数:data[i].noShowNum,
	        	抵达数:data[i].reachNum          
                });
             }
             // create CollectionView on the data (to get events)
             var view2 = new wijmo.collections.CollectionView(array_book);
             // initialize the grid
             var grid2 = new wijmo.grid.FlexGrid('#skSecId', {
              	autoGenerateColumns: false,
             	  columns: [
                    { header: '序号', binding: '序号',width:50,isReadOnly:true }, 
                    { header: '房类', binding: '房类',width:'*' },
                    { header: '订房', binding: '订房',width:'*' },
                    { header: '留房', binding: '留房',width:'*' }, 
                    { header: '抵店日期', binding: '抵店日期',format:'yyyy/MM/dd' ,width:'*'},
                    { header: '离店日期', binding: '离店日期',format:'yyyy/MM/dd' ,width:'*'}, 
                    { header: '房价', binding: '房价',width:'*'},
                    { header: '取消数', binding: '取消数',width:'*' },
                    { header: 'NoShow数', binding: 'NoShow数',width:'*' },
                    { header: '抵达数', binding: '抵达数',width:'*' },
                ],	
                 itemsSource: view2,
             });
			}
		
		})
	
	}else{
		alert("加载默认列表");
		
	}
  }			
  		        
         
         
         
         
         
         