package com.waimai.dao;

import com.waimai.dao.common.BaseMybatisDAO;
import com.waimai.entity.BaseEntity;
import com.waimai.entity.ElmCity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class WaimaiDao extends BaseMybatisDAO {
    private final String MAPPER_NAMESPACE = "com.waimai.dao.mapper.ElmMapper";

    /**
     * 获取所有城市
     * @return
     */
    public List<ElmCity> getAllCity(){
        return super.findAll(MAPPER_NAMESPACE + ".selectAllCity");
    }

    /**
     * 依据id更新城市
     */
    public void updateCityById(ElmCity elmCity){
        String sqlId=MAPPER_NAMESPACE + ".updateCityById";
        super.update(sqlId,elmCity);
    }



}

