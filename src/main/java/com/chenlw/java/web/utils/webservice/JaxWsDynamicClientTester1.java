package com.chenlw.java.web.utils.webservice;

import net.sf.json.JSONObject;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * Webservice客户端调用
 *
 * @author chenlw
 * @date 2019/09/25
 */
public class JaxWsDynamicClientTester1 {

    public static final String jwsServiceUrl = "http://10.0.36.240:9000/soap/webservice/authentication?wsdl";

    public static void main(String[] args) {
        try {
            test1();
        } catch (Exception e) {
            System.out.println("错误：" + e.getMessage());
        }

    }

    /**
     * CXF调用webservice接口
     */
    public static void test1() throws Exception {
        try {
            JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
            Client client = clientFactory.createClient(jwsServiceUrl);
            String methodName = "authentication";
            String ticket = "18627da2-1f40-48da-87b5-c4f4dec31089";

            // 添加HTTP拦截器，填充Header
            HttpHeaderInterceptor httpHeaderInterceptor = new HttpHeaderInterceptor(ticket);
            client.getOutInterceptors().add(httpHeaderInterceptor);

            // 调用webservice请求
            String userid = "100857";
            // String password = "ff9uYf9s+K8HQQgepAbCROtrq/QU0oWfsvtYLP2bjHqeZ20YfNb6tTlhNeL/aF+mgY1Xrsnl7TxyvsLNZwP0lg==";
            String password = "ia04agZTT0n1TZoiDUivNVqdk3m6RPjJJl9a/izRvt3Swp4RXihgrnbqrguFIrx+LwqoG+QOGRW3TyP3eNdqDg==";
            Object[] result = client.invoke(methodName, userid, password);

            if (result == null || result.length == 0) {
                throw new Exception("WebService接口没有数据返回！");
            }

            // 输出结果，结果返回的是一个对象，将其转化成JSON对象，再读取里面数据。这样比较方便
            // {"code":0,"data":"3","msg":"成功"}
            JSONObject jsonObject = JSONObject.fromObject(result[0]);
            if (jsonObject == null) {
                throw new Exception("转化WebService接口返回数据失败！");
            }
            System.out.println(jsonObject.toString());
            System.out.println("code:" + jsonObject.getInt("code"));
            System.out.println("data:" + jsonObject.getString("data"));
            System.out.println("msg:" + jsonObject.getString("msg"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
        System.out.println("调用webservice成功!");
    }

    /**
     * HTTP  header拦截器
     * 用于填充HTTP 请求的Header信息
     */
    public static class HttpHeaderInterceptor extends AbstractPhaseInterceptor<Message> {

        private String ticket;

        public HttpHeaderInterceptor(String ticket) {
            super(Phase.POST_PROTOCOL);
            this.ticket = ticket;
        }

        @Override
        public void handleMessage(Message message) throws Fault {
            Map<String, List> headers = (Map<String, List>) message.get(Message.PROTOCOL_HEADERS);
            try {
                // System.out.println("HttpHeaderInterceptor ticket: " + ticket);
                // 填充Ticket到Headers当中
                headers.put("ticket", Collections.singletonList(ticket));
            } catch (Exception ce) {
                throw new Fault(ce);
            }
        }


        public String getTicket() {
            return ticket;
        }

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }
    }


}
