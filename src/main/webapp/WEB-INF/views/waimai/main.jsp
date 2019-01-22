<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>外卖智能信息推送</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport">
<%--
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
--%>

<link rel="stylesheet" href="<%=basePath%>js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>css/main.css">
<link href="<%=basePath%>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="../js/jquery/jquery.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="../js/main.js"></script>
    <script>
        $(document).ready(function () {
            $(".addss").click(function () {
                $(".bg").css("display", "block");
                $(".xz").css("display", "block");
            });
            $(".close").click(function () {
                $(".bg").css("display", "none");
                $(".xz").css("display", "none");
            });
        });
    </script>
    <link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="main page1" style="display: block;">
    <div class="header">
        <a>
            <span>
             <svg t="1521900739716" class="icon" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="6118" xmlns:xlink="http://www.w3.org/1999/xlink" width="19" height="19"><defs><style type="text/css"></style></defs><path d="M515.417331 978.654232c-58.611909 0.358157-352.582197-443.447694-352.582197-591.852676 0-196.158588 157.851121-355.080088 352.582197-355.080088 194.685028 0 352.537172 158.920476 352.537172 355.080088C867.954503 533.252023 573.099055 979.01239 515.417331 978.654232zM515.417331 71.986521c-172.369789 0-312.049039 140.391471-312.049039 313.38957 0 131.126456 260.158205 533.059642 312.049039 532.87954 51.045583 0.181125 312.048015-403.532613 312.048015-532.87954C827.465346 212.377992 687.744141 71.986521 515.417331 71.986521zM515.417331 534.851451c-81.151251 0-146.938584-66.276473-146.938584-148.049894 0-81.598436 65.787332-147.874909 146.938584-147.874909 81.108273 0 146.895605 66.276473 146.895605 147.874909C662.312936 468.574978 596.52458 534.851451 515.417331 534.851451zM515.417331 291.482631c-51.715849 0-93.622284 42.045605-93.622284 93.89346 0 52.022841 41.907459 94.069469 93.622284 94.069469 51.66673 0 93.580329-42.045605 93.580329-94.069469C608.99766 333.528236 567.084061 291.482631 515.417331 291.482631z" p-id="6119" fill="#ffffff"></path></svg>
            </span>
            <span id="address" style="cursor: pointer;">宁夏大学中卫校区</span>
            <span><svg t="1521900638351" class="icon" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5188" data-spm-anchor-id="a313x.7781069.0.i17" xmlns:xlink="http://www.w3.org/1999/xlink" width="15" height="15"><defs><style type="text/css"></style></defs><path d="M213.333333 341.333333h597.333334l-298.666667 384z" p-id="5189" fill="#ffffff" data-spm-anchor-id="a313x.7781069.0.i16" class="selected"></path></svg></span>
        </a>
    </div>
    <div class="content">
        <ul>
            <li>
                <div>外卖公司：</div>
                <div class="btn-group">
                      <button style="min-width: 250px;" type="button" class="btn btn-primary">饿了么</button>
                      <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="height: 30px;margin-left: -7px;">
                        <span class="caret"></span>
                        <%--<span class="sr-only">Toggle Dropdown</span>--%>
                      </button>
                      <ul class="dropdown-menu">
                        <li style="min-width: 250px;"><a href="#">饿了么</a></li>
                      </ul>
                 </div>
            </li>
            <li>
                <div>偏好:</div>
                <%--<input type="text" style="height: 25px;width: 90%;">--%>
                <div class="btn-group recommendtype" role="group" aria-label="...">
                  <c:forEach var="item" items="${pianhao}">

                      <c:choose>

                          <c:when test="${item.key=='suiji'}">    <!--如果 -->
                              <button type="button" class="btn btn-primary" data-name="${item.key}">${item.value}</button>
                          </c:when>

                          <c:otherwise>  <!--否则 -->
                              <button type="button" class="btn btn-default" data-name="${item.key}">${item.value}</button>
                          </c:otherwise>

                      </c:choose>

                  </c:forEach>
                </div>
            </li>
            <%--<li>--%>
                <%--<div>评价,价格:</div>--%>
                <%--<div class="btn-group pianhao" role="group" aria-label="...">--%>
                  <%--<button type="button" class="btn btn-primary">价格最低</button>--%>
                  <%--<button type="button" class="btn btn-default">满减最多</button>--%>
                  <%--<button type="button" class="btn btn-default">评分最高</button>--%>
                <%--</div>--%>
            <%--</li>--%>
            <li>
                <div>提醒时间：</div>
                <div class="control-group">
                    <div class="controls input-append date form_time" data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii">
                        <input size="20" style="height: 30px;" name="reminder_time" type="text" value="" readonly>
                        <span class="add-on"><i class="icon-remove"></i></span>
                        <span class="add-on"><i class="icon-th"></i></span>
                    </div>
                    <input type="hidden" name="reminder_time" id="dtp_input4" value="" /><br/>
                </div>
            </li>
            <li>
                <div>重复：</div>
                <div class="btn-group reminder_time" role="group" aria-label="...">
                    <button type="button" class="btn btn-primary">星期日</button>
                    <button type="button" class="btn btn-default">星期一</button>
                    <button type="button" class="btn btn-default">星期二</button>
                    <button type="button" class="btn btn-default">星期三</button>
                    <button type="button" class="btn btn-default">星期四</button>
                    <button type="button" class="btn btn-default">星期五</button>
                    <button type="button" class="btn btn-default">星期六</button>
                </div>
            </li>
        </ul>
    </div>
    <input type="hidden" value="37.496747" name="latitude">
    <input type="hidden" value="105.170322" name="longitude">
    <input type="hidden" value="wqdv34ndqh7b" name="geohash">
    <input type="hidden" name="openid" value="${openid}">
    <input type="hidden" name="recommendtype" value="suiji">
    <input type="hidden" name="totaltime" value="星期天,">

    <button type="button" class="btn btn-primary btn-lg btn-block addButton" onclick="insertData()" style="width:90%;margin-top:5px;font-size: 17px;">添加提醒</button>
    <div style="height:100px;"></div>

    <div class="layercart" style="display: none;">设置成功！</div>
    <div class="layerceng"></div>
    <div class="footer">
        <a href="<%=basePath%>waimai/allmessage">
            <span>
                <svg t="1521903022784" class="icon1" style="display: none;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4405" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20"><defs><style type="text/css"></style></defs><path d="M798.408 569.848v-88.407c0-124.764-89.85-229.568-211.378-259.101v-1.493c0-41.949-31.487-75.954-70.328-75.954-38.838 0-70.328 34.004-70.328 75.954v1.733c-121.026 29.856-210.392 134.428-210.392 258.856v72.751h-0.059c1.453 89.798-70.493 107.41-70.493 155.882s39.292 67.386 87.767 67.386h122.755c1.714 58.393 64.233 105.335 141.109 105.335s139.395-46.938 141.109-105.335h122.438c48.475 0 87.771-18.91 87.771-67.386 0.005-45.503-63.661-64.015-69.969-140.22zM517.151 847.314c-58.389 0-105.722-30.723-105.722-68.625 0-0.413 0.010-0.825 0.026-1.239h211.395c0.010 0.413 0.026 0.822 0.026 1.239-0.005 37.901-47.336 68.625-105.727 68.625zM833.375 712.797c-2.197 22.761-32.25 30.008-51.159 30.008h-530.469c-14.482 1.97-51.705-5.107-51.156-29.513v-6.198c0.483-22.037 54.625-40.822 70.179-118.866v-34.038h-0.557v-66.675c0-119.753 91.718-218.82 211.063-235.378v-29.628c0-23.405 15.775-42.378 35.235-42.378s35.235 18.973 35.235 42.378v29.588c119.477 16.445 211.34 115.569 211.34 235.419v66.674h-0.205v30.694h0.273c0.712 56.066 69.668 102.662 70.217 119.522l0.005 8.391z" fill="#ffffff" p-id="4406"></path></svg>
                <svg t="1522248275985" class="icon2" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3720" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20"><defs><style type="text/css"></style></defs><path d="M798.408 569.848v-88.407c0-124.764-89.85-229.568-211.378-259.101v-1.493c0-41.949-31.487-75.954-70.328-75.954-38.838 0-70.328 34.004-70.328 75.954v1.733c-121.026 29.856-210.392 134.428-210.392 258.856v72.751h-0.059c1.453 89.798-70.493 107.41-70.493 155.882s39.292 67.386 87.767 67.386h122.755c1.714 58.393 64.233 105.335 141.109 105.335s139.395-46.938 141.109-105.335h122.438c48.475 0 87.771-18.91 87.771-67.386 0.005-45.503-63.661-64.015-69.969-140.22zM517.151 847.314c-58.389 0-105.722-30.723-105.722-68.625 0-0.413 0.010-0.825 0.026-1.239h211.395c0.010 0.413 0.026 0.822 0.026 1.239-0.005 37.901-47.336 68.625-105.727 68.625zM833.375 712.797c-2.197 22.761-32.25 30.008-51.159 30.008h-530.469c-14.482 1.97-51.705-5.107-51.156-29.513v-6.198c0.483-22.037 54.625-40.822 70.179-118.866v-34.038h-0.557v-66.675c0-119.753 91.718-218.82 211.063-235.378v-29.628c0-23.405 15.775-42.378 35.235-42.378s35.235 18.973 35.235 42.378v29.588c119.477 16.445 211.34 115.569 211.34 235.419v66.674h-0.205v30.694h0.273c0.712 56.066 69.668 102.662 70.217 119.522l0.005 8.391z" fill="#337ab7" p-id="3721"></path></svg>
            </span>
            <span>消息</span>
        </a>
        <a class="disa" href="<%=basePath%>/waimai/main">
                <span>
                    <svg t="1521903125668" class="icon1" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5403" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20"><defs><style type="text/css"></style></defs><path d="M514.567 283.551c123.208 0 225.882 102.674 225.882 231.016 0 123.208-102.674 225.882-225.882 225.882-128.342 0-231.016-102.674-231.016-225.882 0-128.342 102.674-231.016 231.016-231.016zM514.567 237.347c184.813 0 343.957 112.942 415.829 277.22-71.872 159.144-231.016 277.22-415.829 277.22-189.947 0-349.091-118.075-420.963-277.22 71.872-164.278 231.016-277.22 420.963-277.22zM992 514.567c0-266.952-215.615-482.567-477.433-482.567-266.952 0-482.567 215.615-482.567 482.567 0 261.818 215.615 477.433 482.567 477.433 261.818 0 477.433-215.615 477.433-477.433zM627.508 355.423c-20.535 0-41.069 20.535-41.069 41.069 0 20.535 20.535 41.069 41.069 41.069 25.669 0 41.069-20.535 41.069-41.069 0-20.535-15.401-41.069-41.069-41.069zM514.567 422.16c-51.337 0-92.407 41.069-92.407 92.407s41.069 92.407 92.407 92.407c51.337 0 92.407-41.069 92.407-92.407 0-51.337-41.069-92.407-92.407-92.407z" p-id="5404" fill="#ffffff"></path></svg>
                    <svg t="1522249309381" class="icon2" style="display: none" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5443" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20"><defs><style type="text/css"></style></defs><path d="M514.567 283.551c123.208 0 225.882 102.674 225.882 231.016 0 123.208-102.674 225.882-225.882 225.882-128.342 0-231.016-102.674-231.016-225.882 0-128.342 102.674-231.016 231.016-231.016zM514.567 237.347c184.813 0 343.957 112.942 415.829 277.22-71.872 159.144-231.016 277.22-415.829 277.22-189.947 0-349.091-118.075-420.963-277.22 71.872-164.278 231.016-277.22 420.963-277.22zM992 514.567c0-266.952-215.615-482.567-477.433-482.567-266.952 0-482.567 215.615-482.567 482.567 0 261.818 215.615 477.433 482.567 477.433 261.818 0 477.433-215.615 477.433-477.433zM627.508 355.423c-20.535 0-41.069 20.535-41.069 41.069 0 20.535 20.535 41.069 41.069 41.069 25.669 0 41.069-20.535 41.069-41.069 0-20.535-15.401-41.069-41.069-41.069zM514.567 422.16c-51.337 0-92.407 41.069-92.407 92.407s41.069 92.407 92.407 92.407c51.337 0 92.407-41.069 92.407-92.407 0-51.337-41.069-92.407-92.407-92.407z" p-id="5444" fill="#337ab7"></path></svg>
                </span>
            <span>发现</span>
        </a>
        <a href="<%=basePath%>waimai/mycount">
            <span>
               <svg t="1521902794220" class="icon1" style="display: none;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3273" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20"><defs><style type="text/css"></style></defs><path d="M909.952 929.024a397.76 397.76 0 0 0-269.056-376.064l-9.792-3.392 8.832-5.184a255.104 255.104 0 0 0 126.208-219.264c0-140.096-113.984-254.08-254.144-254.08S257.856 185.024 257.856 325.12c0 89.728 48.32 173.76 126.144 219.264l8.832 5.184-9.728 3.392a397.696 397.696 0 0 0-269.056 376.064l0.064 0.896-0.064 0.768c0 12.352 9.6 22.4 21.504 22.4H888.32a22.016 22.016 0 0 0 21.568-22.4l-0.128-0.768c0-0.256 0.192-0.576 0.192-0.896zM305.984 325.12c0-115.456 92.416-209.408 206.016-209.408s205.952 93.952 205.952 209.408c0 104.256-76.544 193.408-178.176 207.296l-0.512 0.128-5.248-0.384a315.968 315.968 0 0 0-44.032 0l-5.248 0.384-0.512-0.128c-101.568-13.888-178.24-103.04-178.24-207.296zM512 579.2a350.144 350.144 0 0 1 349.12 329.088H162.752A350.336 350.336 0 0 1 512 579.2z m0 0" p-id="3274" fill="#ffffff"></path></svg>
               <svg t="1522248098765" class="icon2" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1687" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20"><defs><style type="text/css"></style></defs><path d="M909.952 929.024a397.76 397.76 0 0 0-269.056-376.064l-9.792-3.392 8.832-5.184a255.104 255.104 0 0 0 126.208-219.264c0-140.096-113.984-254.08-254.144-254.08S257.856 185.024 257.856 325.12c0 89.728 48.32 173.76 126.144 219.264l8.832 5.184-9.728 3.392a397.696 397.696 0 0 0-269.056 376.064l0.064 0.896-0.064 0.768c0 12.352 9.6 22.4 21.504 22.4H888.32a22.016 22.016 0 0 0 21.568-22.4l-0.128-0.768c0-0.256 0.192-0.576 0.192-0.896zM305.984 325.12c0-115.456 92.416-209.408 206.016-209.408s205.952 93.952 205.952 209.408c0 104.256-76.544 193.408-178.176 207.296l-0.512 0.128-5.248-0.384a315.968 315.968 0 0 0-44.032 0l-5.248 0.384-0.512-0.128c-101.568-13.888-178.24-103.04-178.24-207.296zM512 579.2a350.144 350.144 0 0 1 349.12 329.088H162.752A350.336 350.336 0 0 1 512 579.2z m0 0" p-id="1688" fill="#337ab7"></path></svg>
            </span>
            <span>我的</span>
        </a>
    </div>
