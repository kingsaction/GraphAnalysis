<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 在一个jsp页面中引入公共的jsp页面 -->
<%@include file="/WEB-INF/views/common/header/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/ms/sign.css">
  </head>
  
  <body>
   <div class="row">
     <div class="col s3"></div>
     <div class="col s5">
       <div class="row">
         <div class="col s1"></div>
         <div class="col s2"></div>
         <div class="col s9">  <!-- s9开始 -->
           <div class="row">
             <div class="col s1"></div> 
             <div class="col s10 box-sign-10">
               <div class="row"> <!-- 占位，使得email输入框和顶部间隔一定的距离 -->
                 <div class="col s12"></div>
               </div>
               <div class="row">
                 <form class="col s12">
                   <div class="row">
                     <div class="input-field col s12">
                       <input id="email" type="email" class="validate">
                       <label for="email">Email</label>
                     </div>
                   </div>
                      <div class="row">
                        <div class="input-field col s12">
                         <input id="userName" type="text" class="validate">
                         <label for="userName">User Name</label>
                        </div>
                     </div>
                     
                     <div class="row">
                       <div class="input-field col s12">
                         <input id="password" type="password" class="validate">
                         <label for="password">Password</label>
                       </div>
                   </div>
                   
                   <div class="row">
                       <div class="input-field col s12">
                         <input id="password_confirm" type="password" class="validate">
                         <label for="password_confirm">Confirm Password</label>
                       </div>
                   </div>
                   
                  <div class="row">
                     <div class="input-field col s8">
                       <input id="validate_code" type="text" class="validate">
                       <label for="validate_code">Validate Code</label>
                     </div>
                    <div class="col s4">
                       <img src="#" width="100%" height="50px"/>
                    </div>
                  </div>
                  
                  <div class="row">
                    
                  </div>
                   
                  <div class="row">
                    <div class="col s12">
                      <button class="btn waves-effect waves-light button-self" type="submit" name="action">
                        注册
                      </button>
                    </div>
                  </div>
                 </form>
               </div>
             </div>
             <div class="col s1"></div>
           </div>  
         </div>  <!-- s9结束 -->
       </div>
       
       <div class="row">
         <div class="col s1"></div>
         <div class="col s2"></div>
         <div class="col s9">
           <div class="row">
             <div class="col s1"></div>
             <div class="col s10 sign-bg-10-small">
                <div class="row">
                  <div class="col s2"></div>
                  <div class="col s8">
                    <div class="row">
                    <div class="col s2"></div>
                    <div class="col s8">
                      <div class="row"></div>
                      <div class="row">
                        <a href="${pageContext.request.contextPath }/sign_in">已经有账号?直接登录</a>
                      </div>
                    </div>
                      <div class="col s2"></div>
                    </div>
                  </div>
                  <div class="col s2"></div>
                </div>
             </div>
             <div class="col s1"></div>
           </div>  <!-- row结束 -->
         </div>
           
       </div>   <!-- row结束 -->
          
     </div>  
     
     <div class="col s1"></div>
     <div class="col s3"></div>
   </div>
  </body>
</html>
