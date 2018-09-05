package ztest;

import java.util.Scanner;

public class today {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String str2 = in.nextLine();
        String str3 = in.nextLine();
        in.close();

        String[] strings = str.split(" ");
        int n1_num = Integer.parseInt(strings[0]);
        int n2_num = Integer.parseInt(strings[1]);
        int target = Integer.parseInt(strings[2]);

        strings = str2.split(" ");
        int[] n1_arr = new int[n1_num];
        for (int i = 0; i < n1_num; i++) {
            n1_arr[i] = Integer.parseInt(strings[i]);
        }

        strings = str3.split(" ");
        int[] n2_arr = new int[n2_num];
        for (int i = 0; i < n2_num; i++) {
            n2_arr[i] = Integer.parseInt(strings[i]);
        }

//       int[] value = {1,2,3};
//       int[] memoCoin = {1};
//       int target = 5;
//        process(value, memoCoin, target);
        process(n1_arr, n2_arr, target);

    }
    public static void process(int[] value, int[] memoCoin, int target){

        int n = value.length;   //有多少行
        int[][] dp = new int[n][target+1];
        // 初始化第一列，target为0时，方案数为1
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        // 初始化第一行，能除尽时，方案数位1
        for (int i = 0; i <= target; i++) {
            if (i % value[0] == 0){
                dp[0][i] = 1;
            }else {
                dp[0][i] = 0;
            }
        }

        //开始从上到下、从左到右计算
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= target; j++) {
                if (j - value[i] >= 0){
                    dp[i][j] = dp[i - 1][j] + dp[i][j - value[i]];
                }
                else{
                    dp[i][j] = dp[i - 1][j];
                }
            }

        }
        int num = dp[value.length - 1][target];

        System.out.println(num);
    }

}