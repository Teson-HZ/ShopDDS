<%-- 
    Document   : SupermarketMainPage
    Created on : 2024-3-15, 17:21:27
    Author     : WHZ2002
--%>


<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        #tb{
            border-collapse: collapse;
        }
        #tb th,#tb td{
            border:1px solid #c8d1d3;
            padding: 5px 10px;
        }
        
        h1 {
        font-size: 24px;
        font-weight: bold;
        text-align: center;
        margin-bottom: 20px;
        }
    
        p {
            font-size: 18px;
            margin-bottom: 10px;
            color: #333333; /*设置段落字体颜色*/
            text-indent: 2em; /*设置段落开头空两个字符*/
        }
        
    </style>
    
    
    
  <meta charset="utf-8">
  <title>超市主界面栏</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 引入 layui.css -->
  <link href="//unpkg.com/layui@2.8.18/dist/css/layui.css" rel="stylesheet"  media="all">
  <!-- 在<head>标签内添加 --> 
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo layui-hide-xs layui-bg-black">超市功能栏</div>
    <!-- 头部区域（可配合layui 已有的水平导航） -->
    <ul class="layui-nav layui-layout-left" lay-filter="navFilter">
      <!-- 移动端显示 -->
      <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-header-event="menuLeft">
        <i class="layui-icon layui-icon-spread-left"></i>
      </li>
      <li class="layui-nav-item layui-hide-xs"><a href="javascript:;">系统信息功能说明</a></li>
      <li class="layui-nav-item layui-hide-xs"><a href="javascript:;">通知公告</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">部门职责</a>
        <dl class="layui-nav-child">
          <dd><a href="javascript:;">总部职责</a></dd>
          <dd><a href="javascript:;">仓库职责</a></dd>
          <dd><a href="javascript:;">超市职责</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item layui-hide-xs"><a href="javascript:;">返回首页</a></li>
    </ul>
    <ul class="layui-nav layui-layout-right" lay-filter="uppwd">
      <!-- 添加显示日期和时间的元素 -->
      <li class="layui-nav-item layui-hide-xs" id="datetime-display" style="color: white; margin-right: 20px;"></li>
      <li class="layui-nav-item" style="color: #00FFFF;">${sessionScope.supermarketName}</li>
      <li class="layui-nav-item layui-hide layui-show-sm-inline-block">
        <a href="javascript:;">
          <img src="//unpkg.com/outeres@0.0.10/img/layui/icon-v2.png" class="layui-nav-img">
          您好，${sessionScope.helloemployee}
        </a>
        <dl class="layui-nav-child">
          <dd><a href="javascript:;">修改密码</a></dd>
        </dl>
      </li>
    </ul>
  </div>
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree" lay-filter="test">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;">商品信息查询</a>
        </li>
        <li class="layui-nav-item layui-nav-itemed">
          <a href="javascript:;">提交需求申请</a>
        </li>
        <li class="layui-nav-item layui-nav-itemed">
          <a href="javascript:;">查询调拨结果</a>
        </li>
        <li class="layui-nav-item"><a href="login_main.jsp">退出系统</a></li>
      </ul>
    </div>
  </div>
    
    
