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
        int[] coins = {1,2,5};
        System.out.println(test.coinChange(coins, 5));
//        test.coinChange(coins, 41010);
    }
    /**
     * @param coins: a list of integer
     * @param money: a total amount of money amount
     * @return: the fewest number of coins that you need to make up
     */
    // 临场发挥版
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        // write your code here
        int[] F = new int[amount + 1];  // 表示当前需要的最少硬币，默认初始为0
        int[] currentValue = new int[amount + 1];   // 默认初始化为0，记录这个amount对应可以凑到的值，如果最后等于0，就说明没有方案可以凑足

        for (int i = 1; i <= amount; i++) {
            int minNums = amount;   // 假设都用1元硬币，那么多少面值就有多少个
            int currValue = 0;
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && F[i - coins[j]] + 1 <= minNums && currentValue[i - coins[j]] + coins[j] == i) {    // 如果用了当前硬币，可以让最小值变得更小
                    // 这里的最小值是遍历完前面所有硬币后，得到的最小值
                    minNums = F[i - coins[j]] + 1;
                    currValue = i;
                }
            }
            F[i] = minNums;
            currentValue[i] = currValue;
        }
        // 没有被改写过，说明没有合适的组合
        if (F[amount] == amount && currentValue[amount] == 0) {
            return -1;
        }
        return F[amount];
    }


//    public int coinChange(int[] coins, int money) {
//
//        // 默认coins的硬币是按照从小到大的顺序排列的
//        if (money == 0){
//            return 0;
//        }
//        Arrays.sort(coins);
//        // write your code here
//        int[] coinNum = new int[money + 1];  // 存储找零这么多钱最少需要的硬币数
//        int[] coinValue = new int[money + 1]; // 存储硬币
//        coinNum[0] = 0;
//
//        for (int i = 1; i <= money; i++) { // 要找多少钱
//            int minNum = i; // 表示i个1元硬币正好找零i元，用i个，
//            int usedMoney = 0; // 这次在原来的基础上要找零数
//            for (int j = 0; j < coins.length; j++) {
//                if (i >= coins[j]){  // 总额大于硬币的面值，才能继续，否则这个硬币肯定不能用
//                    if ( coinNum[i - coins[j]] + 1 <= minNum && (i == coins[j] || coinValue[i - coins[j]] != 0)){    // 如果用上这个硬币，比已有的最小解还小
//                        minNum = coinNum[i - coins[j]] + 1;
//                        usedMoney = coins[j];
//                    }
//                }
//            }
//            coinNum[i] = minNum;
//            coinValue[i] = usedMoney;
//        }
//        if (coinValue[money] == 0){
//            return -1;
//        }
//        return coinNum[money];
//    }
}
