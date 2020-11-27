package leetcode;

import java.util.*;

public class M5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        HashSet<Integer> set = new HashSet();

        String res = "";
        while (number > 0) {
            int tmp = number % 10;
            if (!set.contains(tmp)) {
                res += tmp;
                set.add(tmp);
            }
            number = number / 10;
        }

        System.out.print(res);
    }
}
