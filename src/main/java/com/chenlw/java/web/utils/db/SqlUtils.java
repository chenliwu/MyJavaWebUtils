package com.chenlw.java.web.utils.db;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenlw
 * @date 2019/10/25
 */
public class SqlUtils {

    private static String complexSql = "select \n" +
            "t.corpName as \"单位名称\",\n" +
            "t.currencyName as \"币别\",\n" +
            "(sum((case when t.voucherfrom = 2 then t.paymoney else 0 end)) + sum((case when t.voucherfrom = 7 then t.paymoney else 0 end))) \"下拨汇总金额\",\n" +
            "(sum((case when t.voucherfrom = 5 then t.paymoney else 0 end)) + sum((case when t.voucherfrom = 8 then t.paymoney else 0 end))) \"归集汇总金额\", \n" +
            "sum((case when t.voucherfrom = 3 then t.paymoney else 0 end)) \"请款汇总金额\", \n" +
            "sum((case when t.voucherfrom = 4 then t.paymoney else 0 end)) \"头寸调拨总金额\",\n" +
            "sum((case when t.voucherfrom = 6 then t.paymoney else 0 end)) \"上缴汇总金额\" \n" +
            "from (\n" +
            "\tselect \n" +
            "\t\tc.id as corpId, \n" +
            "\t\tc.code as corpCode, \n" +
            "\t\tc.name as corpName, \n" +
            "\t\tc.net_id as netId, \n" +
            "\t\tb1.voucher_from as voucherFrom,\n" +
            "\t\tb1.req_date as reqDate, \n" +
            "\t\tb1.cur_id as curId,\n" +
            "\t\tb1.pay_money as payMoney, \n" +
            "\t\tb1.purpose as purpose, \n" +
            "\t\tb1.payer_acc as payerAcc, \n" +
            "\t\tb1.payer_acc_name as payerAccName,\n" +
            "\t\tb1.payer_bank as payerBank,\n" +
            "\t\tb1.payee_acc as payeeAcc, \n" +
            "\t\tb1.payee_acc_name as payeeAccName, \n" +
            "\t\tb1.payee_bank as payeeBank, \n" +
            "\t\tacc2.corp_id as oppCorpId,\n" +
            "\t\tbt_currency.cur_name as currencyName\n" +
            "\tfrom bis_exc b1\n" +
            "\tleft join sys_corp c on b1.corp_id = c.id \n" +
            "\tleft join bt_currency on bt_currency.id = b1.cur_id\n" +
            "\tleft join bt_bank_acc acc2 on b1.payee_acc = acc2.bank_acc\n" +
            "\twhere b1.voucher_stat = '0'\n" +
            "\tunion all\n" +
            "\tselect \n" +
            "\t\tc.id as corpId, \n" +
            "\t\tc.code as corpCode, \n" +
            "\t\tc.name as corpName, \n" +
            "\t\tc.net_id as netId, \n" +
            "\t\tb2.voucher_from as voucherFrom, \n" +
            "\t\tb2.return_time as reqDate, \n" +
            "\t\tb2.cur_id as curId,\n" +
            "\t\tb2.gather_amt as payMoney, \n" +
            "\t\tb2.purpose, \n" +
            "\t\tacc1.bank_acc as payerAcc, \n" +
            "\t\tacc1.acc_name as payerAccName, \n" +
            "\t\tacc1.bank_name as payerBank, \n" +
            "\t\tacc1.corp_id as oppCorpId,\n" +
            "\t\tacc2.bank_acc as payeeAcc,\n" +
            "\t\tacc2.acc_name as payeeAccName, \n" +
            "\t\tacc2.bank_name as payeeBank,\n" +
            "\t\tbt_currency.cur_name as currencyName\n" +
            "\tfrom bis_gather_cmd b2 \n" +
            "\tleft join bt_bank_acc acc1 on b2.child_acc_id = acc1.id \n" +
            "\tleft join bt_bank_acc acc2 on b2.parent_acc_id = acc2.id\n" +
            "\tleft join sys_corp c on acc1.corp_id = c.id \n" +
            "\tleft join bt_currency on bt_currency.id = b2.cur_id\n" +
            "\twhere b2.cmd_stat = '0' \n" +
            " ) t  \n" +
            "where 1 = 1\n" +
            "group by t.corpName,t.currencyName\n" +
            "\n";