<!-- 内容主体区域 -->
  <div class="layui-body">
    <div style="padding: 15px;">
        
            <!-- 默认显示的主页内容 -->
    
    <div id="homePage" class="layui-tab-item layui-show" style="background: linear-gradient(135deg, #7F7FD5, #86A8E7, #91EAE4); padding: 40px; border-radius: 15px; box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2), 0 6px 20px 0 rgba(0,0,0,0.19); transition: all 0.3s;">
    <div class="layui-card-body" style="max-width: 800px; margin: auto; background: linear-gradient(to right, #ffffff, #f0f8ff); padding: 20px; border-radius: 10px; box-shadow: 0 4px 8px rgba(0,0,0,0.1), inset 0 0 10px rgba(0,0,0,0.1); border: 1px solid #ccc;">
        <!-- 欢迎信息与简介 -->
        <h2 style="color:#3A3D98; font-weight:600; text-align:center; margin-bottom: 20px; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; text-shadow: 0 2px 4px rgba(0,0,0,0.1);">
            欢迎来到物资分配调拨决策支持系统
        </h2>
        <p style="text-align:justify; font-size:16px; line-height:1.8; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; color: #555;">
            我们的系统旨在为连锁超市提供高效、精确的物资分配与调拨解决方案。通过实时数据分析，帮助您优化库存管理，减少浪费，提升供应链效率。
        </p>

        <!-- 系统背景介绍 -->
        <div style="margin-top:20px;">
            <h3 style="color:#3A3D98; font-weight:600; margin-bottom: 15px; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; text-shadow: 0 1px 3px rgba(0,0,0,0.1);">
                系统背景
            </h3>
            <p style="text-align:justify; font-size:16px; line-height:1.8; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; color: #555;">
                连锁超市集团包括一个管理总部、若干超市门店和若干仓库，它们分别处于城市的不同地段。物资分配调拨是根据各超市向总部提出的物资需求申请（物资种类、数量等），总部按各仓库的库存情况制定分配方案；根据分配方案以及各仓库与各超市的距离，制定物资运输方案。按照物资运输方案制定各仓库的发物表和各超市的接收表，同时修改个仓库库存数和各超市的物资数。
            </p>
        </div>

        <!-- 更多系统细节介绍 -->
        <div style="margin-top:20px;">
            <h3 style="color:#3A3D98; font-weight:600; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; text-shadow: 0 1px 3px rgba(0,0,0,0.1);">
                如何实现高效物资分配
            </h3>
            <p style="text-align:justify; font-size:16px; line-height:1.8; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; color: #555;">
                我们的系统通过综合考虑物资需求、库存水平、距离因素等多种因素，采用先进的数据分析和优化算法，为每次调拨决策提供科学依据。确保物资分配既高效又经济，减少运输成本，缩短供应时间。
            </p>
        </div>

        <!-- 底部版权信息 -->
        <div style="margin-top:50px; text-align:center; font-size:14px; color:#999; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">
            ©2024 物资分配调拨决策支持系统 - 保留所有权利
        </div>
    </div>
</div>

    
  <!-- 系统信息功能说明 -->
