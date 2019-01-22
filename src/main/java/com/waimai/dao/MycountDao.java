package com.waimai.dao;

import com.waimai.dao.common.BaseMybatisDAO;
import com.waimai.entity.Mycount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MycountDao extends BaseMybatisDAO {
    private final String MAPPER_NAMESPACE = "com.waimai.dao.mapper.MycountMapper";

    /**
     * 插入定时数据
     */
    public void insertData(Mycount mycount){
        String sqlId=MAPPER_NAMESPACE + ".insert";
        super.save(sqlId,mycount);
    }

    /**
     * 依据openid获得我的账户
     */
    public List<Mycount> getMycountByOpenid(String openid){
        String sqlId=MAPPER_NAMESPACE + ".getMycountByOpenid";
        return super.findAll(sqlId,openid);
    }

    /**
     * 获取所有的账户
     */
    public List<Mycount> getAllMycount(){
        String sqlId=MAPPER_NAMESPACE + ".getAllMycount";
        return super.findAll(sqlId);
    }
    //删除我的账户
    public void deleteMycountById(int id){
        String sqlId=MAPPER_NAMESPACE + ".deleteMycountById";
        super.delete(sqlId,id);
    }

}
