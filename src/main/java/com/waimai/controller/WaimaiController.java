package com.waimai.controller;


import com.waimai.constants.SearchType;
import com.waimai.context.SpringApplicationContext;
import com.waimai.entity.ElmCity;
import com.waimai.entity.Message;
import com.waimai.entity.Mycount;
import com.waimai.service.MessageService;
import com.waimai.service.WaiMaiService;
import com.waimai.service.common.WaimaiRecommend;
import com.waimai.utils.DateUtils;
import com.waimai.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value = "/waimai")
public class WaimaiController {
    @Autowired
    WaiMaiService waimaiService;


    @Autowired
    MessageService messageService;


    @Resource(name="configMap")//这边需要使用Resource注解
    private Map<String,Object> configMap;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(HttpServletRequest request, HttpSession session, Model model){
        String openid=(String)session.getAttribute("openid");
        Map<String,List<ElmCity>> cities=waimaiService.getAllCity();
        model.addAttribute("pianhao",configMap);
        model.addAttribute("cities",cities);
        model.addAttribute("openid",openid);
        return "/waimai/main";
    }

    /**
     * 依据关键词查询出附近的地址
     * @param request
     * @return
     */
    @RequestMapping(value = "/point", method = RequestMethod.POST)
    @ResponseBody
    public String getKeypoint(HttpServletRequest request){
        String geohash=request.getParameter("geohash");
        String point=request.getParameter("keyword");
        String url="https://www.ele.me/restapi/v2/pois?extras%5B%5D=count&limit=20&type=nearby";
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(url);
        stringBuffer.append("&geohash="+geohash);
        stringBuffer.append("&keyword="+point);
        String totalurl=stringBuffer.toString();
        String result=HttpUtil.httpsRequest(totalurl,"GET",null);
        return result;
    }

    /**
     * 到达message页面，查询出当日的外卖消息
     * @param request
     * @return
     */
    @RequestMapping(value = "/allmessage", method = RequestMethod.GET)
    public String allmessage(HttpServletRequest request,HttpSession session,Model model){
        session.setMaxInactiveInterval(-1);
        String openid=request.getParameter("openid");
        if(openid!=null){
            session.setAttribute("openid",openid);
        }
        else{
            openid= (String) session.getAttribute("openid");
        }
        //查询出最新的外卖信息
        String today=DateUtils.getday();//获取今日的日期
        String time=DateUtils.getTime();
        //依据时间,openid查询出外卖推荐信息
        List<Message> messages=messageService.getMessageByOpenid(today,openid,time);
        //获取最近7天的日期
        List<String> datest=DateUtils.getbeforedate(DateUtils.getToday());
        LinkedHashMap<String,String> datemap=new LinkedHashMap<>();
        for (String date:datest){
            Date date1=DateUtils.StringToDate(date,"yyyy-MM-dd");
            String weekst=DateUtils.getWeekOfDate(date1);
            datemap.put(weekst.substring(weekst.length()-1,weekst.length()),date);
        }
        model.addAttribute("datemap",datemap);
        model.addAttribute("messages",messages);
        return "/waimai/message";
    }




    /**
     * 异步，依据时间获取推荐的消息（从信息表中直接取出）
     * @param request
     * @return
     */

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    @ResponseBody
    public List<Message> message(HttpServletRequest request,HttpSession session,Model model){
        String currentdate=request.getParameter("currentdate");
        String openid= (String) session.getAttribute("openid");
        //依据时间,openid查询出外卖推荐信息
        String time=DateUtils.getTime();
        List<Message> messages=messageService.getMessageByOpenid(currentdate,openid,time);
        return messages;
    }




    /**
     * 到达我的页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/mycount", method = RequestMethod.GET)
    public String mycount(HttpServletRequest request,HttpSession session,Model model){
        //依据openid 查询出我的定时
        String openid= (String) session.getAttribute("openid");
        List<Mycount> list=waimaiService.getMycountByOpenid(openid);
        for(Mycount mycount:list){
            mycount.setTaste((String)configMap.get(mycount.getTaste()));
        }
        model.addAttribute("mycounts",list);
        model.addAttribute("openid",openid);
        return "/waimai/mycount";
    }

    /**
     * 插入定时数据
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertData", method = RequestMethod.POST)
    public String insertData(HttpServletRequest request,
                             @RequestParam(value="openid") String openid,
                             @RequestParam(value="latitude") String latitude,
                             @RequestParam(value="longitude") String longitude,
                             @RequestParam(value="geohash") String geohash,
                             @RequestParam(value="recommendtype") String recommendtype,
                             @RequestParam(value="totaltime") String totaltime,
                             @RequestParam(value="reminder_time") String reminder_time,
                             @RequestParam(value="address") String address){
        Mycount mycount=new Mycount();
        mycount.setGeohash(geohash);
        mycount.setLatitude(latitude);
        mycount.setLongitude(longitude);
        mycount.setOpenid(openid);
        mycount.setRepeate_time(totaltime.substring(0,totaltime.length()-1));
        mycount.setTaste(recommendtype);
        mycount.setReminder_time(reminder_time);
        mycount.setAddress(address);//设置地址
        waimaiService.insertMycount(mycount);
        return "1";

    }


    /**
     * 删除定时数据
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteData", method = RequestMethod.POST)
    public String deleteData(HttpServletRequest request,
                             @RequestParam(value="id") int id
                             ){
        waimaiService.deleteMycountById(id);
        return "1";

    }




}