<div id="hospitalInfo" class="layui-tab-item layui-card layui-panel" style="margin-top: 20px; background: #ffffff; border-radius: 15px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
    <div class="layui-card-header" style="background: linear-gradient(135deg, #7F7FD5, #86A8E7, #91EAE4); border-radius: 15px 15px 0 0; color: white; font-size: 18px; font-weight: bold; padding: 20px;">
        系统信息功能说明
    </div>
    <div class="layui-card-body" style="padding: 20px; font-size: 14px; color: #333; background-color: #F5F7FA; line-height: 1.6;">
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（1）登录验证功能</h2>
            <p>
                系统获取员工或管理员输入的用户名与密码，进行登录验证，验证过程会与数据库中的管理员信息表或员工信息表进行匹配，若匹配信息正确，则会分别进入到三种身份的系统主页面，主页导航栏会有“欢迎您，姓名”字样，说明系统登录成功；若用户名与密码匹配不成功，则无法进行主界面显示，并将在登录进行提示具体的错误信息，让用户进行重新输入。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（2）商品信息查询功能</h2>
            <p>
                在主功能界面栏中有“商品信息查询”字样，点击后在主页面中出现查询按钮，本系统将实现多种查询，包含通过商品条码查询，通过商品名称查询和显示全部商品信息的功能。用户点击后，servlet将获取查询参数，并提交后台数据库，获取到数据库的查询信息后提交给系统新页面进行所查询商品信息的显示。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（3）商品申请功能</h2>
            <p>
                超市管理员在超市系统主界面中选择“商品申请”，根据所查询的商品信息，输入要申请的商品编号与申请数量后，提交给servlet，随后将所申请信息存储到数据库中的超市申请表中。同时申请后会提示用户交互信息，用户可以得知提交成功与提交失败。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（4）仓库库存提交功能</h2>
            <p>
                仓库管理员在仓库系统主界面中选择“库存量提交”，随后系统获取仓库管理员填写的当前物资的编号、物资的实际库存量、物资最低储备量信息后，后端servlet会获取提交的信息，并将所提交信息存储到数据库中的库存信息表中。提交后会弹出交互信息以便用户可以得知库存量是否被成功提交。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（5）总部管理功能</h2>
            <p>
                总部有着最高权限，本系统设定总部可以对超市信息、仓库信息、商品信息、员工信息与管理员信息进行管理，即可以对其进行增加，删除，查找，修改。该功能需要获取相关的信息表，提供查找操作。同时，增加、删除、修改功能也需对对应的信息表进行更新。同样，功能操作后会弹出交互信息，以便用户得知是否该功能成功被执行。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（6）汇总功能</h2>
            <p>
                总部可以实现对各个超市提交的申请表和各个仓库所提交的库存表进行汇总。汇总时将读取各个仓库和超市的库存表与申请表，对各个超市所申请的商品按商品号进行加和计算；同样，对各个仓库所提交的库存表以商品号为单位进行计算各类商品的库存综合，随后将计算结果分别计入到总申请表与总库存表中进行存储。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（7）物资分配功能</h2>
            <p>
                总部具有对物资进行分配的功能。该模块分为分配处理与人工干预两个模块。分配处理需要总部分别读取物资总申请表与物资总库存表，随后进行分配处理。处理有两种方式：第一种为当商品可分配数量大于等于总申请量时，直接进行分配；第二种方式为当商品可分配数量小于商品的总需求量时，则按申请比例整体缩减进行分配。随后输出分配结果，将分配结果存储到物资分配表中。人工干预模块主要对物资分配表进行任何形式的干预修改，干预过程为读取物资分配表，随后进行干预修改，最后对物资分配表进行更新。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（8）分配表查询功能</h2>
            <p>
                总部有权限查看物资分配后产生的物资分配表，在主界面功能栏中，总部管理员可以点击“分配表查询”功能，随后进行查询，查询需要读取数据库中的物资分配信息，随后将数据提交到新的jsp页面进行显示。查询结果将弹出新的界面进行显示。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（9）添加距离信息</h2>
            <p>
                总部可以在主界面中选择“添加距离”，会弹出添加距离的页面。会输入超市编号，仓库编号，最后输入它们之间的距离。后端servlet读取到相关信息后，会将数据信息存储到距离表中，以便物资调拨时使用。当用户提交后，会弹出交互信息，以便用户可以得知本次提交是否成功。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（10）物资调拨功能</h2>
            <p>
                总部具有对物资进行最优调拨的功能。该功能模块分配调拨处理和人工干预修改两个部分。调拨处理需要系统读取超市仓库距离表和分配表，系统通过运输问题进行求解，调拨结果将存储到物资调拨表当中。总部管理员可以对物资调拨结果进行干预，即任意对调拨结果表进行干预修改。修改后更新物资调拨表。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（11）调拨结果查询</h2>
            <p>
                总部可以查询物资调拨结果。当总部管理员点击“调拨结果查询”按键后，系统将从数据库中读取物资调拨表，随后将该数据信息传递到新的jsp页面进行数据展示。超市和仓库也可以查询物资调拨结果，其与总部不同，超市和仓库在查询时多了一个筛选条件，即根据自身超市或仓库的编号进行查询，这样查询生成的结果就是针对本超市或本仓库的超市收物表或仓库发物表。
            </p>
        </div>
    </div>
</div>


    <!-- 通知公告 -->
