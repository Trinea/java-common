/*
 * Copyright 2012 Trinea.com All right reserved. This software is the
 * confidential and proprietary information of Trinea.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Trinea.com.
 */
package com.trinea.java.common.serviceImpl;

import com.trinea.java.common.entity.CacheObject;
import com.trinea.java.common.service.CacheFullRemoveType;

/**
 * 缓存满时删除数据的类型--对象优先级，优先级低先删除
 * 
 * @author Trinea 2012-5-10 上午01:15:50
 */
public class RemoveTypePriorityLow<T> implements CacheFullRemoveType<T> {

    private static final long serialVersionUID = 1L;

    @Override
    public int compare(CacheObject<T> obj1, CacheObject<T> obj2) {
        return (obj1.getPriority() > obj2.getPriority()) ? 1 : ((obj1.getPriority() == obj2.getPriority()) ? 0 : -1);
    }
}
