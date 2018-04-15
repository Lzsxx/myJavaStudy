package LintCode;

import com.sun.org.apache.xpath.internal.SourceTree;
import sun.util.resources.cldr.ar.CalendarData_ar_MA;

import javax.xml.bind.SchemaOutputResolver;

class SearchMatrix {
    public static void main(String[] args) {
        SearchMatrix searchMatrix = new SearchMatrix();
        int[][] arr = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};

        System.out.println(searchMatrix.searchMatrix(arr, 7));
    }
     public boolean searchMatrix(int[][] matrix, int target) {
         // write your code here
         // 要求：在一个二维数组中查找是否具有某个值，时间复杂度o(logM)+o(logN)
         // 思路：横向、纵向上采用二分查找
         // 入手点：二维矩阵一共有4个点，选哪个点入手？
         // 分析：左上、右下是最小值和最大值，不具备指引方向的能力，排除
         //       右上：同行左边小，同列上小下大
         //       左下：同行右边大，同列上小下大
         // 已知：某行的第一个数，大于上一行的最后一个数，入手点可以是左下或者右上
         if (matrix.length <= 0){
             return false;
         }
         int rows = matrix.length;  //有多少行
         int cols = matrix[0].length;   //有多少列
         int targetRow = -1;
         int targetCol = -1;

         // 在竖直方向上用二分法，起点是matrix[0][cols - 1],终点是[rows - 1][cols - 1]
         int i = 0;
         int j = rows - 1;
         while ( i <= j){
             int mid = i + (j - i) / 2;
             if (target > matrix[mid][cols - 1]){
                 i = mid + 1;
             }else if (target < matrix[mid][cols - 1]){
                 // 小于的时候有两个方向可以走，1是往上一层，2是往左，所以还要再判断一次
                 if (target >= matrix[mid][0]){
                     targetRow = mid;
                     break;
                 }else {
                     j = mid - 1;
                 }
             }else {
                 // matrix[mid][cols - 1]就是要找的那个值
                 return true;
             }
         }

         // 如果找到了这一行，再继续
         if (targetRow >= 0){
             // 在横向上用二分法，[targetRow][0] ~[targetRow][cols - 1]
             // 如果再优化一下，可以只比较到[targetRow][0] ~[targetRow][cols - 2]， 因为[targetRow][cols - 1]在上面已经比较过了
             int a = 0;
             int b = cols - 2;
             while ( a <= b ){
                 int mid = a + (b - a) / 2;
                 if (target > matrix[targetRow][mid]){
                     a = mid + 1;
                 }else if (target < matrix[targetRow][mid]){
                     b = mid - 1;
                 }else {
                     targetCol = mid;
                     break;
                 }
             }
         }
         if (targetRow >= 0 && targetCol >= 0 && target == matrix[targetRow][targetCol]){
             return true;
         }
        return false;
     }
}
