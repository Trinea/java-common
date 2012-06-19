package com.trinea.java.common;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class ListUtilsTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
        ListUtils listUtils = new ListUtils();
        assertTrue(listUtils != null);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testIsEmpty() {
        assertTrue(ListUtils.isEmpty(null));
        List<String> sourceList = new ArrayList<String>();
        assertTrue(ListUtils.isEmpty(sourceList));
        sourceList.add("A");
        assertFalse(ListUtils.isEmpty(sourceList));
    }

    public void testJoinListOfString() {
        assertEquals(ListUtils.join(null), "");

        List<String> sourceList = new ArrayList<String>();
        assertEquals(ListUtils.join(sourceList), "");

        sourceList.add("a");
        sourceList.add("b");
        assertEquals(ListUtils.join(sourceList), "a,b");
    }

    public void testJoinListOfStringChar() {

        assertEquals(ListUtils.join(null, '#'), "");

        List<String> sourceList = new ArrayList<String>();
        assertEquals(ListUtils.join(sourceList, '#'), "");

        sourceList.add("a");
        sourceList.add("b");
        assertEquals(ListUtils.join(sourceList, '#'), "a#b");
        sourceList.add("c");
        assertEquals(ListUtils.join(sourceList, '#'), "a#b#c");
        assertEquals(ListUtils.join(sourceList, ' '), "a b c");
    }

    public void testJoinListOfStringString() {

        assertEquals(ListUtils.join(null, null), "");
        assertEquals(ListUtils.join(null, "#"), "");

        List<String> sourceList = new ArrayList<String>();
        assertEquals(ListUtils.join(sourceList, "#"), "");
        assertEquals(ListUtils.join(sourceList, null), "");

        sourceList.add("a");
        sourceList.add("b");
        assertEquals(ListUtils.join(sourceList, "#"), "a#b");
        sourceList.add("c");
        assertEquals(ListUtils.join(sourceList, null), "a,b,c");
        assertEquals(ListUtils.join(sourceList, "#"), "a#b#c");
        assertEquals(ListUtils.join(sourceList, ""), "abc");
        assertEquals(ListUtils.join(sourceList, "#s"), "a#sb#sc");
    }

    public void testAddDistinctEntry() {
        assertFalse(ListUtils.addDistinctEntry(null, null));

        List<String> sourceList = new ArrayList<String>();
        assertTrue(ListUtils.addDistinctEntry(sourceList, "a"));
        assertTrue(ListUtils.addDistinctEntry(sourceList, "b"));
        assertTrue(sourceList.size() == 2);
        assertFalse(ListUtils.addDistinctEntry(sourceList, "a"));
        assertTrue(sourceList.size() == 2);
    }

    public void testAddDistinctList() {
        assertEquals(ListUtils.addDistinctList(null, null), 0);

        List<String> sourceList = new ArrayList<String>();
        List<String> entryList = new ArrayList<String>();
        assertEquals(ListUtils.addDistinctList(sourceList, null), 0);
        assertEquals(ListUtils.addDistinctList(sourceList, entryList), 0);

        sourceList.add("a");
        sourceList.add("b");
        entryList.add("C");
        entryList.add("b");
        assertEquals(ListUtils.addDistinctList(sourceList, entryList), 1);
        assertEquals(ListUtils.addDistinctList(sourceList, entryList), 0);
    }

    public void testDistinctList() {
        assertEquals(ListUtils.distinctList(null), 0);

        List<String> sourceList = new ArrayList<String>();
        assertEquals(ListUtils.distinctList(sourceList), 0);

        sourceList.add("a");
        sourceList.add("b");
        assertEquals(ListUtils.distinctList(sourceList), 0);
        sourceList.add("a");
        sourceList.add("b");
        assertEquals(ListUtils.distinctList(sourceList), 2);
        sourceList.add("a");
        sourceList.add("b");
        assertEquals(ListUtils.distinctList(sourceList), 2);
        sourceList.add("C");
        sourceList.add("b");
        assertEquals(ListUtils.distinctList(sourceList), 1);
        sourceList.add("D");
        sourceList.add("E");
        sourceList.add("F");
        assertEquals(ListUtils.distinctList(sourceList), 0);
    }

    public void testAddListNotNullValue() {
        assertFalse(ListUtils.addListNotNullValue(null, null));

        List<String> sourceList = new ArrayList<String>();
        assertFalse(ListUtils.addListNotNullValue(sourceList, null));
        assertTrue(ListUtils.addListNotNullValue(sourceList, "aa"));
    }

    public void testGetLast() {
        assertNull(ListUtils.getLast(null, "b"));

        List<String> sourceList = new ArrayList<String>();
        sourceList.add("a");
        sourceList.add("b");
        sourceList.add("c");
        sourceList.add("d");
        sourceList.add("e");

        assertEquals(ListUtils.getLast(sourceList, "b"), "a");
        assertEquals(ListUtils.getLast(sourceList, "a"), "e");
    }

    public void testGetNext() {
        assertNull(ListUtils.getNext(null, "b"));

        List<String> sourceList = new ArrayList<String>();
        sourceList.add("a");
        sourceList.add("b");
        sourceList.add("c");
        sourceList.add("d");
        sourceList.add("e");

        assertEquals(ListUtils.getNext(sourceList, "b"), "c");
        assertEquals(ListUtils.getNext(sourceList, "e"), "a");
    }

    public void testInvertList() {
        List<String> sourceList = new ArrayList<String>();
        List<String> invertList = ListUtils.invertList(sourceList);
        assertTrue(invertList.size() == 0);
        sourceList.add("a");
        sourceList.add("b");
        sourceList.add("c");
        sourceList.add("d");
        sourceList.add("e");
        invertList = ListUtils.invertList(sourceList);
        int sourceSize = sourceList.size();
        assertEquals(sourceSize, invertList.size());
        for (int i = 0; i < sourceSize; i++) {
            assertEquals(sourceList.get(i), invertList.get(sourceSize - 1 - i));
        }
    }
}
