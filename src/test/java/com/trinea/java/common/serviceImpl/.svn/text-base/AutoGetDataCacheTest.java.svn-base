package com.trinea.java.common.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import com.trinea.java.common.FileUtils;
import com.trinea.java.common.ObjectUtils;
import com.trinea.java.common.entity.CacheObject;
import com.trinea.java.common.serviceImpl.AutoGetDataCache.OnGetDataListener;
import com.trinea.java.common.utils.SleepUtils;

/**
 * 类AutoGetDataCacheTest.java的实现描述：TODO 类实现描述
 * 
 * @author Trinea 2012-5-6 下午08:39:27
 */
public class AutoGetDataCacheTest extends TestCase {

    private static final String BASE_DIR = "C:\\Users\\Trinea\\Desktop\\temp\\JavaCommonTest\\AutoGetDataCacheTest\\";

    protected void setUp() throws Exception {
        super.setUp();
        FileUtils.makeFolder(BASE_DIR);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testAutoGetDataCache() {
        // 数据源，用map代替网络数据源
        final Map<String, String> dataSource = new HashMap<String, String>();
        List<String> keyList = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            String temp = Integer.toString(i);
            dataSource.put(temp, temp);
            keyList.add(temp);
        }

        // 新建缓存
        AutoGetDataCache<String, String> cache = null;
        cache = new AutoGetDataCache<String, String>(5, new OnGetDataListener<String, String>() {

            private static final long serialVersionUID = 1L;

            @Override
            public CacheObject<String> onGetData(String key) {
                CacheObject<String> o = new CacheObject<String>();
                o.setData(dataSource.get(key));
                return o;
            }
        });
        assertNotNull(cache);
        cache = new AutoGetDataCache<String, String>(new OnGetDataListener<String, String>() {

            private static final long serialVersionUID = 1L;

            @Override
            public CacheObject<String> onGetData(String key) {
                CacheObject<String> o = new CacheObject<String>();
                o.setData(dataSource.get(key));
                return o;
            }
        });
        assertNotNull(cache);
        cache = new AutoGetDataCache<String, String>(5, -1, new OnGetDataListener<String, String>() {

            private static final long serialVersionUID = 1L;

            @Override
            public CacheObject<String> onGetData(String key) {
                CacheObject<String> o = new CacheObject<String>();
                o.setData(dataSource.get(key));
                return o;
            }
        });
        assertNotNull(cache);
    }

    public void testGetAndAutoCacheNewData() {
        // 数据源，用map代替网络数据源
        final Map<String, String> dataSource = new HashMap<String, String>();
        List<String> keyList = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            String temp = Integer.toString(i);
            dataSource.put(temp, temp);
            keyList.add(temp);
        }

        // 新建缓存
        AutoGetDataCache<String, String> cache = null;
        cache = new AutoGetDataCache<String, String>(5, -1, new RemoveTypeEnterTimeFirst<String>(),
                                                     new OnGetDataListener<String, String>() {

                                                         private static final long serialVersionUID = 1L;

                                                         @Override
                                                         public CacheObject<String> onGetData(String key) {
                                                             CacheObject<String> o = new CacheObject<String>();
                                                             o.setData(dataSource.get(key));
                                                             return o;
                                                         }
                                                     });
        assertNotNull(cache);
        // 设置向后缓存1个，向前缓存2个
        cache.setBackCacheNumber(1);
        cache.setForwardCacheNumber(2);

        CacheObject<String> value = cache.get(Integer.toString(0));
        assertNull(cache.get(null));
        assertNull(cache.getAndAutoCacheNewData(null, null));
        assertTrue(value != null && ObjectUtils.isEquals(value.getData(), Integer.toString(0)));
        assertTrue(cache.getValidSize() == 1);
        assertTrue(cache.getHitRate() == 0);
        value = cache.getAndAutoCacheNewData(Integer.toString(2), null);
        value = cache.getAndAutoCacheNewData(Integer.toString(2), keyList);
        assertTrue(1 / 3 - cache.getHitRate() < 0.000001);
        assertTrue(value != null && ObjectUtils.isEquals(value.getData(), Integer.toString(2)));
        SleepUtils.sleep();
        if (cache.getValidSize() == 5) {
            value = cache.getAndAutoCacheNewData(Integer.toString(3), keyList);
            assertTrue(cache.getHitRate() == 0.5);
            value = cache.getAndAutoCacheNewData(Integer.toString(4), keyList);
            assertTrue(cache.getHitRate() == 0.6);
            value = cache.getAndAutoCacheNewData(Integer.toString(1), keyList);
            assertTrue(2 / 3 - cache.getHitRate() < 0.00001);
        }
        value = cache.getAndAutoCacheNewData(Integer.toString(5), keyList);
    }

    public static void testSaveAndLoadData() {
        // 数据源，用map代替网络数据源
        final Map<String, String> dataSource = new HashMap<String, String>();
        List<String> keyList = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            String temp = Integer.toString(i);
            dataSource.put(temp, temp);
            keyList.add(temp);
        }

        int cacheSize = 30, putSize = 50;
        // 新建缓存
        AutoGetDataCache<String, String> cache = null;
        cache = new AutoGetDataCache<String, String>(cacheSize, -1, new RemoveTypeEnterTimeFirst<String>(),
                                                     new OnGetDataListener<String, String>() {

                                                         private static final long serialVersionUID = 1L;

                                                         @Override
                                                         public CacheObject<String> onGetData(String key) {
                                                             CacheObject<String> o = new CacheObject<String>();
                                                             o.setData(dataSource.get(key));
                                                             return o;
                                                         }
                                                     });
        cache.setBackCacheNumber(2);
        cache.setForwardCacheNumber(4);
        for (int i = 1; i <= putSize; i++) {
            CacheObject<String> obj = new CacheObject<String>();
            obj.setData(Integer.toString(i));
            obj.setPriority(i);
            cache.put(Integer.toString(i), obj);
            SleepUtils.sleep(2);
        }
        String saveFile = BASE_DIR + "AutoGetDataCache.obj";
        AutoGetDataCache.saveCache(saveFile, cache);
        assertTrue(FileUtils.isFileExist(saveFile));
        AutoGetDataCache<String, String> outCache = AutoGetDataCache.loadCache(saveFile);
        assertNotNull(outCache);
        assertNotNull(outCache.getOnGetDataListener());
        assertTrue(outCache.getOnGetDataListener() instanceof OnGetDataListener);
        assertEquals(cache.getSize(), 30);
        assertEquals(outCache.getMaxSize(), 30);
        assertTrue(outCache.getCacheFullRemoveType() instanceof RemoveTypeEnterTimeFirst);
        assertEquals(cache.getSize(), outCache.getSize());
        for (int i = putSize - cacheSize + 1; i <= putSize; i++) {
            assertNotNull(cache.get(Integer.toString(i)));
            assertEquals(cache.get(Integer.toString(i)), outCache.get(Integer.toString(i)));
        }
    }
}
