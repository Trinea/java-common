package com.trinea.java.common.serviceImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import com.trinea.java.common.ListUtils;
import com.trinea.java.common.ObjectUtils;
import com.trinea.java.common.SerializeUtils;
import com.trinea.java.common.entity.CacheObject;
import com.trinea.java.common.service.CacheFullRemoveType;

/**
 * <strong>自动获取新数据的缓存</strong>，适用于获取数据较耗时的应用，如网络通讯、响应慢数据获取。<br/>
 * <br/>
 * 可用来自动获取新数据进行缓存。支持前后双向多个数据缓存，使得数据获取效率大大提高，下次使用该数据时不用实时获取直接读取缓存即可<br/>
 * <br/>
 * <ul>
 * 缓存设置及使用
 * <li>使用下面缓存初始化中介绍的几种构造函数之一初始化缓存，使用{@link #setForwardCacheNumber(int)}设置向前缓存个数，默认个数为
 * {@link #DEFAULT_FORWARD_CACHE_NUMBER}；使用{@link #setBackCacheNumber(int)} 设置向后缓存个数，默认个数为
 * {@link #DEFAULT_BACK_CACHE_NUMBER}</li>
 * <li>使用{@link #get(Object, List)}get某个key，并且会自动获取list中key进行缓存</li>
 * <li>使用{@link #get(Object)}get某个key，但不会自动获取新数据进行缓存</li>
 * <li>使用{@link #loadCache(String)}从文件中恢复缓存</li>
 * <li>使用{@link SimpleCache#saveCache(String, SimpleCache)}保存缓存到文件</li>
 * </ul>
 * <ul>
 * 缓存初始化
 * <li>{@link #AutoGetDataCache(OnGetDataListener)}</li>
 * <li>{@link #AutoGetDataCache(OnGetDataListener, int)}</li>
 * <li>{@link #AutoGetDataCache(OnGetDataListener, int, long)}</li>
 * <li>{@link #AutoGetDataCache(OnGetDataListener, int, CacheFullRemoveType)}</li>
 * <li>{@link #AutoGetDataCache(OnGetDataListener, int, long, CacheFullRemoveType)}</li>
 * <li>{@link #loadCache(String)}从文件中恢复缓存</li>
 * </ul>
 * 
 * @author Trinea 2012-3-4 下午12:39:17
 */
public class AutoGetDataCache<K, V> extends SimpleCache<K, V> {

    private static final long               serialVersionUID             = 1L;

    /** 默认自动向前缓存的个数 **/
    private static final int                DEFAULT_FORWARD_CACHE_NUMBER = 3;

    /** 默认自动向后缓存的个数 **/
    private static final int                DEFAULT_BACK_CACHE_NUMBER    = 1;

    /** 自动向前缓存的个数，默认个数为{@link #DEFAULT_FORWARD_CACHE_NUMBER} **/
    private volatile int                    forwardCacheNumber           = DEFAULT_FORWARD_CACHE_NUMBER;

    /** 自动向后缓存的个数 ，默认个数为{@link #DEFAULT_BACK_CACHE_NUMBER} **/
    private volatile int                    backCacheNumber              = DEFAULT_BACK_CACHE_NUMBER;

    /** 获取数据的接口 **/
    private OnGetDataListener<K, V>         onGetDataListener;

    /** 存储正在获取数据的线程，防止多个线程同时获取某个key，同时可以获取某个线程的相关信息 **/
    private transient Map<K, GetDataThread> gettingDataThreadMap         = new ConcurrentHashMap<K, GetDataThread>();

    /**
     * 获取某个key对应的值，并自动获取新数据进行缓存。如果只获取key对应值不获取新数据进行缓存，可使用{@link #get(Object)}
     * 
     * @param key 待获取值的key
     * @param keyList key list，按照该list中的key顺序获取新数据进行缓存，为空表示不进行缓存
     * @return
     */
    public CacheObject<V> get(K key, List<K> keyList) {
        if (key == null) {
            return null;
        }

        // 先前后进行预取
        autoCacheNewDataForward(key, keyList, forwardCacheNumber);
        autoCacheNewDataBack(key, keyList, backCacheNumber);

        return get(key);
    }