<div id="notices" class="layui-tab-item layui-card layui-panel" style="margin-top: 20px; background: #ffffff; border-radius: 15px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
    <div class="layui-card-header" style="background: linear-gradient(135deg, #7F7FD5, #86A8E7, #91EAE4); border-radius: 15px 15px 0 0; color: white; font-size: 20px; font-weight: bold; padding: 20px;">
        通知公告
    </div>
    <div class="layui-card-body" style="padding: 20px;">
        <div class="notice-item" style="background: #f9f9f9; padding: 15px; border-radius: 10px; margin-bottom: 10px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
            <h4 style="color: #3A3D98; font-weight: 600; font-size: 18px; margin-bottom: 10px;">系统维护公告</h4>
            <p style="color: #555; font-size: 16px; line-height: 1.8;">
                为了提升系统性能，我们计划于2024年6月1日凌晨2:00至4:00进行系统维护。期间系统将暂时无法访问，请您提前做好准备。由此带来的不便，我们深表歉意。
            </p>
        </div>
        <div class="notice-item" style="background: #f9f9f9; padding: 15px; border-radius: 10px; margin-bottom: 10px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
            <h4 style="color: #3A3D98; font-weight: 600; font-size: 18px; margin-bottom: 10px;">新增功能发布</h4>
            <p style="color: #555; font-size: 16px; line-height: 1.8;">
                我们很高兴地宣布，系统已新增自动库存预警功能。该功能将帮助您在库存过低或过高时自动发出提醒，确保库存管理更加高效。详情请参阅使用手册中的“自动预警”部分。
            </p>
        </div>
        <div class="notice-item" style="background: #f9f9f9; padding: 15px; border-radius: 10px; margin-bottom: 10px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
            <h4 style="color: #3A3D98; font-weight: 600; font-size: 18px; margin-bottom: 10px;">培训通知</h4>
            <p style="color: #555; font-size: 16px; line-height: 1.8;">
                为帮助用户更好地掌握系统使用，我们将在2024年6月5日举办线上培训活动。培训将涵盖系统基本操作、新功能介绍及常见问题解答。请各位用户提前报名参加。
            </p>
        </div>
        <div class="notice-item" style="background: #f9f9f9; padding: 15px; border-radius: 10px; margin-bottom: 10px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
            <h4 style="color: #3A3D98; font-weight: 600; font-size: 18px; margin-bottom: 10px;">假期工作安排</h4>
            <p style="color: #555; font-size: 16px; line-height: 1.8;">
                端午节假期将至，系统将于假期期间正常运行，但技术支持团队将提供有限支持。如遇紧急问题，请通过系统内的紧急联系通道获取帮助。
            </p>
        </div>
        <div style="text-align: center; color: #999; margin-top: 20px;">
            更多公告内容待更新
        </div>
    </div>
</div>


    <!-- 总部职责 -->
<div id="a2" class="layui-tab-item layui-card layui-panel" style="margin-top: 20px; background: #ffffff; border-radius: 15px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
    <div class="layui-card-header" style="background: linear-gradient(135deg, #3E4A89, #4F75C2, #5B9BD5); border-radius: 15px 15px 0 0; color: white; font-size: 18px; font-weight: bold; padding: 20px; text-align: center;">
        总部职责
    </div>
    <div class="layui-card-body" style="padding: 20px; font-size: 14px; color: #333; background-color: #F7F9FC; line-height: 1.6;">
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（1）管理员管理</h2>
            <p>
                获取管理员的基础信息，对管理员信息进行增删查改操作并存储。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（2）员工管理</h2>
            <p>
                获取员工的基础信息，可对员工信息进行增删查改操作并存储。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（3）商品信息管理</h2>
            <p>
                获取商品的基本信息，可对商品信息进行增删查改操作并存储。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（4）超市管理</h2>
            <p>
                获取超市的基本信息，可对超市信息进行增删查改操作并存储。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（5）仓库管理</h2>
            <p>
                获取仓库的基本信息，可对仓库信息进行增删查改操作并存储。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（6）添加距离</h2>
            <p>
                获取距离的信息表后添加超市仓库距离信息并存储。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（7）汇总</h2>
            <p>
                获取物资申请表信息和仓库库存表信息。汇总后生成总申请信息进行存储，同时生成总存储信息进行汇总。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（8）分配表查询</h2>
            <p>
                从数据库获取分配表信息并显示。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（9）物资分配</h2>
            <p>
                获取总申请表、总库存表信息，做出分配结果并进行存储，分配结果需要进行人工干预后形成物资分配表来进行最终的分配，最后更新物资分配表。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（10）物资调拨</h2>
            <p>
                获取距离信息和分配信息后进行处理，得出调拨结果，调拨结果也需要经过人工干预来形成最终的物资调拨表进行调拨，最后更新物资调拨表。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #3A3D98; font-weight: 600; font-size: 16px;">（11）调拨结果的查询</h2>
            <p>
                获取调拨结果，并对调拨结果信息进行显示。
            </p>
        </div>
    </div>
