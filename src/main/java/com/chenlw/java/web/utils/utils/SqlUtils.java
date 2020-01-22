package com.chenlw.java.web.utils.utils;

/**
 * @author chenlw
 * @date 2019/12/03
 */
public class SqlUtils {

    private static final int DATASET_LIMIT_COUNT = 1000;

    public static void main(String[] args) {
        // top , columnName , querySql, whereSql, oracle litmit sql, columnName , mysql limit sql
        String formatSql = "SELECT %s cb_view.%s FROM (\n%s\n) cb_view %s %s GROUP BY cb_view.%s %s";
    }

    /**
     * 拼接限制数据集结果的行数
     *
     * @param formatSql
     * @param dbType
     * @return
     */
    public static String getColumnLimitDatasetCountSql(String formatSql,
                                                       String dbType,
                                                       String columnName,
                                                       String querySql,
                                                       String whereSql) {
        String result;
        if ("oracle".equals(dbType)) {
            // SELECT * FROM T_EMPLOYEE ROWNUM <= 6 ORDER BY FSALARY DESC
            String oracleLimitCountWhereSql = " AND ROWNUM <= " + DATASET_LIMIT_COUNT;
            result = String.format(formatSql, "", columnName, querySql, whereSql,
                    oracleLimitCountWhereSql, columnName, "");
        } else if ("mysql".equals(dbType)) {
            // SELECT * FROM T_EMPLOYEE OEDER BY FSALARY DESC LIMIT 2,1000
            String mysqlLimitCountWhereSql = " LIMIT 0," + DATASET_LIMIT_COUNT;
            result = String.format(formatSql, "", columnName, querySql, whereSql,
                    "", columnName, mysqlLimitCountWhereSql);
        } else if ("sqlserver".equals(dbType)) {
            // SELECT TOP 5 * FROM T_EMPLOYEE ORDER BY FSALARY DESC
            String sqlServerLimitCountWhereSql = " TOP " + DATASET_LIMIT_COUNT;
            result = String.format(formatSql, sqlServerLimitCountWhereSql, columnName, querySql, whereSql,
                    "", columnName, "");
        } else {
            result = String.format(formatSql, "", columnName, querySql, whereSql,
                    "", columnName, "");
        }
        return result;
    }

}
