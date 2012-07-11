package com.trinea.java.common;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class ArrayUtilsTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
        ArrayUtils arrayUtils = new ArrayUtils();
        assertTrue(arrayUtils != null);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testIsEmpty() {
        assertTrue(ArrayUtils.isEmpty((Object[])null));
        assertTrue(ArrayUtils.isEmpty((String[])null));

        String[] strArray = new String[] {};
        assertTrue(ArrayUtils.isEmpty(strArray));
        strArray = new String[] {"aa"};
        assertFalse(ArrayUtils.isEmpty(strArray));

        List<Integer> strList = new ArrayList<Integer>();
        assertTrue(ArrayUtils.isEmpty(strList.toArray()));
    }

    public void testGetLastVArrayVVBoolean() {

        assertTrue(ArrayUtils.getLast((new ArrayList<String>()).toArray(), "b", null, false) == null);
        assertTrue("default".equals(ArrayUtils.getLast((new ArrayList<String>()).toArray(), "b", "default", false)));
        assertTrue(ArrayUtils.getLast((new ArrayList<String>()).toArray(), "b", null, true) == null);
        assertTrue("default".equals(ArrayUtils.getLast((new ArrayList<String>()).toArray(), "b", "default", true)));

        String[] sourceArray = new String[] {"a", "b", "c", "d", "e"};
        assertTrue(ArrayUtils.getLast(sourceArray, "f", null, false) == null);
        assertTrue("default".equals(ArrayUtils.getLast(sourceArray, "f", "default", false)));

        assertTrue(ArrayUtils.getLast(sourceArray, "c", null, false).equals("b"));
        assertTrue(ArrayUtils.getLast(sourceArray, "c", "default", false).equals("b"));
        assertTrue(ArrayUtils.getLast(sourceArray, "c", null, true).equals("b"));
        assertTrue(ArrayUtils.getLast(sourceArray, "c", "default", true).equals("b"));

        assertTrue(ArrayUtils.getLast(sourceArray, "a", null, true).equals("e"));
        assertTrue(ArrayUtils.getLast(sourceArray, "a", "default", true).equals("e"));
        assertTrue("default".equals(ArrayUtils.getLast(sourceArray, "a", "default", false)));
        assertTrue(ArrayUtils.getLast(sourceArray, "a", null, false) == null);
    }

    public void testGetNextVArrayVVBoolean() {

        assertTrue(ArrayUtils.getNext((new ArrayList<String>()).toArray(), "b", null, false) == null);
        assertTrue("default".equals(ArrayUtils.getNext((new ArrayList<String>()).toArray(), "b", "default", false)));
        assertTrue(ArrayUtils.getNext((new ArrayList<String>()).toArray(), "b", null, true) == null);
        assertTrue("default".equals(ArrayUtils.getNext((new ArrayList<String>()).toArray(), "b", "default", true)));

        String[] sourceArray = new String[] {"a", "b", "c", "d", "e"};
        assertTrue(ArrayUtils.getNext(sourceArray, "f", null, false) == null);
        assertTrue("default".equals(ArrayUtils.getNext(sourceArray, "f", "default", false)));

        assertTrue(ArrayUtils.getNext(sourceArray, "c", null, false).equals("d"));
        assertTrue(ArrayUtils.getNext(sourceArray, "c", "default", false).equals("d"));
        assertTrue(ArrayUtils.getNext(sourceArray, "c", null, true).equals("d"));
        assertTrue(ArrayUtils.getNext(sourceArray, "c", "default", true).equals("d"));

        assertTrue(ArrayUtils.getNext(sourceArray, "e", null, true).equals("a"));
        assertTrue(ArrayUtils.getNext(sourceArray, "e", "default", true).equals("a"));
        assertTrue("default".equals(ArrayUtils.getNext(sourceArray, "e", "default", false)));
        assertTrue(ArrayUtils.getNext(sourceArray, "e", null, false) == null);
    }

    public void testGetLastVArrayVBoolean() {

        assertTrue(ArrayUtils.getLast((new ArrayList<String>()).toArray(), "b", false) == null);
        assertTrue(ArrayUtils.getLast((new ArrayList<String>()).toArray(), "b", true) == null);

        String[] sourceArray = new String[] {"a", "b", "c", "d", "e"};
        assertTrue(ArrayUtils.getLast(sourceArray, "f", false) == null);

        assertTrue(ArrayUtils.getLast(sourceArray, "c", false).equals("b"));
        assertTrue(ArrayUtils.getLast(sourceArray, "c", true).equals("b"));

        assertTrue(ArrayUtils.getLast(sourceArray, "a", true).equals("e"));
        assertTrue(ArrayUtils.getLast(sourceArray, "a", false) == null);
    }

    public void testGetNextVArrayVBoolean() {

        assertTrue(ArrayUtils.getNext((new ArrayList<String>()).toArray(), "b", false) == null);
        assertTrue(ArrayUtils.getNext((new ArrayList<String>()).toArray(), "b", true) == null);

        String[] sourceArray = new String[] {"a", "b", "c", "d", "e"};
        assertTrue(ArrayUtils.getNext(sourceArray, "f", false) == null);

        assertTrue(ArrayUtils.getNext(sourceArray, "c", false).equals("d"));
        assertTrue(ArrayUtils.getNext(sourceArray, "c", true).equals("d"));

        assertTrue(ArrayUtils.getNext(sourceArray, "e", true).equals("a"));
        assertTrue(ArrayUtils.getNext(sourceArray, "e", false) == null);
    }

    public void testGetLastLongArrayLongLongBoolean() {
        try {
            ArrayUtils.getLast(new long[0], 0, -1, false);
        } catch (Exception e) {
            assertTrue(true);
        }

        long[] sourceArray = new long[] {1, 2, 3, 4, 5};
        assertEquals(ArrayUtils.getLast(sourceArray, 6, -1, false), -1);

        assertEquals(ArrayUtils.getLast(sourceArray, 3, -1, false), 2);
        assertEquals(ArrayUtils.getLast(sourceArray, 3, -1, true), 2);

        assertEquals(ArrayUtils.getLast(sourceArray, 1, -1, true), 5);
        assertEquals(ArrayUtils.getLast(sourceArray, 1, -1, false), -1);
    }

    public void testGetNextLongArrayLongLongBoolean() {
        try {
            ArrayUtils.getNext(new long[0], 0, -1, false);
        } catch (Exception e) {
            assertTrue(true);
        }

        long[] sourceArray = new long[] {1, 2, 3, 4, 5};
        assertEquals(ArrayUtils.getNext(sourceArray, 6, -1, false), -1);

        assertEquals(ArrayUtils.getNext(sourceArray, 2, -1, false), 3);
        assertEquals(ArrayUtils.getNext(sourceArray, 2, -1, true), 3);

        assertEquals(ArrayUtils.getNext(sourceArray, 5, -1, true), 1);
        assertEquals(ArrayUtils.getNext(sourceArray, 5, -1, false), -1);
    }

    public void testGetLastIntArrayIntIntegerBoolean() {
        try {
            ArrayUtils.getLast(new int[0], 0, -1, false);
        } catch (Exception e) {
            assertTrue(true);
        }

        int[] sourceArray = new int[] {1, 2, 3, 4, 5};
        assertEquals(ArrayUtils.getLast(sourceArray, 6, -1, false), -1);

        assertEquals(ArrayUtils.getLast(sourceArray, 3, -1, false), 2);
        assertEquals(ArrayUtils.getLast(sourceArray, 3, -1, true), 2);

        assertEquals(ArrayUtils.getLast(sourceArray, 1, -1, true), 5);
        assertEquals(ArrayUtils.getLast(sourceArray, 1, -1, false), -1);
    }

    public void testGetNextIntArrayIntIntegerBoolean() {
        try {
            ArrayUtils.getNext(new int[0], 0, -1, false);
        } catch (Exception e) {
            assertTrue(true);
        }

        int[] sourceArray = new int[] {1, 2, 3, 4, 5};
        assertEquals(ArrayUtils.getNext(sourceArray, 6, -1, false), -1);

        assertEquals(ArrayUtils.getNext(sourceArray, 2, -1, false), 3);
        assertEquals(ArrayUtils.getNext(sourceArray, 2, -1, true), 3);

        assertEquals(ArrayUtils.getNext(sourceArray, 5, -1, true), 1);
        assertEquals(ArrayUtils.getNext(sourceArray, 5, -1, false), -1);
    }
}
