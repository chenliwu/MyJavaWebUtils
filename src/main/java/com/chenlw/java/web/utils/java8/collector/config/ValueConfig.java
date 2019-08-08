package com.chenlw.java.web.utils.java8.collector.config;

/**
 * Created by zyong on 2017/1/9.
 */
public class ValueConfig {
    private String column;
    private String aggType;


    public ValueConfig() {

    }

    public ValueConfig(String column, String aggType) {
        this.column = column;
        this.aggType = aggType;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getAggType() {
        return aggType;
    }

    public void setAggType(String aggType) {
        this.aggType = aggType;
    }
}

