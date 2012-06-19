package com.trinea.java.common.service;

import com.trinea.java.common.entity.CacheObject;

/**
 * 小型缓存
 * 
 * @author Trinea 2011-12-23 上午01:46:01
 */
public interface Cache<K, V> {

    /**
     * 得到缓存中元素个数
     * 
     * @return
     */
    public int getSize();

    /**
     * 从缓存中获取元素
     * 
     * @param key
     * @return
     */
    public CacheObject<V> get(K key);

    /**
     * 向缓存中添加元素
     * 
     * @param key key
     * @param value 元素值
     * @return
     */
    public void put(K key, V value);

    /**
     * 向缓存中添加元素
     * 
     * @param key key
     * @param obj 元素
     * @return
     */
    public void put(K key, CacheObject<V> obj);

    /**
     * 缓存中某个key是否存在
     * 
     * @param key
     * @return
     */
    public boolean containsKey(K key);

    /**
     * 从缓存中删除某个元素
     * 
     * @param key
     * @return 删除的元素
     */
    public CacheObject<V> remove(K key);

    /**
     * 清空缓存
     */
    public void clear();

    /**
     * 得到缓存命中率
     * 
     * @return
     */
    public double getHitRate();
}