</div>

<div class="main page2" style="display: none;">
    <div class="page2content">
        <div class="page2header">
            <li><a href="javascript:showpage()">
                <svg t="1521954964798" class="icon" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3300" xmlns:xlink="http://www.w3.org/1999/xlink" width="30" height="30"><defs><style type="text/css"></style></defs><path d="M862.485 481.154H234.126l203.3-203.3c12.497-12.497 12.497-32.758 0-45.255s-32.758-12.497-45.255 0L135.397 489.373c-12.497 12.497-12.497 32.758 0 45.254l256.774 256.775c6.249 6.248 14.438 9.372 22.627 9.372s16.379-3.124 22.627-9.372c12.497-12.497 12.497-32.759 0-45.255l-203.3-203.301h628.36c17.036 0 30.846-13.81 30.846-30.846s-13.81-30.846-30.846-30.846z" fill="#ffffff" p-id="3301"></path></svg>
            </a></li>
            <li><span>选择收货地址</span></li>

        </div>
        <div class="cleraboth"></div>
        <div class="search">
            <div class="row" style="margin-left:0px;margin-right:0px;">
              <div class="col-lg-6" style="width: 100%;">
                <div class="input-group" style="width: 100%;">
                  <div class="input-group-btn" style="height: 34px;vertical-align: top;float:left;">
                    <button style="font-size:18px;border: none;margin-top:0px;" type="button" class="btn btn-primary dropdown-toggle"><span id="gr_zone_ids">银川市</span><span class="caret"></span></button>
                  </div>
                  <div style="width:70%;float:left;" class="addressdiv">
                      <input id="search_text" type="text" class="form-control" aria-label="..." style="height:30px;">
                      <div id="autodiv">
                      </div>
                  </div>
                </div>
              </div>
            </div>
        </div>
    </div>

    <div class="footer">
        <a class="disa" href="<%=basePath%>/waimai/main">
                <span>
                    <svg t="1521903125668" class="icon1" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5403" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20"><defs><style type="text/css"></style></defs><path d="M514.567 283.551c123.208 0 225.882 102.674 225.882 231.016 0 123.208-102.674 225.882-225.882 225.882-128.342 0-231.016-102.674-231.016-225.882 0-128.342 102.674-231.016 231.016-231.016zM514.567 237.347c184.813 0 343.957 112.942 415.829 277.22-71.872 159.144-231.016 277.22-415.829 277.22-189.947 0-349.091-118.075-420.963-277.22 71.872-164.278 231.016-277.22 420.963-277.22zM992 514.567c0-266.952-215.615-482.567-477.433-482.567-266.952 0-482.567 215.615-482.567 482.567 0 261.818 215.615 477.433 482.567 477.433 261.818 0 477.433-215.615 477.433-477.433zM627.508 355.423c-20.535 0-41.069 20.535-41.069 41.069 0 20.535 20.535 41.069 41.069 41.069 25.669 0 41.069-20.535 41.069-41.069 0-20.535-15.401-41.069-41.069-41.069zM514.567 422.16c-51.337 0-92.407 41.069-92.407 92.407s41.069 92.407 92.407 92.407c51.337 0 92.407-41.069 92.407-92.407 0-51.337-41.069-92.407-92.407-92.407z" p-id="5404" fill="#ffffff"></path></svg>
                    <svg t="1522249309381" class="icon2" style="display: none" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5443" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20"><defs><style type="text/css"></style></defs><path d="M514.567 283.551c123.208 0 225.882 102.674 225.882 231.016 0 123.208-102.674 225.882-225.882 225.882-128.342 0-231.016-102.674-231.016-225.882 0-128.342 102.674-231.016 231.016-231.016zM514.567 237.347c184.813 0 343.957 112.942 415.829 277.22-71.872 159.144-231.016 277.22-415.829 277.22-189.947 0-349.091-118.075-420.963-277.22 71.872-164.278 231.016-277.22 420.963-277.22zM992 514.567c0-266.952-215.615-482.567-477.433-482.567-266.952 0-482.567 215.615-482.567 482.567 0 261.818 215.615 477.433 482.567 477.433 261.818 0 477.433-215.615 477.433-477.433zM627.508 355.423c-20.535 0-41.069 20.535-41.069 41.069 0 20.535 20.535 41.069 41.069 41.069 25.669 0 41.069-20.535 41.069-41.069 0-20.535-15.401-41.069-41.069-41.069zM514.567 422.16c-51.337 0-92.407 41.069-92.407 92.407s41.069 92.407 92.407 92.407c51.337 0 92.407-41.069 92.407-92.407 0-51.337-41.069-92.407-92.407-92.407z" p-id="5444" fill="#337ab7"></path></svg>
                </span>
            <span>发现</span>
        </a>
        <a href="<%=basePath%>waimai/allmessage">
            <span>
                <svg t="1521903022784" class="icon1" style="display: none;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4405" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20"><defs><style type="text/css"></style></defs><path d="M798.408 569.848v-88.407c0-124.764-89.85-229.568-211.378-259.101v-1.493c0-41.949-31.487-75.954-70.328-75.954-38.838 0-70.328 34.004-70.328 75.954v1.733c-121.026 29.856-210.392 134.428-210.392 258.856v72.751h-0.059c1.453 89.798-70.493 107.41-70.493 155.882s39.292 67.386 87.767 67.386h122.755c1.714 58.393 64.233 105.335 141.109 105.335s139.395-46.938 141.109-105.335h122.438c48.475 0 87.771-18.91 87.771-67.386 0.005-45.503-63.661-64.015-69.969-140.22zM517.151 847.314c-58.389 0-105.722-30.723-105.722-68.625 0-0.413 0.010-0.825 0.026-1.239h211.395c0.010 0.413 0.026 0.822 0.026 1.239-0.005 37.901-47.336 68.625-105.727 68.625zM833.375 712.797c-2.197 22.761-32.25 30.008-51.159 30.008h-530.469c-14.482 1.97-51.705-5.107-51.156-29.513v-6.198c0.483-22.037 54.625-40.822 70.179-118.866v-34.038h-0.557v-66.675c0-119.753 91.718-218.82 211.063-235.378v-29.628c0-23.405 15.775-42.378 35.235-42.378s35.235 18.973 35.235 42.378v29.588c119.477 16.445 211.34 115.569 211.34 235.419v66.674h-0.205v30.694h0.273c0.712 56.066 69.668 102.662 70.217 119.522l0.005 8.391z" fill="#ffffff" p-id="4406"></path></svg>
                <svg t="1522248275985" class="icon2" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3720" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20"><defs><style type="text/css"></style></defs><path d="M798.408 569.848v-88.407c0-124.764-89.85-229.568-211.378-259.101v-1.493c0-41.949-31.487-75.954-70.328-75.954-38.838 0-70.328 34.004-70.328 75.954v1.733c-121.026 29.856-210.392 134.428-210.392 258.856v72.751h-0.059c1.453 89.798-70.493 107.41-70.493 155.882s39.292 67.386 87.767 67.386h122.755c1.714 58.393 64.233 105.335 141.109 105.335s139.395-46.938 141.109-105.335h122.438c48.475 0 87.771-18.91 87.771-67.386 0.005-45.503-63.661-64.015-69.969-140.22zM517.151 847.314c-58.389 0-105.722-30.723-105.722-68.625 0-0.413 0.010-0.825 0.026-1.239h211.395c0.010 0.413 0.026 0.822 0.026 1.239-0.005 37.901-47.336 68.625-105.727 68.625zM833.375 712.797c-2.197 22.761-32.25 30.008-51.159 30.008h-530.469c-14.482 1.97-51.705-5.107-51.156-29.513v-6.198c0.483-22.037 54.625-40.822 70.179-118.866v-34.038h-0.557v-66.675c0-119.753 91.718-218.82 211.063-235.378v-29.628c0-23.405 15.775-42.378 35.235-42.378s35.235 18.973 35.235 42.378v29.588c119.477 16.445 211.34 115.569 211.34 235.419v66.674h-0.205v30.694h0.273c0.712 56.066 69.668 102.662 70.217 119.522l0.005 8.391z" fill="#337ab7" p-id="3721"></path></svg>
            </span>
            <span>消息</span>
        </a>
        <a href="<%=basePath%>waimai/mycount?openid=abc">
            <span>
               <svg t="1521902794220" class="icon1" style="display: none;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3273" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20"><defs><style type="text/css"></style></defs><path d="M909.952 929.024a397.76 397.76 0 0 0-269.056-376.064l-9.792-3.392 8.832-5.184a255.104 255.104 0 0 0 126.208-219.264c0-140.096-113.984-254.08-254.144-254.08S257.856 185.024 257.856 325.12c0 89.728 48.32 173.76 126.144 219.264l8.832 5.184-9.728 3.392a397.696 397.696 0 0 0-269.056 376.064l0.064 0.896-0.064 0.768c0 12.352 9.6 22.4 21.504 22.4H888.32a22.016 22.016 0 0 0 21.568-22.4l-0.128-0.768c0-0.256 0.192-0.576 0.192-0.896zM305.984 325.12c0-115.456 92.416-209.408 206.016-209.408s205.952 93.952 205.952 209.408c0 104.256-76.544 193.408-178.176 207.296l-0.512 0.128-5.248-0.384a315.968 315.968 0 0 0-44.032 0l-5.248 0.384-0.512-0.128c-101.568-13.888-178.24-103.04-178.24-207.296zM512 579.2a350.144 350.144 0 0 1 349.12 329.088H162.752A350.336 350.336 0 0 1 512 579.2z m0 0" p-id="3274" fill="#ffffff"></path></svg>
               <svg t="1522248098765" class="icon2" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1687" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20"><defs><style type="text/css"></style></defs><path d="M909.952 929.024a397.76 397.76 0 0 0-269.056-376.064l-9.792-3.392 8.832-5.184a255.104 255.104 0 0 0 126.208-219.264c0-140.096-113.984-254.08-254.144-254.08S257.856 185.024 257.856 325.12c0 89.728 48.32 173.76 126.144 219.264l8.832 5.184-9.728 3.392a397.696 397.696 0 0 0-269.056 376.064l0.064 0.896-0.064 0.768c0 12.352 9.6 22.4 21.504 22.4H888.32a22.016 22.016 0 0 0 21.568-22.4l-0.128-0.768c0-0.256 0.192-0.576 0.192-0.896zM305.984 325.12c0-115.456 92.416-209.408 206.016-209.408s205.952 93.952 205.952 209.408c0 104.256-76.544 193.408-178.176 207.296l-0.512 0.128-5.248-0.384a315.968 315.968 0 0 0-44.032 0l-5.248 0.384-0.512-0.128c-101.568-13.888-178.24-103.04-178.24-207.296zM512 579.2a350.144 350.144 0 0 1 349.12 329.088H162.752A350.336 350.336 0 0 1 512 579.2z m0 0" p-id="1688" fill="#337ab7"></path></svg>
            </span>
            <span>我的</span>
        </a>
    </div>
