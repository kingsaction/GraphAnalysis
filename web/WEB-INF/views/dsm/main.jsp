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
<script type="text/javascript"
	src="${pageContext.request.contextPath }/utils/dropzone/js/dropzone.js"></script>  <!-- 引入dropzone的js文件 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/utils/dropzone/js/dropzone-amd-module.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/dsm/main.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/utils/dropzone/css/dropzone.css">   <!-- 引入dropzone的css文件 -->
</head>

<body>
  <!-- 主框架 -->
  <div class="main">
    <div class="main-side-bar-left">
       <div class="logo">
          <dl>
            <dt>
              <a href="#"><img alt="" src="${pageContext.request.contextPath }/img/main/icon.png"></a>
            </dt>
          </dl>
       </div>
       
       <div class="connection">
          <h5><a>连接</a></h5>
          <div class="file">
            <h5><a>到文件</a></h5>
            <ul>
              <li><a class="waves-effect waves-light  modal-trigger" href="#modal-db-text">文本文件</a></li>
              <li><a class="waves-effect waves-light  modal-trigger" href="#modal-db-json">JSON文件</a></li>
              <li><a>Excel文件</a></li>
              <li><a>更多...<span class="fr">〉</span></a></li>
            </ul>
          </div>
          
          <div class="server">
            <h5><a>到服务器</a></h5>
            <ul>
              <li><a class="waves-effect waves-light  modal-trigger" href="#modal-db-mysql">MySQL</a></li>
              <li><a>PostgreSQL</a></li>
              <li><a>Oracle</a></li>
              <li><a>Microsoft SQL Server</a></li>
              <li><a>更多...<span class="fr">〉</span></a></li>
            </ul>
          </div>
          
          <div class="server">
            <h5><a>已保存数据源</a></h5>
            <ul>
              <li><a>facebook关系网络</a></li>
            </ul>
          </div>
       </div>
    </div>
    
    
    <div class="main-content-center">  <!-- 中心部分内容 -->
       <div class="main-content-center-header"></div>   <!-- 撑开该部分，使得和左侧导航条的logo处一样的高度 -->
        <div class="connection">
          <h5><a style="color: #2a2a2a;">打开</a></h5>
        </div>
    </div>
    
    
    <div class="main-side-bar-right">
      <div class="main-side-bar-right-header"></div>
      <div class="connection">
         <h5><a style="color: #2a2a2a;">探索</a></h5>
      </div>
      
      <div class="explore">
        <h5><a>培训</a></h5>
        <ul>
          <li><a>入门</a></li>
          <li><a>连接到数据</a></li>
          <li><a>可视化分析</a></li>
          <li><a>更多...</a></li>
        </ul>
      </div>
      
      <div class="source">
        <h5><a>资源</a></h5>
        <ul>
          <li><a>论坛</a></li>
          <li><a>博客</a></li>
          <li><a>更多...</a></li>
        </ul>
      </div>
    </div>
  </div>
  
  
    <!-- 文本文件模态框的主要结构模板，其它的文件格式都参考此模板，只需要注意在命名时以text、JSON...结束 -->
	<div id="modal-db-text" class="modal">    <!-- 模态框主要结构开始 -->
		<div class="modal-content">
		   <div class="container">
		     <div class="row">  <!-- 第一行开始 -->
		       <div class="col s4"></div>
		       <div class="col s4">
		         <h5>文本文件</h5>
		       </div>
		       <div class="col s4"></div>
		     </div> <!--  第一行结束 -->
		     
		     <!-- 定义驱动div开始 -->
		     <div class="row">
			    <div class="col s12">
			      <form action="${pageContext.request.contextPath }/file/Upload/Text" class="dropzone" id="my-awesome-dropzone-text" enctype="multipart/form-data" method="POST">
			        <div class="fallback">
			           <input type="file" name="file" />
			        </div>
			      </form>
			    </div>
             </div>
            <!-- 定义驱动div结束 -->
            <div class="row">
               <div class="col s4"></div>
               <div class="col s4">
			        <div class="waves-effect waves-light btn" id="dropzonre-submit-text" style="margin-top: 10px;">保存</div>
			   </div>
			   <div class="col s4"></div>
            </div>
		   </div>  <!-- container结束 -->		
		</div>  <!-- modal-content部分结束 -->
	</div>  <!-- 文本文件模态框主要结构结束 -->

    <!-- JSON文件模态框 -->
	<div id="modal-db-json" class="modal">    <!-- 模态框主要结构开始 -->
		<div class="modal-content">
		   <div class="container">
		     <div class="row">  <!-- 第一行开始 -->
		       <div class="col s4"></div>
		       <div class="col s4">
		         <h5>JSON文件</h5>
		       </div>
		       <div class="col s4"></div>
		     </div> <!--  第一行结束 -->
		     
		     <!-- 定义驱动div开始 -->
		     <div class="row">
			    <div class="col s12">
			      <form action="${pageContext.request.contextPath }/file/Upload/Json" class="dropzone" id="my-awesome-dropzone-json" enctype="multipart/form-data" method="POST">
			        <div class="fallback">
			           <input type="file" name="file" />
			        </div>
			      </form>
			    </div>
             </div>
            <!-- 定义驱动div结束 -->
            <div class="row">
               <div class="col s4"></div>
               <div class="col s4">
			        <div class="waves-effect waves-light btn" id="dropzonre-submit-json" style="margin-top: 10px;">保存</div>
			   </div>
			   <div class="col s4"></div>
            </div>
		   </div>  <!-- container结束 -->		
		</div>  <!-- modal-content部分结束 -->
	</div>  <!-- JSON模态框主要结构结束 -->


	<!-- MySQL模态框的主要结构模板，其它的数据库都采用此模板，只需要注意id命名以mysql、postgresql...结束 -->
	<div id="modal-db-mysql" class="modal">    <!-- 模态框主要结构开始 -->
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
		          <div class="input-field col s9 validate" id="driverName" onblur="driver_validate()">
                    <select  name="driverName">
                      <option value="1" class="validate">com.mysql.jdbc.Driver</option>
				      <option value="2" class="validate">com.mysql.jdbc.Driver</option>
				      <option value="3" class="validate">org.gjt.mm.mysql.Driver</option>
                    </select>
                    <label for="driverName">驱动</label>
                  </div>
                  <div class="input-field col s3">
                    <input readonly="readonly" value="MYSQL" id="dataBaseType"  name="dataBaseType" type="text" onblur="readonly_mysql()" class="validate">
                    <label for="dataBaseType">数据库</label>
                  </div>
		       </div>
            </div> <!-- 第二行结束 -->
            <!-- 定义驱动div结束 -->
            
            
		     <div class="row">  <!-- 第三行开始 -->
		       <div class="col s12">
		          <div class="input-field col s9">
                    <input id="ipAddress" type="text" name="ipAddress" class="validate">
                    <label for="ipAddress">主机名或IP地址</label>
                  </div>  
                  <div class="input-field col s3">
                    <input id="portNumber" type="text" name="portNumber" class="validate" value="3306">
                    <label for="portNumber">端口</label>
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
	                   <button class="waves-effect waves-light btn" type="submit" onmousedown="modal_mysql_test()">测试</button>
	                 </div>
	                 <div class="col s2"></div>
	                 <div class="col s5">
	                   <button class="waves-effect waves-light btn" onclick="modal_mysql_save()">登陆</button>
	                 </div>
	               </div>  
	            </div>
	            <div class="col s2"></div>
	            
	            
	          </div>   <!-- row结束 -->
	      </div>  <!-- container结束 -->
		    
	      </div>		
	</div>  <!-- MySQL数据库模态框主要结构结束 -->
	
	
	
	<!-- 启动文本文件拖拽模态框 -->
	<script type="text/javascript">
		//启动dropzone
		$("#my-awesome-dropzone-text").dropzone({
		    autoProcessQueue: false,  //禁止自动上传
		    autoDiscover: false,
		    maxFilesize: 5000,   //5000MB代表的是5GB，单位默认为MB
		    addRemoveLinks: true,
		    acceptedFiles: ".txt,.csv",
		    dictDefaultMessage: "请拖动文件到该区域或点击上传文件",
		    dictFallbackMessage: "您的浏览器不支持拖拽式文件上传功能",
		    dictRemoveFile: "删除文件",
		    dictCancelUpload: "取消上传",
		    dictCancelUploadConfirmation: "您确定要取消上传？",
		    maxFiles: 1,
		    dictMaxFilesExceeded: "只能上传{{maxFiles}}个",
		    dictInvalidFileType:"您上传的文件类型不正确",
		    dictFileTooBig:"可添加的最大文件大小为{{maxFilesize}}Mb，当前文件大小为{{filesize}}Mb ",
		    dictResponseError: "上传出现错误",  
		    dictRemoveFileConfirmation: "确认删除?",  //删除文件时的确认信息
		    init: function() {
		       var myDropzone = this ;
		       $('#dropzonre-submit-text').on("click", function(e) {
	              myDropzone.processQueue();   //当点击按钮时发生提交操作，此时会访问服务器
	           });
	           
               this.on("success", function(file,backData) { 
                   $('#modal-db-text').closeModal({
                      out_duration: 1000,
                   });   //当文件成功的上传之后，模态框消失
               });
            }
            
            
		})
	</script>
	
	<!-- 启动JSON文件拖拽模态框 -->
	<script type="text/javascript">
		//启动dropzone
		$("#my-awesome-dropzone-json").dropzone({
		    autoProcessQueue: false,  //禁止自动上传
		    autoDiscover: false,
		    maxFilesize: 5000,   //5000MB代表的是5GB，单位默认为MB
		    addRemoveLinks: true,
		    acceptedFiles: ".json",
		    dictDefaultMessage: "请拖动文件到该区域或点击上传文件",
		    dictFallbackMessage: "您的浏览器不支持拖拽式文件上传功能",
		    dictRemoveFile: "删除文件",
		    dictCancelUpload: "取消上传",
		    dictCancelUploadConfirmation: "您确定要取消上传？",
		    maxFiles: 1,
		    dictMaxFilesExceeded: "只能上传{{maxFiles}}个",
		    dictInvalidFileType:"您上传的文件类型不正确",
		    dictFileTooBig:"可添加的最大文件大小为{{maxFilesize}}Mb，当前文件大小为{{filesize}}Mb ",
		    dictResponseError: "上传出现错误",  
		    dictRemoveFileConfirmation: "确认删除?",  //删除文件时的确认信息
		    init: function() {
		       var myDropzone = this ;
		       $('#dropzonre-submit-json').on("click", function(e) {
	              myDropzone.processQueue();   //当点击按钮时发生提交操作，此时会访问服务器
	           });
	           
               this.on("success", function(file,backData) {  //dropzone上传文件成功之后激活的事件，backData为从服务器端返回的数据
                   //alert("返回值为:"+backData);
                   $('#modal-db-json').closeModal({
                       out_duration: 1000,    /*关闭模态框,1000代表1000ms，在1s内关闭模态框*/
                   });
                   window.location.href = "${pageContext.request.contextPath }/JsonTypeDataAnalysis?id="+backData;  //成功上传文件之后跳转到指定的路径中
               });
            }
		})
	</script>
	
	<!-- 启用下拉列表 -->
	<script type="text/javascript">
	    $(document).ready(function() {
        $('select').material_select();
      });
	</script>
	
</body>
</html>
