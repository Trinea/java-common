package com.trinea.java.common;

import junit.framework.TestCase;

public class StringUtilsTest extends TestCase {

    protected void setUp() throws Exception {
        StringUtils stringUtils = new StringUtils();
        assertNotNull(stringUtils);
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testIsEmpty() {
        assertTrue(StringUtils.isEmpty(null));
        assertTrue(StringUtils.isEmpty(""));
        assertFalse(StringUtils.isEmpty("  "));
        assertFalse(StringUtils.isEmpty("aa"));
        assertFalse(StringUtils.isEmpty("啊啊"));
    }

    public void testIsBlank() {
        assertTrue(StringUtils.isBlank(null));
        assertTrue(StringUtils.isBlank(""));
        assertTrue(StringUtils.isBlank("  "));
        assertFalse(StringUtils.isBlank("aa"));
        assertFalse(StringUtils.isBlank(" aa"));
        assertFalse(StringUtils.isBlank("aa "));
        assertFalse(StringUtils.isBlank(" aa "));
        assertFalse(StringUtils.isBlank(" a "));
        assertFalse(StringUtils.isBlank("啊啊"));
    }

    public void testCapitalizeFirstLetter() {
        assertEquals(StringUtils.capitalizeFirstLetter(null), null);
        assertEquals(StringUtils.capitalizeFirstLetter(""), "");
        assertEquals(StringUtils.capitalizeFirstLetter("2ab"), "2ab");
        assertEquals(StringUtils.capitalizeFirstLetter("lab"), "Lab");
        assertEquals(StringUtils.capitalizeFirstLetter("a"), "A");
        assertEquals(StringUtils.capitalizeFirstLetter("ab"), "Ab");
        assertEquals(StringUtils.capitalizeFirstLetter("Abc"), "Abc");
    }

    public void testUtf8EncodeString() {
        assertEquals(StringUtils.utf8Encode(null), null);
        assertEquals(StringUtils.utf8Encode(""), "");
        assertEquals(StringUtils.utf8Encode("aa"), "aa");
        assertEquals(StringUtils.utf8Encode("啊啊啊啊"), HttpUtils.utf8Encode("啊啊啊啊"));
    }

    public void testUtf8EncodeStringString() {
        assertEquals(StringUtils.utf8Encode(null, "default"), null);
        assertFalse("default".equals(StringUtils.utf8Encode("", "default")));
        assertFalse("default".equals(StringUtils.utf8Encode("aa", "default")));
        assertFalse("default".equals(StringUtils.utf8Encode("啊啊啊啊", "default")));
    }

    public void testNullStrToEmpty() {
        assertEquals(StringUtils.nullStrToEmpty(null), "");
        assertEquals(StringUtils.nullStrToEmpty(""), "");
        assertEquals(StringUtils.nullStrToEmpty("aa"), "aa");
    }

    public void testGetHrefInnerHtml() {
        assertEquals(StringUtils.getHrefInnerHtml(null), "");
        assertEquals(StringUtils.getHrefInnerHtml(""), "");
        assertEquals(StringUtils.getHrefInnerHtml("mp3"), "mp3");
        assertEquals(StringUtils.getHrefInnerHtml("<a innerHtml</a>"), "<a innerHtml</a>");
        assertEquals(StringUtils.getHrefInnerHtml("<a>innerHtml</a>"), "innerHtml");
        assertEquals(StringUtils.getHrefInnerHtml("< A >innerHtml</a>"), "innerHtml");
        assertEquals(StringUtils.getHrefInnerHtml("<a<a>innerHtml</a>"), "innerHtml");
        assertEquals(StringUtils.getHrefInnerHtml("<a href=\"baidu.com\">innerHtml</a>"), "innerHtml");
        assertEquals(StringUtils.getHrefInnerHtml("<a href=\"baidu.com\" title=\"baidu\">innerHtml</a>"), "innerHtml");
        assertEquals(StringUtils.getHrefInnerHtml("   <a>innerHtml</a>  "), "innerHtml");
        assertEquals(StringUtils.getHrefInnerHtml("<a>innerHtml</a></a>"), "innerHtml");
        assertEquals(StringUtils.getHrefInnerHtml("jack<a>innerHtml</a></a>"), "innerHtml");
        assertEquals(StringUtils.getHrefInnerHtml("<a>innerHtml1</a><a>innerHtml2</a>"), "innerHtml2");
    }

    public void testHtmlEscapeCharsToString() {
        assertEquals(StringUtils.htmlEscapeCharsToString(null), null);
        assertEquals(StringUtils.htmlEscapeCharsToString(""), "");
        assertEquals(StringUtils.htmlEscapeCharsToString("mp3"), "mp3");
        assertEquals(StringUtils.htmlEscapeCharsToString("mp3&lt;"), "mp3<");
        assertEquals(StringUtils.htmlEscapeCharsToString("mp3&gt;"), "mp3>");
        assertEquals(StringUtils.htmlEscapeCharsToString("mp3&amp;mp4"), "mp3&mp4");
        assertEquals(StringUtils.htmlEscapeCharsToString("mp3&quot;mp4"), "mp3\"mp4");
        assertEquals(StringUtils.htmlEscapeCharsToString("mp3&lt;&gt;&amp;&quot;mp4"), "mp3<>&\"mp4");
    }

    public void testIsEquals() {
        assertTrue(StringUtils.isEquals(null, null));
        assertFalse(StringUtils.isEquals(null, "aa"));
        assertFalse(StringUtils.isEquals("aa", null));
        assertTrue(StringUtils.isEquals("aa", "aa"));
        assertFalse(StringUtils.isEquals("aa", "ab"));
    }
}
