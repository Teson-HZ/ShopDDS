<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>仓库/超市管理员登录平台</title>
  <style>
  .msg{
            display:inline-block;
            color: #d90d24;
            height: 30px;
            line-height: 40px;
            font-weight: bold;
        }
        
  /* 新增样式开始 */
    .login-container {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-start; /* 设置为flex-start使内容靠上对齐 */
        min-height: 100vh;
        padding-top:3%; /* 可根据需要调整这个值 */
      }    
   
    .login-title {
      font-size: 24px;
      margin-bottom: 20px;
      font-weight: bold;
    }

    .demo-login-container {
      width: 320px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
      padding: 20px;
      border-radius: 5px;
      background-color: #fff;
    }
    /* 新增样式结束 */
        body {
        background-image: url('images/bak.jpg'); /* 设置背景图片路径 */
        background-size: cover; /* 背景图片覆盖整个元素 */
        background-position: center; /* 背景图片居中显示 */
        background-attachment: fixed; /* 背景图片固定，不随滚动条滚动 */
    }

    .login-container {
        background-color: rgba(255, 255, 255, 0.5); /* 可以设置半透明的白色背景，以便表单更加清晰 */
    }
    
    
  </style>
  <link href="//unpkg.com/layui@2.8.18/dist/css/layui.css" rel="stylesheet">
</head>
<body>
<style>
.demo-login-container{width: 320px; margin: 21px auto 0;}
.demo-login-other .layui-icon{position: relative; display: inline-block; margin: 0 2px; top: 2px; font-size: 26px;}
</style>
<div class="login-container">
 <div class="login-title">仓库/超市管理员信息平台</div> <!-- 新增标题 -->
 <form class="layui-form demo-login-container" action="EmployeeLoginServlet" method="post">
     <input type="hidden" name="role" value="<%=request.getParameter("role")%>">
  <div class="demo-login-container">
    <div class="layui-form-item">
      <div class="layui-input-wrap">
        <div class="layui-input-prefix">
          <i class="layui-icon layui-icon-username"></i>
        </div>
         
        <input type="text" name="employee_Id" value="" lay-verify="required" placeholder="员工账户" lay-reqtext="请填写账号" autocomplete="off" class="layui-input" lay-affix="clear">
      </div>
    </div>
    <div class="layui-form-item">
      <div class="layui-input-wrap">
        <div class="layui-input-prefix">
          <i class="layui-icon layui-icon-password"></i>
        </div>
        <input type="password" name="employee_Pwd" value="" lay-verify="required" placeholder="密   码" lay-reqtext="请填写密码" autocomplete="off" class="layui-input" lay-affix="eye">
      </div>
    </div>
    
    <div class="layui-form-item">
      <div class="layui-row">
        <div class="layui-col-xs7">
          <div class="layui-input-wrap">
            <div class="layui-input-prefix">
              <i class="layui-icon layui-icon-vercode"></i>
            </div>
            <input type="text" name="captcha" value="" lay-verify="required" placeholder="验证码" lay-reqtext="请填写验证码" autocomplete="off" class="layui-input" lay-affix="clear">
          </div>
        </div>
        <div class="layui-col-xs5">
          <div style="margin-left: 10px;">
            <img src="CaptchaServlet" onclick="this.src='CaptchaServlet?t='+ new Date().getTime();" title="看不清，点击换一张">
          </div>
        </div>
      </div>
    </div>
      
    <div class="layui-form-item">
      <button class="layui-btn layui-btn-fluid" type="submit">登录</button>
    </div>
    <div class="layui-form-item demo-login-other">
       <p>若无账户？请联系总部管理员添加！</p>
    </div>
    <span class="msg">${msg}</span>      
  </div>
</form>
</div>
<!-- 请勿在项目正式环境中引用该 layui.js 地址 -->
<script src="//unpkg.com/layui@2.8.18/dist/layui.js"></script>
<script>

</script>
</body>
</html>