package com.charlie.ssm.demo.bank.icbc.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by chenlw on 2019/07/10  9:18.
 */
@Data
@Accessors(chain = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CMS")
public class ICBCQueryTodayBalanceCms implements Serializable{

    @XmlElement(name = "eb")
    private ICBCQueryTodayBalanceEb eb;

}
