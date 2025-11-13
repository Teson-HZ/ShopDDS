<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Selection</title>
<style>
    body {
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
        background-image: url('images/main1.png'); /* 替换为实际图片文件路径 */
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        text-align: center;
    }

    .content-container {
        background: rgba(255, 255, 255, 0.6); /* 设置半透明白色背景 */
        padding: 40px; /* 内边距 */
        border-radius: 15px; /* 边框圆角 */
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3); /* 盒子阴影，使其更具立体感 */
        max-width: 400px; /* 最大宽度限制 */
    }

    h1 {
        font-size: 50px;
        color: #ADFF2F; /* 设置标题颜色为白灰色 #ADFF2F*/
        margin-bottom: 60px;
        text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
    }

    /* 其他样式保持不变... */
    .button-container {
        display: flex;
        flex-direction: column;
        gap: 20px;
    }

    .login-button {
        padding: 15px 30px;
        font-size: 20px;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    .admin-button {
        background-color: #007bff;
    }

    .admin-button:hover {
        background-color: #0056b3;
    }

    .doctor-button {
        background-color: #28a745;
    }

    .doctor-button:hover {
        background-color: #1e7e34;
    }

    .user-button {
        background-color: #ffc107;
    }

    .user-button:hover {
        background-color: #e0a800;
    }
</style>
</head>
<body>
    <div class="content-container">
        <h1>连锁超市物资分配调拨DDS</h1>
        <div class="button-container">
            <button class="login-button admin-button" onclick="window.location.href='HeadadminLogin.jsp';">我是总部管理员</button>
            <button class="login-button doctor-button" onclick="redirectToLogin('仓库管理员');">我是仓库管理员</button>
            <button class="login-button user-button" onclick="redirectToLogin('超市管理员');">我是超市管理员</button>
        </div>
    </div>
<script>
    // 如果需要在此处添加JavaScript代码，可以在这里添加
    function redirectToLogin(role) {
    let baseUrl = 'EmployeeLogin.jsp';
    // 对role进行URL编码
    let encodedRole = encodeURIComponent(role);
    let urlWithParam = baseUrl + '?role=' + encodedRole;
    window.location.href = urlWithParam;
}
</script>
</body>
</html>
