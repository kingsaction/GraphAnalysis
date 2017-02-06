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
			    <li onmouseover="freeTableAppear();" onmouseout="freeTableDisappear()">
			      <i class="icon-table" style="margin-right: 12px;"></i><%= request.getParameter("fileName") %>
			      <i id="free-table" title="查看数据" class="waves-effect waves-light modal-trigger" href="#modal-free-table"></i>
			    </li>
			  </ul>
			</div>
        </div>

		<div class="main-content-center">
			<!-- 中心部分内容 -->
			<div class="main-content-center-header">
			  <h5 style="margin-left: 25px;"><i class="icon-file-text-alt" style="margin-right: 24px;"></i><%= request.getParameter("fileName") %></h5>
			</div>  <!-- 撑开该部分，使得和左侧导航条的logo处一样的高度 -->
			
			<div class="main-content-center-center" id="target-source" >
			  <div>
			    请将数据集拖拽至此处
			  </div>
			  <div class="main-content-center-footer" id="main-content-center-graphanalysis">
			      图分析数据展示
			  </div>
			</div>	
		    
		    <div class="main-content-center-footer"></div>
		</div>


		<div class="main-side-bar-right">
			<div class="main-side-bar-right-header"></div>
		</div>
		
		<div id="modal-free-table" class="modal modal-fixed-footer">
		<div class="modal-content">
			<p id="modal-content-JSON"></p>
		</div>
		<div class="modal-footer">
            <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat ">关闭</a>
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
					data : {
						"id" : id,
						"fileName" : fileName,
					},
					success : function(backData) {
						//alert(backData.jsonContent);
						$("#modal-content-JSON").html(backData.jsonContent);
					}
				})
			}, // 当模态框打开时执行的函数
			complete : function() {
			} // 当模态框关闭时执行的函数
		}
		);
	</script>
</body>
</html>
