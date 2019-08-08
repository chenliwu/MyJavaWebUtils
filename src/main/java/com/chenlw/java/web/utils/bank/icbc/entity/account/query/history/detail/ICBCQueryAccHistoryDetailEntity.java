package com.chenlw.java.web.utils.bank.icbc.entity.account.query.history.detail;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.io.IOException;

/**
 * Created by chenlw on 2019/07/10  14:08.
 */
@Data
@JacksonXmlRootElement(localName = "CMS")
public class ICBCQueryAccHistoryDetailEntity {


    public static void main(String[] args) throws IOException {
        try{
           // testBeanToXml();
            testXmlToBean();
        }catch (Exception e){
            System.out.println("错误："+e.getMessage());
        }
    }

    public static void testBeanToXml() throws Exception{

        ICBCQueryAccHistoryDetailEntity icbcQueryAccHistoryDetailEntity = new ICBCQueryAccHistoryDetailEntity();
        ICBCQueryAccHistoryDetailEntity.Eb eb = new ICBCQueryAccHistoryDetailEntity.Eb();

        Pub pub = new Pub();
        pub.setTransCode("交易代码");
        pub.setCIS("集团CIS号");
        pub.setBankCode("归属银行编号");
        pub.setID("证书ID");
        pub.setTranDate("交易日期");
        pub.setTranTime("交易时间");
        pub.setFSeqno("指令包序列号");


        ICBCQueryAccHistoryDetailEntity.In in = new In();

        in.setAccNo("查询账号");
        in.setBeginDate("起始日期");
        in.setEndDate("截止日期");
        in.setMinAmt("发生额下限");
        in.setMaxAmt("发生额上限");
        in.setNextTag("查询下页标识");

        eb.setPub(pub);
        eb.setIn(in);
        icbcQueryAccHistoryDetailEntity.setEb(eb);

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String s = xmlMapper.writeValueAsString(icbcQueryAccHistoryDetailEntity);
        System.out.println(s);

    }

    public static void testXmlToBean() throws Exception {
        String xml = "<?xml version=\"1.0\" encoding=\"GBK\" ?>\n" +
                "<CMS>\n" +
                "    <eb>\n" +
                "        <pub>\n" +
                "            <TransCode>交易代码</TransCode>\n" +
                "            <CIS>集团CIS号</CIS>\n" +
                "            <BankCode>归属银行编号</BankCode>\n" +
                "            <ID>证书ID</ID>\n" +
                "            <TranDate>交易日期</TranDate>\n" +
                "            <TranTime>交易时间</TranTime>\n" +
                "            <fSeqno>指令包序列号</fSeqno>\n" +
                "        </pub>\n" +
                "        <in>\n" +
                "            <AccNo>查询账号</AccNo>\n" +
                "            <BeginDate>起始日期</BeginDate>\n" +
                "            <EndDate>截止日期</EndDate>\n" +
                "            <MinAmt>发生额下限</MinAmt>\n" +
                "            <MaxAmt>发生额上限</MaxAmt>\n" +
                "            <NextTag>查询下页标识</NextTag>\n" +
                "        </in>\n" +
                "    </eb>\n" +
                "</CMS>";

        XmlMapper xmlMapper = new XmlMapper();
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            xmlMapper.readValue(xml,ICBCQueryAccHistoryDetailEntity.class);
        }
        System.out.println("XML转Bean耗时:" + (System.currentTimeMillis() - beginTime));

    }


    @JacksonXmlProperty(localName = "eb")
    private Eb eb;

    @Data
    public static class Eb {

        @JacksonXmlProperty(localName = "pub")
        private Pub pub;

        @JacksonXmlProperty(localName = "in")
        private In in;

    }

    @Data
    public static class Pub{
        /**
         * 交易代码
         */
        @JacksonXmlProperty(localName = "TransCode")
        private String transCode;

        /**
         * 集团CIS号
         */
        @JacksonXmlProperty(localName = "CIS")
        private String cIS;

        /**
         * 归属银行编号
         */
        @JacksonXmlProperty(localName = "BankCode")
        private String bankCode;

        /**
         * 证书ID
         */
        @JacksonXmlProperty(localName = "ID")
        private String iD;

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
        private String fSeqno;
    }

    @Data
    public static class In {

        /**
         * 查询账号
         */
        @JacksonXmlProperty(localName = "AccNo")
        private String accNo;

        /**
         * 起始日期
         */
        @JacksonXmlProperty(localName = "BeginDate")
        private String beginDate;

        /**
         * 截止日期
         */
        @JacksonXmlProperty(localName = "EndDate")
        private String endDate;

        /**
         * 发生额下限
         */
        @JacksonXmlProperty(localName = "MinAmt")
        private String minAmt;

        /**
         * 发生额上限
         */
        @JacksonXmlProperty(localName = "MaxAmt")
        private String maxAmt;

        /**
         * 查询下页标识
         */
        @JacksonXmlProperty(localName = "NextTag")
        private String nextTag;

        /**
         * 请求包备用字段1
         */
        @JacksonXmlProperty(localName = "ReqReserved1")
        private String reqReserved1;

        /**
         * 请求包备用字段2
         */
        @JacksonXmlProperty(localName = "ReqReserved2")
        private String reqReserved2;
    }

}
