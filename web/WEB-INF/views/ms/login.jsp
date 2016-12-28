<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 在一个jsp页面中引入公共的jsp页面 -->
<%@include file="/WEB-INF/views/common/header/header.jsp" %>
<!DOCTYPE html>
<html>
  <head>
     <link rel="stylesheet" href="${pageContext.request.contextPath }/css/ms/login.css">
     <script type="text/javascript" src="${pageContext.request.contextPath}/utils/jquery/jquery-3.1.0.min.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath }/utils/materialize/js/materialize.min.js"></script>
     <link rel="stylesheet" href="${pageContext.request.contextPath }/utils/font-Awesome/css/font-awesome.min.css">
     <title>登录</title>
  </head>  
  <body>
  <div class="row bg">
    <div class="col s3"></div>   <!-- 导航条的左侧部分占整个屏幕的三份 -->
    <div class="col s6">  <!-- 主要内容部分占6份 -->
       <div class="row">
       <div class="col s12">
         <div class="box-shadow-big">
           <h1>请登录</h1>
           <!-- <div class="divider"></div> -->
           <div class="row top">       <!-- 在其中包含另外一个模块，该模块放置一个表单 -->
           <div class="col s1 left"></div>   <!-- 占位用 -->
           <div class="col s5">  <!-- 整个box的左半部分 -->
              <div class="section">
                <h5>登陆 Uniplore</h5>
                <div class="row left">
	                <form class="col s12" action="${pageContext.request.contextPath}/ms/login/Login" method="POST">
		                <div class="row left">
					        <div class="input-field col s12">
					           <input id="name"  type="text" class="validate" name="name">
					           <label for="name">用户名或邮箱</label>
					        </div>
		                </div>
		                
		                <div class="row">
                           <div class="input-field col s12">
                               <input id="password" type="password" class="validate" name="password">
                               <label for="password">密码</label>
                        </div>
                     </div>
		            </form>
	            </div>
              </div>
              
              <div class="section">
                <div class="row">
                  <div class="col s6 left">
                     <a href="#">忘记密码?</a>
                  </div>
                  <div class="col s6">
                     <button class="btn waves-effect waves-light button-self" type="submit" name="action">登录
                       <i class="mdi-content-send right"></i>
                     </button>
                  </div>
                </div>
              </div>
           </div>
           
           <div class="col s5 line">   <!-- 整个box的右半部分 -->
               <div class="section right" >
                <h5>使用自己的Uniplore账户来享用其他Uniplore的服务，例如</h5>
               </div>
               <div class="row">
                  <div class="col s2"></div>
                  <div class="col s8">
                  <ul class="myul">
                    <li>保存数据源信息</li>
                    <li>保存图</li>
                    <li>保存分析结果</li>
                    <li>资源隔离</li>
                  </ul> 
                  </div>
                  <div class="col s2"></div>
               </div>
               
                <div class="row">
                  <div class="col s12">
                    <div class="section" >
                     <a href="${pageContext.request.contextPath }/sign_up">没有账户？立即创建一个</a>
                  </div>
               </div>
           </div>
           <div class="col s1"></div>   <!-- 占位用，在实际中不使用 -->
         </div>
        
         </div>
         
       </div> 
       
     </div>
    </div>
    <div class="col s3"></div>
  </div>
  </body>
</html>
