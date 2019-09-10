package com.shenzhen.test.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Android文件工具类
 *
 * @author Mourinho.Zhu on 16-11-24.
 */
public final class MZFile {
    //私有构造函数
    private MZFile() {
    }

    //TAG
    private static final String TAG = "MZFile";

    //遍历递归最大层数
    private static final int MAX_TRAVERSAL_DEEP = 10;

    //换行符
    private static String LINE_SEPARATOR = System.getProperty("line.separator");

    //附件后缀标识
    private static final String COPY_SUFFIX_CN = " - 副本";
    private static final String COPY_MORE_SUFFIX_CN = " - 副本(%s)";

    /**
     * 文件名匹配枚举类型
     */
    public enum GrepType {
        /**
         * 不进行匹配
         */
        NONE,
        /**
         * 字符完全匹配
         */
        EQUALS,
        /**
         * 字符完全匹配，但不区分大小写
         */
        EQUALS_IGNORE_CASE,
        /**
         * 包含
         */
        CONTAINS,
        /**
         * 包含，但不区分大小写
         */
        CONTAINS_IGNORE_CASE
    }

    /**
     * 文件后缀名枚举
     */
    public enum FileType {
        VIDEO(new String[]{".3gp", ".mp4", ".wmv", ".avi", ".mkv", ".mov",
                ".asf", ".mpg", ".vob", ".ts", ".webm"}), AUDIO(new String[]{
                ".mp3", ".wav", ".wma", ".aac", ".ogg", ".m4a", ".flac"}), IMAGE(
                new String[]{".jpg", ".gif", ".bmp", ".png", ".jpe", ".jpeg"}), TEXT(
                new String[]{".txt"}), ALL(new String[]{".*"});

        private String[] mSuffixs;

        private FileType(String[] suffixs) {
            mSuffixs = suffixs;
        }

        public String[] getSuffixs() {
            return mSuffixs;
        }

        public String[] getRegexSuffixs() {
            String[] suffixs = new String[mSuffixs.length];
            for (int i = 0; i < mSuffixs.length; ++i) {
                suffixs[i] = "\\" + mSuffixs[i];
            }
            return suffixs;
        }
    }

    //文件后缀名检测类型常量
    private final static String JPG = "jpg";
    private final static String PNG = "png";
    private final static String GIF = "gif";
    private final static String TIF = "tif";
    private final static String BMP = "bmp";

    //文件后缀名检测类型map
    private final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();

    static {
        FILE_TYPE_MAP.put("FFD8FF", JPG);
        FILE_TYPE_MAP.put("89504E47", PNG);
        FILE_TYPE_MAP.put("47494638", GIF);
        FILE_TYPE_MAP.put("49492A00", TIF);
        FILE_TYPE_MAP.put("424D", BMP);
        //TODO:添加其他类型
    }

    /*********************************** 文件基本操作 开始***********************************/
    /**
     * 检查文件是否存在
     *
     * @param filePath 文件路径
     * @return 存在返回true，否则返回false
     */
    public static boolean exists(String filePath) {
        File file = new File(filePath);
        return null != file && file.exists();
    }

    //检查文件是否存在
    private static boolean exists(File file) {
        return null != file && file.exists();
    }

    /**
     * 检查是否为文件
     *
     * @param filePath 文件路径
     * @return 是文件返回true，否则返回false
     */
    public static boolean isFile(String filePath) {
        File file = new File(filePath);
        return exists(file) && file.isFile();
    }

    /**
     * 检查是否为文件夹
     *
     * @param filePath 文件路径
     * @return 是文件夹返回true，否则返回false
     */
    public static boolean isDirectory(String filePath) {
        File file = new File(filePath);
        return exists(file) && file.isDirectory();
    }

    /**
     * 检查文件是否可读
     *
     * @param filePath 文件路径
     * @return 可读返回true，否则返回false
     */
    public static boolean canRead(String filePath) {
        File file = new File(filePath);
        return exists(file) && file.canRead();
    }

    /**
     * 检查文件是否可写
     *
     * @param filePath 文件路径
     * @return 可写返回true，否则返回false
     */
    public static boolean canWrite(String filePath) {
        File file = new File(filePath);
        return exists(filePath) && file.canWrite();
    }

    /**
     * 检查是否为隐藏文件
     *
     * @param filePath 文件路径
     * @return 是隐藏文件返回true，否则返回false
     */
    public static boolean isHidden(String filePath) {
        File file = new File(filePath);
        return exists(file) && file.isHidden();
    }

