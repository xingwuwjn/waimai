package com.waimai.entity;

import org.springframework.beans.factory.FactoryBean;

import java.util.HashMap;
import java.util.Map;

public class ConfigMapFactory implements FactoryBean<Map<String,Object>> {
    @Override
    public Map<String,Object> getObject() throws Exception {
        Map<String,Object> configMap = new HashMap<String,Object>();
        configMap.put("suiji", "随机");
        configMap.put("noodle", "面食");
        configMap.put("claypot", "煲仔");
        configMap.put("shaguo", "砂锅");
        configMap.put("malatang", "麻辣烫,米线");
        configMap.put("zhou", "粥");
        configMap.put("liangpi", "凉皮");
        configMap.put("hanbao", "炸鸡汉堡");
        configMap.put("shuijiao", "水饺");
        return configMap;
    }
    @Override
    public Class<?> getObjectType() {
        return Map.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
