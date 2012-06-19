package com.trinea.java.common;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

/**
 * http工具函数
 * 
 * @author Trinea 2011-9-18 下午11:17:34
 */
public class HttpUtils {

    /** url中url部分和 参数的分隔符 **/
    public static final String URL_AND_PARA_SEPARATOR = "?";
    /** url中 参数之间的分隔符 **/
    public static final String PARAMETERS_SEPARATOR   = "&";
    /** url中路径之间的分隔符 **/
    public static final String PATHS_SEPARATOR        = "/";
    /** url中参数名和参数值分隔符，即等号 **/
    public static final String EQUAL_SIGN             = "=";
    /** http get 请求方式 **/
    public static String       HTTP_GET_METHOD        = "get";
    /** http post 请求方式 **/
    public static String       HTTP_POST_METHOD       = "post";

    /**
     * 创建参数对
     * <ul>
     * <li>同{@link HttpUtils#createParameter(String key, String value, boolean isFirst)} isFirst为false情况</li>
     * </ul>
     * 
     * @param key
     * @param value
     * @return
     */
    public static String createParameter(String key, String value) {
        return createParameter(key, value, false);
    }

    /**
     * 创建参数对
     * 
     * <pre>
     * createParameter(null, null, true)        =   "=";
     * createParameter(null, null, false)       =   "&=";
     * createParameter(name, trinea, true)      =   "name=trinea";
     * createParameter(name, trinea, false)     =   "&name=trinea";
     * </pre>
     * 
     * @param key 参数名
     * @param value 参数值
     * @param isFirst 是否是第一个参数
     * @return
     *         <ul>
     *         <li>若key和value为null，默认当作""(空字符串)处理</li>
     *         <li>isFirst为true和false的差别，仅仅在为false时会在返回结果前面加上{@link HttpUtils#PARAMETERS_SEPARATOR}</li>
     *         </ul>
     */
    public static String createParameter(String key, String value, boolean isFirst) {
        if (StringUtils.isEmpty(key)) {
            key = "";
        }
        if (StringUtils.isEmpty(value)) {
            value = "";
        }
        return (isFirst ? (key + EQUAL_SIGN + value) : (PARAMETERS_SEPARATOR + key + EQUAL_SIGN + value));
    }

    /**
     * 得到带参数的url
     * 
     * <pre>
     * getUrlWithParas(null, {(a, b)})                                  =   "?a=b";
     * getUrlWithParas("trinea.iteye.com", {})                          =   "trinea.iteye.com";
     * getUrlWithParas("trinea.iteye.com", {(a, b), (i, j)})            =   "trinea.iteye.com?a=b&i=j";
     * getUrlWithParas("trinea.iteye.com", {(a, b), (i, j), (c, d)})    =   "trinea.iteye.com?a=b&i=j&c=d";
     * </pre>
     * 
     * @param url url
     * @param parasMap 参数map,value为参数名，value为参数值
     * @return
     *         <ul>
     *         <li>若url为null，默认当作""(空字符串)处理</li>
     *         </ul>
     */
    public static String getUrlWithParas(String url, Map<String, String> parasMap) {
        StringBuilder urlWithParas = new StringBuilder(StringUtils.isEmpty(url) ? "" : url);
        String paras = getParas(parasMap);
        if (!StringUtils.isEmpty(paras)) {
            urlWithParas.append(URL_AND_PARA_SEPARATOR).append(paras);
        }
        return urlWithParas.toString();
    }

