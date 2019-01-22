package com.waimai.entity;


import lombok.Data;

@Data
public class ElmCity extends BaseEntity{
    private String citytype;
    private String latitude;
    private String longitude;
    private String cityname;
    private String cityid;
    private String geohash;

}
