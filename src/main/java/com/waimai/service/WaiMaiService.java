package com.waimai.service;


import com.mysql.cj.mysqlx.protobuf.MysqlxCrud;
import com.waimai.dao.ElmShopDao;
import com.waimai.dao.MycountDao;
import com.waimai.dao.WaimaiDao;
import com.waimai.entity.ElmCity;
import com.waimai.entity.ElmShop;
import com.waimai.entity.Mycount;
import com.waimai.utils.GeoHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WaiMaiService {
    @Autowired
    WaimaiDao waimaiDao;
    @Autowired
    MycountDao mycountDao;

    @Autowired
    ElmShopDao elmShopDao;


    /**
     * 获取所有城市
     * @return
     */
    public Map<String,List<ElmCity>> getAllCity(){
        List<ElmCity> cityes=waimaiDao.getAllCity();
        Map<String,List<ElmCity>> map=new HashMap<>();
        for (ElmCity elmCity:cityes){
             if(map.get(elmCity.getCitytype())==null){
                 List<ElmCity> list=new ArrayList<>();
                 list.add(elmCity);
                 map.put(elmCity.getCitytype(),list);
             }
             else{
                 List<ElmCity> list=map.get(elmCity.getCitytype());
                 list.add(elmCity);
                 map.put(elmCity.getCitytype(),list);

             }
        }
        return map;
    }

    //计算出geohash
    public void updateGeohash(){
        List<ElmCity> cityes=waimaiDao.getAllCity();
        Map<Long,Object> map=new HashMap<Long, Object>();
        for (ElmCity elmCity:cityes){
             Long id=elmCity.getId();
             Double latitude=Double.parseDouble(elmCity.getLatitude());
             Double longitude=Double.parseDouble(elmCity.getLongitude());
             String geohash=new GeoHash().encode(latitude,longitude );
             elmCity.setGeohash(geohash);
             waimaiDao.updateCityById(elmCity);
        }
    }

    /**
     * 获取所有的商铺
     * @return
     */
     public List<ElmShop> getAllShop(){
         return elmShopDao.getAllElmShop();
     }



    //插入mycount数据
    public void insertMycount(Mycount mycount){
        mycountDao.insertData(mycount);
    }


    //依据openid查询出mycount数据
    public List<Mycount> getMycountByOpenid(String openid){
        return mycountDao.getMycountByOpenid(openid);
    }

    //依据openid查询出mycount数据
    public List<Mycount> getAllMycount(){
        return mycountDao.getAllMycount();
    }


    //依据id删除我的定时
    public void deleteMycountById(int id){
        mycountDao.deleteMycountById(id);
    }

}
