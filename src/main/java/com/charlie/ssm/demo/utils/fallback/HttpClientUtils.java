package com.charlie.ssm.demo.utils.fallback;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class HttpClientUtils {

    /**
     * 临时文件保存
     */
    private static final String tempFileDir = "D://tempFileDir/";

    private static int connectTimeout = 10000;
    private static int socketTimeout = 10000;


    public static void main(String[] args) {
        testDownFile();
    }

    public static void testDownFile() {
        try {
            String scheme = "http";
            String host = "127.0.0.1:8099/MyJavaWebUtils";
            String path = "/api/files/downFile";
            Map<String, String> params = new HashMap<>();
            params.put("downFilePath", "downFilePath");
            String saveFileName = "saveFileName.jpg";
            downFile(scheme, host, path, params, null, connectTimeout, socketTimeout, tempFileDir, saveFileName);
            System.out.println("下载完毕");
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }
    }

    /**
     * GET请求
     * <p>
     * 不使用代理则传参为空即可
     *
     * @param scheme
     * @param host
     * @param path
     * @param params
     * @param proxy
     * @param connectTimeout
     * @param socketTimeout
     * @return
     * @throws Exception
     */
    public static String get(String scheme, String host, String path, Map<String, String> params,
                             HttpHost proxy, int connectTimeout, int socketTimeout) throws Exception {

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

            //构建请求配置
            RequestConfig requestConfig = initRequestConfig(proxy, connectTimeout, socketTimeout);

            if (requestConfig != null) {
                httpGet.setConfig(requestConfig);
            }

            HttpClient httpClient = HttpClients.custom().build();
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
     * POST请求
     *
     * @param scheme
     * @param host
     * @param path
     * @param params
     * @param proxy
     * @param connectTimeout
     * @param socketTimeout
     * @return
     */
    public static String post(String scheme, String host, String path, Map<String, String> params,
                              HttpHost proxy, int connectTimeout, int socketTimeout) {
        URIBuilder builder = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path);

        Map<String, String> trimmedParams = new HashMap<>();
        for (String key : params.keySet()) {
            if (params.get(key) != null) {
                trimmedParams.put(key, params.get(key));
            }
        }
        try {
            URI uri = builder.build();
            RequestBuilder requestBuilder = RequestBuilder.post(uri);
            List<NameValuePair> kvs = new ArrayList<>();
            for (String key : trimmedParams.keySet()) {
                kvs.add(new BasicNameValuePair(key, trimmedParams.get(key)));
            }
            requestBuilder.setEntity(new UrlEncodedFormEntity(kvs, "UTF-8"));

            //构建请求配置
            RequestConfig requestConfig = initRequestConfig(proxy, connectTimeout, socketTimeout);

            HttpUriRequest request = requestBuilder
                    .setConfig(requestConfig)
                    .build();

            HttpClient httpClient = HttpClients.custom()
                    .build();
            HttpResponse resp = httpClient.execute(request);
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
     * 构建请求的配置
     *
     * @param proxy
     * @param connectTimeout
     * @param socketTimeout
     * @return
     */
    private static RequestConfig initRequestConfig(HttpHost proxy, int connectTimeout, int socketTimeout) {
        //请求构建器
        //请求配置构建器
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        RequestConfig defaultRequestConfig = null;
        if (proxy != null) {
            //设置代理
            requestConfigBuilder.setProxy(proxy);
        }
        if (connectTimeout > 0) {
            //设置超时
            requestConfigBuilder.setConnectionRequestTimeout(connectTimeout);
        }
        if (socketTimeout > 0) {
            //建立连接超时
            requestConfigBuilder.setSocketTimeout(socketTimeout);
        }
        defaultRequestConfig = requestConfigBuilder.build();
        return defaultRequestConfig;
    }


    /**
     * 下载文件
     *
     * @param scheme
     * @param host
     * @param path
     * @param params
     * @param proxy
     * @param connectTimeout
     * @param socketTimeout
     * @param savePath       文件保存路径
     * @param saveFileName   文件保存名称
     */
    public static void downFile(String scheme, String host, String path, Map<String, String> params,
                                HttpHost proxy, int connectTimeout, int socketTimeout,
                                String savePath, String saveFileName) {
        OutputStream out = null;
        InputStream in = null;

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
            //构建请求配置
            RequestConfig requestConfig = initRequestConfig(proxy, connectTimeout, socketTimeout);

            HttpClientBuilder httpClientBuilder = HttpClients.custom();
            if (requestConfig != null) {
                //设置请求配置
                httpClientBuilder.setDefaultRequestConfig(requestConfig);
            }
            HttpClient httpClient = httpClientBuilder.build();

            HttpResponse resp = httpClient.execute(httpGet);
            if (resp.getStatusLine().getStatusCode() >= 300) {
                throw new RuntimeException("Something wrong: " + resp.getStatusLine().toString());
            }
            HttpEntity entity = resp.getEntity();
            //读取输入流
            in = entity.getContent();
            long length = entity.getContentLength();

            //临时保存文件的目录
            File dir = new File(tempFileDir);
            if (!dir.exists()) {
                //创建文件夹
                dir.mkdir();
            }

            File file = new File(savePath + saveFileName);
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
            throw new RuntimeException(e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * 获取下载文件
     *
     * @param scheme
     * @param host
     * @param path
     * @param proxy
     * @param connectTimeout
     * @param socketTimeout
     * @param tempFileDir
     * @param saveFileName
     * @return
     */
    public static File getDownFile(String scheme, String host, String path, Map<String, String> params,
                                   HttpHost proxy, int connectTimeout, int socketTimeout,Cookie cookie,
                                   String tempFileDir, String saveFileName) {

        OutputStream out = null;
        InputStream in = null;

        URIBuilder builder = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path);

        for (String key : params.keySet()) {
            builder.setParameter(key, params.get(key).toString());
        }

        File file = null;
        try {
            URI uri = builder.build();

            HttpGet httpGet = new HttpGet(uri);

            //构建请求配置
            RequestConfig requestConfig = initRequestConfig(proxy, connectTimeout, socketTimeout);
            if (requestConfig != null) {
                httpGet.setConfig(requestConfig);
            }

            HttpClient httpClient = HttpClients.custom()
                    .build();


            HttpResponse resp = httpClient.execute(httpGet);
            if (resp.getStatusLine().getStatusCode() >= 300) {
                throw new RuntimeException("Something wrong: " + resp.getStatusLine().toString());
            }
            HttpEntity entity = resp.getEntity();
            //读取输入流
            in = entity.getContent();

            //临时保存文件的目录
            File dir = new File(tempFileDir);
            if (!dir.exists()) {
                //创建文件夹
                dir.mkdir();
            }
            long length = entity.getContentLength();
            file = new File(tempFileDir + saveFileName);
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
            throw new RuntimeException(e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return file;
    }


}
