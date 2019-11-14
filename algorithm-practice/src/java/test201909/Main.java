package test201909;

import java.util.Scanner;

/**
 * @author chenlw 2019/08/19
 */
public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Solution solution = new Solution();
        System.out.println(solution.JumpFloor(n));
    }

    public static class Solution {
        public int JumpFloor(int target) {
            if (target < 0) {
                return 0;
            }
            if (target == 0) {
                return 1;
            } else {
                return JumpFloor(target - 1) + JumpFloor(target - 2);
            }
        }
    }


}
