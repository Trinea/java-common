/*
 * Copyright 2012 Trinea.com All right reserved. This software is the
 * confidential and proprietary information of Trinea.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Trinea.com.
 */
package com.trinea.java.common;

import junit.framework.TestCase;

public class RandomsUtilsTest extends TestCase {

    protected void setUp() throws Exception {
        RandomUtils randomUtils = new RandomUtils();
        assertNotNull(randomUtils);
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetRandomNumbersAndLetters() {
        assertTrue(RandomUtils.getRandomNumbersAndLetters(1).length() == 1);
        assertTrue(RandomUtils.getRandomNumbersAndLetters(2).length() == 2);
        assertTrue(RandomUtils.getRandomNumbersAndLetters(5).length() == 5);
        assertTrue(RandomUtils.getRandomNumbersAndLetters(9).length() == 9);
        assertTrue(RandomUtils.getRandomNumbersAndLetters(13).length() == 13);
        assertTrue(RandomUtils.getRandomNumbersAndLetters(25).length() == 25);
        assertTrue(RandomUtils.getRandomNumbersAndLetters(46).length() == 46);
        assertTrue(RandomUtils.getRandomNumbersAndLetters(67).length() == 67);
    }

    public void testGetRandomNumbers() {
        assertTrue(RandomUtils.getRandomNumbers(1).length() == 1);
        assertTrue(RandomUtils.getRandomNumbers(2).length() == 2);
        assertTrue(RandomUtils.getRandomNumbers(5).length() == 5);
        assertTrue(RandomUtils.getRandomNumbers(9).length() == 9);
        assertTrue(RandomUtils.getRandomNumbers(13).length() == 13);
        assertTrue(RandomUtils.getRandomNumbers(25).length() == 25);
        assertTrue(RandomUtils.getRandomNumbers(46).length() == 46);
        assertTrue(RandomUtils.getRandomNumbers(67).length() == 67);
    }

    public void testGetRandomLetters() {
        assertTrue(RandomUtils.getRandomLetters(1).length() == 1);
        assertTrue(RandomUtils.getRandomLetters(2).length() == 2);
        assertTrue(RandomUtils.getRandomLetters(5).length() == 5);
        assertTrue(RandomUtils.getRandomLetters(9).length() == 9);
        assertTrue(RandomUtils.getRandomLetters(13).length() == 13);
        assertTrue(RandomUtils.getRandomLetters(25).length() == 25);
        assertTrue(RandomUtils.getRandomLetters(46).length() == 46);
        assertTrue(RandomUtils.getRandomLetters(67).length() == 67);
    }

    public void testGetRandomCapitalLetters() {
        assertTrue(RandomUtils.getRandomCapitalLetters(1).length() == 1);
        assertTrue(RandomUtils.getRandomCapitalLetters(46).length() == 46);
    }

    public void testGetRandomLowerCaseLetters() {
        assertTrue(RandomUtils.getRandomLowerCaseLetters(1).length() == 1);
        assertTrue(RandomUtils.getRandomLowerCaseLetters(46).length() == 46);
    }

    public void testGetRandomStringInt() {
        String source = null;
        assertNull(RandomUtils.getRandom(source, -1));
        assertNull(RandomUtils.getRandom("", -1));
        assertNull(RandomUtils.getRandom("", 1));
        assertTrue(RandomUtils.getRandom("qqq", 46).length() == 46);
    }

    public void testGetRandomCharArrayInt() {
        char[] source = null;
        assertNull(RandomUtils.getRandom(source, -1));
        assertNull(RandomUtils.getRandom(new char[0], -1));
        assertNull(RandomUtils.getRandom(new char[0], 1));
        assertNull(RandomUtils.getRandom(new char[] {'a'}, -1));
        assertTrue(RandomUtils.getRandom(new char[] {'a'}, 1).length() == 1);
        assertTrue(RandomUtils.getRandom(new char[] {'a', 'b'}, 46).length() == 46);
    }
}
