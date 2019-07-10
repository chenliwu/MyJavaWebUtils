package com.charlie.ssm.demo.bank.icbc;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * 工商银行-多账号余额查询
 * created by chenlw on 2019/07/10
 */
@Data
@Accessors(chain = true)
@JacksonXmlRootElement(localName = "CMS")
public class ICBCMultipleAccQueryBalanceInput {

    private Eb eb;

    @Data
    public static class Eb {
        private Pub pub;
        private Out out;
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
        /**
         * 交易返回码
         */
        @JacksonXmlProperty(localName = "RetCode")
        private String retCode;
        /**
         * 交易返回描述
         */
        @JacksonXmlProperty(localName = "RetMsg")
        private String retMsg;
    }

    @Data
    public static class Out {
        /**
         * 交易条数
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
        @JacksonXmlProperty(localName = "RepReserved2")
        private String repReserved2;
        /**
         * 返回集合
         */
        @JacksonXmlElementWrapper(useWrapping = false)
        private Collection<Rd> rd;
    }

    @Data
    public static class Rd {
        /**
         * 借贷标志
         */
        @JacksonXmlProperty(localName = "Drcrf")
        private String drcrf;
        /**
         * 凭证号
         */
        @JacksonXmlProperty(localName = "VouhNo")
        private String vouhNo;
        /**
         * 借方发生额
         */
        @JacksonXmlProperty(localName = "DebitAmount")
        private BigDecimal debitAmount;
        /**
         * 贷方发生额
         */
        @JacksonXmlProperty(localName = "CreditAmount")
        private BigDecimal creditAmount;
        /**
         * 余额
         */
        @JacksonXmlProperty(localName = "Balance")
        private BigDecimal balance;
        /**
         * 对方行号
         */
        @JacksonXmlProperty(localName = "RecipBkNo")
        private String recipBkNo;
        /**
         * 对方账号
         */
        @JacksonXmlProperty(localName = "RecipBkName")
        private String recipBkName;
        /**
         * 对方账号
         */
        @JacksonXmlProperty(localName = "RecipAccNo")
        private String recipAccNo;
        /**
         * 对方户名
         */
        @JacksonXmlProperty(localName = "RecipName")
        private String recipName;
        /**
         * 摘要
         */
        @JacksonXmlProperty(localName = "Summary")
        private String summary;
        /**
         * 用途
         */
        @JacksonXmlProperty(localName = "UseCN")
        private String useCN;
        /**
         * 附言
         */
        @JacksonXmlProperty(localName = "PostScript")
        private String postScript;
        /**
         * 业务代码
         */
        @JacksonXmlProperty(localName = "BusCode")
        private String busCode;
        /**
         * 交易日期
         */
        @JacksonXmlProperty(localName = "Date")
        private String date;
        /**
         * 时间戳
         */
        @JacksonXmlProperty(localName = "Time")
        private String time;
        /**
         * 业务编号
         */
        @JacksonXmlProperty(localName = "Ref")
        private String ref;
        /**
         * 相关业务编号
         */
        @JacksonXmlProperty(localName = "Oref")
        private String oref;
        /**
         * 英文备注
         */
        @JacksonXmlProperty(localName = "EnSummary")
        private String enSummary;
        /**
         * 业务种类
         */
        @JacksonXmlProperty(localName = "BusType")
        private String busType;
        /**
         * 凭证种类
         */
        @JacksonXmlProperty(localName = "VouhType")
        private String vouhType;
        /**
         * 附加信息
         */
        @JacksonXmlProperty(localName = "AddInfo")
        private String addInfo;
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
