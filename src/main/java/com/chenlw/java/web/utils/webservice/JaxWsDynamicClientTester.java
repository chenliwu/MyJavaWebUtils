package com.chenlw.java.web.utils.webservice;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.xml.namespace.QName;

/**
 * Webservice客户端调用
 *
 * @author chenlw
 * @date 2019/09/25
 */
public class JaxWsDynamicClientTester {

    public static final String jwsServiceUrl = "http://192.168.0.193:9090/Service/ServiceHello?wsdl";

    public static void main(String[] args) {
        try {
            //test1();

            test2();
        } catch (Exception e) {
            System.out.println("错误：" + e.getMessage());
        }

    }

    public static void test1() {
        try {
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
            org.apache.cxf.endpoint.Client client = dcf.createClient(jwsServiceUrl);
            // url为调用webService的wsdl地址
            QName name = new QName("http://webservice.chenlw.com/", "getValue");
            // namespace是命名空间，methodName是方法名
            String param = "chenlw";
            Object[] objects;
            objects = client.invoke(name, param);
            if (objects.length > 0) {
                Object result = objects[0].toString();
                System.out.println(result);
            } else {
                System.out.println("no result");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e.getMessage());
        }
    }

    /**
     * CXF调用webservice接口
     */
    public static void test2() {
        JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client = clientFactory.createClient(jwsServiceUrl);
        try {
            String methodName = "getValue";
            String param = "chenlw";
            Object[] result = client.invoke(methodName, param);
            System.out.println(result[0]);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("调用webservice成功!");
    }

}
