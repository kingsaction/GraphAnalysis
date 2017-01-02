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
		Materialize.toast('两次输入的密码不一致，请重新输入', 4000,'rounded');
		return false;
	}else if(pwd == pwd_confirm && pwd.length == 0){
		$("#password").removeClass("valid");
		$("#password_confirm").removeClass("valid");
		$("#password").addClass("invalid");
		$("#password_confirm").addClass("invalid");
		Materialize.toast('密码不能为空', 4000,'rounded');
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
	if(emailSize == 0){   //说明email为空
		$("#email").removeClass("valid");
		$("#email").addClass("invalid");
		Materialize.toast('邮箱不能为空，请重新输入', 4000,'rounded');
		return false;
	}else{   //否则说明email不为空
		$("#email").removeClass("invalid");
		$("#email").addClass("valid");
		return true;
	}
}

//提交表单时进行校验
function doSubmit(){
	var passwordValidate = validatePasswordEqual();
	var userNameState = userNameNULL();
	//alert("两个密码是否一致"+passwordValidate);
	if (passwordValidate && userNameState) {
		 return true;
	}else{
		return false;
	}
}

//检查用户名是否存在
function checkUserNameExisted(){
	//alert("检查用户名是否存在");
	//获取用户名输入框中的数据
	var userName = $("#userName").val();
	//alert("接收到的用户名为:"+userName);
	if(userName.length == 0){
		//alert("用户名为空")
		$("#userName").removeClass("valid");
		$("#userName").addClass("invalid");
		Materialize.toast('您输入的用户名为空，请重新输入', 4000,'rounded');
		return false;
	}else{
		//使用ajax异步提交技术检查用户名是否存在，因为在登录模块中允许用户使用用户名登录，所以用户名不能重复
		$.ajax({
			url : "ms/sign/CheckUserNameExisted?t=" + (new Date()).getTime(), // 加随机数防止缓存，主要是为了解决IE浏览器的行为异常
			type : "POST",
			data: {
				"userName": userName,
			},
			success : function(data) {
				if(data > 0){
					//说明用户名已经存在
					$("#userName").removeClass("valid");
					$("#userName").addClass("invalid");
					Materialize.toast('用户名已经存在，请重新输入', 4000,'rounded');
					return false;
				}else{
					//否则说明用户名不存在
					$("#userName").removeClass("invalid");
					$("#userName").addClass("valid");
					return true;
				}
			}
		})
	}
	
}

//检查email是否在数据库中已经存在
function checkEmailExisted(){
	//alert("检查邮箱是否已经被注册");
	//获取输入框中的email地址
	var email = $("#email").val();
	if(email.length == 0){
		//说明email为空，不满足要求
		$("#email").removeClass("valid");
		$("#email").addClass("invalid");
		Materialize.toast('邮箱不能为空，请重新输入', 4000,'rounded');
		return false;
	}else{
		$.ajax({
			url : "ms/sign/CheckEmailExisted?t=" + (new Date()).getTime(), // 加随机数防止缓存，主要是为了解决IE浏览器的行为异常
			type : "POST",
			data: {
				"email": email,
			},
			success : function(data) {
				if(data > 0){
					//说明email已经存在
					$("#email").removeClass("valid");
					$("#email").addClass("invalid");
					Materialize.toast('邮箱已经存在，请重新输入', 4000,'rounded');
					return false;
				}else{
					//否则说明email不存在
					$("#email").removeClass("invalid");
					$("#email").addClass("valid");
					return true;
				}
			}
		})
	}	
}