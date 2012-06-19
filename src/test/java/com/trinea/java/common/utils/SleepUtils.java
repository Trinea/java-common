/*
 * Copyright 2012 Trinea.com All right reserved. This software is the
 * confidential and proprietary information of Trinea.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Trinea.com.
 */
package com.trinea.java.common.utils;

/**
 * slepp一段时间
 * 
 * @author Trinea 2012-3-26 下午02:08:44
 */
public class SleepUtils {

    /** 默认sleep时间 **/
    public static long DEFAULT_SLEEP_TIME = 100;
    
    /** 默认中等sleep时间 **/
    public static long DEFAULT_MIDDLE_SLEEP_TIME = 300;

    /** 采用默认sleep时间 {@link SleepUtils#DEFAULT_SLEEP_TIME} **/
    public static void sleep() {
        sleep(DEFAULT_SLEEP_TIME);
    }

    /**
     * sleep一段时间
     * 
     * @param mills 毫秒
     */
    public static void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
