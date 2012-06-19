package com.trinea.java.common;

import java.util.Arrays;

import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtilsTest extends TestCase {

    JSONObject nullJsonObject;
    JSONObject emptyJsonObject;
    JSONObject jsonObject;

    String     nullString;
    String     emptyString;
    String     stringObject;
    String     errotrStringbject;

    protected void setUp() throws Exception {
        super.setUp();
        JSONUtils jSONUtils = new JSONUtils();
        assertNotNull(jSONUtils);
        nullJsonObject = null;
        nullString = null;
        emptyJsonObject = new JSONObject("{}");
        jsonObject = new JSONObject(
                                    "{long:1000,boolTrue:true,boolFalse:false,int:10,String:\"字符串\",StringArray:[\"a\",\"b\"],jsonObject:{target:right},jsonArray:[{name:aaa,openid:111},{name:bbb,openid:222},{name:ccc,openid:333}]}");

        emptyString = "";
        stringObject = "{long:1000,boolTrue:true,boolFalse:false,int:10,String:\"字符串\",StringArray:[\"a\",\"b\"],jsonObject:{target:right},jsonArray:[{name:aaa,openid:111},{name:bbb,openid:222},{name:ccc,openid:333}]}";
        errotrStringbject = "{long:1000,boolTrue:true,boolFalse:false,int:10,String:\"字符串\"";
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetLongJSONObjectStringLong() {
        assertNull(JSONUtils.getLong(nullJsonObject, null, null));
        assertNull(JSONUtils.getLong(jsonObject, null, null));

        assertEquals(JSONUtils.getLong(nullJsonObject, null, Long.valueOf(-1l)), Long.valueOf(-1l));
        assertEquals(JSONUtils.getLong(jsonObject, null, Long.valueOf(-1l)), Long.valueOf(-1l));

        assertEquals(JSONUtils.getLong(emptyJsonObject, "long", Long.valueOf(-1l)), Long.valueOf(-1l));
        assertEquals(JSONUtils.getLong(jsonObject, "long", Long.valueOf(-1l)), Long.valueOf(1000l));
    }

    public void testGetLongStringStringLong() {
        assertNull(JSONUtils.getLong(nullString, null, null));
        assertNull(JSONUtils.getLong(stringObject, null, null));

        assertEquals(JSONUtils.getLong(nullString, null, Long.valueOf(-1l)), Long.valueOf(-1l));
        assertEquals(JSONUtils.getLong(stringObject, null, Long.valueOf(-1l)), Long.valueOf(-1l));

        assertEquals(JSONUtils.getLong(emptyString, "long", Long.valueOf(-1l)), Long.valueOf(-1l));
        assertEquals(JSONUtils.getLong(stringObject, "long", Long.valueOf(-1l)), Long.valueOf(1000l));
        assertEquals(JSONUtils.getLong(errotrStringbject, "long", Long.valueOf(-1l)), Long.valueOf(-1l));

    }

    public void testGetLongJSONObjectStringLong1() {

        assertEquals(JSONUtils.getLong(nullJsonObject, null, -1), -1l);
        assertEquals(JSONUtils.getLong(jsonObject, null, -1l), -1l);

        assertEquals(JSONUtils.getLong(emptyJsonObject, "long", -1l), -1l);
        assertEquals(JSONUtils.getLong(jsonObject, "long", -1l), 1000l);
    }

    public void testGetLongStringStringLong1() {

        assertEquals(JSONUtils.getLong(nullString, null, -1l), -1l);
        assertEquals(JSONUtils.getLong(stringObject, null, -1l), -1l);

        assertEquals(JSONUtils.getLong(emptyString, "long", -1l), -1l);
        assertEquals(JSONUtils.getLong(stringObject, "long", -1l), 1000l);
    }

    public void testGetIntJSONObjectStringInteger() {
        assertNull(JSONUtils.getInt(nullJsonObject, null, null));
        assertNull(JSONUtils.getInt(jsonObject, null, null));

        assertEquals(JSONUtils.getInt(nullJsonObject, null, Integer.valueOf(-1)), Integer.valueOf(-1));
        assertEquals(JSONUtils.getInt(jsonObject, null, Integer.valueOf(-1)), Integer.valueOf(-1));

        assertEquals(JSONUtils.getInt(emptyJsonObject, "int", Integer.valueOf(-1)), Integer.valueOf(-1));
        assertEquals(JSONUtils.getInt(jsonObject, "int", Integer.valueOf(-1)), Integer.valueOf(10));
    }

    public void testGetIntStringStringInteger() {
        assertNull(JSONUtils.getInt(nullString, null, null));
        assertNull(JSONUtils.getInt(stringObject, null, null));

        assertEquals(JSONUtils.getInt(nullString, null, Integer.valueOf(-1)), Integer.valueOf(-1));
        assertEquals(JSONUtils.getInt(stringObject, null, Integer.valueOf(-1)), Integer.valueOf(-1));

        assertEquals(JSONUtils.getInt(emptyString, "int", Integer.valueOf(-1)), Integer.valueOf(-1));
        assertEquals(JSONUtils.getInt(stringObject, "int", Integer.valueOf(-1)), Integer.valueOf(10));
        assertEquals(JSONUtils.getInt(errotrStringbject, "int", Integer.valueOf(-1)), Integer.valueOf(-1));
    }

    public void testGetIntJSONObjectStringInt() {
        assertEquals(JSONUtils.getInt(nullJsonObject, null, -1), -1);
        assertEquals(JSONUtils.getInt(jsonObject, null, -1), -1);

        assertEquals(JSONUtils.getInt(emptyJsonObject, "int", -1), -1);
        assertEquals(JSONUtils.getInt(jsonObject, "int", -1), 10);
    }

    public void testGetIntStringStringInt() {
        assertEquals(JSONUtils.getInt(nullString, null, -1), -1);
        assertEquals(JSONUtils.getInt(stringObject, null, -1), -1);

        assertEquals(JSONUtils.getInt(emptyString, "int", -1), -1);
        assertEquals(JSONUtils.getInt(stringObject, "int", -1), 10);
    }

    public void testGetStringJSONObjectStringString() {

        assertNull(JSONUtils.getString(nullJsonObject, null, null));
        assertNull(JSONUtils.getString(jsonObject, null, null));

        assertEquals(JSONUtils.getString(nullJsonObject, null, "default"), "default");
        assertEquals(JSONUtils.getString(jsonObject, null, "default"), "default");

        assertEquals(JSONUtils.getString(emptyJsonObject, "String", "default"), "default");
        assertEquals(JSONUtils.getString(jsonObject, "String", "default"), "字符串");
    }

    public void testGetStringStringStringString() {

        assertNull(JSONUtils.getString(nullString, null, null));
        assertNull(JSONUtils.getString(stringObject, null, null));

        assertEquals(JSONUtils.getString(nullString, null, "default"), "default");
        assertEquals(JSONUtils.getString(stringObject, null, "default"), "default");

        assertEquals(JSONUtils.getString(emptyString, "String", "default"), "default");
        assertEquals(JSONUtils.getString(stringObject, "String", "default"), "字符串");
        assertEquals(JSONUtils.getString(errotrStringbject, "String", "default"), "default");
    }

    public void testGetStringArrayJSONObjectStringStringArray() {
        assertNull(JSONUtils.getStringArray(nullJsonObject, null, null));
        assertNull(JSONUtils.getStringArray(jsonObject, null, null));

        String[] defaultArray = {"-1"};
        String[] targetArray = {"a", "b"};
        assertTrue(JSONUtils.getStringArray(nullJsonObject, null, defaultArray).equals(defaultArray));
        assertTrue(JSONUtils.getStringArray(jsonObject, null, defaultArray).equals(defaultArray));

        assertTrue(JSONUtils.getStringArray(jsonObject, "StringArrayNo", defaultArray).equals(defaultArray));
        assertTrue(JSONUtils.getStringArray(emptyJsonObject, "StringArray", defaultArray).equals(defaultArray));
        assertEquals(ListUtils.join(Arrays.asList(JSONUtils.getStringArray(jsonObject, "StringArray", defaultArray))),
                     ListUtils.join(Arrays.asList(targetArray)));
    }

    public void testGetStringArrayStringStringStringArray() {
        assertNull(JSONUtils.getStringArray(nullString, null, null));
        assertNull(JSONUtils.getStringArray(stringObject, null, null));

        String[] defaultArray = {"-1"};
        String[] targetArray = {"a", "b"};
        assertTrue(JSONUtils.getStringArray(nullString, null, defaultArray).equals(defaultArray));
        assertTrue(JSONUtils.getStringArray(stringObject, null, defaultArray).equals(defaultArray));

        assertTrue(JSONUtils.getStringArray("[]", "StringArray", defaultArray).equals(defaultArray));
        assertTrue(JSONUtils.getStringArray(emptyString, "StringArray", defaultArray).equals(defaultArray));
        assertEquals(ListUtils.join(Arrays.asList(JSONUtils.getStringArray(stringObject, "StringArray", defaultArray))),
                     ListUtils.join(Arrays.asList(targetArray)));
        assertTrue(JSONUtils.getStringArray(errotrStringbject, "StringArray", defaultArray).equals(defaultArray));
    }

    public void testGetJSONObjectJSONObjectStringJSONObject() {
        assertNull(JSONUtils.getJSONObject(nullJsonObject, null, null));
        assertNull(JSONUtils.getJSONObject(jsonObject, null, null));

        JSONObject defaultJson;
        JSONObject targetJson;
        try {
            defaultJson = new JSONObject("{test:test}");
            targetJson = new JSONObject("{target:right}");
            assertEquals(JSONUtils.getJSONObject(nullJsonObject, null, defaultJson), defaultJson);
            assertEquals(JSONUtils.getJSONObject(jsonObject, null, defaultJson), defaultJson);

            assertEquals(JSONUtils.getJSONObject(emptyJsonObject, "jsonObject", defaultJson), defaultJson);
            assertEquals(JSONUtils.getJSONObject(jsonObject, "jsonObject", defaultJson).toString(),
                         targetJson.toString());
        } catch (JSONException e) {
            assertTrue(false);
        }
    }

    public void testGetJSONObjectStringStringJSONObject() {
        assertNull(JSONUtils.getJSONObject(nullString, null, null));
        assertNull(JSONUtils.getJSONObject(stringObject, null, null));

        JSONObject defaultJson;
        JSONObject targetJson;
        try {
            defaultJson = new JSONObject("{test:test}");
            targetJson = new JSONObject("{target:right}");
            assertEquals(JSONUtils.getJSONObject(nullString, null, defaultJson), defaultJson);
            assertEquals(JSONUtils.getJSONObject(stringObject, null, defaultJson), defaultJson);

            assertEquals(JSONUtils.getJSONObject(emptyString, "jsonObject", defaultJson), defaultJson);
            assertEquals(JSONUtils.getJSONObject(stringObject, "jsonObject", defaultJson).toString(),
                         targetJson.toString());
            assertEquals(JSONUtils.getJSONObject(errotrStringbject, "jsonObject", defaultJson), defaultJson);
        } catch (JSONException e) {
            assertTrue(false);
        }
    }

    public void testGetJSONArrayJSONObjectStringJSONArray() {
        assertNull(JSONUtils.getStringArray(nullJsonObject, null, null));
        assertNull(JSONUtils.getStringArray(jsonObject, null, null));

        try {
            JSONArray defaultArray = new JSONArray("[-1]");
            JSONArray targetArray = new JSONArray("[\"a\", \"b\"]");
            assertEquals(JSONUtils.getJSONArray(nullJsonObject, null, defaultArray), defaultArray);
            assertEquals(JSONUtils.getJSONArray(jsonObject, null, defaultArray), defaultArray);

            assertEquals(JSONUtils.getJSONArray(emptyJsonObject, "jsonArray", defaultArray), defaultArray);
            assertEquals(JSONUtils.getJSONArray(jsonObject, "StringArray", defaultArray).join(","),
                         targetArray.join(","));
        } catch (JSONException e) {
            assertTrue(false);
        }
    }

    public void testGetJSONArrayStringStringJSONArray() {
        assertNull(JSONUtils.getStringArray(nullString, null, null));
        assertNull(JSONUtils.getStringArray(stringObject, null, null));

        try {
            JSONArray defaultArray = new JSONArray("[-1]");
            JSONArray targetArray = new JSONArray("[\"a\", \"b\"]");
            assertEquals(JSONUtils.getJSONArray(nullString, null, defaultArray), defaultArray);
            assertEquals(JSONUtils.getJSONArray(stringObject, null, defaultArray), defaultArray);

            assertEquals(JSONUtils.getJSONArray(emptyString, "jsonArray", defaultArray), defaultArray);
            assertEquals(JSONUtils.getJSONArray(stringObject, "StringArray", defaultArray).join(","),
                         targetArray.join(","));
            assertEquals(JSONUtils.getJSONArray(errotrStringbject, "jsonArray", defaultArray), defaultArray);
        } catch (JSONException e) {
            assertTrue(false);
        }
    }

    public void testGetBooleanJSONObjectStringBoolean() {

        assertEquals(JSONUtils.getBoolean(nullJsonObject, null, false), false);
        assertEquals(JSONUtils.getBoolean(jsonObject, null, false), false);

        assertEquals(JSONUtils.getBoolean(emptyJsonObject, "boolTrue", false), false);
        assertEquals(JSONUtils.getBoolean(jsonObject, "boolTrue", false), true);

        assertEquals(JSONUtils.getBoolean(emptyJsonObject, "boolFalse", true), true);
        assertEquals(JSONUtils.getBoolean(jsonObject, "boolFalse", true), false);
    }

    public void testGetBooleanStringStringBoolean() {

        assertEquals(JSONUtils.getBoolean(nullString, null, false), false);
        assertEquals(JSONUtils.getBoolean(stringObject, null, false), false);

        assertEquals(JSONUtils.getBoolean(emptyString, "boolTrue", false), false);
        assertEquals(JSONUtils.getBoolean(stringObject, "boolTrue", false), true);

        assertEquals(JSONUtils.getBoolean(emptyString, "boolFalse", true), true);
        assertEquals(JSONUtils.getBoolean(stringObject, "boolFalse", true), false);
        assertEquals(JSONUtils.getBoolean(errotrStringbject, "boolFalse", true), true);
    }

    public void testParseKeyAndValueToMapJSONObject() {
        assertNull(JSONUtils.parseKeyAndValueToMap((JSONObject)null));
        assertNotNull(JSONUtils.parseKeyAndValueToMap(jsonObject));
        assertEquals(JSONUtils.parseKeyAndValueToMap(jsonObject).get("String"), "字符串");
        assertNotNull(JSONUtils.parseKeyAndValueToMap(emptyJsonObject));
    }

    public void testParseKeyAndValueToMapString() {
        assertNull(JSONUtils.parseKeyAndValueToMap((String)null));
        assertNull(JSONUtils.parseKeyAndValueToMap(""));
        assertNull(JSONUtils.parseKeyAndValueToMap(errotrStringbject));
        assertTrue(JSONUtils.parseKeyAndValueToMap("{\"T1009234687\":\"童期声\",\"jiacuo\":\"加措活佛-慈爱基金\",\"lq1600\":\"李强\",\"t1009234678\":\"快乐转播\"}") != null);
    }

}
