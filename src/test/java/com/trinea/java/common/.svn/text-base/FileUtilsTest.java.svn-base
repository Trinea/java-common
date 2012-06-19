package com.trinea.java.common;

import junit.framework.TestCase;

public class FileUtilsTest extends TestCase {

    private static final String BASE_DIR = "C:\\Users\\Trinea\\Desktop\\temp\\JavaCommonTest\\FileUtilsTest";

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testReadAndWriteFile() {
        String fileName = BASE_DIR + "\\testReadAndWriteFile.txt";
        assertTrue(ObjectUtils.isEquals(FileUtils.readFile(fileName), null));
        assertTrue(ObjectUtils.isEquals(FileUtils.readFileToList(fileName), null));

        assertTrue(FileUtils.deleteFile(fileName));
        assertTrue(FileUtils.writeFile(fileName, "aa", true));
        assertTrue(ObjectUtils.isEquals(FileUtils.readFile(fileName).toString(), "aa"));

        assertTrue(FileUtils.writeFile(fileName, "bb", false));
        assertTrue(ObjectUtils.isEquals(FileUtils.readFile(fileName).toString(), "bb"));

        assertTrue(FileUtils.writeFile(fileName, "cc", true));
        assertTrue(ObjectUtils.isEquals(FileUtils.readFile(fileName).toString(), "bbcc"));

        assertTrue(FileUtils.deleteFile(fileName));
    }

    public void testReadFileToList() {
        String fileName = BASE_DIR + "\\testReadFileToList.txt";
        String seperator = "+";
        assertTrue(FileUtils.deleteFile(fileName));
        assertTrue(FileUtils.writeFile(fileName, "aa", true));
        assertTrue(ObjectUtils.isEquals(ListUtils.join(FileUtils.readFileToList(fileName), seperator), "aa"));

        assertTrue(FileUtils.writeFile(fileName, "bb", false));
        assertTrue(ObjectUtils.isEquals(ListUtils.join(FileUtils.readFileToList(fileName), seperator), "bb"));

        assertTrue(FileUtils.writeFile(fileName, "\r\n", true));
        assertTrue(FileUtils.writeFile(fileName, "cc", true));
        assertTrue(ObjectUtils.isEquals(FileUtils.readFile(fileName).toString(), "bb\r\ncc"));
        assertTrue(ObjectUtils.isEquals(ListUtils.join(FileUtils.readFileToList(fileName), seperator), "bb" + seperator
                                                                                                       + "cc"));

        assertTrue(FileUtils.deleteFile(fileName));
    }

    public void testGetFileNameWithoutExtension() {
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileNameWithoutExtension(null), null));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileNameWithoutExtension(""), ""));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileNameWithoutExtension("   "), "   "));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileNameWithoutExtension("abc"), "abc"));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileNameWithoutExtension("a.mp3"), "a"));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileNameWithoutExtension("a.b.rmvb"), "a.b"));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileNameWithoutExtension("c:\\"), ""));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileNameWithoutExtension("c:\\a"), "a"));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileNameWithoutExtension("c:\\a.b"), "a"));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileNameWithoutExtension("c:a.txt\\a"), "a"));
    }

    public void testGetFileName() {
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileName(null), null));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileName(""), ""));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileName("   "), "   "));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileName("a.mp3"), "a.mp3"));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileName("a.b.rmvb"), "a.b.rmvb"));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileName("abc"), "abc"));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileName("c:\\"), ""));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileName("c:\\a"), "a"));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileName("c:\\a.b"), "a.b"));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileName("c:a.txt\\a"), "a"));
    }

    public void testGetFolderName() {
        assertTrue(ObjectUtils.isEquals(FileUtils.getFolderName(null), null));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFolderName(""), ""));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFolderName("   "), ""));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFolderName("a.mp3"), ""));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFolderName("a.b.rmvb"), ""));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFolderName("abc"), ""));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFolderName("c:\\"), "c:"));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFolderName("c:\\a"), "c:"));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFolderName("c:\\a.b"), "c:"));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFolderName("c:a.txt\\a"), "c:a.txt"));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFolderName("c:a\\b\\c\\d.txt"), "c:a\\b\\c"));
    }

    public void testGetFileExtension() {
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileExtension(null), null));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileExtension(""), ""));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileExtension("   "), "   "));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileExtension("a.mp3"), "mp3"));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileExtension("a.b.rmvb"), "rmvb"));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileExtension("abc"), ""));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileExtension("c:\\"), ""));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileExtension("c:\\a"), ""));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileExtension("c:\\a.b"), "b"));
        assertTrue(ObjectUtils.isEquals(FileUtils.getFileExtension("c:a.txt\\a"), ""));
    }

    public void testMakeFolder() {
        assertFalse(FileUtils.makeFolder(null));
        assertFalse(FileUtils.makeFolder(""));
        String folderName = BASE_DIR + "\\testMakeFolder\\";
        assertTrue(FileUtils.makeFolder(folderName));
        assertTrue(FileUtils.makeFolder(folderName));
        assertTrue(FileUtils.makeFolder(folderName + "a\\b\\c\\d\\"));
        assertTrue(FileUtils.isFolderExist(folderName + "\\a"));
        assertTrue(FileUtils.isFolderExist(folderName + "\\a\\b"));
        assertTrue(FileUtils.isFolderExist(folderName + "\\a\\b\\c\\d"));
        assertTrue(FileUtils.deleteFile(folderName + "\\a\\b\\c\\d"));
    }

    public void testIsFileExist() {
        assertFalse(FileUtils.isFileExist(null));
        assertFalse(FileUtils.isFileExist(""));
        String fileName = BASE_DIR + "\\testIsFileExist.txt";
        FileUtils.writeFile(fileName, "aa", true);
        assertTrue(FileUtils.isFileExist(fileName));
        assertTrue(FileUtils.deleteFile(fileName));
    }

    public void testIsFolderExist() {
        assertFalse(FileUtils.isFolderExist(null));
        assertFalse(FileUtils.isFolderExist(""));
    }

    public void testDeleteFile() {
        assertTrue(FileUtils.deleteFile(null));
        assertTrue(FileUtils.deleteFile(""));
        String folderName = BASE_DIR + "\\testDeleteFile\\";
        assertTrue(FileUtils.deleteFile(folderName));
        assertTrue(FileUtils.makeFolder(folderName));
        assertTrue(FileUtils.deleteFile(folderName));
        String fileName = BASE_DIR + "\\testDeleteFile.txt";
        FileUtils.writeFile(fileName, "aa", true);
        assertTrue(FileUtils.deleteFile(fileName));
    }

}
