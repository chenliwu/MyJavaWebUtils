package com.chenlw.java.web.utils.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ParamsEntity {

    String param1;
    String param2;

}
