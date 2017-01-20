<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 在一个jsp页面中引入公共的jsp页面 -->
<%@include file="/WEB-INF/views/common/header/header.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/ms/sign.css">
    <script src="http://code.jquery.com/jquery-1.12.3.min.js"></script>
    <script src="http://static.geetest.com/static/tools/gt.js"></script>
    <script src="${pageContext.request.contextPath }/utils/jquery/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath }/js/ms/sign.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/common/footer/footer.css">
  </head>
  
  <body>
  <a class="backtop">返回顶部</a>
   <div class="row">
     <div class="col s4"></div>
     <div class="col s4">
       <div class="row">
         <div class="col s1"></div>
         <div class="col s2"></div>
         <div class="col s6">  <!-- s6开始 -->
           <div class="row">
             <div class="col s1"></div> 
             <div class="col s10 box-sign-10">
               <div class="row"> <!-- 占位，使得email输入框和顶部间隔一定的距离 -->
                 <div class="col s12"></div>
               </div>
               <div class="row">
                 <form class="col s12" action="${pageContext.request.contextPath }/ms/sign/SignUp" method="POST" onsubmit="return doSubmit()">
                   <div class="row">
                     <div class="input-field col s12">
                       <input id="email" type="text" class="" name="email" value="${user.email}" onfocus="Materialize.toast('请输入您的有效邮箱，用于激活您的账户', 4000,'rounded')" onblur="emailNULL(),checkEmailExisted()" >
                       <label for="email">Email</label>
                     </div>
                   </div>
                      <div class="row">
                        <div class="input-field col s12">
                         <input id="userName" type="text" class="" name="userName" value="${user.userName}" onfocus="Materialize.toast('请输入用户名，可以为汉字、字母、数字组成的字符串', 4000,'rounded')" onblur="userNameNULL(),checkUserNameExisted()">
                         <label for="userName">User Name</label>
                        </div>
                     </div>
                     
                     <div class="row">
                       <div class="input-field col s12">
                         <input id="password" type="password" class="" name="password" value="${user.password}" onfocus="Materialize.toast('请注意保护您的密码', 4000,'rounded')" >
                         <label for="password">Password</label>
                       </div>
                   </div>
                   
                   <div class="row">
                       <div class="input-field col s12">
                         <input id="password_confirm" type="password" class="" onfocus="Materialize.toast('请再次输入您的密码', 4000,'rounded')" onblur="validatePasswordEqual()">
                         <label for="password_confirm">Confirm Password</label>
                       </div>
                   </div>
            
                  <div class="row">
                    <div class="col s1"></div>
                    <div class="col s10">
                      <div id="embed-captcha"></div> 
                      <p id="wait" class="show">正在加载验证码</p>
                      <p id="notice" class="hide">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请先拖动验证码到指定位置</p>
                    </div>
                    <div class="col s1"></div>
                  </div>
                  
                  <div class="row">
                    <div class="col s12">
                      <button class="btn waves-effect waves-light button-self" type="submit" id="embed-submit">
                        注册
                      </button>
                    </div>
                  </div>
                 </form>
               </div>
             </div>
             <div class="col s1"></div>
           </div>  
         </div>  <!-- s6结束 -->
         <div class="col s2"></div>
         <div class="col s1"></div>
       </div>
       
       <div class="row">
         <div class="col s1"></div>
         <div class="col s2"></div>
         <div class="col s6">
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
                        <a href="${pageContext.request.contextPath }/sign_in">已有账号?直接登录</a>
                      </div>
                    </div>
                      <div class="col s2"></div>
                    </div>
                  </div>
                  <div class="col s2"></div>
                </div>
             </div>
            <div class="col s1"></div>
            <div class="col s2"></div>
           </div>  <!-- row结束 -->
         </div>
           
       </div>   <!-- row结束 -->      
     </div>  
     <div class="col s4"></div>
   </div>
   
   <div class="footer">  <!-- 页脚 -->
      <div class="footer-content">
         <div class="footer-content-1">     <!-- 第一部分内容 -->
           <h5><a href="#">产品</a></h5>
           <ul>
             <li><a href="#">敏捷BI</a></li>
             <li><a href="#">MPP&nbsp;数据集市</a></li>
             <li><a href="#">深度分析</a></li>
           </ul>
         </div>
         
         <div class="footer-content-1">     <!-- 第二部分内容 -->
           <h5><a href="#">解决方案</a></h5>
           <ul>
             <li><a href="#">金融行业</a></li>
             <li><a href="#">电信行业</a></li>
             <li><a href="#">零售行业</a></li>
             <li><a href="#">医疗行业</a></li>
             <li><a href="#">高教行业</a></li>
             <li><a href="#">电商行业</a></li>
             <li><a href="#">交通行业</a></li>
           </ul>
         </div>   <!-- 第二部分结束 -->
         
         <div class="footer-content-1">     <!-- 第三部分内容 -->
           <h5><a href="#">案例</a></h5>
           <ul>
             <li><a href="#">金融行业</a></li>
             <li><a href="#">电信行业</a></li>
             <li><a href="#">能源行业</a></li>
             <li><a href="#">电商行业</a></li>
             <li><a href="#">互联网行业</a></li>
           </ul>
         </div>   <!-- 第三部分结束 -->
         
         <div class="footer-content-1">     <!-- 第四部分内容 -->
           <h5><a href="#">合作</a></h5>
           <ul>
             <li><a href="#">渠道代理</a></li>
           </ul>
         </div>   <!-- 第四部分结束 -->
         
         <div class="footer-content-1">     <!-- 第五部分内容 -->
           <h5><a href="#">支持</a></h5>
           <ul>
             <li><a href="#">授权验证</a></li>
             <li><a href="#">常见问题</a></li>
             <li><a href="#">产品使用文档</a></li>
             <li><a href="#">产品白皮书</a></li>
             <li><a href="#">在线学习</a></li>
             <li><a href="#">支持服务</a></li>
             <li><a href="#">观看演示</a></li>
           </ul>
         </div>   <!-- 第五部分结束 -->
         
         <div class="footer-content-1">     <!-- 第五部分内容 -->
           <h5><a href="#">关于</a></h5>
           <ul>
             <li><a href="#">联系我们</a></li>
             <li><a href="#">工作机会</a></li>
             <li><a href="#">媒体报道</a></li>
             <li><a href="#">新闻与活动</a></li>
             <li><a href="#">管理团队</a></li>
             <li><a href="#">公司介绍</a></li>
           </ul>
         </div>   <!-- 第五部分结束 -->
      </div>
      
      <div style="clear:both"></div>   <!-- 采用外墙法将之前的浮动全部清除 -->
      <div class="footer-content" style="margin-top :20px; color: #fff;">Copyright  &copy; 2016-2017 贵州贵阳优联博睿科技有限公司</div>
      </div>   <!-- 页脚结束 -->
      
      
      <script type="text/javascript">
         $(".backtop").click(function(){
	         $("html,body").animate({
		        "scrollTop" : 0
	            },1000);
         })
      </script>

  </body>
</html>
