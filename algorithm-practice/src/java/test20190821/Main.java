package test20190821;

import java.util.Scanner;

/**
 * @author chenlw 2019/08/19
 */
public class Main {


    public static void main(String[] args) {
        fun();
    }


    public static void fun() {

        StringBuilder binary = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while (true) {
            binary.append(n % 2);
            n /= 2;
            if (n / 2 == 0) {
                binary.append(n % 2);
                break;
            }
        }
        // System.out.println(binary.reverse().toString());
        int ans = 0;
        for (char ch : binary.toString().toCharArray()) {
            if (ch == '1') {
                ans++;
            }
        }
        System.out.println(ans);
    }


}
