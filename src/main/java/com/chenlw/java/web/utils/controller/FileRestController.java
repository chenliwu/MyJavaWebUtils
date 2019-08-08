package com.chenlw.java.web.utils.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.chenlw.java.web.utils.utils.HttpUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;

/**
 * 文件系统
 */
@RestController
@RequestMapping("/api/files")
public class FileRestController {

    @CrossOrigin(allowCredentials="true",maxAge = 3600)
    @RequestMapping("/upload")
    public String upload(MultipartFile file){
        if (file.isEmpty()) {
            return null;
        }
        String filename = file.getOriginalFilename();

        String ext= null;
        if(filename.contains(".")){
            ext = filename.substring(filename.lastIndexOf("."));
        }else{
            ext = "";
        }

        String uuid =  UUID.randomUUID().toString().replaceAll("-", "");
        String nfileName = uuid + ext;
        String curDate= DateUtil.format(new Date(), "yyyyMMdd");
        String dirPath = "/passenger-web/file/"+ curDate ; //DateFormatUtils.format(new Date(), "yyyyMMdd");
        String BASE_PATH=System.getProperty("user.dir");
        String filepath = BASE_PATH.endsWith("/") ? BASE_PATH+dirPath : BASE_PATH+"/"+dirPath;
        File targetFile = new File(filepath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        File toFile = new File(filepath, nfileName);
        try {
            file.transferTo(toFile);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //下载url 等于=日期/id.文件类型
        if(StrUtil.isNotEmpty(ext)){
            ext=ext.substring(1);
        }
        String downloadUrl = "/file/download/"+curDate+ "/"+uuid+"/"+ext;
        return downloadUrl;
    }

    @GetMapping("/testParam")
    public String testHttpClientParam(@RequestParam("param") String param){
        System.out.println("测试HttpClient参数传递");
        System.out.println("param = "+param);
        return param;
    }

    @GetMapping("/downFile")
    public void downFile(HttpServletResponse response, @RequestParam(name = "downFilePath",required = false) String downFilePath) throws Exception {
        try {
            System.out.println("downFilePath:"+downFilePath);
            //String filePath = "F:/myimg.jpg";
            String filePath = "E:/myimg.jpg";
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("文件不存在");
                return;
            }
            outputFile(response, file);
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }
    }

    /**
     * 跨域下载文件
     *
     * @param response
     * @throws Exception
     */
    @GetMapping("/downFile1")
    public void downFile1(HttpServletResponse response) throws Exception {
        try {
            File file = HttpUtils.getDownFile("/api/files/downFile", "downPath", "testDown.jpg");
            if (!file.exists()) {
                System.out.println("文件不存在");
                return;
            }
            outputFile(response, file);
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }

    }

    /**
     * 将文件转化成输出流
     *
     * @param response
     * @param file
     */
    private void outputFile(HttpServletResponse response, File file) throws Exception {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            //转码，免得文件名中文乱码
            String filename = URLEncoder.encode(file.getName(), "UTF-8");
            //设置文件下载头
            response.addHeader("Content-Disposition", "attachment;filename=" + filename);
            response.addHeader("Content-Length", String.valueOf(file.length()));
            //设置文件ContentType类型
            response.setContentType("multipart/form-data");

            //获取文件输入流
            inputStream = new FileInputStream(file);
            //获取相应输出流
            outputStream = response.getOutputStream();
            byte[] buff = new byte[1024];
            int length;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }


    @GetMapping("/getImg")
    public void getImg(HttpServletResponse response) throws IOException {
        //读取本地图片输入流
        String filePath = "F:/myimg.jpg";
        if (new File(filePath).exists()) {
            FileInputStream inputStream = null;
            OutputStream out = null;
            try {
                inputStream = new FileInputStream(filePath);
                int i = inputStream.available();
                //byte数组用于存放图片字节数据
                byte[] buff = new byte[i];
                inputStream.read(buff);
                //设置发送到客户端的响应内容类型
                response.setContentType("image/*");
                out = response.getOutputStream();
                out.write(buff);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    //关闭输入流
                    inputStream.close();
                }
                if (out != null) {
                    //关闭响应输出流
                    out.close();
                }
            }
        } else {
            System.out.println("文件不存在");
        }
    }

}
