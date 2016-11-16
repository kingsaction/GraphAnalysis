<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 在一个jsp页面中引入公共的jsp页面 -->
<%@include file="/WEB-INF/views/common/header/header.jsp" %>
<!DOCTYPE html>
<html>
  <head>
     <link rel="stylesheet" href="${pageContext.request.contextPath }/css/ms/login.css">
     <script type="text/javascript" src="${pageContext.request.contextPath }/utils/materialize/js/materialize.min.js"></script>
     <title>登录</title>
  </head>
     <div class="row">
       <div class="col s3"></div>
       <div class="col s6">
         <div class="box-shadow-big">
           <h1>请登录</h1>
           <!-- <div class="divider"></div> -->
           <div class="row top">       <!-- 在其中包含另外一个模块，该模块放置一个表单 -->
           <div class="col s1 left"></div>   <!-- 占位用，在实际中不使用 -->
           <div class="col s5">  <!-- 整个box的左半部分 -->
              <div class="section">
                <h5>登陆 Uniplore</h5>
                <div class="row left">
	                <form class="col s12">
		                <div class="row left">
					        <div class="input-field col s12">
					           <input id="username"  type="text" class="validate">
					           <label for="username">用户名</label>
					        </div>
		                </div>
		                
		                <div class="row">
                           <div class="input-field col s12">
                               <input id="password" type="password" class="validate">
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
                     <button class="btn waves-effect waves-light" type="submit" name="action">登录
                       <i class="mdi-content-send right"></i>
                     </button>
                  </div>
                </div>
              </div>
           </div>
           
           <div class="col s5">   <!-- 整个box的右半部分 -->
               <div class="section right" >
                <h5>使用自己的Uniplore账户来享用其他Uniplore的服务，例如</h5>
               </div>
               <div class="row">
                  <div class="col s2"></div>
                  <div class="col s8">
                  <ul class="myul">
                    <li>可以定义自己的数据源</li>
                    <li>可以保存自己的数据源信息</li>
                    <li>可以定义只属于自己的图</li>
                    <li>可以保存只能自己查看的图</li>
                  </ul> 
                  </div>
                  <div class="col s2"></div>
               </div>
               
                <div class="row">
                  <div class="col s12">
                    <div class="section" >
                     <a href="#">没有账户？立即创建一个</a>
                  </div>
               </div>
           </div>
           <div class="col s1"></div>   <!-- 占位用，在实际中不使用 -->
         </div>
        
         </div>
         
       </div> 
       <div class="col s3"></div>
     </div>
  <body>
    
  </body>
</html>
