package com.waimai.dao;

import com.waimai.dao.common.BaseMybatisDAO;
import com.waimai.entity.ElmFood;
import com.waimai.entity.ElmShop;
import com.waimai.entity.Mycount;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 商铺dao
 */
@Repository
public class ElmShopDao extends BaseMybatisDAO {
    private final String MAPPER_NAMESPACE = "com.waimai.dao.mapper.ElmShopMapper";
    /**
     * 获取所有的商铺
     */
    public List<ElmShop> getAllElmShop(){
        String sqlId=MAPPER_NAMESPACE + ".getAllElmShop";
        return super.findAll(sqlId);
    }


    //依据口味以及商家id获取商品
    public List<ElmFood> getElmFood(){
        String sqlId=MAPPER_NAMESPACE + "";
        return super.findAll(sqlId);
    }
}
