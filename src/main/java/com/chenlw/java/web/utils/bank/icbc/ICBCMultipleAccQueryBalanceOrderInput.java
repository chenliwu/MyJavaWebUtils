package com.chenlw.java.web.utils.bank.icbc;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * 如果客户提交单笔指令，则银行对该指令实时处理，返回指令级返回包。
 * <p>
 * QACCBAL指令级返回包
 * 交易代码为：QACCBAL
 * <p>
 * 工商银行-多账号余额查询
 * created by chenlw on 2019/07/10
 */
@Data
@Accessors(chain = true)
@JacksonXmlRootElement(localName = "CMS")
public class ICBCMultipleAccQueryBalanceOrderInput {

    private Eb eb;

    @Data
    public static class Eb {
        private Pub pub;
        private Out out;
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

    @Data
    public static class Out {
        /**
         * 返回集合
         */
        @JacksonXmlElementWrapper(useWrapping = false)
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
        private Long currType;

        /**
         * 钞汇标志
         * 0-钞；1-汇；2-假汇；3-境外汇款
         */
        @JacksonXmlProperty(localName = "CashExf")
        private Long cashExf;

        /**
         * 账户属性
         * 第3位数字为帐户属性： 1：基本户 2：一般结算户 3：临时户 4：专用户 6：集团二级户 7：协定存款户 8：保证金户
         * 查询帐户为定期户数据字典：0 国有企业1 外企及金融机构
         */
        @JacksonXmlProperty(localName = "AcctProperty")
        private Long acctProperty;

        /**
         * 昨日余额
         */
        @JacksonXmlProperty(localName = "AccBalance")
        private BigDecimal accBalance;

        /**
         * 当前余额
         * 以币种的最小单位为单位
         */
        @JacksonXmlProperty(localName = "Balance")
        private BigDecimal balance;

        /**
         * 可用余额
         * 以币种的最小单位为单位
         */
        @JacksonXmlProperty(localName = "UsableBalance")
        private BigDecimal usableBalance;

        /**
         * 冻结额度合计
         * 以币种的最小单位为单位
         */
        @JacksonXmlProperty(localName = "FrzAmt")
        private BigDecimal frzAmt;

        /**
         * 查询时间
         * yyyyMMddHHmmssssssss
         */
        @JacksonXmlProperty(localName = "QueryTime")
        private Long queryTime;


        /**
         * 明细交易返回码
         */
        @JacksonXmlProperty(localName = "iRetCode")
        private String iRetCode;

        /**
         * 明细交易返回描述
         */
        @JacksonXmlProperty(localName = "iRetMsg")
        private String iRetMsg;

        /**
         * 响应备用字段3
         * 行别
         */
        @JacksonXmlProperty(localName = "RepReserved3")
        private String repReserved3;

        /**
         * 响应备用字段4
         * 启用为“信用额度”；以币种的最小金额为单位
         */
        @JacksonXmlProperty(localName = "RepReserved4")
        private String repReserved4;
    }

}
