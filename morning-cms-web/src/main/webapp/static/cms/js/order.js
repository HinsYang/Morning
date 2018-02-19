/**
 * 进行格式转换
 */
function timeFormatter(value) {
	return new Date(value).Format("yyyy-MM-dd HH:mm:ss");
}
function payFormatter(value) {
	if (value == 1) {
		return '<span class="label label-primary">在线支付</span>'
	} else if (value == 0) {
		return '<span class="label label-danger">线下支付</span>'
	}
}
function shipmentTimeFormatter(value) {
	if (value == 3){
        return '<span class="label label-info">双休日、假日送货</span>'
	}
	else if (value == 2) {
        return '<span class="label label-info">工作日送货</span>'
	} else if (value == 1) {
        return '<span class="label label-info">不限送货时间</span>'
	}
}
function shipmentTypeFormatter(value) {
    if (value == 1){
        return '<span class="label label-info">附加运费</span>'
    }
    else if (value == 0) {
        return '<span class="label label-info">免运费</span>'
    }
}

function invoiceTypeFormatter(value) {
	if (value == 1){
		return '<span class="label label-info">不开发票</span>'
	}
	else if (value == 2){
		return '<span class="label label-info">电子发票</span>'
	}
	else if (value == 3){
        return '<span class="label label-info">普通发票</span>'
	}
}

function orderStatusFormatter(value) {
    if (value == 1){
        return '<span class="label label-default">订单提交</span>'
    }
    else if (value == 2){
        return '<span class="label label-primary">已付款</span>'
    }
    else if (value == 3){
        return '<span class="label label-info">拣取配货</span>'
    }
    else if (value == 4){
        return '<span class="label label-info">商品出库</span>'
    }
    else if (value == 5){
        return '<span class="label label-info">等待收货</span>'
    }
    else if (value == 6){
        return '<span class="label label-success">确认收货</span>'
    }
    else if (value == 11){
        return '<span class="label label-warning">自动取消订单</span>'
    }
    else if (value == 12){
        return '<span class="label label-danger">手动取消订单</span>'
    }
}

function actionFormatter(value, row, index) {
    return [
        '<a class="log m-r-sm text-primary" href="javascript:void(0)" title="广告详情">',
        '<i class="glyphicon glyphicon-sort-by-attributes-alt"></i>',
        '</a>',
        '<a class="edit m-r-sm text-warning" href="javascript:void(0)" title="编辑">',
        '<i class="glyphicon glyphicon-edit"></i>',
        '</a>',
        '<a class="remove m-r-sm text-danger" href="javascript:void(0)" title="取消订单">',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>',
    ].join('');
}

window.actionEvents = {
	'click .edit' : function(e, value, row, index) {
		layer_show(row.name, baselocation + '/system/order/list/' + row.orderId + '/edit', 900, 650)
	},
	'click .remove' : function(e, value, row, index) {
		admin_delete(index, row.orderId);
	},
	'click .log' : function(e, value, row, index) {
		window.location.href = baselocation + '/system/order/list/' + row.orderId + '/detail/view';
	}
};

/**
 * 取消订单
 */
function admin_delete(index, value) {
	layer.confirm('确认要取消该订单吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			type : 'delete',
			dataType : 'json',
			url : baselocation + '/system/order/list/delete/' + value,
			success : function(result) {
				if (result.code == 1) {
                    $('#table').bootstrapTable('updateCell', {
                        index : index,
                        field : 'orderStatus',
                        value : 12
                    });
					layer.msg('该订单取消成功!', {
						icon : 1,
						time : 1000
					});
				} else {
					layer.alert(result.message, {
						icon : 2
					});
				}
			}
		})
	});
}

/**
 * 多选框插件
 */
$(document).ready(function() {
	$('input').iCheck({
		checkboxClass : 'icheckbox_flat-green',
		radioClass : 'iradio_flat-green'
	});
});

/**
 * 系统提示
 */
$(function() {
	$('.status-tip').on("click", function() {
		layer.tips('"显示" 代表此数据可用<br>"隐藏" 代表此数据不可用', '.status-tip');
	})
})


/**
 * 表单验证
 */
$(function() {
	$('#form').bootstrapValidator({
		container : 'tooltip',
		message : 'This value is not valid',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			'name' : {
				message : '广告名称验证失败',
				validators : {
					notEmpty : {
						message : '广告名称不能为空'
					}
				}
			},
			'code' : {
				message : '广告标志验证失败',
				validators : {
					notEmpty : {
						message : '广告标志不能为空'
					}
				}
			},	
			'showNumber' : {
				message : '显示数量验证失败',
				validators : {
					notEmpty : {
						message : '广告栏显示数量不能为空'
					},
		            regexp: {
		                regexp: /^[0-9]*$/,
		                message: '广告栏显示数量只能为数字'
		            }
				}
			},
			'width' : {
				message : '宽度验证失败',
				validators : {
					notEmpty : {
						message : '宽度不能为空'
					},
		            regexp: {
		                regexp: /^[0-9]*$/,
		                message: '宽度只能为数字'
		            }
				}
			},	
			'height' : {
				message : '高度验证失败',
				validators : {
					notEmpty : {
						message : '高度不能为空'
					},
		            regexp: {
		                regexp: /^[0-9]*$/,
		                message: '高度只能为数字'
		            }
				}
			},				
		}
	})
		.on('success.form.bv', function(e) {
			// Prevent form submission
			e.preventDefault();

			// Get the form instance
			var $form = $(e.target);

			// Get the BootstrapValidator instance
			var bv = $form.data('bootstrapValidator');
			
			var method = $('#form').attr('data-method');
			// Use Ajax to submit form data
			if (method == 'put') {
				$.ajax({
					data : $form.serialize(),
					dataType : 'json',
					type : 'put',
					url : $form.attr('action'),
					success : function(result) {
						if (result.code == 1) {
							parent.layer.msg("更新广告成功!", {
								shade : 0.3,
								time : 1500
							}, function() {
								window.parent.location.reload(); // 刷新父页面
							});
						} else {
							layer.msg(result.message, {
								icon : 2,
								time : 1000
							});
						}
					}
				})
			} else if (method == 'post') {
				$.ajax({
					data : $form.serialize(),
					dataType : 'json',
					type : 'post',
					url : $form.attr('action'),
					success : function(result) {
						if (result.code == 1) {
							parent.layer.msg("创建广告成功!", {
								shade : 0.3,
								time : 1500
							}, function() {
								window.parent.location.reload(); // 刷新父页面
							});
						} else {
							layer.msg(result.message, {
								icon : 2,
								time : 1000
							});
						}
					}
				})
			}
		});
})