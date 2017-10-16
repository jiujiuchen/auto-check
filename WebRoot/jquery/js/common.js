function validate(data){
		for (var i=0 ;i<data.length;i++){
			var values = data[i][0];
			if(!values){
				alert(data[i][1]+"不能为空");
				return false;
			}
		}
		return true;
}
function selectByValue(id,value){
 	$("#"+id+" option").each(function(){
	    if($(this).val() == value){
	       $(this).attr('selected', 'selected');
	    }
	 });
}
//修改下拉菜单为扁平化按钮选择后，设置样式操作
function flatSelectByValue(id,value){
    if(typeof(id) != "undefined" && id != null && id.indexOf(".") != -1){
         id = id.replace(".","\\.");
    }
 	$("#"+id+"_span a").each(function(){ 
 	    $(this).removeClass();
	    if(this.getAttribute('value') == value){ 
	       $("#"+id).val(value);
	       $(this).addClass('unit current');
	    }else{
	       $(this).addClass('unit');
	    }
	 });
}
//清空remote验证结果
function emptyValue(id)
{
	var ement= $('#'+id);
	if((ement.data("previousValue") != null && ement.data("previousValue") != "undefined")){
		 ement.data("previousValue").old = null;
	}
}

//滑块
function slider(max,min,step,unit,value){
	var F = new Object;
	F.DurationConfig = {};
	F.DurationConfig.data = [];
	var vm_duration_config = [{"max":max,"min":min,"step":step,"unit":unit}];
	
	var i = 0, l = 0, _i = 0, _l = 0,
    step1 = 0,
    min1 = 0,
    max1 = 0,
    type;

    for(_i = vm_duration_config[i].min - 0, _l = vm_duration_config[i].max - 0; _i <= _l; _i = _i + vm_duration_config[i].step){
    	min1 = max1;
    	max1 = _i;
        step1 = max1 - min1;
        F.DurationConfig.data.push({
            'unit': step1,
            'min': min1,
            'max': max1
        });
    }

    F.DurationConfig.defaultValue = value;
    
    var t = new UC.slider("#uc-duration",{
        data: F.DurationConfig.data,
        defaultValue: F.DurationConfig.defaultValue,
        min: vm_duration_config[0].min,
    	max: vm_duration_config[0].max,
    	step: vm_duration_config[0].step,
        bindInput: '.uc-input'
    });
}
