package com.chenlw.java.web.utils.webservice;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author chenlw 2019/09/24
 */
public class MyClient {

    public static void main(String args[]) {
        try { URL url = new URL("http://localhost:8888/ms?wsdl");
            //命名空间 及 名称
            QName qName = new QName("http://hello.cxfdemo.com/","MyWebServiceImplService");
            Service service = Service.create(url, qName);
            JwsServiceHello myWebservice = service.getPort(JwsServiceHello.class);
            System.out.println(myWebservice.getValue("12313123"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
