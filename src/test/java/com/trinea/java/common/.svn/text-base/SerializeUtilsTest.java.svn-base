/*
 * Copyright 2012 Trinea.com All right reserved. This software is the
 * confidential and proprietary information of Trinea.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Trinea.com.
 */
package com.trinea.java.common;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
 * 类SerializeUtilsTest.java的实现描述：TODO 类实现描述
 * 
 * @author Trinea 2012-5-15 下午12:46:14
 */
public class SerializeUtilsTest extends TestCase {

    private static final String BASE_DIR = "C:\\Users\\Trinea\\Desktop\\temp\\JavaCommonTest\\SerializeUtilsTest\\";

    protected void setUp() throws Exception {
        assertTrue(FileUtils.makeFolder(BASE_DIR));
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @SuppressWarnings("unchecked")
    public void testSerializationAndDeserialization() {
        // 未实现serialize接口的类
        ClassNotImplSerialize classNotImplSerialize = new ClassNotImplSerialize(100, "user1");
        String user1File = BASE_DIR + "user1.obj";
        assertTrue(FileUtils.deleteFile((user1File)));
        try {
            SerializeUtils.serialization("", classNotImplSerialize);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
        try {
            SerializeUtils.serialization(user1File, classNotImplSerialize);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
        try {
            SerializeUtils.deserialization("");
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
        try {
            SerializeUtils.deserialization(user1File);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }

        // 实现serialize接口的类
        ClassImplSerialize classImplSerialize = new ClassImplSerialize(100, "user1");
        String user2File = BASE_DIR + "user2.obj";
        assertTrue(FileUtils.deleteFile((user2File)));
        try {
            SerializeUtils.serialization(user2File, classImplSerialize);
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
        try {
            ClassImplSerialize classImplSerialize2 = (ClassImplSerialize)SerializeUtils.deserialization(user2File);
            assertNotNull(classImplSerialize2);
            assertTrue(classImplSerialize.getUserName().equals(classImplSerialize2.getUserName()));
            assertTrue(classImplSerialize.getUserId() == classImplSerialize2.getUserId());
        } catch (Exception e) {
            assertTrue(false);
        }

        // 容器序列化
        Map<String, ClassImplSerialize> inMap = new LinkedHashMap<String, ClassImplSerialize>();
        String mapFile = BASE_DIR + "map.obj";
        int entityCount = 1000;
        for (int i = 0; i < entityCount; i++) {
            ClassImplSerialize o = new ClassImplSerialize(i, "user" + i);
            inMap.put(Integer.toString(i), o);
        }
        SerializeUtils.serialization(mapFile, inMap);
        Map<String, ClassImplSerialize> outMap = (Map<String, ClassImplSerialize>)SerializeUtils.deserialization(mapFile);
        assertEquals(inMap.size(), outMap.size());
        for (int i = 0; i < inMap.size(); i++) {
            ClassImplSerialize o1 = inMap.get(Integer.toString(i));
            ClassImplSerialize o2 = outMap.get(Integer.toString(i));
            assertTrue(o1.getUserName().equals(o2.getUserName()));
            assertTrue(o1.getUserId() == o2.getUserId());
        }
    }

    public class ClassNotImplSerialize {

        @SuppressWarnings("unused")
        private long   userId;
        @SuppressWarnings("unused")
        private String userName;

        public ClassNotImplSerialize(long userId, String userName){
            this.userId = userId;
            this.userName = userName;
        }
    }

    public static class ClassImplSerialize implements Serializable {

        private static final long serialVersionUID = 88940079192401092L;
        private long              userId;
        private String            userName;

        public ClassImplSerialize(){
            this.userId = 1;
            this.userName = "defaultUser";
        }

        public ClassImplSerialize(long userId, String userName){
            this.userId = userId;
            this.userName = userName;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
