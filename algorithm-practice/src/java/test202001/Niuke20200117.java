package test202001;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @author chenlw
 * @date 2020/01/17
 */
public class Niuke20200117 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a, b, c;
        a = in.nextInt();
        b = in.nextInt();
        c = in.nextInt();

        List<Integer> list = new ArrayList<>();
        list.add(a + b + c);
        list.add(a * (b + c));
        list.add(a * b * c);
        list.add((a + b) * c);
        list.add(a + (b * c));
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
//        for (Integer integer:list) {
//            System.out.println(integer+"  ");
//        }
        System.out.println(list.get(0));
    }

}