    /**
     * 只获取key对应值不获取新数据进行缓存（同步），如果想获取某个key对应的值，并自动获取新数据进行缓存可使用{@link #get(Object, List)}
     * <ul>
     * <li>若该key为null，返回null</li>
     * <li>若key已在缓存中，返回该key对应的值</li>
     * <li>若key不在缓存中，会自动调用其{@link OnGetDataListener#onGetData(Object)}方法获取数据将其返回，getData为null时返回null</li>
     * </ul>
     * 
     * @param key
     * @return
     */
    @Override
    public CacheObject<V> get(K key) {
        if (key == null) {
            return null;
        }

        CacheObject<V> object = super.get(key);
        if (object == null && onGetDataListener != null) {
            GetDataThread getDataThread = gettingData(key);
            // 实时获取需要等待获取完成
            if (getDataThread != null) {
                try {
                    getDataThread.getLatch().await();
                } catch (InterruptedException e) {
                    throw new RuntimeException("InterruptedException occurred. ", e);
                }
            }

            // 需要重新计算命中率，此种等待情况当作没有命中计算
            object = super.get(key);
            if (object != null) {
                hitCount.decrementAndGet();
            } else {
                missCount.decrementAndGet();
            }
        }
        return object;
    }

    /**
     * 自动向前获取新数据缓存
     * <ul>
     * <strong>缓存规则如下:</strong><br/>
     * 若key为null或list为空不进行缓存，否则从前到后依次循环keyList中每个元素，从等于key的元素的下一个元素开始按照下面规则判断，如此反复直到key前第cacheCount个元素或list尾时结束
     * <li>若key已在缓存中，继续判断下一个元素，否则</li>
     * <li>若某线程正在获取该key对应的数据，继续判断下一个元素，否则</li>
     * <li>新建线程获取数据，继续判断下一个元素</li>
     * </ul>
     * 
     * @param key 当前获取数据的key
     * @param keyList key队列
     * @param cacheCount 要缓存的个数
     * @return 返回正在获取数据的key个数，即要缓存的个数减去已经在缓存中key的个数
     */
    protected int autoCacheNewDataForward(K key, List<K> keyList, int cacheCount) {
        int gettingDataCount = 0;
        if (key != null && !ListUtils.isEmpty(keyList) && onGetDataListener != null) {
            int cachedCount = 0;
            boolean beginCount = false;
            for (int i = 0; i < keyList.size() && cachedCount <= cacheCount; i++) {
                K k = keyList.get(i);
                if (ObjectUtils.isEquals(k, key)) {
                    beginCount = true;
                    continue;
                }

                if (k != null && beginCount) {
                    cachedCount++;
                    if (gettingData(k) == null) {
                        gettingDataCount++;
                    }
                }
            }
        }
        return gettingDataCount;
    }

    /**
     * 自动向后获取新数据缓存
     * <ul>
     * <strong>缓存规则如下:</strong><br/>
     * 若key为null或list为空不进行缓存，否则从后到前依次循环keyList中每个元素，从等于key的元素的上一个元素开始按照下面规则判断，如此反复直到key后第cacheCount个元素或list头时结束
     * <li>若key已在缓存中，继续判断上一个元素，否则</li>
     * <li>若某线程正在获取该key对应的数据，继续判断上一个元素，否则</li>
     * <li>新建线程获取数据，继续判断上一个元素</li>
     * </ul>
     * 
     * @param key 当前获取数据的key
     * @param keyList key队列
     * @param cacheCount 要缓存的个数
     * @return 返回正在获取数据的key个数，即要缓存的个数减去已经在缓存中key的个数
     */
    protected int autoCacheNewDataBack(K key, List<K> keyList, int cacheCount) {
        int gettingDataCount = 0;
        if (key != null && !ListUtils.isEmpty(keyList) && onGetDataListener != null) {
            int cachedCount = 0;
            boolean beginCount = false;
            for (int i = keyList.size() - 1; i >= 0 && cachedCount <= cacheCount; i--) {
                K k = keyList.get(i);
                if (ObjectUtils.isEquals(k, key)) {
                    beginCount = true;
                    continue;
                }

                if (k != null && beginCount) {
                    cachedCount++;
                    if (gettingData(k) == null) {
                        gettingDataCount++;
                    }
                }
            }
        }
        return gettingDataCount;
    }

