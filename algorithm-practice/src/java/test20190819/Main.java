package test20190819;

import java.util.Scanner;

/**
 * @author chenlw 2019/08/19
 */
public class Main {

    public static void main(String[] args) {
        String string;
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            //注意while处理多个case              int a = in.nextInt();
            string = in.nextLine();
            System.out.println(reverseString(string));
        }
    }

    public static String reverseString(String string) {
        return new StringBuffer(string).reverse().toString();
    }

}
