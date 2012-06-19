package com.trinea.java.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * map工具类，用于实现一些map的常用操作
 * 
 * @author Trinea 2011-7-22 上午12:37:10
 */
public class MapUtils {

    /** 默认key和value的分隔符 **/
    public static final String DEFAULT_KEY_AND_VALUE_SEPARATOR      = ":";
    /** 默认每个key value对之间的分隔符 **/
    public static final String DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR = ",";

    /**
     * 判断map是否为空或大小为0
     * 
     * <pre>
     * isEmpty(null)   =   true;
     * isEmpty({})     =   true;
     * isEmpty({1, 2})    =   false;
     * </pre>
     * 
     * @param str
     * @return 若map为null或长度为0, 返回true; 否则返回false.
     */
    public static <K, V> boolean isEmpty(Map<K, V> sourceMap) {
        return (sourceMap == null || sourceMap.size() == 0);
    }

    /**
     * 向map中put key和value对，key必须非null，并且为非空字符串
     * 
     * @param map
     * @param key
     * @param value
     * @return 若put成功，返回true，否则返回false
     *         <ul>
     *         <li>若map为null，返回false，否则</li>
     *         <li>若key为null或空字符串，返回false，否则</li>
     *         <li>调用{@link Map#put(Object, Object)} 返回true</li>
     *         </ul>
     */
    public static boolean putMapNotEmptyKey(Map<String, String> map, String key, String value) {
        if (map == null || StringUtils.isEmpty(key)) {
            return false;
        }

        map.put(key, value);
        return true;
    }

    /**
     * 向map中put key和value对，key和value都必须非null，并且为非空字符串
     * 
     * @param map
     * @param key
     * @param value
     * @return 若put成功，返回true，否则返回false
     *         <ul>
     *         <li>若map为null，返回false，否则</li>
     *         <li>若key为null或空字符串，返回false，否则</li>
     *         <li>若value为null或空字符串，返回false，否则</li>
     *         <li>调用{@link Map#put(Object, Object)} 返回true</li>
     *         </ul>
     */
    public static boolean putMapNotEmptyKeyAndValue(Map<String, String> map, String key, String value) {
        if (map == null || StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return false;
        }

        map.put(key, value);
        return true;
    }

    /**
     * 向map中put key和value对，key必须非null，并且为非空字符串，若value为null或者空字符串，put defaultValue
     * 
     * @param map
     * @param key
     * @param value
     * @param defaultValue
     * @return 若put成功，返回true，否则返回false
     *         <ul>
     *         <li>若map为null，返回false，否则</li>
     *         <li>若key为null或空字符串，返回false，否则</li>
     *         <li>若value为null或空字符串，不管defaultValue是否为空，put defaultValue，返回true</li>
     *         <li>若value不为null，且不为空字符串，put字符串(value)，返回true</li>
     *         </ul>
     */
    public static boolean putMapNotEmptyKeyAndValue(Map<String, String> map, String key, String value,
                                                    String defaultValue) {
        if (map == null || StringUtils.isEmpty(key)) {
            return false;
        }

        map.put(key, StringUtils.isEmpty(value) ? defaultValue : value);
        return true;
    }

    /**
     * 根据value得到key的值，从头开始匹配，若存在返回匹配的第一个key，否则返回null
     * 
     * <pre>
     * 如果map为空，返回null;
     * 如果map中存在value，查找第一个value（对于value为null同样适用），返回key，否则返回null.
     * </pre>
     * 
     * <strong>注意：</strong>
     * 由于对于一般的HashMap，元素的顺序并不是put的顺序，所以使用本函数得到的值并不是第一次put的值，如果想得到第一次put的值，请使用TreeMap<br/>
     * <br/>
     * 
     * @param <V>
     * @param map map
     * @param value value值
     * @return
     */
    public static <K, V> K getKeyByValue(Map<K, V> map, V value) {
        if (isEmpty(map)) {
            return null;
        }

        for (Entry<K, V> entry : map.entrySet()) {
            if (ObjectUtils.isEquals(entry.getValue(), value)) {
                return entry.getKey();
            }
        }

        return null;
    }