</div>

    
    <!-- 仓库职责 -->
<div id="a3" class="layui-tab-item layui-card layui-panel" style="margin-top: 20px; background: #ffffff; border-radius: 15px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
    <div class="layui-card-header" style="background: linear-gradient(135deg, #2C3E50, #4CA1AF); border-radius: 15px 15px 0 0; color: white; font-size: 18px; font-weight: bold; padding: 20px; text-align: center;">
        仓库职责
    </div>
    <div class="layui-card-body" style="padding: 20px; font-size: 14px; color: #333; background-color: #F7F9FC; line-height: 1.6;">
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #2C3E50; font-weight: 600; font-size: 16px;">（1）查询商品信息</h2>
            <p>
                仓库管理员可以通过系统查询商品的基本信息，包括商品名称、规格类型、定价等详细信息，确保仓库管理的有效性和准确性。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #2C3E50; font-weight: 600; font-size: 16px;">（2）提交当前的库存信息</h2>
            <p>
                仓库管理员需要定期提交当前库存信息，包括物资编号、实际库存量和最低储备量等。提交的信息将存储在数据库中，以便总部进行统一管理和调配。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #2C3E50; font-weight: 600; font-size: 16px;">（3）查询调拨结果</h2>
            <p>
                仓库管理员可以查询物资调拨结果，系统将根据分配表和调拨表展示调拨结果，仓库管理员可根据查询结果进行物资接收和发放操作，确保物资调拨的顺利进行。
            </p>
        </div>
    </div>
</div>

    
    <!-- 超市职责 -->
<div id="a4" class="layui-tab-item layui-card layui-panel" style="margin-top: 20px; background: #ffffff; border-radius: 15px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
    <div class="layui-card-header" style="background: linear-gradient(135deg, #2D3748, #4A5568); border-radius: 15px 15px 0 0; color: white; font-size: 18px; font-weight: bold; padding: 20px; text-align: center;">
        超市职责
    </div>
    <div class="layui-card-body" style="padding: 20px; font-size: 14px; color: #333; background-color: #F7F9FC; line-height: 1.6;">
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #2D3748; font-weight: 600; font-size: 16px;">（1）查询商品信息</h2>
            <p>
                超市管理员可以通过系统查询商品的基本信息，包括商品名称、规格、价格等详细信息，确保超市运营的有效性和准确性。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #2D3748; font-weight: 600; font-size: 16px;">（2）提交商品需求</h2>
            <p>
                超市管理员需要定期提交商品需求信息，包括商品名称、需求数量和时间等。提交的信息将存储在数据库中，以便总部进行统一管理和调配。
            </p>
        </div>
        <div style="margin-bottom: 20px; border: 1px solid #E0E0E0; padding: 20px; border-radius: 10px; background-color: #FFFFFF;">
            <h2 style="color: #2D3748; font-weight: 600; font-size: 16px;">（3）查询调拨结果</h2>
            <p>
                超市管理员可以查询商品调拨结果，系统将根据分配表和调拨表展示调拨结果，超市管理员可根据查询结果进行商品接收和上架操作，确保商品调拨的顺利进行。
            </p>
        </div>
    </div>
</div>
    