    /**
     * 得到带有序参数串的url
     * 
     * <pre>
     * getUrlWithParas(null, {(a, b)})                                =   "?a=b";
     * getUrlWithOrderedParas("trinea.iteye.com", {})                 =   "trinea.iteye.com";
     * getUrlWithOrderedParas("trinea.iteye.com", {(a, b), (c, d)})   =   "trinea.iteye.com?a=b&c=d";
     * getUrlWithOrderedParas("trinea.iteye.com", {(a, b), (i, j), (c, d)})   =   "trinea.iteye.com?a=b&c=d&i=j";
     * </pre>
     * 
     * @param url url
     * @param parasMap 参数map,value为参数名，value为参数值
     * @return
     *         <ul>
     *         <li>若url为null，默认当作""(空字符串)处理</li>
     *         </ul>
     */
    public static String getUrlWithOrderedParas(String url, Map<String, String> parasMap) {
        StringBuilder urlWithParas = new StringBuilder(StringUtils.isEmpty(url) ? "" : url);
        String paras = getOrderedParas(parasMap);
        if (!StringUtils.isEmpty(paras)) {
            urlWithParas.append(URL_AND_PARA_SEPARATOR).append(paras);
        }
        return urlWithParas.toString();
    }

    /**
     * 得到带参数的url，参数的值经过utf-8编码
     * 
     * @param url url
     * @param parasMap 参数map,value为参数名，value为参数值
     * @return
     *         <ul>
     *         <li>若url为null，默认当作""(空字符串)处理</li>
     *         <li>相比{@link HttpUtils#getUrlWithParas(String, Map)}，区别为此函数会先对参数map中参数的值进行utf-8编码</li>
     *         </ul>
     */
    public static String getUrlWithValueEncodeParas(String url, Map<String, String> parasMap) {
        StringBuilder urlWithParas = new StringBuilder(StringUtils.isEmpty(url) ? "" : url);
        String paras = getValueEncodeParas(parasMap);
        if (!StringUtils.isEmpty(paras)) {
            urlWithParas.append(URL_AND_PARA_SEPARATOR).append(paras);
        }
        return urlWithParas.toString();
    }

    /**
     * 得到带有序参数串的url
     * 
     * @param url url
     * @param parasMap 参数map,value为参数名，value为参数值
     * @return
     *         <ul>
     *         <li>若url为null，默认当作""(空字符串)处理</li>
     *         <li>相比{@link HttpUtils#getUrlWithOrderedParas(String, Map)}，区别为此函数会先对参数map中参数的值进行utf-8编码</li>
     *         </ul>
     */
    public static String getUrlWithOrderedValueEncodeParas(String url, Map<String, String> parasMap) {
        StringBuilder urlWithParas = new StringBuilder(StringUtils.isEmpty(url) ? "" : url);
        String paras = getOrderedValueEncodeParas(parasMap);
        if (!StringUtils.isEmpty(paras)) {
            urlWithParas.append(URL_AND_PARA_SEPARATOR).append(paras);
        }
        return urlWithParas.toString();
    }

