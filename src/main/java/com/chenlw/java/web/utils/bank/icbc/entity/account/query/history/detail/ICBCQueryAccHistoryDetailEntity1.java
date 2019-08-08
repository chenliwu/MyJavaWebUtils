package com.chenlw.java.web.utils.bank.icbc.entity.account.query.history.detail;

import com.chenlw.java.web.utils.utils.JaxbUtils;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.*;
import java.io.IOException;

/**
 * Created by chenlw on 2019/07/10  14:08.
 */
@Data
@Accessors(chain = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CMS")
public class ICBCQueryAccHistoryDetailEntity1 {

    private static final String XML_GBK_ENCODE = "GBK";

    public static void main(String[] args) throws IOException {
        try {
            //testBeanToXml();
            testXmlToBean();
        } catch (Exception e) {
            System.out.println("错误：" + e.getMessage());
        }
    }

    public static void testBeanToXml() throws Exception {

        ICBCQueryAccHistoryDetailEntity1 icbcQueryAccHistoryDetailEntity = new ICBCQueryAccHistoryDetailEntity1();
        Eb eb = new Eb();


        Pub pub = new Pub();
        pub.setTransCode("交易代码");
        pub.setCIS("集团CIS号");
        pub.setBankCode("归属银行编号");
        pub.setID("证书ID");
        pub.setTranDate("交易日期");
        pub.setTranTime("交易时间");
        pub.setFSeqno("指令包序列号");

        In in = new In();

        in.setAccNo("查询账号");
        in.setBeginDate("起始日期");
        in.setEndDate("截止日期");
        in.setMinAmt("发生额下限");
        in.setMaxAmt("发生额上限");
        in.setNextTag("查询下页标识");

        eb.setPub(pub);
        eb.setIn(in);
        icbcQueryAccHistoryDetailEntity.setEb(eb);

        String xml = JaxbUtils.marshaller(icbcQueryAccHistoryDetailEntity, XML_GBK_ENCODE, true);
        System.out.println(xml);

    }

    public static void testXmlToBean() {
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
        ICBCQueryAccHistoryDetailEntity1 entity1 = JaxbUtils.unmarshaller(ICBCQueryAccHistoryDetailEntity1.class, xml);
        System.out.println();
        System.out.println(entity1.toString());

        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            JaxbUtils.unmarshaller(ICBCQueryAccHistoryDetailEntity1.class, xml);
        }
        System.out.println("XML转Bean耗时:" + (System.currentTimeMillis() - beginTime));

    }


    @XmlElement(name = "eb")
    private Eb eb;

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"pub", "in"})
    public static class Eb {

        @XmlElement(name = "pub")
        private Pub pub;

        @XmlElement(name = "in")
        private In in;

    }


    @Data
    @Accessors(chain = true)
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"TransCode", "CIS", "BankCode", "ID", "TranDate", "TranTime", "fSeqno"})
    public static class Pub {

        /**
         * 交易代码
         */
        private String TransCode;

        /**
         * 集团CIS号
         */
        private String CIS;

        /**
         * 归属银行编号
         */
        private String BankCode;

        /**
         * 证书ID
         */
        private String ID;

        /**
         * 交易日期
         */
        private String TranDate;

        /**
         * 交易时间
         */
        private String TranTime;

        /**
         * 指令包序列号
         */
        private String fSeqno;

    }



    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class In {

        /**
         * 查询账号
         */
        private String AccNo;

        /**
         * 起始日期
         */
        private String BeginDate;

        /**
         * 截止日期
         */
        private String EndDate;

        /**
         * 发生额下限
         */
        private String MinAmt;

        /**
         * 发生额上限
         */
        private String MaxAmt;

        /**
         * 查询下页标识
         */
        private String NextTag;

        /**
         * 请求包备用字段1
         */
        private String ReqReserved1;

        /**
         * 请求包备用字段2
         */
        private String ReqReserved2;
    }

}