    private static String complexSql1 = "select \n" +
            "t.type_name as \"银行类别\",\n" +
            "bankbal.currency_name as \"币别\",\n" +
            "sum(balMoney) as \"余额总金额\"\n" +
            "from (\n" +
            "\tselect \n" +
            "\tdistinct (b.bank_acc),\n" +
            "\tb.bank_type_id, \n" +
            "\t(\n" +
            "\t\tselect \n" +
            "\t\t\tdistinct a.avail_bal\n" +
            "\t\tfrom bis_acc_bal a,\n" +
            "\t\t(\n" +
            "\t\t\tselect \n" +
            "\t\t\t\tbank_acc, \n" +
            "\t\t\t\tmax(bal_date) maxdate\n" +
            "\t\t\tfrom bis_acc_bal ab \n" +
            "\t\t\twhere 1 = 1\n" +
            "\t\t\tand (ab.status >= '95' or ab.status is null)\n" +
            "\t\t\tgroup by bank_acc\n" +
            "\t\t) abc\n" +
            "\t\twhere a.bank_acc = abc.bank_acc\n" +
            "\t\tand a.bal_date = abc.maxdate\n" +
            "\t\tand a.cur_id = c.cur_id\n" +
            "\t\tand a.bank_acc = b.bank_acc\n" +
            "\t\tand (a.status is null or a.status >= 95)\n" +
            "\t) as balMoney,\n" +
            "\tbt_currency.cur_name as currency_name\n" +
            "\tfrom bt_bank_acc b\n" +
            "\tinner join bt_bank_acc_cur c on c.bank_acc_id = b.id  \n" +
            "\tinner join bt_currency on c.cur_id = bt_currency.id\n" +
            "\twhere 1 = 1\n" +
            "\tand b.status <> '-2'\n" +
            "\tand b.valid_sign = '1'\n" +
            ") bankbal\n" +
            "left join bt_bank_type t on t.id = bankbal.bank_type_id\n" +
            "where bankbal.balMoney is not null\n" +
            "group by bankbal.bank_type_id,bankbal.currency_name,t.type_name";


    public static void main(String[] args) {
        // String sql = " select acc.id as id,acc.bank_acc as bankAcc,acc.acc_name as accName,acc.corp_id as corpId,  su.username as createBy,acc.REG_DATE as regDate,acc.VALID_SIGN as validSign,  acc.acc_type as accType,att.attribute_name as attributeName, acc.IS_ONLINE as isOnline,acc.RATES as rates,  nat.nature_name as natureName,banktype.type_name as bankTypeName,acc.bank_name as bankName,  acc.prov as prov,acc.city as city,acc.electric_bill as electricBill,acc.is_capital_pool as isCapitalPool  ,acc.check_expire_date as checkExpireDate from bt_bank_acc acc  left join BIS_ACC_ATTRIBUTE att on acc.attribute_id = att.id  left join bt_acc_nature nat on acc.nature_id = nat.id  left join bt_bank_type banktype on acc.bank_type_id = banktype.id  left join sys_user su on su.login_name = acc.create_by  where 1=1 and acc.status <> -2  and ( acc.corp_id in ( '402880425b8abcfb015b8ac16e980000' )) and acc.is_online = 1 and acc.valid_sign = 1";
        //String sql="select * from sys_user group by sys_user.id order by sys_user.id asc ";

        String sql = complexSql1;
        String countSql = prepareCountSql1(sql);
        System.out.println("查询SQL:" + sql);
        System.out.println();
        System.out.println("countSql:" + countSql);
    }

    /**
     * 根据查询语句获取统计语句
     *
     * @param orgSql 查询SQL
     * @return 统计语句
     */
    public static String prepareCountSQL(String orgSql) {
        orgSql = orgSql.toUpperCase();
        StringBuilder sql = new StringBuilder();
        String fromSql = StringUtils.substringAfter(orgSql, "FROM");
        orgSql = StringUtils.substringBeforeLast(fromSql, "ORDER BY");
        orgSql = StringUtils.substringBeforeLast(orgSql, "GROUP BY");
        sql.append("SELECT COUNT(*) FROM ")
                .append(orgSql);
        return sql.toString();
    }


    public static String prepareCountSql1(String orgSql) {
        orgSql = orgSql.toUpperCase();

        int fromKeywordEndIndex = getFromKeywordEndIndex(orgSql);
        if (fromKeywordEndIndex != -1) {
            orgSql = orgSql.substring(fromKeywordEndIndex);
        }
        orgSql = StringUtils.substringBeforeLast(orgSql, "ORDER BY");
        orgSql = StringUtils.substringBeforeLast(orgSql, "GROUP BY");

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) FROM ")
                .append(orgSql);
        return sql.toString();
    }

    private static Pattern formKeywordPattern = Pattern.compile("\\sFROM\\s");

    private static int getFromKeywordEndIndex(String sql) {
        int fromKeywordEndIndex = -1;
        Matcher matcher = formKeywordPattern.matcher(sql);
        if (matcher.find()) {
            fromKeywordEndIndex = matcher.end();
        }
        return fromKeywordEndIndex;
    }




}