<div id="l1" class="layui-tab-item layui-card layui-panel" style="margin-top: 20px; background: #ffffff; border-radius: 15px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
    <div class="layui-card-header" style="background: linear-gradient(135deg, #FF512F, #DD2476); border-radius: 15px 15px 0 0; color: white; font-size: 18px; font-weight: bold; padding: 20px; text-align: center;">
        查看所有商品信息
    </div>
    <div class="layui-card-body" style="padding: 20px; font-size: 14px; color: #333; background-color: #F7F9FC; line-height: 1.6;">
        <form class="layui-form layui-form-pane" action="SelectCommodityServlet" method="post">
            <div class="layui-form-item" style="text-align: center;">
                <button class="layui-btn" type="submit" style="background: linear-gradient(135deg, #FF512F, #DD2476); border-radius: 10px; border: none;">显示商品信息</button>
            </div>
        </form>
    </div>
</div>

    
<div id="l2" class="layui-tab-item layui-card layui-panel" style="margin-top: 20px; background: #ffffff; border-radius: 15px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
    <div class="layui-card-header" style="background: linear-gradient(135deg, #FF5F6D, #FFC371); border-radius: 15px 15px 0 0; color: white; font-size: 18px; font-weight: bold; padding: 20px; text-align: center;">
        提交商品需求信息
    </div>
    <div class="layui-card-body" style="padding: 20px; font-size: 14px; color: #333; background-color: #F7F9FC; line-height: 1.6;">
        <form class="layui-form layui-form-pane" action="AddAplServlet" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label" style="white-space: nowrap; overflow: hidden; text-overflow: clip; width: 160px;">请选择商品</label>
                <div class="layui-input-inline">
                    <select id="commodityid" name="commodity_Id" lay-verify="required" lay-filter="commodityid" class="layui-input" style="border-radius: 10px; border: 1px solid #E0E0E0;">
                        <option value=""></option>
                    </select>
                </div>
            </div>
            
            <div class="layui-form-item">
                <label class="layui-form-label" style="white-space: nowrap; overflow: hidden; text-overflow: clip; width: 160px;">请输入申请数量</label>
                <div class="layui-input-inline">
                    <input type="text" name="apl_Amount" autocomplete="off" placeholder="请输入数量" lay-verify="required" class="layui-input" style="border-radius: 10px; border: 1px solid #E0E0E0;">
                </div>
            </div>

            <div class="layui-form-item" style="text-align: center;">
                <button class="layui-btn" type="submit" style="background: linear-gradient(135deg, #FF5F6D, #FFC371); border-radius: 10px; border: none;">提交</button>
                <button type="reset" class="layui-btn layui-btn-primary" style="border-radius: 10px; border: 1px solid #E0E0E0;">重置</button>
            </div>
        </form>
        <%
            if (request.getAttribute("addapl") != null) {
                String addapl = (String) request.getAttribute("addapl");
                if (!addapl.isEmpty()) { // 只有在message不为空字符串时才弹出提示
                    %>
                    <script>
                        alert('<%= addapl %>');
                    </script>
                    <%
                    request.setAttribute("addapl", ""); // 提交表单后将message的值设置为空字符串
                }
            }
        %>
    </div>
</div>


    
<div id="l4" class="layui-tab-item layui-card layui-panel" style="margin-top: 20px; background: #ffffff; border-radius: 15px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
    <div class="layui-card-header" style="background: linear-gradient(135deg, #36D1DC, #5B86E5); border-radius: 15px 15px 0 0; color: white; font-size: 18px; font-weight: bold; padding: 20px; text-align: center;">
        查看本超市收物表
    </div>
    <div class="layui-card-body" style="padding: 20px; font-size: 14px; color: #333; background-color: #F7F9FC; line-height: 1.6;">
        <form class="layui-form layui-form-pane" action="SelectAcceptServlet" method="post">
            <div class="layui-form-item" style="text-align: center;">
                <button class="layui-btn" type="submit" style="background: linear-gradient(135deg, #36D1DC, #5B86E5); border-radius: 10px; border: none;">点击查询</button>
            </div>
        </form>
    </div>
