package com.charlie.ssm.demo.filesystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 文件系统
 */
@Controller
@RequestMapping("/api/files")
public class FileRestController {

    @GetMapping("/downFile")
    public void downFile(HttpServletResponse response) throws Exception{
        String filePath = "F:/myimg.jpg";
        File file = new File(filePath);
        if(!file.exists()){
            System.out.println("文件不存在");
            return ;
        }
        InputStream inputStream = null;
        OutputStream outputStream =null;
        try {
            //转码，免得文件名中文乱码
            String filename = URLEncoder.encode(file.getName(),"UTF-8");
            //设置文件下载头
            response.addHeader("Content-Disposition", "attachment;filename="+ filename);
            response.addHeader("Content-Length",String.valueOf(file.length()));
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
            if(outputStream!=null){
                outputStream.close();
            }
            if(inputStream!=null){
                inputStream.close();
            }
        }
    }


    @GetMapping("/getImg")
    public void getImg(HttpServletResponse response) throws IOException{
        //读取本地图片输入流
        String filePath = "F:/myimg.jpg";
        if(new File(filePath).exists()){
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
                if(inputStream != null){
                    //关闭输入流
                    inputStream.close();
                }
                if(out!=null){
                    //关闭响应输出流
                    out.close();
                }
            }
        }else{
            System.out.println("文件不存在");
        }
    }

}
