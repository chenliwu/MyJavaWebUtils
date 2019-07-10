package com.charlie.ssm.demo.jaxb.icbc.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.*;

/**
 * Created by chenlw on 2019/07/10  9:12.
 */
@Data
@Accessors(chain = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "in")
@XmlType(propOrder = {})
public class ICBCTodayBalance {

    @XmlElement(name = "pub")
    private ICBCPub pub;

    @XmlElement
    private String AccNo;
    @XmlElement
    private String AreaCode;

    @XmlElement
    private String MinAmt;

    @XmlElement
    private String MaxAmt;

    @XmlElement
    private String BeginTime;

    @XmlElement
    private String EndTime;

    @XmlElement
    private String NextTag;

    @XmlElement
    private String ReqReserved1;

    @XmlElement
    private String ReqReserved2;


}
