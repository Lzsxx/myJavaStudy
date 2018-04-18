package LintCode;

public class BackPack_with_value_125 {
    public int backPack(int m, int[] A) {
        // write your code here
        int[][] bucket = new int[A.length + 1][m + 1];  // 辅助数组，记录总体状态
        // 初始化第一行和第一列为0
        for (int i = 0; i <= m ; i++) {    //   第一行
            bucket[0][i] = 0;
        }
        for (int i = 0; i <= A.length; i++) {   //第一列
            bucket[i][0] = 0;
        }

        // 从上到下，从左到右开始处理
        for (int i = 1; i <= A.length ; i++) {
            for (int j = 1; j <= m; j++) {
                if ( j < A[i-1]){ //背包大小小于当前的物品时，这个物品装不进去，则取上一行的值
                    bucket[i][j] = bucket[i - 1][j];
                }else{  //此时当前物品可能可以装进去，但要分析
                    bucket[i][j] = getMax(bucket[i - 1][j], bucket[i - 1][j - A[i-1]] + A[i-1]);    // 后一项是指，如果拿出其他东西到刚好能够加入当前项，和不加入当前项的状态比较，求哪个最大，就取哪个
                }
            }
        }
        return bucket[A.length][m];
    }
    public int getMax (int a, int b){
        return a > b ? a : b;
    }
}
