package com.charlie.ssm.demo.bank.icbc.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.*;

/**
 * Created by chenlw on 2019/07/10  9:19.
 */
@Data
@Accessors(chain = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"pub", "in"})
public class ICBCQueryTodayBalanceEb {

    @XmlElement(name = "pub")
    private ICBCOrderPub pub;

    @XmlElement(name = "in")
    private ICBCQueryTodayBalanceIn in;

}