</div>
<div class="xz">
<div class="xzborder">
            <div class="container" style="z-index: 9999; position: relative;">
                <div id="toast"></div>
                <div class="city" id="city_list">
                   <c:forEach var="citylist" items="${cities}">
                        <div class="city-list"><span class="city-letter" id="${citylist.key}1">${citylist.key}</span>
                            <c:forEach var="city" items="${citylist.value}">
                                <p data-id="210300" data-geohash="${city.geohash}">${city.cityname}</p>
                            </c:forEach>
                        </div>
                   </c:forEach>
                </div>
                <input type="hidden" value="wqgdcqhpu20x" name="citygeohash">
                <div class="letter">
                    <ul>
                        <li><a href="javascript:;">A</a></li>
                        <li><a href="javascript:;">B</a></li>
                        <li><a href="javascript:;">C</a></li>
                        <li><a href="javascript:;">D</a></li>
                        <li><a href="javascript:;">E</a></li>
                        <li><a href="javascript:;">F</a></li>
                        <li><a href="javascript:;">G</a></li>
                        <li><a href="javascript:;">H</a></li>
                        <li><a href="javascript:;">J</a></li>
                        <li><a href="javascript:;">K</a></li>
                        <li><a href="javascript:;">L</a></li>
                        <li><a href="javascript:;">M</a></li>
                        <li><a href="javascript:;">N</a></li>
                        <li><a href="javascript:;">P</a></li>
                        <li><a href="javascript:;">Q</a></li>
                        <li><a href="javascript:;">R</a></li>
                        <li><a href="javascript:;">S</a></li>
                        <li><a href="javascript:;">T</a></li>
                        <li><a href="javascript:;">W</a></li>
                        <li><a href="javascript:;">X</a></li>
                        <li><a href="javascript:;">Y</a></li>
                        <li><a href="javascript:;">Z</a></li>
                    </ul>
                </div>
            </div>
        </div>
