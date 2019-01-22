package com.waimai.entity;


import lombok.Data;

import java.util.Date;

@Data
public class Message extends BaseEntity{

    private String openid;//微信openid
    private String foodname;//食物名称
    private String shopname;//店铺名称
    private Float price;//价格
    private Float twoprice;//两份价格
    private Float rating;  //评价
    private String shopurl;   //店铺url
    private String repeate_time;//重复时间
    private String reminder_time;//提醒时间
    private String today;//当前时间
}
