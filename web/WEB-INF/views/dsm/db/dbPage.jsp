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
				    <li><input name="group1" type="radio" id="test3"
						class="with-gap" value="grid" checked="checked"/> <label for="test3">grid</label></li>
				    <li><input name="group1" type="radio" id="test4"
						class="with-gap" value="concentric"/> <label for="test4">concentric</label></li>
					<li><input name="group1" type="radio" id="test2"
						class="with-gap" value="cose" /> <label for="test2">cose</label></li>
					<li><input name="group1" type="radio" id="test7"
						class="with-gap" value="circle"/> <label for="test7">circle</label></li>
					<li><input name="group1" type="radio" id="test1"
						class="with-gap" value="breadthfirst"/> <label for="test1">breadthfirst</label></li>
					<li><input name="group1" type="radio" id="test6"
						class="with-gap" value="random"/> <label for="test6">random</label></li>
					<li><input name="group1" type="radio" id="test5"
						class="with-gap" value="preset"/> <label for="test5">preset</label></li>
				</ul>
			</div>
			<input type="hidden" id="nodeTap"/>   <!-- 隐藏域，保存抓取到的点的id -->
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

			<div id="main-content-center-footer" ondblclick="graph_display()">
			 
			</div>
		</div>

        <!-- 右侧边栏放置分析算法 -->
		<div class="main-side-bar-right">
			<div class="main-side-bar-right-header"></div>
			<div class="analysis"><h5>图分析</h5></div>
			<div class="analysis">
				<ul style="padding-left: 25px;margin-top: 25px;" id="graph_impor">
					<li><input name="group2" type="radio" id="algorithm1"
						class="with-gap" value="firstNeighbors" disabled/> <label for="algorithm1">firstNeighbors</label></li>
					<li><input name="group2" type="radio" id="algorithm2"
						class="with-gap" value="breadthFirstSearch" disabled/> <label for="algorithm2">breadthFirstSearch</label></li>
					<li><input name="group2" type="radio" id="algorithm3"
						class="with-gap" value="depthFirstSearch" disabled/> <label for="algorithm3">depthFirstSearch</label></li>
					<li><input name="group2" type="radio" id="algorithm4"
						class="with-gap" value="dijkstra" disabled/> <label for="algorithm4">dijkstra</label></li>
					<li><input name="group2" type="radio" id="algorithm5"
						class="with-gap" value="aStar" disabled/> <label for="algorithm5">aStar</label></li>
					<li><input name="group2" type="radio" id="algorithm6"
						class="with-gap" value="floydWarshall" disabled/> <label for="algorithm6">floydWarshall</label></li>
					<li><input name="group2" type="radio" id="algorithm7"
						class="with-gap" value="bellmanFord" disabled/> <label for="algorithm7">bellmanFord</label></li>
						<li><input name="group2" type="radio" id="algorithm8"
						class="with-gap" value="kruskal" disabled/> <label for="algorithm8">kruskal</label></li>
					<li><input name="group2" type="radio" id="algorithm9"
						class="with-gap" value="kargerStein" disabled/> <label for="algorithm9">kargerStein</label></li>
					<li><input name="group2" type="radio" id="algorithm10"
						class="with-gap" value="pageRank" disabled/> <label for="algorithm10">pageRank</label></li>
					<li><input name="group2" type="radio" id="algorithm11"
						class="with-gap" value="degreeCentrality" disabled/> <label for="algorithm11">degreeCentrality</label></li>
					<li><input name="group2" type="radio" id="algorithm12"
						class="with-gap" value="degreeCentralityNormalized" disabled/> <label for="algorithm12">degreeCentralityNormalized</label></li>
					<li><input name="group2" type="radio" id="algorithm13"
						class="with-gap" value="closenessCentrality" disabled/> <label for="algorithm13">closenessCentrality</label></li>
					<li><input name="group2" type="radio" id="algorithm14"
						class="with-gap" value="closenessCentralityNormalized" disabled/> <label for="algorithm14">closenessCentralityNormalized</label></li>
				    <li><input name="group2" type="radio" id="algorithm15"
						class="with-gap" value="betweennessCentrality" disabled/> <label for="algorithm15">betweennessCentrality</label></li>
				</ul>
			</div>
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
	        if(dbName != "请选择数据库"){
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
				success : function(backData) {
					if (backData.tables != null) {
						var tables = backData.tables.toString();
						var arr = tables.split(",");

						var selector = $('#table_select'); //找到相应的select元素
						for (var i = 0; i < arr.length; i++) {
							selector.append('<option value="' + i + '">' + arr[i] + '</option>'); //构造选择表下拉菜单的option
						}

					}
				},
				error: function (){
                alert("数据库表返回错误");
                }    
	        })       
	        }
	        
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
			var sourceNode = $('#source_select').find("option:selected").text(); //获取当前选中的起始点
			var targetNode = $('#target_select').find("option:selected").text(); //获取当前选中的终点
			
			var driverName =  '<%= (String) request.getParameter("driverName")%>';
			var dataBaseType = '<%= (String) request.getParameter("dataBaseType")%>';
			var ipAddress = '<%= (String) request.getParameter("ipAddress")%>';
			var portNumber = '<%= (String) request.getParameter("portNumber")%>';
			var connectionName = '<%= (String) request.getParameter("connectionName")%>';
			var userName = '<%= (String) request.getParameter("userName")%>';
			var password = '<%= (String) request.getParameter("password")%>';
	
			//alert("获取到的数据为:" + dbName + " " + tableName + " " + source + " " + target);
			
			if(dbName == "请选择数据库" || tableName == "请选择表" || sourceNode == "请选择起始点" || targetNode == "请选择目标点") {
			    //alert("不执行");
			}else{
			    //alert("执行");
			    //获取到数据库的连接信息、选择好的数据库、数据库表、表中的两行发送到服务器端
			    $.ajax({
			       url: "/graphanalysis/dsm/db/dbDataFormatJson?t=" + (new Date()).getTime(),
			       type: "POST",
			       dataType: "JSON",
			       data: {
			            "dbName": dbName,
			            "tableName": tableName,
			            "sourceNode": sourceNode,
			            "targetNode": targetNode,		            
			            "driverName" : driverName,
						"dataBaseType" : dataBaseType,
						"ipAddress": ipAddress,
						"portNumber": portNumber,
						"connectionName": connectionName,
						"userName": userName,
						"password": password,
			       },
			       success: function (backData){
			         //alert("成功的开始构造JSON字符串");
			         var str = JSON.stringify(backData,  null , 2);  //将JSON对象转换成字符串
			         var ly = $('input:radio[name="group1"]:checked').val();
			         //alert(str);
			         cy = cytoscape({    //在此声明了一个全局变量cy，在任何地方都能引用该变量
							container : $("#main-content-center-footer"),  //jquery获取元素
							elements : backData,
							style : [ // the stylesheet for the graph
									{
										selector : 'node',
										style : {
											'background-color' : 'red',
											'label' : 'data(name)',
											/* 'width': 2, */
											'opacity': .9,
											'size': 60,
										}
									},
	
									{
										selector : 'edge',
										style : {
											'width' : 1,
											'line-color' : '#000',
											'target-arrow-color' : '#ccc',
											'target-arrow-shape' : 'triangle',
											'opacity': .9,
										}
									}
								],
							layout : {
								name : ly
							},
							zoom: 1,
                            pan: { x: 0, y: 0 },
                            hideEdgesOnViewport: true,
                            motionBlur: true,
                            motionBlurOpacit: 0.5,
                            wheelSensitivity: 0.5,  /*滚轮滚动时改变图的大小的参数*/
                            pixelRatio: 'auto',
						});
						
						cy.nodes().on('tap', function(event) {
							var nodeID = event.cyTarget.id();   //获取到点击时元素的id
							//获取到要删除元素的id,cyTarget代表当前事件操作的元素
							var nodeObj = cy.$('#'+ nodeID);    //使用#和id进行拼接，形成完整的id选择器，并获取到该元素
							//alert("点击节点"+ nodeID);
						    $('input:radio[name="group2"]').removeAttr("disabled");    //当获取到一个特定的元素之后删除单选框的disabled属性
	                        $('#graph_impor li label').css('color','#2a2a2a');  //当一个点被选中时，给右侧的分析算法颜色全部变成深黑色
	                        
	                        //将上述得到的nodeID保存到sessinon中，方便客户端其它函数获取
	                        $.ajax({
	                            async: false,
	                            url: "/graphanalysis/dsm/db/saveNodeId?t=" + (new Date()).getTime(),
	                            type: "POST",
	                            dataType: "JSON",
	                            data: {
	                                "nodeID" : nodeID,
	                            },
	                            success: function (backData) {
	                                //alert(typeof(backData.nodeID)); 
	                                var inputElement = $('#nodeTap');
	                                inputElement.val(backData.nodeID);
	                            },
	                            error: function (XMLHttpRequest, textStatus, errorThrown){
	                                alert("返回错误");
	                                alert(XMLHttpRequest.readyState + XMLHttpRequest.status + XMLHttpRequest.responseText);
	                            }
	                        })
						});  //tap事件结束
						
		 	       },
			       error: function (XMLHttpRequest, textStatus, errorThrown) {
			           alert("返回错误");
			           alert(XMLHttpRequest.readyState + XMLHttpRequest.status + XMLHttpRequest.responseText);
			       },
			    })
			}
		}   //构造JSON串实现图
	</script>
	
	<!-- 当布局发生变化时，切换布局 -->
	<script type="text/javascript">
	  $(function (){
	     /*在单选列表上增加鼠标单击事件，进行布局的切换*/
			$(":radio").click(function() {
				var ly = $('input:radio[name="group1"]:checked').val();   //获取当前选中的元素值
				//alert(cy);   //获取到变量
				//alert("布局方式为:" + ly + "接受到的id为:" + id + "接受到的fileName为:" + fileName);
				cy.layout({name: ly});    //使用API获取到全局变量cy，切换布局方式
			});
	  })
	</script>
	
	<!-- 当图分析算法发生点击事件时，激活该段代码 -->
	<script type="text/javascript">
     /*在单选列表上增加鼠标单击事件，进行算法切换*/
		$('input:radio[name="group2"]').click(function() {
			var algorithmName = $('input:radio[name="group2"]:checked').val();   //获取当前选中的元素值
			//alert("得到的算法为:"+algorithmName);
		    var nodeID = $('#nodeTap').val();   //得到点的id
		    var nodeObj = cy.$('#'+ nodeID);    //使用#和id进行拼接，形成完整的id选择器，并获取到该元素
		    var colorStr="";
		    //字符串的每一字符的范围  
            var randomArr=['0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f']; 
            //产生一个六位的字符串  
		    for(var i=0;i<6;i++){  
		        //15是范围上限，0是范围下限，两个函数保证产生出来的随机数是整数  
		        colorStr+=randomArr[Math.ceil(Math.random()*(15-0)+0)];  
		    }  
		    var color = '#' + colorStr;
		    //alert("得到的最近的节点为:"+ nodeID); 
		    switch (algorithmName){
		        case "firstNeighbors" :
		        {
		            nodeObj.style({'background-color' : color});    //将选中的点的颜色设置为黑色
                    nodeObj.neighborhood('node').style({'background-color' : color});   //将选中的点的邻居点全部颜色设置为黑色
                    nodeObj.neighborhood('edge').style({'line-color' : color});   //将选中的点的邻居边全部设置为蓝色
                    break;
		        }
		        case "breadthFirstSearch" :
		        {
		            var bfs = cy.elements().bfs({
		                 roots: nodeObj,   
		            });
		            var path = bfs.path;   //找到的路径
		            var found = bfs.found;   //找到的点
		            path.style({'background-color' : color});   //给找到的边着色
		            found.style({'background-color' : color});  //给找到的点着色
		            break;
		        }
		        case "depthFirstSearch" :
		        {
		            var dfs = cy.elements().dfs({
		                roots: nodeObj,
		            });
		            var path = dfs.path; //找到的点的路径
		            var found = dfs.found ; //找到的点
		            path.style({'background-color' : color});   //给找到的边着色
		            found.style({'background-color' : color});  //给找到的点着色
		            break;
		        }
		        case "dijkstra" : 
		        {
		            var dijkstra = cy.elements().dijkstra({
		                root: nodeObj,
		            });
		            break;
		        }
		        
		        case "pageRank" :
		        {
		            var pr = cy.elements().pageRank();
		            //alert(pr.rank(nodeObj));   //输出某个点的pageRank值
		            break;
		        }
		        case "aStar" :
		        {
		            break;
		        }
		        
		        case "floydWarshall" :
		        {
		            break;  
		        }
		        case "bellmanFord" :
		        {
		            break;
		        }
		        case "kruskal" :
		        {
		            break;
		        }
		        case "kargerStein" :
		        {
		            break;
		        }
		        case "degreeCentrality" :
		        {
		            break;
		        }
		        case "degreeCentralityNormalized" :
		        {
		            break;
		        }
		        case "closenessCentrality" :
		        {
		            break;
		        }
		        case "closenessCentralityNormalized" :
		        {
		            break;
		        }
		        case "betweennessCentrality" :
		        {
		            break;
		        }
		    }
		});
	</script>
</body>
</html>
