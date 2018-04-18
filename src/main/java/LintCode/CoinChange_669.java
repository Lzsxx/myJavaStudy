package LintCode;

// 给出不同面额的硬币以及一个总金额. 写一个方法来计算给出的总金额可以换取的最少的硬币数量.
// 如果已有硬币的任意组合均无法与总金额面额相等, 那么返回 -1.


import javax.swing.*;
import java.util.Arrays;

// 思路：动态规划
public class CoinChange_669 {
    public static void main(String[] args) {
        CoinChange_669 test = new CoinChange_669();
//        int[] coins = {1,2,20,41,30,40,34,43,47,38,26,39,25,42,37,23,28,49,27};
        int[] coins = {2};
        System.out.println(test.coinChange(coins, 3));
//        test.coinChange(coins, 41010);
    }
    /**
     * @param coins: a list of integer
     * @param money: a total amount of money amount
     * @return: the fewest number of coins that you need to make up
     */
    public int coinChange(int[] coins, int money) {
        // 默认coins的硬币是按照从小到大的顺序排列的
        if (money == 0){
            return 0;
        }
        Arrays.sort(coins);
        // write your code here
        int[] coinNum = new int[money + 1];  // 存储找零这么多钱最少需要的硬币数
        int[] coinValue = new int[money + 1]; // 存储硬币
        coinNum[0] = 0;

        for (int i = 1; i <= money; i++) { // 要找多少钱
            int minNum = i; // 表示i个1元硬币正好找零i元，用i个，
            int usedMoney = 0; // 这次在原来的基础上要找零数
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]){  // 总额大于硬币的面值，才能继续，否则这个硬币肯定不能用
                    if ( coinNum[i - coins[j]] + 1 <= minNum && (i == coins[j] || coinValue[i - coins[j]] != 0)){    // 如果用上这个硬币，比已有的最小解还小
                        minNum = coinNum[i - coins[j]] + 1;
                        usedMoney = coins[j];
                    }
                }
            }
            coinNum[i] = minNum;
            coinValue[i] = usedMoney;
        }
        if (coinValue[money] == 0){
            return -1;
        }
        return coinNum[money];
    }
}
