package com.waimai.service.common;

import com.waimai.dao.MessageDao;
import com.waimai.entity.ElmShop;
import com.waimai.entity.Message;
import com.waimai.entity.Mycount;
import com.waimai.entity.RecommendFood;
import com.waimai.service.WaiMaiService;
import com.waimai.utils.DateUtils;
import com.waimai.utils.DistanceUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PushCommonService {
    @Autowired
    WaiMaiService waiMaiService;

    @Autowired
    MessageDao messageDao;

    //获取附近4公里以内的商家
    public List<String> getAllShop(Mycount mycount){
        List<ElmShop> elmShops=waiMaiService.getAllShop();
        List<String> list=new ArrayList<>();//存放4公里以内的所有商家id
        for(ElmShop elmShop:elmShops){
            String shoplatitude=elmShop.getLatitude();//商铺经度
            String shoplongitude=elmShop.getLongitude();
            String mylatitude=mycount.getLatitude();//我的未知的经度
            String mylongtitude=mycount.getLongitude();
            String distance=DistanceUtil.getDistance(shoplatitude,shoplongitude,mylatitude,mylongtitude);
            if(Integer.parseInt(distance)<4000){//将距离小于4千米的放入新的list中
                list.add(elmShop.getShopid());
            }
        }
        return list;
    }

    /**
     * 依据我的账户，以及查询出来的商品，进行一定规则下的过滤
     * @param mycount
     * @param foods
     */
    public List<Message> filterFood(Mycount mycount,List<RecommendFood> foods){
        List<RecommendFood> ratingFoods=new ArrayList<>(foods);

        List<RecommendFood> recommendFoods=new ArrayList<>();

        //3:依据评分进行排序
        Collections.sort(ratingFoods);
        for(int i=0;i<foods.size();i++){
            System.out.println(foods.get(i));
        }
        System.out.println("月售前20");
        for(int i=0;i<ratingFoods.size();i++){
            System.out.println(ratingFoods.get(i));
        }
        System.out.println("评分前20");
        //4:取出销量前15，评价前15的所有外卖商品
        for(int i=0;i<15;i++){//遍历评价前10销量前10的
            for(int j=0;j<15;j++) {
                if (foods.get(i).getFood_id().equals(ratingFoods.get(j).getFood_id())) {
                    recommendFoods.add(foods.get(i));
                }
            }
        }
        List<Message> messages=new ArrayList<>();   //存放消息的集合
        //5计算价格
        for(RecommendFood recommendFood:recommendFoods){
            String tips_102=recommendFood.getTips_102();//满减

            String regEx = "[^0-9]";//匹配指定范围内的数字
            //Pattern是一个正则表达式经编译后的表现模式
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(tips_102);
            String st="";
            if(m.find()){
                st=m.replaceAll(" ").trim();
            }
            Map<Integer,Integer> tipsmap=new HashMap<>();//存放满减的map,key为满，value为减
            if(st!=""){
                String ss[]=st.replace("  "," ").split(" ");
                for (int i = 0; i < ss.length; i++){
                    if(i%2==0){
                        tipsmap.put(Integer.parseInt(ss[i]),Integer.parseInt(ss[i+1]));
                    }
                }
            }
            Float delivery_fee=recommendFood.getDelivery_fee();//配送费
            int package_fee=recommendFood.getPacking_fee();//打包费
            int price=recommendFood.getPrice();//商品价格
            recommendFood.getMinsendprice();//获取最小配送费
            //计算一份价格
            Float oneprice=(float)price+package_fee;
            Float tmponeprice=oneprice;
            if(tipsmap.size()>=1){
                for (Map.Entry<Integer, Integer> entry : tipsmap.entrySet()) {
                    int key = entry.getKey();
                    int value = entry.getValue();
                    if(oneprice>=(float)key){
                        tmponeprice=oneprice;
                        tmponeprice=tmponeprice-value;
                    }

                }
            }
            //计算两份价格
            Float twoprice=(float)price*2+package_fee*2;
            Float tmptwoprice=twoprice;//最终的价格（还没有加运费）
            if(tipsmap.size()>=1){
                for (Map.Entry<Integer, Integer> entry : tipsmap.entrySet()) {
                    int key = entry.getKey();
                    int value = entry.getValue();
                    if(twoprice>=(float)key){
                        tmptwoprice=twoprice;
                        tmptwoprice=tmptwoprice-value;
                    }

                }
            }
            if(delivery_fee>7){
                delivery_fee=(float)7;
            }
            oneprice=tmponeprice+delivery_fee;//最终一份的价格
            twoprice=tmptwoprice+delivery_fee;//最终两份的价格

            Message message=new Message();
            message.setFoodname(recommendFood.getName());//设置食品名称
            message.setPrice(oneprice);//一份的价格
            message.setRating(recommendFood.getRating());//评论数
            message.setShopname(recommendFood.getShopname());//店铺名称
            message.setTwoprice(twoprice);//两份的价格
            message.setOpenid(mycount.getOpenid());//设置openid
            message.setReminder_time(mycount.getReminder_time());//提醒时间
            message.setRepeate_time(mycount.getRepeate_time());//重复时间
            message.setShopurl("https://www.ele.me/shop/"+recommendFood.getShop_id());
            //获取今天的时间
            message.setToday(DateUtils.getday());
            messages.add(message);
        }
        //获取相同名称的价格最便宜的
        Map<String,Message> tmpMap=new HashMap<>();
        for(Message message:messages){
            String name=message.getFoodname();
            Float twoprice=message.getTwoprice();
            if(tmpMap.get(name)==null){
                tmpMap.put(name,message);
            }
            else{
                if(tmpMap.get(name).getTwoprice()>twoprice){  //如果原有的价格大于当前的价格则覆盖
                    tmpMap.put(name,message);
                }
            }

        }
        //将map中的消息转到list中
        List<Message> insertMessage=new ArrayList<>();
        for (Map.Entry<String, Message> entry : tmpMap.entrySet()) {
            Message message = entry.getValue();
            insertMessage.add(message);
        }
       return insertMessage;

    }
}
