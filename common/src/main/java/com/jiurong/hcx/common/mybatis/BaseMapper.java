package com.jiurong.hcx.common.mybatis;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author soyeajr
 * @date 2019-2-28
 * @Description
 */
public interface BaseMapper<T> {
    /**
     * 保存一个实体
     *
     * @param t 实体
     * @return 保存的行数
     */
    int save(T t);

    /**
     * 删除一个实体
     *
     * @param id 实体id
     * @return 删除的行数
     */
    int deleteById(@Param("id") String id);

    /**
     * 删除根据条件
     *
     * @param params 参数Map
     * @return 删除的行数
     */
    int deleteBySelective(Map params);

    /**
     * 更新一个实体
     *
     * @param t 实体
     * @return 更新的行数
     */
    int updateById(T t);

    /**
     * 更新一个实体可选字段
     *
     * @param t 实体
     * @return 更新的行数
     */
    int updateByIdSelective(T t);

    /**
     * 查询一个实体
     *
     * @param id id
     * @return 实体
     */
    T selectById(@Param("id") String id);

    /**
     * 查询实体
     *
     * @param params 参数Map
     * @return 实体
     */
    T selectOneBySelective(Map params);

    /**
     * 查询实体列表
     *
     * @param params 参数Map
     * @return 实体列表
     */
    List<T> selectBySelective(Map params);

    /**
     * 查询数量
     *
     * @param params 参数Map
     * @return 数量
     */
    int count(Map params);
}
