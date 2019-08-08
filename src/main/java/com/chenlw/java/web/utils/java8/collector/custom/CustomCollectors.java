package com.chenlw.java.web.utils.java8.collector.custom;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 描述:自定义收集器
 *
 * @author chenlw
 * @create 2019-05-16 10:53
 */
public class CustomCollectors {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // 按照2个分组
        List<List<Integer>> twoNumberList = list.stream().collect(CustomCollectors.groupByNumber());
        System.out.println(twoNumberList.size());
//        twoNumberList.stream().forEach(row->{
//            row.stream().forEach(System.out::println);
//        });

        System.out.println("\n");
        // 按照5个分组
        List<List<Integer>> fiveNumberList = list.stream().collect(CustomCollectors.groupByNumber(5));
        System.out.println(fiveNumberList.size());
//        fiveNumberList.stream().forEach(row->{
//            row.stream().forEach(System.out::println);
//        });
    }


    // 默认采用2个一起分组
    public static <T> Collector<T, List<List<T>>, List<List<T>>> groupByNumber() {
        return CustomCollectors.groupByNumber(2);
    }

    // 根据number的大小进行分组
    public static <T> Collector<T, List<List<T>>, List<List<T>>> groupByNumber(int number) {
        return new NumberCollectorImpl(number);
    }

    /**
     * 个数分组器
     *
     * @param <T>
     */
    static class NumberCollectorImpl<T> implements Collector<T, List<List<T>>, List<List<T>>> {
        // 每组的个数
        private int number;

        public NumberCollectorImpl(int number) {
            this.number = number;
        }

        /**
         * Supplier<A> supplier()： 怎么创建一个累加器
         *
         * @return
         */
        @Override
        public Supplier<List<List<T>>> supplier() {
            return ArrayList::new;
        }

        /**
         * BiConsumer<A, T> accumulator()：怎么把一个对象添加到累加器中
         *
         * @return
         */
        @Override
        public BiConsumer<List<List<T>>, T> accumulator() {
            return (list, item) -> {
                if (list.isEmpty()) {
                    list.add(this.createNewList(item));
                } else {
                    List<T> last = (List<T>) list.get(list.size() - 1);
                    if (last.size() < number) {
                        last.add(item);
                    } else {
                        list.add(this.createNewList(item));
                    }
                }
            };
        }

        /**
         * BinaryOperator<A> combiner()：怎么把一个累加器和另一个累加器合并起来
         *
         * @return
         */
        @Override
        public BinaryOperator<List<List<T>>> combiner() {
            return (list1, list2) -> {
                list1.addAll(list2);
                return list1;
            };
        }

        /**
         * Function<A, R> finisher()，其实就是怎么把A转化为R，
         *
         * @return
         */
        @Override
        public Function<List<List<T>>, List<List<T>>> finisher() {
            return Function.identity();
        }

        /**
         * Set<Characteristics> characteristics()这里直接可以按照Collectors.toList来弄就行了，
         * 也就是直接采用Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH))
         *
         * @return
         */
        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
        }

        private List<T> createNewList(T item) {
            List<T> newOne = new ArrayList<T>();
            newOne.add(item);
            return newOne;
        }
    }

}
