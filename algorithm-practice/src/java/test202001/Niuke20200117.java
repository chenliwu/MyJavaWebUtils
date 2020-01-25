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

    public static int ans;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();

        dfs(x, y, 0, 0);
        System.out.println(ans);
    }

    public static void dfs(int targetX, int targetY, int x, int y) {
        if (x > targetX || y > targetY) {
            return;
        }
        if (x == targetX && y == targetY) {
            ans++;
            return;
        }
        dfs(targetX, targetY, x + 1, y);
        dfs(targetX, targetY, x, y + 1);
    }

}
