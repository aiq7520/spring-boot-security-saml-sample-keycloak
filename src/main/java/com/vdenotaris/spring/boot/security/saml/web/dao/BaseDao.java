package com.vdenotaris.spring.boot.security.saml.web.dao;


import com.vdenotaris.spring.boot.security.saml.web.utils.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname BaseDao
 * @Description TODO
 * @Date 2022/4/18 14:05
 * @Created by ge.ji
 */

public interface BaseDao<T> {
    /**
     * 添加实体类
     * @param t
     * @return
     */
    T add(T t);

    /**
     * 更新实体类
     * @param t
     * @return
     */
    T update(T t);

    /**
     * 根据主键ID删除实体类
     * @param id
     */
    void delete(Serializable id);

    /**
     * 根据JPQL语句更新或删除操作
     * @param jpql
     * @param obj
     */
    int executeUpdate(String jpql,Object... obj);

    /**
     * 根据主键ID查找单个实体类
     * @param id
     * @return
     */
    T load(Serializable id);

    /**
     * 根据JPQL语句查询单个实体类
     * @param jpql
     * @param obj（参数可有可无）
     * @return
     */
    T load(String jpql,Object... obj);

    /**
     * 查找所有的实体类
     * @return
     */
    List<T> findAll();

    /**
     * 根据JPQL语句查询集合实体类
     * @param jpql
     * @param obj（参数可有可无）
     * @return
     */
    List<T> find(String jpql,Object... obj);

    /**
     * 聚合查询
     * @param jpql
     * @param obj
     * @return
     */
    Object findByAggregate(String jpql,Object... obj);

    /**
     * 查找总记录数
     * @return
     */
    int count();

    /**
     * 根据JPQL语句查询记录数
     * @param jpql
     * @param obj
     * @return
     */
    int count(String jpql,Object... obj);

    /**
     * 分页查询集合实体类
     * @param firstIndex
     * @param maxResults
     * @return
     */
    List<T> findPage(Integer firstIndex, Integer maxResults);

    /**
     * 根据JPQL语句查询集合实体类
     * @param firstIndex
     * @param maxResults
     * @param jpql
     * @param obj
     * @return
     */
    List<T> findPage(Integer firstIndex, Integer maxResults, String jpql, Object... obj);


    Page findPage(Page page, String jpql, Object... obj);

}
