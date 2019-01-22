package com.waimai.constants;

import java.util.HashMap;
import java.util.Map;

public class SearchType {
    public final static Map map = new HashMap<String,String>();
    static {
        map.put("SuiJi", "随机");
        map.put("noodle", "面食");
        map.put("claypot", "煲仔");
        map.put("shaguo", "砂锅");
        map.put("malatang", "麻辣烫,米线");
        map.put("zhou", "粥,包子");
        map.put("liangpi", "凉皮");
        map.put("hanbao", "炸鸡汉堡");
        map.put("shuijiao", "水饺");
    }
    public SearchType(){

    }
//    public static final Map<String,String> map = new HashMap<String,String>(){{
//
//    }};
}
