package file;

import java.io.File;

/**
 * @author chenlw 2019/08/21
 */
public class FileTester {

    public static void main(String[] args) {
        try {
            createDirectoryUsingMakeDir();
            createDirectoryUsingMakeDirs();
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }
    }

    /**
     * 使用mkdir()方法创建目录E:/AAA/BBB/CCC，其结果为false，即创建失败。
     * 原因：mkdir()，创建此抽象路径名指定的目录。如果父目录不存在则创建不成功。因为AAA和BBB目录都不存在，所以目录创建失败。
     */
    public static void createDirectoryUsingMakeDir() {
        String directoryPath = "E:/AAA/BBB/CCC";
        File directory = new File(directoryPath);
        System.out.println("文件是否存在：" + directory.exists());
        if (!directory.exists()) {
            boolean createdResult = directory.mkdir();
            System.out.println("使用mkdir()方法创建目录结果：" + createdResult);
        }

    }

    /**
     * mkdirs() 创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
     */
    public static void createDirectoryUsingMakeDirs() {
        String directoryPath = "E:/AAA/BBB/CCC";
        File directory = new File(directoryPath);
        System.out.println("文件是否存在：" + directory.exists());
        if (!directory.exists()) {
            boolean createdResult = directory.mkdirs();
            System.out.println("使用mkdirs()方法创建目录结果：" + createdResult);
        }

    }


}
