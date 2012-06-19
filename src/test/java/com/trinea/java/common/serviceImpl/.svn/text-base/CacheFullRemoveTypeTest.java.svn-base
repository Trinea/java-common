package com.trinea.java.common.serviceImpl;

import junit.framework.TestCase;

import com.trinea.java.common.entity.CacheObject;
import com.trinea.java.common.serviceImpl.RemoveTypeDataBig;
import com.trinea.java.common.serviceImpl.RemoveTypeDataSmall;
import com.trinea.java.common.serviceImpl.RemoveTypeEnterTimeFirst;
import com.trinea.java.common.serviceImpl.RemoveTypeEnterTimeLast;
import com.trinea.java.common.serviceImpl.RemoveTypeLastUsedTimeFirst;
import com.trinea.java.common.serviceImpl.RemoveTypeLastUsedTimeLast;
import com.trinea.java.common.serviceImpl.RemoveTypeNotRemove;
import com.trinea.java.common.serviceImpl.RemoveTypePriorityHigh;
import com.trinea.java.common.serviceImpl.RemoveTypePriorityLow;
import com.trinea.java.common.serviceImpl.RemoveTypeUsedCountBig;
import com.trinea.java.common.serviceImpl.RemoveTypeUsedCountSmall;

public class CacheFullRemoveTypeTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCacheObject() {
        CacheObject<String> object = new CacheObject<String>();
        object.setEnterTime(System.currentTimeMillis());
        object.setExpired(false);
        object.setForever(false);
        object.setUsedCount(object.getUsedCount() + 1);
        object.setPriority(1);
        object.setLastUsedTime(System.currentTimeMillis());
        object.setData("data");
        assertTrue(object.getEnterTime() <= System.currentTimeMillis());
        assertTrue(object.getLastUsedTime() <= System.currentTimeMillis());
        assertFalse(object.isExpired());
        assertFalse(object.isForever());
        assertTrue(object.getUsedCount() >= 0);
        assertTrue(object.getPriority() >= 0);
        assertTrue(object.getData() != null);
    }

    public void testCompareTo() {
        CacheObject<Integer> obj1 = new CacheObject<Integer>();
        CacheObject<Integer> obj2 = new CacheObject<Integer>();
        obj1.setEnterTime(10000);
        obj1.setLastUsedTime(10000);
        obj1.setPriority(1);
        obj1.setUsedCount(1);
        obj1.setData(100);

        obj2.setEnterTime(20000);
        obj2.setLastUsedTime(20000);
        obj2.setPriority(2);
        obj2.setUsedCount(2);
        obj2.setData(200);
        assertTrue(((new RemoveTypeNotRemove<Integer>()).compare(obj1, obj2)) == 0);

        assertTrue(((new RemoveTypeEnterTimeFirst<Integer>()).compare(obj2, obj1)) > 0);
        assertTrue(((new RemoveTypeEnterTimeFirst<Integer>()).compare(obj1, obj1)) == 0);
        assertTrue(((new RemoveTypeEnterTimeFirst<Integer>()).compare(obj1, obj2)) < 0);
        assertTrue(((new RemoveTypeEnterTimeLast<Integer>()).compare(obj1, obj2)) > 0);
        assertTrue(((new RemoveTypeEnterTimeLast<Integer>()).compare(obj2, obj1)) < 0);
        assertTrue(((new RemoveTypeEnterTimeLast<Integer>()).compare(obj1, obj1)) == 0);

        assertTrue(((new RemoveTypeLastUsedTimeFirst<Integer>()).compare(obj1, obj2)) < 0);
        assertTrue(((new RemoveTypeLastUsedTimeFirst<Integer>()).compare(obj1, obj1)) == 0);
        assertTrue(((new RemoveTypeLastUsedTimeFirst<Integer>()).compare(obj2, obj1)) > 0);
        assertTrue(((new RemoveTypeLastUsedTimeLast<Integer>()).compare(obj1, obj2)) > 0);
        assertTrue(((new RemoveTypeLastUsedTimeLast<Integer>()).compare(obj1, obj1)) == 0);
        assertTrue(((new RemoveTypeLastUsedTimeLast<Integer>()).compare(obj2, obj1)) < 0);

        assertTrue(((new RemoveTypePriorityLow<Integer>()).compare(obj1, obj2)) < 0);
        assertTrue(((new RemoveTypePriorityLow<Integer>()).compare(obj1, obj1)) == 0);
        assertTrue(((new RemoveTypePriorityLow<Integer>()).compare(obj2, obj1)) > 0);
        assertTrue(((new RemoveTypePriorityHigh<Integer>()).compare(obj1, obj2)) > 0);
        assertTrue(((new RemoveTypePriorityHigh<Integer>()).compare(obj1, obj1)) == 0);
        assertTrue(((new RemoveTypePriorityHigh<Integer>()).compare(obj2, obj1)) < 0);

        assertTrue(((new RemoveTypeUsedCountSmall<Integer>()).compare(obj1, obj2)) < 0);
        assertTrue(((new RemoveTypeUsedCountSmall<Integer>()).compare(obj1, obj1)) == 0);
        assertTrue(((new RemoveTypeUsedCountSmall<Integer>()).compare(obj2, obj1)) > 0);
        assertTrue(((new RemoveTypeUsedCountBig<Integer>()).compare(obj1, obj2)) > 0);
        assertTrue(((new RemoveTypeUsedCountBig<Integer>()).compare(obj1, obj1)) == 0);
        assertTrue(((new RemoveTypeUsedCountBig<Integer>()).compare(obj2, obj1)) < 0);

        assertTrue(((new RemoveTypeDataSmall<Integer>()).compare(obj1, obj2)) < 0);
        assertTrue(((new RemoveTypeDataSmall<Integer>()).compare(obj1, obj1)) == 0);
        assertTrue(((new RemoveTypeDataSmall<Integer>()).compare(obj2, obj1)) > 0);
        assertTrue(((new RemoveTypeDataBig<Integer>()).compare(obj1, obj2)) > 0);
        assertTrue(((new RemoveTypeDataBig<Integer>()).compare(obj1, obj1)) == 0);
        assertTrue(((new RemoveTypeDataBig<Integer>()).compare(obj2, obj1)) < 0);

        obj1.setPriority(1);
        obj2.setPriority(1);
        assertTrue(((new RemoveTypePriorityLow<Integer>()).compare(obj1, obj2)) == 0);
        assertTrue(((new RemoveTypePriorityHigh<Integer>()).compare(obj1, obj2)) == 0);

        CacheObject<String> obj3 = new CacheObject<String>();
        CacheObject<String> obj4 = new CacheObject<String>();
        obj3.setData(null);
        obj4.setData(null);
        assertTrue(((new RemoveTypeDataSmall<String>()).compare(obj3, obj4)) == 0);
        assertTrue(((new RemoveTypeDataBig<String>()).compare(obj3, obj4)) == 0);
        obj3.setData("aa");
        obj4.setData("ab");
        assertTrue(((new RemoveTypeDataSmall<String>()).compare(obj3, obj4)) < 0);
        assertTrue(((new RemoveTypeDataBig<String>()).compare(obj3, obj4)) > 0);
        obj4.setData("aa");
        assertTrue(((new RemoveTypeDataSmall<String>()).compare(obj3, obj4)) == 0);
        assertTrue(((new RemoveTypeDataBig<String>()).compare(obj3, obj4)) == 0);

        CacheObject<TestClass> obj5 = new CacheObject<TestClass>();
        CacheObject<TestClass> obj6 = new CacheObject<TestClass>();
        TestClass class5 = new TestClass();
        class5.setName("aa");
        obj5.setData(null);
        obj6.setData(null);
        assertTrue(((new RemoveTypeDataSmall<TestClass>()).compare(obj5, obj6)) == 0);
        assertTrue(((new RemoveTypeDataBig<TestClass>()).compare(obj5, obj6)) == 0);
        obj5.setData(class5);
        assertTrue(((new RemoveTypeDataSmall<TestClass>()).compare(obj5, obj6)) > 0);
        assertTrue(((new RemoveTypeDataBig<TestClass>()).compare(obj5, obj6)) < 0);

        TestClass class6 = new TestClass();
        class6.setName("bb");
        obj6.setData(class6);
        assertTrue(((new RemoveTypeDataSmall<TestClass>()).compare(obj5, obj6)) < 0);
        assertTrue(((new RemoveTypeDataBig<TestClass>()).compare(obj5, obj6)) > 0);
        class5.setName("ca");
        assertTrue(((new RemoveTypeDataSmall<TestClass>()).compare(obj5, obj6)) > 0);
        assertTrue(((new RemoveTypeDataBig<TestClass>()).compare(obj5, obj6)) < 0);
    }

    @SuppressWarnings("rawtypes")
    public class TestClass implements Comparable {

        private String name;

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(Object o) {
            if (o == null) {
                return 1;
            }
            return this.name.compareTo(((TestClass)o).name);
        }

    }

}
