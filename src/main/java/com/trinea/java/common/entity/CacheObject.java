package com.trinea.java.common.entity;

import java.io.Serializable;

import com.trinea.java.common.ObjectUtils;

/**
 * 缓存中的数据
 * 
 * @author Trinea 2011-12-23 上午01:27:06
 */
public class CacheObject<V> implements Serializable, Comparable<CacheObject<V>> {

    private static final long serialVersionUID = 1L;

    /** 对象进入缓存时间 **/
    protected long            enterTime;
    /** 对象上次使用时间， 即上次被get的时间 **/
    protected long            lastUsedTime;
    /** 对象使用次数， get一次表示被使用一次 **/
    protected long            usedCount;
    /** 对象优先级 **/
    protected int             priority;

    /** 对象是否已经过期 **/
    protected boolean         isExpired;
    /** 对象是否永不过期 **/
    protected boolean         isForever;

    /** 对象数据 **/
    protected V               data;

    public CacheObject(){
        this.enterTime = System.currentTimeMillis();
        this.lastUsedTime = System.currentTimeMillis();
        this.usedCount = 0;
        this.priority = 0;
        this.isExpired = false;
        this.isForever = true;
    }

    public long getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(long enterTime) {
        this.enterTime = enterTime;
    }

    public long getLastUsedTime() {
        return lastUsedTime;
    }

    public void setLastUsedTime(long lastUsedTime) {
        this.lastUsedTime = lastUsedTime;
    }

    public long getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(long usedCount) {
        this.usedCount = usedCount;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean isExpired) {
        this.isExpired = isExpired;
    }

    public boolean isForever() {
        return isForever;
    }

    public void setForever(boolean isForever) {
        this.isForever = isForever;
    }

    public V getData() {
        return data;
    }

    public void setData(V data) {
        this.data = data;
    }

    /**
     * 仅对data字段进行比较
     * 
     * @param o
     * @return
     */
    @Override
    public int compareTo(CacheObject<V> o) {
        return o == null ? 1 : ObjectUtils.compare(this.getData(), o.getData());
    }

    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        CacheObject<V> obj = (CacheObject<V>)(o);
        return (ObjectUtils.isEquals(this.getData(), obj.getData()) && this.getEnterTime() == obj.getEnterTime()
                && this.getPriority() == obj.getPriority() && this.isExpired == obj.isExpired && this.isForever == obj.isForever);
    }
}
