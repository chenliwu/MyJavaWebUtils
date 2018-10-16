package com.charlie.ssm.demo.utils;

import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpUtils {


    private static String host = "127.0.0.1:8099/MySSM";

    private static String scheme_http= "http";

    private static String scheme_https= "https";

    private static HttpClient httpClient;

    private static OutputStream out = null;
    private static InputStream in = null;

    public static void main(String[] args) {
        //test1();
        test2();
    }

    public static void test1() {
        try {
            String path = "/api/user/queryOne";
            Map<String, String> params = new HashedMap();
            params.put("username", "chenlw");
            params.put("password", "chenlw");
            String result = get(path, params);
            System.out.println("result:" + result);
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }

    }


    public static void test2() {
        try {
            String path = "/api/user/queryOne1";
            Map<String, String> params = new HashedMap();
            params.put("username", "chenlw");
            params.put("password", "chenlw");
            String result = post(path, params);
            System.out.println("result:" + result);
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }

    }




    static {
        //初始化httpclient
        httpClient = HttpClients.custom().build();
    }

    /**
     * 发送GET请求
     *
     * @param path
     * @param params
     * @return
     */
    public static String get(String path, Map<String, String> params) {
        //String basePath = "/v1";
        URIBuilder builder = new URIBuilder()
                .setScheme(scheme_http)
                .setHost(host)
                .setPath(path);

        for (String key : params.keySet()) {
            builder.setParameter(key, params.get(key).toString());
        }
        try {

            URI uri = builder.build();

            HttpGet httpGet = new HttpGet(uri);
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
     * @param path
     * @param params
     * @return
     */
    public static String post(String path, Map<String, String> params) {
        //String basePath = "/v1";
        URIBuilder builder = new URIBuilder().setScheme(scheme_http)
                .setHost(host)
                .setPath(path);
        // clear the params with empty value
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
            HttpUriRequest request = requestBuilder.build();
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
     * 下载文件
     * @param path
     * @param params
     * @param localFileName
     */
    public static void downFile(String path, Map<String, String> params, String localFileName) {
        //String basePath = "/v1";
        URIBuilder builder = new URIBuilder().setScheme(scheme_http)
                .setHost(host)
                .setPath(path);

        for (String key : params.keySet()) {
            builder.setParameter(key, params.get(key).toString());
        }

        try {
            URI uri = builder.build();

            HttpGet httpGet = new HttpGet(uri);
            HttpResponse resp = httpClient.execute(httpGet);
            if (resp.getStatusLine().getStatusCode() >= 300) {
                throw new RuntimeException("Something wrong: " + resp.getStatusLine().toString());
            }
            HttpEntity entity = resp.getEntity();
            in = entity.getContent();

            long length = entity.getContentLength();

            File file = new File(localFileName);
            if (!file.exists()) {
                file.createNewFile();
            }

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


}
