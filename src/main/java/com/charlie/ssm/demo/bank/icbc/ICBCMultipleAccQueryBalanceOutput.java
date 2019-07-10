package com.charlie.ssm.demo.bank.icbc;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;

/**
 * QACCBAL发送包
 * 工商银行-多账号余额查询
 * 交易代码为：QACCBAL
 * <p>
 *
 * 如果客户提交单笔指令，则银行对该指令实时处理，返回指令级返回包。
 * 如果客户提交多笔指令，则银行返回文件级返回包，由银行后台系统异步处理指令；当客户开通主动反馈功能时，银行处理完毕后将指令级返回包主动发送给客户。
 *
 *
 * created by chenlw on 2019/07/10
 */
@Data
@Accessors(chain = true)
@JacksonXmlRootElement(localName = "CMS")
public class ICBCMultipleAccQueryBalanceOutput {

    private Eb eb;

    @Data
    public static class Eb {
        private Pub pub;
        private In in;
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
         * 客户注册时的归属编码
         */
        @JacksonXmlProperty(localName = "CIS")
        private String cis;
        /**
         * 归属银行编号
         * 客户注册时的归属单位
         */
        @JacksonXmlProperty(localName = "BankCode")
        private String bankCode;
        /**
         * 证书ID
         * 无证书客户可上送空
         */
        @JacksonXmlProperty(localName = "ID")
        private String id;
        /**
         * 交易日期
         * ERP系统产生的交易日期，格式是yyyyMMdd
         */
        @JacksonXmlProperty(localName = "TranDate")
        private String tranDate;
        /**
         * 交易时间
         * ERP系统产生的交易时间，格式如HHmmssSSS，精确到毫秒
         */
        @JacksonXmlProperty(localName = "TranTime")
        private String tranTime;
        /**
         * 指令包序列号
         * ERP系统产生的指令包序列号，一个集团永远不能重复
         */
        @JacksonXmlProperty(localName = "fSeqno")
        private String sequence;
    }

    @Data
    public static class In {

        /**
         * 总笔数
         * 需要查询的账号的个数，即提交包明细的笔数。
         */
        @JacksonXmlProperty(localName = "TotalNum")
        private Long totalNum;

        /**
         * 响应备用字段1
         * 备用，目前无意义
         */
        @JacksonXmlProperty(localName = "RepReserved1")
        private String repReserved1;

        /**
         * 响应备用字段2
         * 备用，目前无意义
         */
        @JacksonXmlProperty(localName = "ReqReserved2")
        private String repReserved2;

        @JacksonXmlElementWrapper(localName = "rd", useWrapping = false)
        private Collection<Rd> rd;

    }

    @Data
    public static class Rd {

        /**
         * 指令顺序号
         * 不能为空，并且不能重复
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
         * 工行币种代码,不输入则自动取账号币种
         */
        @JacksonXmlProperty(localName = "CurrType")
        private String currType;


        /**
         * 响应备用字段3
         * 行别,集团有他行账号时为必输项（输入102或不输入为工行，其他为他行）
         */
        @JacksonXmlProperty(localName = "RepReserved3")
        private String repReserved3;

        /**
         * 响应备用字段4
         * 备用，目前无意义
         */
        @JacksonXmlProperty(localName = "RepReserved4")
        private String repReserved4;
    }

}
