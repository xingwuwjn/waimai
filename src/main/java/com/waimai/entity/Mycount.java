package com.waimai.entity;


import lombok.Data;

@Data
public class Mycount extends BaseEntity{
    private String openid;
    private String taste;//口味
    private String reminder_time;//提醒时间
    private String repeate_time;
    private String latitude;
    private String longitude;
    private String geohash;
    private String address;
}
