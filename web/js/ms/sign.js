//定义验证码上存在的事件，比如当验证码输入错误时，显示的文字提示
var handlerEmbed = function(captchaObj) {
	$("#embed-submit").click(function(e) {
		var validate = captchaObj.getValidate();
		if (!validate) {
			$("#notice")[0].className = "show";
			setTimeout(function() {
				$("#notice")[0].className = "hide";
			}, 2000);
			e.preventDefault();
		}
	});
	// 将验证码加到id为captcha的元素里，同时会有三个input的值：geetest_challenge, geetest_validate, geetest_seccode
	captchaObj.appendTo("#embed-captcha");
	captchaObj.onReady(function() {
		$("#wait")[0].className = "hide";
	});
};

//使用JQuery技术异步获取验证码
$.ajax({
	// 获取id，challenge，success（是否启用failback）
	url : "graphanalysis/imageCaptcha?t=" + (new Date()).getTime(), // 加随机数防止缓存，主要是为了解决IE浏览器的行为异常
	type : "get",
	dataType : "json",
	success : function(data) {
		// 使用initGeetest接口
		// 参数1：配置参数
		// 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
		initGeetest({
			gt : data.gt,
			challenge : data.challenge,
			product : "float", // 产品形式，包括：float，embed，popup。注意只对PC版验证码有效
			offline : !data.success
		// 表示用户后台检测极验服务器是否宕机，一般不需要关注
		// 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
		}, handlerEmbed);
	}
});

//检查两次输入的密码是否一致
function validatePasswordEqual(){
	var pwd = $("#password").val();
	var pwd_confirm = $("#password_confirm").val();
	if(pwd != pwd_confirm){   //两次密码不相等，不提交表单
		$("#password").removeClass("valid");
		$("#password_confirm").removeClass("valid");
		$("#password").addClass("invalid");
		$("#password_confirm").addClass("invalid");
		return false;
	}else if(pwd == pwd_confirm && pwd.length == 0){
		$("#password").removeClass("valid");
		$("#password_confirm").removeClass("valid");
		$("#password").addClass("invalid");
		$("#password_confirm").addClass("invalid");
		return false;
	}else{
		$("#password").removeClass("invalid");
		$("#password_confirm").removeClass("invalid");
		$("#password").addClass("valid");
		$("#password_confirm").addClass("valid");
		return true;
	}
}

//用户名不能为空
function userNameNULL(){
	var userNameSize = $("#userName").val().length;
	if(userNameSize == 0){
		$("#userName").removeClass("valid");
		$("#userName").addClass("invalid");
		return false;
	}else{
		$("#userName").removeClass("invalid");
		$("#userName").addClass("valid");
	}
}

//email不能为空
function emailNULL(){
	var emailSize = $("#email").val().length;
	if(emailSize == 0){
		$("#email").removeClass("valid");
		$("#email").addClass("invalid");
		return false;
	}else{
		$("#email").removeClass("invalid");
		$("#email").addClass("valid");
		return true;
	}
}
//提交表单时进行校验
function doSubmit() {
	//表单数据js检查
	//提交表单
	alert("哈哈");
	var validate = captchaObj.getValidate(); //获取滑块校验码的状态，如果没有正确的托动滑块到指定的位置，则其状态为false，否则为true
	
	var passwordValidate = validatePasswordEqual();
	alert(passwordValidate);
	if (validate == true  && passwordValidate == true) {
		document.getElementById("loginForm").submit();
	}
}