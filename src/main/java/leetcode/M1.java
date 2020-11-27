package leetcode;

import java.util.Scanner;

public class M1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            System.out.println(getResult(sc.next()));
        }
    }

    private static Integer getResult(String hexStr) {
        hexStr = hexStr.replace("x", "");
        return Integer.parseInt(hexStr, 16);
    }
}
