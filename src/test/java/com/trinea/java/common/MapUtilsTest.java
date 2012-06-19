package com.trinea.java.common;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import junit.framework.TestCase;

public class MapUtilsTest extends TestCase {

    protected void setUp() throws Exception {
        MapUtils mapUtils = new MapUtils();
        assertTrue(mapUtils != null);
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testIsEmpty() {
        assertTrue(MapUtils.isEmpty(null));
        Map<String, String> sourceMap = new HashMap<String, String>();
        assertTrue(MapUtils.isEmpty(sourceMap));
        sourceMap.put("a", "b");
        assertFalse(MapUtils.isEmpty(sourceMap));
    }

    public void testPutMapNotEmptyKey() {
        assertFalse(MapUtils.putMapNotEmptyKey(null, null, null));

        Map<String, String> sourceMap = new HashMap<String, String>();
        assertFalse(MapUtils.putMapNotEmptyKey(sourceMap, null, null));
        assertFalse(MapUtils.putMapNotEmptyKey(sourceMap, "", null));
        assertFalse(MapUtils.putMapNotEmptyKey(sourceMap, "", "b"));
        assertTrue(sourceMap.size() == 0);

        assertTrue(MapUtils.putMapNotEmptyKey(sourceMap, "a", "b"));
        assertTrue(sourceMap.size() > 0);
    }

    public void testPutMapNotEmptyValueMapOfStringStringStringString() {
        assertFalse(MapUtils.putMapNotEmptyKeyAndValue(null, null, null));

        Map<String, String> sourceMap = new HashMap<String, String>();
        assertFalse(MapUtils.putMapNotEmptyKeyAndValue(sourceMap, null, null));
        assertFalse(MapUtils.putMapNotEmptyKeyAndValue(sourceMap, "", null));
        assertTrue(sourceMap.size() == 0);

        assertFalse(MapUtils.putMapNotEmptyKeyAndValue(sourceMap, null, "b"));
        assertFalse(MapUtils.putMapNotEmptyKeyAndValue(sourceMap, "", "b"));
        assertFalse(MapUtils.putMapNotEmptyKeyAndValue(sourceMap, "a", null));
        assertFalse(MapUtils.putMapNotEmptyKeyAndValue(sourceMap, "a", ""));
        assertTrue(sourceMap.size() == 0);

        assertTrue(MapUtils.putMapNotEmptyKeyAndValue(sourceMap, "a", "b"));
        assertTrue(sourceMap.size() > 0);
    }

    public void testputMapNotEmptyKeyAndValueMapOfStringStringStringStringString() {
        assertFalse(MapUtils.putMapNotEmptyKeyAndValue(null, null, null, null));

        Map<String, String> sourceMap = new HashMap<String, String>();
        assertFalse(MapUtils.putMapNotEmptyKeyAndValue(sourceMap, null, null, null));
        assertFalse(MapUtils.putMapNotEmptyKeyAndValue(sourceMap, "", null, null));
        assertFalse(MapUtils.putMapNotEmptyKeyAndValue(sourceMap, "", "b", null));
        assertTrue(sourceMap.size() == 0);

        assertTrue(MapUtils.putMapNotEmptyKeyAndValue(sourceMap, "a", null, "b"));
        assertTrue(sourceMap.size() > 0);
        assertTrue(sourceMap.containsKey("a") && sourceMap.get("a").equals("b"));
        assertTrue(MapUtils.putMapNotEmptyKeyAndValue(sourceMap, "c", "", "d"));
        assertTrue(sourceMap.size() > 0);
        assertTrue(sourceMap.containsKey("c") && sourceMap.get("c").equals("d"));
        assertTrue(MapUtils.putMapNotEmptyKeyAndValue(sourceMap, "e", "f", "g"));
        assertTrue(sourceMap.size() > 0);
        assertTrue(sourceMap.containsKey("e") && sourceMap.get("e").equals("f"));
    }

    public void testGetKeyByValue() {
        assertNull(MapUtils.getKeyByValue(null, null));

        Map<String, String> sourceMap = new TreeMap<String, String>();
        assertNull(MapUtils.getKeyByValue(sourceMap, null));
        sourceMap.put("a", "b");
        assertTrue("a".equals((MapUtils.getKeyByValue(sourceMap, "b"))));
        assertNull(MapUtils.getKeyByValue(sourceMap, "c"));

        sourceMap.put("c", "d");
        sourceMap.put("e", "f");
        sourceMap.put("g", "d");
        sourceMap.put("h", null);
        sourceMap.put("i", "j");
        assertTrue("c".equals((MapUtils.getKeyByValue(sourceMap, "d"))));
        assertTrue("h".equals((MapUtils.getKeyByValue(sourceMap, null))));
    }

    public void testParseKeyAndValueToMapStringStringStringString() {
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("", "", "", true), null));
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap(null, "", "", true), null));
        Map<String, String> parasMap = new HashMap<String, String>();
        parasMap.put("a", "b");
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a:b,:", "", "", true), parasMap));
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a:b,  : d", "", "", true), parasMap));
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a:b, c ", "", "", true), parasMap));
        parasMap.put("c", "");
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a:b, c: ", "", "", true), parasMap));
        parasMap.remove("c");
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a:b, c ", "", "", false), parasMap));
        parasMap.put(" c ", "");
        parasMap.remove(" c ");
        parasMap.put("c", "d");
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a:b, c : d", "", "", true), parasMap));
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a:b, c : d", ":", "", true), parasMap));
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a:b, c : d", ":", ",", true), parasMap));
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a=b, c = d", "=", ",", true), parasMap));
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a=b, c=d", "=", ",", true), parasMap));
        parasMap.remove("c");

        parasMap.put(" c ", " d");
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a:b, c : d", "", "", false), parasMap));
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a:b, c : d", ":", "", false), parasMap));
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a:b, c : d", ":", ",", false), parasMap));
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a=b, c = d", "=", ",", false), parasMap));
        parasMap.remove(" c ");
        parasMap.put(" c", "d");
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a=b, c=d", "=", ",", false), parasMap));
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a=b; c=d", "=", ";", false), parasMap));

        parasMap.clear();
        parasMap.put("\"a", "\"b");
        parasMap.put("c", "\"d");
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("\"a:\"b, c : \"d", ":", ",", true),
                                               parasMap));

        parasMap.clear();
        parasMap.put("'a'", "'b'");
        parasMap.put("'c'", "d");
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("'a':'b', 'c' : d", ":", ",", true),
                                               parasMap));

        parasMap.clear();
        parasMap.put("a=b", " c=d");
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a=b, c=d", ",", ";", false), parasMap));
    }

    public void testParseKeyAndValueToMapString() {
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap(null), null));
        Map<String, String> parasMap = new HashMap<String, String>();
        parasMap.put("a", "b");
        parasMap.put("  c", " d");
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a:b,  c: d", false), parasMap));
        parasMap.clear();
        parasMap.put("a", "b");
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a:b,  : d", true), parasMap));
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a:b,  : d"), parasMap));
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a:b, c "), parasMap));
        parasMap.put("c", "d");
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a:b, c : d"), parasMap));
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a:b, c : d"), parasMap));
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a:b, c : d"), parasMap));
        assertTrue(JUnitTestUtils.assertEquals(MapUtils.parseKeyAndValueToMap("a:b, c : d"), parasMap));

        assertTrue(MapUtils.parseKeyAndValueToMap("\"QQGenius\" : \"微博精灵\", \"renquan\" : \"任泉\", \"renzhiqiang\" : \"任志强\"") != null);
        assertTrue(MapUtils.parseKeyAndValueToMap("\"QQGenius\":\"微博精灵\",\"renquan\":\"任泉\",\"renzhiqiang\":\"任志强\"") != null);
        assertTrue(MapUtils.parseKeyAndValueToMap("\"QQGenius\":\"微博精灵\",\"renquan\":\"任泉\",\"renzhiqiang\":\"任志强\"",
                                                  ":", ",", true) != null);
    }
}
