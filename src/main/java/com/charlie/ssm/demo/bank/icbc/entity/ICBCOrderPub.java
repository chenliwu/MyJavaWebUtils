package com.charlie.ssm.demo.bank.icbc.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by chenlw on 2019/07/10  9:47.
 */
@Data
@Accessors(chain = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"TransCode", "CIS", "BankCode", "ID", "TranDate", "TranTime", "fSeqno"})
public class ICBCOrderPub {

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
