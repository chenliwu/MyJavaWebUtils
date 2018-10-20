package com.charlie.ssm.demo.utils;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2018-10-20 17:40
 */
public class FileUtils {


    public static void main(String[] args) {
        getFileNameByAbsolutePath("D://tempFileDir/saveFileName.jpg");
    }

    /**
     * 根据文件路径获取文件名称
     *
     * @param fileAbsolutePath
     * @return
     */
    public static String getFileNameByAbsolutePath(String fileAbsolutePath) {
        String str = fileAbsolutePath.trim();
        String fileName = str.substring(str.lastIndexOf("/") + 1);
        System.out.println("fileName = " + fileName);
        return fileName;
    }

}
