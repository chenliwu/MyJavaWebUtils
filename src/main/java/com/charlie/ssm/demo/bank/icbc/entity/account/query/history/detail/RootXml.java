package com.charlie.ssm.demo.bank.icbc.entity.account.query.history.detail;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.io.IOException;

@Data
@JacksonXmlRootElement(localName = "CMS")
public class RootXml {
    public static void main(String[] args) throws IOException {
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
                "            <AreaCode>地区代码</AreaCode>\n" +
                "            <MinAmt>发生额下限</MinAmt>\n" +
                "            <MaxAmt>发生额上限</MaxAmt>\n" +
                "            <BeginTime>开始时间</BeginTime>\n" +
                "            <EndTime>终止时间</EndTime>\n" +
                "            <NextTag>查询下页标识</NextTag>\n" +
                "        </in>\n" +
                "    </eb>\n" +
                "</CMS>";
        XmlMapper xmlMapper = new XmlMapper();
        //XML转Bean
        RootXml rootXml = xmlMapper.readValue(xml, RootXml.class);
        System.out.println(rootXml);
        //bean转xml
        String s = xmlMapper.writeValueAsString(rootXml);
        System.out.println(s);
    }

    @JacksonXmlProperty(localName = "eb")
    private Eb eb;

    @Data
    public static class Eb {
        private Pub pub;
        private In in;
    }

    @Data
    public static class Pub {
        @JacksonXmlProperty(localName = "TransCode")
        private String transCode;
        @JacksonXmlProperty(localName = "CIS")
        private String cis;
        @JacksonXmlProperty(localName = "BankCode")
        private String bankCode;
        @JacksonXmlProperty(localName = "ID")
        private String id;
        @JacksonXmlProperty(localName = "TranDate")
        private String tranDate;
        @JacksonXmlProperty(localName = "TranTime")
        private String tranTime;
        @JacksonXmlProperty(localName = "fSeqno")
        private String sequence;
    }

    @Data
    public static class In {
        @JacksonXmlProperty(localName = "AccNo")
        private String accountNo;
        @JacksonXmlProperty(localName = "AreaCode")
        private String areaCode;
        @JacksonXmlProperty(localName = "MinAmt")
        private String minAmount;
        @JacksonXmlProperty(localName = "MaxAmt")
        private String maxAmount;
        @JacksonXmlProperty(localName = "BeginTime")
        private String beginTime;
        @JacksonXmlProperty(localName = "EndTime")
        private String endTime;
        @JacksonXmlProperty(localName = "NextTag")
        private String nextTag;
    }

}
