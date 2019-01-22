package com.waimai.service;

import com.waimai.dao.WaimaiDao;
import com.waimai.entity.ElmCity;

import com.waimai.entity.ElmShop;
import com.waimai.entity.Mycount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)//表示整合JUnit4进行测试
@ContextConfiguration(locations={"classpath:applicationContext.xml"})//加载spring配置文件
public class WaiMaiServiceTest {
    @Autowired
    WaiMaiService waiMaiService;
    @Test
    public void getAllCity() {
        Map<String,List<ElmCity>> cities=waiMaiService.getAllCity();
        for (Map.Entry<String,List<ElmCity>> entry : cities.entrySet()) {
            System.out.println(entry.getKey());
            for(ElmCity elmCity:entry.getValue()){
                System.out.println(elmCity.toString());
            }
        }
    }
    @Test
    public void updateGeohash(){

        waiMaiService.updateGeohash();
    }


    @Test
    public void getMycountByOpenid(){
        List <Mycount> list=waiMaiService.getMycountByOpenid("abc");
        list.forEach(System.out::println);
    }


    @Test
    public void getAllElmshop(){
        List<ElmShop> list=waiMaiService.getAllShop();
        System.out.println(list.size());
    }

}