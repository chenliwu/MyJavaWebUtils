package com.chenlw.java.web.utils.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP工具类
 * （1）发送GET请求
 * （2）发送POST请求
 * （3）下载文件请求
 *
 * @author: chenlw
 * @date 2018/10/19  22:47
 **/
public class HttpClientUtils1 {

    /**
     * 临时文件保存
     */
    private static final String tempFileDir = "D://tempFileDir/";

    private static int connectTimeout = 10000;
    private static int socketTimeout = 10000;


    public static void main(String[] args) {
        //testGet();
        testDownFile();
    }

    public static void testGet() {
        try {
            String url = "http://127.0.0.1:8099/api/files/testParam";
            Map<String, String> params = new HashMap<>();
            params.put("param", "paramValue");
            System.out.println("testGet：" + get(url, params));
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }
    }

    public static void testDownFile() {
        try {
            String scheme = "http";
            String host = "127.0.0.1:8099/MyJavaWebUtils";
            String path = "/api/files/downFile";
            Map<String, String> params = new HashMap<>();
            params.put("downFilePath", "downFilePath");
            String saveFileName = "saveFileName.jpg";
            getFile(scheme, host, path, params, tempFileDir + saveFileName);
            System.out.println("下载文件完毕...");
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }
    }

    /**
     * get请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String get(String url, Map<String, String> params) {

        URIBuilder builder = new URIBuilder();

        //构建请求参数
        for (String key : params.keySet()) {
            builder.setParameter(key, params.get(key).toString());
        }
        try {
            HttpGet httpGet = new HttpGet(url);
            URI uri = builder.build();
            httpGet.setURI(uri);

            HttpClientBuilder httpClientBuilder = HttpClients.custom();

            HttpClient httpClient = httpClientBuilder.build();

            HttpResponse resp = httpClient.execute(httpGet);
            if (resp.getStatusLine().getStatusCode() >= 300) {
                System.err.println("Something wrong: " + resp.getStatusLine().toString());
            }
            BufferedReader input = new BufferedReader(new InputStreamReader(resp.getEntity().getContent(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            char[] buf = new char[1000];
            int count;
            while ((count = input.read(buf)) > 0) {
                sb.append(buf, 0, count);
            }
            return sb.toString();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 下载文件
     *
     * @param destFileName xxx.jpg/xxx.png/xxx.txt
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static void getFile(String scheme, String host, String path, Map<String, String> params, String destFileName)
            throws ClientProtocolException, IOException {

        OutputStream out = null;
        InputStream in = null;

        // 生成一个httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        URIBuilder builder = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path);

        for (String key : params.keySet()) {
            builder.setParameter(key, params.get(key).toString());
        }

        try {

            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);

            HttpResponse response = httpclient.execute(httpGet);

            HttpEntity entity = response.getEntity();
            in = entity.getContent();

            //读取输入流
            in = entity.getContent();
            long length = entity.getContentLength();

            //临时保存文件的目录
            File dir = new File(tempFileDir);
            if (!dir.exists()) {
                //创建文件夹
                dir.mkdir();
            }

            File file = new File(destFileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            //文件输出流，把输入流的内容保存到文件当中
            out = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int readLength = 0;
            while ((readLength = in.read(buffer)) > 0) {
                byte[] bytes = new byte[readLength];
                System.arraycopy(buffer, 0, bytes, 0, readLength);
                out.write(bytes);
            }
            out.flush();
        } catch (IOException | URISyntaxException e) {

        } finally {
            // 关闭低层流。
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            httpclient.close();
        }
    }


}
