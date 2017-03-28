// JavaScript Document form

$(document).ready(function(){
	    var c1=false;
		var c2=true;
		var c3=false;
		var c4=true;
		//////////////////// checkbox //////////////////////
		var checkbox_src1="image/checkbox1.jpg"
		var checkbox_src2="image/checkbox2.jpg"
		var radio_src1="image/radio1.jpg"
		var radio_src2="image/radio2.jpg"
		$(".checkbox").click(function(){
			if(!$(this).find("input").attr("checked"))
			{
				$(this).find("input").attr("checked",c2);
				$(this).css("background-image","url("+checkbox_src2+")");
			}
			else
			{
				$(this).find("input").attr("checked",c1);
				$(this).css("background-image","url("+checkbox_src1+")");
			}
		});
		/////////////////////////checkbox_2///////////////////////////////////
		var checkbox_src3="image/checkbox3.png"
		var checkbox_src4="image/checkbox4.png"
       $(".checkbox_2").click(function(){
			if(!$(this).find("input").attr("checked"))
			{
				$(this).find("input").attr("checked",c4);
				$(this).css("background-image","url("+checkbox_src4+")");
			}
			else
			{
				$(this).find("input").attr("checked",c3);
				$(this).css("background-image","url("+checkbox_src3+")");
			}
		})
		
		///////////////////////// radio///////////////////////////////////
		$(".radio-c").click(function(){
			if(!$(this).find("input").attr("checked"))
			{
				$(this).find("input").attr("checked",true)
				$(this).css("background-image","url("+radio_src2+")");
				$(this).siblings(".radio-c").find("input").attr("checked",false);
				$(this).siblings(".radio-c").css("background-image","url("+radio_src1+")");
			}
		})
		//////////////////// select //////////////////////
		$(".select").click(function(){
			$(this).find("ul").fadeToggle(150);
		})
		
		
		$(".select ul li").click(function(){
			click_selectli($(this));
		})
		
		/////////////////////// placeholder //////////////////////////////////////////////////////
		var doc=document;
		var inputs=doc.getElementsByTagName('input');
		var supportPlaceholder='placeholder'in doc.createElement('input');
		var placeholder=function(input){
				var text=input.getAttribute('placeholder');
				var defaultValue=input.defaultValue;
				if(defaultValue=='')
				{
					input.value=text
				}
				input.onfocus=function(){
					if(input.value===text)
					{
						this.value=''
					}
				};
				input.onblur=function(){
					if(input.value==='')
					{
						this.value=text
					}
				}
		};
		if(!supportPlaceholder){
				for(var i=0,len=inputs.length;i<len;i++)
				{
					var input=inputs[i],text=input.getAttribute('placeholder');
					if(input.type==='text'&&text)
					{
						placeholder(input)
					}
				}
		};
/////////////////////////// textarea ///////////////////////////////////////
		
		$(".textarea textarea").keyup(function(){
			if(parseInt($(this).scrollTop()))
			{
				$(this).css("height",parseInt($(this).css("height"))+parseInt($(this).css("line-height")))	
			}
		})
})//docunment.ready end

////////////////////////////// textarea 字数控制 ////////////////////////////////////////////////
		function textarea_count_ctrl(thisobj,max_count){
			if(thisobj.val().length<max_count)
			{	
				thisobj.next(".count").find("span").html(thisobj.val().length)
			}
			else
			{
				thisobj.val(thisobj.val().substring(0,max_count))
				thisobj.next(".count").find("span").html(thisobj.val().length)
			}
		}
////////////////////////////// upload ////////////////////////////////////////////////
			function uploadButtonMove(e,a,x0,y0){
				a.find("input").stop();
				var x1=e.pageX;
				var y1=e.pageY;
				var x2=a.offset().left
				var y2=a.offset().top
				var x=x1-x2-x0;
				var y=y1-y2-y0;
				a.find("input").animate({left:x+"px",top:y+"px"},0);
			}
//////////////////// checkbox_list //////////////////////
		
		function checkbox_list(s,a){
			if(!a.find("input").attr("checked"))
			{
				if(a.parents(".checkbox_list").find("input[checked=checked]").length<s||s==0)
				{
					
					a.find("input").attr("checked",true);
					a.css("border","1px #21db6c solid");
					if(a.parents(".checkbox_list").find("input[checked=checked]").length==s)
					{
						a.siblings().not(a.siblings().has("input[checked=checked]")).css({"border":"1px #ccc dashed","color":"#ccc"});
						a.siblings().not(a.siblings().has("input[checked=checked]")).attr("disabled",true);
					}
				}
			}
			else
			{
				if(a.parents(".checkbox_list").find("input[checked=checked]").length==s)
				{
					a.siblings().not(a.siblings().has("input[checked=checked]")).css({"border":"1px #ccc solid","color":"#8d8d8d"});
					a.siblings().not(a.siblings().has("input[checked=checked]")).attr("disabled",false);
				}
				a.find("input").attr("checked",false);
				a.css("border","1px #ccc solid");
			}
		}
		
		////////////////////////// select 点击事件方法 ////////////////////////////////////////////////////////////
		function click_selectli(thisobj)
		{
			thisobj.siblings("li").removeClass("current");
			thisobj.addClass("current");
			thisobj.parents(".select").find("select option").attr("selected",false);
			thisobj.parents(".select").find("select option:eq("+thisobj.index()+")").attr("selected",true);
			thisobj.parents(".select").find("h2").html(thisobj.html())
			thisobj.parents(".select").find("select").change();
		}
		
		
