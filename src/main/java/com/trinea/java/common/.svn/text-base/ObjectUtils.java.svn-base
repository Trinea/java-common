package com.trinea.java.common;

/**
 * Object工具类
 * 
 * @author Trinea 2011-10-24 下午08:21:11
 */
public class ObjectUtils {

    /**
     * 比较两个对象是否相等
     * 
     * @param actual
     * @param expected
     * @return
     *         <ul>
     *         <li>若两个对象都为null，则返回true</li>
     *         <li>若{@code actual}对象不为null，则调用{@code actual}对象相应的{@link Object#equals(Object)}函数进行判断，返回判断结果</li>
     *         </ul>
     * @see
     *      <ul>
     *      <li>对于基本类实现了{@link Object#equals(Object)}的话都会先判断类型是否匹配，类型不匹配返回false，可参考{@link String#equals(Object)}</li>
     *      <li>关于如何利用此函数比较自定义对象可下载源代码，参考测试代码中的{@link ObjectUtilsTest#testIsEquals()}</li>
     *      </ul>
     */
    public static boolean isEquals(Object actual, Object expected) {
        return actual == null ? expected == null : actual.equals(expected);
    }

    /**
     * long数组转换成Long数组
     * 
     * @param source
     * @return
     */
    public static Long[] transformLongArray(long[] source) {
        Long[] destin = new Long[source.length];
        for (int i = 0; i < source.length; i++) {
            destin[i] = source[i];
        }
        return destin;
    }

    /**
     * Long数组转换成long数组
     * 
     * @param source
     * @return
     */
    public static long[] transformLongArray(Long[] source) {
        long[] destin = new long[source.length];
        for (int i = 0; i < source.length; i++) {
            destin[i] = source[i];
        }
        return destin;
    }

    /**
     * int数组转换成Integer数组
     * 
     * @param source
     * @return
     */
    public static Integer[] transformIntArray(int[] source) {
        Integer[] destin = new Integer[source.length];
        for (int i = 0; i < source.length; i++) {
            destin[i] = source[i];
        }
        return destin;
    }

    /**
     * Integer数组转换成int数组
     * 
     * @param source
     * @return
     */
    public static int[] transformIntArray(Integer[] source) {
        int[] destin = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            destin[i] = source[i];
        }
        return destin;
    }

    /**
     * 比较两个值的大小<br/>
     * <ul>
     * </ul>
     * <strong>关于比较的结果</strong>
     * <ul>
     * <li>v1大于v2返回1</li>
     * <li>v1等于v2返回0</li>
     * <li>v1小于v2返回-1</li>
     * </ul>
     * <strong>关于比较的规则</strong>
     * <ul>
     * <li>若v1为null，v2为null，则相等</li>
     * <li>若v1为null，v2不为null，则v1小于v2</li>
     * <li>若v1不为null，v2为null，则v1大于v2</li>
     * <li>若v1、v2均不为null，则利用v1的{@link Comparable#compareTo(Object)}判断，参数为v2</li>
     * </ul>
     * 
     * @param v1
     * @param v2
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <V> int compare(V v1, V v2) {
        return v1 == null ? (v2 == null ? 0 : -1) : (v2 == null ? 1 : ((Comparable)v1).compareTo(v2));
    }
}
