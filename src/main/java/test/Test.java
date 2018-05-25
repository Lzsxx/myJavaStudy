package test;


import javax.swing.*;
import javax.swing.plaf.ToolBarUI;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        int[] arr = {1,2,20,41,30,40,34,43,47,38,26,39,25,42,37,23,28,49,27};
        char[] matrix = {'A', 'B', 'C', 'E', 'S', 'F', 'C', 'S', 'A', 'D', 'E', 'E'};
        char[] str = {'A', 'B', 'C', 'C', 'E', 'D'};
        System.out.println(test.coinChange(arr, 41010));;
    }

    public int coinChange(int[] coins, int amount) {
        // write your code here
        // 换硬币的问题，就是一个完全背包问题，还需要必定满足
        Arrays.sort(coins);
        int[] F = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            F[i] = Integer.MIN_VALUE;
        }

        int[] coinNum = new int[amount + 1];
        for (int i = 0; i <= amount; i++) {
            coinNum[i] = 0;
        }

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                // 完全背包问题，顺序
                // 多加了一个 > Integer.MIN_VALUE 的条件，确保在前序都没有解决方法的情况下，不会进去
                if (F[j - coins[i]] > Integer.MIN_VALUE && F[j - coins[i]] + coins[i] >= F[j]) {
                    F[j] = F[j - coins[i]] + coins[i];
                    coinNum[j] = coinNum[j - coins[i]] + 1; // 用上了新的大硬币，肯定会减少数量
                }
            }
        }

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {    // 硬币金额小于、等于总额时才需要用
//                    if ()

                }
                
            }
        }
        
        return F[amount] > Integer.MIN_VALUE ? coinNum[amount] : -1;
    }
}
