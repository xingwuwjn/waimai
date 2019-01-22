package com.waimai.service.pushmessageService;

import com.waimai.dao.MessageDao;
import com.waimai.dao.RecommendFoodDao;
import com.waimai.entity.ElmShop;
import com.waimai.entity.Message;
import com.waimai.entity.Mycount;
import com.waimai.entity.RecommendFood;
import com.waimai.service.WaiMaiService;
import com.waimai.service.common.PushCommonService;
import com.waimai.service.common.WaimaiRecommend;
import com.waimai.utils.DistanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 查询出所有的煲仔饭
 */
@Service("claypot")
public class ClayPotService extends PushCommonService implements WaimaiRecommend {

     @Autowired
     RecommendFoodDao recommendFoodDao;

     @Autowired
     MessageDao messageDao;

    /**
     *
     * @param mycount    当前设置的提醒账户
     * @return
     */
    @Override
    public void getFood(Mycount mycount) {
        //1:依据经度，纬度计算距离商家的距离,获取所有4公里以内的商家id
        List<String> list=super.getAllShop(mycount);
        //2:依据商家id以及口味获得商品
         Map<String,Object> map=new HashMap<String,Object>();
         map.put("shopids",list);
         map.put("keyword1","%煲仔%");
         List<String> keywords=new ArrayList<>();
         keywords.add("%盖饭%");
         map.put("keywords",keywords);
         List<RecommendFood> foods=recommendFoodDao.getElmFood(map);//取出销量前20的煲仔饭
         //通过一定的规则获取最好的几个商品
         List<Message> insertMessage=super.filterFood(mycount,foods);
        //批量插入消息数据
        messageDao.insertData(insertMessage);
    }
}
