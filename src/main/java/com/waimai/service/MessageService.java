package com.waimai.service;


import com.waimai.dao.MessageDao;
import com.waimai.entity.ElmShop;
import com.waimai.entity.Message;
import com.waimai.entity.Mycount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    @Autowired
    MessageDao messageDao;

    /**
     * 插入消息数据,在插入之前查询是否有相同数据
     * @param insertMessage
     */
    public void insertData(List<Message> insertMessage){
        for(Message message:insertMessage){
            Message message1=messageDao.getMessageByMessage(message);
            if(message1!=null){
                insertMessage.remove(message);
            }
        }
        messageDao.insertData(insertMessage);
    }

    /**
     * 依据openid以及今天的时间查询出消息数据
     * @param today
     * @param openid
     * @return
     */
    public List<Message> getMessageByOpenid(String today, String openid,String time){
        Map<String,Object> map=new HashMap<>();
        map.put("today",today);
        map.put("openid",openid);
        map.put("time",time);
        return messageDao.getMessageByOpenid(map);
    }

    /**
     * 依据时间，openid查询出所对应的要推送的消息
     */
    public List<Message> getMessageBytime(Mycount mycount,String today){
        Map<String,String> map=new HashMap<>();
        map.put("openid",mycount.getOpenid());
        map.put("reminder_time",mycount.getReminder_time());
        map.put("today",today);
        return messageDao.getMessageBytime(map);
    }

    public Message getMessageByMessage(Message message){
        return messageDao.getMessageByMessage(message);
    }




}
