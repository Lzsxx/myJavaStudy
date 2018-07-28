package LintCode;


import LintCode.MinStack_12;

import javax.swing.*;
import javax.swing.plaf.ToolBarUI;
import java.util.*;

//给一个 n 英寸长的杆子和一个包含所有小于 n 的尺寸的价格. 确定通过切割杆并销售碎片可获得的最大值.例如，如果棒的长度为8，
// 并且不同长度部件的值如下，则最大可获得值为 22(通过切割两段长度 2 和 6 )
 class Test {
    public static void main(String[] args) {
        Test test = new Test();
        int[] arr = {3,5,8,9,10,17,17,20};
        System.out.println(test.cutting(arr, 8));;
    }

    public int cutting(int[] prices, int n) {
        // Write your code here
        int[] bucket = new int[n + 1];
        // 初始化
        bucket[0] = 0;
        for (int i = 1; i <= n; i++) {
            bucket[i] = Integer.MIN_VALUE;  // 假设表示负无穷
        }
        // 开始计算:一维数组，正序, 转移数组： max{ bucket[v], bucket[v - ci] + wi  }
        for (int i = 1; i <= prices.length; i++) {   // 横坐标为第几个物品
            for (int j = 1; j <= n; j++) {  // 纵坐标为容量
                if (j >= i) {   // 只有当总长度大于等于分割段的时候，才需要进行分割，否则，维持之前的值不变
                    bucket[j] = Math.max(bucket[j], bucket[j - i] + prices[i - 1]);
                }
            }
        }
//        for (int i = 0; i < bucket.length; i++) {
//            System.out.println(bucket[i]);
//        }
        return bucket[n];
    }

    public int cutting2(int[] prices, int n) {
        // Write your code here
        int[] F = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= prices.length; j++) {
                if (i >= j && F[i] < F[i - j] + prices[j - 1]) {    // 只有当要切割的长度小于等于杆子总刹那高度，且切割一定长度后+切割那段的value是最大的，才更新最大值
                    F[i] = F[i - j] + prices[j - 1];
                }
            }
        }
        return F[n];
    }

}
