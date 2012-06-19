package com.trinea.java.common.service;

import java.io.Serializable;

import com.trinea.java.common.entity.CacheObject;

/**
 * 缓存满时删除数据的类型
 * 
 * @author Trinea 2011-12-26 下午11:40:39
 */
public interface CacheFullRemoveType<V> extends Serializable {

    /**
     * 比较两个数据<br/>
     * <br/>
     * <ul>
     * <strong>关于比较的结果</strong>
     * <li>obj1大于obj2返回1</li>
     * <li>obj1等于obj2返回0</li>
     * <li>obj1小于obj2返回-1</li>
     * </ul>
     * 
     * @param obj1 数据1
     * @param obj2 数据2
     * @return
     */
    public int compare(CacheObject<V> obj1, CacheObject<V> obj2);
}