</div>

      
<div id="l5" class="layui-tab-item layui-card layui-panel" style="margin-top: 20px; background: #ffffff; border-radius: 15px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);">
    <div class="layui-card-header" style="background: linear-gradient(135deg, #FF5722, #FFC107); border-radius: 15px 15px 0 0; color: white; font-size: 18px; font-weight: bold; padding: 20px; text-align: center;">
        修改个人密码
    </div>
    <div class="layui-card-body" style="padding: 20px; font-size: 14px; color: #333; background-color: #F7F9FC; line-height: 1.6;">
        <form class="layui-form layui-form-pane" action="UpdatePwd1Servlet" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label" style="white-space: nowrap; overflow: hidden; text-overflow: clip; width: 160px;">请输入原密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="SoursePwd" autocomplete="off" placeholder="请输入原密码" lay-verify="required" class="layui-input" style="border-radius: 10px; border: 1px solid #E0E0E0;">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="white-space: nowrap; overflow: hidden; text-overflow: clip; width: 160px;">请输入新密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="NewPwd" autocomplete="off" placeholder="请输入新密码" lay-verify="required" class="layui-input" style="border-radius: 10px; border: 1px solid #E0E0E0;">
                </div>
            </div>
            <div class="layui-form-item" style="text-align: center;">
                <button class="layui-btn" type="submit" style="background: linear-gradient(135deg, #FF5722, #FFC107); border-radius: 10px; border: none;">提交</button>
                <button type="reset" class="layui-btn layui-btn-primary" style="border-radius: 10px; border: 1px solid #E0E0E0;">重置</button>
            </div>
        </form>
        <%
            if (request.getAttribute("UpD1") != null) {
                String UpD = (String) request.getAttribute("UpD1");
                if (!UpD.isEmpty()) { // 只有在message不为空字符串时才弹出提示
                    %>
                    <script>
                        alert('<%= UpD %>');
                    </script>
                    <%
                    request.setAttribute("UpD1", ""); // 提交表单后将message的值设置为空字符串
                }
            }
        %>
    </div>
</div>

      </div>
  </div>
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    保持身心健康，享受生活的每一个美好瞬间！
  </div>    
</div>
 
<!-- 引入 layui.js -->
<script src="//unpkg.com/layui@2.8.18/dist/layui.js"></script>
<script>
//JS 
layui.use(['element', 'layer', 'util'], function(){
  var element = layui.element;
  var layer = layui.layer;
  var util = layui.util;
  var $ = layui.$;
  
  //头部事件
  util.event('lay-header-event', {
    menuLeft: function(othis){ // 左侧菜单事件
      layer.msg('展开左侧菜单的操作', {icon: 0});
    },
    menuRight: function(){  // 右侧菜单事件
      layer.open({
        type: 1,
        title: '更多',
        content: '<div style="padding: 15px;">处理右侧面板的操作</div>',
        area: ['260px', '100%'],
        offset: 'rt', // 右上角
        anim: 'slideLeft', // 从右侧抽屉滑出
        shadeClose: true,
        scrollbar: false
      });
    }
  });
});


//上方导航跳转
layui.use(['element', 'layer'], function(){
  var element = layui.element;
  var layer = layui.layer;

  // 监听导航点击
  element.on('nav(navFilter)', function(elem){
    var id = elem.text().trim(); // 获取点击的导航项文本
    // 根据导航项文本显示对应的内容
    switch (id) {
      case '系统信息功能说明':
        showContent('hospitalInfo');
        break;
      case '通知公告':
        showContent('notices');
        break;
      case '部门职责':
        break;
      case '返回首页':
        showContent('homePage');
        break;
      case '总部职责':
        showContent('a2');
        break;
      case '仓库职责':
        showContent('a3');
        break;
      case '超市职责':
        showContent('a4');
        break;
      default:
        showContent('homePage');
        break;
    }
  });

  // 显示对应的内容页面
  function showContent(contentId) {
    var contentPages = document.querySelectorAll('.layui-body .layui-tab-item');
    // 隐藏所有内容页面
    contentPages.forEach(function (page) {
      page.classList.remove('layui-show');
    });
    // 显示对应的内容页面
    var activePage = document.getElementById(contentId);
    if (activePage) {
      activePage.classList.add('layui-show');
    }
  }
});

