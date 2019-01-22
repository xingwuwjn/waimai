package com.waimai.service.pushmessageService;

import com.waimai.dao.MessageDao;
import com.waimai.dao.RecommendFoodDao;
import com.waimai.entity.Message;
import com.waimai.entity.Mycount;
import com.waimai.entity.RecommendFood;
import com.waimai.service.common.PushCommonService;
import com.waimai.service.common.WaimaiRecommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.LabelView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("suiji")
public class SuiJi extends PushCommonService implements WaimaiRecommend {
    @Autowired
    RecommendFoodDao recommendFoodDao;

    @Autowired
    MessageDao messageDao;
    @Override
    public void getFood(Mycount mycount) {
        //1:依据经度，纬度计算距离商家的距离,获取所有3公里以内的商家id
        List<String> list=super.getAllShop(mycount);
        //2:依据商家id以及口味获得商品
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("shopids",list);
        map.put("keyword1","%面%");
        List<String> keywords=new ArrayList<>();
        keywords.add("%煲仔%");
        keywords.add("%鸡堡%");
        keywords.add("%鸡腿堡%");
        keywords.add("%牛肉堡%");
        keywords.add("%鸡翅%");
        keywords.add("%鸡腿饭%");
        keywords.add("%鸡柳%");
        keywords.add("%鸡肉卷%");
        keywords.add("%虾堡%");
        keywords.add("%双层煲%");
        keywords.add("%鸡肉饭%");
        keywords.add("%烤堡%");
        keywords.add("%鸡块%");
        keywords.add("%鸡翅%");
        keywords.add("%枪腿%");
        keywords.add("%凉皮%");
        keywords.add("%粥%");
        keywords.add("%包子%");
        keywords.add("%砂锅%");
        keywords.add("%水饺%");
        keywords.add("%干饺%");
        keywords.add("%米线%");
        keywords.add("%麻辣烫%");
        map.put("keywords",keywords);
        List<RecommendFood> foods=recommendFoodDao.getElmFood(map);//取出销量前20
        //通过一定的规则获取最好的几个商品
        List<Message> insertMessage=super.filterFood(mycount,foods);
        //批量插入消息数据
        messageDao.insertData(insertMessage);
    }
}
