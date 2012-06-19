package com.trinea.java.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作工具类
 * <ul>
 * <li>读写文件</li>
 * <li>文件字符串相关操作</li>
 * </ul>
 * 
 * @author Trinea 2012-5-12 下午03:42:05
 */
public class FileUtils {

    public final static String FILE_EXTENSION_SEPARATOR = ".";

    /**
     * 读文件
     * 
     * @param filePath 文件路径
     * @return 若文件路径所在文件不存在返回null，否则返回文件内容
     */
    public static StringBuilder readFile(String filePath) {
        File file = new File(filePath);
        StringBuilder fileContent = new StringBuilder("");
        if (file != null && file.isFile()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    if (!fileContent.toString().equals("")) {
                        fileContent.append("\r\n");
                    }
                    fileContent.append(line);
                }
                reader.close();
                return fileContent;
            } catch (IOException e) {
                throw new RuntimeException("IOException occurred. ", e);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        throw new RuntimeException("IOException occurred. ", e);
                    }
                }
            }
        }
        return null;
    }

    /**
     * 写文件
     * 
     * @param filePath 文件路径
     * @param content 内容
     * @param append 是否追加, true写在文件尾部，false写在文件头部（覆盖源文件内容）
     * @return
     */
    public static boolean writeFile(String filePath, String content, boolean append) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath, append);
            fileWriter.write(content);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * 读文件，每行作为list的一个元素
     * 
     * @param filePath 文件路径
     * @return 若文件路径所在文件不存在返回null，否则返回文件内容
     */
    public static List<String> readFileToList(String filePath) {
        File file = new File(filePath);
        List<String> fileContent = new ArrayList<String>();
        if (file != null && file.isFile()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    fileContent.add(line);
                }
                reader.close();
                return fileContent;
            } catch (IOException e) {
                throw new RuntimeException("IOException occurred. ", e);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        throw new RuntimeException("IOException occurred. ", e);
                    }
                }
            }
        }
        return null;
    }

    /**
     * 从路径中获得文件名（不包含后缀名）
     * 
     * @param filePath 文件路径
     * @return 文件名（不包含后缀名）
     * @see
     * <pre>
     *      getFileNameWithoutExtension(null)               =   null
     *      getFileNameWithoutExtension("")                 =   ""
     *      getFileNameWithoutExtension("   ")              =   "   "
     *      getFileNameWithoutExtension("abc")              =   "abc"
     *      getFileNameWithoutExtension("a.mp3")            =   "a"
     *      getFileNameWithoutExtension("a.b.rmvb")         =   "a.b"
     *      getFileNameWithoutExtension("c:\\")              =   ""
     *      getFileNameWithoutExtension("c:\\a")             =   "a"
     *      getFileNameWithoutExtension("c:\\a.b")           =   "a"
     *      getFileNameWithoutExtension("c:a.txt\\a")        =   "a"
     *      getFileNameWithoutExtension("/home/admin")      =   "admin"
     *      getFileNameWithoutExtension("/home/admin/a.txt/b.mp3")  =   "b"
     * </pre>
     */
    public static String getFileNameWithoutExtension(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }

        int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int filePosi = filePath.lastIndexOf(File.separator);
        if (filePosi == -1) {
            return (extenPosi == -1 ? filePath : filePath.substring(0, extenPosi));
        } else {
            if (extenPosi == -1) {
                return filePath.substring(filePosi + 1);
            } else {
                return (filePosi < extenPosi ? filePath.substring(filePosi + 1, extenPosi) : filePath.substring(filePosi + 1));
            }
        }
    }

    /**
     * 从路径中获得文件名（包含后缀名）
     * 
     * @param filePath 文件路径
     * @return 文件名（包含后缀名）
     * @see
     * <pre>
     *      getFileName(null)               =   null
     *      getFileName("")                 =   ""
     *      getFileName("   ")              =   "   "
     *      getFileName("a.mp3")            =   "a.mp3"
     *      getFileName("a.b.rmvb")         =   "a.b.rmvb"
     *      getFileName("abc")              =   "abc"
     *      getFileName("c:\\")              =   ""
     *      getFileName("c:\\a")             =   "a"
     *      getFileName("c:\\a.b")           =   "a.b"
     *      getFileName("c:a.txt\\a")        =   "a"
     *      getFileName("/home/admin")      =   "admin"
     *      getFileName("/home/admin/a.txt/b.mp3")  =   "b.mp3"
     * </pre>
     */
    public static String getFileName(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        if (filePosi == -1) {
            return filePath;
        }
        return filePath.substring(filePosi + 1);
    }

    /**
     * 从路径中获得文件夹路径
     * 
     * @param filePath 文件名
     * @return 文件夹路径
     * @see
     * <pre>
     *      getFolderName(null)               =   null
     *      getFolderName("")                 =   ""
     *      getFolderName("   ")              =   ""
     *      getFolderName("a.mp3")            =   ""
     *      getFolderName("a.b.rmvb")         =   ""
     *      getFolderName("abc")              =   ""
     *      getFolderName("c:\\")              =   "c:"
     *      getFolderName("c:\\a")             =   "c:"
     *      getFolderName("c:\\a.b")           =   "c:"
     *      getFolderName("c:a.txt\\a")        =   "c:a.txt"
     *      getFolderName("c:a\\b\\c\\d.txt")    =   "c:a\\b\\c"
     *      getFolderName("/home/admin")      =   "/home"
     *      getFolderName("/home/admin/a.txt/b.mp3")  =   "/home/admin/a.txt"
     * </pre>
     */
    public static String getFolderName(String filePath) {

        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        if (filePosi == -1) {
            return "";
        }
        return filePath.substring(0, filePosi);
    }

    /**
     * 从路径中获得文件后缀名
     * 
     * @param filePath 文件名
     * @return 后缀名
     * @see
     * <pre>
     *      getFileExtension(null)               =   ""
     *      getFileExtension("")                 =   ""
     *      getFileExtension("   ")              =   "   "
     *      getFileExtension("a.mp3")            =   "mp3"
     *      getFileExtension("a.b.rmvb")         =   "rmvb"
     *      getFileExtension("abc")              =   ""
     *      getFileExtension("c:\\")              =   ""
     *      getFileExtension("c:\\a")             =   ""
     *      getFileExtension("c:\\a.b")           =   "b"
     *      getFileExtension("c:a.txt\\a")        =   ""
     *      getFileExtension("/home/admin")      =   ""
     *      getFileExtension("/home/admin/a.txt/b")  =   ""
     *      getFileExtension("/home/admin/a.txt/b.mp3")  =   "mp3"
     * </pre>
     */
    public static String getFileExtension(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return filePath;
        }

        int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int filePosi = filePath.lastIndexOf(File.separator);
        if (extenPosi == -1) {
            return "";
        } else {
            if (filePosi >= extenPosi) {
                return "";
            }
            return filePath.substring(extenPosi + 1);
        }
    }

    /**
     * 根据文件路径循环创建文件的文件夹<br/>
     * <br/>
     * <strong>注意：</strong><br/>
     * makeFolder("C:\\Users\\Trinea")仅能创建Users文件夹, makeFolder("C:\\Users\\Trinea\\")才能创建到Trinea文件夹
     * 
     * @param filePath 文件路径
     * @return 是否成功创建文件夹，若文件夹已存在，返回true
     *         <ul>
     *         <li>若{@link FileUtils#getFolderName(String)}返回为空，返回false;</li>
     *         <li>若文件夹存在，返回true</li>
     *         <li>否则返回{@link java.io.File#makeFolder}</li>
     *         </ul>
     */
    public static boolean makeFolder(String filePath) {
        String folderName = getFolderName(filePath);
        if (StringUtils.isEmpty(folderName)) {
            return false;
        }

        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
    }

    /**
     * 判断文件是否存在
     * 
     * @param filePath 文件路径
     * @return 存在返回true，否则返回false
     */
    public static boolean isFileExist(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return false;
        }

        File file = new File(filePath);
        return (file.exists() && file.isFile());
    }

    /**
     * 判断文件夹是否存在
     * 
     * @param directoryPath 文件夹路径
     * @return 存在返回true，否则返回false
     */
    public static boolean isFolderExist(String directoryPath) {
        if (StringUtils.isBlank(directoryPath)) {
            return false;
        }

        File dire = new File(directoryPath);
        return (dire.exists() && dire.isDirectory());
    }

    /**
     * 删除文件或空文件夹
     * <ul>
     * <li>路径为null或空字符串，返回true</li>
     * <li>路径不存在，返回true</li>
     * <li>路径存在并且为文件或空文件夹，返回{@link File#delete()}，否则返回false</li>
     * <ul>
     * 
     * @param path 路径
     * @return 是否删除成功
     */
    public static boolean deleteFile(String path) {
        if (StringUtils.isBlank(path)) {
            return true;
        }

        File file = new File(path);
        if (file.exists()) {
            if (file.isFile()) {
                return file.delete();
            } else if (file.isDirectory()) {
                return file.delete();
            }
            return false;
        }
        return true;
    }
}
