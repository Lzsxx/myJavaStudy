package ztest;

import java.util.Scanner;

/**
 * @author lzs
 * @date 2018/8/30 19:46
 */
public class zhaoHang2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] priceStr = line.split(" ");
        int[] price = new int[priceStr.length];
        for (int i = 0; i < price.length; i++) {
            price[i] = Integer.parseInt(priceStr[i]);
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < price.length - 1; i++) {
            int tempMax = Integer.MIN_VALUE;
            int buyPrice = price[i];
            for (int j = i + 1; j < price.length; j++) {
                int profit = price[j] - buyPrice;
                if (profit > tempMax) {
                    tempMax = profit;
                }
            }
            if (tempMax > max) {
                max = tempMax;
            }
        }
        if (max == Integer.MAX_VALUE) {
            max = 0;
        }

        System.out.println(max);
    }
}
