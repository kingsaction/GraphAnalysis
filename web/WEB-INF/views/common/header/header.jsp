<!-- 所有jsp页面的公共头文件，仅仅包含了导航栏部分 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<title>图分析软件</title>
<link media="screen" rel="stylesheet" href="${pageContext.request.contextPath}/utils/common/css/header/header.css">

<script src="${pageContext.request.contextPath}/utils/jquery/jquery-3.1.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
jQuery(document).ready(function(){
	var qcloud={};
	$('[_t_nav]').hover(function(){
		var _nav = $(this).attr('_t_nav');
		clearTimeout( qcloud[ _nav + '_timer' ] );
		qcloud[ _nav + '_timer' ] = setTimeout(function(){
		$('[_t_nav]').each(function(){
		$(this)[ _nav == $(this).attr('_t_nav') ? 'addClass':'removeClass' ]('nav-up-selected');
		});
		$('#'+_nav).stop(true,true).slideDown(200);
		}, 150);
	},function(){
		var _nav = $(this).attr('_t_nav');
		clearTimeout( qcloud[ _nav + '_timer' ] );
		qcloud[ _nav + '_timer' ] = setTimeout(function(){
		$('[_t_nav]').removeClass('nav-up-selected');
		$('#'+_nav).stop(true,true).slideUp(200);
		}, 150);
	});
});
</script>

</head>
<body>
<div class="head-v3">
    
	<div class="navigation-up">
		<div class="navigation-inner">
			<div class="navigation-v3">
				<ul>
					<li class="nav-up-selected-inpage" _t_nav="home">
						<h2>
							<a href="#">首页</a>
						</h2>
					</li>
					<li class="" _t_nav="product">
						<h2>
							<a href="#">云产品</a>
						</h2>
					</li>
					<li class="" _t_nav="wechat">
						<h2>
							<a href="#">微信建站</a>
						</h2>
					</li>
					<li class="" _t_nav="solution">
						<h2>
							<a href="#">行业解决方案</a>
						</h2>
					</li>
					<li class="" _t_nav="cooperate">
						<h2>
							<a href="#">合作伙伴</a>
						</h2>
					</li>
					<li _t_nav="support">
						<h2>
							<a href="#">帮助与支持</a>
						</h2>
					</li>
					<li class="nav-up-selected-inpage" _t_nav="home login" >
						<h2>
							<a href="#">登录</a>
						</h2>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="navigation-down">
		<div id="product" class="nav-down-menu menu-1" style="display: none;" _t_nav="product">
			<div class="navigation-down-inner">
				<dl style="margin-left: 100px;">
					<dt>计算机与网络</dt>
					<dd>
						<a hotrep="hp.header.product.compute1" href="#">云服务器</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.compute2" href="#">弹性Web引擎</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.compute3" href="#">负载均衡</a>
					</dd>
				</dl>
				<dl>
					<dt>存储与CDN</dt>
					<dd>
						<a hotrep="hp.header.product.storage1" href="#">云数据库</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.storage2" href="#">NoSQL高速存储</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.storage4" href="#">对象存储服务(beta)</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.storage3" href="#">CDN</a>
					</dd>
				</dl>
				<dl>
					<dt>监控与安全</dt>
					<dd>
						<a hotrep="hp.header.product.monitoring1" href="#">云监控</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.monitoring2" href="#">云安全</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.monitoring3" href="#">云拨测</a>
					</dd>
				</dl>
				<dl>
					<dt>数据分析</dt>
					<dd>
						<a hotrep="hp.header.product.analysis1" href="#">腾讯云分析</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.analysis2" href="#">关键因子</a>
					</dd>
				</dl>
				<dl>
					<dt>开发者工具</dt>
					<dd>
						<a hotrep="hp.header.product.devtool1" href="#">移动加速</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.devtool2" href="#">应用加固</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.devtool3" href="#">信鸽推送</a>
					</dd>
				</dl>
				<dl>
					<dt>开发者服务</dt>
					<dd>
						<a hotrep="hp.header.product.service1" href="#">安全认证服务</a>
					</dd>
					<dd>
						<a hotrep="hp.header.product.service2" href="#">域名备案</a>
					</dd>
				</dl>
			</div>
		</div>
		<div id="solution" class="nav-down-menu menu-3 menu-1" style="display: none;" _t_nav="solution">
			<div class="navigation-down-inner">
				<dl style="margin-left: 380px;">
					<dd>
						<a class="link" hotrep="hp.header.solution.1" href="#">微信</a>
					</dd>
				</dl>
				<dl>
					<dd>
						<a class="link" hotrep="hp.header.solution.2" href="#">游戏</a>
					</dd>
				</dl>
				<dl>
					<dd>
						<a class="link" hotrep="hp.header.solution.3" href="#">移动应用</a>
					</dd>
				</dl>
			</div>
		</div>
		<div id="support" class="nav-down-menu menu-3 menu-1" style="display: none;" _t_nav="support">
			<div class="navigation-down-inner">
				<dl style="margin-left: 610px;">
					<dd>
						<a class="link" hotrep="hp.header.support.1" href="#">资料库</a>
					</dd>
				</dl>
				<dl>
					<dd>
						<a class="link" hotrep="hp.header.support.2" href="#">论坛</a>
					</dd>
				</dl>
				<dl>
					<dd>
						<a class="link" hotrep="hp.header.support.3" href="#">亿元扶持</a>
					</dd>
				</dl>
			</div>
		</div>
		<div id="cooperate" class="nav-down-menu menu-3 menu-1" style="display: none;" _t_nav="cooperate">
			<div class="navigation-down-inner">
				<dl style="margin-left: 480px;">
					<dd>
						<a hotrep="hp.header.partner.1" href="#">代理商</a>
					</dd>
				</dl>
				<dl>
					<dd>
						<a hotrep="hp.header.partner.2" href="#">微信服务商</a>
					</dd>
				</dl>
				<dl>
					<dd>
						<a hotrep="hp.header.partner.3" href="#">创投机构</a>
					</dd>
				</dl>
			</div>
		</div>
	</div>
</div>
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
<p> 主体内容 </p>
</div>
</body>
</html>