</div>
</body>

</html>
<script type="text/javascript" src="../js/zepto.js"></script>
<script type="text/javascript">
    //加载城市事件
    $('body').on('click', '#zone_ids,#gr_zone_ids', function () {
        var zid = $(this).attr('id');
        $('.container').slideToggle();

    });
    //选择城市 start
    $('body').on('click', '.city-list p', function () {
        var type = $('.container').data('type');
        $('#zone_ids').html($(this).html()).attr('data-id', $(this).attr('data-id'));
        $('#gr_zone_ids').html($(this).html()).attr('data-id', $(this).attr('data-id'));
        $("input[name='citygeohash']").val($(this).attr("data-geohash"));
        $('.container').slideUp();
    });

    var city_list = $("#city_list");
    $('body').on('click', '.letter a', function () {
        var s = $(this).text();
        city_list.scrollTop(0);
        $("#toast").text(s).fadeIn();
        setTimeout(function(){
            $("#toast").fadeOut();
        },1200)
        t = $('#' + s + '1');
        f = t.position().top;
        city_list.scrollTop(t.position().top);
    });


</script>

<script type="text/javascript">
    //测试用的数据，这里可以用AJAX获取服务器数据
    var old_value = "";
    var highlightindex = -1;   //高亮
    /**
     *
     * @param autodiv   弹出框
     * @param searchtest
     * @param mylist
     * @constructor
     */
    function AutoComplete(auto, search, carlist) {
        if ($("#" + search).val() != old_value) {
            var autoNode = $("#" + auto);   //缓存对象（弹出框）
            console.log(carlist.length);
            if (carlist.length == 0) {
                autoNode.hide();
                return;
            }
            autoNode.empty();  //清空上次的记录
            for (var i in carlist) {
                console.log(i);
                var wordNode = carlist[i];   //弹出框里的每一条内容
                var newDivNode = $("<li>").attr("id", i);    //设置每个节点的id值
                /*newDivNode.attr("data-latitude",wordNode['latitude']);*/
                newDivNode.attr("style", "background: #e6e6e6;padding-top: 8px;color:#337ab7;cursor: pointer;");
                console.log(wordNode['name']);
                newDivNode.html(wordNode['name']).appendTo(autoNode);  //追加到弹出框
                //鼠标移入高亮，移开不高亮
                newDivNode.mouseover(function () {
                    if (highlightindex != -1) {        //原来高亮的节点要取消高亮（是-1就不需要了）
                        autoNode.children("li").eq(highlightindex).css("background-color", "#e6e6e6");
                        autoNode.children("li").eq(highlightindex).css("color", "white");
                    }
                    //记录新的高亮节点索引
                    highlightindex = $(this).attr("id");
                    $(this).css("background-color", "#337ab7");
                });
                newDivNode.mouseout(function () {
                    $(this).css("background-color", "#e6e6e6");
                    $(this).css("color", "#337ab7");
                });
                //鼠标点击文字上屏
                newDivNode.click(function () {
                    //取出高亮节点的文本内容
                    var comText = autoNode.hide().children("li").eq(highlightindex).text();
                    highlightindex = -1;
                    //文本框中的内容变成高亮节点的内容
                    $("#" + search).val(comText);
                    $("input[name='longitude']").val(wordNode['longitude']);
                    $("input[name='latitude']").val(wordNode['latitude']);
                    $("input[name='geohash']").val(wordNode['geohash']);
                    $("#address").html(comText);
                    $(".page2").hide();
                    $(".page1").show();
                });
                if (carlist.length > 0) {//如果返回值有内容就显示出来
                    autoNode.show();
                } else {//服务器端无内容返回 那么隐藏弹出框
                    autoNode.hide();
                    //弹出框隐藏的同时，高亮节点索引值也变成-1
                    highlightindex = -1;
                }
            }
        }
        //点击页面隐藏自动补全提示框
        /*document.onclick = function (e) {
            var e = e ? e : window.event;
            var tar = e.srcElement || e.target;
            if (tar.id != search) {
                if ($("#" + auto).is(":visible")) {
                    $("#" + auto).css("display", "none")
                }
            }
        }*/
    }