    /**
     * 返回正在获取某个key对应数据的线程
     * <ul>
     * <li>若key在缓存中，返回null，否则</li>
     * <li>若某线程正在获取该key对应的数据，返回该线程，否则</li>
     * <li>新建线程获取数据并返回该线程</li>
     * </ul>
     * 
     * @param key
     * @return
     */
    protected synchronized GetDataThread gettingData(K key) {
        if (containsKey(key)) {
            return null;
        }

        if (gettingDataThreadMap.containsKey(key)) {
            return gettingDataThreadMap.get(key);
        }

        GetDataThread getDataThread = new GetDataThread(this, key, onGetDataListener);
        gettingDataThreadMap.put(key, getDataThread);
        getDataThread.start();
        return getDataThread;

    }

    /**
     * 初始化缓存
     * <ul>
     * <li>缓存最大容量为{@link SimpleCache#DEFAULT_MAX_SIZE}</li>
     * <li>元素不会失效</li>
     * <li>cache满时删除元素类型为{@link RemoveTypeEnterTimeFirst}</li>
     * </ul>
     * 
     * @param onGetDataListener 获取数据的方法
     */
    public AutoGetDataCache(OnGetDataListener<K, V> onGetDataListener){
        this(onGetDataListener, DEFAULT_MAX_SIZE, -1, new RemoveTypeEnterTimeFirst<V>());
    }

    /**
     * 初始化缓存
     * <ul>
     * <li>元素不会失效</li>
     * <li>cache满时删除元素类型为{@link RemoveTypeEnterTimeFirst}</li>
     * </ul>
     * 
     * @param onGetDataListener 获取数据的方法
     * @param maxSize 缓存最大容量
     */
    public AutoGetDataCache(OnGetDataListener<K, V> onGetDataListener, int maxSize){
        this(onGetDataListener, maxSize, -1, new RemoveTypeEnterTimeFirst<V>());
    }

    /**
     * 初始化缓存
     * <ul>
     * <li>cache满时删除元素类型为{@link RemoveTypeEnterTimeFirst}</li>
     * </ul>
     * 
     * @param onGetDataListener 获取数据的方法
     * @param maxSize 缓存最大容量
     * @param validTime 缓存中元素有效时间，小于等于0表示元素不会失效，失效规则见{@link SimpleCache#isExpired(CacheObject)}
     */
    public AutoGetDataCache(OnGetDataListener<K, V> onGetDataListener, int maxSize, long validTime){
        this(onGetDataListener, maxSize, validTime, new RemoveTypeEnterTimeFirst<V>());
    }

    /**
     * 初始化缓存
     * <ul>
     * <li>元素不会失效</li>
     * </ul>
     * 
     * @param onGetDataListener 获取数据的方法
     * @param maxSize 缓存最大容量
     * @param cacheFullRemoveType cache满时删除元素类型，见{@link CacheFullRemoveType}
     */
    public AutoGetDataCache(OnGetDataListener<K, V> onGetDataListener, int maxSize,
                            CacheFullRemoveType<V> cacheFullRemoveType){
        this(onGetDataListener, maxSize, -1, cacheFullRemoveType);
    }