    /**
     * 删除文件
     *
     * @param filePath 文件路径
     * @return 删除成功返回true
     */
    public static boolean delete(String filePath) {
        File file = new File(filePath);
        if (!exists(file)) {
            // 文件不存在直接返回
            return false;
        }
        if (file.isDirectory()) {
            // 先删除文件夹里面全部文件，再删除文件夹
            File[] subFiles = file.listFiles();
            if (null != subFiles) {
                for (File subFile : subFiles) {
                    delete(subFile.getAbsolutePath());
                }
            }
        }
        return file.delete();
    }

    /**
     * 创建文件夹
     *
     * @param filePath   文件夹路径
     * @param isOverride 设置true会删除已存在的文件夹或者是同名文件
     * @return 创建成功返回该文件夹，否则返回null
     */
    public static File createDirectory(String filePath, boolean isOverride) {
        return createFileOrDirectory(filePath, isOverride, false/* 是否为文件 */);
    }

    /**
     * 创建文件
     *
     * @param filePath   文件夹路径
     * @param isOverride 设置true会删除已存在的文件
     * @return 创建成功返回该文件，否则返回null
     */
    public static File createFile(String filePath, boolean isOverride) {
        return createFileOrDirectory(filePath, isOverride, true/* 是否为文件 */);
    }

