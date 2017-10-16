/**
 * 新增部署单元
 * @return
 */
function newDeployUnit(){
		$.ajax({
			type:'post',
			datatype : "json",
			url:baseUrl+'/viewDeployUnit/newDeployUnit.action',
			async : false,
			data:{//获得表单数据-替换假数据
			'deployunitPo.datacenter_id':'111',
            'deployunitPo.app_id':'11',
			'deployunitPo.cname':'11',
			'deployunitPo.full_cname':'11',
			'deployunitPo.ename':'11',
			'deployunitPo.full_ename':'11',
			'deployunitPo.service_type_code':'11',
			'deployunitPo.status':'1',
			'deployunitPo.secure_area_code':'11',
			'deployunitPo.sevure_tier_code':'11',
			'deployunitPo.remark':'1',
			'deployunitPo.is_active':'1'
	      },
			success:(function(data){
				alert(data.otherContent);
			}),
			error:function(){alert('发生错误');} 
		});
	 }

/**
 * 更新部署单元
 * @return
 */
function modifyDeployUnit(){
	$.ajax({
		type:'post',
		datatype : "json",
		url:baseUrl+'/viewDeployUnit/updateDeployUnit.action',
		async : false,
		data:{//获得表单数据-替换假数据
		'deployunitPo.du_id':'97284BF933FA39497414107470806263',
		'deployunitPo.datacenter_id':'22',
        'deployunitPo.app_id':'22',
		'deployunitPo.cname':'22',
		'deployunitPo.full_cname':'22',
		'deployunitPo.ename':'22',
		'deployunitPo.full_ename':'22',
		'deployunitPo.service_type_code':'22',
		'deployunitPo.status':'2',
		'deployunitPo.secure_area_code':'22',
		'deployunitPo.sevure_tier_code':'22',
		'deployunitPo.remark':'2',
		'deployunitPo.is_active':'2'
      },
		success:(function(data){
			alert(data.otherContent);
		}),
		error:function(){alert('发生错误');} 
	});
}

/**
 * 删除部署单元
 * @return
 */
function deleteDeployUnit(){
	$.ajax({
		type:'post',
		datatype : "json",
		url:baseUrl+'/viewDeployUnit/deleteDeployUnit.action',
		async : false,
		data:{//获得表单数据-替换假数据 '1','2','3'
		'du_ids':'\'B83B4F1A6B95EB047E14107542475563\',\'B83B4F1A6B95EB047E14107542482704\''
      },
		success:(function(data){
			alert(data.otherContent);
		}),
		error:function(){alert('发生错误');} 
	});
}

