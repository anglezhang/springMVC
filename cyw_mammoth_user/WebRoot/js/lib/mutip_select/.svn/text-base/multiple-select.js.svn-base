var MultipeSelect = function(){
	 var $select = this ;
	 $(".multipeSelect").each(function(){
		 var targetObjectId = $(this).attr("targetObjectId");
		 var targetObjectName = $(this).attr("targetObjectName");
		 var target =  $("#" + targetObjectName) ;
		 var $this = $(this) ;
		 
		 var value = $("#" + targetObjectId).val() ;
		 
		 var $height = 0 ;
		 $this.find("input").each(function(){
			 var val = $(this).val();
			 $(this).attr("checked" , $select.IndexOf(value , val)); 
			 $height += 25 ;
		 }) ;
		 $this.css("height" , $height  +  25 + "px");
		 //点击触发多选下拉
		 target.click(function(){  			
			 $this.css({left:target.left + "px", top:target.top + target.outerHeight() + "px"}).slideDown("fast");	
			
			 $("#table_0").bind("mousedown" , function onBlur(event){
				 if(!( $select.checkEventId(event.target.id) || event.target.className == "multipeSelect" || $(event.target).parents(".multipeSelect").length>0)){
					 var ids = [] , names = [] ;
					 $this.find("input[type='checkbox']").each(function(){
						 if($(this).attr("checked")){
    						 ids.push($(this).val());
    						 names.push($(this).next("span").text());
						 }
		    		 }) ;
					 $("#" + targetObjectId).val(ids.join(",")) ;
					 target.val(names.join(","));
					 $this.fadeOut("fast");
					 $("#table_0").unbind("mousedown" , onBlur);
				 }
			 });
		 });
		 //连锁清除
		 target.change(function(){
    			var val = $(this).val() ;
        		if($select.IsNull(val)){	 
        			$(this).prev("input").val();
        			$(this).next(".multipeSelect").find("input[type='checkbox']").each(function(){
        				$(this).attr("checked" , false) ;
        			})
        		}
    	 });
		 //反选
		 $(this).find("a:last").click(function(){
			 $(this).prevAll("ul").find("input[type='checkbox']").each(function(){
				// $(this).attr("checked" , !($(this).attr("checked"))) ;
				 this.checked = ! this.checked ;
				// console.log($(this).attr("checked"));
				 
			 });
			 return false ;
		 });
		 //清空
		 $(this).find("a:first").click(function(){
			 $(this).prevAll("ul").find("input[type='checkbox']").each(function(){
				 $(this).attr("checked" , false) ;
				 this.checked = false ;
			 });
			 return false ;
		 });
	 }) ;
}
MultipeSelect.prototype.IsNull = function (s) {
	 return (typeof s == 'undefined' || s == null || s == '') ;
}
 
MultipeSelect.prototype.IndexOf = function(src , dest){
	if(this.IsNull(src) || this.IsNull(dest)){
		return false ;
	}
	var a = ',' + src + ',' ;
	var b = ',' + dest + ',' ;
	return (src.indexOf(dest) != -1) ;
}
MultipeSelect.prototype.checkEventId = function (id) {
	var result = false ;
	 $(".multipeSelect").each(function(){
		 if($(this).attr("targetObjectId") == id){
			 result = true ;
			 return false ;
		 }
	 });
	 return result ;
};