    /* 创建文件或文件夹 */
    private static File createFileOrDirectory(String filePath,
                                              boolean isOverride, boolean isFile) {
        File file = new File(filePath);
        try {
            if (!exists(filePath) || (isOverride && delete(filePath))) {
                return (isFile ? file.createNewFile() : file
                        .mkdirs()) ? file : null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 无法创建文件
        MZLog.e(TAG, "createFileOrDirectory fail --> " + filePath);
        return null;
    }

    /**
     * 重命名文件
     *
     * @param oldFilePath 源文件路径
     * @param newFilePath 目标文件路径
     * @param isOverride  是否覆盖
     * @return 重命名成功返回true
     */
    public static boolean rename(String oldFilePath, String newFilePath,
                                 boolean isOverride) {
        if (exists(oldFilePath)) {
            if (isOverride || !exists(newFilePath)) {
                File file = new File(oldFilePath);
                return file.renameTo(new File(newFilePath));
            }
        }
        return false;
    }

    /**
     * 获取文件的文件夹名称
     *
     * @param filePath 文件路径
     * @return 文件的文件夹名称, 无法获取返回null
     */
    public static String getDirectoryName(String filePath) {
        File file = new File(filePath);
        if (exists(file)) {
            File parentFile = file.getParentFile();
            if (exists(parentFile)) {
                return parentFile.getName();
            }
        }
        return null;
    }

    /**
     * 获取文件名称
     *
     * @param filePath      文件路径
     * @param containSuffix 是否包含后缀名
     * @return 文件名称, 无法获取返回null
     */
    public static String getName(String filePath, boolean containSuffix) {
        File file = new File(filePath);
        String name = exists(file) ? file.getName() : null;
        if (null != name && containSuffix) {
            if (name.contains(".")) {
                name = name.substring(0, name.lastIndexOf("."));
            }
        }
        return name;
    }

    /**
     * 获取文件后缀名
     *
     * @param filePath 文件路径
     * @return 文件后缀名, 无法获取返回null
     */
    public static String getSuffix(String filePath) {
        String name = getName(filePath, true);
        if (null != name && name.contains(".")) {
            return name.substring(name.lastIndexOf("."));
        }
        return null;
    }

    /**
     * 获取子文件路径
     *
     * @param parentPath 父文件路径
     * @param fileName   子文件名称
     * @return 子文件路径
     */
    public static String getChildPath(String parentPath, String fileName) {
        return (null != parentPath && parentPath.endsWith(File.separator)) ? parentPath + fileName : parentPath + File.separator + fileName;
    }


    /*********************************** 文件基本操作 结束***********************************/

    /*********************************** 文件读写操作 开始***********************************/
    /**
     * 按行读取文件
     *
     * @param filePath 文件路径
     * @return 文件内容字符串, 文件路径无效返回null
     */
    public static String readByLine(String filePath) {
        if (!canRead(filePath)) {
            return null;
        }
        File file = new File(filePath);
        try {
            return readByBufferReader(new BufferedReader(new FileReader(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从BufferedReader中读取内容
     *
     * @param bufferedReader bufferedReader
     * @return 内容字符串, 无法读取返回null
     */
    public static String readByBufferReader(BufferedReader bufferedReader) {
        StringBuilder sb = null;
        try {
            sb = new StringBuilder();
            String tempString;
            while (null != (tempString = bufferedReader.readLine())) {
                sb.append(tempString).append(LINE_SEPARATOR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null == sb ? null : sb.toString().trim();
    }

    /**
     * 按字节读取文件，以byte数组形式返回文件内容
     *
     * @param filePath 文件路径
     * @return byte数组形式文件内容，文件路径无效或读取异常返回null
     */
    public static byte[] readToByte(String filePath) {
        if (!canRead(filePath)) {
            return null;
        }
        byte[] data = null;
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            int READ_SIZE = 1024;
            int bytesRead;
            fis = new FileInputStream(filePath);
            bos = new ByteArrayOutputStream();
            byte[] b = new byte[READ_SIZE];
            while ((bytesRead = fis.read(b)) != -1) {
                bos.write(b, 0, bytesRead);
            }
            data = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    return null;
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    return null;
                }
            }
        }
        return data;
    }

    /**
     * 从文件读取byte数组
     *
     * @param context  上下文
     * @param filePath 文件路径
     * @return byte数组格式数据, 无法读取返回null
     */
    public static byte[] readFromContext(Context context, String filePath) {
        byte[] data = null;
        int bytesRead = 0;
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = context.openFileInput(filePath);
            bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            while ((bytesRead = fis.read(b)) != -1) {
                bos.write(b, 0, bytesRead);
            }
            data = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    return null;
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    return null;
                }
            }
        }
        return data;
    }

    /**
     * 读取asset文件
     *
     * @param context  当前上下文
     * @param fileName 文件名称
     * @return 文件内容字符串, 无法读取返回null
     */
    public static String readAssets(Context context, String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context
                    .getResources().getAssets().open(fileName));
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            return readByBufferReader(bufferedReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把字符串写入指定文件
     *
     * @param filePath   文件路径
     * @param content    写入内容字符串
     * @param isOverride 是否覆盖文件
     * @param isAppend   是否追加写入
     * @return 写入成功返回true
     */
    public static boolean writeFromString(String filePath, String content,
                                          boolean isOverride, boolean isAppend) {
        File file = createFile(filePath, isOverride);
        if (null != file) {
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(file, isAppend));
                writer.write(content);
                writer.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    /**
     * 把BufferedReader中的内容写入文件
     *
     * @param filePath       文件路径
     * @param bufferedReader bufferedReader
     * @param charListName   编码集名称
     * @param isOverride     是否覆盖文件
     * @param isAppend       是否追加写入
     * @return 写入成功返回true
     */
    public static boolean writeFromBufferReader(String filePath,
                                                BufferedReader bufferedReader, String charListName,
                                                boolean isOverride, boolean isAppend) {
        File file = createFile(filePath, isOverride);
        if (file != null) {
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file, isAppend);
                StringBuffer content = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line.trim() + LINE_SEPARATOR);
                }
                fos.write(content.toString().getBytes(charListName));
                fos.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.close();
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 把byte数组内容写入指定文件
     *
     * @param filePath   文件路径
     * @param data       byte数组内容
     * @param isOverride 是否覆盖文件
     * @param isAppend   是否追加写入
     * @return 写入成功返回true
     */
    public static boolean writeFromByte(String filePath, byte[] data,
                                        boolean isOverride, boolean isAppend) {
        File file = createFile(filePath, isOverride);
        if (file != null) {
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file, isAppend);
                fos.write(data);
                fos.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    /**
     * 把byte数组写入文件
     *
     * @param context  上下文
     * @param filePath 文件路径
     * @param data     byte数组格式数据
     * @param isAppend 是否追加写入
     * @return 写入成功返回true
     */
    public static boolean writeFromContext(Context context, String filePath, byte[] data,
                                           boolean isAppend) {
        FileOutputStream fos = null;
        try {
            int mode = Context.MODE_PRIVATE;
            if (isAppend) {
                mode |= Context.MODE_APPEND;
            }
            fos = context.openFileOutput(filePath, mode);
            fos.write(data);
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 拷贝asset文件
     *
     * @param context      上下文
     * @param fileName     asset文件名
     * @param destFilePath 目标路径
     * @param isOverride   是否覆盖
     * @return 拷贝成功返回true
     */
    public static boolean copyAssets(Context context, String fileName,
                                     String destFilePath, boolean isOverride) {
        if (exists(destFilePath) && !isOverride) {
            return false;
        }
        File destFile = createFile(destFilePath, isOverride);
        if (destFile != null) {
            InputStream is = null;
            OutputStream os = null;
            try {
                is = context.getAssets().open(fileName);
                os = new FileOutputStream(destFilePath, isOverride);
                byte[] buffer = new byte[1024];
                int length = is.read(buffer);
                while (length > 0) {
                    os.write(buffer, 0, length);
                    length = is.read(buffer);
                }
                os.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (os != null) {
                        os.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 复制单个文件
     *
     * @param oldFilePath 源文件路径
     * @param newFilePath 目标文件路径
     * @param isOverride  是否允许覆盖
     * @return 复制成功返回true
     */
    public static boolean copy(String oldFilePath, String newFilePath,
                               boolean isOverride) {
        // 如果oldFilePath是文件，并且newFilePath不是已经存在的目录，才执行复制操作
        if (isFile(oldFilePath) && !isDirectory(newFilePath)) {
            // 不允许覆盖
            if (!isOverride && isFile(newFilePath)) {
                File file = new File(newFilePath);
                if (exists(file)) {
                    newFilePath = getRepeatPath(file.getParentFile()
                            .getAbsolutePath(), file.getName());
                }
            }
            File newFile = createFile(newFilePath, isOverride);
            if (null != newFile) {
                InputStream is = null;
                FileOutputStream fs = null;
                try {
                    int byteCount = 0;
                    is = new FileInputStream(oldFilePath); // 读入原文件
                    fs = new FileOutputStream(newFile);
                    byte[] buffer = new byte[1024];
                    while ((byteCount = is.read(buffer)) != -1) {
                        fs.write(buffer, 0, byteCount);
                    }
                    fs.flush();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fs != null) {
                            fs.close();
                        }
                        if (is != null) {
                            is.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    /**
     * 复制文件夹
     *
     * @param oldDirectoryPath 源文件夹路径
     * @param newDirectoryPath 目标文件夹路径
     * @param isOverride       是否允许覆盖
     * @return 复制成功返回true
     */
    public static boolean copyDirectory(String oldDirectoryPath,
                                        String newDirectoryPath, boolean isOverride) {
        // 如果oldFilePath是文件夹，并且newFilePath不是已经存在的文件，才执行复制操作
        if (isDirectory(oldDirectoryPath) && !isFile(newDirectoryPath)) {
            if (!isOverride && isDirectory(newDirectoryPath)) {
                return false;
            }
            // 建立新的目标文件夹
            if ((createDirectory(newDirectoryPath, isOverride) != null)) {
                // 递归拷贝文件
                File oldDirectory = new File(oldDirectoryPath);
                File[] subFiles = oldDirectory.listFiles();
                if (null != subFiles) {
                    for (File file : subFiles) {
                        if (file.isFile()) {
                            copy(file.getAbsolutePath(), newDirectoryPath
                                    + File.separator + file.getName(), false);
                        } else if (file.isDirectory()) {
                            copyDirectory(file.getAbsolutePath(), newDirectoryPath
                                            + File.separator + file.getName(),
                                    isOverride);
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 剪切单个文件
     *
     * @param oldFilePath 源文件路径
     * @param newFilePath 目标文件路径
     * @param isOverride  是否允许覆盖
     * @return 剪切成功返回true
     */
    public static boolean move(String oldFilePath, String newFilePath,
                               boolean isOverride) {
        return copy(oldFilePath, newFilePath, isOverride)
                && delete(oldFilePath);
    }

    /**
     * 剪切文件夹
     *
     * @param oldDirectoryPath 源文件夹路径
     * @param newDirectoryPath 目标文件夹路径
     * @param isOverride       是否允许覆盖
     * @return 剪切成功返回true
     */
    public static boolean moveDirectory(String oldDirectoryPath,
                                        String newDirectoryPath, boolean isOverride) {
        return copyDirectory(oldDirectoryPath, newDirectoryPath, isOverride)
                && delete(oldDirectoryPath);
    }

    //获取重复文件名称,如1.txt在同目录复制3次分别为1 - 副本.txt,1 - 副本(2).txt,1 - 副本(3).txt
    private static String getRepeatPath(String parentPath, String fileName) {
        String suffix = "";
        int index = fileName.lastIndexOf(".");
        if (index != -1) {
            fileName = fileName.substring(0, index);
            suffix = fileName.substring(index);
        }
        //先检查" - 副本"文件是否存在
        String copySuffix = COPY_SUFFIX_CN;
        String copyMoreSuffix = COPY_MORE_SUFFIX_CN;
        String newPath = parentPath + fileName + copySuffix + suffix;
        if (exists(newPath)) {
            //依次检查" - 副本(n)"文件是否存在
            for (int i = 2; ; ++i) {
                String repeatName = String.format(copyMoreSuffix, i);
                newPath = parentPath + fileName + repeatName + suffix;
                if (!exists(newPath)) {
                    break;
                }
            }
        }
        return newPath;
    }
    /*********************************** 文件读写操作 结束***********************************/





    //*****************************zip压缩/解压缩 开始************************************//

    /**
     * 解压缩
     *
     * @param zipPath    zip文件名
     * @param unZipPath  解压缩路径
     * @param isOverride 是否覆盖解压缩文件
     * @return true表示成功
     */
    public static boolean unZip(String zipPath, String unZipPath, boolean isOverride) {
        MZLog.d(TAG, "unZip zipPath --> " + zipPath + " unZipPath --> " + unZipPath);
        if (!exists(zipPath)) {
            MZLog.d(TAG, "file is not existed! --> " + zipPath);
            return false;
        }
        if (exists(unZipPath) && !isOverride) {
            MZLog.d(TAG, "unZipPath is existed! --> " + unZipPath);
            return false;
        }
        ZipInputStream inZip = null;
        try {
            inZip = new ZipInputStream(new FileInputStream(zipPath));
            ZipEntry zipEntry;
            String name;
            while ((zipEntry = inZip.getNextEntry()) != null) {
                name = zipEntry.getName();
                if (zipEntry.isDirectory()) {
                    name = name.substring(0, name.length() - 1);
                    String path = unZipPath + File.separator + name;
                    createDirectory(path, isOverride);
                } else {
                    String path = unZipPath + File.separator + name;
                    File file = createFile(path, isOverride);
                    if (null != file) {
                        FileOutputStream out = null;
                        try {
                            out = new FileOutputStream(file);
                            int len;
                            byte[] buffer = new byte[1024];
                            while ((len = inZip.read(buffer)) != -1) {
                                out.write(buffer, 0, len);
                                out.flush();
                            }
                        } finally {
                            if (null != out) {
                                out.close();
                            }
                        }
                    } else {
                        return false;
                    }
                }
            }
            return exists(unZipPath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != inZip) {
                try {
                    inZip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }


    /**
     * 压缩文件
     *
     * @param filePath    待压缩文件路径
     * @param zipFilePath 压缩文件路径
     * @return true表示成功
     */
    public static boolean zip(String filePath, String zipFilePath) {
        if (!exists(filePath)) {
            MZLog.d(TAG, "file is not existed! --> " + filePath);
            return false;
        }
        ZipOutputStream outZip = null;
        File file = new File(filePath);
        try {
            outZip = new ZipOutputStream(new FileOutputStream(zipFilePath));
            zip(file.getParent() + File.separator, file.getName(), outZip);
            outZip.finish();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != outZip) {
                try {
                    outZip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    //压缩文件
    private static void zip(String folderPath, String filePath, ZipOutputStream outZip) throws Exception {
        File file = new File(folderPath + filePath);
        if (file.isFile()) {
            ZipEntry zipEntry = new ZipEntry(filePath);
            FileInputStream inputStream = new FileInputStream(file);
            outZip.putNextEntry(zipEntry);
            int len;
            byte[] buffer = new byte[4096];
            while ((len = inputStream.read(buffer)) != -1) {
                outZip.write(buffer, 0, len);
            }
            outZip.closeEntry();
        } else {
            String fileList[] = file.list();
            if (fileList.length <= 0) {
                ZipEntry zipEntry = new ZipEntry(filePath + File.separator);
                outZip.putNextEntry(zipEntry);
                outZip.closeEntry();
            }
            for (int i = 0; i < fileList.length; i++) {
                zip(folderPath, filePath + File.separator + fileList[i]);
            }
        }
    }

    /**
     * 获取zip里面的文件列表
     *
     * @param zipPath zip文件名
     * @return　zip里面的文件列表
     */
    public static List<File> getZipFileList(String zipPath) {
        List<File> fileList = new ArrayList<File>();
        ZipInputStream inZip = null;
        try {
            inZip = new ZipInputStream(new FileInputStream(zipPath));
            ZipEntry zipEntry;
            String name;
            while ((zipEntry = inZip.getNextEntry()) != null) {
                name = zipEntry.getName();
                if (zipEntry.isDirectory()) {
                    name = name.substring(0, name.length() - 1);
                }
                fileList.add(new File(name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != inZip) {
                try {
                    inZip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileList;
    }

    //*****************************zip压缩/解压缩 结束************************************//
    private static final String[][] MIME_TYPE_TABLE = {

            //{后缀名，    MIME类型}

            {".3gp", "video/3gpp"},

            {".apk", "application/vnd.android.package-archive"},

            {".amr", "audio/amr"},

            {".asf", "video/x-ms-asf"},

            {".avi", "video/x-msvideo"},

            {".bin", "application/octet-stream"},

            {".bmp", "image/bmp"},

            {".c", "text/plain"},

            {".class", "application/octet-stream"},

            {".conf", "text/plain"},

            {".cpp", "text/plain"},

            {".doc", "application/msword"},

            {".exe", "application/octet-stream"},

            {".gif", "image/gif"},

            {".gtar", "application/x-gtar"},

            {".gz", "application/x-gzip"},

            {".h", "text/plain"},

            {".htm", "text/html"},

            {".html", "text/html"},

            {".jar", "application/java-archive"},

            {".java", "text/plain"},

            {".jpeg", "image/jpeg"},

            {".jpg", "image/jpeg"},

            {".js", "application/x-javascript"},

            {".log", "text/plain"},

            {".m3u", "audio/x-mpegurl"},

            {".m4a", "audio/mp4a-latm"},

            {".m4b", "audio/mp4a-latm"},

            {".m4p", "audio/mp4a-latm"},

            {".m4u", "video/vnd.mpegurl"},

            {".m4v", "video/x-m4v"},

            {".mov", "video/quicktime"},

            {".mp2", "audio/mp2"},

            {".mp3", "audio/mp3"},

            {".mp4", "video/mp4"},

            {".mpc", "application/vnd.mpohun.certificate"},

            {".mpe", "video/mpeg"},

            {".mpeg", "video/mpeg"},

            {".mpg", "video/mpeg"},

            {".mpg4", "video/mp4"},

            {".mpga", "audio/mpeg"},

            {".msg", "application/vnd.ms-outlook"},

            {".ogg", "audio/ogg"},

            {".pdf", "application/pdf"},

            {".png", "image/png"},

            {".pps", "application/vnd.ms-powerpoint"},

            {".ppt", "application/vnd.ms-powerpoint"},

            {".prop", "text/plain"},

            {".rar", "application/x-rar-compressed"},

            {".rc", "text/plain"},

            {".rmvb", "audio/x-pn-realaudio"},

            {".rtf", "application/rtf"},

            {".sh", "text/plain"},

            {".tar", "application/x-tar"},

            {".tgz", "application/x-compressed"},

            {".txt", "text/plain"},

            {".wav", "audio/x-wav"},

            {".wma", "audio/x-ms-wma"},

            {".wmv", "audio/x-ms-wmv"},

            {".wps", "application/vnd.ms-works"},

            //{".xml",    "text/xml"},

            {".xml", "text/plain"},

            {".z", "application/x-compress"},

            {".zip", "application/zip"},

            {"", "*/*"}

    };

    /**
     * 获取文件mime type
     *
     * @param filePath 文件路径
     * @return 文件mime type
     */
    public static String getMimeType(String filePath) {
        String type = "";
        if (exists(filePath)) {
            File file = new File(filePath);
            String fileName = file.getName();
            //获取后缀名前的分隔符"."在fName中的位置。
            int dotIndex = fileName.lastIndexOf(".");
            if (dotIndex < 0) {
                return type;
            }
            /* 获取文件的后缀名 */
            String end = fileName.substring(dotIndex, fileName.length()).toLowerCase();
            if (end.equals("")) return type;
            //在MIME和文件类型的匹配表中找到对应的MIME类型。
            for (int i = 0; i < MIME_TYPE_TABLE.length; i++) {
                if (end.equals(MIME_TYPE_TABLE[i][0]))
                    type = MIME_TYPE_TABLE[i][1];
            }
        }
        return type;
    }
}
