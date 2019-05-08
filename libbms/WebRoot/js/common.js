function parseISO8601(dateStringInRange) {
	var isoExp = /^\s*(\d{4})-(\d\d)-(\d\d)\s*$/, date = new Date(NaN), month, parts = isoExp
			.exec(dateStringInRange);
	if (parts) {
		month = +parts[2];
		date.setFullYear(parts[1], month - 1, parts[3]);
		if (month != date.getMonth() + 1) {
			date.setTime(NaN);
		}
	}
	return date;
}

function showMessage(type, message, title, formObj, fadeTimeout) {
	if($("#msg_showen").val() == "0") {
		$("#msg_showen").val("1");
		alert(message);
	}
}

function ajax_gotopage(pageNo, formObj) {
	if (formObj) {
		formObj = formObj instanceof $ ? formObj: $(formObj);
	} else {
		formObj = $(".main-blk form");
	}
	$('input[name=pageNo]', formObj).val(pageNo);
	formObj.submit();
}
function clearPageNo(formObj) {
	if (formObj) {
		formObj = formObj instanceof $ ? formObj: $(formObj);
	} else {
		formObj = $(".main-blk form");
	}
	$('input[name=pageNo]', formObj).val(1);
	
}

function set_date_picker(start_date, afternow) {
	if (start_date) {
		start_date = start_date instanceof $ ? start_date: $(start_date);
	} else {
		start_date = $("#start_date");
	}
	var tstart = start_date.data('start');
	var tend = start_date.data('end');
	start_date.DatePicker({
		format:'Y-m-d',
		date: new Date(),
		current: new Date(),
		starts: 1,
		onBeforeShow: function(){
			var inputDateVal = start_date.val();
			start_date.DatePickerSetDate(inputDateVal?inputDateVal:new Date(), true);
			start_date.val(start_date.DatePickerGetDate(start_date));
			return true;
		},
		onRender: function(date) {
			if(afternow) {
				var now = new Date();
				now.setHours(0,0,0,0); 
				return {
					disabled: (date.valueOf() < now.valueOf())
				}
			} else {
				if(tstart && parseISO8601(tstart).valueOf() > date.valueOf()) {
					return {
						disabled: true
					}
				}
				if(tend && parseISO8601(tend).valueOf() <= date.valueOf()) {
					return {
						disabled: true
					}
				}
				return {};
			}
		},
		onChange: function(formated, dates){
			if(formated != start_date.val()) {
				start_date.val(formated);
				start_date.DatePickerHide();
			}
			return true;
		}
	});
	return start_date;
}

function set_interval_date_picker(start_date, end_date, afternow) {
	start_date = set_date_picker(start_date, afternow);
	if (end_date) {
		end_date = end_date instanceof $ ? end_date: $(end_date);
	} else {
		end_date = $("#end_date");
	}
	end_date.DatePicker({
		format:'Y-m-d',
		date: new Date(),
		current: new Date(),
		starts: 1,
		onBeforeShow: function(){
			var inputDateVal = end_date.val();
			var startDateVal = start_date.val();
			startDateVal = startDateVal?new Date(parseISO8601(startDateVal).getTime()+24*60*60*1000):new Date();
			end_date.DatePickerSetDate(inputDateVal?inputDateVal:startDateVal, true);
			end_date.val(end_date.DatePickerGetDate(end_date));
			return true;
		},
		onRender: function(date) {
			var now = new Date();
			now.setHours(0,0,0,0); 
			return {
				disabled: (date.valueOf() < now.valueOf() || date.valueOf() <= parseISO8601(start_date.DatePickerGetDate(start_date)).valueOf())
			}
		},
		onChange: function(formated, dates){
			if(formated != end_date.val()) {
				end_date.val(formated);
				end_date.DatePickerHide();
			}
			return true;
		}
	});
}

function checkAll(checkObj) {
	if (checkObj) {
		checkObj = checkObj instanceof $ ? checkObj: $(checkObj);
	} else {
		checkObj = $('.data_id');
	}
	if(checkObj.length) {
		var ischeck = !checkObj.eq(0).prop('checked');
		checkObj.each(function(index){
			$(this).prop('checked', ischeck);
		});
	}
}

function submit_batch(btnObj) {
	var totalNum = $('.data_id:checked').length;
	if(totalNum > 0) {
		if(confirm("确定要批量操作选中的数据吗？")) {
			$("#batch_form").attr("action",$(btnObj).data('action'));
			$("#batch_form").submit();
		}
	} else {
		showMessage('', '请选择需要操作的数据');
	}
}
