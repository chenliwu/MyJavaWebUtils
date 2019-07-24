package com.charlie.ssm.demo.java8.collector;

import com.chenliwu.java.utils.demo.java8.collector.result.ColumnIndex;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-05-16 10:13
 */
public class CutomGroupByTester {

    public static void main(String[] args) {
        //Double[] ds = new Double[3];
        //Arrays.stream(ds).forEach(System.out::println);
        test1();
        test2();
    }

    /**
     * 测试自定义收集器
     */
    public static void test1() {
        System.out.println("\ntest1()");
        String[][] data = new String[][]{
                {"111", "222", "333"},
                {"200", "210", "220"},
                {"200", "210", "220"},
                {"300", "310", "320"},
                {"300", "310", "320"},
                {"400", "410", "420"}
        };
        List<ColumnIndex> dimensionList = new ArrayList<>();
        dimensionList.add(new ColumnIndex(0, null, "班级编号"));
        dimensionList.add(new ColumnIndex(1, null, "学号"));
        dimensionList.add(new ColumnIndex(2, null, "姓名"));

        ///列下标，聚合类型，列名称
        List<ColumnIndex> valuesList = new ArrayList<>();
        //valuesList.add(new ColumnIndex("avg", "班级编号"));
        valuesList.add(new ColumnIndex(0, "sum", "班级编号"));
        valuesList.add(new ColumnIndex(1, "avg", "学号"));
        valuesList.add(new ColumnIndex(2, "", "姓名"));

        Map<Dimensions, Double[]> grouped = Arrays.stream(data)
                .skip(1)
                .collect(Collectors.groupingBy(row -> {
                    String[] ds = dimensionList.stream()
                            .map(d -> row[d.getIndex()])
                            .toArray(String[]::new);
                    return new Dimensions(ds);
                }, AggregateCollector.getCollector(valuesList)));


        // 第三种：推荐，尤其是容量大时
        System.out.println("");
        for (Map.Entry<Dimensions, Double[]> entry : grouped.entrySet()) {
            //Map.entry<Integer,String> 映射项（键-值对）  有几个方法：用上面的名字entry
            //entry.getKey() ;entry.getValue(); entry.setValue();
            //map.entrySet()  返回此映射中包含的映射关系的 Set视图。
            System.out.println("key= " + entry.getKey());
            //System.out.println("value size = " + entry.getValue().length);
            Arrays.stream(entry.getValue()).forEach(row -> {
                System.out.println(row);
            });
            System.out.println();
        }


    }

    public static void test2() {
        System.out.println("\ntest2()");
        String[][] data = new String[][]{
                {"111", "222", "333"},
                {"200", "210", "220"},
                {"200", "210", "220"},
                {"300", "310", "320"},
                {"300", "310", "320"},
                {"400", "410", "420"}
        };
        List<ColumnIndex> dimensionList = new ArrayList<>();
        dimensionList.add(new ColumnIndex(0, null, "班级编号"));
        dimensionList.add(new ColumnIndex(1, null, "学号"));
        dimensionList.add(new ColumnIndex(2, null, "姓名"));

        ///列下标，聚合类型，列名称
        List<ColumnIndex> valuesList = new ArrayList<>();
        //valuesList.add(new ColumnIndex("avg", "班级编号"));
        valuesList.add(new ColumnIndex(0, "sum", "班级编号"));
        valuesList.add(new ColumnIndex(1, "avg", "学号1"));
        valuesList.add(new ColumnIndex(2, "", "姓名1"));

        Map<Dimensions, Double[]> grouped = getGrouped(data, dimensionList, valuesList);

        System.out.println("");
        for (Map.Entry<Dimensions, Double[]> entry : grouped.entrySet()) {
            System.out.println("key= " + entry.getKey());
            Arrays.stream(entry.getValue()).forEach(row -> {
                System.out.println(row);
            });
            System.out.println();
        }
    }

    public static Map<Dimensions, Double[]> getGrouped(String[][] data,
                                                       List<ColumnIndex> dimensionList,
                                                       List<ColumnIndex> valuesList) {
        Map<Dimensions, Double[]> grouped = new HashMap<>();
        final int valueListSize = valuesList.size();
        String aggType;

        //countMap用于数据列聚合计数
        Map<String, Double> countMap = new HashMap<>();

        //dimensionsCountMap用于统计相同dimensions的个数
        Map<Dimensions, Integer> dimensionsCountMap = new HashMap<>();

        for (int i = 1, dataSize = data.length; i < dataSize; i++) {
            List<String> dsList = new ArrayList<>();
            for (ColumnIndex columnIndex : dimensionList) {
                dsList.add(data[i][columnIndex.getIndex()]);
            }
            String[] ds = dsList.toArray(new String[0]);
            Dimensions dimensions = new Dimensions(ds);

            if (!grouped.containsKey(dimensions)) {
                grouped.put(dimensions, new Double[valueListSize]);
            }

            //统计相同dimensions的个数
            if (dimensionsCountMap.containsKey(dimensions)) {
                int count = dimensionsCountMap.get(dimensions);
                count++;
                dimensionsCountMap.put(dimensions, count);
            } else {
                dimensionsCountMap.put(dimensions, 1);
            }

            //把所有列数据存入double[]
            Double[] result = grouped.get(dimensions);
            String doubleStr;
            for (int j = 0; j < valueListSize; j++) {
                doubleStr = dsList.get(valuesList.get(j).getIndex());
                aggType = valuesList.get(j).getAggType();
                if ("sum".equals(aggType)) {
                    if (result[j] == null) {
                        result[j] = toDouble(doubleStr);
                    } else {
                        result[j] = result[j] + toDouble(doubleStr);
                    }
                } else if ("avg".equals(aggType)) {
                    if (result[j] == null) {
                        result[j] = toDouble(doubleStr);
                    } else {
                        result[j] = result[j] + toDouble(doubleStr);
                    }
                } else if ("max".equals(aggType)) {
                    if (result[j] == null) {
                        result[j] = toDouble(doubleStr);
                    } else {
                        double d1 = result[j];
                        double d2 = toDouble(doubleStr);
                        if (d2 > d1) {
                            result[j] = d2;
                        }
                    }
                } else if ("min".equals(aggType)) {
                    if (result[j] == null) {
                        result[j] = toDouble(doubleStr);
                    } else {
                        double d1 = result[j];
                        double d2 = toDouble(doubleStr);
                        if (d2 < d1) {
                            result[j] = d2;
                        }
                    }
                } else if ("distinct".equals(aggType)) {
                    result[j] = 1.0;
                } else {
                    //计数
                    if (countMap.containsKey(doubleStr)) {
                        double count = countMap.get(doubleStr);
                        count++;
                        countMap.put(doubleStr, count);
                    } else {
                        countMap.put(doubleStr, 1.0);
                    }
                    result[j] = countMap.get(doubleStr);
                }

            }
            grouped.put(dimensions, result);
        }

        for (Map.Entry<Dimensions, Double[]> entry : grouped.entrySet()) {
            Double[] result = entry.getValue();
            for (int i = 0; i < valueListSize; i++) {
                aggType = valuesList.get(i).getAggType();
                if ("avg".equals(aggType)) {
                    int saveDimensionCount = dimensionsCountMap.get(entry.getKey());
                    result[i] = result[i] / saveDimensionCount;
                }
            }
        }


        return grouped;
    }

    private static double toDouble(Object o) {
        if (o instanceof Double) {
            return ((Double) o).doubleValue();
        } else {
            double result = 0;
            try {
                result = Double.parseDouble((String) o);
            } catch (Exception e) {
            }
            return result;
        }
    }


}
