package com.trinea.java.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Json工具类
 * 
 * @author Trinea 2012-5-12 下午03:42:41
 */
public class JSONUtils {

    /**
     * 从JSONObject中根据key得到Long型值，若存在异常则返回默认值
     * 
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return
     *         <ul>
     *         <li>若jsonObject为null，返回defaultValue</li>
     *         <li>若key为null或为空字符串，返回defaultValue</li>
     *         <li>若{@link JSONObject#getLong(String)}异常，返回defaultValue</li>
     *         <li>返回{@link JSONObject#getLong(String)}</li>
     *         </ul>
     */
    public static Long getLong(JSONObject jsonObject, String key, Long defaultValue) {
        if (jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getLong(key);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    /**
     * 从jsonData中根据key得到Long型值，若存在异常则返回默认值
     * 
     * @param jsonData
     * @param key
     * @param defaultValue
     * @return
     *         <ul>
     *         <li>若jsonData 为null或为空，返回defaultValue</li>
     *         <li>若jsonData {@link JSONObject#JSONObject(String)}异常，返回defaultValue</li>
     *         <li>调用{@link JSONUtils#getLong(JSONObject, String, JSONObject)}</li>
     *         </ul>
     */
    public static Long getLong(String jsonData, String key, Long defaultValue) {
        if (StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getLong(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    /**
     * 见{@link JSONUtils#getLong(JSONObject, String, Long)}
     * 
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return
     */
    public static long getLong(JSONObject jsonObject, String key, long defaultValue) {
        return getLong(jsonObject, key, (Long)defaultValue);
    }

    /**
     * 见{@link JSONUtils#getLong(String, String, Long)}
     * 
     * @param jsonData
     * @param key
     * @param defaultValue
     * @return
     */
    public static long getLong(String jsonData, String key, long defaultValue) {
        return getLong(jsonData, key, (Long)defaultValue);
    }

    /**
     * 从JSONObject中根据key得到Int型值，若存在异常则返回默认值
     * 
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return
     *         <ul>
     *         <li>若jsonObject为null，返回defaultValue</li>
     *         <li>若key为null或为空字符串，返回defaultValue</li>
     *         <li>若{@link JSONObject#getInt(String)}异常，返回defaultValue</li>
     *         <li>返回{@link JSONObject#getInt(String)}</li>
     *         </ul>
     */
    public static Integer getInt(JSONObject jsonObject, String key, Integer defaultValue) {
        if (jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getInt(key);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    /**
     * 从jsonData中根据key得到Int型值，若存在异常则返回默认值
     * 
     * @param jsonData
     * @param key
     * @param defaultValue
     * @return
     *         <ul>
     *         <li>若jsonData 为null或为空，返回defaultValue</li>
     *         <li>若jsonData {@link JSONObject#JSONObject(String)}异常，返回defaultValue</li>
     *         <li>调用{@link JSONUtils#getInt(JSONObject, String, JSONObject)}</li>
     *         </ul>
     */
    public static Integer getInt(String jsonData, String key, Integer defaultValue) {
        if (StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getInt(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    /**
     * 见{@link JSONUtils#getInt(JSONObject, String, Integer)}
     * 
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(JSONObject jsonObject, String key, int defaultValue) {
        return getInt(jsonObject, key, (Integer)defaultValue);
    }

    /**
     * 见{@link JSONUtils#getInt(String, String, Integer)}
     * 
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(String jsonData, String key, int defaultValue) {
        return getInt(jsonData, key, (Integer)defaultValue);
    }

    /**
     * 从JSONObject中根据key得到String型值，若存在异常则返回默认值
     * 
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return
     *         <ul>
     *         <li>若jsonObject为null，返回defaultValue</li>
     *         <li>若key为null或为空字符串，返回defaultValue</li>
     *         <li>若{@link JSONObject#getString(String)}异常，返回defaultValue</li>
     *         <li>返回{@link JSONObject#getString(String)}</li>
     *         </ul>
     */
    public static String getString(JSONObject jsonObject, String key, String defaultValue) {
        if (jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getString(key);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    /**
     * 从jsonData中根据key得到String型值，若存在异常则返回默认值
     * 
     * @param jsonData
     * @param key
     * @param defaultValue
     * @return
     *         <ul>
     *         <li>若jsonData 为null或为空，返回defaultValue</li>
     *         <li>若jsonData {@link JSONObject#JSONObject(String)}异常，返回defaultValue</li>
     *         <li>调用{@link JSONUtils#getString(JSONObject, String, JSONObject)}</li>
     *         </ul>
     */
    public static String getString(String jsonData, String key, String defaultValue) {
        if (StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getString(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    /**
     * 从JSONObject中根据key得到String数组型值，若存在异常则返回默认值
     * 
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return
     *         <ul>
     *         <li>若jsonObject为null，返回defaultValue</li>
     *         <li>若key为null或为空字符串，返回defaultValue</li>
     *         <li>若{@link JSONObject#getJSONArray(String)}异常，返回defaultValue</li>
     *         <li>若{@link JSONArray#getString(int)}异常，返回defaultValue</li>
     *         <li>返回{@link JSONArray#getString(int)}组成的数组</li>
     *         </ul>
     */
    public static String[] getStringArray(JSONObject jsonObject, String key, String[] defaultValue) {
        if (jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            JSONArray statusArray = jsonObject.getJSONArray(key);
            if (statusArray != null) {
                String[] value = new String[statusArray.length()];
                for (int i = 0; i < statusArray.length(); i++) {
                    value[i] = statusArray.getString(i);
                }
                return value;
            }
        } catch (JSONException e) {
            return defaultValue;
        }
        return defaultValue;
    }

    /**
     * 从jsonData中根据key得到String数组型值，若存在异常则返回默认值
     * 
     * @param jsonData
     * @param key
     * @param defaultValue
     * @return
     *         <ul>
     *         <li>若jsonData 为null或为空，返回defaultValue</li>
     *         <li>若jsonData {@link JSONObject#JSONObject(String)}异常，返回defaultValue</li>
     *         <li>调用{@link JSONUtils#getStringArray(JSONObject, String, JSONObject)}</li>
     *         </ul>
     */
    public static String[] getStringArray(String jsonData, String key, String[] defaultValue) {
        if (StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getStringArray(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    /**
     * 从JSONObject中根据key得到JSONObject型值，若存在异常则返回默认值
     * 
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return
     *         <ul>
     *         <li>若jsonObject为null，返回defaultValue</li>
     *         <li>若key为null或为空字符串，返回defaultValue</li>
     *         <li>若{@link JSONObject#getJSONObject(String)}异常，返回defaultValue</li>
     *         <li>返回{@link JSONObject#getJSONObject(String)}</li>
     *         </ul>
     */
    public static JSONObject getJSONObject(JSONObject jsonObject, String key, JSONObject defaultValue) {
        if (jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getJSONObject(key);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    /**
     * 从jsonData中根据key得到JSONObject型值，若存在异常则返回默认值
     * 
     * @param jsonData
     * @param key
     * @param defaultValue
     * @return
     *         <ul>
     *         <li>若jsonData 为null或为空，返回defaultValue</li>
     *         <li>若jsonData {@link JSONObject#JSONObject(String)}异常，返回defaultValue</li>
     *         <li>调用{@link JSONUtils#getJSONObject(JSONObject, String, JSONObject)}</li>
     *         </ul>
     */
    public static JSONObject getJSONObject(String jsonData, String key, JSONObject defaultValue) {
        if (StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getJSONObject(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    /**
     * 从JSONObject中根据key得到JSONArray值，若存在异常则返回默认值
     * 
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return
     *         <ul>
     *         <li>若jsonObject为null，返回defaultValue</li>
     *         <li>若key为null或为空字符串，返回defaultValue</li>
     *         <li>若{@link JSONObject#getJSONArray(String)}异常，返回defaultValue</li>
     *         <li>返回{@link JSONObject#getJSONArray(String)}</li>
     *         </ul>
     */
    public static JSONArray getJSONArray(JSONObject jsonObject, String key, JSONArray defaultValue) {
        if (jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getJSONArray(key);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    /**
     * 从jsonData中根据key得到JSONArray型值，若存在异常则返回默认值
     * 
     * @param jsonData
     * @param key
     * @param defaultValue
     * @return
     *         <ul>
     *         <li>若jsonData 为null或为空，返回defaultValue</li>
     *         <li>若jsonData {@link JSONObject#JSONObject(String)}异常，返回defaultValue</li>
     *         <li>调用{@link JSONUtils#getJSONArray(JSONObject, String, JSONObject)}</li>
     *         </ul>
     */
    public static JSONArray getJSONArray(String jsonData, String key, JSONArray defaultValue) {
        if (StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getJSONArray(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    /**
     * 从JSONObject中根据key得到boolean型值，若存在异常则返回默认值
     * 
     * @param jsonObject
     * @param key
     * @param defaultValue
     * @return
     *         <ul>
     *         <li>若jsonObject为null，返回defaultValue</li>
     *         <li>若key为null或为空字符串，返回defaultValue</li>
     *         <li>若key对应的value为true，则返回true，否则返回false</li>
     *         </ul>
     */
    public static boolean getBoolean(JSONObject jsonObject, String key, Boolean defaultValue) {
        if (jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getBoolean(key);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    /**
     * 从jsonData中根据key得到boolean型值，若存在异常则返回默认值
     * 
     * @param jsonData
     * @param key
     * @param defaultValue
     * @return
     *         <ul>
     *         <li>若jsonData 为null或为空，返回defaultValue</li>
     *         <li>若jsonData {@link JSONObject#JSONObject(String)}异常，返回defaultValue</li>
     *         <li>调用{@link JSONUtils#getBoolean(JSONObject, String, Boolean)}</li>
     *         </ul>
     */
    public static boolean getBoolean(String jsonData, String key, Boolean defaultValue) {
        if (StringUtils.isEmpty(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getBoolean(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            return defaultValue;
        }
    }

    /**
     * 将key和value键值对形式的json转换成map，忽略为空的key，在解析异常时put空字符串
     * 
     * @param sourceObj key和value键值对形式的json
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, String> parseKeyAndValueToMap(JSONObject sourceObj) {
        if (sourceObj == null) {
            return null;
        }

        Map<String, String> keyAndValueMap = new HashMap<String, String>();
        for (Iterator iter = sourceObj.keys(); iter.hasNext();) {
            String key = (String)iter.next();
            MapUtils.putMapNotEmptyKey(keyAndValueMap, key, getString(sourceObj, key, ""));
        }
        return keyAndValueMap;
    }

    /**
     * 将key和value键值对形式的json转换成map，忽略为空的key，在解析异常时put空字符串
     * 
     * @param source key和value键值对形式的json 字符串
     * @return
     * @see
     *      <ul>
     *      <li>若source 为null或为空，返回null</li>
     *      <li>若jsonData {@link JSONObject#JSONObject(String)}异常，返回null</li>
     *      <li>调用{@link JSONUtils#parseKeyAndValueToMap(JSONObject)}</li>
     *      </ul>
     */
    public static Map<String, String> parseKeyAndValueToMap(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }

        try {
            JSONObject jsonObject = new JSONObject(source);
            return parseKeyAndValueToMap(jsonObject);
        } catch (JSONException e) {
            return null;
        }
    }
}
