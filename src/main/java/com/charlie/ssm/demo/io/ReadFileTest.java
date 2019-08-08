package com.charlie.ssm.demo.io;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author chenlw 2019/08/08
 */
public class ReadFileTest {

    public static void main(String[] args) {
        try {
            // 绝对路径或相对路径都可以
            String pathname = "mfsfile.txt";
            String data = readFileContentByLine(pathname);
            System.out.println(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    /**
     * 按行读取文件内容，将其内容转化成字符串返回
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static String readFileContentByLine(String filePath) throws Exception {
        StringBuilder data = new StringBuilder();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data.append(line);
            }
        } catch (Exception e) {
            throw new Exception("按行读取文件内容错误：" + e.getMessage());
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return data.toString();
    }


}