//左侧导航跳转
layui.use(['element', 'layer'], function(){
  var element = layui.element;
  var layer = layui.layer;

  // 监听左侧导航点击
  element.on('nav(test)', function(elem){
    var id1 = elem.text().trim(); // 获取点击的导航项文本
    // 根据导航项文本显示对应的内容
    switch (id1) {
      case '商品信息查询':
        showContent('l1');
        break;
      case '提交需求申请':
        showContent('l2');
        break;
      case '查询调拨结果':
        showContent('l4');
        break;
    }
  });

  // 显示对应的内容页面
  function showContent(contentId) {
    var contentPages = document.querySelectorAll('.layui-body .layui-tab-item');
    // 隐藏所有内容页面
    contentPages.forEach(function (page) {
      page.classList.remove('layui-show');
    });
    // 显示对应的内容页面
    var activePage = document.getElementById(contentId);
    if (activePage) {
      activePage.classList.add('layui-show');
    }
  }
});

//个人信息跳转
layui.use(['element', 'layer'], function(){
  var element = layui.element;
  var layer = layui.layer;

  // 监听左侧导航点击
  element.on('nav(uppwd)', function(elem){
    var id3 = elem.text().trim(); // 获取点击的导航项文本
    // 根据导航项文本显示对应的内容
    switch (id3) {
      case '修改密码':
        showContent('l5');
        break;
    }
  });

  // 显示对应的内容页面
  function showContent(contentId) {
    var contentPages = document.querySelectorAll('.layui-body .layui-tab-item');
    // 隐藏所有内容页面
    contentPages.forEach(function (page) {
      page.classList.remove('layui-show');
    });
    // 显示对应的内容页面
    var activePage = document.getElementById(contentId);
    if (activePage) {
      activePage.classList.add('layui-show');
    }
  }
});

//获取商品编号
layui.use(['form', 'jquery'], function(){
    var form = layui.form;
    var $ = layui.jquery;

    // 页面加载完毕后，立即加载下拉菜单数据
    $(document).ready(function(){
        loadSelectData();
    });
    // 封装加载下拉菜单数据的函数
    function loadSelectData() {
        $.ajax({
            type: "GET",
            url: 'CommodityIdAcquireServlet',
            dataType: "json",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            async: true,  // 建议使用异步加载
            cache: false,
            success: function (data) {
                $('#commodityid').empty(); // 先清空下拉菜单
                $('#commodityid').append('<option value=""></option>'); // 添加默认空选项
                $.each(data, function(index, item){
                    $('#commodityid').append(new Option(item.id + ' - ' + item.name, item.id)); // 添加选项，这里假设你想显示 name 而不是 id
                });
                form.render('select'); // 重新渲染下拉菜单
            }
        });
    }
    // 保留下拉选择框的选择事件处理逻辑，但是不要在这里重新加载数据
    form.on('select(commodityid)', function(data){
        // 这里可以处理选择后的逻辑，但不要重新加载下拉菜单数据
        console.log(data.value); // 打印选择的值
    });
});

</script>

<script>
    function updateDateTime() {
        const now = new Date();
        const options = { 
            year: 'numeric', month: 'long', day: 'numeric', 
            hour: '2-digit', minute: '2-digit', second: '2-digit', 
            hour12: false 
        };
        const formattedDateTime = now.toLocaleString('zh-CN', options);
        document.getElementById('datetime-display').innerText = formattedDateTime;
    }
    setInterval(updateDateTime, 1000);
    updateDateTime(); // 初始调用以便立即显示时间
</script>

</body>
</html>

