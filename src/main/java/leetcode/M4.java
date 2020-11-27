package leetcode;

import java.util.Scanner;
import java.util.TreeMap;

public class M4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        TreeMap<Integer, Integer> map = new TreeMap();

        for (int i = 0; i < size; i++) {
            Integer k = sc.nextInt();
            Integer v = sc.nextInt();
            if (map.get(k) != null) {
                Integer newValue = map.get(k) + v;
                map.put(k, newValue);
            } else {
                map.put(k, v);
            }
        }

        map.forEach((k, v) -> {
            System.out.println(k + " " + v);
        });

    }
}
