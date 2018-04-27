package test;

import java.util.Arrays;
import java.util.Scanner;

public class fintech1 {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int askNum =  Integer.parseInt(in.nextLine());
//        for (int i = 0; i < askNum; i++) {
//            String[] strs = in.nextLine().split(" ");
//            String[] valueStr = in.nextLine().split(" ");
//            int valueCategory = Integer.parseInt(strs[0]);  //
//            int money = Integer.parseInt(strs[1]);
//
//            int[] coins = new int[valueCategory];
//            for (int j = 0; j < valueCategory; j++) {
//                coins[j] = Integer.parseInt(valueStr[j]);
//            }
//            // begin process
//            int result = coinChange(coins, money);
//            System.out.println(result);
//        }
        int[] coins = {1,2,3,4};
        System.out.println(coinChange(coins, 3));
    }
    public static int coinChange(int[] coins, int amount) {
        long[][] table = new long[coins.length + 1][amount + 1];

        for (int i = 0; i <= coins.length; i++){
            table[i][0] = 1;
        }

        for (int i = 1; i <= coins.length; i++){
            for (int j = 1; j <= amount; j++){
                table[i][j] = 0;
                for (int k = 0; k <= j / coins[i - 1]; k++){
                    table[i][j] += table[i - 1][j - k*coins[i - 1]];
                }
            }
        }
        return (int) (table[coins.length][amount] % 100000007);
    }
}
