package com.trinea.java.common;

/**
 * 数组工具类
 * <ul>
 * <li>继承自{@link org.apache.commons.lang3.ArrayUtils}</li>
 * <li>{@link ArrayUtils#getLast(Object[], Object, Object, boolean)}得到array中某个元素（从前到后第一次匹配）的前一个元素</li>
 * <li>{@link ArrayUtils#getNext(Object[], Object, Object, boolean)}得到array中某个元素（从前到后第一次匹配）的后一个元素</li>
 * </ul>
 * 
 * @author Trinea 2011-10-24 下午08:21:11
 */
public class ArrayUtils extends org.apache.commons.lang3.ArrayUtils {

    /**
     * 得到array中某个元素（从前到后第一次匹配）的前一个元素
     * <ul>
     * <li>若数组为空，返回defaultValue</li>
     * <li>若数组中未找到value，返回defaultValue</li>
     * <li>若找到了value并且不为第一个元素，返回该元素的前一个元素</li>
     * <li>若找到了value并且为第一个元素，isCircle为true时，返回数组最后一个元素；isCircle为false时，返回defaultValue</li>
     * </ul>
     * 
     * @param <V>
     * @param sourceArray 源array
     * @param value 待查找值，若value为null同样适用，会查找第一个为null的值
     * @param defaultValue 默认返回值
     * @param isCircle 是否是圆
     * @return
     */
    public static <V> V getLast(V[] sourceArray, V value, V defaultValue, boolean isCircle) {
        if (isEmpty(sourceArray)) {
            return defaultValue;
        }

        int currentPosition = -1;
        for (int i = 0; i < sourceArray.length; i++) {
            if (ObjectUtils.isEquals(value, sourceArray[i])) {
                currentPosition = i;
                break;
            }
        }
        if (currentPosition == -1) {
            return defaultValue;
        }

        if (currentPosition == 0) {
            return isCircle ? sourceArray[sourceArray.length - 1] : defaultValue;
        }
        return sourceArray[currentPosition - 1];
    }

    /**
     * 得到array中某个元素（从前到后第一次匹配）的后一个元素
     * <ul>
     * <li>若数组为空，返回defaultValue</li>
     * <li>若数组中未找到value，返回defaultValue</li>
     * <li>若找到了value并且不为最后一个元素，返回该元素的下一个元素</li>
     * <li>若找到了value并且为最后一个元素，isCircle为true时，返回数组第一个元素；isCircle为false时，返回defaultValue</li>
     * </ul>
     * 
     * @param <V>
     * @param sourceArray 源array
     * @param value 待查找值，若value为null同样适用，会查找第一个为null的值
     * @param defaultValue 默认返回值
     * @param isCircle 是否是圆
     * @return
     */
    public static <V> V getNext(V[] sourceArray, V value, V defaultValue, boolean isCircle) {
        if (isEmpty(sourceArray)) {
            return defaultValue;
        }

        int currentPosition = -1;
        for (int i = 0; i < sourceArray.length; i++) {
            if (ObjectUtils.isEquals(value, sourceArray[i])) {
                currentPosition = i;
                break;
            }
        }
        if (currentPosition == -1) {
            return defaultValue;
        }

        if (currentPosition == sourceArray.length - 1) {
            return isCircle ? sourceArray[0] : defaultValue;
        }
        return sourceArray[currentPosition + 1];
    }

    /**
     * 参考{@link ArrayUtils#getLast(Object[], Object, Object, boolean)} defaultValue为null
     */
    public static <V> V getLast(V[] sourceArray, V value, boolean isCircle) {
        return getLast(sourceArray, value, null, isCircle);
    }

    /**
     * 参考{@link ArrayUtils#getNext(Object[], Object, Object, boolean)} defaultValue为null
     */
    public static <V> V getNext(V[] sourceArray, V value, boolean isCircle) {
        return getNext(sourceArray, value, null, isCircle);
    }

    /**
     * 参考{@link ArrayUtils#getLast(Object[], Object, Object, boolean)} Object为Long
     */
    public static long getLast(long[] sourceArray, long value, long defaultValue, boolean isCircle) {
        if (sourceArray.length == 0) {
            throw new IllegalArgumentException("The length of source array must be greater than 0.");
        }

        Long[] array = ObjectUtils.transformLongArray(sourceArray);
        return getLast(array, value, defaultValue, isCircle);

    }

    /**
     * 参考{@link ArrayUtils#getNext(Object[], Object, Object, boolean)} Object为Long
     */
    public static long getNext(long[] sourceArray, long value, long defaultValue, boolean isCircle) {
        if (sourceArray.length == 0) {
            throw new IllegalArgumentException("The length of source array must be greater than 0.");
        }

        Long[] array = ObjectUtils.transformLongArray(sourceArray);
        return getNext(array, value, defaultValue, isCircle);
    }

    /**
     * 参考{@link ArrayUtils#getLast(Object[], Object, Object, boolean)} Object为Integer
     */
    public static int getLast(int[] sourceArray, int value, int defaultValue, boolean isCircle) {
        if (sourceArray.length == 0) {
            throw new IllegalArgumentException("The length of source array must be greater than 0.");
        }

        Integer[] array = ObjectUtils.transformIntArray(sourceArray);
        return getLast(array, value, defaultValue, isCircle);

    }

    /**
     * 参考{@link ArrayUtils#getNext(Object[], Object, Object, boolean)} Object为Integer
     */
    public static int getNext(int[] sourceArray, int value, int defaultValue, boolean isCircle) {
        if (sourceArray.length == 0) {
            throw new IllegalArgumentException("The length of source array must be greater than 0.");
        }

        Integer[] array = ObjectUtils.transformIntArray(sourceArray);
        return getNext(array, value, defaultValue, isCircle);
    }
}
