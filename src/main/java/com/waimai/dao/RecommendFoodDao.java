package com.waimai.dao;

import com.waimai.dao.common.BaseMybatisDAO;
import com.waimai.entity.RecommendFood;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class RecommendFoodDao extends BaseMybatisDAO {
    private final String MAPPER_NAMESPACE = "com.waimai.dao.mapper.RecommendFoodMapper";


    //依据口味以及商家id获取商品
    public List<RecommendFood> getElmFood(Map map){
        String sqlId=MAPPER_NAMESPACE + ".getElmFood";
        return super.findAll(sqlId,map);
    }

}
