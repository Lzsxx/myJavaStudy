package ztest;

import java.util.Scanner;

/**
 * @author lzs
 * @date 2018/9/9 19:43
 */
public class JD {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        int total = Integer.parseInt(input);
        int[][] table = new int[total][3];
        for (int i = 0; i < total; i++) {
            input = in.nextLine();
            String[] inputArr = input.split(" ");
            table[i][0] = Integer.parseInt(inputArr[0]);
            table[i][1] = Integer.parseInt(inputArr[1]);
            table[i][2] = Integer.parseInt(inputArr[2]);
        }

        int count = 0;
        for (int i = 0; i < total; i++) {
            int curr = i;
            for (int j = 1; j < total; j++) {
                if (j == curr) {
                    continue;
                }
                if ((table[curr][0] < table[j][0]) && (table[curr][1] < table[j][1]) && (table[curr][2] < table[j][2])) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
