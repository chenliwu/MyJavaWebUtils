package com.charlie.ssm.demo.jaxb.icbc.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by chenlw on 2019/07/10  9:47.
 */
@Data
@Accessors(chain = true)
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlRootElement(name = "pub")
@XmlType(propOrder = {"TransCode", "CIS", "BankCode", "ID", "TranDate", "TranTime", "fSeqno","testField"})
public class ICBCQueryPub {

    private String TransCode;

    private String CIS;

    private String BankCode;

    private String ID;

    private String TranDate;

    private String TranTime;

    private String fSeqno;

    /**
     * 定义了字段，如果不设置该字段的值，它就不会出现在xml当中。
     */
    private String testField;

}
