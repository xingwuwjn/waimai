$(function () {

    //口味js
    $(".recommendtype button").click(function () {
        $(".recommendtype button").each(function () {
            $(this).removeClass("btn-primary");
            $(this).addClass("btn-default");
        });
        var recommendname=$(this).attr("data-name");
        $(this).addClass("btn-primary");
        $(this).removeClass("btn-default");
        $("input[name='recommendtype']").val(recommendname);
    });
    
    
    //提醒时间
    $(".reminder_time button").click(function () {
        if($(this).hasClass("btn-primary")){
            $(this).removeClass("btn-primary");
            $(this).addClass("btn-default");
            var time=total_time();
            $("input[name='totaltime']").val(time);
        }
        else{
            $(this).addClass("btn-primary");
            $(this).removeClass("btn-default");
            var time=total_time();
            $("input[name='totaltime']").val(time);
        }
    });
    //统计所有选中的时间
    function total_time() {
        var totaltime="";
        $(".reminder_time button").each(function () {
            if($(this).hasClass("btn-primary")){
                totaltime=totaltime+$(this).html()+",";
            }
        });
        return totaltime;
    }

});