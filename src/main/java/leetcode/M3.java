package leetcode;

import java.util.Scanner;

public class M3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Double number = sc.nextDouble();
        Double tmp = number * 10;
        if (tmp.intValue() % 10 >= 5) {
            System.out.print(number.intValue() + 1);
        } else {
            System.out.print(number.intValue());
        }
    }
}
