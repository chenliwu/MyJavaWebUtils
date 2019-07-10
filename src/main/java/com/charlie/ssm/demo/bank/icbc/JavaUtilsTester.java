package com.charlie.ssm.demo.bank.icbc;

import com.charlie.ssm.demo.bank.icbc.entity.ICBCOrderPub;
import com.charlie.ssm.demo.bank.icbc.entity.ICBCQueryTodayBalanceCms;
import com.charlie.ssm.demo.bank.icbc.entity.ICBCQueryTodayBalanceEb;
import com.charlie.ssm.demo.bank.icbc.entity.ICBCQueryTodayBalanceIn;
import com.charlie.ssm.demo.utils.JaxbUtils;

/**
 * Created by chenlw on 2019/07/10  9:05.
 */
public class JavaUtilsTester {

    private static final String XML_GBK_ENCODE="GBK";


    public static void main(String[]args){
        // test1();
        test2();
    }

    public static void test1(){
//        ICBCQueryTodayBalanceCms cms = new ICBCQueryTodayBalanceCms();
//        ICBCQueryTodayBalanceEb eb = new ICBCQueryTodayBalanceEb();
//
//        ICBCOrderPub pub = new ICBCOrderPub();
//        pub.setTransCode("交易代码");
//        pub.setCIS("集团CIS号");
//        pub.setBankCode("归属银行编号");
//        pub.setID("证书ID");
//        pub.setTranDate("交易日期");
//        pub.setTranTime("交易时间");
//        pub.setFSeqno("指令包序列号");
//
//        ICBCQueryTodayBalanceIn todayBalance = new ICBCQueryTodayBalanceIn();
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
        ICBCQueryTodayBalanceCms cms = new ICBCQueryTodayBalanceCms();
        ICBCQueryTodayBalanceEb eb = new ICBCQueryTodayBalanceEb();

        ICBCOrderPub pub = new ICBCOrderPub();
        pub.setTransCode("交易代码");
        pub.setCIS("集团CIS号");
        pub.setBankCode("归属银行编号");
        pub.setID("证书ID");
        pub.setTranDate("交易日期");
        pub.setTranTime("交易时间");
        pub.setFSeqno("指令包序列号");

        ICBCQueryTodayBalanceIn todayBalance = new ICBCQueryTodayBalanceIn();
        todayBalance.setAccNo("查询账号");
        todayBalance.setAreaCode("地区代码");
        todayBalance.setMinAmt("发生额下限");
        todayBalance.setMaxAmt("发生额上限");
        todayBalance.setBeginTime("开始时间");
        todayBalance.setEndTime("终止时间");
        todayBalance.setNextTag("查询下页标识");

        eb.setPub(pub);
        eb.setIn(todayBalance);
        cms.setEb(eb);

        //String xml = JaxbUtils.marshaller(cms,XML_ENCODE);
        String xml = JaxbUtils.marshaller(cms,XML_GBK_ENCODE,true);
        System.out.println(xml);
    }


}
