package com.trinea.java.common.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import com.trinea.java.common.entity.CacheObject;
import com.trinea.java.common.serviceImpl.AutoGetDataCache.OnGetDataListener;

/**
 * AutoGetDataCache使用示例
 * 
 * @author Trinea 2012-6-18 下午02:14:16
 */
public class AutoGetDataCacheDemo extends TestCase {

    private static String[]            data       = {"data1", "data2", "data3", "data4", "data5", "data6", "data7",
            "data8", "data9", "data10", "data11", "data12", "data13", "data14", "data15", "data16", "data17", "data18",
            "data19", "data20"                    };
    private static ArrayList<String>   keyList    = new ArrayList<String>();
    private static Map<String, String> dataSource = new HashMap<String, String>();
    private static int                 index      = 0;

    /**
     * 初始化数据源
     */
    public static void initDataSource() {
        for (int i = 0; i < data.length; i++) {
            String temp = Integer.toString(i);
            dataSource.put(temp, data[i]);
            keyList.add(temp);
        }
    }

    /**
     * 从数据源中获取数据
     * 
     * @return
     */
    public static String getSlowResponseData() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return dataSource.get(Integer.toString((index++) % keyList.size()));
    }

    public static void main(String[] args) {
        initDataSource();

        // 缓存定义，在OnGetDataListener中定义获取数据的接口
        AutoGetDataCache<String, String> cache = null;
        cache = new AutoGetDataCache<String, String>(new OnGetDataListener<String, String>() {

             private static final long serialVersionUID = 1L;

             @Override
             public CacheObject<String> onGetData(String key) {
                 CacheObject<String> o = new CacheObject<String>();
                 o.setData(getSlowResponseData());
                 return o;
             }
         }, 5, -1,
                                                     new RemoveTypeEnterTimeFirst<String>());

        int count = 10;
        long start = 0, end = 0;
        start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            System.out.print(cache.get(Integer.toString(i), keyList).getData() + " ");
        }
        end = System.currentTimeMillis();
        System.out.println();
        System.out.println("缓存后用时(ms)：" + (end - start) + "。缓存命中率为" + cache.getHitRate() + "(" + cache.getHitCount()
                           + "/" + (cache.getHitCount() + cache.getMissCount()) + ")");

        start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            System.out.print(getSlowResponseData() + " ");
        }
        end = System.currentTimeMillis();
        System.out.println();
        System.out.println("缓存前用时(ms)：" + (end - start));
    }
}
