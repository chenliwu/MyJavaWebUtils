package com.charlie.ssm.demo.bank.icbc;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 如果客户提交单笔指令，则银行对该指令实时处理，返回指令级返回包。
 * <p>
 * QACCBAL文件级返回包
 * 交易代码为：QACCBAL
 * <p>
 * 工商银行-多账号余额查询
 * created by chenlw on 2019/07/10
 */
@Data
@Accessors(chain = true)
@JacksonXmlRootElement(localName = "CMS")
public class ICBCMultipleAccQueryBalanceFileInput {

    private Eb eb;

    @Data
    public static class Eb {
        private Pub pub;
    }

    @Data
    public static class Pub {
        /**
         * 交易代码：QACCBAL
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
        /**
         * 交易返回码
         * 返回0为查询成功，具体返回信息显示在明细区
         */
        @JacksonXmlProperty(localName = "RetCode")
        private String retCode;
        /**
         * 交易返回描述
         */
        @JacksonXmlProperty(localName = "RetMsg")
        private String retMsg;
    }

}
