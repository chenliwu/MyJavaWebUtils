package test20190819;

import java.util.*;

/**
 * @author chenlw 2019/08/19
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String string = in.nextLine();
        char[] words = string.toCharArray();

        Map<String, Integer> countMap = new HashMap<>();
        Set<String> wordsSet = new HashSet<>();

        String temp;
        for (int i = 0; i < words.length; i++) {
            temp = String.valueOf(words[i]);
            if (!countMap.containsKey(temp)) {
                wordsSet.add(temp);
                countMap.put(temp, 1);
            } else {
                Integer count = countMap.get(temp);
                countMap.put(temp, count + 1);
            }
        }

        StringBuilder ans = new StringBuilder();
        for (String word:wordsSet) {
            ans.append(word).append(countMap.get(word));
        }
        System.out.println(ans.toString());
    }

}
