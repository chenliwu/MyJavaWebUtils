package com.chenlw.java.web.utils.xml;

import com.chenlw.java.web.utils.utils.FileUtils;
import com.chenlw.java.web.utils.utils.JackSonXmUtils;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author chenlw 2019/08/09
 */
@Data
@Accessors(chain = true)
public class JacksonXmlTester {

    public static void main(String[] args) {
        try {
            // testXmlParse();
            testXmlFileContentToObj();
        } catch (Exception e) {
            System.out.println("异常信息：" + e.getMessage());
        }
    }

    /**
     * 测试对象转化成XML
     *
     * @throws Exception
     */
    public static void testXmlParse() throws Exception {
        AreaData areaData = new AreaData();
        List<AreaData.Rd> rdList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AreaData.Rd rd = new AreaData.Rd();
            rd.setBank("bank" + i);
            rd.setBytter("bytter" + i);
            rdList.add(rd);
        }
        areaData.setRd(rdList);
        String xml = JackSonXmUtils.beanToXml(areaData);
        System.out.println(xml);
    }

    /**
     * 测试XML文件内容转化成对象
     */
    public static void testXmlFileContentToObj() throws Exception {
        String filePath = "AreaCode.xml";
        String data = FileUtils.readFileContentByLine(filePath);
        System.out.println(data);
        XmlMapper xmlMapper = new XmlMapper();
        AreaData areaData = xmlMapper.readValue(new File(filePath), AreaData.class);
        System.out.println("\n测试XML文件内容转化成对象");
        System.out.println(JackSonXmUtils.beanToXml(areaData));
    }


    @Data
    @Accessors(chain = true)
    @JacksonXmlRootElement(localName = "data")
    // @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AreaData {

        @JacksonXmlElementWrapper(localName = "rd", useWrapping = false)
        private Collection<Rd> rd;

        @Data
        @Accessors(chain = true)
        public static class Rd {
            @JacksonXmlProperty(localName = "bank")
            private String bank;

            @JacksonXmlProperty(localName = "bytter")
            private String bytter;
        }


    }


}
