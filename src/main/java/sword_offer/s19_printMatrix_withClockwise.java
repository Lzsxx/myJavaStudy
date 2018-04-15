package sword_offer;

import sun.plugin.javascript.navig.LinkArray;

import javax.swing.*;
import java.util.ArrayList;

//输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
// 例如，如果输入如下矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
// 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.

// 思路：每一圈为一次循环，每一次记录上下左右四个角的坐标，只要还还能组成一个多行多列的矩形，就继续循环
// 否则，就是一行或者一列，或者单独的一个元素，分开处理
public class s19_printMatrix_withClockwise {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix.length < 1){
            return list;
        }
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;

        while (left < right && top < bottom){
            // 处理上面的行
            for (int i = left; i <= right ; i++) {
                list.add(matrix[top][i]);
            }
            // 处理右边的列
            for (int i = top + 1; i <= bottom ; i++) {
                list.add(matrix[i][right]);
            }
            // 处理下面的行
            for (int i = right -1; i >= left ; i--) {
                list.add(matrix[bottom][i]);
            }
            // 处理左边的列
            for (int i = bottom - 1; i > top; i--) {
                list.add(matrix[i][left]);
            }
            left ++;
            right --;
            top ++;
            bottom --;
        }

        // 只剩一个元素
        if (left == right && top == bottom){
            list.add(matrix[left][top]);
        }
        // 只剩一行
        if (left < right && top == bottom){
            for (int i = left; i <= right ; i++) {
                list.add(matrix[top][i]);
            }
        }
        // 只剩一列
        if (left == right && top < bottom){
            for (int i = top; i <= bottom; i++) {
                list.add(matrix[i][left]);
            }
        }
        return list;
    }
}
