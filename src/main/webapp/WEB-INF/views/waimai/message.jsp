<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>外卖信息推送</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport">
    <%--
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    --%>

    <link rel="stylesheet" href="<%=basePath%>js/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>css/main.css">
    <link href="<%=basePath%>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <script type="text/javascript" src="../js/jquery/jquery-1.8.3.min.js" charset="UTF-8"></script>
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
    <div class="messageheader">
     <c:forEach var="item" items="${datemap}" begin = "0" varStatus="s">
        <li>
            <div>${item.key}</div>

                <div>
                    <a <c:if test="${s.last}"> class="currentdate" </c:if> data-date="${item.value}" >
                            ${fn:split(item.value,"-")[2]}
                    </a>
                </div>
       </li>
     </c:forEach>
    </div>
    <div class="messagediv" >
        <ul style="display: block;" class="messageul">
           <c:forEach var="item" items="${messages}">
                <li>
                    <div style="height:33%;width: 100%">
                        <label style="float:left;margin-left:5px;">${item.foodname}-----推荐时间：${item.reminder_time}</label>
                        <label style="float:right;margin-right:5px;">评分：${item.rating}</label></div>
                    <div style="height:33%;width: 100%">
                        <label style="float:left;margin-top:20px;margin-left:5px;">1份：${item.price}元，2份：${item.twoprice}</label>
                        <svg t="1523113470569" class="icon" style="float:right;margin-top:20px;margin-right:20px;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1911" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20"><defs><style type="text/css"></style></defs><path d="M782.8 511.1 351.9 942c-22.8 22.8-59.8 22.8-82.6 0-22.8-22.8-22.8-59.8 0-82.6L617.5 511.1 269.2 162.8c-22.8-22.8-22.8-59.8 0-82.6 22.8-22.8 59.8-22.8 82.6 0L782.8 511.1 782.8 511.1zM782.8 511.1" p-id="1912" fill="#2c2c2c"></path></svg>
                    </div>
                    <div style="float:right;margin-top:5px;margin-right:6px;height:33%">
                        <a href="${item.shopurl}">店铺：${item.shopname}</a>
                    </div>
                </li>
           </c:forEach>
        </ul>
        <div class="nomessage" style="text-align: center;height: 100px;line-height:100px;<c:if test="${messages!=null}">display: none</c:if> ">今日暂无推荐!</div>
    </div>

    <div style="height:100px;"></div>
    <div class="footer">
        <a class="disa" href="<%=basePath%>waimai/allmessage">
            <span>
                <svg t="1521903022784" class="icon1" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4405" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20"><defs><style type="text/css"></style></defs><path d="M798.408 569.848v-88.407c0-124.764-89.85-229.568-211.378-259.101v-1.493c0-41.949-31.487-75.954-70.328-75.954-38.838 0-70.328 34.004-70.328 75.954v1.733c-121.026 29.856-210.392 134.428-210.392 258.856v72.751h-0.059c1.453 89.798-70.493 107.41-70.493 155.882s39.292 67.386 87.767 67.386h122.755c1.714 58.393 64.233 105.335 141.109 105.335s139.395-46.938 141.109-105.335h122.438c48.475 0 87.771-18.91 87.771-67.386 0.005-45.503-63.661-64.015-69.969-140.22zM517.151 847.314c-58.389 0-105.722-30.723-105.722-68.625 0-0.413 0.010-0.825 0.026-1.239h211.395c0.010 0.413 0.026 0.822 0.026 1.239-0.005 37.901-47.336 68.625-105.727 68.625zM833.375 712.797c-2.197 22.761-32.25 30.008-51.159 30.008h-530.469c-14.482 1.97-51.705-5.107-51.156-29.513v-6.198c0.483-22.037 54.625-40.822 70.179-118.866v-34.038h-0.557v-66.675c0-119.753 91.718-218.82 211.063-235.378v-29.628c0-23.405 15.775-42.378 35.235-42.378s35.235 18.973 35.235 42.378v29.588c119.477 16.445 211.34 115.569 211.34 235.419v66.674h-0.205v30.694h0.273c0.712 56.066 69.668 102.662 70.217 119.522l0.005 8.391z" fill="#ffffff" p-id="4406"></path></svg>
                <svg t="1522248275985" class="icon2" style="display: none;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3720" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20"><defs><style type="text/css"></style></defs><path d="M798.408 569.848v-88.407c0-124.764-89.85-229.568-211.378-259.101v-1.493c0-41.949-31.487-75.954-70.328-75.954-38.838 0-70.328 34.004-70.328 75.954v1.733c-121.026 29.856-210.392 134.428-210.392 258.856v72.751h-0.059c1.453 89.798-70.493 107.41-70.493 155.882s39.292 67.386 87.767 67.386h122.755c1.714 58.393 64.233 105.335 141.109 105.335s139.395-46.938 141.109-105.335h122.438c48.475 0 87.771-18.91 87.771-67.386 0.005-45.503-63.661-64.015-69.969-140.22zM517.151 847.314c-58.389 0-105.722-30.723-105.722-68.625 0-0.413 0.010-0.825 0.026-1.239h211.395c0.010 0.413 0.026 0.822 0.026 1.239-0.005 37.901-47.336 68.625-105.727 68.625zM833.375 712.797c-2.197 22.761-32.25 30.008-51.159 30.008h-530.469c-14.482 1.97-51.705-5.107-51.156-29.513v-6.198c0.483-22.037 54.625-40.822 70.179-118.866v-34.038h-0.557v-66.675c0-119.753 91.718-218.82 211.063-235.378v-29.628c0-23.405 15.775-42.378 35.235-42.378s35.235 18.973 35.235 42.378v29.588c119.477 16.445 211.34 115.569 211.34 235.419v66.674h-0.205v30.694h0.273c0.712 56.066 69.668 102.662 70.217 119.522l0.005 8.391z" fill="#337ab7" p-id="3721"></path></svg>
            </span>
            <span>消息</span>
        </a>
        <a href="<%=basePath%>/waimai/main">
                <span>
                    <svg t="1521903125668" class="icon1" style="display: none" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5403" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20"><defs><style type="text/css"></style></defs><path d="M514.567 283.551c123.208 0 225.882 102.674 225.882 231.016 0 123.208-102.674 225.882-225.882 225.882-128.342 0-231.016-102.674-231.016-225.882 0-128.342 102.674-231.016 231.016-231.016zM514.567 237.347c184.813 0 343.957 112.942 415.829 277.22-71.872 159.144-231.016 277.22-415.829 277.22-189.947 0-349.091-118.075-420.963-277.22 71.872-164.278 231.016-277.22 420.963-277.22zM992 514.567c0-266.952-215.615-482.567-477.433-482.567-266.952 0-482.567 215.615-482.567 482.567 0 261.818 215.615 477.433 482.567 477.433 261.818 0 477.433-215.615 477.433-477.433zM627.508 355.423c-20.535 0-41.069 20.535-41.069 41.069 0 20.535 20.535 41.069 41.069 41.069 25.669 0 41.069-20.535 41.069-41.069 0-20.535-15.401-41.069-41.069-41.069zM514.567 422.16c-51.337 0-92.407 41.069-92.407 92.407s41.069 92.407 92.407 92.407c51.337 0 92.407-41.069 92.407-92.407 0-51.337-41.069-92.407-92.407-92.407z" p-id="5404" fill="#ffffff"></path></svg>
                    <svg t="1522249309381" class="icon2" style="" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5443" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20"><defs><style type="text/css"></style></defs><path d="M514.567 283.551c123.208 0 225.882 102.674 225.882 231.016 0 123.208-102.674 225.882-225.882 225.882-128.342 0-231.016-102.674-231.016-225.882 0-128.342 102.674-231.016 231.016-231.016zM514.567 237.347c184.813 0 343.957 112.942 415.829 277.22-71.872 159.144-231.016 277.22-415.829 277.22-189.947 0-349.091-118.075-420.963-277.22 71.872-164.278 231.016-277.22 420.963-277.22zM992 514.567c0-266.952-215.615-482.567-477.433-482.567-266.952 0-482.567 215.615-482.567 482.567 0 261.818 215.615 477.433 482.567 477.433 261.818 0 477.433-215.615 477.433-477.433zM627.508 355.423c-20.535 0-41.069 20.535-41.069 41.069 0 20.535 20.535 41.069 41.069 41.069 25.669 0 41.069-20.535 41.069-41.069 0-20.535-15.401-41.069-41.069-41.069zM514.567 422.16c-51.337 0-92.407 41.069-92.407 92.407s41.069 92.407 92.407 92.407c51.337 0 92.407-41.069 92.407-92.407 0-51.337-41.069-92.407-92.407-92.407z" p-id="5444" fill="#337ab7"></path></svg>
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
</body>
</html>
<script>
    $(".messageheader a").click(function () {
        $(".messageheader a").each(function () {
            $(this).removeClass("currentdate");
        });
        $(this).addClass("currentdate");
        //发送ajax请求
        var currentdate=$(this).attr("data-date");
        $.ajax({
            type: "GET",
            url: "/waimai/message",
            data: {currentdate:currentdate},
            dataType: "json",
            success: function(data){
                console.log(data);
                if(data.length==0){
                    $(".messageul").hide();
                    $(".nomessage").show();
                }
                else{
                    var messagecontent='';
                    for(var i = 0; i < data.length; i++){
                        var message='<li>' +
                            '                    <div style="height:30%;width: 100%">\n' +
                            '                        <label style="float:left;margin-left:5px;">'+data[i].foodname+'-----'+data[i].reminder_time+'</label>' +
                            '                        <label style="float:right;margin-right:5px;">评分：'+data[i].rating+'</label></div>' +
                            '                    <div style="height:30%;width: 100%">\n' +
                            '                        <label style="float:left;margin-top:20px;margin-left:5px;">1份：'+data[i].price+'元，2份：'+data[i].twoprice+'元</label>' +
                            '                        <svg t="1523113470569" class="icon" style="float:right;margin-top:20px;margin-right:20px;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1911" xmlns:xlink="http://www.w3.org/1999/xlink" width="20" height="20"><defs><style type="text/css"></style></defs><path d="M782.8 511.1 351.9 942c-22.8 22.8-59.8 22.8-82.6 0-22.8-22.8-22.8-59.8 0-82.6L617.5 511.1 269.2 162.8c-22.8-22.8-22.8-59.8 0-82.6 22.8-22.8 59.8-22.8 82.6 0L782.8 511.1 782.8 511.1zM782.8 511.1" p-id="1912" fill="#2c2c2c"></path></svg>' +
                            '                    </div>' +
                            '                    <div style="float:right;margin-top:5px;margin-right:6px;">' +
                            '                        <a href="\'+data[i].shopurl+\'">店铺：'+data[i].shopname+'</a>' +
                            '                    </div>' +
                            '                </li>';
                        messagecontent=messagecontent+message;
                    }
                    $(".messageul").html(messagecontent).show();
                    $(".nomessage").hide();
                }

            }
        });
    })
</script>
