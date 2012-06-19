package com.trinea.java.common;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class ObjectUtilsTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
        ObjectUtils objectUtils = new ObjectUtils();
        assertTrue(objectUtils != null);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * <ul>
     * <li>测试基本类型的比较以及list比较</li>
     * <li>自定义对象进行比较</li>
     * </ul>
     */
    public void testIsEquals() {
        assertTrue(ObjectUtils.isEquals(null, null));
        assertFalse(ObjectUtils.isEquals(null, "aa"));
        assertFalse(ObjectUtils.isEquals("aa", null));

        /** int long **/
        assertFalse(ObjectUtils.isEquals(null, 1));
        assertFalse(ObjectUtils.isEquals(1, null));
        assertTrue(ObjectUtils.isEquals(1, 1));
        assertFalse(ObjectUtils.isEquals(1l, 1));

        /** double float, type of 1.2 is double default **/
        assertTrue(ObjectUtils.isEquals(1.2d, 1.2));
        assertFalse(ObjectUtils.isEquals(1.2f, 1.2));
        assertTrue(ObjectUtils.isEquals(1.2f, 1.2f));

        /** boolean **/
        boolean trueBool = true;
        assertTrue(ObjectUtils.isEquals(true, trueBool));
        assertFalse(ObjectUtils.isEquals(false, trueBool));

        /** String **/
        String str1 = "aa", str2 = "aa", str3 = "ab";
        assertTrue(ObjectUtils.isEquals(str1, str2));
        assertFalse(ObjectUtils.isEquals(str1, str3));

        /** List **/
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        list1.add(str1);
        list2.add(str2);
        assertTrue(ObjectUtils.isEquals(list1, list2));
        list2.add(str3);
        assertFalse(ObjectUtils.isEquals(list1, list2));

        /** Class without equals funciton madeby self **/
        class TestClass {

            @SuppressWarnings("unused")
            private int    number;
            @SuppressWarnings("unused")
            private String value;

            TestClass(int number, String value){
                this.number = number;
                this.value = value;
            }
        }
        ;

        TestClass equalTest1 = new TestClass(1, "aa");
        TestClass equalTest2 = new TestClass(1, "aa");
        assertFalse(ObjectUtils.isEquals(equalTest1, equalTest2));

        /** Class with equals funciton madeby self **/
        class TestClassWithEqualFun {

            private int    number;
            private String value;

            TestClassWithEqualFun(int number, String value){
                this.number = number;
                this.value = value;
            }

            public boolean equals(Object obj) {
                if (obj instanceof TestClassWithEqualFun) {
                    TestClassWithEqualFun testClassWithEqualFun2 = (TestClassWithEqualFun)obj;
                    return (this.number == testClassWithEqualFun2.number && this.value.equals(testClassWithEqualFun2.value));
                }
                return false;
            }
        }
        ;

        TestClassWithEqualFun testClassWithEqualFun1 = new TestClassWithEqualFun(1, "aa");
        TestClassWithEqualFun testClassWithEqualFun2 = new TestClassWithEqualFun(1, "aa");
        TestClassWithEqualFun testClassWithEqualFun3 = new TestClassWithEqualFun(1, "bb");
        assertTrue(ObjectUtils.isEquals(testClassWithEqualFun1, testClassWithEqualFun2));
        assertFalse(ObjectUtils.isEquals(testClassWithEqualFun1, testClassWithEqualFun3));
    }

    public void testTransformLongArrayLongArray() {
        assertEquals(ObjectUtils.transformLongArray(new long[0]).length, 0);
        long entry1 = 1l, entry2 = 2l, entry3 = 3l;
        long[] source = new long[] {entry1, entry2, entry3};
        Long[] destin = ObjectUtils.transformLongArray(source);
        assertTrue(destin != null && destin.length == 3);
        assertEquals(destin[0].longValue(), entry1);
        assertEquals(destin[1].longValue(), entry2);
        assertEquals(destin[2].longValue(), entry3);
    }

    public void testTransformLongArrayLongArray1() {
        assertEquals(ObjectUtils.transformLongArray(new Long[0]).length, 0);

        Long entry1 = new Long(1), entry2 = new Long(2), entry3 = new Long(3);
        Long[] source = new Long[] {entry1, entry2, entry3};
        long[] destin = ObjectUtils.transformLongArray(source);
        assertTrue(destin != null && destin.length == 3);
        assertEquals(destin[0], entry1.longValue());
        assertEquals(destin[1], entry2.longValue());
        assertEquals(destin[2], entry3.longValue());
    }

    public void testTransformIntArrayIntArray() {
        int entry1 = 1, entry2 = 2, entry3 = 3;
        int[] source = new int[] {entry1, entry2, entry3};
        Integer[] destin = ObjectUtils.transformIntArray(source);
        assertTrue(destin != null && destin.length == 3);
        assertEquals(destin[0].intValue(), entry1);
        assertEquals(destin[1].intValue(), entry2);
        assertEquals(destin[2].intValue(), entry3);
    }

    public void testTransformIntArrayIntegerArray() {
        Integer entry1 = 1, entry2 = 2, entry3 = 3;
        Integer[] source = new Integer[] {entry1, entry2, entry3};
        int[] destin = ObjectUtils.transformIntArray(source);
        assertTrue(destin != null && destin.length == 3);
        assertEquals(destin[0], entry1.intValue());
        assertEquals(destin[1], entry2.intValue());
        assertEquals(destin[2], entry3.intValue());
    }

    /**
     * <ul>
     * <li>测试基本类型的大小</li>
     * <li>自定义对象进行大小比较</li>
     * </ul>
     */
    public void testCompare() {
        assertEquals(ObjectUtils.compare(null, null), 0);
        assertEquals(ObjectUtils.compare(null, "aa"), -1);
        assertEquals(ObjectUtils.compare("aa", null), 1);

        /** int long **/
        assertEquals(ObjectUtils.compare(1, 1), 0);
        try {
            ObjectUtils.compare(1l, 1);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }

        /** double float, type of 1.2 is double default **/
        assertEquals(ObjectUtils.compare(1.2d, 1.2), 0);
        try {
            ObjectUtils.compare(1.2f, 1.2);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
        assertEquals(ObjectUtils.compare(1.2f, 1.2f), 0);

        /** boolean **/
        boolean trueBool = true;
        assertEquals(ObjectUtils.compare(true, trueBool), 0);
        assertEquals(ObjectUtils.compare(false, trueBool), -1);
        assertEquals(ObjectUtils.compare(trueBool, false), 1);

        /** String **/
        String str1 = "aa", str2 = "aa", str3 = "ab";
        assertEquals(ObjectUtils.compare(str1, str2), 0);
        assertEquals(ObjectUtils.compare(str1, str3), -1);

        /** Class without equals funciton madeby self **/
        class User implements Comparable<User> {

            private String name;
            private int    age;

            public User(String name, int age){
                this.name = name;
                this.age = age;
            }

            @Override
            public int compareTo(User another) {
                int compareName = this.name.compareTo(another.getName());
                if (compareName == 0) {
                    return (this.age == another.getAge() ? 0 : (this.age > another.getAge() ? 1 : -1));
                }
                return compareName;
            }

            public String getName() {
                return name;
            }

            public int getAge() {
                return age;
            }
        }

        User user1 = new User("bb", 1);
        User user2 = new User("bb", 1);
        User user3 = new User("ab", 1);
        User user4 = new User("cb", 1);
        User user5 = new User("bb", 0);
        User user6 = new User("bb", 2);
        assertEquals(ObjectUtils.compare(user1, user2), 0);
        assertEquals(ObjectUtils.compare(user1, user3), 1);
        assertEquals(ObjectUtils.compare(user1, user4), -1);
        assertEquals(ObjectUtils.compare(user1, user5), 1);
        assertEquals(ObjectUtils.compare(user1, user6), -1);
    }
}