$(function () {
            $("#search_text").focus(function () {
                if ($("#search_text").val() == "") {
                    /*AutoComplete("auto_div", "search_text", test_list);*/
                }
            });
            $("#search_text").keyup(function () {
                old_value = $("#search_text").val();
                if(old_value!="") {
                    var geohash=$("input[name='citygeohash']").val();//获取当前城市的geohash
                    $.ajax({
                        type: "Post",
                        url: "/waimai/point",
                        data: {
                            geohash: geohash,
                            keyword: encodeURI(old_value)
                        },
                        success: function (data) {
                            if(data){
                                AutoComplete("autodiv", "search_text", eval('(' + data + ')'));
                            }
                        }
                    });
                }

            });
        });

</script>
<script type="text/javascript" src="../js/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../js/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
<script>
    $('.form_time').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse: 0
    });
    $("#address").click(function () {
        $(".page1").hide();
        $(".page2").show();
    });
    function showpage() {
        $(".page2").hide();
        $(".page1").show();
    }
</script>
<script>

    function insertData() {
        var openid = $("input[name='openid']").val();
        var latitude = $("input[name='latitude']").val();
        var longitude = $("input[name='longitude']").val();
        var geohash = $("input[name='geohash']").val();
        var recommendtype = $("input[name='recommendtype']").val();
        var totaltime = $("input[name='totaltime']").val();
        var reminder_time = $("input[name='reminder_time']").val();
        var address=$("#address").html();
        console.log(reminder_time);
        if (reminder_time == "") {
            console.log("sdfasdfa");
            $(".layercart").html("时间没有选择！");
            $(".layercart").show();
            setTimeout("$('.layercart').hide()", 1500);
            return ;
        }
        $.ajax({
            type: "POST",
            url: "/waimai/insertData",
            data: {
                openid: openid,
                latitude: latitude,
                longitude: longitude,
                geohash: geohash,
                recommendtype: recommendtype,
                totaltime: totaltime,
                reminder_time: reminder_time,
                address:address

            },
            success: function (data) {
                if(data=1){
                    $(".layercart").html("设置成功！");
                    $(".layercart").show();
                    setTimeout("window.location.href='/waimai/mycount?openid=abc'", 1500);
                }

            }
        });
    }
</script>