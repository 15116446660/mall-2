package com.perenc.mall.common.util;

import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @ClassName: FileUtils
 * @Description: 文件操作封装类
 *
 * @Author: GR
 * @Date: 2019-7-12 17:09
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019-7-12     GR
 */
public class FileUtils {

    public static final int BUFFER_SIZE = 4096;

    /**
     * @description: //TODO 获取文件的扩展名
     * @param fileName
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:38
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static String getExtensionName(String fileName) {
        if (StringUtils.hasLength(fileName)) {
            int position = fileName.lastIndexOf('.');
            if (position >= 0 && position < (fileName.length() - 1)) {
                return fileName.substring(position + 1);
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    /**
     * @description: //TODO 根据完整路径获取文件名
     * @param fileName
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:38
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static String getFileName(String fileName) {
        if (StringUtils.hasLength(fileName)) {
            File file = new File(fileName);
            return file.getName();
        } else {
            return "";
        }
    }

    /**
     * @description: //TODO 获取文件完整路径中的路径名
     * @param fileName
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:39
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static String getFilePath(String fileName) {
        if (StringUtils.hasLength(fileName)) {
            File file = new File(fileName);
            return file.getParentFile().getPath();
        } else {
            return "";
        }
    }

    /**
     * 拼接文件路径
     *
     * @param srcPath
     *            原路径
     * @param combinePath
     *            需要拼接的路径
     * @param createFlag
     *            创建标识，如果路径不存在就创建
     * @return
     */
    /**
     * @description: //TODO 拼接文件路径
     * @param srcPath
     *              原路径
     * @param combinePath
     *              需要拼接的路径
     * @param createFlag
     *              创建标识，如果路径不存在就创建
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:39
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static String combinePath(String srcPath, String combinePath,
                                     boolean createFlag) throws Exception {
        File file = new File(srcPath);
        if (!file.exists()) {
            boolean flag = file.mkdir();
            if (!flag && createFlag) {
                throw new Exception("要创建的原路径不存在，无法进行路径拼接！");
            }
        }
        String rtn = srcPath;
        rtn.replaceAll("\\\\", "/");
        if (!rtn.endsWith("/")) {
            rtn = srcPath + "/";
        }
        rtn = rtn + combinePath;
        if (createFlag) {
            file = new File(rtn);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        return rtn;
    }

    /**
     * @description: //TODO 去除绝对路径中的扩展名
     * @param fileName
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:39
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static String getFileNameNoExtension(String fileName) {
        if (StringUtils.hasLength(fileName)) {
            int position = fileName.lastIndexOf('.');
            if (position >= 0 && position < (fileName.length() - 1)) {
                return fileName.substring(0, position);
            } else {
                return fileName;
            }
        } else {
            return "";
        }
    }

    /**
     * @description: //TODO 拷贝文件
     * @param srcFileName
     * @param targetFileName
     * @return void
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:40
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static void copy(String srcFileName, String targetFileName)
            throws Exception {
        Path source = Paths.get(srcFileName);
        Path target = Paths.get(targetFileName);
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

    }

    /**
     * @description: //TODO 拷贝文件
     * @param in
     * @param out
     * @return void
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:40
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static void copy(File in, File out) throws Exception {
        Files.copy(in.toPath(), out.toPath(),
                StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * @description: //TODO 拷贝文件字节到输出文件
     * @param in
     * @param out
     * @return void
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:40
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static void copy(byte[] in, File out) throws Exception {
        ByteArrayInputStream inStream = new ByteArrayInputStream(in);
        Files.copy(inStream, out.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * @description: //TODO 移动文件
     * @param srcFileName    原文件
     * @param targetFileName 目标文件
     * @return void
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:40
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static void moveFile(String srcFileName, String targetFileName)
            throws Exception {
        Path source = Paths.get(srcFileName);
        Path target = Paths.get(targetFileName);
        Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * @description: //TODO 读取文件到输入流
     * @param fileName
     * @return java.io.InputStream
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:41
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static InputStream getInputStream(String fileName) throws Exception {
        File file = new File(fileName);
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    /**
     * @description: //TODO 读取文件到字节流
     * @param fileName
     * @return byte[]
     * @throws Exception
     * @author: GR
     * @date: 2019-7-15 16:41
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static byte[] readFileBytes(String fileName) throws Exception {
        return Files.readAllBytes(Paths.get(fileName));
    }

    /**
     * @description: //TODO 读取文件字节流
     * @param file
     * @return byte[]
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:41
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static byte[] readFileBytes(File file) throws IOException {
        return Files.readAllBytes(file.toPath());
    }

    /**
     * @description: //TODO 从输入流拷贝到输出流
     * @param in
     * @param out
     * @return int
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:42
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static int copy(InputStream in, OutputStream out)
            throws Exception {
        try {
            int byteCount = 0;
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                byteCount += bytesRead;
            }
            out.flush();
            return byteCount;
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
            }
            try {
                out.close();
            } catch (IOException ex) {
            }
        }
    }

    /**
     * @description: //TODO 从reader对象拷贝数据导Writer对象
     * @param in
     * @param out
     * @return int
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:42
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static int copy(Reader in, Writer out) throws IOException {
        try {
            int byteCount = 0;
            char[] buffer = new char[BUFFER_SIZE];
            int bytesRead = -1;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                byteCount += bytesRead;
            }
            out.flush();
            return byteCount;
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
            }
            try {
                out.close();
            } catch (IOException ex) {
            }
        }
    }

    /**
     * @description: //TODO 将字符串写入输出对象
     * @param in
     * @param out
     * @return void
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:43
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static void copy(String in, Writer out) throws IOException {
        try {
            out.write(in);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
            }
        }
    }

    /**
     * @description: //TODO 将reader对象拷贝到String对象返回
     * @param in
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:43
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static String copyToString(Reader in) throws IOException {
        StringWriter out = new StringWriter();
        copy(in, out);
        return out.toString();
    }

    public static byte[] copyToByteArray(InputStream in) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);
        copy(in, out);
        return out.toByteArray();
    }

    /**
     * @description: //TODO 删除文件
     * @param fileName
     * @return boolean
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:43
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        boolean result = false;
        int tryCount = 0;
        while (!result && tryCount++ < 10) {
            System.gc();
            result = file.delete();
        }
        return result;

    }

    /**
     * @description: //TODO 创建目录
     * @param dirName
     * @return boolean
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:43
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static boolean createDir(String dirName) {
        File file = new File(dirName);
        return file.mkdir();

    }

    /**
     * @description: //TODO 判断文件是否存在
     * @param fileName
     * @return boolean
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:43
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static boolean isExist(String fileName) {
        boolean rst = false;
        if ((fileName != null) && (!fileName.equalsIgnoreCase(""))) {
            rst = new File(fileName).exists();
        }
        return rst;
    }

    /**
     * @description: //TODO 重命名文件
     * @param oldName
     * @param newName
     * @return boolean
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:44
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static boolean rename(String oldName, String newName) {
        boolean rst = false;
        File oldFile = new File(oldName);
        File newFile = new File(newName);
        rst = oldFile.renameTo(newFile);
        return rst;
    }

    /**
     * @description: //TODO 拷贝目录（不包含子目录）
     * @param srcPath
     * @param targetPath
     * @return boolean
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:44
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static boolean copyDir(String srcPath, String targetPath)
            throws Exception {
        boolean rst = false;
        if (!FileUtils.isExist(targetPath)) {
            FileUtils.createDir(targetPath);
        }
        File[] files = (new File(srcPath)).listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                copy(files[i],
                        new File(targetPath + File.separator
                                + files[i].getName()));
            }
        }
        return rst;
    }

    /**
     * @description: //TODO 拷贝目录，包括子目录
     * @param srcPath
     * @param targetPath
     * @return void
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:44
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static void xCopyDir(String srcPath, String targetPath)
            throws Exception {
        if (!FileUtils.isExist(targetPath)) {
            FileUtils.createDir(targetPath);
        }
        File[] files = (new File(srcPath)).listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                copy(files[i],
                        new File(targetPath + File.separator
                                + files[i].getName()));
            }
            if (files[i].isDirectory()) {
                xCopyDir(srcPath + File.separator + files[i].getName(),
                        targetPath + File.separator + files[i].getName());
            }
        }
    }

    /**
     * @description: //TODO 根据文件名生成目录名称
     * @param fileName
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:44
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static String generateDirName(String fileName) throws Exception {
        String dirname = "";
        try {
            File fileComptation = new File(fileName);
            int i = fileComptation.getName().lastIndexOf('.');

            if (i != -1) {
                dirname = fileComptation.getName().substring(0, i);
            } else {
                dirname = fileComptation.getName();
            }
        } catch (RuntimeException e) {
            throw e;
        }
        return dirname;
    }

    /**
     * @description: //TODO 删除目录
     * @param filePath
     * @return void
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:44
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static void delAllFile(String filePath) {
        File file = new File(filePath);
        File[] fileList = file.listFiles();
        String dirPath = null;
        if (fileList != null) {
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isFile()) {

                    fileList[i].delete();
                }
                if (fileList[i].isDirectory()) {
                    dirPath = fileList[i].getPath();
                    delAllFile(dirPath);
                }
            }
            file.delete();
        }
    }

    /**
     * @description: //TODO 删除所有目录文件
     * @param filePath
     * @return void
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:45
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static void forceDelAllFile(String filePath) {
        File file = new File(filePath);
        File[] fileList = file.listFiles();
        String dirPath = null;
        if (fileList != null) {
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isFile()) {
                    forceDeleteFile(fileList[i]);
                }
                if (fileList[i].isDirectory()) {
                    dirPath = fileList[i].getPath();
                    forceDelAllFile(dirPath);
                }
            }
            forceDeleteFile(file);
        }
    }

    /**
     * @description: //TODO 读取文件
     * @param path
     * @param charset
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:45
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static String readFile(String path, String charset) throws Exception {
        String content = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(path), charset));
            String line;
            while ((line = reader.readLine()) != null) {
                content += line;
            }
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    throw e;
                }
            }
        }
        return content;
    }

    /**
     * @description: //TODO 将输入流读取到文件
     * @param in
     * @param charset
     * @return java.lang.String
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:46
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static String readFile(InputStream in, String charset)
            throws Exception {
        String content = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in, charset));
            String line;
            while ((line = reader.readLine()) != null) {
                content += line + (char) 13 + (char) 10;
            }
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    throw e;
                }
            }
        }
        return content;
    }

    /**
     * @description: //TODO 将字符串写入文件
     * @param content
     * @param fileName
     * @return void
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:46
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static void writeToFile(String content, String fileName)
            throws Exception {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new FileWriter(fileName)));
            out.println(content);
            out.flush();
            out.close();
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * @description: //TODO 将字符串写入文件 writeToFile
     * @param content
     * @param fileName
     * @param encode
     * @return void
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:47
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static void writeToFile(String content, String fileName,
                                   String encode) throws Exception {
        File file = new File(fileName);
        final Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), encode));
        out.write(content);
        out.flush();
        out.close();

    }

    /**
     * @description: //TODO 将InputStream保存到文件
     * @param in
     * @param fileName
     * @return void
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:47
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static void writeStreamToFile(InputStream in, String fileName)
            throws Exception {
        BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream(fileName));
        BufferedInputStream bufferin = new BufferedInputStream(in);
        int c;
        while ((c = bufferin.read()) != -1) {
            out.write(c);
        }
        bufferin.close();
        out.close();
    }

    /**
     * @description: //TODO 将文件内容读取到字节
     * @param fileName
     * @return byte[]
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:47
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static byte[] getFileStream(String fileName) {
        InputStream fileIn = null;
        DataInputStream dataIn = null;
        try {

            File file = new File(fileName);
            byte buff[] = new byte[new Long(file.length()).intValue()];
            fileIn = new FileInputStream(file);
            dataIn = new DataInputStream(fileIn);
            dataIn.readFully(buff);
            return buff;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                dataIn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @description: //TODO 获取文件大小
     * @param fileName
     * @return long
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:47
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static long getFileSize(String fileName) throws Exception {
        File file = new File(fileName);
        return file.length();
    }

    /**
     * @description: //TODO 强制删除
     * @param file
     * @return void
     * @throws
     * @author: GR
     * @date: 2019-7-15 16:47
     *
     * modification history:
     * date         author      description
     *---------------------------------------------------------*
     * 2019-7-15       GR
     */
    public static void forceDeleteFile(File file) {

        boolean result = false;
        int tryCount = 0;
        while (!result && tryCount++ < 10) {
            System.gc();
            result = file.delete();
        }
    }

}
