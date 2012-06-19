package com.trinea.java.common;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class HttpUtilsTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCreateParameterStringString() {
        assertTrue("&=".equals(HttpUtils.createParameter(null, null)));
        assertTrue("&=".equals(HttpUtils.createParameter(null, "")));
        assertTrue("&=".equals(HttpUtils.createParameter("", null)));
        assertTrue("&name=trinea".equals(HttpUtils.createParameter("name", "trinea")));
    }

    public void testCreateParameterStringStringBoolean() {
        assertTrue("=".equals(HttpUtils.createParameter(null, null, true)));
        assertTrue("&=".equals(HttpUtils.createParameter(null, null, false)));
        assertTrue("name=trinea".equals(HttpUtils.createParameter("name", "trinea", true)));
        assertTrue("&name=trinea".equals(HttpUtils.createParameter("name", "trinea", false)));
    }

    public void testGetUrlWithParas() {
        assertEquals(HttpUtils.getUrlWithParas(null, null), "");
        Map<String, String> parasMap = new HashMap<String, String>();
        assertEquals(HttpUtils.getUrlWithParas(null, parasMap), "");
        assertEquals(HttpUtils.getUrlWithParas("trinea.iteye.com", parasMap), "trinea.iteye.com");
        parasMap.put("a", "b");
        assertEquals(HttpUtils.getUrlWithParas(null, parasMap), "?a=b");
        assertEquals(HttpUtils.getUrlWithParas("trinea.iteye.com", parasMap), "trinea.iteye.com?a=b");
        parasMap.put("i", "j");
        assertTrue(JUnitTestUtils.assertEquals(HttpUtils.getUrlWithParas("trinea.iteye.com", parasMap),
                                               "trinea.iteye.com?a=b&i=j", "trinea.iteye.com?i=j&a=b"));
    }

    public void testGetUrlWithOrderedParas() {
        assertEquals(HttpUtils.getUrlWithOrderedParas(null, null), "");
        Map<String, String> parasMap = new HashMap<String, String>();
        assertEquals(HttpUtils.getUrlWithOrderedParas(null, parasMap), "");
        assertEquals(HttpUtils.getUrlWithOrderedParas("trinea.iteye.com", parasMap), "trinea.iteye.com");
        parasMap.put("a", "b");
        assertEquals(HttpUtils.getUrlWithOrderedParas(null, parasMap), "?a=b");
        assertEquals(HttpUtils.getUrlWithOrderedParas("trinea.iteye.com", parasMap), "trinea.iteye.com?a=b");
        parasMap.put("i", "j");
        assertEquals(HttpUtils.getUrlWithOrderedParas("trinea.iteye.com", parasMap), "trinea.iteye.com?a=b&i=j");
        parasMap.put("c", "d");
        assertEquals(HttpUtils.getUrlWithOrderedParas("trinea.iteye.com", parasMap), "trinea.iteye.com?a=b&c=d&i=j");
    }

    public void testGetUrlWithValueEncodeParas() {
        assertEquals(HttpUtils.getUrlWithValueEncodeParas(null, null), "");
        Map<String, String> parasMap = new HashMap<String, String>();
        assertEquals(HttpUtils.getUrlWithValueEncodeParas(null, parasMap), "");
        assertEquals(HttpUtils.getUrlWithValueEncodeParas("trinea.iteye.com", parasMap), "trinea.iteye.com");
        parasMap.put("a", "啊啊");
        assertEquals(HttpUtils.getUrlWithValueEncodeParas(null, parasMap), "?a=" + HttpUtils.utf8Encode("啊啊"));
        assertEquals(HttpUtils.getUrlWithValueEncodeParas("trinea.iteye.com", parasMap), "trinea.iteye.com?a="
                                                                                         + HttpUtils.utf8Encode("啊啊"));
        parasMap.put("i", "爱爱");
        assertTrue(JUnitTestUtils.assertEquals(HttpUtils.getUrlWithValueEncodeParas("trinea.iteye.com", parasMap),
                                               "trinea.iteye.com?a=" + HttpUtils.utf8Encode("啊啊") + "&i="
                                                       + HttpUtils.utf8Encode("爱爱"),
                                               "trinea.iteye.com?i=" + HttpUtils.utf8Encode("爱爱") + "&a="
                                                       + HttpUtils.utf8Encode("啊啊")));
    }

    public void testGetUrlWithOrderedValueEncodeParas() {
        assertEquals(HttpUtils.getUrlWithOrderedValueEncodeParas(null, null), "");
        Map<String, String> parasMap = new HashMap<String, String>();
        assertEquals(HttpUtils.getUrlWithOrderedValueEncodeParas(null, parasMap), "");
        assertEquals(HttpUtils.getUrlWithOrderedValueEncodeParas("trinea.iteye.com", parasMap), "trinea.iteye.com");
        parasMap.put("a", "啊啊");
        assertEquals(HttpUtils.getUrlWithOrderedValueEncodeParas(null, parasMap), "?a=" + HttpUtils.utf8Encode("啊啊"));
        assertEquals(HttpUtils.getUrlWithOrderedValueEncodeParas("trinea.iteye.com", parasMap),
                     "trinea.iteye.com?a=" + HttpUtils.utf8Encode("啊啊"));
        parasMap.put("i", "爱爱");
        assertEquals(HttpUtils.getUrlWithOrderedValueEncodeParas("trinea.iteye.com", parasMap),
                     "trinea.iteye.com?a=" + HttpUtils.utf8Encode("啊啊") + "&i=" + HttpUtils.utf8Encode("爱爱"));
        parasMap.put("c", "滴滴");
        assertEquals(HttpUtils.getUrlWithOrderedValueEncodeParas("trinea.iteye.com", parasMap),
                     "trinea.iteye.com?a=" + HttpUtils.utf8Encode("啊啊") + "&c=" + HttpUtils.utf8Encode("滴滴") + "&i="
                             + HttpUtils.utf8Encode("爱爱"));
    }

    public void testGetParas() {
        assertEquals(HttpUtils.getParas(null), "");
        Map<String, String> parasMap = new HashMap<String, String>();
        assertEquals(HttpUtils.getParas(parasMap), "");
        parasMap.put("a", "b");
        assertEquals(HttpUtils.getParas(parasMap), "a=b");
        parasMap.put("i", "j");
        assertTrue(JUnitTestUtils.assertEquals(HttpUtils.getParas(parasMap), "a=b&i=j", "i=j&a=b"));
    }

    public void testGetOrderedParas() {
        assertEquals(HttpUtils.getOrderedParas(null), "");
        Map<String, String> parasMap = new HashMap<String, String>();
        assertEquals(HttpUtils.getOrderedParas(parasMap), "");
        parasMap.put("a", "b");
        assertEquals(HttpUtils.getOrderedParas(parasMap), "a=b");
        parasMap.put("i", "j");
        assertEquals(HttpUtils.getOrderedParas(parasMap), "a=b&i=j");
        parasMap.put("c", "d");
        assertEquals(HttpUtils.getOrderedParas(parasMap), "a=b&c=d&i=j");
    }

    public void testGetValueEncodeParas() {
        assertEquals(HttpUtils.getValueEncodeParas(null), "");
        Map<String, String> parasMap = new HashMap<String, String>();
        assertEquals(HttpUtils.getValueEncodeParas(parasMap), "");
        parasMap.put("a", "啊啊");
        assertEquals(HttpUtils.getValueEncodeParas(parasMap), "a=" + HttpUtils.utf8Encode("啊啊"));
        parasMap.put("i", "爱爱");
        assertTrue(JUnitTestUtils.assertEquals(HttpUtils.getValueEncodeParas(parasMap),
                                               "a=" + HttpUtils.utf8Encode("啊啊") + "&i=" + HttpUtils.utf8Encode("爱爱"),
                                               "i=" + HttpUtils.utf8Encode("爱爱") + "&a=" + HttpUtils.utf8Encode("啊啊")));
    }

    public void testGetOrderedValueEncodeParas() {
        assertEquals(HttpUtils.getOrderedValueEncodeParas(null), "");
        Map<String, String> parasMap = new HashMap<String, String>();
        assertEquals(HttpUtils.getOrderedValueEncodeParas(parasMap), "");
        parasMap.put("a", "啊啊");
        assertEquals(HttpUtils.getOrderedValueEncodeParas(parasMap), "a=" + HttpUtils.utf8Encode("啊啊"));
        parasMap.put("i", "爱爱");
        assertEquals(HttpUtils.getOrderedValueEncodeParas(parasMap), "a=" + HttpUtils.utf8Encode("啊啊") + "&i="
                                                                     + HttpUtils.utf8Encode("爱爱"));
        parasMap.put("c", "滴滴");
        assertEquals(HttpUtils.getOrderedValueEncodeParas(parasMap),
                     "a=" + HttpUtils.utf8Encode("啊啊") + "&c=" + HttpUtils.utf8Encode("滴滴") + "&i="
                             + HttpUtils.utf8Encode("爱爱"));
    }

    public void testGetParasMapString() {
        assertTrue(HttpUtils.getParasMap(null) == null);
        Map<String, String> parasMap = new HashMap<String, String>();
        parasMap.put("a", "b");
        assertTrue(JUnitTestUtils.assertEquals(HttpUtils.getParasMap("a=b"), parasMap));
        assertTrue(JUnitTestUtils.assertEquals(HttpUtils.getParasMap("a=b&"), parasMap));
        assertTrue(JUnitTestUtils.assertEquals(HttpUtils.getParasMap("a=b&c"), parasMap));
        parasMap.put("c", "d");
        assertTrue(JUnitTestUtils.assertEquals(HttpUtils.getParasMap("a=b&c=d"), parasMap));
        assertFalse(JUnitTestUtils.assertEquals(HttpUtils.getParasMap("a=b:c=d"), parasMap));
    }

    public void testGetParasMapStringString() {
        assertTrue(HttpUtils.getParasMap(null, null) == null);
        assertTrue(HttpUtils.getParasMap("", null) == null);
        Map<String, String> parasMap = new HashMap<String, String>();
        parasMap.put("a", "b");
        assertTrue(JUnitTestUtils.assertEquals(HttpUtils.getParasMap("a=b", null), parasMap));
        assertTrue(JUnitTestUtils.assertEquals(HttpUtils.getParasMap("a=b&", null), parasMap));
        assertTrue(JUnitTestUtils.assertEquals(HttpUtils.getParasMap("a=b&c", null), parasMap));
        parasMap.put("c", "d");
        assertTrue(JUnitTestUtils.assertEquals(HttpUtils.getParasMap("a=b&c=d", null), parasMap));
        assertTrue(JUnitTestUtils.assertEquals(HttpUtils.getParasMap("a=b&c=d", "&"), parasMap));
        assertFalse(JUnitTestUtils.assertEquals(HttpUtils.getParasMap("a=b&c=d", ":"), parasMap));
        assertTrue(JUnitTestUtils.assertEquals(HttpUtils.getParasMap("a=b:c=d", ":"), parasMap));
    }

    public void testUtf8Encode() {
        assertEquals(HttpUtils.utf8Encode(null), null);
        assertEquals(HttpUtils.utf8Encode(""), "");
        assertEquals(HttpUtils.utf8Encode("http://www.baidu.com"), "http%3A%2F%2Fwww.baidu.com");
        assertEquals(HttpUtils.utf8Encode("http://www.baidu.com?query1=a&query2=b"),
                     "http%3A%2F%2Fwww.baidu.com%3Fquery1%3Da%26query2%3Db");
        assertEquals(HttpUtils.utf8Encode("http://www.baidu.com?query1=啊啊"),
                     "http%3A%2F%2Fwww.baidu.com%3Fquery1%3D%E5%95%8A%E5%95%8A");
    }

    public void testUtf8Decode() {
        assertEquals(HttpUtils.utf8Decode(null), null);
        assertEquals(HttpUtils.utf8Decode(""), "");
        assertEquals(HttpUtils.utf8Decode("http%3A%2F%2Fwww.baidu.com"), "http://www.baidu.com");
        assertEquals(HttpUtils.utf8Decode("http%3A%2F%2Fwww.baidu.com%3Fquery1%3Da%26query2%3Db"),
                     "http://www.baidu.com?query1=a&query2=b");
        assertEquals(HttpUtils.utf8Decode("http%3A%2F%2Fwww.baidu.com%3Fquery1%3D%E5%95%8A%E5%95%8A"),
                     "http://www.baidu.com?query1=啊啊");
    }

    public void testGetQueryParameterStringString() {
        assertEquals(HttpUtils.getQueryParameter(null, null), null);
        assertEquals(HttpUtils.getQueryParameter(null, "aa"), null);
        assertEquals(HttpUtils.getQueryParameter("http://www.baidu.com?query1=a&query2=b", null), null);
        assertEquals(HttpUtils.getQueryParameter("http://www.baidu.com", "query1"), null);
        assertEquals(HttpUtils.getQueryParameter("http://www.baidu.com?", "query1"), null);
        assertTrue("".equals(HttpUtils.getQueryParameter("http://www.baidu.com?query1", "query1")));
        assertTrue("".equals(HttpUtils.getQueryParameter("http://www.baidu.com?query1=", "query1")));
        assertTrue("".equals(HttpUtils.getQueryParameter("http://www.baidu.com?query1=&query2=b", "query1")));
        assertTrue("a".equals(HttpUtils.getQueryParameter("http://www.baidu.com?query1=a&query2=b", "query1")));
    }

    public void testGetQueryParameterStringStringStringString() {
        assertEquals(HttpUtils.getQueryParameter(null, null, "?", "&"), null);
        assertEquals(HttpUtils.getQueryParameter(null, "aa", "?", "&"), null);
        assertEquals(HttpUtils.getQueryParameter("http://www.baidu.com?query1=a&query2=b", null, "?", "&"), null);
        assertEquals(HttpUtils.getQueryParameter("http://www.baidu.com", "query1", "?", "&"), null);
        assertEquals(HttpUtils.getQueryParameter("http://www.baidu.com?", "query1", "?", "&"), null);
        assertTrue("".equals(HttpUtils.getQueryParameter("http://www.baidu.com?query1", "query1", "?", "&")));
        assertTrue("".equals(HttpUtils.getQueryParameter("http://www.baidu.com?query1=", "query1", "?", "&")));
        assertTrue("".equals(HttpUtils.getQueryParameter("http://www.baidu.com?query1=&query2=b", "query1", "?", "&")));
        assertTrue("a".equals(HttpUtils.getQueryParameter("http://www.baidu.com?query1=a&query2=b", "query1", "?", "&")));
        assertTrue("a".equals(HttpUtils.getQueryParameter("http://www.baidu.com#query1=a,query2=b", "query1", "#", ",")));
    }

    public void testGetUrlPath() {
        assertEquals(HttpUtils.getUrlPath(null), null);
        assertEquals(HttpUtils.getUrlPath(""), null);
        assertEquals(HttpUtils.getUrlPath("http://www.baidu.com/s"), "http://www.baidu.com/s");
        assertEquals(HttpUtils.getUrlPath("http://www.baidu.com/s?wd=a"), "http://www.baidu.com/s");
        assertEquals(HttpUtils.getUrlPath("http://www.baidu.com/s?wd=a&issp=1"), "http://www.baidu.com/s");
    }

    public void testHttpGetString() {
        assertEquals(HttpUtils.httpGet(null), null);
        assertEquals(HttpUtils.httpGet(""), null);
        assertFalse(StringUtils.isEmpty(HttpUtils.httpGet("http://www.baidu.com/")));

        try {
            StringUtils.isEmpty(HttpUtils.httpGet("www.baidu.com"));
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    public void testHttpGetStringString() {
        assertEquals(HttpUtils.httpGet(null, ""), null);
        assertEquals(HttpUtils.httpGet("", ""), null);
        assertFalse(StringUtils.isEmpty(HttpUtils.httpGet("http://www.baidu.com/", "")));
        assertFalse(StringUtils.isEmpty(HttpUtils.httpGet("http://www.baidu.com/", "wd=a")));
        try {
            StringUtils.isEmpty(HttpUtils.httpGet("www.baidu.com"));
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
        try {
            StringUtils.isEmpty(HttpUtils.httpGet("www.baidu.com", "wd=a"));
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    public void testHttpGetStringMapOfStringString() {
        Map<String, String> parasMap = new HashMap<String, String>();
        assertFalse(StringUtils.isEmpty(HttpUtils.httpGet("http://www.baidu.com/", parasMap)));
        parasMap.put("wd", "a");
        assertFalse(StringUtils.isEmpty(HttpUtils.httpGet("http://www.baidu.com/", parasMap)));
    }

    public void testHttpGetEncodeParas() {
        Map<String, String> parasMap = new HashMap<String, String>();
        assertFalse(StringUtils.isEmpty(HttpUtils.httpGetEncodeParas("http://www.baidu.com/", parasMap)));
        parasMap.put("wd", "a");
        assertFalse(StringUtils.isEmpty(HttpUtils.httpGetEncodeParas("http://www.baidu.com/", parasMap)));
        parasMap.put("wd", "啊啊");
        assertFalse(StringUtils.isEmpty(HttpUtils.httpGetEncodeParas("http://www.baidu.com/", parasMap)));
    }

    public void testHttpPostStringString() {
        assertEquals(HttpUtils.httpPost(null, ""), null);
        assertEquals(HttpUtils.httpPost("", ""), null);
        assertTrue(StringUtils.isEmpty(HttpUtils.httpPost("http://www.baidu.com/", "")));
        try {
            StringUtils.isEmpty(HttpUtils.httpPost("www.baidu.com", ""));
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    public void testHttpPostStringMapOfStringString() {
    }

    public void testHttpPostEncodeParas() {
    }

    public void testHttpPostWithFileStringStringMapOfStringString() {
    }

    public void testHttpPostWithFileStringMapOfStringStringMapOfStringString() {
    }

    public void testHttpPostEncodeParasWithFileStringMapOfStringStringMapOfStringString() {
    }

    public void testHttpPostEncodeParasWithFileStringStringMapOfStringString() {
    }

}
