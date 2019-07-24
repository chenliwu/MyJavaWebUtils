package com.charlie.ssm.demo.java8.collector;


import com.chenliwu.java.utils.demo.java8.collector.result.ColumnIndex;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 1、Collector<T, A, R>接口
 * 方法中有泛型，所以要先要介绍哈Collector中的三个泛型T, A, R。
 * <p>
 * T：stream在调用collect方法收集前的数据类型
 * A：A是T的累加器，遍历T的时候，会把T按照一定的方式添加到A中，换句话说就是把一些T通过一种方式变成A
 * R：R可以看成是A的累加器，是最终的结果，是把A汇聚之后的数据类型，换句话说就是把一些A通过一种方式变成R
 *
 * @param <T>
 */
public class AggregateCollector<T> implements Collector<T[], Object[], Double[]> {
    private List<ColumnIndex> valueList;
    private List<Collector> collectors;

    public static <T> AggregateCollector<T> getCollector(List<ColumnIndex> valueList) {
        return new AggregateCollector(valueList);
    }

    private AggregateCollector(List<ColumnIndex> valueList) {
        this.valueList = valueList;
        this.collectors = new ArrayList<>(valueList.size());
        valueList.stream().forEach(e -> collectors.add(null));
        IntStream.range(0, valueList.size()).forEach(i -> collectors.set(i, newCollector(valueList.get(i))));
    }

    private double toDouble(Object o) {
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

    private Collector newCollector(ColumnIndex columnIndex) {
        switch (columnIndex.getAggType()) {
            case "sum":
                return Collectors.summingDouble(this::toDouble);
            case "avg":
                return Collectors.averagingDouble(this::toDouble);
            case "max":
                return Collectors.maxBy(Comparator.comparingDouble(this::toDouble));
            case "min":
                return Collectors.minBy(Comparator.comparingDouble(this::toDouble));
            case "distinct":
                return new CardinalityCollector();
            default:
                return Collectors.counting();
        }
    }

    /**
     * Supplier<A> supplier()： 怎么创建一个累加器
     *
     * @return
     */
    @Override
    public Supplier<Object[]> supplier() {
        //new value row array
        return () -> {
            Object[] container = new Object[valueList.size()];
            IntStream.range(0, valueList.size()).forEach(i -> container[i] = collectors.get(i).supplier().get());
            return container;
        };
    }

    /**
     * BiConsumer<A, T> accumulator()：怎么把一个对象添加到累加器中
     *
     * @return
     */
    @Override
    public BiConsumer<Object[], T[]> accumulator() {
        return (array, e) ->
                IntStream.range(0, array.length).forEach(i -> {
                    collectors.get(i).accumulator().accept(array[i], e[valueList.get(i).getIndex()]);
                });

    }

    /**
     * BinaryOperator<A> combiner()：怎么把一个累加器和另一个累加器合并起来
     *
     * @return
     */
    @Override
    public BinaryOperator<Object[]> combiner() {
        return (a, b) -> {
            IntStream.range(0, a.length).forEach(i -> a[i] = collectors.get(i).combiner().apply(a[i], b[i]));
            return a;
        };
    }

    /**
     * Function<A, R> finisher()，其实就是怎么把A转化为R，
     *
     * @return
     */
    @Override
    public Function<Object[], Double[]> finisher() {
        return (array) -> {
            Double[] result = new Double[array.length]; //TODO new?
            IntStream.range(0, array.length).forEach(i -> {
                Object r = collectors.get(i).finisher().apply(array[i]);
                if (r instanceof Double) {
                    result[i] = (Double) r;
                } else if (r instanceof Long) {
                    result[i] = ((Long) r).doubleValue();
                } else if (r instanceof Integer) {
                    result[i] = ((Integer) r).doubleValue();
                } else if (r instanceof Optional) {
                    result[i] = toDouble(((Optional) r).get());
                }

            });
            return result;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
