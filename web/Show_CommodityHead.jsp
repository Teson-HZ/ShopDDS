<%-- 
    Document   : Show_Commodity
    Created on : 2024-5-16, 18:43:19
    Author     : WHZ2002
--%>


<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>所有商品信息</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }
        h1 {
            text-align: center;
            color: #4a4a4a;
            margin-top: 30px;
            font-size: 2em;
        }
        table {
            width: 80%;
            margin: 30px auto;
            border-collapse: collapse;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: #ffffff;
        }
        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #dddddd;
        }
        th {
            background-color: #f7f7f7;
            color: #5b5b5b;
            font-weight: bold;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
            cursor: pointer;
            transform: scale(1.02);
            transition: transform 0.2s;
        }
        .footer {
            text-align: center;
            padding: 20px;
            color: #6d6d6d;
        }
        .return-button {
            display: inline-block;
            padding: 10px 20px;
            margin-top: 20px;
            background-color: #4CAF50;
            color: #ffffff;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .return-button:hover {
            background-color: #45a049;
        }
        .center-btn {
            text-align: center;
        }
    </style>
</head>
<body>
    <%-- 获取CommodityAllList属性中的数据 --%>
    <c:set var="app" value="${CommodityAllList}" />

    <h1>商品所有信息</h1>
    <table>
        <thead>
            <tr>
                <th>商品编号</th>
                <th>商品条码</th>
                <th>商品名</th>
                <th>商品类别</th>
                <th>计量单位</th>
                <th>商品单价</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="commodity" items="${app}">
                <tr>
                    <td>${commodity.commodity_Id}</td>
                    <td>${commodity.commodity_Barcode}</td>
                    <td>${commodity.commodity_Name}</td>
                    <td>${commodity.commodity_Class}</td>
                    <td>${commodity.commodity_Unit}</td>
                    <td>${commodity.commodity_Price}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="center-btn">
        <button class="return-button" onclick="window.location.href = 'HeadadminMainPage.jsp'">返回</button>
    </div>
    <div class="footer">
        © 2024 物资分配调拨决策支持系统
    </div>
</body>
</html>
