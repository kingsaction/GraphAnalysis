<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 在一个jsp页面中引入公共的jsp页面 -->
<%@include file="/WEB-INF/views/common/header/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/ms/sign.css">
    <script src="http://code.jquery.com/jquery-1.12.3.min.js"></script>
    <script src="http://static.geetest.com/static/tools/gt.js"></script>
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
                 <form class="col s12" id="loginForm" action="success.jsp" method="post">
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
                    <div class="col s12">
                      <div id="embed-captcha"></div>
                      <p id="wait" class="show">正在加载验证码......</p>
                      <p id="notice" class="hide">请先拖动验证码到相应位置</p>
                    </div>
                  </div>
                  
                  <div class="row">
                    <div class="col s12">
                      <button class="btn waves-effect waves-light button-self" type="submit" name="action"  id="embed-submit" onclick="doSubmit();">
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
   
   <script>
     var handlerEmbed = function (captchaObj) {
         $("#embed-submit").click(function (e) {
             var validate = captchaObj.getValidate();
             if (!validate) {
                 $("#notice")[0].className = "show";
                setTimeout(function () {
                     $("#notice")[0].className = "hide";
                 }, 2000);
                 e.preventDefault();
            }
         });
        // 将验证码加到id为captcha的元素里，同时会有三个input的值：geetest_challenge, geetest_validate, geetest_seccode
        captchaObj.appendTo("#embed-captcha");
        captchaObj.onReady(function () {
           $("#wait")[0].className = "hide";
       });
     };
     
      $.ajax({
         // 获取id，challenge，success（是否启用failback）
         url: "graphanalysis/imageCaptcha?t=" + (new Date()).getTime(), // 加随机数防止缓存
         type: "get",
         dataType: "json",
         success: function (data) {
             // 使用initGeetest接口
             // 参数1：配置参数
             // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
             initGeetest({
                 gt: data.gt,
                 challenge: data.challenge,
                 product: "float", // 产品形式，包括：float，embed，popup。注意只对PC版验证码有效
                 offline: !data.success // 表示用户后台检测极验服务器是否宕机，一般不需要关注
                 // 更多配置参数请参见：http://www.geetest.com/install/sections/idx-client-sdk.html#config
             }, handlerEmbed);
         }
     });
     
     function doSubmit(){
       //表单数据js检查
       //提交表单
      var validate = captchaObj.getValidate();  //获取滑块校验码的状态，如果没有正确的托动滑块到指定的位置，则其状态为false，否则为true
       if(validate == true){  
         document.getElementById("loginForm").submit(); 
       }

     }
 </script>
  </body>
</html>
