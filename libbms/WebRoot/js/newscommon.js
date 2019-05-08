/*
 * 改变查询条件框的状态
 */
function changeschdiv(){  
	var id = $("#key_id").val();
	if(id == 1 || id == 2){
		$("#time_sch").show();	
		$("#text_sch").hide();
		$("#word_id").val("");	
	}else if(id == 3 || id == 4 ){
		$("#time_sch").hide();	
		$("#text_sch").show();
		$("#start_date").val("");
		$("#end_date").val("");
		
	}
}

/*
 * 计算字符串英文单词的个数
 */
function getwordnum(value,id){
	//去除特殊符号
	value=value.replace(/[\ |\~|\`|\!|\@|\#|\$|\%|\^|\&|\*|\(|\)|\-|\_|\+|\=|\||\\|\[|\]|\{|\}|\;|\:|\"|\'|\,|\<|\.|\>|\/|\?]/g," "); 			
    // 替换中文字符为空格
    value = value.replace(/[\u4e00-\u9fa5]+/g, " ");
    // 将换行符，前后空格不计算为单词数
    value = value.replace(/\n|\r|^\s+|\s+$/gi,"");
    // 多个空格替换成一个空格
    value = value.replace(/\s+/gi," ");
    // 更新计数
    var length = 0;
    var match = value.match(/\s/g);
    if (match) {
        length = match.length + 1;
    } else if (value) {
        length = 1;
    }
    $("#"+id).val(length);	

}

/*
 * 计算中文模块字符串的字数
 */
function getchwordnum(value,id){
	
	//去除特殊符号
	value=value.replace(/[\ |\~|\`|\!|\@|\#|\$|\%|\^|\&|\*|\(|\)|\-|\_|\+|\=|\||\\|\[|\]|\{|\}|\;|\:|\"|\'|\,|\<|\.|\>|\/|\?]/g," "); 			
    // 将中文字符用英文字符代替在统计英文单词的个数
    value = value.replace(/[\u4e00-\u9fa5]/g, " abc ");
    // 将换行符，前后空格不计算为单词数
    value = value.replace(/\n|\r|^\s+|\s+$/gi,"");
    // 多个空格替换成一个空格
    value = value.replace(/\s+/gi," ");
    // 更新计数
    var length = 0;
    var match = value.match(/\s/g);
    if (match) {
        length = match.length + 1;
    } else if (value) {
        length = 1;
    }
    $("#"+id).val(length);	

}


/*
 * 根据map自动生成select菜单
 */
function getoplist(map,tag){
	var currentop = '<option value=""></option>';	
		$.each(map,function(key,value){
			if(key == tag){ 
		   		currentop = currentop + "<option value='"+key+"' selected>"+map[key]+"</option>";	
		   }else{
		   		currentop = currentop + "<option value='"+key+"'>"+map[key]+"</option>";	
		   }							
		}); 
	return currentop;
		
}


/*
 * 查询条件的时间判断验证
 */
function checksch(){
	var year = $("#year_id").val();
	var sdate = $("#start_date").val();
	var edate = $("#end_date").val();
	var stime = new Date(Date.parse(sdate.replace(/-/g,   "/"))); //转换成Data(); 
	var etime = new Date(Date.parse(edate.replace(/-/g,   "/"))); //转换成Data(); 
	


	if(sdate == "" && edate != ""){
		alert("请输入查询时间段开始时间");
		return false;
	}
	if(sdate != "" && edate == ""){
		alert("请输入查询时间段结束时间");
		return false;
		
	}
	if(year!=0){
		var syear = (year-1)+"-11-1";
		var eyear = year+"-11-1";
		var start = new Date(Date.parse(syear.replace(/-/g,   "/"))); //转换成Data(); 
		var end = new Date(Date.parse(eyear.replace(/-/g,   "/"))); //转换成Data(); 
		
		if(sdate!="" || edate!=""){
			if(stime>end || stime<start){
				alert('查询开始时间只能在'+syear+"到"+eyear+"之间");
				return false;
			}
			
			if(etime>end || etime<start){
				alert('查询结束时间只能在'+syear+"到"+eyear+"之间");
				return false;
			}
		}
	}

}




