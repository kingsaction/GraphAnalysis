//模态框启动
$(document).ready(function() {
	// the "href" attribute of .modal-trigger must specify the modal ID that
	// wants to be triggered
	$('.modal-trigger').leanModal({
		dismissible : false, // 点击模态框外部则关闭模态框
	});
});

function modal_mysql_test(){
	alert("修改该部分，采用ajax异步实现提前判断是否能连接上数据源");
	$("#modal_mysql").submit();
}

function readonly_mysql(){
	$("#readonly").removeClass("invalid");
	$("#readonly").addClass("valid");
}