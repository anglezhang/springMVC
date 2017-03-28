var path = $('#path').val();	
var roomSelect = false;
var styleRooms = "block";
	//当前房态的房间状态的判断
		var temp = new Array();
		temp[0] = 1;
		temp[1] = 2;
		temp[2] = 4;
		temp[3] = 8;
		temp[4] = 16;
		temp[5] = 32;
		temp[6] = 64;
		temp[7] = 128;
		temp[8] = 256;
		temp[9] = 512;
		temp[10] = 1024;
	function selectRoomType(){
		var ftnum = 65535 ;
		var flag = false;
		var temin;
		$("#f_div input[type='checkbox']").each(function(){
			flag = flag || $(this).is(":checked");
			//alert(flag);
			if($(this).is(":checked")){
				ftnum = (parseInt(ftnum) & parseInt($(this).attr("data-v"))); 
			}
		});
		var tempi = parseInt(ftnum);
		for (var int = 0; int < temp.length; int++) {
			temin = parseInt(temp[int]);
			if(flag){
				if((tempi & temin) != 0){//和0比较，不等于0，可以点击的房态
					$("#kt_"+temin).css("background","white");
				}else{
					$("#kt_"+temin).css("background","gray");//白色可以点击，灰色不可点击
				}
			}else{
				$("#kt_"+temin).css("background","white");
			}
		}
	}
	
	var intfjnum = 0 ;
	function savehoci(el){
		var elnum = new Array();
		elnum.push(el);
		for(var i=0;i<elnum.length;i++){
			if(el==elnum[i]){
				if(styleRooms=="block"){
					$("#shuzi").append('<li id="'+elnum+'">'+elnum+'</li>');
					intfjnum++;
				}else{
					$("#shuzi").remove('<li id="'+elnum+'">'+elnum+'</li>');
					intfjnum--;
				}
				
			}
		}
	
		$("#detailnumfj").val(intfjnum);
		styleRooms=="block"
	}
	