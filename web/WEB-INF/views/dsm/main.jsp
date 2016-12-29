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
	src="${pageContext.request.contextPath }/utils/materialize/js/materialize.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/utils/font-Awesome/css/font-awesome.min.css">
	
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/dsm/main.css">
</head>

<body>
	<!-- 主框架 -->
	<div class="row" style="margin: 0px;">
		<div class="col s1 main-side-bar-1">
		    <div class="row">  <!-- 占位用 -->
			</div>
		    <div class="row"> 
		      <div class="col s12 hoverable">
		        <a href="#"><img alt="" src="${pageContext.request.contextPath }/img/main/icon.png" width="40px;"></a>
		      </div>  
			</div>
			
			<div class="row">
			  <div class="col s12 hoverable"><h5>连接</h5></div>
			</div>
			
			<div class="row ">
			  <div class="col s12 hoverable"><h6>到文件</h6></div>
			</div>
			
			<div class="row">
			  <div class="col s2"></div>
			  <div class="col s10 hoverable"><a href="#">文本文件</a></div>
			</div>

			<div class="row">
			  <div class="col s2"></div>
			  <div class="col s10 hoverable"><a href="#">JSON文件</a></div>
			</div>
			
			<div class="row">
			  <div class="col s2"></div>
			  <div class="col s10 hoverable"><a href="#">更多...</a></div>
			</div>
			
			<div class="row">
			  <div class="col s12 hoverable"><h6>到服务器</h6></div>
			</div>
			
			<div class="row">
			  <div class="col s2"></div>
			  <!-- Modal Trigger -->
			  <div class="col s10 hoverable"><a class="waves-effect waves-light  modal-trigger" href="#modal-db">MySQL</a></div>
			</div>
			
			<div class="row">
			  <div class="col s2"></div>
			  <!-- Modal Trigger -->
			  <div class="col s10 hoverable"><a class="waves-effect waves-light  modal-trigger" href="#modal-db">Postgresql</a></div>
			</div>
			
			<div class="row">
			  <div class="col s2"></div>
			  <!-- Modal Trigger -->
			  <div class="col s10 hoverable"><a class="waves-effect waves-light  modal-trigger" href="#modal-db">Oracle</a></div>
			</div>
			
			<div class="row">
			  <div class="col s2"></div>
			  <!-- Modal Trigger -->
			  <div class="col s10 hoverable"><a class="waves-effect waves-light  modal-trigger" href="#modal-db">SQL Server</a></div>
			</div>
			
			<div class="row">
			  <div class="col s2"></div>
			  <div class="col s10 hoverable"><a href="#">更多...</a></div>
			</div>
		</div>
		<div class="col s10"></div>
		<div class="col s1"></div>
	</div>













	
	
	<!-- 模态框的主要结构 -->
	<div id="modal-db" class="modal">    <!-- 模态框主要结构开始 -->
		<div class="modal-content">
		   <div class="container">
		     <div class="row">  <!-- 第一行开始 -->
		       <div class="col s2"></div>
		       <div class="col s8">
		         <h5>MySQL数据库连接信息</h5>
		       </div>
		       <div class="col s2"></div>
		     </div> <!--  第一行结束 -->
		     
		     <!-- 定义驱动div开始 -->
		     <div class="row">  <!-- 第二行开始 -->
		       <div class="col s12">
		          <div class="input-field col s12">
                    <input disabled id="driver" type="text" name="driver" class="validate" value="com.mysql.jdbc.Driver">
                    <label for="driver">驱动</label>
                  </div>
		       </div>
            </div> <!-- 第二行结束 -->
            <!-- 定义驱动div结束 -->
            
            
		     <div class="row">  <!-- 第三行开始 -->
		       <div class="col s12">
		          <div class="input-field col s10">
                    <input id="host" type="text" name="host" class="validate">
                    <label for="host">主机名或IP地址</label>
                  </div>  
                  <div class="input-field col s2">
                    <input id="port" type="text" name="port" class="validate" value="3306">
                    <label for="port">端口</label>
                  </div>    
		       </div>
            </div> <!-- 第三行结束 -->
		     
            
            <div class="row">  <!-- 第四行行开始 -->
		       <div class="col s12">
                  <div class="input-field col s12">
                    <input id="connectionName" type="text" name="connectionName" class="validate">
                    <label for="connectionName">连接名</label>
                  </div>  
		       </div>
            </div> <!-- 第四行结束 -->
            
            <div class="row">  <!-- 第五行开始 -->
		       <div class="col s12">
		          <div class="input-field col s6">
                    <input id="userName" type="text" name="userName" class="validate"> 
                    <label for="userName">用户名</label>
                  </div>  
                  <div class="input-field col s6">
                    <input id="password" type="password" name="password" class="validate"> 
                    <label for="password">密码</label>
                  </div>    
		       </div>
            </div> <!-- 第五行结束 -->
   
		   </div>  <!-- container结束 -->		
		</div>  <!-- modal-content部分结束 -->
		
		
	   <div class="modal-footer">
	      <div class="container">
	          <div class="row">
	            <div class="col s2"></div>
	            <div class="col s8">
	               <div class="row">
	                 <div class="col s5">
	                   <a href="#!" class="waves-effect waves-light btn">测试</a>
	                 </div>
	                 <div class="col s2"></div>
	                 <div class="col s5">
	                   <a href="#!" class="waves-effect waves-light btn">保存</a>
	                 </div>
	               </div>  
	            </div>
	            <div class="col s2"></div>
	            
	            
	          </div>   <!-- row结束 -->
	      
	      </div>  <!-- container结束 -->
		    
	      </div>		
	</div>  <!-- 模态框主要结构结束 -->
	
	<!-- 加载模态框 -->
	<script type="text/javascript">
	  $(document).ready(function() {
		// the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
		$('.modal-trigger').leanModal({
		   dismissible: false, // 点击模态框外部则关闭模态框
		});
	});
	</script>
</body>
</html>
