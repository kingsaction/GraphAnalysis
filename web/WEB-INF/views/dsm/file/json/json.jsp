<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="Shortcut Icon"
	href="${pageContext.request.contextPath}/img/main/icon.png" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/utils/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/utils/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/utils/font-awesome/css/font-awesome-ie7.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/utils/materialize/css/materialize.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/utils/jquery/jquery-3.1.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/utils/easyui/js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/utils/easyui/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/utils/materialize/js/materialize.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/dsm/file/json.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/utils/easyui/css/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/utils/easyui/css/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/dsm/file/json.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/utils/cytoscape/js/cytoscape.min.js"></script>
</head>

<body>
	<div class="main">
		<div class="main-side-bar-left">
			<div class="logo">
				<dl>
					<dt>
						<a href="#"><img alt=""
							src="${pageContext.request.contextPath }/img/main/icon.png"></a>
					</dt>
				</dl>
			</div>

			<div class="source">
				<h5>连接</h5>
				<ul>
					<li>
						<div class="file-title"><%= request.getParameter("fileName") %></div>
						<div class="file-type">JSON文件</div>
					</li>
				</ul>
			</div>

			<div class="worksheet" id="data-source">
				<h5>文件</h5>
				<ul>
					<li onmouseover="freeTableAppear();" onmouseout="freeTableDisappear()" title="拖动数据集到指定位置">
					<i class="icon-table" style="margin-right: 12px;"></i><%= request.getParameter("fileName") %>
						<i id="free-table" title="查看数据"
						class="waves-effect waves-light modal-trigger"
						href="#modal-free-table"></i>
				    </li>
				</ul>
			</div>
		</div>

		<div class="main-content-center">
			<!-- 中心部分内容 -->
			<div class="main-content-center-header">
				<h5 style="margin-left: 25px;">
					<i class="icon-file-text-alt" style="margin-right: 24px;"></i><%= request.getParameter("fileName") %></h5>
			</div>
			<!-- 撑开该部分，使得和左侧导航条的logo处一样的高度 -->

			<div class="main-content-center-center" id="target-source">
				<div>请将数据集拖拽至此处</div>
			</div>

			<div id="main-content-center-footer">
			 
			</div>
		</div>


		<div class="main-side-bar-right">
			<div class="main-side-bar-right-header"></div>
		</div>

		<div id="modal-free-table" class="modal modal-fixed-footer">
			<div class="modal-content">
				<span id="modal-content-JSON"></span>
			</div>
			<div class="modal-footer">
				<a class="modal-action modal-close waves-effect waves-green btn-flat ">关闭</a>
			</div>
		</div>

	</div>
	<script type="text/javascript">
		$('.modal-trigger').leanModal({
			dismissible : true, // 点击模态框外部则关闭模态框
			opacity : 0.5, // 背景透明度
			in_duration : 300, // 切入时间
			out_duration : 200, // 切出时间
			ready : function() {
				var id =  '<%= (String) request.getParameter("id")%>';
				var fileName = '<%= (String) request.getParameter("fileName")%>';
				$.ajax({
					async : false,
					url : "file/Upload/FindData?t=" + (new Date()).getTime(),
					type : "POST",
					dataType: "JSON",
					data : {
						"id" : id,
						"fileName" : fileName,
					},
					success : function(backData) {
					    //alert(typeof(backData));   //从服务器端得到的是JSON对象，类型为Object
					    var str = JSON.stringify(backData);  //将JSON对象转换成字符串
					    //alert("得到的字符串为:"+str);
						$("#modal-content-JSON").html(str);  //将字符串赋值给指定的部分用于显示
					}//success函数结束
				})
			}, // 当模态框打开时执行的函数
			complete: function () {
			    var id =  '<%=(String) request.getParameter("id")%>';
			    var fileName = '<%=(String) request.getParameter("fileName")%>';
				$.ajax({
					async : false,
					url : "file/Upload/FindData?t=" + (new Date()).getTime(),
					type : "POST",
					dataType: "JSON",
					data : {
						"id" : id,
						"fileName" : fileName,
					},
					success : function(backData) {
						$(function() {
							cytoscape({
								container : document.getElementById('main-content-center-footer'),
								elements : backData,
								layout : { name : 'circle'}
							});
						});
					}, //success函数结束
					error : function (XMLHttpRequest, textStatus, errorThrown) {
					   alert("返回错误");
					   alert(XMLHttpRequest.readyState + XMLHttpRequest.status + XMLHttpRequest.responseText);  
					}
				})
			}//complete函数结束
		}
		);
	</script>
</body>
</html>
