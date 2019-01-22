package com.waimai.dao.common;


import com.waimai.entity.BaseEntity;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mybatis基类
 */
public class BaseMybatisDAO {
	
	@Autowired
    SqlSessionTemplate template;
	
	/**
	 * 查询指定SQL语句的所有记录
	 * @param sqlId	SQL语句ID
	 * @return	查询到的结果集合
	 */
	public <T extends BaseEntity> List<T> findAll(String sqlId) {
		return template.selectList(sqlId);
	}
	
	/**
	 * 查询指定SQL语句的所有记录
	 * @param sqlId	SQL语句ID
	 * @param params	条件参数
	 * @return	查询到的结果集合
	 */
	public <T extends BaseEntity> List<T> findAll(String sqlId, Map<String, Object> params) {
		return template.selectList(sqlId, params);
	}
	
	/**
	 * 查询指定SQL语句的所有记录
	 * @param sqlId	SQL语句ID
	 * @param param	条件参数
	 * @return	查询到的结果集合
	 */
	public <T extends BaseEntity> List<T> findAll(String sqlId, Object param) {
		return template.selectList(sqlId, param);
	}
	

	
	/**
	 * 查询指定SQL语句的一条记录
	 * @param sqlId	SQL语句ID
	 * @return	查询到的实体
	 */
	public <T extends BaseEntity> T findOne(String sqlId) {
		return template.selectOne(sqlId);
	}
	
	/**
	 * 根据条件查询指定SQL语句的一条记录
	 * @param sqlId	SQL语句ID
	 * @param <T> 返回值类型
	 * @param params	条件参数
	 * @return	查询到的结果
	 */
	public <T extends BaseEntity> T findOne(String sqlId, Map<String, Object> params) {
		return template.selectOne(sqlId, params);
	}
	
	public Long findId(String sqlId, Object param) {
		return template.selectOne(sqlId, param);
	}
	
	/**
	 * 根据条件查询指定SQL语句的一条记录，主要用于关联查询的情况
	 * @param sqlId	SQL语句ID
	 * @param param	条件参数
	 * @return	查询到的结果
	 */
	public <T extends BaseEntity> T findOne(String sqlId, Object param) {
		return template.selectOne(sqlId, param);
	}
	
	public <T> T findOneObject(String sqlId, Map<String, Object> params) {
		return template.selectOne(sqlId, params);
	}
	
	/**
	 * 查询指定SQL语句所包含的记录数
	 * @param sqlId	SQL语句ID
	 * @param params	参数
	 * @return	符合条件的记录数
	 */
	public long count(String sqlId, Map<String, Object> params) {
		return template.selectOne(sqlId, params);
	}

	
	public <T extends BaseEntity> int save(String sqlId, T entity) {
		return template.insert(sqlId, entity);
	}
	
	/**
	 * 插入指定SQL的数据
	 * @param sqlId	SQL语句ID
	 * @param entities	要插入的实体集合
	 * @return	
	 */
	public <T extends BaseEntity> int saveBatch(String sqlId, List<T> entities) {
		return template.insert(sqlId, entities);
	}
	

	/**
	 * 更新指定SQL的数据
	 * @param sqlId	SQL语句ID
	 * @param entities	要更新的对象
	 * @return	成功更新的记录数
	 */
	public <T extends BaseEntity> int update(String sqlId, T... entities) {
		if (null != entities && entities.length == 1) {
			return template.update(sqlId, entities[0]);
		}
		return template.update(sqlId, Arrays.asList(entities));
	}
	
	/**
	 * 更新指定SQL的数据
	 * @param sqlId	SQL语句ID
	 * @param params	参数
	 * @return	成功更新的记录数
	 */
	public int update(String sqlId, Map<Long, Object> params) {
		return template.update(sqlId, params);
	}
	
	/**
	 * 删除指定条件的SQL的数据
	 * @param sqlId
	 * @param key	
	 * @return
	 */
	public int delete(String sqlId, Object key) {
		return template.delete(sqlId, key);
	}
	
	/**
	 * 删除指定SQL的数据
	 * @param sqlId	SQL语句ID
	 * @param params	查询参数
	 * @return	成功删除记录数
	 */
	public int delete(String sqlId, Map<String, Object> params) {
		return template.delete(sqlId, params);
	}

	
}