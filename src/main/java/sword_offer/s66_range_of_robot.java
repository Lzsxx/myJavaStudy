package sword_offer;

// 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
// 但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），
// 因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？

// 思路：继续回溯法

public class s66_range_of_robot {
    public static void main(String[] args) {
        s66_range_of_robot test = new s66_range_of_robot();
        test.movingCount(10, 100, 1);
    }
    public int movingCount(int threshold, int rows, int cols)
    {
        //创建一个布尔数组
        boolean visited[][]= new boolean[rows][cols];
        // 开始不断判断，如果里面为false，会继续尝试其他
        return hasPathCore(threshold,0,0,rows,cols,visited);   // k表示当前要判断的字符
    }
    // rows和cols表示矩阵的行数和列数
    // i,j表示当前在矩阵中要判断的位置
    public int hasPathCore(int threshold,int i,int j,int rows,int cols,boolean[][] visited){
        // 对于给定行数和列数得到其在矩阵中的下标
        //判断合法性
        // 判断坐标各位相加是否超过阈值
        if(i<0||i>=rows||j<0||j>=cols||visited[i][j] || bitSum(i) + bitSum(j) > threshold){
            return 0 ;
        }
        visited[i][j] = true;   // 如果能走到，就标记一些，否则后面回溯，会走不到这里
        int count = 1 + hasPathCore(threshold,i-1,j,rows,cols,visited)  //向左走
        + hasPathCore(threshold,i,j-1,rows,cols,visited)   // 向上走
        + hasPathCore(threshold,i+1,j,rows,cols,visited)  // 向右走
        + hasPathCore(threshold,i,j+1,rows,cols,visited); //向下走
        return count;
    }


    public int bitSum(int t){
        int count = 0;
        while (t != 0){
            count += t % 10;
            t /= 10;
         }
        return count;
    }

}
