package com.waimai.entity;

import lombok.Data;

import java.sql.Time;


/**
 * 饿了么商铺实体类
 */
@Data
public class ElmShop extends BaseEntity{
    private String name;
    private String status;
    private String shopid;
    private String address;
    private String tips102;
    private String latitude;//经度
    private String longitude;//纬度
    private int minsendprice;   //起送费
    private String openhour;   //开门时间
    private String closehour;  //关闭时间
    private String delivery_fee;   //配送费用
    private String order_lead_time;  //平均配送时长
    private String phone;  //手机号
    private Float rating;  //评分
    private int recent_order_num;//最近订单数量
}
