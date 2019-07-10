package com.charlie.ssm.demo.bank.icbc;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 工商银行-查询历史明细
 *
 * @author zuoy 2019-07-10
 */
@Data
@Accessors(chain = true)
@JacksonXmlRootElement(localName = "CMS")
public class ICBCMultipleAccQueryBalanceOutput {

    public static void main(String[]args){
        try {
            test1();
        }catch (Exception e){
            System.out.println("error:"+e.getMessage());
        }
    }

    public static void test1() throws Exception{
        ICBCMultipleAccQueryBalanceOutput multipleAccQueryBalanceOutput = new ICBCMultipleAccQueryBalanceOutput();
        Eb eb = new Eb();
        Pub pub = new Pub();
        pub.setTransCode("交易代码");
        pub.setCis("集团CIS号");
        pub.setBankCode("归属银行编号");
        pub.setId("证书ID");
        pub.setTranDate("交易日期");
        pub.setTranTime("交易时间");
        pub.setSequence("指令包序列号");

        List<Rd> rdList = new ArrayList<>();
        In in = new In();
        in.setTotalNum(6L);
        Rd rd = new Rd();
        rd.setISeqno("指令顺序号");
        rd.setAccNo("账号");
        rd.setCurrType("RMB");
        rdList.add(rd);
        in.setRd(rdList);

        eb.setPub(pub);
        eb.setIn(in);
        multipleAccQueryBalanceOutput.setEb(eb);

        XmlMapper xmlMapper = new XmlMapper();
        // 格式化
       xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        // 设置xml头
//        xmlMapper.enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);
        //字段为null，自动忽略，不再序列化
//        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //XML标签名:使用骆驼命名的属性名，
//        xmlMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        //设置转换模式
//        xmlMapper.enable(MapperFeature.USE_STD_BEAN_NAMING);
        String xml = xmlMapper.writeValueAsString(multipleAccQueryBalanceOutput);
        System.out.println(xml);
    }

    private Eb eb;

    @Data
    public static class Eb {
        private Pub pub;
        private In in;
    }

    @Data
    public static class Pub {
        /**
         * 交易代码
         */
        @JacksonXmlProperty(localName = "TransCode")
        private String transCode;
        /**
         * 集团CIS号
         */
        @JacksonXmlProperty(localName = "CIS")
        private String cis;
        /**
         * 归属银行编号
         */
        @JacksonXmlProperty(localName = "BankCode")
        private String bankCode;
        /**
         * 证书ID
         */
        @JacksonXmlProperty(localName = "ID")
        private String id;
        /**
         * 交易日期
         */
        @JacksonXmlProperty(localName = "TranDate")
        private String tranDate;
        /**
         * 交易时间
         */
        @JacksonXmlProperty(localName = "TranTime")
        private String tranTime;
        /**
         * 指令包序列号
         */
        @JacksonXmlProperty(localName = "fSeqno")
        private String sequence;
    }

    @Data
    public static class In {

        /**
         * 总笔数
         */
        @JacksonXmlProperty(localName = "TotalNum")
        private Long totalNum;

        /**
         * 响应备用字段1
         */
        @JacksonXmlProperty(localName = "RepReserved1")
        private String repReserved1;

        /**
         * 响应备用字段2
         */
        @JacksonXmlProperty(localName = "ReqReserved2")
        private String repReserved2;

        @JacksonXmlElementWrapper(localName = "rd",useWrapping = false)
        private Collection<Rd> rd;

    }

    @Data
    public static class Rd {

        /**
         * 指令顺序号
         */
        @JacksonXmlProperty(localName = "iSeqno")
        private String iSeqno;

        /**
         * 账号
         */
        @JacksonXmlProperty(localName = "AccNo")
        private String accNo;

        /**
         * 币种
         */
        @JacksonXmlProperty(localName = "CurrType")
        private String currType;


        /**
         * 响应备用字段3
         */
        @JacksonXmlProperty(localName = "RepReserved3")
        private String repReserved3;

        /**
         * 响应备用字段4
         */
        @JacksonXmlProperty(localName = "RepReserved4")
        private String repReserved4;
    }

}
