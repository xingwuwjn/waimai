package com.waimai.dao;

import com.waimai.dao.common.BaseMybatisDAO;
import com.waimai.entity.ElmFood;
import com.waimai.entity.ElmShop;
import com.waimai.entity.Message;
import com.waimai.entity.Mycount;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class MessageDao extends BaseMybatisDAO {

    private final String MAPPER_NAMESPACE = "com.waimai.dao.mapper.MessageMapper";
    /**
     * 获取所有的商铺
     */
    public List<Message> getMessageByOpenid(Map<String,Object> map){
        String sqlId=MAPPER_NAMESPACE + ".getMessageByOpenid";
        return super.findAll(sqlId,map);
    }


    /**
     * 批量插入提醒数据
     */
    public void insertData(List<Message> list){
        String sqlId=MAPPER_NAMESPACE + ".batchInsertMessage";
        super.saveBatch(sqlId,list);
    }

    /**
     * 依据时间，openid查询出所对应的要推送的消息
     *
     */

     public List<Message> getMessageBytime(Map map){
         String sqlId=MAPPER_NAMESPACE + ".getMessageBytime";
         return super.findAll(sqlId,map);
     }

    /**
     * 依据message查询出message
     */
    public Message getMessageByMessage(Message message){
        String sqlId=MAPPER_NAMESPACE + ".getMessageByMessage";
        return super.findOne(sqlId,message);
    }
}