    /**
     * 得到参数串
     * 
     * <pre>
     * getParas({})                 =   "";
     * getParas({(a, b), (c, d)})   =   "a=b&c=d";
     * </pre>
     * 
     * @param parasMap 参数map,value为参数名，value为参数值
     * @return
     *         <ul>
     *         <li>若parasMap为null，返回""(空字符串)</li>
     *         </ul>
     */
    public static String getParas(Map<String, String> parasMap) {
        StringBuilder paras = new StringBuilder("");
        if (parasMap != null && parasMap.size() > 0) {
            Iterator<Map.Entry<String, String>> ite = parasMap.entrySet().iterator();
            while (ite.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry<String, String>)ite.next();
                paras.append(entry.getKey()).append(EQUAL_SIGN).append(entry.getValue());
                if (ite.hasNext()) {
                    paras.append(PARAMETERS_SEPARATOR);
                }
            }
        }
        return paras.toString();
    }

    /**
     * 得到有序的参数串
     * 
     * <pre>
     * getOrderedParas({})                 =   "";
     * getOrderedParas({(a, b), (c, d)})   =   "a=b&c=d";
     * getOrderedParas({(a, b), (i, j), (c, d)})   =   "a=b&c=d&i=j";
     * </pre>
     * 
     * @param parasMap 参数map,value为参数名，value为参数值
     * @return
     *         <ul>
     *         <li>若parasMap为null，返回""(空字符串)</li>
     *         <li>parasMap无须有序，结果以key的字母排序</li>
     *         </ul>
     */
    public static String getOrderedParas(Map<String, String> parasMap) {

        if (parasMap == null || parasMap.size() == 0) {
            return "";
        }
        TreeMap<String, String> orderedParasMap = new TreeMap<String, String>(parasMap);
        return getParas(orderedParasMap);
    }

    /**
     * 得到参数串，参数的值经过utf-8编码
     * 
     * @param parasMap 参数map,value为参数名，value为参数值
     * @return
     *         <ul>
     *         <li>若parasMap为null，返回""(空字符串)</li>
     *         <li>相比{@link HttpUtils#getParas(Map)}，区别为此函数会先对参数map中参数的值进行utf-8编码</li>
     *         </ul>
     */
    public static String getValueEncodeParas(Map<String, String> parasMap) {
        StringBuilder paras = new StringBuilder("");
        if (parasMap != null && parasMap.size() > 0) {
            Iterator<Map.Entry<String, String>> ite = parasMap.entrySet().iterator();
            while (ite.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry<String, String>)ite.next();
                paras.append(entry.getKey()).append(EQUAL_SIGN).append(utf8Encode(entry.getValue()));
                if (ite.hasNext()) {
                    paras.append(PARAMETERS_SEPARATOR);
                }
            }
        }
        return paras.toString();
    }

    /**
     * 得到有序的参数串，参数的值经过utf-8编码
     * 
     * @param parasMap 参数map,value为参数名，value为参数值
     * @return
     *         <ul>
     *         <li>若parasMap为null，返回""(空字符串)</li>
     *         <li>parasMap无须有序，结果以key的字母排序</li>
     *         <li>相比{@link HttpUtils#getOrderedParas(Map)}，区别为此函数会先对参数map中参数的值进行utf-8编码</li>
     *         </ul>
     */
    public static String getOrderedValueEncodeParas(Map<String, String> parasMap) {

        if (MapUtils.isEmpty(parasMap)) {
            return "";
        }

        TreeMap<String, String> orderedParasMap = new TreeMap<String, String>(parasMap);
        return getValueEncodeParas(orderedParasMap);
    }

    /**
     * 解析字符串得到key和value对应的map
     * 
     * @param paras 字符串
     * @return
     *         <ul>
     *         <li>见 {@link HttpUtils#getParasMap(String, String)}</li>
     *         </ul>
     */
    public static Map<String, String> getParasMap(String paras) {
        return getParasMap(paras, PARAMETERS_SEPARATOR);
    }

    /**
     * 解析字符串得到key和value对应的map
     * 
     * <pre>
     * getParasMap(null, null) = null
     * getParasMap("", null) = null
     * getParasMap("a=b", null) = {(a, b)}
     * getParasMap("a=b&", null) = {(a, b)}
     * getParasMap("a=b&c", null) = {(a, b)}
     * getParasMap("a=b&c=d", null) = {(a, b), (c, d)}
     * getParasMap("a=b&c=d", "&") = {(a, b), (c, d)}
     * getParasMap("a=b:c=d", ":") = {(a, b), (c, d)}
     * </pre>
     * 
     * @param paras 字符串
     * @param parametersSeparator 参数分隔符
     * @return
     *         <ul>
     *         <li>若paras为null或为空字符串，则返回null</li>
     *         <li>若parametersSeparator为null或为空字符串，则当作{@link HttpUtils#PARAMETERS_SEPARATOR}</li>
     *         </ul>
     */
    public static Map<String, String> getParasMap(String paras, String parametersSeparator) {
        if (StringUtils.isEmpty(paras)) {
            return null;
        }

        if (StringUtils.isEmpty(parametersSeparator)) {
            parametersSeparator = PARAMETERS_SEPARATOR;
        }

        Map<String, String> parasMap = new HashMap<String, String>();
        String[] parasArray = paras.split(parametersSeparator);
        int keyAndValueIndex = 0;
        for (String para : parasArray) {
            keyAndValueIndex = para.indexOf(EQUAL_SIGN);
            if (keyAndValueIndex != -1) {
                parasMap.put(para.substring(0, keyAndValueIndex), para.substring(keyAndValueIndex + 1));
            }
        }
        return parasMap;
    }

    /**
     * url utf8进行编码
     * 
     * <pre>
     * utf8Encode(null)        =   null;
     * utf8Encode("")          =   "";
     * utf8Encode("http://www.baidu.com")                   =   "http%3A%2F%2Fwww.baidu.com";
     * utf8Encode("http://www.baidu.com?query1=a&query2=b") =   "http%3A%2F%2Fwww.baidu.com%3Fquery1%3Da%26query2%3Db";
     * utf8Encode("http://www.baidu.com?query1=啊啊")         =   "http%3A%2F%2Fwww.baidu.com%3Fquery1%3D%E5%95%8A%E5%95%8A";
     * </pre>
     * 
     * @param url 原字符
     * @return
     *         <ul>
     *         <li>若url为null，返回null</li>
     *         <li>若url为空字符串，返回空字符串</li>
     *         <li>若url编码异常，抛出异常</li>
     *         </ul>
     */
    public static String utf8Encode(String url) {
        if (!StringUtils.isEmpty(url)) {
            try {
                return URLEncoder.encode(url, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
            }
        }
        return url;
    }

    /**
     * url utf8进行解码
     * 
     * <pre>
     * HttpUtils.utf8Decode(null) = null;
     * HttpUtils.utf8Decode("") = "";
     * HttpUtils.utf8Decode("http%3A%2F%2Fwww.baidu.com") = "http://www.baidu.com";
     * HttpUtils.utf8Decode("http%3A%2F%2Fwww.baidu.com%3Fquery1%3Da%26query2%3Db") = "http://www.baidu.com?query1=a&query2=b";
     * HttpUtils.utf8Decode("http%3A%2F%2Fwww.baidu.com%3Fquery1%3D%E5%95%8A%E5%95%8A" = "http://www.baidu.com?query1=啊啊";
     * </pre>
     * 
     * @param url 原字符
     * @return
     *         <ul>
     *         <li>若url为null，返回null</li>
     *         <li>若url为空字符串，返回空字符串</li>
     *         <li>若url编码异常，抛出异常</li>
     *         </ul>
     */
    public static String utf8Decode(String url) {
        if (!StringUtils.isEmpty(url)) {
            try {
                return URLDecoder.decode(url, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
            }
        }
        return url;
    }

    /**
     * 根据key得到url中的参数值
     * 
     * @param url url
     * @param key 参数key
     * @return
     */
    public static String getQueryParameter(String url, String key) {
        return getQueryParameter(url, key, URL_AND_PARA_SEPARATOR, PARAMETERS_SEPARATOR);
    }

    /**
     * 根据key得到url中的参数值
     * 
     * <pre>
     * getQueryParameter(null, null, "?", "&") = null;
     * getQueryParameter(null, "aa", "?", "&") = null;
     * getQueryParameter("http://www.baidu.com?query1=a&query2=b", null, "?", "&") = null;
     * getQueryParameter("http://www.baidu.com", "query1", "?", "&") = null;
     * getQueryParameter("http://www.baidu.com?", "query1", "?", "&") = null;
     * getQueryParameter("http://www.baidu.com?query1", "query1", "?", "&") = "";
     * getQueryParameter("http://www.baidu.com?query1=", "query1", "?", "&") = "";
     * getQueryParameter("http://www.baidu.com?query1=&query2=b", "query1", "?", "&") = "";
     * getQueryParameter("http://www.baidu.com?query1=a&query2=b", "query1", "?", "&") = "a";
     * getQueryParameter("http://www.baidu.com#query1=a,query2=b", "query1", "#", ",") = "a";
     * </pre>
     * 
     * @param url url
     * @param key 参数key
     * @param paraSeparator 参数之间分隔符
     * @param pathParaSeparator url path和参数之间分隔符
     * @return
     */
    public static String getQueryParameter(String url, String key, String pathParaSeparator, String paraSeparator) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(url)) {
            return null;
        }

        if (url.indexOf(pathParaSeparator) >= 0) {
            String paraPart = url.substring(url.indexOf(pathParaSeparator) + 1);
            if (!StringUtils.isEmpty(paraPart)) {
                String keyWithEqualSign = key + EQUAL_SIGN;
                String[] paraArray = paraPart.split(paraSeparator);
                for (String paraInfo : paraArray) {
                    if (paraInfo.equals(key) || paraInfo.equals(keyWithEqualSign)) {
                        return "";
                    } else if (paraInfo.indexOf(keyWithEqualSign) >= 0) {
                        return paraInfo.substring(paraInfo.indexOf(keyWithEqualSign) + keyWithEqualSign.length());
                    }
                }
            }
        }
        return null;
    }

    /**
     * 返回一个url的非query部分
     * 
     * <pre>
     * getUrlQuery(null) = null
     * getUrlQuery("") = null
     * getUrlQuery("http://www.baidu.com/s?wd=a") = "http://www.baidu.com/s"
     * getUrlQuery("http://www.baidu.com/s?wd=a&issp=1") = "http://www.baidu.com/s"
     * getUrlQuery("http://www.baidu.com/s?wd=a&issp=1#123") = "http://www.baidu.com/s"
     * </pre>
     * 
     * @param url
     * @return
     *         <ul>
     *         <li>注意是使用String的substring(0, indexOf(?))得到的，不一定精确</li>
     *         </ul>
     */
    public static String getUrlPath(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }

        if (url.indexOf(URL_AND_PARA_SEPARATOR) >= 0) {
            return url.substring(0, url.indexOf(URL_AND_PARA_SEPARATOR));
        }
        return url;
    }

    /**
     * http get请求
     * <ul>
     * <li>注意需要url参数加上http协议说明，如：http://www.baidu.com</li>
     * </ul>
     * 
     * @param url url地址
     * @param timeOut 超时时间，毫秒为单位
     * @return
     */
    public static String httpGet(String url, int timeOut) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }

        HttpClient httpClient = new HttpClient();
        GetMethod httpGet = new GetMethod(url);
        setTimeOut(httpClient, timeOut);

        try {
            if (httpClient.executeMethod(httpGet) != HttpStatus.SC_OK) {
                // System.err.println("HttpGet Method failed: " + httpGet.getStatusLine());
                return null;
            }
            return httpGet.getResponseBodyAsString();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred. ", e);
        } finally {
            httpGet.releaseConnection();
            httpClient = null;
        }
    }

    /**
     * http get请求，默认不设置超时时间
     * <ul>
     * <li>注意需要url参数加上http协议说明，如：http://www.baidu.com</li>
     * </ul>
     * 
     * @param url url地址
     * @return
     */
    public static String httpGet(String url) {
        return httpGet(url, -1);
    }

    /**
     * http get请求
     * <ul>
     * <li>注意需要url参数加上http协议说明，如：http://www.baidu.com</li>
     * </ul>
     * 
     * @param url url地址
     * @param paras url参数
     * @return
     */
    public static String httpGet(String url, String paras) {

        if (!StringUtils.isEmpty(paras)) {
            url += (URL_AND_PARA_SEPARATOR + paras);
        }

        return StringUtils.isEmpty(url) ? null : httpGet(url);
    }

    /**
     * http get请求
     * <ul>
     * <li>注意需要url参数加上http协议说明，如：http://www.baidu.com</li>
     * </ul>
     * 
     * @param url url地址
     * @param parasMap url参数map
     * @return
     */
    public static String httpGet(String url, Map<String, String> parasMap) {

        String urlWithParas = getUrlWithOrderedParas(url, parasMap);
        return StringUtils.isEmpty(urlWithParas) ? null : httpGet(urlWithParas);
    }

    /**
     * http get请求，参数的值经过utf-8编码
     * <ul>
     * <li>注意需要url参数加上http协议说明，如：http://www.baidu.com</li>
     * </ul>
     * 
     * @param url url地址
     * @param parasMap url参数map
     * @return
     */
    public static String httpGetEncodeParas(String url, Map<String, String> parasMap) {

        String urlWithParas = getUrlWithOrderedValueEncodeParas(url, parasMap);
        return StringUtils.isEmpty(urlWithParas) ? null : httpGet(urlWithParas);
    }

    /**
     * http post请求
     * <ul>
     * <li>注意需要url参数加上http协议说明，如：http://www.baidu.com</li>
     * </ul>
     * 
     * @param url url链接
     * @param paras url参数
     * @param timeOut 超时时间，毫秒为单位
     * @return
     */
    public static String httpPost(String url, String paras, int timeOut) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }

        HttpClient httpClient = new HttpClient();
        PostMethod httpPost = new PostMethod(url);
        httpPost.addParameter("Content-Type", "application/x-www-form-urlencoded");
        setTimeOut(httpClient, timeOut);

        if (!StringUtils.isEmpty(paras)) {
            httpPost.setRequestEntity(new ByteArrayRequestEntity(paras.getBytes()));
        }

        try {
            if (httpClient.executeMethod(httpPost) != HttpStatus.SC_OK) {
                // System.err.println("HttpPost Method failed: " + httpPost.getStatusLine());
                return null;
            }
            return httpPost.getResponseBodyAsString();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred. ", e);
        } finally {
            httpPost.releaseConnection();
            httpClient = null;
        }
    }

    /**
     * http post请求，不设置超时时间
     * <ul>
     * <li>注意需要url参数加上http协议说明，如：http://www.baidu.com</li>
     * </ul>
     * 
     * @param url url链接
     * @param paras url参数
     * @return
     */
    public static String httpPost(String url, String paras) {
        return httpPost(url, paras, -1);
    }

    /**
     * http post请求
     * <ul>
     * <li>注意需要url参数加上http协议说明，如：http://www.baidu.com</li>
     * </ul>
     * 
     * @param url url地址
     * @param parasMap url参数map
     * @return
     */
    public static String httpPost(String url, Map<String, String> parasMap) {
        return httpPost(url, getOrderedParas(parasMap));
    }

    /**
     * http post请求，参数的值经过utf-8 编码
     * <ul>
     * <li>注意需要url参数加上http协议说明，如：http://www.baidu.com</li>
     * </ul>
     * 
     * @param url url地址
     * @param parasMap url参数map
     * @return
     */
    public static String httpPostEncodeParas(String url, Map<String, String> parasMap) {
        return httpPost(url, getOrderedValueEncodeParas(parasMap));
    }

    /**
     * http post请求，可传送文件
     * <ul>
     * <li>注意需要url参数加上http协议说明，如：http://www.baidu.com</li>
     * </ul>
     * 
     * @param url url链接
     * @param paras url参数
     * @param filePathMap 文件map，value为post对应字段，value为file path
     * @param timeOut 超时时间，毫秒为单位
     * @return
     */
    public static String httpPostWithFile(String url, String paras, Map<String, String> filePathMap, int timeOut) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        if (MapUtils.isEmpty(filePathMap)) {
            return httpPost(url, paras);
        }
        if (!StringUtils.isEmpty(paras)) {
            url += (URL_AND_PARA_SEPARATOR + paras);
        }

        HttpClient httpClient = new HttpClient();
        setTimeOut(httpClient, timeOut);
        PostMethod httpPost = new PostMethod(url);
        try {
            Map<String, String> parasMap = getParasMap(paras);
            int length = parasMap.size() + (filePathMap == null ? 0 : filePathMap.size());
            Part[] parts = new Part[length];
            int i = 0;
            Iterator<Map.Entry<String, String>> paraIte = parasMap.entrySet().iterator();
            while (paraIte.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry<String, String>)paraIte.next();
                parts[i++] = new StringPart(entry.getKey().toString(), entry.getValue().toString(), "UTF-8");
            }
            Iterator<Map.Entry<String, String>> fileIte = filePathMap.entrySet().iterator();
            while (fileIte.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry<String, String>)fileIte.next();
                File file = new File(entry.getValue().toString());
                parts[i++] = new FilePart(entry.getKey().toString(), file.getName(), file, null, "UTF-8");
            }

            httpPost.setRequestEntity(new MultipartRequestEntity(parts, httpPost.getParams()));

            int statusCode = httpClient.executeMethod(httpPost);
            if (statusCode != HttpStatus.SC_OK) {
                // System.err.println("HttpPost Method failed: " + httpPost.getStatusLine());
                return null;
            }
            return httpPost.getResponseBodyAsString();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred. ", e);
        } finally {
            httpPost.releaseConnection();
            httpClient = null;
        }
    }

    /**
     * http post请求，可传送文件，不设置超时时间
     * <ul>
     * <li>注意需要url参数加上http协议说明，如：http://www.baidu.com</li>
     * </ul>
     * 
     * @param url url链接
     * @param paras url参数
     * @param filePathMap 文件map，value为post对应字段，value为file path
     * @return
     */
    public static String httpPostWithFile(String url, String paras, Map<String, String> filePathMap) {
        return httpPostWithFile(url, paras, filePathMap, -1);
    }

    /**
     * http post请求，可传送文件
     * <ul>
     * <li>注意需要url参数加上http协议说明，如：http://www.baidu.com</li>
     * </ul>
     * 
     * @param url url地址
     * @param parasMap url参数map
     * @param filePathMap 文件map，value为post对应字段，value为file path
     * @return
     */
    public static String httpPostWithFile(String url, Map<String, String> parasMap, Map<String, String> filePathMap) {
        return httpPostWithFile(url, getOrderedParas(parasMap), filePathMap);
    }

    /**
     * http post请求，可传送文件，参数的值经过utf-8编码
     * <ul>
     * <li>注意需要url参数加上http协议说明，如：http://www.baidu.com</li>
     * </ul>
     * 
     * @param url url地址
     * @param parasMap url参数map
     * @param filePathMap 文件map，value为post对应字段，value为file path
     * @return
     */
    public static String httpPostEncodeParasWithFile(String url, Map<String, String> parasMap,
                                                     Map<String, String> filePathMap) {
        return httpPostWithFile(url, getOrderedValueEncodeParas(parasMap), filePathMap);
    }

    /**
     * http post请求，可传送文件，参数的值经过utf-8编码
     * <ul>
     * <li>注意需要url参数加上http协议说明，如：http://www.baidu.com</li>
     * </ul>
     * 
     * @param url url地址
     * @param paras url参数
     * @param filePathMap 文件map，value为post对应字段，value为file path
     * @return
     */
    public static String httpPostEncodeParasWithFile(String url, String paras, Map<String, String> filePathMap) {
        return httpPostWithFile(url, paras, filePathMap);
    }

    /**
     * 设置超时时间
     * 
     * @param httpClient
     * @param timeOut
     */
    private static void setTimeOut(HttpClient httpClient, int timeOut) {
        if (httpClient != null && timeOut > 0) {
            HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
            managerParams.setConnectionTimeout(timeOut);
            managerParams.setSoTimeout(timeOut);
        }
    }
}
