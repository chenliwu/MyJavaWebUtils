package com.charlie.ssm.demo.jaxb.icbc;

import com.charlie.ssm.demo.jaxb.icbc.entity.ICBCCms;
import com.charlie.ssm.demo.jaxb.icbc.entity.ICBCEb;
import com.charlie.ssm.demo.jaxb.icbc.entity.ICBCPub;
import com.charlie.ssm.demo.jaxb.icbc.entity.ICBCTodayBalance;
import com.charlie.ssm.demo.utils.JaxbUtils;

/**
 * Created by chenlw on 2019/07/10  9:05.
 */
public class JavaUtilsTester {

    private static final String XML_ENCODE="GBK";


    public static void main(String[]args){

        test1();
    }

    public static void test1(){
//        ICBCCms cms = new ICBCCms();
//        ICBCEb eb = new ICBCEb();
//
//        ICBCPub pub = new ICBCPub();
//        pub.setTransCode("交易代码");
//        pub.setCIS("集团CIS号");
//        pub.setBankCode("归属银行编号");
//        pub.setID("证书ID");
//        pub.setTranDate("交易日期");
//        pub.setTranTime("交易时间");
//        pub.setFSeqno("指令包序列号");
//
//        ICBCTodayBalance todayBalance = new ICBCTodayBalance();
//        todayBalance.setAccNo("查询账号");
//        todayBalance.setAreaCode("地区代码");
//        todayBalance.setMinAmt("发生额下限");
//        todayBalance.setMaxAmt("发生额上限");
//        todayBalance.setBeginTime("开始时间");
//        todayBalance.setEndTime("终止时间");
//        todayBalance.setNextTag("查询下页标识");
//
//        eb.setPub(pub);
//        eb.setIn(todayBalance);
//        cms.setEb(eb);
//
//        //String xml = JaxbUtils.marshaller(cms,XML_ENCODE);
//        String xml = JaxbUtils.marshaller(cms,XML_ENCODE,true,true,true);
//        System.out.println(xml);

    }

    public static void test2(){

    }


}