    /**
     * 将key和value键值对转换成map，结果忽略为空的key
     * 
     * <pre>
     * parseKeyAndValueToMap("","","",true)=null
     * parseKeyAndValueToMap(null,"","",true)=null
     * parseKeyAndValueToMap("a:b,:","","",true)={(a,b)}
     * parseKeyAndValueToMap("a:b,:d","","",true)={(a,b)}
     * parseKeyAndValueToMap("a:b,c:d","","",true)={(a,b),(c,d)}
     * parseKeyAndValueToMap("a=b, c = d","=",",",true)={(a,b),(c,d)}
     * parseKeyAndValueToMap("a=b, c = d","=",",",false)={(a, b),( c , d)}
     * parseKeyAndValueToMap("a=b, c=d","=", ",", false)={(a,b),( c,d)}
     * parseKeyAndValueToMap("a=b; c=d","=", ";", false)={(a,b),( c,d)}
     * parseKeyAndValueToMap("a=b, c=d", ",", ";", false)={(a=b, c=d)}
     * </pre>
     * 
     * @param source key和value键值对
     * @param keyAndValueSeparator 键值对中key和value分隔符
     * @param keyAndValuePairSeparator 每个key value对之间的分隔符
     * @param ignoreSpace 是否忽略key和value两端的空格，true表示忽略
     * @return
     */
    public static Map<String, String> parseKeyAndValueToMap(String source, String keyAndValueSeparator,
                                                            String keyAndValuePairSeparator, boolean ignoreSpace) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }

        if (StringUtils.isEmpty(keyAndValueSeparator)) {
            keyAndValueSeparator = DEFAULT_KEY_AND_VALUE_SEPARATOR;
        }
        if (StringUtils.isEmpty(keyAndValuePairSeparator)) {
            keyAndValuePairSeparator = DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR;
        }
        Map<String, String> keyAndValueMap = new HashMap<String, String>();
        String[] keyAndValueArray = source.split(keyAndValuePairSeparator);
        if (keyAndValueArray != null) {
            int seperator;
            for (String valueEntity : keyAndValueArray) {
                if (!StringUtils.isEmpty(valueEntity)) {
                    seperator = valueEntity.indexOf(keyAndValueSeparator);
                    if (seperator != -1) {
                        if (ignoreSpace) {
                            MapUtils.putMapNotEmptyKey(keyAndValueMap, valueEntity.substring(0, seperator).trim(),
                                                       valueEntity.substring(seperator + 1).trim());
                        } else {
                            MapUtils.putMapNotEmptyKey(keyAndValueMap, valueEntity.substring(0, seperator),
                                                       valueEntity.substring(seperator + 1));
                        }
                    }
                }
            }
        }
        return keyAndValueMap;
    }

    /**
     * 将key和value键值对转换成map，结果忽略为空的key
     * 
     * @param source key和value键值对
     * @param ignoreSpace 是否忽略key和value两端的空格，true表示忽略
     * @return
     * @see
     *      <ul>
     *      <li>见{@link StringUtils#parseKeyAndValueToMap(String, String, String, boolean)}</li>
     *      </ul>
     */
    public static Map<String, String> parseKeyAndValueToMap(String source, boolean ignoreSpace) {
        return parseKeyAndValueToMap(source, DEFAULT_KEY_AND_VALUE_SEPARATOR, DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR,
                                     ignoreSpace);
    }

    /**
     * 将key和value键值对转换成map，结果忽略为空的key，忽略key和value两端的空格
     * 
     * @param source key和value键值对
     * @return
     * @see
     *      <ul>
     *      <li>见{@link StringUtils#parseKeyAndValueToMap(String, String, String, boolean)}</li>
     *      </ul>
     */
    public static Map<String, String> parseKeyAndValueToMap(String source) {
        return parseKeyAndValueToMap(source, DEFAULT_KEY_AND_VALUE_SEPARATOR, DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR,
                                     true);
    }
}
