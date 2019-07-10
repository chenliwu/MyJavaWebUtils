package com.charlie.ssm.demo.jaxb.icbc.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.*;

/**
 * Created by chenlw on 2019/07/10  9:19.
 */
@Data
@Accessors(chain = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "eb")
@XmlSeeAlso({ICBCPub.class,ICBCTodayBalance.class})
public class ICBCEb {

    @XmlElement(name = "pub")
    private ICBCPub pub;

    @XmlElement(name = "in")
    private Object in;

}
