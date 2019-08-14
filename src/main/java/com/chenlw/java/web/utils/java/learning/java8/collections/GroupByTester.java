package com.chenlw.java.web.utils.java.learning.java8.collections;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 描述:Stream Collectors groupingBy 分组示例
 *
 * @author chenlw
 * @create 2019-05-15 17:30
 */
public class GroupByTester {

    public static void main(String[] args) {
        //test1();
        test2();
    }

    public static void test1() {
        List<String> items = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");
        Map<String, Long> result = items.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(result);

    }

    /**
     * 测试实体类分组
     */
    public static void test2() {
//3 apple, 2 banana, others 1
        List<Item> items = Arrays.asList(
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 20, new BigDecimal("19.99")),
                new Item("orang", 10, new BigDecimal("29.99")),
                new Item("watermelon", 10, new BigDecimal("29.99")),
                new Item("papaya", 20, new BigDecimal("9.99")),
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 10, new BigDecimal("19.99")),
                new Item("apple", 20, new BigDecimal("9.99"))
        );

        //根据Item的名称分组，统计数量
        Map<String, Long> counting = items.stream().collect(Collectors.groupingBy(Item::getName, Collectors.counting()));
        System.out.println(counting);

        //根据Item的名称分组，统计同一个名称的Item的qty属性的总和
        Map<String, Integer> sum = items.stream().collect(Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));
        System.out.println(sum);


        System.out.println("\nComparator.comparing");
        Optional<Item> min =items.stream().collect(Collectors.minBy(Comparator.comparing(Item::getQty)));

        min.ifPresent(System.out::println);
    }


    static class Item {
        private String name;
        private int qty;
        private BigDecimal price;
        //constructors, getter/setters


        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", qty=" + qty +
                    ", price=" + price +
                    '}';
        }

        public Item(String name, int qty, BigDecimal price) {
            this.name = name;
            this.qty = qty;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }

}
