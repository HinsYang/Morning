/**
 * 评论
 */
function addComment() {
    var params = $("#commentForm").serialize();
	$.ajax({
		type : "POST",
		url : baselocation + '/comment/comment',
		data : params,
		dataType : "json",
		success : function(result) {
			if (result.code == 1) {
                layer.msg('评论成功', {
                    icon : 1,
                    time : 3000
                });
                window.location.href=baselocation+"/uc/order/list?type=6";
			} else {
				layer.alert(result.message, {
					icon : 2
				});
			}
		}
	})
}