    /**
     * 初始化缓存
     * 
     * @param onGetDataListener 获取数据的方法
     * @param maxSize 缓存最大容量
     * @param validTime 缓存中元素有效时间，小于等于0表示元素不会失效，失效规则见{@link SimpleCache#isExpired(CacheObject)}
     * @param cacheFullRemoveType cache满时删除元素类型，见{@link CacheFullRemoveType}
     */
    public AutoGetDataCache(OnGetDataListener<K, V> onGetDataListener, int maxSize, long validTime,
                            CacheFullRemoveType<V> cacheFullRemoveType){
        super(maxSize, validTime, cacheFullRemoveType);
        this.onGetDataListener = onGetDataListener;
    }

    /**
     * 得到自动向前缓存的个数
     * 
     * @return
     */
    public int getForwardCacheNumber() {
        return forwardCacheNumber;
    }

    /**
     * 设置自动向前缓存的个数
     * 
     * @param forwardCacheNumber
     */
    public void setForwardCacheNumber(int forwardCacheNumber) {
        this.forwardCacheNumber = forwardCacheNumber;
    }

    /**
     * 得到自动向后缓存的个数
     * 
     * @return
     */
    public int getBackCacheNumber() {
        return backCacheNumber;
    }

    /**
     * 设置自动向后缓存的个数
     * 
     * @param backCacheNumber
     */
    public void setBackCacheNumber(int backCacheNumber) {
        this.backCacheNumber = backCacheNumber;
    }

    /**
     * 得到获取数据的方法
     * 
     * @return the onGetDataListener
     */
    public OnGetDataListener<K, V> getOnGetDataListener() {
        return onGetDataListener;
    }

    /**
     * 设置获取数据的方法
     * 
     * @param onGetDataListener
     */
    public void setOnGetDataListener(OnGetDataListener<K, V> onGetDataListener) {
        this.onGetDataListener = onGetDataListener;
    }

    /**
     * 从文件中恢复缓存
     * 
     * @param filePath 文件路径
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <K, V> AutoGetDataCache<K, V> loadCache(String filePath) {
        return (AutoGetDataCache<K, V>)SerializeUtils.deserialization(filePath);
    }

    /**
     * 获取新数据的类
     * 
     * @author Trinea 2012-3-4 下午01:34:27
     */
    public interface OnGetDataListener<K, V> extends Serializable {

        /**
         * 获取数据的方法
         * 
         * @param key
         * @return 返回null不会存入缓存
         */
        public CacheObject<V> onGetData(K key);
    }

    /**
     * 获取新数据的线程
     * 
     * @author Trinea 2012-3-4 下午02:09:10
     */
    protected class GetDataThread extends Thread {

        private AutoGetDataCache<K, V>  cache;
        private K                       key;
        private OnGetDataListener<K, V> onGetDataListener;

        /** put结束的锁 **/
        private CountDownLatch          finishPutLock;

        /**
         * 获取数据
         * 
         * @param cache 存储数据的缓存
         * @param key 获取数据的key
         * @param onGetDataListener 获取数据的接口
         */
        public GetDataThread(AutoGetDataCache<K, V> cache, K key, OnGetDataListener<K, V> onGetDataListener){
            super("GetDataThread whose key is " + key);
            this.cache = cache;
            this.key = key;
            this.onGetDataListener = onGetDataListener;
            finishPutLock = new CountDownLatch(1);
        }

        public void run() {
            if (key != null && onGetDataListener != null && cache != null) {
                CacheObject<V> object = onGetDataListener.onGetData(key);
                if (object != null) {
                    cache.put(key, object);
                }
            }
            // 执行结束释放锁
            finishPutLock.countDown();

            if (gettingDataThreadMap != null && key != null) {
                gettingDataThreadMap.remove(key);
            }
        }

        /**
         * 得到latch
         * 
         * @return the latch
         */
        public CountDownLatch getLatch() {
            return finishPutLock;
        }
    };
}
