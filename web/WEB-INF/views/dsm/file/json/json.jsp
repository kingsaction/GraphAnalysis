<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<link rel="Shortcut Icon"
	href="${pageContext.request.contextPath}/img/main/icon.png" />
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
			
			<div class="source">数据源</div>
			
			<div class="worksheet" id="data-source">
			  <h5>工作表</h5>
			</div>
        </div>

		<div class="main-content-center">
			<!-- 中心部分内容 -->
			<div class="main-content-center-header">
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
		
	</div>
</body>
</html>
