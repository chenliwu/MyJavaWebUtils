package com.charlie.ssm.demo.jaxb.icbc.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.*;

/**
 * Created by chenlw on 2019/07/10  9:47.
 */
@Data
@Accessors(chain = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "pub")
@XmlType(propOrder = {"TransCode","CIS","BankCode","ID","TranDate","TranTime","fSeqno"})
public class ICBCPub {


    @XmlElement
    private String TransCode;

    @XmlElement
    private String CIS;

    @XmlElement
    private String BankCode;

    @XmlElement
    private String ID;

    @XmlElement
    private String TranDate;

    @XmlElement
    private String TranTime;

    @XmlElement
    private String fSeqno;

}
