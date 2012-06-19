package com.trinea.java.common.serviceImpl;

import junit.framework.TestCase;

import com.trinea.java.common.FileUtils;
import com.trinea.java.common.entity.CacheObject;
import com.trinea.java.common.utils.SleepUtils;

public class SimpleCacheTest extends TestCase {

    private static final String BASE_DIR        = "C:\\Users\\Trinea\\Desktop\\temp\\JavaCommonTest\\SimpleCacheTest\\";

    SimpleCache<String, String> cache1;
    SimpleCache<String, String> cache2;
    SimpleCache<String, String> cache3;
    SimpleCache<String, String> cache4;

    int                         cache1MaxSize   = 5, cache2MaxSize = 5, cache3MaxSize = 5;
    long                        cache1ValidTime = 1000;

    protected void setUp() throws Exception {
        super.setUp();
        FileUtils.makeFolder(BASE_DIR);
        cache1 = new SimpleCache<String, String>(cache3MaxSize, cache1ValidTime, new RemoveTypeEnterTimeFirst<String>());
        cache2 = new SimpleCache<String, String>(cache2MaxSize, -1, new RemoveTypeNotRemove<String>());
        cache2 = new SimpleCache<String, String>(cache2MaxSize, new RemoveTypeNotRemove<String>());
        try {
            cache2 = new SimpleCache<String, String>(-1, -1, new RemoveTypeNotRemove<String>());
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
        try {
            cache2 = new SimpleCache<String, String>(cache2MaxSize, -1, null);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
        cache3 = new SimpleCache<String, String>(cache3MaxSize);
        cache4 = new SimpleCache<String, String>();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSimpleCacheIntLongCacheFullRemoveType() {
        SimpleCache<String, String> cache5 = new SimpleCache<String, String>(5, 1, new RemoveTypeDataBig<String>());
        assertTrue(cache5 != null);
    }

    public void testSimpleCacheIntLong() {
        SimpleCache<String, String> cache5 = new SimpleCache<String, String>(5, 1);
        assertTrue(cache5 != null);
    }

    public void testSimpleCacheInt() {
        SimpleCache<String, String> cache5 = new SimpleCache<String, String>(5);
        assertTrue(cache5 != null);
    }

    public void testSimpleCache() {
        SimpleCache<String, String> cache5 = new SimpleCache<String, String>(5);
        assertTrue(cache5 != null);
        SimpleCache<String, String> cache6 = null;
        try {
            cache6 = new SimpleCache<String, String>(-1);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(cache6 == null);
        }
    }

    public void testGetMaxSize() {
        assertTrue(cache1.getMaxSize() == cache1MaxSize);
        assertTrue(cache4.getMaxSize() == SimpleCache.DEFAULT_MAX_SIZE);
    }

    public void testGetValidTime() {
        assertTrue(cache1.getValidTime() == cache1ValidTime);
    }

    public void testGetCacheFullRemoveType() {
        assertTrue(cache2.getCacheFullRemoveType() instanceof RemoveTypeNotRemove);
    }

    public void testGetSize() {
        cache1.put("cache1Key1", "cache1Value1");
        cache2.put("cache2Key1", "cache2Value1s");
        assertTrue(cache1.getSize() >= 1);
    }

    public void testGetValidSize() {
        cache1.put("cache1Key2", "cache1Value2");
        cache2.put("cache2Key2", "cache2Value2");
        try {
            assertTrue(cache1.getValidSize() > 0);
            Thread.sleep(cache1ValidTime + 10);
            assertTrue(cache1.getSize() > cache1.getValidSize());
            assertTrue(cache2.getSize() == cache2.getValidSize());
        } catch (InterruptedException e) {
        }
    }

    public void testGet() {
        cache1.put("cache1Key3", "cache1Value3");
        assertTrue(cache1.get("cache1Key3").getData().equals("cache1Value3"));
        try {
            Thread.sleep(cache1ValidTime + 10);
            assertTrue(cache1.get("cache1Key3") == null);
        } catch (InterruptedException e) {
        }
    }

    public void testPutKV() {
        cache1.clear();
        cache1.put("cache1Key4", "cache1Value4");
        assertTrue(cache1.getSize() > 0);
        cache2.clear();
        for (int i = 0; i <= cache2MaxSize; i++) {
            cache2.put("cache2Key" + i, "cache2Value" + i);
        }
        cache2.put("cache2Key" + (cache2MaxSize + 1), "cache2Value" + (cache2MaxSize + 1));
        assertFalse(cache2.containsKey("cache2Key" + (cache2MaxSize + 1)));
    }

    public void testPutKCacheObjectOfV() {
        CacheObject<String> obj = new CacheObject<String>();
        obj.setData("cache1Value51");
        cache1.clear();
        cache1.put("cache1Key51", obj);
        assertTrue(cache1.getSize() > 0);

        try {
            Thread.sleep(cache1ValidTime + 10);
            obj = new CacheObject<String>();
            obj.setData("cache1Value52");
            cache1.clear();
            cache1.put("cache1Key52", obj);
            assertTrue(cache1.getSize() > 0);
        } catch (InterruptedException e) {
        }

        cache2.clear();
        for (int i = 0; i <= cache2MaxSize; i++) {
            cache2.put("cache2Key" + i, "cache2Value" + i);
        }
    }

    public void testExist() {
        cache1.put("cache1Key6", "cache1Value6");
        assertTrue(cache1.containsKey("cache1Key6"));

        cache2.clear();
        for (int i = 0; i <= cache2MaxSize; i++) {
            cache2.put("cache2Key" + i, "cache2Value" + i);
        }
        cache2.put("cache2Key" + (cache2MaxSize + 1), "cache2Value" + (cache2MaxSize + 1));
        assertFalse(cache2.containsKey("cache2Key" + (cache2MaxSize + 1)));
    }

    public void testIsExpired() {
        cache1.put("cache1Key7", "cache1Value7");
        cache3.put("cache3Key1", "cache3Value1");
        try {
            Thread.sleep(cache1ValidTime + 10);
            assertTrue(cache1.isExpired("cache1Key7"));
            assertFalse(cache3.isExpired("cache3Key1"));
        } catch (InterruptedException e) {
        }
    }

    public void testRemove() {
        cache1.put("cache1Key7", "cache1Value7");
        assertTrue(cache1.remove("cache1Key7") != null);
    }

    public void testFullRemoveOne() {
        cache1.clear();
        for (int i = 0; i < cache1MaxSize; i++) {
            try {
                Thread.sleep(100);
                cache1.put("cache1Key" + i, "cache1Value" + i);
            } catch (InterruptedException e) {
            }
        }
        CacheObject<String> removeOne = cache1.fullRemoveOne();
        assertTrue(removeOne != null && removeOne.getData() != null && removeOne.getData().equals("cache1Value0"));
    }

    public void testRemoveExpired() {
        cache1.clear();
        for (int i = 0; i < cache1MaxSize; i++) {
            cache1.put("cache1Key" + i, "cache1Value" + i);
        }
        try {
            Thread.sleep(cache1ValidTime + 10);
            int sourceSize = cache1.getSize();
            assertTrue(cache1.removeExpired() == sourceSize);
        } catch (InterruptedException e) {
        }
    }

    public void testClear() {
        cache1.clear();
    }

    public void testRemoveByLastUsedTime() {
        int cacheSize = 5, putSize = cacheSize + 3;
        SimpleCache<String, String> cache = new SimpleCache<String, String>(cacheSize, -1,
                                                                            new RemoveTypeLastUsedTimeFirst<String>());
        for (int i = 1; i <= putSize; i++) {
            cache.put(Integer.toString(i), Integer.toString(i));
            cache.get(Integer.toString(i));
            SleepUtils.sleep(10);
        }
        assertFalse(cache.containsKey("1"));
        assertFalse(cache.containsKey("3"));
        assertTrue(cache.containsKey("4"));
        assertTrue(cache.containsKey(Integer.toString(putSize)));

        cache = new SimpleCache<String, String>(cacheSize, -1, new RemoveTypeLastUsedTimeLast<String>());
        for (int i = 1; i <= putSize; i++) {
            cache.put(Integer.toString(i), Integer.toString(i));
            cache.get(Integer.toString(i));
            SleepUtils.sleep(10);
        }
        assertTrue(cache.containsKey("1"));
        assertTrue(cache.containsKey("4"));
        assertFalse(cache.containsKey("5"));
        assertFalse(cache.containsKey("6"));
        assertTrue(cache.containsKey(Integer.toString(putSize)));
    }

    public void testRemoveByUsedCount() {
        int cacheSize = 5, putSize = cacheSize + 3;
        SimpleCache<String, String> cache = new SimpleCache<String, String>(cacheSize, -1,
                                                                            new RemoveTypeUsedCountSmall<String>());
        for (int i = 1; i <= putSize; i++) {
            cache.put(Integer.toString(i), Integer.toString(i));
            for (int j = 0; j < i; j++) {
                cache.get(Integer.toString(i));
            }
        }
        assertFalse(cache.containsKey("1"));
        assertFalse(cache.containsKey("3"));
        assertTrue(cache.containsKey("4"));
        assertTrue(cache.containsKey(Integer.toString(putSize)));

        cache = new SimpleCache<String, String>(cacheSize, -1, new RemoveTypeUsedCountBig<String>());
        for (int i = 1; i <= putSize; i++) {
            cache.put(Integer.toString(i), Integer.toString(i));
            for (int j = 0; j < i; j++) {
                cache.get(Integer.toString(i));
            }
        }
        assertTrue(cache.containsKey("1"));
        assertTrue(cache.containsKey("4"));
        assertFalse(cache.containsKey("5"));
        assertFalse(cache.containsKey("6"));
        assertTrue(cache.containsKey(Integer.toString(putSize)));
    }

    public void testRemoveByPriority() {
        int cacheSize = 5, putSize = cacheSize + 3;
        SimpleCache<String, String> cache = new SimpleCache<String, String>(cacheSize, -1,
                                                                            new RemoveTypePriorityLow<String>());
        for (int i = 1; i <= putSize; i++) {
            CacheObject<String> obj = new CacheObject<String>();
            obj.setData(Integer.toString(i));
            obj.setPriority(i);
            cache.put(Integer.toString(i), obj);
        }
        assertFalse(cache.containsKey("1"));
        assertFalse(cache.containsKey("3"));
        assertTrue(cache.containsKey("4"));
        assertTrue(cache.containsKey(Integer.toString(putSize)));

        cache = new SimpleCache<String, String>(cacheSize, -1, new RemoveTypePriorityHigh<String>());
        for (int i = 1; i <= putSize; i++) {
            CacheObject<String> obj = new CacheObject<String>();
            obj.setData(Integer.toString(i));
            obj.setPriority(i);
            cache.put(Integer.toString(i), obj);
        }
        assertTrue(cache.containsKey("1"));
        assertTrue(cache.containsKey("4"));
        assertFalse(cache.containsKey("5"));
        assertFalse(cache.containsKey("6"));
        assertTrue(cache.containsKey(Integer.toString(putSize)));
    }

    public void testRemoveByData() {
        int cacheSize = 5, putSize = cacheSize + 3;
        SimpleCache<String, Integer> cache = new SimpleCache<String, Integer>(cacheSize, -1,
                                                                              new RemoveTypeDataSmall<Integer>());
        for (int i = 1; i <= putSize; i++) {
            CacheObject<Integer> obj = new CacheObject<Integer>();
            obj.setData(i);
            obj.setPriority(i);
            cache.put(Integer.toString(i), obj);
        }
        assertFalse(cache.containsKey("1"));
        assertFalse(cache.containsKey("3"));
        assertTrue(cache.containsKey("4"));
        assertTrue(cache.containsKey(Integer.toString(putSize)));

        cache = new SimpleCache<String, Integer>(cacheSize, -1, new RemoveTypeDataBig<Integer>());
        for (int i = 1; i <= putSize; i++) {
            CacheObject<Integer> obj = new CacheObject<Integer>();
            obj.setData(i);
            obj.setPriority(i);
            cache.put(Integer.toString(i), obj);
        }
        assertTrue(cache.containsKey("1"));
        assertTrue(cache.containsKey("4"));
        assertFalse(cache.containsKey("5"));
        assertFalse(cache.containsKey("6"));
        assertTrue(cache.containsKey(Integer.toString(putSize)));
    }

    public void testGetHitRate() {
        int cacheSize = 5, putSize = cacheSize + 3;
        SimpleCache<String, String> cache = new SimpleCache<String, String>(cacheSize, -1,
                                                                            new RemoveTypePriorityLow<String>());
        assertTrue(cache.getHitRate() - 0 < 0.0001);
        for (int i = 1; i <= putSize; i++) {
            CacheObject<String> obj = new CacheObject<String>();
            obj.setData(Integer.toString(i));
            obj.setPriority(i);
            cache.put(Integer.toString(i), obj);
        }
        assertTrue(cache.get("1") == null);
        assertTrue(cache.get("3") == null);
        assertTrue(cache.get("4") != null);
        assertTrue(cache.get(Integer.toString(putSize)) != null);
        assertEquals(cache.getHitCount(), 2);
        assertEquals(cache.getMissCount(), 2);
        assertEquals(cache.getHitRate(), 0.5);
    }

    public void testSaveAndLoadData() {
        int cacheSize = 30, putSize = 50;
        SimpleCache<String, String> cache = new SimpleCache<String, String>(cacheSize, -1,
                                                                            new RemoveTypePriorityLow<String>());
        for (int i = 1; i <= putSize; i++) {
            CacheObject<String> obj = new CacheObject<String>();
            obj.setData(Integer.toString(i));
            obj.setPriority(i);
            cache.put(Integer.toString(i), obj);
        }
        SimpleCache.saveCache(BASE_DIR + "simplecache.obj", cache);
        SimpleCache<String, String> outCache = SimpleCache.loadCache(BASE_DIR + "simplecache.obj");

        assertEquals(cache.getSize(), 30);
        assertEquals(outCache.getMaxSize(), 30);
        assertTrue(outCache.getCacheFullRemoveType() instanceof RemoveTypePriorityLow);
        assertEquals(cache.getSize(), outCache.getSize());
        for (int i = putSize - cacheSize + 1; i <= putSize; i++) {
            assertNotNull(cache.get(Integer.toString(i)));
            assertEquals(cache.get(Integer.toString(i)), outCache.get(Integer.toString(i)));
        }
    }
}
