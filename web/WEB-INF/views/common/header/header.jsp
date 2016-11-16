<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>导航条</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/utils/materialize/css/materialize.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/common/header/head.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/utils/jquery/jquery-3.1.0.min.js"></script>
</head>
<body>
<div class="header">
	<div class="headerinner">
		<ul class="headernav">
			<li class="logo">
				<img src="${pageContext.request.contextPath}/img/main/icon.png" alt="Logo" />
			</li>
			<li><a href="#">首页</a></li>
			<li><a href="#" >链接1</a></li>
			<li><a href="#">增加链接2</a></li>
			<li><a href="#" >增加链接3</a></li>
			<li><a href="#" >关于我们</a></li>
			<li class="search">
				<a class="search_pic"></a>
			</li>
			<li class="list">
				<a></a>
			</li>
		</ul>
		<!-- 搜索 -->
		<form action="">
			<div class="search_main">
				<button class="search_btn" type="submit"></button>
				<input class="search_text" type="text" placeholder="搜索">
				<span class="close_btn"></span>
			</div>
		</form>
		<!-- 会员登录 -->
		<div class="member">
			<p>用户中心</p>
			<ul>
				<li>
					<img src="${pageContext.request.contextPath}/img/header/huiyuan1.png" alt="">
					<a href="${pageContext.request.contextPath }/sign_in">登录</a>
				</li>
				<li>
					<img src="${pageContext.request.contextPath}/img/header//huiyuan1.png" alt="">
					<a href="register.html">新用户注册</a>
				</li>
			</ul>
		</div>
	</div>
</div>
<script>
$(function(){
	/*搜索*/
	$(".search_pic").click(function(){
		$(".headernav").fadeOut(500);
		$(".search_main").fadeIn(1000);
	});
	$(".search_main .close_btn").click(function(){
		$(".search_main").fadeOut(500);
		$(".headernav").fadeIn(1000);
	});
	/*登录*/
	$(".list a").click(function(){
		$(".member").slideToggle(500);
	});

});
</script>

</body>
</html>
