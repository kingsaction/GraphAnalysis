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
	href="${pageContext.request.contextPath }/css/dsm/db/db.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/utils/easyui/css/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/utils/easyui/css/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/dsm/db/db.js"></script>
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
						<div class="file-title"><%= request.getParameter("ipAddress") %></div>
						<div class="file-type"><%= request.getParameter("dataBaseType") %></div>
					</li>
				</ul>
			</div>

			<div class="worksheet" id="data-source">
				<h5>数据库</h5>
				<ul>
					<li>
						<div>
							<select class="browser-default" id="db_select">
								<option value="" selected>请选择数据库</option>
							</select>
						</div>
					</li>
				</ul>
			</div>
			
			<div class="worksheet" id="data-source">
				<h5>表</h5>
				<ul>
					<li>
						<div>
							<select class="browser-default" id="table_select">
								<option value="" selected>请选择表</option>
							</select>
						</div>
					</li>
				</ul>		
			</div>
            
            <div class="worksheet" id="data-source">
				<h5>源点</h5>
				<ul>
					<li>
						<div>
							<select class="browser-default" id="source_select">
								<option value="" selected>请选择起始点</option>
							</select>
						</div>
					</li>
				</ul>			
			</div>
			
			<div class="worksheet" id="data-source">
				<h5>目标点</h5>
				<ul>
					<li>
						<div>
							<select class="browser-default" id="target_select">
								<option value="" selected>请选择目标点</option>
							</select>
						</div>
					</li>
				</ul>
						
			</div>
			
			<div class="graph-layout">
				<h5>布局</h5>
				<ul>
					<li><input name="group1" type="radio" id="test1"
						class="with-gap" value="breadthfirst"/> <label for="test1">breadthfirst</label></li>
					<li><input name="group1" type="radio" id="test2"
						class="with-gap" value="cose" /> <label for="test2">cose</label></li>
					<li><input name="group1" type="radio" id="test3"
						class="with-gap" value="grid" checked="checked"/> <label for="test3">grid</label></li>
					<li><input name="group1" type="radio" id="test4"
						class="with-gap" value="concentric"/> <label for="test4">concentric</label></li>
					<li><input name="group1" type="radio" id="test5"
						class="with-gap" value="preset"/> <label for="test5">preset</label></li>
					<li><input name="group1" type="radio" id="test6"
						class="with-gap" value="random"/> <label for="test6">random</label></li>
					<li><input name="group1" type="radio" id="test7"
						class="with-gap" value="circle"/> <label for="test7">circle</label></li>
				</ul>
			</div>
		</div>

		<div class="main-content-center">
			<!-- 中心部分内容 -->
			<div class="main-content-center-header">
				<h5 style="margin-left: 25px;">
					<i class="icon-file-text-alt" style="margin-right: 24px;"></i><%= request.getParameter("ipAddress") %></h5>
			</div>
			<!-- 撑开该部分，使得和左侧导航条的logo处一样的高度 -->

			<div class="main-content-center-center" id="target-source">
				<div>请将数据集拖拽至此处</div>
			</div>

			<div id="main-content-center-footer" onclick="graph_display()">
			 
			</div>
		</div>


		<div class="main-side-bar-right">
			<div class="main-side-bar-right-header"></div>
		</div>

		<div id="modal-free-table" class="modal modal-fixed-footer">
			<div class="modal-content">
				<pre id="modal-content-JSON" class="cls"></pre>
			</div>
			<div class="modal-footer">
				<a class="modal-action modal-close waves-effect waves-green btn-flat ">关闭</a>
			</div>
		</div>

	</div>
	
	<script type="text/javascript">
	  $(document).ready(function() {
        $('select').material_select();
      });
	</script>
	
	<!-- 当页面加载完成后，完成数据库的初始化操作 -->
	<script type="text/javascript">
	  $(function (){
	     //在加载该页面时完成"选择数据库"下拉菜单的初始化
	     var driverName =  '<%= (String) request.getParameter("driverName")%>';
		 var dataBaseType = '<%= (String) request.getParameter("dataBaseType")%>';
		 var ipAddress = '<%= (String) request.getParameter("ipAddress")%>';
		 var portNumber = '<%= (String) request.getParameter("portNumber")%>';
		 var connectionName = '<%= (String) request.getParameter("connectionName")%>';
		 var userName = '<%= (String) request.getParameter("userName")%>';
		 var password = '<%= (String) request.getParameter("password")%>';
		 
		 $.ajax({
		     url: "/graphanalysis/dsm/db/showDatabase?t=" + (new Date()).getTime(),
		     type: "POST",
		     dataType: "JSON",
		     data : {
				"driverName" : driverName,
				"dataBaseType" : dataBaseType,
				"ipAddress": ipAddress,
				"portNumber": portNumber,
				"connectionName": connectionName,
				"userName": userName,
				"password": password,
			},
			success: function (backData){
			     var dbNames = backData.dbNames.toString();
			     //alert(dbNames);
			     var arr = dbNames.split(",");   //将字符串从,处切分开
			     var selector = $('#db_select');   //找到相应的select元素
			     for(var i = 0 ; i < arr.length ; i++){
			        //alert(arr[i]);   //遍历得到的字符数组
			        selector.append('<option value="'+i+'">'+arr[i]+'</option>');  //得到的元素形式为<option value="0">information_schema</option>
			     }
			},
			
			error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("数据库返回错误");
				}
		 })
	  })
	</script>
	
	<!-- 完成数据库和该数据库中表的二级联动 -->
	<script type="text/javascript">
	    $('#db_select').change(function (){
	        //在构造option之前，应该删除之前已经存在的option，否则会出现option的叠加现象
	        var tableElement = document.getElementById("table_select");
	        tableElement.options.length = 1;
	        var sourceElement = document.getElementById("source_select");  //联动源点的下拉菜单
	        sourceElement.options.length = 1;
	        var targetElement = document.getElementById("target_select");   //联动目标点的下拉菜单
	        targetElement.options.length = 1; 
	        
	        
	        //获取数据库选中的值
	        var index = this.selectedIndex;  //this代表是当前select下拉框
	        var optionElement = this[index];   //把整个下拉框看成一个数组来处理，每一个option都是数组中的元素
	        var dbName = optionElement.innerHTML;   //获取到选中下来框的内容
	        
	        var driverName =  '<%= (String) request.getParameter("driverName")%>';
			var dataBaseType = '<%= (String) request.getParameter("dataBaseType")%>';
			var ipAddress = '<%= (String) request.getParameter("ipAddress")%>';
			var portNumber = '<%= (String) request.getParameter("portNumber")%>';
			var connectionName = '<%= (String) request.getParameter("connectionName")%>';
			var userName = '<%= (String) request.getParameter("userName")%>';
			var password = '<%= (String) request.getParameter("password")%>';
	        //发送ajax请求到服务器端，得到该数据库下所有的表
	        $.ajax({
	            url: "/graphanalysis/dsm/db/showTable?t=" + (new Date()).getTime(),
	            type: "POST",
	            dataType: "JSON",
	            data: {
	                "driverName" : driverName,
					"dataBaseType" : dataBaseType,
					"ipAddress": ipAddress,
					"portNumber": portNumber,
					"connectionName": connectionName,
					"userName": userName,
					"password": password,
					"dbName": dbName,
	            },
	            success: function (backData) {
					var tables = backData.tables.toString();
					var arr = tables.split(",");
                    
					var selector = $('#table_select'); //找到相应的select元素
					for (var i = 0; i < arr.length; i++) {
						selector.append('<option value="' + i + '">' + arr[i] + '</option>'); //构造选择表下拉菜单的option
					}
	
					},
	            error: function (){
	                alert("数据库表返回错误");
	            }    
	        })       
	    })
	</script>
	
	<!-- 完成数据库表到表的列的二级联动 -->
	<script type="text/javascript">
	    $('#table_select').change(function (){
	        //在构造option之前，应该删除之前已经存在的option，否则会出现option的叠加现象
	        var sourceElement = document.getElementById("source_select");  //联动源点的下拉菜单
	        sourceElement.options.length = 1;
	        var targetElement = document.getElementById("target_select");   //联动目标点的下拉菜单
	        targetElement.options.length = 1; 
	        
	        //获取选中的数据库名
	        var dbName = $('#db_select').find("option:selected").text();
	        /* alert(dbName); */
	        //获取表选中的值
	        var index = this.selectedIndex;  //this代表是当前select下拉框
	        var optionElement = this[index];   //把整个下拉框看成一个数组来处理，每一个option都是数组中的元素
	        var tableName = optionElement.innerHTML;   //获取到选中下来框的内容
	        
	        //alert(tableName);
	        var driverName =  '<%= (String) request.getParameter("driverName")%>';
			var dataBaseType = '<%= (String) request.getParameter("dataBaseType")%>';
			var ipAddress = '<%= (String) request.getParameter("ipAddress")%>';
			var portNumber = '<%= (String) request.getParameter("portNumber")%>';
			var connectionName = '<%= (String) request.getParameter("connectionName")%>';
			var userName = '<%= (String) request.getParameter("userName")%>';
			var password = '<%= (String) request.getParameter("password")%>';
	        //发送ajax请求到服务器端，得到该数据库下所有的表
	        $.ajax({
	            url: "/graphanalysis/dsm/db/showColumn?t=" + (new Date()).getTime(),
	            type: "POST",
	            dataType: "JSON",
	            data: {
	                "driverName" : driverName,
					"dataBaseType" : dataBaseType,
					"ipAddress": ipAddress,
					"portNumber": portNumber,
					"connectionName": connectionName,
					"userName": userName,
					"password": password,
					"dbName": dbName,
					"tableName": tableName
	            },
	            success: function (backData){
	               var columns = backData.columns.toString();
	               var arr = columns.split(",");
	               var selector_source = $('#source_select');   //找到相应的select元素
	               var selector_target = $('#target_select');   //找到相应的select元素
	               for(var i = 0 ; i < arr.length ; i++){    
	                   selector_source.append('<option value="'+i+'">'+arr[i]+'</option>');  //构造选择表下拉菜单的option
	                   selector_target.append('<option value="'+i+'">'+arr[i]+'</option>');  //构造选择表下拉菜单的option
	               }
	            },
	            error: function (){
	                alert("返回错误");
	            }    
	        })       
	    })
	</script>
	
	<!-- 构造JSON串，实现图展示 -->
	<script type="text/javascript">
		function graph_display() {
			var dbName = $('#db_select').find("option:selected").text(); //获取当前的选中的数据库
			var tableName = $('#table_select').find("option:selected").text(); //获取当前选中的表
			var source = $('#source_select').find("option:selected").text(); //获取当前选中的起始点
			var target = $('#target_select').find("option:selected").text(); //获取当前选中的终点
	
			alert("获取到的数据为:" + dbName + " " + tableName + " " + source + " " + target);
		}
	</script>
</body>
</html>
