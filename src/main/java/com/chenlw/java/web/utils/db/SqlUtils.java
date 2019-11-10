package com.chenlw.java.web.utils.db;

import org.apache.commons.lang.StringUtils;

/**
 * @author chenlw
 * @date 2019/10/25
 */
public class SqlUtils {

    public static void main(String[] args) {
        // String sql = " select acc.id as id,acc.bank_acc as bankAcc,acc.acc_name as accName,acc.corp_id as corpId,  su.username as createBy,acc.REG_DATE as regDate,acc.VALID_SIGN as validSign,  acc.acc_type as accType,att.attribute_name as attributeName, acc.IS_ONLINE as isOnline,acc.RATES as rates,  nat.nature_name as natureName,banktype.type_name as bankTypeName,acc.bank_name as bankName,  acc.prov as prov,acc.city as city,acc.electric_bill as electricBill,acc.is_capital_pool as isCapitalPool  ,acc.check_expire_date as checkExpireDate from bt_bank_acc acc  left join BIS_ACC_ATTRIBUTE att on acc.attribute_id = att.id  left join bt_acc_nature nat on acc.nature_id = nat.id  left join bt_bank_type banktype on acc.bank_type_id = banktype.id  left join sys_user su on su.login_name = acc.create_by  where 1=1 and acc.status <> -2  and ( acc.corp_id in ( '402880425b8abcfb015b8ac16e980000' )) and acc.is_online = 1 and acc.valid_sign = 1";
        String sql="select * from sys_user group by sys_user.id order by sys_user.id asc ";
        String countSql = prepareCountSQL(sql);
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

}
