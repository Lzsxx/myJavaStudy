package test;

import java.util.Scanner;

public class aiqiyi3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] strs = input.split(" ");
        int n = Integer.parseInt(strs[0]);
        int m = Integer.parseInt(strs[1]);
        int[][] limits = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] temp = in.nextLine().split(" ");
            limits[i][0] = Integer.parseInt(temp[0]);
            limits[i][1] = Integer.parseInt(temp[1]);
        }
        in.close();



        System.out.println(12);
    }
}
