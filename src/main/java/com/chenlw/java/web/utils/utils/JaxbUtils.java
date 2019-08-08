package com.chenlw.java.web.utils.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.concurrent.ConcurrentHashMap;

/**
 * JAXB帮助类
 *
 * @author zuoy 2019-06-13
 */
public class JaxbUtils {

    /**
     * JAXBContext缓存
     */
    private static ConcurrentHashMap<Object, JAXBContext> jaxbContextMap = new ConcurrentHashMap<>();

    public static JAXBContext newJAXBContext(Class... cls) {
        JAXBContext jaxbContext = jaxbContextMap.get(cls);
        if (jaxbContext == null) {
            // 如果每次都调用JAXBContext.newInstance方法，会导致性能急剧下降
            try {
                jaxbContext = JAXBContext.newInstance(cls);
                jaxbContextMap.put(cls, jaxbContext);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
        return jaxbContext;
    }

    /**
     * 对象转xml
     *
     * @param obj 对象
     * @return
     */
    public static String marshaller(Object obj, String encode) {
        return marshaller(obj, encode, true, false, false);
    }

    /**
     * 对象转xml
     *
     * @param obj            对象
     * @param encode         编码格式
     * @param hideStandalone 是否隐藏xml头声明的hideStandalone属性
     * @return
     */
    public static String marshaller(Object obj, String encode, boolean hideStandalone) {
        return marshaller(obj, encode, true, hideStandalone ? true : false, hideStandalone);
    }

    /**
     * 对象转xml
     *
     * @param obj            对象
     * @param encode         编码格式
     * @param format         是否格式化生成的xml串
     * @param fragment       是否省略xml头声明信息
     * @param hideStandalone 是否隐藏xml头声明的hideStandalone属性，为true时，fragment参数无效
     * @return
     */
    public static String marshaller(Object obj, String encode, boolean format, boolean fragment, boolean hideStandalone) {
        try {
            JAXBContext context = newJAXBContext(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encode);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, format);
            StringWriter writer = new StringWriter();
            // 是否隐藏xml头声明的hideStandalone属性
            if (hideStandalone) {
                // 1) 隐去报文头的生成, Marshaller.JAXB_FRAGMENT默认为false
                marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
                // 2) 自定义生成
                //writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
                String str = String.format("<?xml version=\"1.0\" encoding=\"%s\" ?>\n",encode);
                writer.write(str);
            } else {
                marshaller.setProperty(Marshaller.JAXB_FRAGMENT, fragment);
            }
            marshaller.marshal(obj, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * xml转对象
     *
     * @param cls  转换类
     * @param file 转换路径
     * @param <T>
     * @return
     */
    public static <T> T unmarshaller(File file, Class... cls) {
        try {
            JAXBContext context = newJAXBContext(cls);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * xml转对象
     *
     * @param cls 转换类
     * @param xml 转换xml文件
     * @param <T>
     * @return
     */
    public static <T> T unmarshaller(Class<T> cls, String xml) {
        try {
            JAXBContext context = newJAXBContext(cls);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * xml转对象
     *
     * @param cls 转换类
     * @param in  xml流
     * @param <T>
     * @return
     */
    public static <T> T unmarshaller(Class cls, InputStream in) {
        try {
            JAXBContext context = newJAXBContext(cls);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(in);
        } catch (JAXBException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
