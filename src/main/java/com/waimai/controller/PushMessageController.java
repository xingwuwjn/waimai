package com.waimai.controller;


import com.alibaba.fastjson.JSON;
import com.waimai.context.SpringApplicationContext;
import com.waimai.entity.Message;
import com.waimai.entity.Mycount;
import com.waimai.entity.TemplateModel;
import com.waimai.service.MessageService;
import com.waimai.service.WaiMaiService;
import com.waimai.service.WxAuthorizeServer;
import com.waimai.service.common.WaimaiRecommend;
import com.waimai.utils.DateUtils;
import com.waimai.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PushMessageController{
    @Autowired
    WxAuthorizeServer wxAuthorizeServer;

    @Autowired
    WaiMaiService waiMaiService;

    @Autowired
    MessageService messageService;

    @Value("#{prop.template_id}")
    String template_id;

    @Value("#{prop.temurl}")
    String url;

    @Value("#{prop.topcolor}")
    String topcolor;

    /**
     * 定时推送外卖消息
     */
//    @Scheduled(cron="0/60 * *  * * ? ")
    public void task() throws IOException {
       System.out.println("发送外卖消息定时任务");
       String access_token=wxAuthorizeServer.getAccess_token();//获取access_token
       String tempurl=String.format("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s",access_token);
       List<Mycount> mycountList=waiMaiService.getAllMycount();
       for(Mycount mycount:mycountList){
           String address=mycount.getAddress();
           String openid=mycount.getOpenid();
           String time=mycount.getReminder_time();
           String repeate_time=mycount.getRepeate_time();
           String xinqi=DateUtils.getWeekOfDate(new Date());//获取今日的是星期几
           if(time.equals(DateUtils.getTime())&&repeate_time.indexOf(xinqi)!=-1){ //时间相等并且星期也相等则进行推荐
               //依据openid,time,以及今天的日期查询出要推荐的商品
               List<Message> messageList=messageService.getMessageBytime(mycount,DateUtils.getday());
               String foodnames="";
               for (Message message:messageList){
                   foodnames=foodnames+message.getFoodname()+",";
               }
               //定义发送的消息
               TemplateModel templateModel=new TemplateModel(mycount.getReminder_time(),foodnames.substring(0,foodnames.length()-1));
               templateModel.setTemplate_id(template_id);
               templateModel.setTopcolor(topcolor);
               templateModel.setUrl(url);
               templateModel.setTouser(openid);//设置openid
               String tempJson=JSON.toJSONString(templateModel);
               String result=HttpUtil.sendPost(tempurl,tempJson);
           }

       }

    }

    /**
     *每1分钟执行一次，定时分析出便宜又美味的外卖
     */
//    @Scheduled(cron="0/60 * *  * * ? ")
    public void analytical(){
        System.out.println("分析外卖消息定时任务");
        //1：取出所有的设置的提醒
        List<Mycount> mycounts=waiMaiService.getAllMycount();
        String today=DateUtils.getday();
        List<Mycount> mycounts1=new ArrayList<>(mycounts);
        for(Mycount mycount:mycounts){
            List<Message> messages=messageService.getMessageBytime(mycount,today);
            if(messages.size()!=0){//已经分析出数据来了，所以不需要
                mycounts1.remove(mycount);
            }
        }
        String xinqi=DateUtils.getWeekOfDate(new Date());//获取今日的是星期几
        for(Mycount mycount:mycounts1){
            String repeate_time=mycount.getRepeate_time();
            if(repeate_time.indexOf(xinqi)!=-1){
                //2:依据口味调用不同的service层分析出有用的数据
                String taste=mycount.getTaste();
                WaimaiRecommend service = SpringApplicationContext.getBean(taste);
                service.getFood(mycount);//依据规则获取商品并且插入到数据库
            }


        }
    }


}
