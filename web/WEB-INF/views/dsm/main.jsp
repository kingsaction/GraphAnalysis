<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="Shortcut Icon"
	href="${pageContext.request.contextPath}/img/main/icon.png" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/utils/materialize/css/materialize.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/utils/jquery/jquery-3.1.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/utils/materialize/js/materialize.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/utils/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/dsm/main.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/utils/dropzone/js/dropzone.js"></script>  <!-- 引入dropzone的js文件 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/utils/dropzone/js/dropzone-amd-module.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/dsm/main.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/dsm/db/db.js"></script>
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
              <li><a class="waves-effect waves-light  modal-trigger" href="#modal-db-postgresql">PostgreSQL</a></li>
              <li><a class="waves-effect waves-light  modal-trigger" href="#modal-db-greenplum">Greenplum</a></li>
              <!-- <li><a class="waves-effect waves-light  modal-trigger" href="#modal-db-oracle">Oracle</a></li> -->
              <!-- <li><a>Microsoft SQL Server</a></li> -->
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
		         <h5 style="font-weight: 600;font-size: 22px;">JSON文件</h5>
		       </div>
		       <div class="col s4"></div>
		     </div> <!--  第一行结束 -->
		     
		     <!-- 定义驱动div开始 -->
		     <div class="row">
			    <div class="col s12">
			      <!-- 在上传文件时，必须要指定enctype="multipart/form-data"属性，并且method属性必须为POST，因为get方式对上传的大小有限制，会被截断 -->
			      <form action="${pageContext.request.contextPath }/file/Upload/Json" class="dropzone" id="my-awesome-dropzone-json" enctype="multipart/form-data" method="POST">
			        <div class="fallback">
			           <input type="file" name="file" />   <!-- 上传文件的文本域，类型必须指定为file -->
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
		         <h5 class="db-title-content">MySQL数据库连接信息</h5>
		       </div>
		       <div class="col s2"></div>
		     </div> <!--  第一行结束 -->
		    
		     <!-- 定义驱动div开始 -->
		     <div class="row">  <!-- 第二行开始 -->
		       <div class="col s12">
		          <div class="input-field col s9 validate valid" id="mysqlDriverName">
                    <select  name="mysqlDriverName">
                      <option value="1" class="validate">com.mysql.jdbc.Driver</option>
				      <option value="2" class="validate">com.mysql.jdbc.Driver</option>
				      <option value="3" class="validate">org.gjt.mm.mysql.Driver</option>
                    </select>
                    <label for="mysqlDriverName">驱动</label>
                  </div>
                  <div class="input-field col s3">
                    <input readonly="readonly" value="MYSQL" id="mysqlDataBaseType"  name="mysqlDataBaseType" type="text" onblur="readonly_mysql()" class="validate">
                    <label for="mysqlDataBaseType">数据库</label>
                  </div>
		       </div>
            </div> <!-- 第二行结束 -->
            <!-- 定义驱动div结束 -->
            
            
		     <div class="row">  <!-- 第三行开始 -->
		       <div class="col s12">
		          <div class="input-field col s9">
                    <input id="mysqlIpAddress" type="text" name="mysqlIpAddress" class="validate">
                    <label for="mysqlIpAddress">主机名或IP地址</label>
                  </div>  
                  <div class="input-field col s3">
                    <input id="mysqlPortNumber" type="text" name="mysqlPortNumber" class="validate" value="3306">
                    <label for="mysqlPortNumber">端口</label>
                  </div>    
		       </div>
            </div> <!-- 第三行结束 -->
		     
            
            <div class="row">  <!-- 第四行行开始 -->
		       <div class="col s12">
                  <div class="input-field col s6">
                    <input id="mysqlConnectionName" type="text" name="mysqlConnectionName" class="validate">
                    <label for="mysqlConnectionName">连接名</label>
                  </div>
                  <div class="input-field col s6">
                    <input id="mysqlDataBaseName" type="text" name="mysqlDataBaseName" class="validate">
                    <label for="mysqlDataBaseName">初始数据库名</label>
                  </div>  
		       </div>
            </div> <!-- 第四行结束 -->
            
            <div class="row">  <!-- 第五行开始 -->
		       <div class="col s12">
		          <div class="input-field col s6">
                    <input id="mysqlUserName" type="text" name="mysqlUserName" class="validate"> 
                    <label for="mysqlUserName">用户名</label>
                  </div>  
                  <div class="input-field col s6">
                    <input id="mysqlPassword" type="password" name="mysqlPassword" class="validate"> 
                    <label for="mysqlPassword">密码</label>
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
	                   <button class="waves-effect waves-light btn"   type="submit" onclick="modal_mysql_login()">连接</button>
	                 </div>
	               </div>  
	            </div>
	            <div class="col s2"></div>
	            
	            
	          </div>   <!-- row结束 -->
	      </div>  <!-- container结束 -->
		    
	      </div>		
	</div>  <!-- MySQL数据库模态框主要结构结束 -->
	
	<!-- postgresql模态框 -->
	<div id="modal-db-postgresql" class="modal">    <!-- 模态框主要结构开始 -->
		<div class="modal-content">
		   <div class="container">
		     <div class="row">  <!-- 第一行开始 -->
		       <div class="col s2"></div>
		       <div class="col s8">
		         <h5 class="db-title-content">PostgreSQL数据库连接信息</h5>
		       </div>
		       <div class="col s2"></div>
		     </div> <!--  第一行结束 -->
		    
		     <!-- 定义驱动div开始 -->
		     <div class="row">  <!-- 第二行开始 -->
		       <div class="col s12">
		          <div class="input-field col s9 validate valid" id="postgresqlDriverName">
                    <select  name="postgresqlDriverName">
                      <option value="1" class="validate">org.postgresql.Driver</option>
				      <option value="2" class="validate">org.postgresql.Driver</option>
                    </select>
                    <label for="postgresqlDriverName">驱动</label>
                  </div>
                  <div class="input-field col s3">
                    <input readonly="readonly" value="POSTGRESQL" id="postgresqlDataBaseType"  name="postgresqlDataBaseType" type="text" onblur="readonly_postgresql()" class="validate">
                    <label for="postgresqlDataBaseType">数据库</label>
                  </div>
		       </div>
            </div> <!-- 第二行结束 -->
            <!-- 定义驱动div结束 -->
            
            
		     <div class="row">  <!-- 第三行开始 -->
		       <div class="col s12">
		          <div class="input-field col s9">
                    <input id="postgresqlIpAddress" type="text" name="postgresqlIpAddress" class="validate">
                    <label for="postgresqlIpAddress">主机名或IP地址</label>
                  </div>  
                  <div class="input-field col s3">
                    <input id="postgresqlPortNumber" type="text" name="postgresqlPortNumber" class="validate" value="5432">
                    <label for="postgresqlPortNumber">端口</label>
                  </div>    
		       </div>
            </div> <!-- 第三行结束 -->
		     
            
            <div class="row">  <!-- 第四行行开始 -->
		       <div class="col s12">
                  <div class="input-field col s6">
                    <input id="postgresqlConnectionName" type="text" name="postgresqlConnectionName" class="validate">
                    <label for="postgresqlConnectionName">连接名</label>
                  </div>
                  <div class="input-field col s6">
                    <input id="postgresqlDataBaseName" type="text" name="postgresqlDataBaseName" class="validate">
                    <label for="postgresqlDataBaseName">初始数据库名</label>
                  </div>  
		       </div>
            </div> <!-- 第四行结束 -->
            
            <div class="row">  <!-- 第五行开始 -->
		       <div class="col s12">
		          <div class="input-field col s6">
                    <input id="postgresqlUserName" type="text" name="postgresqlUserName" class="validate"> 
                    <label for="postgresqlUserName">用户名</label>
                  </div>  
                  <div class="input-field col s6">
                    <input id="postgresqlPassword" type="password" name="postgresqlPassword" class="validate"> 
                    <label for="postgresqlPassword">密码</label>
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
	                   <button class="waves-effect waves-light btn" type="submit" onmousedown="modal_postgresql_test()">测试</button>
	                 </div>
	                 <div class="col s2"></div>
	                 <div class="col s5">
	                   <button class="waves-effect waves-light btn"   type="submit" onclick="modal_postgresql_login()">连接</button>
	                 </div>
	               </div>  
	            </div>
	            <div class="col s2"></div>
	            
	            
	          </div>   <!-- row结束 -->
	      </div>  <!-- container结束 -->
		    
	      </div>		
	</div>  <!-- PostgreSQL数据库模态框主要结构结束 -->
	
	<!-- greenplum模态框 -->
	<div id="modal-db-greenplum" class="modal">    <!-- 模态框主要结构开始 -->
		<div class="modal-content">
		   <div class="container">
		     <div class="row">  <!-- 第一行开始 -->
		       <div class="col s2"></div>
		       <div class="col s8">
		         <h5 class="db-title-content">Greenplum数据库连接信息</h5>
		       </div>
		       <div class="col s2"></div>
		     </div> <!--  第一行结束 -->
		    
		     <!-- 定义驱动div开始 -->
		     <div class="row">  <!-- 第二行开始 -->
		       <div class="col s12">
		          <div class="input-field col s9 validate valid" id="greenplumDriverName">
                    <select  name="greenplumDriverName">
                      <option value="1" class="validate">com.pivotal.jdbc.GreenplumDriver</option>
				      <option value="2" class="validate">com.pivotal.jdbc.GreenplumDriver</option>
                    </select>
                    <label for="greenplumDriverName">驱动</label>
                  </div>
                  <div class="input-field col s3">
                    <input readonly="readonly" value="GREENPLUM" id="greenplumDataBaseType"  name="greenplumDataBaseType" type="text" onblur="readonly_greenplum()" class="validate">
                    <label for="greenplumDataBaseType">数据库</label>
                  </div>
		       </div>
            </div> <!-- 第二行结束 -->
            <!-- 定义驱动div结束 -->
            
            
		     <div class="row">  <!-- 第三行开始 -->
		       <div class="col s12">
		          <div class="input-field col s9">
                    <input id="greenplumIpAddress" type="text" name="greenplumIpAddress" class="validate">
                    <label for="greenplumIpAddress">主机名或IP地址</label>
                  </div>  
                  <div class="input-field col s3">
                    <input id="greenplumPortNumber" type="text" name="greenplumPortNumber" class="validate" value="5432">
                    <label for="greenplumPortNumber">端口</label>
                  </div>    
		       </div>
            </div> <!-- 第三行结束 -->
		     
            
            <div class="row">  <!-- 第四行行开始 -->
		       <div class="col s12">
                  <div class="input-field col s6">
                    <input id="greenplumConnectionName" type="text" name="greenplumConnectionName" class="validate">
                    <label for="greenplumConnectionName">连接名</label>
                  </div>
                  <div class="input-field col s6">
                    <input id="greenplumDataBaseName" type="text" name="greenplumDataBaseName" class="validate">
                    <label for="greenplumDataBaseName">初始数据库名</label>
                  </div>  
		       </div>
            </div> <!-- 第四行结束 -->
            
            <div class="row">  <!-- 第五行开始 -->
		       <div class="col s12">
		          <div class="input-field col s6">
                    <input id="greenplumUserName" type="text" name="greenplumUserName" class="validate"> 
                    <label for="greenplumUserName">用户名</label>
                  </div>  
                  <div class="input-field col s6">
                    <input id="greenplumPassword" type="password" name="greenplumPassword" class="validate"> 
                    <label for="greenplumPassword">密码</label>
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
	                   <button class="waves-effect waves-light btn" type="submit" onmousedown="modal_greenplum_test()">测试</button>
	                 </div>
	                 <div class="col s2"></div>
	                 <div class="col s5">
	                   <button class="waves-effect waves-light btn"   type="submit" onclick="modal_greenplum_login()">连接</button>
	                 </div>
	               </div>  
	            </div>
	            <div class="col s2"></div>
	            
	            
	          </div>   <!-- row结束 -->
	      </div>  <!-- container结束 -->
		    
	      </div>		
	</div>  <!-- greenplum数据库模态框主要结构结束 -->
	
	<!-- oracle模态框 -->
	<div id="modal-db-oracle" class="modal">    <!-- 模态框主要结构开始 -->
		<div class="modal-content">
		   <div class="container">
		     <div class="row">  <!-- 第一行开始 -->
		       <div class="col s2"></div>
		       <div class="col s8">
		         <h5 class="db-title-content">Oracle数据库连接信息</h5>
		       </div>
		       <div class="col s2"></div>
		     </div> <!--  第一行结束 -->
		    
		     <!-- 定义驱动div开始 -->
		     <div class="row">  <!-- 第二行开始 -->
		       <div class="col s12">
		          <div class="input-field col s9 validate valid" id="oracleDriverName">
                    <select  name="oracleDriverName">
                      <option value="1" class="validate">oracle.jdbc.driver.OracleDriver</option>
				      <option value="2" class="validate">oracle.jdbc.driver.OracleDriver</option>
                    </select>
                    <label for="oracleDriverName">驱动</label>
                  </div>
                  <div class="input-field col s3">
                    <input readonly="readonly" value="ORACLE" id="oracleDataBaseType"  name="oracleDataBaseType" type="text" onblur="readonly_oracle()" class="validate">
                    <label for="oracleDataBaseType">数据库</label>
                  </div>
		       </div>
            </div> <!-- 第二行结束 -->
            <!-- 定义驱动div结束 -->
            
            
		     <div class="row">  <!-- 第三行开始 -->
		       <div class="col s12">
		          <div class="input-field col s9">
                    <input id="oracleIpAddress" type="text" name="oracleIpAddress" class="validate">
                    <label for="oracleIpAddress">主机名或IP地址</label>
                  </div>  
                  <div class="input-field col s3">
                    <input id="oraclePortNumber" type="text" name="oraclePortNumber" class="validate" value="1521">
                    <label for="oraclePortNumber">端口</label>
                  </div>    
		       </div>
            </div> <!-- 第三行结束 -->
		     
            
            <div class="row">  <!-- 第四行行开始 -->
		       <div class="col s12">
                  <div class="input-field col s6">
                    <input id="oracleConnectionName" type="text" name="oracleConnectionName" class="validate">
                    <label for="oracleConnectionName">连接名</label>
                  </div>
                  <div class="input-field col s6">
                    <input id="oracleDataBaseName" type="text" name="oracleDataBaseName" class="validate">
                    <label for="oracleDataBaseName">初始数据库名</label>
                  </div>  
		       </div>
            </div> <!-- 第四行结束 -->
            
            <div class="row">  <!-- 第五行开始 -->
		       <div class="col s12">
		          <div class="input-field col s6">
                    <input id="oracleUserName" type="text" name="oracleUserName" class="validate"> 
                    <label for="oracleUserName">用户名</label>
                  </div>  
                  <div class="input-field col s6">
                    <input id="oraclePassword" type="password" name="oraclePassword" class="validate"> 
                    <label for="oraclePassword">密码</label>
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
	                   <button class="waves-effect waves-light btn" type="submit" onmousedown="modal_oracle_test()">测试</button>
	                 </div>
	                 <div class="col s2"></div>
	                 <div class="col s5">
	                   <button class="waves-effect waves-light btn"   type="submit" onclick="modal_oracle_login()">连接</button>
	                 </div>
	               </div>  
	            </div>
	            <div class="col s2"></div>
	            
	            
	          </div>   <!-- row结束 -->
	      </div>  <!-- container结束 -->
		    
	      </div>		
	</div>  <!-- oracle数据库模态框主要结构结束 -->
	
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
                   //alert("返回值id为:"+backData.id+" "+" 返回的文件名为"+backData.fileName);
                   $('#modal-db-json').closeModal({
                       out_duration: 1000,    /*关闭模态框,1000代表1000ms，在1s内关闭模态框*/
                   });
                   window.location.href = "${pageContext.request.contextPath }/JsonTypeDataAnalysis?id="+backData.id+"&fileName="+backData.fileName;  //成功上传文件之后跳转到指定的路径中
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
