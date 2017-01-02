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

//启动dropzone
$("#my-awesome-dropzone").dropzone({
    maxFilesize: 5000,   //5000MB代表的是5GB，单位默认为MB
    addRemoveLinks: true,
    acceptedFiles: ".txt,.csv",
    dictDefaultMessage: "请拖动文件到该区域或点击上传文件",
    dictFallbackMessage: "您的浏览器不支持拖拽式文件上传功能",
    dictRemoveFile: "删除文件",
    dictCancelUpload: "取消上传",
    dictCancelUploadConfirmation: "您确定要取消上传？",
    maxFiles: 1,
    dictMaxFilesExceeded: "只能上传{{maxFiles}}个",
    dictInvalidFileType:"您上传的文件类型不正确",
    dictFileTooBig:"可添加的最大文件大小为{{maxFilesize}}Mb，当前文件大小为{{filesize}}Mb ",
    dictResponseError: "上传出现错误",  
    dictRemoveFileConfirmation: "确认删除?",  //删除问价时的确认信息
})