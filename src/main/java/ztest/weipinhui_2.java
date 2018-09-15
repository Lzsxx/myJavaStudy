package ztest;

import java.util.Scanner;

/**
 * @author lzs
 * @date 2018/9/15 20:09
 */
public class weipinhui_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] strings = line.split(" ");
        // 取第一个数
        int lenA = strings[0].length();
        String[] strA = strings[0].split("");
        int[] numA = new int[lenA];
        for (int i = 0; i < lenA; i++) {
            numA[i] = Integer.parseInt(strA[i]);
        }
        // 取第二个数
        int lenB = strings[1].length();
        String[] strB = strings[1].split("");
        int[] numB = new int[lenB];
        for (int i = 0; i < lenB; i++) {
            numB[i] = Integer.parseInt(strB[i]);
        }

        // 转化成数字
        int num1 = 0;
        int num2 = 0;

        for (int i = lenA - 1; i >= 0; i--) {
            int base = (int) Math.pow(2, lenA - 1 - i);
            num1 += base * numA[i];
        }
        for (int i = lenB - 1; i >= 0; i--) {
            int base = (int) Math.pow(2, lenB - 1 - i);
            num2 += base * numB[i];
        }

        int num3 = num1 + num2;
        System.out.println(Integer.toBinaryString(num3));
    }
}
