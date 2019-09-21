package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Knapsack_01problem {
    public static void main(String[] args) {
        int capacity = 13;
        int[] value = {10,40,30,50};
        int[] weight = {2,4,6,3};
//        knapsack_01_path(value, weight, capacity);
        knapsack_01(value, weight, capacity);
    }
    public static void knapsack_01(int[] value, int[] weight, int capacity){
        // 先申请数组，初始化第一行和第一列
        int[][] countMax = new int[value.length + 1][capacity + 1];
        for (int i = 0; i <= value.length; i++) {
            countMax[i][0] = 0;
        }
        for (int i = 0; i <= capacity; i++) {
            countMax[0][i] = 0;
        }
        // 开始计算,循环次数是数组去掉第一行和第一列后的大小，外围是行，内层是列，每次内层循环完，应该填完一列
        for (int i = 1; i <= value.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                // 优化：如果当前背包的总大小还没有当前物品大，那么当前物品肯定放不进去，直接取上一行同一列的值
                // （表示不考虑当前物品，但不能直接填0，因为前面可能有更小的物体已经被放进去，此时我们应该取那一个值）
                if (j < weight[i-1]){   //需要注意的是，weight[]、value[]和countMax的下标并不统一，前两者中表示第i个物体的对应值，要用i-1
                    countMax[i][j] = countMax[i-1][j];
                }else {
                    countMax[i][j] = Math.max( countMax[i-1][j], countMax[i-1][j - weight[i-1]] + value[i-1] );
                }
            }
        }

        for (int[] row : countMax) {
            for (int col : row){
                System.out.print(col+"\t");
            }
            System.out.println();
        }
    }

    public static void knapsack_01_path(int[] value, int[] weight, int capacity){
        // 先申请数组，初始化第一行和第一列
        int[][] countMax = new int[value.length + 1][capacity + 1];
        int[][] path = new int[value.length + 1][capacity + 1];
        for (int i = 0; i <= value.length; i++) {
            countMax[i][0] = 0;
        }
        for (int i = 0; i <= capacity; i++) {
            countMax[0][i] = 0;
        }
        // 开始计算,循环次数是数组去掉第一行和第一列后的大小，外围是行，内层是列，每次内层循环完，应该填完一列
        for (int i = 1; i <= value.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                // 优化：如果当前背包的总大小还没有当前物品大，那么当前物品肯定放不进去，直接取上一行同一列的值
                // （表示不考虑当前物品，但不能直接填0，因为前面可能有更小的物体已经被放进去，此时我们应该取那一个值）
                if (j < weight[i-1]){   //需要注意的是，weight[]、value[]和countMax的下标并不统一，前两者中表示第i个物体的对应值，要用i-1
                    countMax[i][j] = countMax[i-1][j];
                }else {
                    if (countMax[i-1][j] < countMax[i-1][j - weight[i-1]] + value[i-1]){
                        countMax[i][j] = countMax[i-1][j - weight[i-1]] + value[i-1];
                        path[i][j] = 1;
                    }else {
                        countMax[i][j] = countMax[i-1][j];
                    }
                }
//                System.out.println("第"+i+"行:第"+j+"列");
//                System.out.println("----------countMax-----------");
//                for (int[] row : countMax) {
//                    for (int col : row){
//                        System.out.print(col+"\t");
//                    }
//                    System.out.println();
//                }
//                System.out.println("==========Path ============");
//                for (int[] row : path) {
//                    for (int col : row){
//                        System.out.print(col+"\t");
//                    }
//                    System.out.println();
//                }
            }
        }

        //输出计算过程的二维数组
        System.out.println("\n\n");
        for (int[] row : countMax) {
            for (int col : row){
                System.out.print(col+"\t");
            }
            System.out.println();
        }
        // 输出path的值
        for (int[] row : path) {
            for (int col : row){
                System.out.print(col+"\t");
            }
            System.out.println();
        }
        // 输出被放到背包中的物品
        List<Integer> list = new ArrayList<>();
        int i = countMax.length - 1;
        int j = countMax[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {      // 注：path只能看出物品在只有前i种的情况下什么时候被放入到背包中，因为当只有前i种物品，而物品i只有在被放入时是最优解的情况下，才会放入，一旦放入，就不再拿出来
                list.add(i);
                j -= weight[i - 1];
            }
            i--;
        }
        StringBuffer buffer = new StringBuffer();
        for (int k = list.size() - 1; k >= 0; k--) {
            if (k == 0) {
                buffer.append(list.get(k));
            } else {
                buffer.append(list.get(k) + " ");
            }
        }
        System.out.println(buffer.toString());
    }
}
