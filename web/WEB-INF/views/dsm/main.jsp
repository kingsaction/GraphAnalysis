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
	href="${pageContext.request.contextPath }/css/dsm/modal.css">
</head>

<body>
	<!-- Modal Trigger -->
	<a class="waves-effect waves-light btn modal-trigger" href="#modal-db">数据源</a>

	<!-- Modal Structure -->
	<div id="modal-db" class="modal">
		<div class="modal-content">
		   <div class="container">
		     <div class="row">  <!-- 第一行开始 -->
		       <div class="col s3"></div>
		       <div class="col s6">
		         <h5>数据库连接信息</h5>
		       </div>
		       <div class="col s3"></div>
		     </div>  <!-- 第一行结束 -->
		     <div class="row">  <!-- 第二行开始 -->
		       <div class="col s12">
		          <div class="input-field col s12">
                    <input id="Driver" type="text" name="Driver">
                    <label for="Driver">Driver</label>
                  </div>   
		       </div>
            </div> <!-- 第二行结束 -->
		     
		     <div class="row">  <!-- 第三行开始 -->
		       <div class="col s12">
		          <div class="input-field col s12">
                    <input id="url" type="text" name="Url">
                    <label for="url">Url</label>
                  </div>   
		       </div>
            </div> <!-- 第三行结束 -->
            
            <div class="row">  <!-- 第四行开始 -->
		       <div class="col s12">
		          <div class="input-field col s12">
                    <input id="userName" type="text" name="UserName">
                    <label for="userName">User Name</label>
                  </div>   
		       </div>
            </div> <!-- 第四行结束 -->
            
            <div class="row">  <!-- 第五行开始 -->
		       <div class="col s12">
		          <div class="input-field col s12">
                    <input id="password" type="password" name="password">
                    <label for="password">Password</label>
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
	                   <a href="#!" class="waves-effect waves-light btn">连接</a>
	                 </div>
	               </div>  
	            </div>
	            <div class="col s2"></div>
	            
	            
	          </div>   <!-- row结束 -->
	      
	      </div>  <!-- container结束 -->
		    
	      </div>		
	</div>  <!-- modal-db部分结束 -->
	
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
