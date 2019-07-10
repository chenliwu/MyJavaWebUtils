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
//@XmlRootElement(name = "in")
@XmlType(propOrder = {"AccNo", "AreaCode", "MinAmt", "MaxAmt", "BeginTime", "EndTime", "NextTag", "ReqReserved1", "ReqReserved2"})
public class ICBCQueryTodayBalanceIn {

    private String AccNo;

    private String AreaCode;

    private String MinAmt;

    private String MaxAmt;

    private String BeginTime;

    private String EndTime;

    private String NextTag;

    private String ReqReserved1;

    private String ReqReserved2;


}
