<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 在一个jsp页面中引入公共的jsp页面 -->
<%@include file="/WEB-INF/views/common/header/header.jsp" %>
<!DOCTYPE html>
<html>
  <head>
     <link rel="stylesheet" href="${pageContext.request.contextPath }/css/ms/login.css">
     <script type="text/javascript" src="${pageContext.request.contextPath}/utils/jquery/jquery-3.1.0.min.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath }/js/ms/login.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath }/utils/materialize/js/materialize.min.js"></script>
     <link rel="stylesheet" href="${pageContext.request.contextPath }/utils/font-awesome/css/font-awesome.min.css">
     <link rel="stylesheet" href="${pageContext.request.contextPath }/css/common/footer/footer.css">
     <title>登录</title>
  </head>  
  <body>
  <a class="backtop"><img src="${pageContext.request.contextPath }/img/ms/topback.gif"></a>
  <div class="row bg">
    <div class="col s3"></div>   <!-- 左侧部分占整个屏幕的三份 -->
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
	                <form class="col s12" action="${pageContext.request.contextPath}/ms/login/Login" method="POST" id="loginForm">
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
                     <a href="#" style="text-decoration: none">忘记密码?</a>
                  </div>
                  <div class="col s6">
                     <button class="btn waves-effect waves-light button-self" type="submit" name="action" onclick="doSubmitLogin()">
                       登录
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
                     <a href="${pageContext.request.contextPath }/sign_up" style="text-decoration: none">没有账户？立即创建一个</a>
                  </div>
               </div>
           </div>
           <div class="col s1"></div>   <!-- 占位用，在实际中不使用 -->
         </div>
        
         </div>
         
       </div> 
       
     </div>
    </div>
    <div class="col s3"></div>   <!-- 右侧占满三份 -->
    </div>
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

    <!-- 增加返回顶部，之后将其换成图片 -->
	<script type="text/javascript">
		$(".backtop").click(function() {
			$("html,body").animate({
				"scrollTop" : 0
			}, 1000);
		})
	</script>
</body>
</html>
