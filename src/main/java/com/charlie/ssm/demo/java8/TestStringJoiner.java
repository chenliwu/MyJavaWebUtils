package com.charlie.ssm.demo.java8;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2019-05-06 16:51
 */
public class TestStringJoiner {

    public static void main(String[] args) {
        //test2();
        //test4();
        test5();
    }

    public static void test1() {
        List<String> list = Arrays.asList("A", "B", "C", "D");
        //String result=  list.stream().collect(Collectors.joining("-"));
        String result = list.stream().collect(Collectors.joining(" "));
        System.out.println(result);
    }

    public static void test2() {
        String[] strings = new String[]{"A", "B", "C", "D"};
        StringJoiner joiner = new StringJoiner(".");
        Arrays.stream(strings).map(i -> i.toString()).forEach(joiner::add);
        System.out.println(joiner.toString());


        StringBuilder sb = new StringBuilder();
        for (int i = 0, sSize = strings.length; i < sSize; i++) {
            if(i == sSize-1){
                sb.append(strings[i]);
                break;
            }else{
                sb.append(strings[i]).append(".");
            }
        }
        System.out.println();
        System.out.println(sb.toString());

    }


    public static void test3(){
        StringJoiner ddl = new StringJoiner(", ", "CREATE TABLE " + "tableName" + "(", ");");
    }

    public static void test4(){
        List<String> list = Arrays.asList("A", "B", "C", "D");
        String str = list.stream().collect(Collectors.joining(" "+"---"+" "));
        System.out.println(str);

        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<list.size();i++){
            sb.append(list.get(i)).append(" "+"---"+" ");
        }
        System.out.println(sb.toString());
    }

    public static void test5(){
        StringJoiner where = new StringJoiner("\nAND ", "where" + " ", "");
        where.setEmptyValue("");
        where.add("AAA");
        System.out.println(where.toString());
    }



}
