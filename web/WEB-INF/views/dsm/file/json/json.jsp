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
<script type="text/javascript"
	src="${pageContext.request.contextPath }/utils/qtip/js/cytoscape.min.js"></script>
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

			<div class="graph-layout">
				<h5>布局</h5>
				<ul>
					<li><input name="group1" type="radio" id="test1"
						class="with-gap" value="breadthfirst" /> <label for="test1">breadthfirst</label></li>
					<li><input name="group1" type="radio" id="test2"
						class="with-gap" value="cose"/> <label for="test2">cose</label></li>
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
				<pre id="modal-content-JSON" class="cls"></pre>
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
					    var str = JSON.stringify(backData,  null , 2);  //将JSON对象转换成字符串
					    str = str.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
					    //alert("得到的字符串为:"+str);
						$("#modal-content-JSON").html(str);  //将字符串赋值给指定的部分用于显示
					}//success函数结束
				})
			}, // 当模态框打开时执行的函数
			//complete: function () {
			    //var id =  '<%=(String) request.getParameter("id")%>';
			    //var fileName = '<%=(String) request.getParameter("fileName")%>';
			    //var ly = $('input:radio[name="group1"]:checked').val();
			    //alert("布局方式为:"+ly);
				//$.ajax({
					//async : false,
					//url : "file/Upload/FindData?t=" + (new Date()).getTime(),
					//type : "POST",
					//dataType: "JSON",
					//data : {
						//"id" : id,
						//"fileName" : fileName,
					//},
					//success : function(backData) {
						//$(function() {
							//cytoscape({
								//container : document.getElementById('main-content-center-footer'),
								//elements : backData,
								//layout: { name: ly} 
							//});
						//});
					//}, //success函数结束
					//error : function (XMLHttpRequest, textStatus, errorThrown) {
					   //alert("返回错误");
					   //alert(XMLHttpRequest.readyState + XMLHttpRequest.status + XMLHttpRequest.responseText);  
					//}
				//})
			//}//complete函数结束
		}
		);
	</script>

    <!-- 当页面加载时调用的js代码 -->
	<script type="text/javascript">
		$(function() {
			var id = '<%=(String) request.getParameter("id")%>';
			var fileName = '<%=(String) request.getParameter("fileName")%>';
			var ly = $('input:radio[name="group1"]:checked').val();
			//alert("布局方式为:" + ly + "接受到的id为:" + id + "接受到的fileName为:" + fileName);
			$.ajax({
				async : false,
				url : "file/Upload/FindData?t=" + (new Date()).getTime(),
				type : "POST",
				dataType : "JSON",
				data : {
					"id" : id,
					"fileName" : fileName,
				},
				success : function(backData) {
					$(function() {
						cy = cytoscape({    //在此声明了一个全局变量cy，在任何地方都能引用该变量
							container : $("#main-content-center-footer"),  //jquery获取元素
							elements : backData,
							style : [ // the stylesheet for the graph
									{
										selector : 'node',
										style : {
											'background-color' : '#000',
											'label' : 'data(id)'
										}
									},
	
									{
										selector : 'edge',
										style : {
											/* 'width' : 1, */
											'line-color' : '#ccc',
											'target-arrow-color' : '#ccc',
											'target-arrow-shape' : 'triangle'
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
	
						/*在已有的图中增加节点和边，新增的边既可以和原先已有的数据建立联系，也可以和新增的其它节点建立联系*/
						/* cy.add([
						  {  data: { id: "n0" }, position: { x: 100, y: 100 } },
	  					      {  data: { id: "n1" }, position: { x: 200, y: 200 } },
	                          {  data: { id: "e0", source: "n0", target: "195" } },
	                          {  data: { id: "e1", source: "n0", target: "n1" } }
						]) */
	
						/*当抓取到节点时，首先获取当该节点的id，并删除该节点*/
						/* cy.nodes().on('tap', function(event) {
							//alert(event.cyTarget.id());   //获取到点击时元素的id
							
							//获取到要删除元素的id,cyTarget代表当前事件操作的元素
							var r_n0 = cy.$('#'+event.cyTarget.id());    //使用#和id进行拼接，形成完整的id选择器，并获取到该元素
						    cy.remove(r_n0);   //删除上述方法获取到的元素
						}); */
						
					});
				}, //success函数结束
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("返回错误");
					alert(XMLHttpRequest.readyState + XMLHttpRequest.status + XMLHttpRequest.responseText);
				}
			})
			
			/*在单选列表上增加鼠标单击事件，进行布局的切换*/
			$(":radio").click(function() {
				var ly = $('input:radio[name="group1"]:checked').val();   //获取当前选中的元素值
				//alert(cy);   //获取到变量
				//alert("布局方式为:" + ly + "接受到的id为:" + id + "接受到的fileName为:" + fileName);
				cy.layout({name: ly});    //使用API获取到全局变量cy，切换布局方式
			});
		});
	</script>
</body>
</html>
